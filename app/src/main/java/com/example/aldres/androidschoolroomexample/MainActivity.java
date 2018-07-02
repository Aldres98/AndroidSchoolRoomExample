package com.example.aldres.androidschoolroomexample;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyRecyclerAdapter adapter;
    FloatingActionButton fab;
    List<Note> notes;
    NoteDAO noteDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        fab = findViewById(R.id.fab_add_note);

        notes = new ArrayList<>();
        noteDAO = NoteDatabase.getInstance(MainActivity.this).getNoteDao();
        loadNotes();
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AddNoteActivity.newIntent(MainActivity.this));
            }
        });
    }



    private void loadNotes(){
        HandlerThread handlerThread = new HandlerThread("MyHandlerThread");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        Handler handler = new Handler(looper);
        handler.post(new Runnable() {
            @Override
            public void run() {
                notes = noteDAO.getNotes();
                adapter = new MyRecyclerAdapter(notes);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    public static final Intent newIntent(Context context){
        return new Intent(context, MainActivity.class);
    }
}
