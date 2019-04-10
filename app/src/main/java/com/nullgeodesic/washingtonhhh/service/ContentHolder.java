package com.nullgeodesic.washingtonhhh.service;

import com.nullgeodesic.washingtonhhh.dto.HashEventDto;
import com.nullgeodesic.washingtonhhh.dto.Kennel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentHolder {

    public static final String EVENT_DETAIL_ID = "event_id";

    public static List<HashEventDto> allEvents = new ArrayList<>();
    public static Map<String, HashEventDto> eventMap = new HashMap<>();
    public static Map<String, Kennel> kennelMap = new HashMap<>();
    private static String message = "";

    public static void setItems(final List<HashEventDto> events, final List<Kennel> kennels) {
        allEvents.clear();
        eventMap.clear();
        for (HashEventDto event : events) {
            allEvents.add(event);
            eventMap.put(event.googleId, event);
        }
        kennelMap.clear();
        for (final Kennel kennel : kennels) {
            kennelMap.put(kennel.id, kennel);
        }
    }

    public static Kennel kennel(final String kennelId) {
        final Kennel kennel = kennelMap.get(kennelId);
        if (kennel == null) {
            return Kennel.UNKNOWN;
        }
        return kennel;
    }

    public static void setMessage(String msg) {
        message = msg;
    }

    public static String getMessage() {
        return message;
    }
}
