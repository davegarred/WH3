package com.nullgeodesic.washingtonhhh.dto;

import android.util.Log;

import com.nullgeodesic.washingtonhhh.service.CommunicationController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HashEventDto {


    private static final String TAG = CommunicationController.class.getSimpleName();

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat LISTING_FORMAT = new SimpleDateFormat("EEE\nMMM d");

    public String googleId;
    public String date;
    public String dateTime;
    public String eventNumber;
    public String hare;
    public String eventName;
    public String description;
    public String mapLink;
    public String kennel;

    public String dateForListing() {
        try {
            final Date eventDate = DATE_FORMAT.parse(date);
            return LISTING_FORMAT.format(eventDate);
        } catch (ParseException e) {
            Log.v(TAG, "error parsing date " + date, e);
            return "";
        }
    }
}
