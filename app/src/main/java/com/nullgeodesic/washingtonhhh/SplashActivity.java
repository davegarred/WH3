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

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
            return;
        }

        CommunicationController.kickoff(this);
    }


    public void warnNoNetwork() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.no_network_connection)
                .show();
    }


}
