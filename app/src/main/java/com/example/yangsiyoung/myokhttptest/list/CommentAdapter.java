package com.example.yangsiyoung.myokhttptest.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yangsiyoung.myokhttptest.R;

import java.util.ArrayList;

/**
 * Created by Yang Si Young on 2016-03-21.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private ArrayList<Comment> commentList;

    public CommentAdapter(ArrayList<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View commentView = inflater.inflate(R.layout.list_comment, parent, false);

        ViewHolder viewHolder = new ViewHolder(commentView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommentAdapter.ViewHolder holder, int position) {

        Comment comment = commentList.get(position);

        TextView txtComment = holder.txtComment;
        txtComment.setText(comment.getComment());

    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtComment;

        public ViewHolder(View itemView) {
            super(itemView);

            txtComment = (TextView) itemView.findViewById(R.id.txtComment);

        }

    }
}
