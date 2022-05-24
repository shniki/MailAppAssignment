package com.example.mailappassignment;

import android.content.Context;
import android.content.Intent;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageAdapter extends BaseAdapter {
    //adapter can help us use lists
    //gets a list in its C-TOR, implements important functions
    private List<Message> msgList;

    public MessageAdapter(List<Message> msgList) {
        this.msgList = msgList;
    }

    @Override
    public int getCount() {
        return msgList.size();
    }

    @Override
    public Object getItem(int i) {
        return msgList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Long.parseLong(msgList.get(i).getId());
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null) //haven't been created before, first time shown
        {
            //inflate takes XML file and creates a view item by its info
            View itemView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.message_view_item,viewGroup,false);
            view = itemView;
        }
        //else : view has been created before and hasn't been deleted/freed,
        // we've just scrolled down - not in view, but still exists for a while
        // (jvm's garbage collector will free in later on)

        //TODO edit and change textView-s
        TextView tvTitle = view.findViewById(R.id.textView);  //get text-view item from our VIEW, so we could set it (by id)
        TextView tvTime = view.findViewById(R.id.textView);
        TextView tvSender = view.findViewById(R.id.textView);
        TextView tvReceiver = view.findViewById(R.id.textView); //create a new useable text for us to view

        tvTitle.setText(msgList.get(i).getTitle());
        tvTime.setText(msgList.get(i).getTime());
        tvSender.setText(msgList.get(i).getSender());
        tvReceiver.setText(msgList.get(i).getReceiver());

        return view;
    }
}
