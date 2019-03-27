package com.nullgeodesic.washingtonhhh;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.nullgeodesic.washingtonhhh.domain.archive.HashEvent;
import com.nullgeodesic.washingtonhhh.service.ContentHolder;

public class EventDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_event_detail_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.activity_event_detail_floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final TextView eventDescription = (TextView) findViewById(R.id.content_event_detail_description);

        int position = getIntent().getIntExtra("position", 0);
        final HashEvent hashEvent = ContentHolder.ITEMS.get(position);
        if(hashEvent.getMapLink() == null || hashEvent.getMapLink() == "") {
            fab.hide();
        }
        toolbar.setTitle(hashEvent.getTitle());
        eventDescription.setText(hashEvent.getDescription());
    }
}
