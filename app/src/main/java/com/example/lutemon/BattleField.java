package com.example.lutemon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BattleField#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BattleField extends Fragment {
    private Lutemon lutemon;
    private Lutemon enemy;
    private TextView hp1, hp2, playerName, enemyName;

    private enum AttackType {
        ACCURATE,
        STRONG,
        DEFEND,
    };

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

        lutemon =  Storage.getInstance().getLutemons().get(0);
        enemy = Lutemon.CreateEnemy(lutemon.GetWins());

        ImageView lutemonImage1 = view.findViewById(R.id.lutemonImage1);
        lutemonImage1.setImageResource(lutemon.GetImage());

        ImageView lutemonImage2 = view.findViewById(R.id.lutemonImage2);
        lutemonImage2.setImageResource(enemy.GetImage());

        hp1 = view.findViewById(R.id.hp1);
        hp2 = view.findViewById(R.id.hp2);

        UpdateHealths();

        playerName = view.findViewById(R.id.playerName);
        playerName.setText(lutemon.GetName());
        enemyName = view.findViewById(R.id.enemyName);
        enemyName.setText(enemy.GetName());

        Button accurateAttackBtn = view.findViewById(R.id.accurateAttackBtn);
        accurateAttackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { AttackTurn(AttackType.ACCURATE); }
        });
        Button strongAttackBtn = view.findViewById(R.id.strongAttackBtn);
        strongAttackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { AttackTurn(AttackType.STRONG); }
        });
        Button defendBtn = view.findViewById(R.id.defendBtn);
        defendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { AttackTurn(AttackType.DEFEND); }
        });

        return view;
    }

    private void UpdateHealths() {
        hp1.setText(String.format("%d / %d", lutemon.GetHealth(), lutemon.GetMaxHealth()));
        hp2.setText(String.format("%d / %d", enemy.GetHealth(), enemy.GetMaxHealth()));
    }

    private void AttackTurn(AttackType attack) {
        switch (attack) {
            case ACCURATE:
                lutemon.Attack(enemy, false);
                break;
            case STRONG:
                lutemon.Attack(enemy, true);
                break;
            case DEFEND:
                lutemon.Defend();
                break;
        }

        if (enemy.GetHealth() <= 0) {
            lutemon.AddWin();
            System.out.println("You won!");
            lutemon.GainExperience((lutemon.GetWins() + 1) * 150);
            BackToHome();
        }

        enemy.RandomAttack(lutemon);

        if (lutemon.GetHealth() <= 0) {
            System.out.println("You lost.");
            BackToHome();
        }

        UpdateHealths();
    }

    private void BackToHome() {
        FragmentActivity activity = getActivity();
        TabLayout tabs = activity.findViewById(R.id.tablayout);
        tabs.selectTab(tabs.getTabAt(1));

        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.framelayout, new Home())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

}
