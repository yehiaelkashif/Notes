package com.example.notes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.view.View;

import com.example.notes.adapter.NotesRecyclerViewAdapter;
import com.example.notes.data.NoteRepository;
import com.example.notes.data.NoteRepository;
import com.example.notes.model.Note;
import com.example.notes.util.VerticalSpacingItemDecorato;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NotesListActivity extends AppCompatActivity  implements NotesRecyclerViewAdapter.OnNoteListener, View.OnClickListener {
    //UI Componant
    RecyclerView recyclerView;
    //var
    ArrayList<Note> mNotesList=new ArrayList<Note>();;
    NotesRecyclerViewAdapter notesRecyclerViewAdapter;
    private NoteRepository mNoteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

         recyclerView =findViewById(R.id.recyclerView);
        mNoteRepository = new NoteRepository(this);

        initRecyclerView();
        retrieveNotes();
     //   insertFakeNotes();

        setSupportActionBar((Toolbar)findViewById(R.id.notes_toolbar));
        setTitle("Nota");
            findViewById(R.id.fab).setOnClickListener(this);


    }


    private void retrieveNotes() {
        mNoteRepository.retrieveNotesTask().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                if(mNotesList.size() > 0){
                    mNotesList.clear();
                }
                if(notes != null){
                    mNotesList.addAll(notes);
                }
                notesRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }
    private void insertFakeNotes(){
        for(int i = 0; i < 1000; i++){
            Note note = new Note();
            note.setTitle("title #" + i);
            note.setContent("content #: " + i);
            note.setTimestamp("Jan 2019");
            mNotesList.add(note);
        }
        notesRecyclerViewAdapter.notifyDataSetChanged();
    }
  private void   initRecyclerView(){
      LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
      VerticalSpacingItemDecorato  verticalSpacingItemDecorato=new VerticalSpacingItemDecorato(10);
      recyclerView.addItemDecoration(verticalSpacingItemDecorato);
      new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

      notesRecyclerViewAdapter  = new NotesRecyclerViewAdapter(mNotesList,this);
      recyclerView.setAdapter(notesRecyclerViewAdapter);
  }
    @Override
    public void onNoteClick(int position) {

            Intent intent =new Intent(this,NoteActivity.class);
           intent.putExtra("selected_note",mNotesList.get(position));
        startActivity(intent);

    }


    @Override
    public void onClick(View v) {
        Intent intent =new Intent(this,NoteActivity.class);

        startActivity(intent);

    }
    private void deleteNote(Note note) {
        mNotesList.remove(note);
        notesRecyclerViewAdapter.notifyDataSetChanged();
        mNoteRepository.deleteNoteTask(note);
    }
    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            deleteNote(mNotesList.get(viewHolder.getAdapterPosition()));
        }
    };

}
