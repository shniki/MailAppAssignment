package com.example.mailappassignment;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Message {
    @PrimaryKey
    @NonNull
    private String id;
    private String sender;
    private String receiver;
    private String title;
    private String content;
    private String time;
    private String commentId;

    /*public Message(@NonNull String id, String sender, String receiver, String title, String content, String time, String commentId) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.title = title;
        this.content = content;
        this.time = time;
        this.commentId = commentId;
    }*/

    public Message(String title, String sender, String receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.title = title;
        this.content = content;
        this.time = dateParse();
        this.id = sender+", "+time.toString();
        this.commentId = "null"; //no comment
    }

    private String dateParse(){
        String[] fullDate= new Date().toString().split(" ");
        return fullDate[1]+" "+fullDate[2]+" "+fullDate[5]+", "+fullDate[3];
    }

    public String getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
