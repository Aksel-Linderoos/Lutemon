package com.example.lutemon;

import java.util.ArrayList;

public class Storage {
    private ArrayList<Lutemon> lutemons = new ArrayList<Lutemon>();
    private static Storage storage = null;

    public static Storage getInstance(){
        if (storage == null) {
            storage = new Storage();
        }
        return storage;
    }

    ArrayList<Lutemon> getLutemons() {
        return lutemons;
    }

    public void addLutemon(Lutemon lutemon){
        lutemons.add(lutemon);
    }

}


