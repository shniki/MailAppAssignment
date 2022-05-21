package com.example.mailappassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Message> msgList; //TODO get message list from DAO

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvMessage = findViewById(R.id.rvMessage); //get recycler-view by id
        MessageAdapter adapter = new MessageAdapter(this, msgList); //create adapter
        rvMessage.setAdapter(adapter); //set adapter
        //choose type of layout: linear, horological or staggered
        rvMessage.setLayoutManager(new LinearLayoutManager(this));
    }
}