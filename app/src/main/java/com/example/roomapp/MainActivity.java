package com.example.roomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {
    TodoRoomDb todoRoomDb;
    TodoDao todoDao;
    EditText etTitle,etNotes,etAuthor;
    TextView dbresults;
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
                searchTodo(etTitle.getText().toString());
                break;
        }
    }

    private void searchTodo(String SearchString) {
        dbresults= findViewById(R.id.results);
      new  searchTask(SearchString,dbresults).execute();
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

     class searchTask extends AsyncTask<Void,Void, List<Todo>>{

        String mString;
        TextView mTextView;

        public searchTask(String searchString, TextView dbresults) {


            mString=searchString;
            mTextView=dbresults;
        }

        @Override
        protected List<Todo> doInBackground(Void...Void) {
            return todoDao.findWord(mString);

        }

         @Override
         protected void onPostExecute(List<Todo> todos) {
             super.onPostExecute(todos);
             mTextView.setText(todos.get(0).title + "\n"+ todos.get(0).notes+ "\n"+todos.get(0).author);
         }
     }
    }
