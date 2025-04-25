package com.example.lutemon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//public class LutemonListAdapter extends RecyclerView.Adapter<LutemonViewHolder> {
//
//    private Context context;
//    public ArrayList<Lutemon> Lutemons;
//
//    {
//        Storage.getInstance();
//    }
//
//    public LutemonListAdapter(Context context, ArrayList<Lutemon> Lutemons){
//        this.context= context;
//        this.Lutemons = Lutemons;
//    }
//
//    @NonNull
//    @Override
//    public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new LutemonViewHolder(LayoutInflater.from(context).inflate(R.layout.LutemonView, parent, false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {
//        holder.nameText.setText(Lutemons.get(position).getName() );
//        holder.levelText.setText(Lutemons.get(position).getLevel());
//        holder.hpText.setText(Lutemons.get(position).getHP());
//        holder.attackText.setText(Lutemons.get(position).getAttack());
//        holder.defText.setText(Lutemons.get(position).getDefence());
//    }
//
//    @Override
//    public int getItemCount() {
//        return Lutemons.size();
//    }
//}
