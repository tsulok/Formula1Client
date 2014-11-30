package com.tsulok.formula1client.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tsulok.formula1client.R;
import com.tsulok.formula1client.model.Comment;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private ArrayList<Comment> comments;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView author;
        TextView comment;
        TextView date;
        public ViewHolder(View v){
            super(v);
            comment = (TextView) v.findViewById(R.id.comment);
            author = (TextView) v.findViewById(R.id.author);
            date = (TextView) v.findViewById(R.id.date);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public CommentAdapter(ArrayList<Comment> comments){
        this.comments = comments;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_comment, viewGroup, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Comment comment = comments.get(position);
        viewHolder.comment.setText(comment.getComment());
        viewHolder.author.setText(comment.getAuthor());
        viewHolder.date.setText(comment.getDate());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
}
