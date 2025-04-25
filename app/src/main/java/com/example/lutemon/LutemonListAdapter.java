package com.example.lutemon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LutemonListAdapter extends RecyclerView.Adapter<LutemonViewHolder> {

   private Context context;
   private ArrayList<Lutemon> lutemons;

   Storage storage = Storage.getInstance();
   public LutemonListAdapter(Context context, ArrayList<Lutemon> lutemons){
        this.context= context;
        this.lutemons = lutemons;
   }

    @NonNull
    @Override
    public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LutemonViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_lutemon_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {
        Lutemon lutemon = lutemons.get(position);
        holder.nameText.setText(String.format("NAME: %s", lutemon.GetName()));
        holder.levelText.setText(String.format("LVL: %d", lutemon.GetLevel()));
        holder.hpText.setText(String.format("HP: %d / %d", lutemon.GetHealth(), lutemon.GetMaxHealth()));
        holder.attackText.setText(String.format("ATK: %d", lutemon.GetAttack()));
        holder.defText.setText(String.format("DEF: %d", lutemon.GetDefense()));
        holder.lutemonImage.setImageResource(lutemon.GetImage());
    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}
