package com.example.daniily.footballmap_audiochampionshipguiderexample.activities.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.daniily.footballmap_audiochampionshipguiderexample.R;

/**
 * @author tko8567
 */
public class RouteRecyclerViewAdapter
        extends RecyclerView.Adapter<RouteRecyclerViewAdapter.RouteRecyclerViewHolder> {

    private OnItemClickListener onItemClickListener = null;

    private String[] data;

    public RouteRecyclerViewAdapter(String[] data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RouteRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.route_list_item, parent, false);
        return new RouteRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RouteRecyclerViewHolder holder, int position) {
        holder.mTextView.setText(data[position]);
        if (onItemClickListener != null) {
            holder.mTextView.setOnClickListener(v -> onItemClickListener.onClick(v, position));
        }
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onClick(View v, int position);
    }

    public static class RouteRecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public RouteRecyclerViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }
}
