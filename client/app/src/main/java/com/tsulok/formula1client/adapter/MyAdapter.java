package com.tsulok.formula1client.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tsulok.formula1client.R;
import com.tsulok.formula1client.model.Announcement;
import com.tsulok.formula1client.model.IdentifiedModel;
import com.tsulok.formula1client.model.Season;
import com.tsulok.formula1client.model.Team;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<? extends IdentifiedModel> data;
    private ViewHolder.IAdapterClicker onClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        IAdapterClicker listener;
        TextView textView;
        public ViewHolder(TextView v, IAdapterClicker listener){
            super(v);
            textView = v;
            this.listener = listener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listener != null){
                listener.lisItemClicked(v);
            }
        }

        public static interface IAdapterClicker {
            public void lisItemClicked(View v);
        }
    }

    public MyAdapter(ArrayList<? extends IdentifiedModel> data, ViewHolder.IAdapterClicker onClickListener){
        this.data = data;
        this.onClickListener = onClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_textview, viewGroup, false);

        ViewHolder vh = new ViewHolder((TextView)v, onClickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        if(data.get(position) instanceof Announcement){
            Announcement a = (Announcement)data.get(position);
            viewHolder.textView.setText(a.getContent());
        } else if(data.get(position) instanceof Team){
            Team team = (Team)data.get(position);
            viewHolder.textView.setText(team.getName());
        } else if(data.get(position) instanceof Season){
            Season season = (Season)data.get(position);
            viewHolder.textView.setText(Integer.toString(season.getYear()));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public IdentifiedModel getItem(int position){
        if(data != null){
            return data.get(position);
        } else {
            return null;
        }

    }

    public ArrayList<? extends IdentifiedModel> getAllData() {
        return data;
    }
}
