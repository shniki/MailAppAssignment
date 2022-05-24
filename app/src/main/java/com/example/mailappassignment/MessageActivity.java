package com.example.mailappassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MessageActivity extends AppCompatActivity {
    private AppDB db;
    private Message msg;
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
            msg = msgDao.get(id);

            //TODO edit and change textView2-s
            TextView tvTitle = findViewById(R.id.textView2);  //get text-view item from our VIEW, so we could set it (by id)
            TextView tvTime = findViewById(R.id.textView2);
            TextView tvSender = findViewById(R.id.textView2);
            TextView tvReceiver = findViewById(R.id.textView2); //create a new useable text for us to view
            TextView tvContent = findViewById(R.id.textView2);

            tvTitle.setText(msg.getTitle());
            tvTime.setText(msg.getTime());
            tvSender.setText(msg.getSender());
            tvReceiver.setText(msg.getReceiver());
            tvContent.setText(msg.getContent());

            //TODO add comments
        }

        Button btn = findViewById(R.id.btnComment);
        btn.setOnClickListener(view -> {
            Intent intent = new Intent(this, CreateMessageActivity.class);
            intent.putExtra("id", msg.getId()); //what are we commenting on
            //intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        });
    }
}