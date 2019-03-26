package com.nullgeodesic.washingtonhhh;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nullgeodesic.washingtonhhh.service.CommunicationController;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        CommunicationController.kickoff(this);
    }


    public void warn(String s) {
        new AlertDialog.Builder(this)
                .setTitle("Comm Error")
                .setMessage(s)
                .show();
    }


}
