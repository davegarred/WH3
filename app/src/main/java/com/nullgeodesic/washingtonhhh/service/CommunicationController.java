package com.nullgeodesic.washingtonhhh.service;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.util.Log;

import com.nullgeodesic.washingtonhhh.SplashActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import static android.provider.Settings.Secure.*;

public class CommunicationController {

    private static final String TAG = CommunicationController.class.getSimpleName();

    public static void kickoff(final Activity activity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final String androidId = getString(activity.getContentResolver(), ANDROID_ID);
                    int versionCode = 0;
                    try {
                        versionCode = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0).versionCode;
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                    final String target = "http://nullgeodesic.com/seahhh/v2?dev=" + androidId + "&vers=" + versionCode;

                    try {
                        retrieve(target);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    Thread.sleep(5000);
                    Log.v(TAG, "ohhai!");
                    final Intent intent = new Intent(activity, SplashActivity.class);
                    activity.startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


    private static void retrieve(String target) throws IOException {
        URL url = new URL(target);
        URLConnection connection = url.openConnection();
        InputStream in = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder builder = new StringBuilder();
        String val;
        while((val = reader.readLine()) != null) {
            builder.append(val);
        }
        in.close();
        Log.v(TAG, builder.toString());
//        Gson gson = new Gson();
//        receivedDto = gson.fromJson(builder.toString(), Map.class);
    }


}
