package com.nullgeodesic.washingtonhhh;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.nullgeodesic.washingtonhhh.click_listener.EventDetailClickListener;
import com.nullgeodesic.washingtonhhh.dto.HashEventDto;
import com.nullgeodesic.washingtonhhh.dto.Kennel;
import com.nullgeodesic.washingtonhhh.service.ContentHolder;


public class KennelActivity extends AppCompatActivity {

    public static final String KENNEL_EXTRA = "kennel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kennel);

        final String kennelId = getIntent().getStringExtra(KENNEL_EXTRA);
        final ImageView kennelLogo = findViewById(R.id.activity_kennel_logo);
        final TextView kennelName = findViewById(R.id.activity_kennel_hash_name);
        final LinearLayout badgeLayout = findViewById(R.id.activity_kennel_badge_layout);
        final TextView kennelDescription = findViewById(R.id.activity_kennel_description);
        final TableLayout detailsTable = findViewById(R.id.activity_kennel_details_table);
        final TextView hareraiserName = findViewById(R.id.activity_kennel_hareraiser_name);
        final TextView founders = findViewById(R.id.activity_kennel_founders);
        final TextView lineage = findViewById(R.id.activity_kennel_lineage);
        final TableRow foundedRow = findViewById(R.id.activity_kennel_tablerow_founding);
        final TextView founded = findViewById(R.id.activity_kennel_founding);
        final TableRow nextEventRow = findViewById(R.id.activity_kennel_tablerow_next_event);
        final TextView nextEventTextView = findViewById(R.id.activity_kennel_next_event);
        final FloatingActionButton emailFab = findViewById(R.id.activity_kennel_email_fab);


        final Kennel kennel = ContentHolder.kennel(kennelId);
        final int kennelDrawableId = kennel.drawableId();
        if (kennelDrawableId == 0) {
            kennelLogo.setVisibility(View.INVISIBLE);
        } else {
            kennelLogo.setImageResource(kennelDrawableId);
        }
        kennelName.setText(kennel.name);
        for (final int badgeDrawableId : kennel.badgeDrawableIds()) {
            final ImageView badgeImageView = new ImageView(getApplicationContext());
            badgeImageView.setImageResource(badgeDrawableId);
            final Resources r = getResources();
            final int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 160, r.getDisplayMetrics());
            final int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, r.getDisplayMetrics());

            final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
            badgeImageView.setLayoutParams(layoutParams);
            badgeLayout.addView(badgeImageView);
        }
        kennelDescription.setText(kennel.description);
        founders.setText(kennel.founders.replaceAll(", ", "\n"));
        lineage.setText(kennel.lineage);
        final String firstHash = kennel.firstHash;
        if (firstHash == null || firstHash.isEmpty()) {
            detailsTable.removeView(foundedRow);
        } else {
            founded.setText(firstHash);
        }
        hareraiserName.setText(kennel.hareraiserName);

        final Integer nextEventPos = ContentHolder.nextEventForKennel(kennelId);
        if (nextEventPos == null) {
            detailsTable.removeView(nextEventRow);
        } else {
            final HashEventDto nextEvent = ContentHolder.allEvents.get(nextEventPos);
            final String eventListing = nextEvent.dateForNextEvent() + "\n" + nextEvent.eventName;
            nextEventTextView.setText(eventListing);
            nextEventTextView.setOnClickListener(new EventDetailClickListener(this, nextEventPos));
        }

        emailFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"fbeavershit@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, kennel.name + " trails");
                email.putExtra(Intent.EXTRA_TEXT, "This is a placeholder email (please don't send it). After deployment this will be sent to: " + kennel.hareraiserEmail);
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Contact the hareraiser"));
            }
        });
    }
}
