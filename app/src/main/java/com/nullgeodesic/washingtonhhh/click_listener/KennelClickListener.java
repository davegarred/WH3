package com.nullgeodesic.washingtonhhh.click_listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.nullgeodesic.washingtonhhh.KennelActivity;

public class KennelClickListener implements View.OnClickListener {

    private final Context context;
    private final String kennelId;

    public KennelClickListener(Context context, String kennelId) {
        this.context = context;
        this.kennelId = kennelId;
    }

    @Override
    public void onClick(View v) {
        final Intent intent = new Intent(context, KennelActivity.class);
        intent.putExtra(KennelActivity.KENNEL_EXTRA, kennelId);
        context.startActivity(intent);
    }
}
