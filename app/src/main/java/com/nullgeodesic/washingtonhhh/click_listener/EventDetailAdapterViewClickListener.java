package com.nullgeodesic.washingtonhhh.click_listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.nullgeodesic.washingtonhhh.EventDetailActivity;

public class EventDetailAdapterViewClickListener implements AdapterView.OnItemClickListener {

    private final Context context;

    public EventDetailAdapterViewClickListener(Context context) {
        this.context = context;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Intent intent = new Intent(context, EventDetailActivity.class);
        intent.putExtra(EventDetailActivity.EVENT_EXTRA, position);
        context.startActivity(intent);
    }
}
