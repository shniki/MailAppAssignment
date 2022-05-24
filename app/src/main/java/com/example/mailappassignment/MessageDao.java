package com.example.mailappassignment;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MessageDao {
    @Query("select * from Message") //get all messages
    List<Message> index();

    @Query("select * from Message where id= :id") //get a specific message
    Message get(String id);

    //@Insert(onConflict = OnConflictStrategy.REPLACE) void insertAll(Message... messages); //add message
    @Insert
    void insert(Message... messages); //add message

    @Update
    void update(Message... messages); //update message

    @Delete
    void delete(Message message); //delete messages
}