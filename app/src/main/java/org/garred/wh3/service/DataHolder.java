package org.garred.wh3.service;

import org.garred.wh3.model.HashEvent;
import org.garred.wh3.wh3.dummy.DummyContent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataHolder {

    private static List<HashEvent> events;
    private static Map<String,HashEvent> eventMap;

    public static boolean dataAvailable() {
        return eventMap != null && !eventMap.isEmpty();
    }

    public static void load() {
        events = new ArrayList<>();
        eventMap = new HashMap<>();
        for (DummyContent.DummyItem item : DummyContent.ITEMS) {
            HashEvent hashEvent = buildEvent(item);
            events.add(hashEvent);
            eventMap.put(item.id, hashEvent);
        }
    }

    public List<HashEvent> events() {
        return events;
    }

    public static List<HashEvent> getEvents() {
        return events;
    }
    public static HashEvent getEvent(String eventId) {
        return eventMap.get(eventId);
    }

    private static HashEvent buildEvent(DummyContent.DummyItem item) {
        return new HashEvent.Builder()
                .withId(item.id)
                .withEventName(item.content)
                .withDescription(item.details)
                .build();
    }

}
