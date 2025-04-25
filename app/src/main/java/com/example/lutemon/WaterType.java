package com.example.lutemon;

public class WaterType extends Lutemon {

    WaterType(String name) {
        super(name, LutemonType.WATER);
    }

    @Override
    public void Defense(Lutemon attacker) {
        System.out.println("Test print from WaterType::Defense()");
    }
}
