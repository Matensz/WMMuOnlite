package com.wolfmatemark.wmmuonlite.character;

public enum CharacterType {
    DARK_KNIGHT(8, 2, 7, 3, 4, 3, 2, 1),
    DARK_WIZARD(3, 4, 5, 8, 1, 3, 2, 4),
    ELF(5, 7, 5, 3, 2, 4, 3, 1);

    private int strength;
    private int agility;
    private int stamina;
    private int energy;
    private int strengthMod;
    private int agilityMod;
    private int staminaMod;
    private int energyMod;

    CharacterType(int strength, int agility, int stamina, int energy, int strengthMod, int agilityMod, int staminaMod, int energyMod) {
        this.strength = strength;
        this.agility = agility;
        this.stamina = stamina;
        this.energy = energy;
        this.strengthMod = strengthMod;
        this.agilityMod = agilityMod;
        this.staminaMod = staminaMod;
        this.energyMod = energyMod;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getStamina() {
        return stamina;
    }

    public int getEnergy() {
        return energy;
    }

    public int getStrengthMod() {
        return strengthMod;
    }

    public int getAgilityMod() {
        return agilityMod;
    }

    public int getStaminaMod() {
        return staminaMod;
    }

    public int getEnergyMod() {
        return energyMod;
    }
}
