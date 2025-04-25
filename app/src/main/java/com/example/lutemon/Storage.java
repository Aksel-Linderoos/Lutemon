package com.example.lutemon;


import java.util.ArrayList;

public class Storage {

    public ArrayList<Lutemon> Lutemons;
    public static Storage storage = null;
    public static Storage getInstance(){
        if (storage == null) {
            storage = new Storage();
        }
        return storage;
    }

    ArrayList<Lutemon> getLutemons() {
        return Lutemons;
    }

    public void addLutemon(Lutemon Lutemon){
        Lutemons.add(Lutemon);
    }



}


