package com.nullgeodesic.washingtonhhh;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nullgeodesic.washingtonhhh.dto.HashEventDto;
import com.nullgeodesic.washingtonhhh.dto.Kennel;
import com.nullgeodesic.washingtonhhh.service.ContentHolder;

import java.util.regex.Pattern;

public class EventDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.activity_event_detail_toolbar);
        final ImageView kennelLogo = (ImageView) findViewById(R.id.activity_event_detail_kennel_logo);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.activity_event_detail_floating_action_button);
        final TextView eventDescription = (TextView) findViewById(R.id.content_event_detail_description);

        setSupportActionBar(toolbar);

        int position = getIntent().getIntExtra("position", 0);
        final HashEventDto hashEvent = ContentHolder.allEvents.get(position);
        final Kennel kennel = ContentHolder.kennel(hashEvent.kennel);

        final String mapLink = hashEvent.mapLink;
        if (mapLink == null) {
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
        final int kennelDrawableId = kennel.drawableId();
        if (kennelDrawableId == 0) {
            kennelLogo.setVisibility(View.INVISIBLE);
        } else {
            kennelLogo.setImageResource(kennelDrawableId);
        }

        toolbar.setTitle(hashEvent.eventName);

        final boolean hasHtml = Pattern.compile("<[^>]*>").matcher(hashEvent.description).find();
        final CharSequence description = hasHtml ? Html.fromHtml(hashEvent.description) : hashEvent.description;
        eventDescription.setText(description);
    }
}
