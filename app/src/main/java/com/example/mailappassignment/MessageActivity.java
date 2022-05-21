package com.example.mailappassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Button btn = findViewById(R.id.btnSend);
        btn.setOnClickListener(view -> {
            EditText edtInfo = findViewById(R.id.info); //get input line (edit text) by id]

            boolean exists=false;
            if(edtInfo!=null) {
                String strInfo = edtInfo.getText().toString();
                if (!TextUtils.isEmpty(strInfo)) {
                    //TODO add message using DAO
                    exists=true;
                }
            }

            if(!exists)
                Snackbar.make(view, R.string.wrong_input, Snackbar.LENGTH_LONG).show();
        });
    }
}