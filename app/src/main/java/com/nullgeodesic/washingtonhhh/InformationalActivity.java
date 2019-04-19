package com.nullgeodesic.washingtonhhh;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;

public class InformationalActivity extends AppCompatActivity {

    public static final String INFORMATIONAL_TITLE_EXTRA = "title";
    public static final String INFORMATIONAL_TARGET_EXTRA = "target";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informational);
        final CollapsingToolbarLayout toolBarLayout = findViewById(R.id.activity_informational_toolbar_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_informational_toolbar);
        setSupportActionBar(toolbar);


        final String title = getIntent().getStringExtra(INFORMATIONAL_TITLE_EXTRA);
        toolBarLayout.setTitle(title);
        final int target = getIntent().getIntExtra(INFORMATIONAL_TARGET_EXTRA, 0);
        final View layout = findViewById(target);
        layout.setVisibility(View.VISIBLE);
    }
}
