package com.nullgeodesic.washingtonhhh;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.nullgeodesic.washingtonhhh.dto.HashEventDto;
import com.nullgeodesic.washingtonhhh.service.ContentHolder;

import java.util.regex.Pattern;

public class EventDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_event_detail_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.activity_event_detail_floating_action_button);


        final TextView eventDescription = (TextView) findViewById(R.id.content_event_detail_description);

        int position = getIntent().getIntExtra("position", 0);
        final HashEventDto hashEvent = ContentHolder.ITEMS.get(position);
        final String mapLink = hashEvent.mapLink;
        if (mapLink == null || mapLink == "") {
            fab.hide();
        } else {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                    mapIntent.setData(Uri.parse(mapLink));
                    startActivity(mapIntent);
                }
            });
        }
        toolbar.setTitle(hashEvent.eventName);

        final boolean hasHtml = Pattern.compile("<[^>]*>").matcher(hashEvent.description).find();
        final CharSequence description = hasHtml ? Html.fromHtml(hashEvent.description) : hashEvent.description;
        eventDescription.setText(description);
    }
}
