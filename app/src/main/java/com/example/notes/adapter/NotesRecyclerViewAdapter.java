package com.example.notes.adapter;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.notes.R;
import com.example.notes.model.Note;
import com.example.notes.util.Utility;

import java.util.ArrayList;

public class NotesRecyclerViewAdapter   extends RecyclerView.Adapter<NotesRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Note>mNotesList=new ArrayList<Note>();
      private  OnNoteListener onNoteListener;
    private static final String TAG = "NotesRecyclerAdapter";

    public NotesRecyclerViewAdapter(ArrayList<Note> mNotesList,OnNoteListener onNoteListener) {
        this.mNotesList = mNotesList;
        this.onNoteListener=onNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item,parent,false);
        return new ViewHolder(view,onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        try{
            String month = mNotesList.get(position).getTimestamp().substring(0, 2);
            month = Utility.getMonthFromNumber(month);
            String year = mNotesList.get(position).getTimestamp().substring(3);
            String timestamp = month + " " + year;
            holder.timeStamp.setText(timestamp);
            holder.title.setText(mNotesList.get(position).getTitle());
        }catch (NullPointerException e){
            Log.e(TAG, "onBindViewHolder: Null Pointer: " + e.getMessage() );
        }


    }

    @Override
    public int getItemCount() {
        return mNotesList.size();
    }

    public  class  ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {

        TextView  title,timeStamp;
        OnNoteListener onNoteListener;

        public ViewHolder(@NonNull View itemView,OnNoteListener monNoteListener) {
            super(itemView);
           title= itemView.findViewById(R.id.note_title);
           timeStamp= itemView.findViewById(R.id.note_timestamp);
           itemView.setOnClickListener(this);
          this.onNoteListener=monNoteListener;
        }

        @Override
        public void onClick(View v) {
        onNoteListener.onNoteClick(getAdapterPosition());
        }
    }


    public  interface  OnNoteListener{
        void  onNoteClick(int position );
    }

}
