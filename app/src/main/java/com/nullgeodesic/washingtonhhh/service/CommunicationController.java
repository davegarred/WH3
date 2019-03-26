package com.nullgeodesic.washingtonhhh.service;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.nullgeodesic.washingtonhhh.MainActivity;
import com.nullgeodesic.washingtonhhh.SplashActivity;
import com.nullgeodesic.washingtonhhh.dto.CalendarDto;

import org.json.JSONObject;

import java.io.IOException;

import static android.provider.Settings.Secure.ANDROID_ID;
import static android.provider.Settings.Secure.getString;

public class CommunicationController {

    private static final String TAG = CommunicationController.class.getSimpleName();

    public static void kickoff(final MainActivity activity) {
        final String androidId = getString(activity.getContentResolver(), ANDROID_ID);
        int versionCode = 0;
        try {
            versionCode = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        final String target = "http://nullgeodesic.com/seahhh/v2?dev=" + androidId + "&vers=" + versionCode;

        try {
            final Request request = retrieve(target, activity);
            Volley.newRequestQueue(activity).add(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static Request retrieve(String target, final MainActivity activity) throws IOException {
//        target = "http://nullgedesic.com/seahhh";
        return new StringRequest
                (Request.Method.GET, target, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        CalendarDto cal = new Gson().fromJson(response, CalendarDto.class);
                        Log.v(TAG, cal.toString());
                        final Intent intent = new Intent(activity, SplashActivity.class);
                        activity.startActivity(intent);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v(TAG, "some error: " + error.getMessage());
                        activity.warn(error.getMessage());
                    }
                });
    }


}
