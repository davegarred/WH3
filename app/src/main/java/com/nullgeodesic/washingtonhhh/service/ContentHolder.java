package com.nullgeodesic.washingtonhhh.service;

import com.nullgeodesic.washingtonhhh.dto.HashEventDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentHolder {

    public static final String EVENT_DETAIL_ID = "event_id";

    public static List<HashEventDto> ITEMS = new ArrayList<>();
    public static Map<String, HashEventDto> ITEM_MAP = new HashMap<>();
    private static String message = "";

    private static void addItem(HashEventDto item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.googleId, item);
    }

    public static void setItems(final List<HashEventDto> events) {
        ITEMS.clear();
        ITEM_MAP.clear();
        for(HashEventDto event : events) {
            addItem(event);
        }
    }

    public static void setMessage(String msg) {
        message = msg;
    }

    public static String getMessage() {
        return message;
    }
}
