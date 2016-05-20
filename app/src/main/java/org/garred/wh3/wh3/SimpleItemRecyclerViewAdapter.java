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
import org.garred.wh3.wh3.dummy.DummyContent;

import java.util.List;

public class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

    private final EventListActivity eventListActivity;
//    private final List<DummyContent.DummyItem> values;

    public SimpleItemRecyclerViewAdapter(EventListActivity eventListActivity) {
        this.eventListActivity = eventListActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = DataHolder.getEvents().get(position);
        holder.mIdView.setText(holder.mItem.getId());
        holder.mContentView.setText(holder.mItem.getEventName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eventListActivity.isTwoPane()) {
                    Bundle arguments = new Bundle();
                    arguments.putString(EventDetailFragment.ARG_ITEM_ID, holder.mItem.getId());
                    EventDetailFragment fragment = new EventDetailFragment();
                    fragment.setArguments(arguments);
                    eventListActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.event_detail_container, fragment)
                            .commit();
                } else {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, EventDetailActivity.class);
                    intent.putExtra(EventDetailFragment.ARG_ITEM_ID, holder.mItem.getId());

                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return DataHolder.getEvents().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public HashEvent mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
