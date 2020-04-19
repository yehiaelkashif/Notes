package com.example.notes.data;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.notes.async.DeleteAsyncTask;
import com.example.notes.async.InsertASyncTask;
import com.example.notes.async.UpdateASyncTask;
import com.example.notes.model.Note;

import java.util.List;

public class NoteRepository {
    private NotesRoomDataBase mNoteDatabase;
    public NoteRepository(Context context) {
        mNoteDatabase = mNoteDatabase.getInstance(context);
    }

    public void insertNoteTask(Note note){
        new InsertASyncTask(mNoteDatabase.getNoteDao()).execute(note); }

    public void updateNoteTask(Note note){

        new UpdateASyncTask(mNoteDatabase.getNoteDao()).execute(note);

    }
    public LiveData<List<Note>> retrieveNotesTask() {

        return mNoteDatabase.getNoteDao().getAllNoDos();
    }
    public void deleteNoteTask(Note note){

        new DeleteAsyncTask(mNoteDatabase.getNoteDao()).execute(note);

    }

}
