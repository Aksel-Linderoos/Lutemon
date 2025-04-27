package com.example.lutemon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BattleField#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BattleField extends Fragment {

    ImageView lutemonImage1, lutemonImage2;
    TextView hp1, hp2;
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_battle_field, container, false);


    }
    public void beginBattle(View view){
        ArrayList<Lutemon> lutemons = Storage.getInstance().getLutemons();
        lutemonImage1 = getView().findViewById(R.id.lutemonImage1);
        lutemonImage1.setImageResource(lutemons.get(0).GetImage());
        hp1.setText(lutemons.get(0).GetHealth());
    }
}