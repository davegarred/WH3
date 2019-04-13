package com.nullgeodesic.washingtonhhh.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.nullgeodesic.washingtonhhh.R;
import com.nullgeodesic.washingtonhhh.dto.Kennel;


public class KennelActivity extends AppCompatActivity {

    public static final String KENNEL_EXTRA = "kennel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kennel);

        final String kennelId = getIntent().getStringExtra(KENNEL_EXTRA);
        final ImageView kennelLogo = findViewById(R.id.activity_kennel_logo);
        final TextView kennelName = findViewById(R.id.activity_kennel_hash_name);
        final TextView kennelDescription = findViewById(R.id.activity_kennel_description);
        final TableRow hareraiserRow = findViewById(R.id.activity_kennel_tablerow_hareraiser);
        final TextView hareraiserName = findViewById(R.id.activity_kennel_hareraiser_name);
        final TableRow foundersRow = findViewById(R.id.activity_kennel_tablerow_founders);
        final TextView founders = findViewById(R.id.activity_kennel_founders);
        final TableRow lineageRow = findViewById(R.id.activity_kennel_tablerow_lineage);
        final TextView lineage = findViewById(R.id.activity_kennel_lineage);
        final FloatingActionButton emailFab = findViewById(R.id.activity_kennel_email_fab);


        final Kennel kennel = ContentHolder.kennel(kennelId);
        final int kennelDrawableId = kennel.drawableId();
        if (kennelDrawableId == 0) {
            kennelLogo.setVisibility(View.INVISIBLE);
        } else {
            kennelLogo.setImageResource(kennelDrawableId);
        }
        kennelDescription.setText(kennel.description);
        hareraiserName.setText(kennel.hareraiserName);
        founders.setText(kennel.founders);
        lineage.setText(kennel.lineage);
        emailFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"fbeavershit@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, kennel.name + " trails");
                email.putExtra(Intent.EXTRA_TEXT,kennel.hareraiserEmail);
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email,"Contact the hareraiser"));
            }
        });
    }
}
