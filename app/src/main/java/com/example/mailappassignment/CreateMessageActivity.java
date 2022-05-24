
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

import java.util.Date;

public class CreateMessageActivity extends AppCompatActivity {

    private AppDB db;
    private Message msg;
    MessageDao msgDao;
    TextView tvSender;
    TextView tvReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "MessagesDb")
                .allowMainThreadQueries().build();

        msgDao = db.messageDao();
        handleSave();

        if(getIntent().getExtras()!=null){ //get commented message from intent
            String id = getIntent().getExtras().getString("id");
            Message comment = msgDao.get(id);
            comment.setCommentId(msg.getId());

            TextView tvSender = findViewById(R.id.textView);
            TextView tvReceiver = findViewById(R.id.textView); //create a new useable text for us to view

            tvSender.setText(comment.getReceiver());
            tvReceiver.setText(comment.getSender());

            //TODO make sender + receiver non changeable

            //TODO updateDB
        }
    }

    private void handleSave(){
        Button btn = findViewById(R.id.btnSend);

        btn.setOnClickListener(view -> {
            //TODO edit and change edt-s
            EditText edtTitle = findViewById(R.id.edt); //get input line (edit text) by id]
            EditText edtSender = findViewById(R.id.edt);
            EditText edtReciver = findViewById(R.id.edt);
            EditText edtContent = findViewById(R.id.edt);
            //TODO title, sender, receiver, content

            boolean exists=false;
            if(edtTitle!=null&&edtSender!=null&&edtReciver!=null&&edtContent!=null) {
                String strTitle = edtTitle.getText().toString();
                String strSender = edtSender.getText().toString();
                String strReciver = edtReciver.getText().toString();
                String strContent = edtContent.getText().toString();
                if (!TextUtils.isEmpty(strTitle)&&
                        !TextUtils.isEmpty(strSender)&&
                        !TextUtils.isEmpty(strReciver)&&
                        !TextUtils.isEmpty(strContent)) {

                    //add message using DAO
                    msg=new Message(strTitle, strSender, strReciver, strContent, new Date());
                    msgDao.insert(msg);

                    //Intent intent = new Intent(this, MainActivity.class); //go back to main activity
                    //startActivity(intent);

                    exists=true;

                    finish();
                }
            }

            if(!exists) //id not exists
                Snackbar.make(view, R.string.wrong_input, Snackbar.LENGTH_LONG).show();
        });
    }
}