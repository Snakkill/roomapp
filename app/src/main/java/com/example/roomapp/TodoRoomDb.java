package com.example.roomapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Todo.class},version = 1)
public abstract class TodoRoomDb extends RoomDatabase {

    public static TodoRoomDb INSTANCE;
    public abstract TodoDao todoDao();

    public static TodoRoomDb getDatabase(Context context) {
        INSTANCE = Room.databaseBuilder(context,TodoRoomDb.class,"Book_DB")
                .build();
        return INSTANCE;
    }
}
