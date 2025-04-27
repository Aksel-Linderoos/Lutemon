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
    private static boolean first_time = true;

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

        if (Home.first_time) {
            System.out.println("Deserializing.");
            try {
                FileInputStream file = context.getApplicationContext().openFileInput("save.data");
                ObjectInputStream stream = new ObjectInputStream(file);
                Object object = stream.readObject();
                stream.close();
                file.close();

                storage.SetLutemons((ArrayList<Lutemon>)object);

            } catch (FileNotFoundException ex) {
                System.out.println("error: file not found.");
                ex.printStackTrace();
            } catch (Exception ex) {
                System.out.println("error: ");
                ex.printStackTrace();
            }

        } else {
            System.out.println("Serializing.");
            try {
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                ObjectOutputStream stream = new ObjectOutputStream(bytes);
                stream.writeObject(storage.getLutemons());
                stream.flush();
                bytes.flush();
                stream.close();

                FileOutputStream file = context.getApplicationContext().openFileOutput("save.data", Context.MODE_PRIVATE);
                file.write(bytes.toByteArray());
                file.close();
                bytes.close();

            } catch (FileNotFoundException ex) {
                System.out.println("error: file not found.");
                ex.printStackTrace();
            } catch (Exception ex) {
                System.out.println("error: ");
                ex.printStackTrace();
            }
        }



        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new LutemonListAdapter(context, storage.getLutemons()));
        return view;
    }


}