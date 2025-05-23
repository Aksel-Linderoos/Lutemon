package com.example.lutemon;

import java.io.Serializable;
import java.util.Random;

public class Lutemon implements Serializable {
    private String name;
    private LutemonType type;
    private int attack;
    private int defense;
    private int experience;
    private int level;
    private int health;
    private int max_health;
    private boolean is_blocking;
    private int wins;

    public static int number_created = 0;

    public Lutemon(String name, LutemonType type, int max_health) {
        this.name = name;
        this.type = type;
        this.attack = 1;
        this.defense = 1;
        this.experience = 0;
        this.level = 1;
        this.health = max_health;
        this.max_health = max_health;
        this.is_blocking = false;
        this.wins = 0;

        Lutemon.number_created += 1;
    }

    public static Lutemon CreateEnemy(int wins) {
        Random gen = new Random();

        int max_health = 10 + wins;
        int pick = gen.nextInt(LutemonType.values().length - 1);
        LutemonType type = LutemonType.values()[pick];

        String name = null;
        switch (type) {
            case FIRE: name = "Lutmeleon"; break;
            case WATER: name = "Lustoise"; break;
            case ELECTRIC: name = "Lukachu"; break;
            case GHOST: name = "Luaunter"; break;
            case GRASS: name = "Lutbasaur"; break;
            default:
                System.out.println("error: unknown lutemon type.");
                break;
        }

        return new Lutemon(name, type, max_health);
    }

    public String GetName() { return this.name; }
    public LutemonType GetType() { return this.type; }
    public int GetAttack() { return this.attack; }
    public int GetDefense() { return this.defense; }
    public int GetExperience() { return this.experience; }
    public int GetLevel() { return this.level; }
    public int GetHealth() { return this.health; }
    public int GetMaxHealth() { return this.max_health; }
    public int GetWins() { return this.wins; }
    public void AddWin() { this.wins += 1; }

    public int GetImage() {
        switch (this.type) {
            case FIRE:
                return R.drawable.charmeleon;
            case WATER:
                return R.drawable.blastoise;
            case ELECTRIC:
                return R.drawable.pikachu;
            case GHOST:
                return R.drawable.haunter;
            case GRASS:
                return R.drawable.bulbasaur;
            default:
                System.out.println("error: unknown lutemon type.");
                return 0;
        }
    }

    public float GetAccuracy(int attack, int defense) {
        float accuracy = (float)attack - (float)defense * 0.5f;
        return Math.max(accuracy, 0.f);
    }

    private void TakeDamage(int damage) {
        this.health -= damage;

        if (this.is_blocking) {
            this.is_blocking = false;
            this.defense /= 2;
        }
    }

    private void LevelUp() {
        this.attack += 1;
        this.defense += 1;
        this.max_health += 3;
        this.health += 3;
    }

    public int GetExperienceNextLevel() {
        return this.level * 100 + (int)Math.pow((double)this.level, 3);
    }

    public void GainExperience(int experience) {
        this.experience += experience;

        while (this.experience >= GetExperienceNextLevel()) {
            System.out.println("Level up!");
            this.level += 1;
            LevelUp();
        }
    }

    public void fullHeal(){
        this.health = this.max_health;
    }

    public void Attack(Lutemon defender, boolean try_strong) {
        float accuracy = GetAccuracy(this.attack, defender.defense);

        if (try_strong) {
            Random gen = new Random();
            if (!gen.nextBoolean()) {
                System.out.println("Heavy attack missed!");
                return;
            }
            else accuracy *= 2;
        }

        LutemonType vulnerable = TypeHelper.GetVulnerability(this.type);
        LutemonType advantage = TypeHelper.GetAdvantage(this.type);

        if (defender.type == vulnerable) {
            if (accuracy == 0) defender.health -= 2;
            else defender.TakeDamage((int)Math.ceil(accuracy * 2.f));
        }

        else if (defender.type == advantage) {
            if (accuracy == 0) defender.health -= 1;
            else defender.TakeDamage((int)Math.ceil(accuracy * 0.5f));
        }

        else {
            if (accuracy == 0) defender.health -= 1;
            else defender.TakeDamage((int)Math.ceil(accuracy));
        }
    }

    public void Defend() {
        this.is_blocking = true;
        this.defense *= 2;
    }

    public void RandomAttack(Lutemon defender) {
        Random gen = new Random();

        switch (gen.nextInt(3)) {
            case 0: Attack(defender, false); return;
            case 1: Attack(defender, true); return;
            case 2: Defend(); return;
            default:
                System.out.println("error: could not pick a random attack.");
                return;
        }
    }

}

