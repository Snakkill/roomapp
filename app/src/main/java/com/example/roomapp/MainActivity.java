package com.example.roomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    TodoRoomDb todoRoomDb;
    TodoDao todoDao;
    EditText etTitle,etNotes,etAuthor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoRoomDb = TodoRoomDb.getDatabase(this);
        todoDao = todoRoomDb.todoDao();  //not sure about this



        etTitle = findViewById(R.id.Input_title);
        etNotes = findViewById(R.id.Input_Note);
        etAuthor = findViewById(R.id.Input_author);

    }

    public void DbHandler(View view) {
        switch (view.getId()){

            case R.id.commitbttn:
                String title = etTitle.getText().toString();
                String notes = etNotes.getText().toString();
                String author = etAuthor.getText().toString();
                insertAsync(title,notes,author);
                etTitle.setText("");
                etNotes.setText("");
                etAuthor.setText("");
                break;
            case R.id.retreviebttn:
                break;
        }
    }

    public void insertAsync(String title, String notes,String author) {

        new InsertTask(title,notes,author).execute();
    }

    class InsertTask extends AsyncTask<Void,Void,Void> {
        String mTitle, mNotes,mAuthor;

        public InsertTask(String title, String notes,String author) {
            mTitle=title;
            mNotes=notes;
            mAuthor=author;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            todoDao.insert(new Todo( mTitle,mNotes,mAuthor));
            return null;
        }
    }
}