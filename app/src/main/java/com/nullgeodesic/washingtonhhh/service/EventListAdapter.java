package com.nullgeodesic.washingtonhhh.service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nullgeodesic.washingtonhhh.R;
import com.nullgeodesic.washingtonhhh.domain.archive.HashEvent;

import java.util.List;

public class EventListAdapter extends ArrayAdapter<HashEvent> {

    private final Context context;
    private final List<HashEvent> events;

    public EventListAdapter(final Context context, final int resource, int textViewResourceId, final List<HashEvent> events) {
        super(context, resource, textViewResourceId, events);
        this.context = context;
        this.events = events;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final HashEvent event = this.events.get(position);
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.content_event_list_item, parent, false);
        textView(view, R.id.list_item_date_textview, event.getDateStringShort());
        textView(view, R.id.list_item_name_textview, event.getEventName());
        return view;
    }

    private static void textView(View parentView, int viewId, String text) {
        ((TextView) parentView.findViewById(viewId)).setText(text);
    }
}
