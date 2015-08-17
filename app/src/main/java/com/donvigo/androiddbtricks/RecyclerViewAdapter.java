package com.donvigo.androiddbtricks;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.donvigo.databaseinterface.model.UserModel;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by vgaidarji on 8/17/15.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<UserModel> items;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.textViewName)
        TextView textViewName;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.inject(this, v);
        }
    }

    public RecyclerViewAdapter(List<UserModel> users) {
        items = users;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_user, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewName.setText(items.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}