package com.nullgeodesic.washingtonhhh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nullgeodesic.washingtonhhh.service.CommunicationController;

public class SplashActivity extends AppCompatActivity {

    public static final String EXTRA_EXIT = "EXIT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (getIntent().getBooleanExtra(EXTRA_EXIT, false)) {
            finish();
            return;
        }

        CommunicationController.kickoff(this);
    }

}
