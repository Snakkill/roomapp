package com.example.roomapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Todo {


    @PrimaryKey(autoGenerate = true)
    public int tid;

    public String title;
    public String notes;
    public String author;

    public Todo(){}

    public Todo(String mTitle, String mNotes,String mAuthor) {
        this.title = mTitle;
        this.notes = mNotes;
        this.author=mAuthor;
    }
}
