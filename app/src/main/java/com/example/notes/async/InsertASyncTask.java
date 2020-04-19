package com.example.notes.async;

import android.os.AsyncTask;

import com.example.notes.data.NotesDao;
import com.example.notes.model.Note;

public class InsertASyncTask  extends AsyncTask<Note,Void,Void> {

  private NotesDao notesDao;


          public  InsertASyncTask(NotesDao notesDao){
              this.notesDao=notesDao;
          }
    @Override
    protected Void doInBackground(Note... notes) {
        notesDao.insertNotes(notes);
        return null;

          }
}
