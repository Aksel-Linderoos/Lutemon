package com.example.lutemon;

import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView;

public class LutemonViewHolder extends RecyclerView.ViewHolder {
    TextView nameText, levelText, hpText, attackText, defText, expText, winsText;
    ImageButton deleteButton, selectButton, feedButton;
    ImageView lutemonImage;

    public LutemonViewHolder(View itemView) {
        super(itemView);
        nameText = itemView.findViewById(R.id.nameText);
        levelText = itemView.findViewById(R.id.levelText);
        hpText = itemView.findViewById(R.id.hpText);
        attackText = itemView.findViewById(R.id.attackText);
        defText = itemView.findViewById(R.id.defText);
        deleteButton = itemView.findViewById(R.id.deleteButton);
        selectButton = itemView.findViewById(R.id.selectButton);
        feedButton = itemView.findViewById(R.id.feedButton);
        lutemonImage = itemView.findViewById(R.id.lutemonImage);
        expText = itemView.findViewById(R.id.expText);
        winsText = itemView.findViewById(R.id.winsText);
    }

}