package com.example.mailappassignment;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Message.class}, version = 1)
public abstract class AppDB extends RoomDatabase{
    public abstract MessageDao messageDao();
}
