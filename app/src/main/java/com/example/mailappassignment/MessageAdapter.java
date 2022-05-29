package com.example.mailappassignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends BaseAdapter {
    //adapter can help us use lists
    //gets a list in its C-TOR, implements important functions
    private List<Message> msgList;

    private class ViewHolder{
        TextView tvTitle;
        TextView tvTime;
        TextView tvSender;
        TextView tvReceiver;
    }

    public MessageAdapter(List<Message> msgList) {
        this.msgList = msgList;
    }

    @Override
    public int getCount() {
        return msgList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;//msgList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;//Long.parseLong(msgList.get(i).getId());
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null) //haven't been created before, first time shown
        {
            //inflate takes XML file and creates a view item by its info
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.message_view_item,viewGroup,false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.tvTitle = view.findViewById(R.id.tvTitle);  //get text-view item from our VIEW, so we could set it (by id)
            viewHolder.tvTime = view.findViewById(R.id.tvTime);
            viewHolder.tvSender = view.findViewById(R.id.tvSender);
            viewHolder.tvReceiver = view.findViewById(R.id.tvReceiver); //create a new usable text for us to view

            view.setTag(viewHolder);
        }
        //else : view has been created before and hasn't been deleted/freed,
        // we've just scrolled down - not in view, but still exists for a while
        // (jvm's garbage collector will free in later on)

        String from = viewGroup.getContext().getString(R.string.txt_from);
        String to = viewGroup.getContext().getString(R.string.txt_to);

        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.tvTitle.setText(msgList.get(i).getTitle());
        viewHolder.tvTime.setText(msgList.get(i).getTime());
        viewHolder.tvSender.setText(from+msgList.get(i).getSender());
        viewHolder.tvReceiver.setText(to+msgList.get(i).getReceiver());

        return view;
    }
}
