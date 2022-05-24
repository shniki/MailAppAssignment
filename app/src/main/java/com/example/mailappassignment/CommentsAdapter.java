package com.example.mailappassignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {
    List<Message> msgList;

    public CommentsAdapter(List<Message> msgList) {
        this.msgList = msgList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating...
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_view_item,parent,false);

        return new ViewHolder(view); //returning a view-holder item, which contains the inflated view information
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message msg = msgList.get(position);

        //puts information view (actually in view-holder)
        holder.tvTitle.setText(msg.getTitle());
        holder.tvTime.setText(msg.getTime());
        holder.tvSender.setText(msg.getSender());
        holder.tvReceiver.setText(msg.getReceiver());
        holder.tvContent.setText(msg.getContent());
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //a class inside adapter,
        // this class HOLDS (saves inside) the view of one object from our list

        //lets add inside all view items used for each variable inside one object
        TextView tvTitle;
        TextView tvTime;
        TextView tvSender;
        TextView tvReceiver;
        TextView tvContent;

        //in C-TOR we get all view items from our layout using
        public ViewHolder(@NonNull View itemView) {
            super(itemView); //after it's inflated

            tvTitle = itemView.findViewById(R.id.tvOneTitle);  //get text-view item from our VIEW, so we could set it (by id)
            tvTime = itemView.findViewById(R.id.tvOneTime);
            tvSender = itemView.findViewById(R.id.tvOneSender);
            tvReceiver = itemView.findViewById(R.id.tvOneReceiver); //create a new usable text for us to view
            tvContent = itemView.findViewById(R.id.tvOneContent);
        }
    }

}
