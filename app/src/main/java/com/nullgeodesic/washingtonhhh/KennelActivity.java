package com.nullgeodesic.washingtonhhh;

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

import com.nullgeodesic.washingtonhhh.click_listener.EmailClickListener;
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

        final LinearLayout mainLayout = findViewById(R.id.activity_kennel_layout);
        final String kennelId = getIntent().getStringExtra(KENNEL_EXTRA);
        final ImageView kennelLogo = findViewById(R.id.activity_kennel_logo);
        final TextView kennelName = findViewById(R.id.activity_kennel_hash_name);
        final LinearLayout badgeLayout = findViewById(R.id.activity_kennel_badge_layout);
        final TextView kennelDescription = findViewById(R.id.activity_kennel_description);
        final TableLayout detailsTable = findViewById(R.id.activity_kennel_details_table);
        final TextView founders = findViewById(R.id.activity_kennel_founders);
        final TextView lineage = findViewById(R.id.activity_kennel_lineage);
        final TableRow foundedRow = findViewById(R.id.activity_kennel_tablerow_founding);
        final TextView founded = findViewById(R.id.activity_kennel_founding);
        final TextView nextRunTitle = findViewById(R.id.activity_kennel_next_run_title);
        final LinearLayout nextRunLayout = findViewById(R.id.activity_kennel_next_run_layout);
        final TextView nextRunDate = findViewById(R.id.activity_kennel_next_run_date);
        final TextView nextRunDescription = findViewById(R.id.activity_kennel_next_run_description);
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

        final Integer nextEventPos = ContentHolder.nextEventForKennel(kennelId);
        if (nextEventPos == null) {
            mainLayout.removeView(nextRunTitle);
            mainLayout.removeView(nextRunLayout);
        } else {
            final HashEventDto nextEvent = ContentHolder.allEvents.get(nextEventPos);
            nextRunDate.setText(nextEvent.dateForNextEvent());
            nextRunDescription.setText(nextEvent.eventName);
            nextRunLayout.setOnClickListener(new EventDetailClickListener(this, nextEventPos));
        }

        emailFab.setOnClickListener(new EmailClickListener(this, kennel));
    }
}
