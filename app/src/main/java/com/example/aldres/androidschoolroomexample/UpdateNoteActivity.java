package com.example.aldres.androidschoolroomexample;

import android.content.Context;
import android.content.Intent;
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

public class UpdateNoteActivity extends AppCompatActivity {

    EditText titleEdit, bodyEdit;
    Button updateButton;
    Note note;
    NoteDAO noteDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);
        titleEdit = findViewById(R.id.title_edit_update);
        bodyEdit = findViewById(R.id.body_edit_update);
        updateButton = findViewById(R.id.update_note_button);
        noteDAO = NoteDatabase.getInstance(UpdateNoteActivity.this).getNoteDao();
        note = (Note) getIntent().getSerializableExtra("Note");
        titleEdit.setText(note.getTitle());
        bodyEdit.setText(note.getBody());

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HandlerThread handlerThread = new HandlerThread("MyHandlerThread");
                handlerThread.start();
                Looper looper = handlerThread.getLooper();
                Handler handler = new Handler(looper);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (TextUtils.isEmpty(titleEdit.getText())) {
                            Toast.makeText(getApplicationContext(), "Title can't be blank", Toast.LENGTH_SHORT).show();
                        }
                        if (TextUtils.isEmpty(bodyEdit.getText())) {
                            Toast.makeText(getApplicationContext(), "Body can't be blank", Toast.LENGTH_SHORT).show();
                        } else {
                            note.setBody(bodyEdit.getText().toString());
                            note.setTitle(titleEdit.getText().toString());
                            noteDAO.update(note);
                        }
                    }
                });
                startActivity(MainActivity.newIntent(UpdateNoteActivity.this));
            }
        });
    }

    public static final Intent newIntent(Context context){
        Intent intent = new Intent(context, UpdateNoteActivity.class);
        return intent;
    }
}
