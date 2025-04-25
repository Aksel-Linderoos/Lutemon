package com.example.lutemon;

public abstract class Lutemon {
    protected String name;
    protected LutemonType type;
    protected int attack;
    protected int defense;
    protected int experience;
    protected int level;
    protected int health;
    protected int max_health;
    private int number_created = 0;

    public Lutemon(String name, LutemonType type) {
        this.name = name;
        this.type = type;
        this.attack = 1;
        this.defense = 1;
        this.experience = 0;
        this.level = 1;
        this.health = 10;
        this.max_health = 10;

        this.number_created += 1;
    }

    public abstract void Defense(Lutemon attacker);

    final public int GetLutemonsCreated() {
        return this.number_created;
    }
}
