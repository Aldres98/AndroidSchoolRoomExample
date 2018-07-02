package com.example.aldres.androidschoolroomexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Aldres on 02.07.2018.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.NoteViewHolder> {

    List<Note> notes;

    @Override
    public MyRecyclerAdapter.NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note, parent, false);
        NoteViewHolder holder = new NoteViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyRecyclerAdapter.NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.noteTitle.setText(note.getTitle());
        holder.noteBody.setText(note.getBody());

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public MyRecyclerAdapter(List<Note> recyclerData) {
        this.notes = recyclerData;
    }


    class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView noteTitle, noteBody;

        public NoteViewHolder(View noteView) {
            super(noteView);
            noteTitle = noteView.findViewById(R.id.note_title);
            noteBody = noteView.findViewById(R.id.note_body);
            noteView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Note note = notes.get(pos);
                        v.getContext().startActivity(UpdateNoteActivity.newIntent(v.getContext()).putExtra("Note", note));
                    }
                }
            });
        }


    }
}
