package com.nullgeodesic.washingtonhhh.service;

import com.nullgeodesic.washingtonhhh.domain.archive.HashEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentHolder {

    public static final String EVENT_DETAIL_ID = "event_id";

    public static List<HashEvent> ITEMS = new ArrayList<>();
    public static Map<String, HashEvent> ITEM_MAP = new HashMap<>();
    private static String message = "";

    private static void addItem(HashEvent item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId().toString(), item);
    }

    public static void setItems(List<HashEvent> events) {
        ITEMS.clear();
        ITEM_MAP.clear();
        for(HashEvent event : events) {
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
