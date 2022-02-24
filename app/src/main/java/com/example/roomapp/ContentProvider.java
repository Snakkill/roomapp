package com.example.roomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class ContentProvider extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        ListView cpListView = findViewById(R.id.lv_contentprovider);
        ContentResolver contentResolver = getContentResolver(); //fetch the content provider
        Uri uriCalls = Uri.parse("content://call_log/calls");

        Cursor dataCursor = contentResolver.query(uriCalls,null,null,null,null);

        String[] ColomnNames = new String[]{"number","type"};
        int[] My_TextViews= new int[]{android.R.id.text1,android.R.id.text2};

        CursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,dataCursor,ColomnNames,My_TextViews);

        cpListView.setAdapter(adapter);
    }
}