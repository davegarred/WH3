package com.nullgeodesic.washingtonhhh.dto;

import android.text.format.DateUtils;
import android.util.Log;

import com.nullgeodesic.washingtonhhh.service.CommunicationController;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HashEventDto {


    private static final String TAG = CommunicationController.class.getSimpleName();

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DAY_OF_WEEK_FORMAT = new SimpleDateFormat("EEEE");
    private static final SimpleDateFormat LISTING_FORMAT = new SimpleDateFormat("EEEE\nMMM d");
    private static final SimpleDateFormat NEXT_EVENT_FORMAT = new SimpleDateFormat("EEEE, MMMM d");
    public static final String TIME_FORMAT = "h:mm";

    public String googleId;
    public String date;
    public String dateTime;
    public String eventNumber;
    public String hare;
    public String eventName;
    public String description;
    public String mapLink;
    public String kennel;

    public String time() {
        if (dateTime == null) {
            return "";
        }
        try {
            final DateTime eventDateTime = new DateTime(dateTime);
            if (eventDateTime.getHourOfDay() == 12) {
                return "Noon";
            }
            if (eventDateTime.getHourOfDay() < 12) {
                return eventDateTime.toString(TIME_FORMAT + " a");
            }
            return eventDateTime.toString(TIME_FORMAT);

        } catch (IllegalArgumentException e) {
            Log.v(TAG, "error parsing date " + date, e);
            return "";
        }
    }

    public String dateForListing() {
        final String eventTime = time();
        final String timeSuffix = eventTime.isEmpty() ? "" : "\n" + eventTime;

        try {
            final Date eventDate = DATE_FORMAT.parse(date);
            if (DateUtils.isToday(eventDate.getTime())) {
                return "Today" + timeSuffix;
            }
            Calendar nextWeek = Calendar.getInstance();
            nextWeek.add(Calendar.DATE, 6);
            if (eventDate.before(nextWeek.getTime())) {
                return DAY_OF_WEEK_FORMAT.format(eventDate) + timeSuffix;
            }
            return LISTING_FORMAT.format(eventDate);
        } catch (ParseException e) {
            Log.v(TAG, "error parsing date " + date, e);
            return "";
        }
    }

    public String dateForNextEvent() {
        try {
            final Date eventDate = DATE_FORMAT.parse(date);
            if (DateUtils.isToday(eventDate.getTime())) {
                return "Today";
            }
            Calendar nextWeek = Calendar.getInstance();
            nextWeek.add(Calendar.DATE, 6);
            if (eventDate.before(nextWeek.getTime())) {
                return DAY_OF_WEEK_FORMAT.format(eventDate);
            }
            return NEXT_EVENT_FORMAT.format(eventDate);
        } catch (ParseException e) {
            Log.v(TAG, "error parsing date " + date, e);
            return "";
        }

    }

}
