package com.example.lutemon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteListAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private Context context;
    public ArrayList<Note> Notes;

    {
        NoteStorage.getInstance();
    }

    public NoteListAdapter(Context context, ArrayList<Note> Notes){
        this.context= context;
        this.Notes = Notes;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.note_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.noteTitleText.setText(Notes.get(position).getTitle() );
        holder.noteContentText.setText(Notes.get(position).getContent());
        holder.noteIdText.setText(Notes.get(position).getIdAsString());
        holder.noteTimeAndDateText.setText(Notes.get(position).getTimeAndDate());
    }

    @Override
    public int getItemCount() {
        return Notes.size();
    }
}
