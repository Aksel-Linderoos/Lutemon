package com.example.lutemon;

public class TypeHelper {

    public static LutemonType GetVulnerability(LutemonType type) {
        switch (type) {
            case WATER: return LutemonType.ELECTRIC;
            case FIRE: return LutemonType.WATER;
            case GRASS: return LutemonType.FIRE;
            case ELECTRIC: return LutemonType.GHOST;
            case GHOST: return LutemonType.GRASS;
            default:
                System.out.println("error: unknown lutemon type.");
                return LutemonType.INVALID;
        }
    }

    public static LutemonType GetAdvantage(LutemonType type) {
        switch (type) {
            case WATER: return LutemonType.FIRE;
            case FIRE: return LutemonType.GRASS;
            case GRASS: return LutemonType.GHOST;
            case ELECTRIC: return LutemonType.WATER;
            case GHOST: return LutemonType.ELECTRIC;
            default:
                System.out.println("error: unknown lutemon type.");
                return LutemonType.INVALID;
        }
    }

}
