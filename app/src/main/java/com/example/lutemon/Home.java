package com.example.lutemon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Home extends Fragment {

    public Storage storage;
    private RecyclerView recyclerView;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Context context = getContext();

        storage = Storage.getInstance();
        recyclerView = view.findViewById(R.id.listLutemonsRV);

        System.out.println("Serializing save data.");
        try {
            FileOutputStream file = context.getApplicationContext().openFileOutput("save.data", Context.MODE_PRIVATE);
            ObjectOutputStream stream = new ObjectOutputStream(file);

            stream.writeObject(storage.getLutemons());

            stream.flush();
            file.flush();
            stream.close();
            file.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new LutemonListAdapter(context, storage.getLutemons()));
        return view;
    }


}