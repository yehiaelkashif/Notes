package com.example.notes.async;

import android.os.AsyncTask;

import com.example.notes.data.NotesDao;
import com.example.notes.model.Note;

public class DeleteAsyncTask  extends AsyncTask<Note, Void, Void> {


    private NotesDao mNoteDao;

    public DeleteAsyncTask(NotesDao dao) {
        mNoteDao = dao;
    }
    @Override
    protected Void doInBackground(Note... notes) {

                mNoteDao.delete(notes);
        return null;
    }
}
