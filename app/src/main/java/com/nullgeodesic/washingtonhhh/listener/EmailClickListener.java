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
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"fbeavershit@gmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, kennel.name + " trails");
        email.putExtra(Intent.EXTRA_TEXT, "This is a placeholder email (please don't send it). After deployment this will be sent to: " + kennel.hareraiserEmail);
        email.setType("message/rfc822");
        context.startActivity(Intent.createChooser(email, "Contact the hareraiser"));
    }
}
