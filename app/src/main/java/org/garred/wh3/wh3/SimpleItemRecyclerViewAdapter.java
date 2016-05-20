package org.garred.wh3.wh3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.garred.wh3.model.HashEvent;
import org.garred.wh3.service.DataHolder;

public class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<HashEventViewHolder> {

    private final EventListActivity eventListActivity;
//    private final List<DummyContent.DummyItem> values;

    public SimpleItemRecyclerViewAdapter(EventListActivity eventListActivity) {
        this.eventListActivity = eventListActivity;
    }

    @Override
    public HashEventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_list_content, parent, false);
        return new HashEventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HashEventViewHolder holder, int position) {
        final HashEvent hashEvent = DataHolder.getEvents().get(position);
        holder.mIdView.setText(hashEvent.getId());
        holder.mContentView.setText(hashEvent.getEventName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eventListActivity.isTwoPane()) {
                    Bundle arguments = new Bundle();
                    arguments.putString(EventDetailFragment.ARG_ITEM_ID, hashEvent.getId());
                    EventDetailFragment fragment = new EventDetailFragment();
                    fragment.setArguments(arguments);
                    eventListActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.event_detail_container, fragment)
                            .commit();
                } else {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, EventDetailActivity.class);
                    intent.putExtra(EventDetailFragment.ARG_ITEM_ID, hashEvent.getId());

                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return DataHolder.getEvents().size();
    }

}
