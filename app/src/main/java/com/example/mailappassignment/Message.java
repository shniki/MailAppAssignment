package com.example.mailappassignment;

import java.util.Date;

public class Message {
    private String sender;
    private String receiver;
    private String title;
    private String content;
    private Date time;

    public Message(String sender, String receiver, String title, String content, Date time) {
        this.sender = sender;
        this.receiver = receiver;
        this.title = title;
        this.content = content;
        this.time = time;
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

    public Date getTime() {
        return time;
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

    public void setTime(Date time) {
        this.time = time;
    }
}
