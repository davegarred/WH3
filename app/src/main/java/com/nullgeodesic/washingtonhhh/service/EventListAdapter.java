package com.nullgeodesic.washingtonhhh.service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nullgeodesic.washingtonhhh.R;
import com.nullgeodesic.washingtonhhh.dto.HashEventDto;

import java.util.List;

public class EventListAdapter extends ArrayAdapter<HashEventDto> {

    private final Context context;
    private final List<HashEventDto> events;

    public EventListAdapter(final Context context, final int resource, int textViewResourceId, final List<HashEventDto> events) {
        super(context, resource, textViewResourceId, events);
        this.context = context;
        this.events = events;
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
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(event.kennel.id.equals("UNKNOWN")) {
            return inflater.inflate(R.layout.content_event_list_item_no_kennel, parent, false);
        }
        final View view = inflater.inflate(R.layout.content_event_list_item_kennel, parent, false);
        textView(view, R.id.list_item_kennel_textview, event.kennel.name);
        return view;
    }

    private static void textView(View parentView, int viewId, String text) {
        ((TextView) parentView.findViewById(viewId)).setText(text);
    }
}
