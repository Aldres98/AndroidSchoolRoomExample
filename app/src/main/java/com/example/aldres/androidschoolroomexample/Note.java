package com.example.aldres.androidschoolroomexample;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by Aldres on 30.06.2018.
 */

@Entity(tableName = "Notes")
public class Note implements Serializable {

    @PrimaryKey(autoGenerate = true)
    long id;
    String title;
    String body;

    public Note(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
