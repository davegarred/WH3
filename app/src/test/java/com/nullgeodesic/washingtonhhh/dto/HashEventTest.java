package com.nullgeodesic.washingtonhhh.dto;

import android.text.format.DateUtils;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;


public class HashEventTest {

    @Mock
    private DateUtils dateUtils;

    @Test
    public void testDate() {
        final HashEventDto hashEvent = new HashEventDto();
        hashEvent.date = "2022-09-30";
        assertEquals("Friday, September 30", hashEvent.dateForNextEvent());
        assertEquals("Friday\nSep 30", hashEvent.dateForListing());
    }

    @Test
    public void testTime() {
        final HashEventDto hashEvent = new HashEventDto();
        assertEquals("", hashEvent.time());

        hashEvent.dateTime = "2019-04-21T12:00:00-07:00";
        assertEquals("12:00 PM", hashEvent.time());

        hashEvent.dateTime = "2019-04-21T15:00:00-07:00";
        assertEquals("3:00 PM", hashEvent.time());

        hashEvent.dateTime = "2019-04-21T00:00:00-07:00";
        assertEquals("12:00 AM", hashEvent.time());

        hashEvent.dateTime = "2019-04-21";
        assertEquals("12:00 AM", hashEvent.time());
    }

    @Test
    public void testTime_badTime() {
        final HashEventDto hashEvent = new HashEventDto();

        hashEvent.dateTime = "this is not a time";
        assertEquals("", hashEvent.time());
    }
}
