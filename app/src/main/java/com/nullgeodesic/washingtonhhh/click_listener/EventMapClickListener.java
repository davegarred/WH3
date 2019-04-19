package com.nullgeodesic.washingtonhhh.click_listener;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

public class EventMapClickListener implements View.OnClickListener {

    private final Context context;
    private final String mapLink;

    public EventMapClickListener(Context context, String mapLink) {
        this.context = context;
        this.mapLink = mapLink;
    }

    @Override
    public void onClick(View v) {
        final Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        mapIntent.setData(Uri.parse(mapLink));
        context.startActivity(mapIntent);
    }
}
