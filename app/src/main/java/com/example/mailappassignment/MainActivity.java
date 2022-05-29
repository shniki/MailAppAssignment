package com.example.mailappassignment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AppDB db;
    private ListView lvMessage;
    //private List<String> messages; //list of IDs
    private List<Message> msgDb; //get message list from DAO
    private MessageAdapter adapter;
    private MessageDao msgDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(R.string.title_all);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "MessagesDb")
                .allowMainThreadQueries().build();

        msgDao = db.messageDao();

        ImageButton btn = findViewById(R.id.btnAddNew);

        btn.setOnClickListener(view->{
            Intent intent = new Intent(this, CreateMessageActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        handleMessages();
    }

    private void handleMessages(){

        loadMessages();

        //putting in adapter
        adapter = new MessageAdapter(msgDb);

        //set list view item
        lvMessage = findViewById(R.id.lvMessage);
        lvMessage.setAdapter(adapter);

        //add button listeners
        lvMessage.setOnItemClickListener((adapterView, view, i, l)->{
            Intent intent = new Intent(this, MessageActivity.class);
            intent.putExtra("id", msgDb.get(i).getId());
            startActivity(intent);
        });
        lvMessage.setOnItemLongClickListener((adapterView, view, i, l)-> {
            //messages.remove(i);
            Message msg = msgDb.remove(i);
            msgDao.delete(msg);
            adapter.notifyDataSetChanged();
            return true;
        });
    }

    private void loadMessages(){
        //messages.clear();
        msgDb = msgDao.index();
        Collections.reverse(msgDb); //messages will show be from new to old
        //for (Message msg : msgDb)
        //    messages.add(msg.getId());

        //adapter.notifyDataSetChanged();
    }
}