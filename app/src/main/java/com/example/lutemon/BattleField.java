package com.example.lutemon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BattleField#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BattleField extends Fragment {

    ArrayList<Lutemon> lutemons = Storage.getInstance().getLutemons();
    Lutemon enemy = Lutemon.CreateEnemy();
    public static int battles_won = 0;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BattleField() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BattleField.
     */
    // TODO: Rename and change types and number of parameters
    public static BattleField newInstance(String param1, String param2) {
        BattleField fragment = new BattleField();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_battle_field, container, false);
        ImageView lutemonImage1 = view.findViewById(R.id.lutemonImage1);
        lutemonImage1.setImageResource(lutemons.get(0).GetImage());
        TextView hp1 =view.findViewById(R.id.hp1);
        hp1.setText(String.format("%d / %d", lutemons.get(0).GetHealth(), lutemons.get(0).GetMaxHealth()));
        ImageView lutemonImage2 = view.findViewById(R.id.lutemonImage2);
        lutemonImage2.setImageResource(enemy.GetImage());
        TextView hp2 =view.findViewById(R.id.hp2);
        hp2.setText(String.format("%d / %d", enemy.GetHealth(), enemy.GetMaxHealth()));
        Button accurateAttackBtn = view.findViewById(R.id.accurateAttackBtn);
        accurateAttackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lutemons.get(0).Attack(enemy, false);
                TextView hp2 =view.findViewById(R.id.hp2);
                hp2.setText(String.format("%d / %d", enemy.GetHealth(), enemy.GetMaxHealth()));
            }
        });
        Button strongAttackBtn = view.findViewById(R.id.strongAttackBtn);
        strongAttackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lutemons.get(0).Attack(enemy, true);
                TextView hp2 =view.findViewById(R.id.hp2);
                hp2.setText(String.format("%d / %d", enemy.GetHealth(), enemy.GetMaxHealth()));
            }
        });
        Button defendBtn = view.findViewById(R.id.defendBtn);
        accurateAttackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lutemons.get(0).Defend();
            }
        });
        // Inflate the layout for this fragment

        return view;


    }

}
