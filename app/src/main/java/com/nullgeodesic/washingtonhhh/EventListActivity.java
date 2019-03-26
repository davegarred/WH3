package com.nullgeodesic.washingtonhhh;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nullgeodesic.washingtonhhh.R;
import com.nullgeodesic.washingtonhhh.domain.archive.HashEvent;
import com.nullgeodesic.washingtonhhh.service.ContentHolder;

import java.util.ArrayList;
import java.util.List;

public class EventListActivity extends AppCompatActivity {

    private ArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final ListView listView = (ListView) findViewById(R.id.content_event_list_view);
        final List<String> eventList = new ArrayList<>();
        for(final HashEvent item : ContentHolder.ITEMS) {
            eventList.add(item.getEventName());
        }
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, eventList);
        listView.setAdapter(arrayAdapter);
    }

}
