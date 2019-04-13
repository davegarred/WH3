package com.nullgeodesic.washingtonhhh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nullgeodesic.washingtonhhh.dto.HashEventDto;
import com.nullgeodesic.washingtonhhh.service.ContentHolder;
import com.nullgeodesic.washingtonhhh.service.EventListAdapter;

public class EventListActivity extends AppCompatActivity {

    private static final String TAG = EventListActivity.class.getSimpleName();

    private ArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ListView listView = (ListView) findViewById(R.id.content_event_list_view);
        final ArrayAdapter<HashEventDto> arrayAdapter = new EventListAdapter(this, R.layout.content_event_list_item_no_kennel, R.id.list_item_name_textview, ContentHolder.allEvents);
        listView.setAdapter(arrayAdapter);

        final Activity currentActivity = this;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent = new Intent(currentActivity, EventDetailActivity.class);
                intent.putExtra(EventDetailActivity.EVENT_EXTRA, position);
                currentActivity.startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        final Intent intent = new Intent(this, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }

}
