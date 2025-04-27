package com.example.lutemon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import java.util.Collections;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LutemonListAdapter extends RecyclerView.Adapter<LutemonViewHolder> {

   private Context context;
   private ArrayList<Lutemon> lutemons;
   private RecyclerView recycler;

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

        holder.selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Storage storage = Storage.getInstance();
                Collections.swap(storage.getLutemons(), 0, holder.getAdapterPosition());

                RecyclerView.Adapter adapter = recycler.getAdapter();
                if (adapter != null) {
                    adapter.notifyItemMoved(holder.getAdapterPosition(), 0);
                }
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Storage storage = Storage.getInstance();
                storage.getLutemons().remove(holder.getAdapterPosition());

                RecyclerView.Adapter adapter = recycler.getAdapter();
                if (adapter != null) {
                    adapter.notifyItemRemoved(holder.getAdapterPosition());
                }
            }
        });

        holder.feedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Storage storage = Storage.getInstance();
                storage.getLutemons().get(holder.getAdapterPosition()).fullHeal();
                RecyclerView.Adapter adapter = recycler.getAdapter();
                adapter.notifyItemChanged(holder.getAdapterPosition());

            }
        });
    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recycler = recyclerView;
    }
}
