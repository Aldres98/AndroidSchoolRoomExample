package com.example.aldres.androidschoolroomexample;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Aldres on 30.06.2018.
 */

@Dao
public interface NoteDAO {

    @Query("SELECT * from Notes where id = :id")
    Note getNoteById(long id);

    @Query("SELECT * FROM notes")
    List<Note> getNotes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Note note);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update (Note note);

    @Delete
    void delete(Note note);



}
