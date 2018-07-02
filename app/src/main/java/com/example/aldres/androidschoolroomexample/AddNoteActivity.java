package com.example.aldres.androidschoolroomexample;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class AddNoteActivity extends AppCompatActivity {

    EditText titleEdit;
    EditText bodyEdit;
    Button addNoteButton;
    NoteDAO noteDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        titleEdit = findViewById(R.id.title_edit);
        bodyEdit = findViewById(R.id.body_edit);
        addNoteButton = findViewById(R.id.add_note_button);

        HandlerThread handlerThread = new HandlerThread("MyHandlerThread");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        noteDAO = NoteDatabase.getInstance(AddNoteActivity.this).getNoteDao();
        final Handler handler = new Handler(looper);

        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(titleEdit.getText())) {
                    Toast.makeText(getApplicationContext(), "Title can't be blank", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(bodyEdit.getText())) {
                    Toast.makeText(getApplicationContext(), "Body can't be blank", Toast.LENGTH_SHORT).show();
                } else
                    {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            noteDAO.insert(new Note(titleEdit.getText().toString(), bodyEdit.getText().toString()));
                        }
                    });
                    startActivity(MainActivity.newIntent(AddNoteActivity.this));
                }
            }
        });

    }

    public static final Intent newIntent(Context context){
        return new Intent(context, AddNoteActivity.class);
    }

}
