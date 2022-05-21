package com.example.mailappassignment;

import android.content.Context;
import android.content.Intent;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    //extends RecyclerView's adapters which is a particle view,
    // RecyclerView is a repeatable object in our view page, for example: Post

    //adapter can help us use lists, reminds me of an iterator
    //gets a list in its C-TOR, implements important functions
    private List<Message> msgList;

    private Context context;

    public MessageAdapter(Context context, List<Message> msgList) {
        this.context = context;
        this.msgList = msgList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating...
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_item_layout,parent,false); //TODO layout messgae

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //single click - goto specific message
                Intent specificMessageIntent = new Intent(context, MessageActivity.class);
                context.startActivity(specificMessageIntent);
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //long click - delete message
                //TODO delete message using DAO
                return false;
            }
        });

        return new ViewHolder(view); //returning a view-holder item, which contains the inflated view information
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //postList[position] -> holder
        Message msg = msgList.get(position);

        //puts information view (actually in view-holder)
        holder.tvTitle.setText(msg.getTitle());
        holder.tvTime.setText(msg.getTime().toString());
        holder.tvSender.setText(msg.getSender());
        holder.tvReceiver.setText(msg.getReceiver());
    }

    @Override
    public int getItemCount() { //IMPORTANT!!!!!!!
        return msgList.size();
    }





    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvTime;
        TextView tvSender;
        TextView tvReceiver;

        //in C-TOR we get all view items from our layout using
        public ViewHolder(@NonNull View itemView) {
            super(itemView); //after it's inflated

            tvTitle = itemView.findViewById(R.id.textView);  //get text-view item from our VIEW, so we could set it (by id)
            tvTime = itemView.findViewById(R.id.textView3);
            tvSender = itemView.findViewById(R.id.imageView);
            tvReceiver = itemView.findViewById(R.id.textView2);
        }
    }
}
