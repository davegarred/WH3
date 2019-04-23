package com.nullgeodesic.washingtonhhh.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.nullgeodesic.washingtonhhh.EventDetailActivity;

public class EventDetailClickListener implements View.OnClickListener {

    private final Context context;
    private final int eventPosition;

    public EventDetailClickListener(Context context, int eventPosition) {
        this.context = context;
        this.eventPosition = eventPosition;
    }


    @Override
    public void onClick(View v) {
        final Intent intent = new Intent(context, EventDetailActivity.class);
        intent.putExtra(EventDetailActivity.EVENT_EXTRA, eventPosition);
        context.startActivity(intent);
    }
}
