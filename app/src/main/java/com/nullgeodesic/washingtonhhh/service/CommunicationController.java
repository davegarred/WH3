package com.nullgeodesic.washingtonhhh.service;

import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.nullgeodesic.washingtonhhh.EventListActivity;
import com.nullgeodesic.washingtonhhh.SplashActivity;
import com.nullgeodesic.washingtonhhh.dto.CalendarDto;

import java.io.IOException;

public class CommunicationController {

    private static final String TAG = CommunicationController.class.getSimpleName();

    public static void kickoff(final SplashActivity activity) {
        final String target = "https://seahhh.nullgeodesic.com/seahhh";

        try {
            final Request request = retrieve(target, activity);
            Volley.newRequestQueue(activity).add(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static Request retrieve(String target, final SplashActivity activity) throws IOException {
        return new StringRequest
                (Request.Method.GET, target, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        CalendarDto cal = new Gson().fromJson(response, CalendarDto.class);
                        ContentHolder.setItems(cal.events);
                        Log.v(TAG, cal.toString());
                        final Intent intent = new Intent(activity, EventListActivity.class);
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
