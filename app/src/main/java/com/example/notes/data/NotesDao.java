package com.example.notes.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notes.model.Note;

import java.util.List;

@Dao
public interface NotesDao {

//CRud

    @Insert
    long [] insertNotes(Note ...note);

     @Delete
      int delete(Note ...note);

    @Update
    int update(Note ...note);
    @Query("SELECT *  FROM notes_table")
    LiveData< List<Note>>getAllNoDos() ;



}
