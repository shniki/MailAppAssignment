package com.example.mailappassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AppDB db;
    private ListView lvMessage;
    private List<String> messages; //list of IDs
    private List<Message> msgDb; //get message list from DAO
    private MessageAdapter adapter;
    private MessageDao msgDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "MessagesDb")
                .allowMainThreadQueries().build();

        msgDao = db.messageDao();
        handleMessages();


    }

    private void handleMessages(){
        lvMessage = findViewById(R.id.lvMessage); //get recycler-view by id
        messages = new ArrayList<>();
        adapter = new MessageAdapter(msgDb); //create adapter

        loadMessages();
        lvMessage.setAdapter(adapter); //set adapter
        lvMessage.setOnItemClickListener((adapterView, view, i, l)->{
            Intent intent = new Intent(this, MessageActivity.class);
            intent.putExtra("id", msgDb.get(i).getId());
            startActivity(intent);
        });
        lvMessage.setOnItemLongClickListener((adapterView, view, i, l)-> {
            messages.remove(i);
            Message msg = msgDb.remove(i);
            msgDao.delete(msg);
            adapter.notifyDataSetChanged();
            return true;
        });
    }

    private void loadMessages(){
        messages.clear();
        msgDb = msgDao.index();
        for (Message msg : msgDb)
            messages.add(msg.getId());

        adapter.notifyDataSetChanged();
    }
}