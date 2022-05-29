
package com.example.mailappassignment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.android.material.snackbar.Snackbar;

public class CreateMessageActivity extends AppCompatActivity {

    private AppDB db;
    private Message msg;
    private Message commentOn;
    MessageDao msgDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);

        getSupportActionBar().setTitle(R.string.title_new);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "MessagesDb")
                .allowMainThreadQueries().build();

        msgDao = db.messageDao();
        commentOn=null;
        handleSave();

        if(getIntent().getExtras()!=null){ //get commented message from intent
            getSupportActionBar().setTitle(R.string.title_reply);

            String id = getIntent().getExtras().getString("id");
            commentOn = msgDao.get(id);

            EditText edtSender = findViewById(R.id.edtSender);
            EditText edtReceiver = findViewById(R.id.edtReceiver); //create a new usable text for us to view

            String str1 = commentOn.getReceiver();
            String str2 = commentOn.getSender();

            edtSender.setText(str1);
            edtReceiver.setText(str2);

            edtSender.setEnabled(false);
            edtReceiver.setEnabled(false);
        }
    }

    private void handleSave(){
        ImageButton btn = findViewById(R.id.btnSend);

        btn.setOnClickListener(view -> {
            EditText edtTitle = findViewById(R.id.edtTitle); //get input line (edit text) by id]
            EditText edtSender = findViewById(R.id.edtSender);
            EditText edtReceiver = findViewById(R.id.edtReceiver);
            EditText edtContent = findViewById(R.id.edtContent);

            boolean exists=false;
            if(edtTitle!=null&&edtSender!=null&&edtReceiver!=null&&edtContent!=null) {
                String strTitle = edtTitle.getText().toString();
                String strSender = edtSender.getText().toString();
                String strReceiver = edtReceiver.getText().toString();
                String strContent = edtContent.getText().toString();
                if(!Patterns.EMAIL_ADDRESS.matcher(strSender).matches()||!Patterns.EMAIL_ADDRESS.matcher(strReceiver).matches()) {
                    Snackbar.make(view, R.string.wrong_emails, Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (!TextUtils.isEmpty(strTitle)&&
                        !TextUtils.isEmpty(strSender)&&
                        !TextUtils.isEmpty(strReceiver)&&
                        !TextUtils.isEmpty(strContent)) {

                    //add message using DAO
                    msg=new Message(strTitle, strSender, strReceiver, strContent);
                    if(commentOn!=null) {
                        commentOn.setCommentId(msg.getId());
                        msgDao.update(commentOn);
                    }
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