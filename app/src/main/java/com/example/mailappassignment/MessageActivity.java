package com.example.mailappassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {
    private AppDB db;
    private List<Message> msgList;
    private CommentsAdapter adapter;
    MessageDao msgDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "MessagesDb")
                .allowMainThreadQueries().build();

        msgDao = db.messageDao();

        if(getIntent().getExtras()!=null){ //get message from intent
            String id = getIntent().getExtras().getString("id");

            createList(id);

            RecyclerView recyclerView = findViewById(R.id.rvComments); //get recycler-view by id
            adapter = new CommentsAdapter(msgList); //create adapter
            recyclerView.setAdapter(adapter); //set adapter
            //choose type of layout: linear, horological or staggered
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

        Button btn = findViewById(R.id.btnComment);
        btn.setOnClickListener(view -> {
            Intent intent = new Intent(this, CreateMessageActivity.class);
            intent.putExtra("id", msgList.get(msgList.size()-1).getId()); //what are we commenting on
            //intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter.notifyDataSetChanged();
    }

    private void createList(String id){
        if(msgList!=null)
            msgList.clear();
        msgList=new ArrayList<>();
        Message msgPos; //get first message
        while(!id.equals("null")){
            msgPos = msgDao.get(id); //get message
            msgList.add(msgPos); //add message to list
            id = msgPos.getCommentId(); //get Next ID
        }
    }
}