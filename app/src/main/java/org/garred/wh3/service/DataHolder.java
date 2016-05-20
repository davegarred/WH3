package org.garred.wh3.service;

import org.garred.wh3.model.HashEvent;
import org.garred.wh3.wh3.dummy.DummyContent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataHolder {

    public boolean dataAvailable() {
        return true;
    }

    public void reload() {
    }

    public List<HashEvent> events() {
        List<HashEvent> events = new ArrayList<>();
        for (DummyContent.DummyItem item : DummyContent.ITEMS) {
            events.add(new HashEvent.Builder()
                    .withDescription(item.details)
                    .build());
        }
        return events;
    }
}
