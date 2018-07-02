package com.example.aldres.androidschoolroomexample;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Aldres on 30.06.2018.
 */

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract NoteDAO getNoteDao();

    private static NoteDatabase noteDatabase;

    public static NoteDatabase getInstance(Context context) {
        if (noteDatabase == null) {
            noteDatabase = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class
                    ,"NoteDB").build();
            return noteDatabase;
        }
        return noteDatabase;
    }

}
