package org.garred.wh3.wh3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.garred.wh3.wh3.dummy.DummyContent;

import java.util.List;

public class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

    private final EventListActivity eventListActivity;
    private final List<DummyContent.DummyItem> values;

    public SimpleItemRecyclerViewAdapter(EventListActivity eventListActivity, List<DummyContent.DummyItem> items) {
        this.eventListActivity = eventListActivity;
        this.values = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = values.get(position);
        holder.mIdView.setText(values.get(position).id);
        holder.mContentView.setText(values.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eventListActivity.isTwoPane()) {
                    Bundle arguments = new Bundle();
                    arguments.putString(EventDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                    EventDetailFragment fragment = new EventDetailFragment();
                    fragment.setArguments(arguments);
                    eventListActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.event_detail_container, fragment)
                            .commit();
                } else {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, EventDetailActivity.class);
                    intent.putExtra(EventDetailFragment.ARG_ITEM_ID, holder.mItem.id);

                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DummyContent.DummyItem mItem;

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
