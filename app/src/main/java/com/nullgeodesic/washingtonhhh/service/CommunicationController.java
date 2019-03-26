package com.nullgeodesic.washingtonhhh.service;

import android.app.Activity;
import android.content.Intent;

import com.nullgeodesic.washingtonhhh.SplashActivity;

public class CommunicationController {

    public static void kickoff(final Activity activity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    final Intent intent = new Intent(activity, SplashActivity.class);
                    activity.startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


}
