package com.example.roomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    TodoRoomDb todoRoomDb;
    TodoDao todoDao;
    EditText etTitle,etNotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoRoomDb = TodoRoomDb.getDatabase(this);
        todoDao = todoRoomDb.todoDao();  //not sure about this



        etTitle = findViewById(R.id.Input_title);
        etNotes = findViewById(R.id.Input_Note);
    }

    public void DbHandler(View view) {
        switch (view.getId()){

            case R.id.commitbttn:
                String title = etTitle.getText().toString();
                String notes = etNotes.getText().toString();
                insertAsync(title,notes);
                etTitle.setText("");
                etNotes.setText("");
                break;
            case R.id.retreviebttn:
                break;
        }
    }

    public void insertAsync(String title, String notes) {

        new InsertTask(title,notes).execute();
    }

    class InsertTask extends AsyncTask<Void,Void,Void> {
        String mTitle, mNotes;

        public InsertTask(String title, String notes) {
            mTitle=title;
            mNotes=notes;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            todoDao.insert(new Todo( mTitle,mNotes));
            return null;
        }
    }
}