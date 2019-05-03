package com.nullgeodesic.washingtonhhh.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.nullgeodesic.washingtonhhh.dto.Kennel;

public class EmailClickListener implements View.OnClickListener {


    private final Context context;
    private final Kennel kennel;

    public EmailClickListener(Context context, Kennel kennel) {
        this.context = context;
        this.kennel = kennel;
    }


    @Override
    public void onClick(View v) {
        final Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{kennel.hareraiserEmail});
        email.putExtra(Intent.EXTRA_SUBJECT, kennel.name + " trails");
        email.putExtra(Intent.EXTRA_TEXT, "Dear " + kennel.hareraiserName + ",\n\nI'd like to hare a trail.");
        email.setType("message/rfc822");
        context.startActivity(Intent.createChooser(email, "Contact the hareraiser"));
    }
}
