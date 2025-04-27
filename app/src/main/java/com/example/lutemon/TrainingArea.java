package com.example.lutemon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrainingArea#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrainingArea extends Fragment {
    private ImageView image;
    private TextView name;
    private TextView exp;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TrainingArea() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrainingArea.
     */
    // TODO: Rename and change types and number of parameters
    public static TrainingArea newInstance(String param1, String param2) {
        TrainingArea fragment = new TrainingArea();
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
        View view = inflater.inflate(R.layout.fragment_training_area, container, false);

        this.image = view.findViewById(R.id.lutemonImageTrain);
        this.name = view.findViewById(R.id.lutemonNameTrain);
        this.exp = view.findViewById(R.id.lutemonExpTrain);

        Button train = view.findViewById(R.id.trainButton);
        train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lutemon selected = Storage.getInstance().getLutemons().get(0);
                selected.GainExperience(1);
                exp.setText(String.format("EXP: %d / %d", selected.GetExperience(), selected.GetExperienceNextLevel()));
            }
        });

        SetStatistics();
        return view;
    }

    private void SetStatistics() {
        Lutemon selected = Storage.getInstance().getLutemons().get(0);
        image.setImageResource(selected.GetImage());
        name.setText(String.format("NAME: %s", selected.GetName()));
        exp.setText(String.format("EXP: %d / %d", selected.GetExperience(), selected.GetExperienceNextLevel()));
    }

}