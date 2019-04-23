package com.nullgeodesic.washingtonhhh.service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nullgeodesic.washingtonhhh.R;
import com.nullgeodesic.washingtonhhh.dto.HashEventDto;
import com.nullgeodesic.washingtonhhh.dto.Kennel;

import java.util.List;

public class EventListAdapter extends ArrayAdapter<HashEventDto> {

    private final Context context;
    private final List<HashEventDto> events;

    public EventListAdapter(final Context context) {
        super(context, R.layout.content_event_list_item_no_kennel, R.id.list_item_name_textview, ContentHolder.allEvents);
        this.context = context;
        this.events = ContentHolder.allEvents;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final HashEventDto event = this.events.get(position);
        final View view = baseView(parent, event);
        textView(view, R.id.list_item_date_textview, event.dateForListing());
        textView(view, R.id.list_item_name_textview, event.eventName);
        return view;
    }

    private View baseView(ViewGroup parent, final HashEventDto event) {
        final Kennel kennel = ContentHolder.kennel(event.kennel);
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (kennel.isUnknown()) {
            return inflater.inflate(R.layout.content_event_list_item_no_kennel, parent, false);
        }
        final View view = inflater.inflate(R.layout.content_event_list_item_kennel, parent, false);
        textView(view, R.id.list_item_kennel_textview, formatKennelName(kennel, event));
        return view;
    }

    private String formatKennelName(Kennel kennel, HashEventDto event) {
        if(event.eventNumber != null && !event.eventNumber.isEmpty()){
            return kennel.name + "  - run #" + event.eventNumber;
        }
        return kennel.name;
    }

    private static void textView(View parentView, int viewId, String text) {
        ((TextView) parentView.findViewById(viewId)).setText(text);
    }
}
