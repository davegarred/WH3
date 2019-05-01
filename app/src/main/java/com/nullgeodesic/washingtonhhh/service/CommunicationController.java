package com.nullgeodesic.washingtonhhh.service;

import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.nullgeodesic.washingtonhhh.MainActivity;
import com.nullgeodesic.washingtonhhh.SplashActivity;
import com.nullgeodesic.washingtonhhh.dto.CalendarDto;

import java.io.IOException;
import java.util.Calendar;

public class CommunicationController {

    private static final String TAG = CommunicationController.class.getSimpleName();
    private static final String TARGET = "https://seahhh.nullgeodesic.com/seahhh";

    public static void kickoff(final SplashActivity activity) {
        try {
            final Request request = retrieveAndStartMain(activity);
            Volley.newRequestQueue(activity).add(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void update(final MainActivity activity) {
        try {
            final Request request = retrieve(activity);
            Volley.newRequestQueue(activity).add(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static Request retrieveAndStartMain(final SplashActivity activity) throws IOException {
        return new StringRequest
                (Request.Method.GET, TARGET, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        final CalendarDto cal = new Gson().fromJson(response, CalendarDto.class);
                        Log.v(TAG, "events first pulled at " + Calendar.getInstance().getTime());
                        PersistenceService.storeV1(activity, response);
                        ContentHolder.setItems(cal.events, cal.kennels);
                        final Intent intent = new Intent(activity, MainActivity.class);
                        activity.startActivity(intent);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v(TAG, "network error: " + error.getMessage());
                        final String storedEvents = PersistenceService.retrieveV1(activity);
                        if(storedEvents != null) {
                            final CalendarDto cal = new Gson().fromJson(storedEvents, CalendarDto.class);
                            ContentHolder.setItems(cal.events, cal.kennels);
                        }
                        final Intent intent = new Intent(activity, MainActivity.class);
                        intent.putExtra(MainActivity.EXTRA_NO_NETWORK, true);
                        activity.startActivity(intent);

                    }
                });
    }

    private static Request retrieve(final MainActivity activity) throws IOException {
        return new StringRequest
                (Request.Method.GET, TARGET, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        CalendarDto cal = new Gson().fromJson(response, CalendarDto.class);
                        ContentHolder.setItems(cal.events, cal.kennels);
                        Log.v(TAG, "events updated at " + Calendar.getInstance().getTime());
                        activity.listUpdated();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v(TAG, "network error updating events: " + error.getMessage());
                        activity.warnNoNetwork();
                    }
                });
    }


}
