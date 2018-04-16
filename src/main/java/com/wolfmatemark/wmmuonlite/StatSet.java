package com.wolfmatemark.wmmuonlite;

import java.util.Objects;

public final class StatSet {
    private int strength;
    private int agility;
    private int stamina;
    private int energy;

    public StatSet() {
        this.strength = 0;
        this.agility = 0;
        this.stamina = 0;
        this.energy = 0;
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

    public void incrementStrength() {
        this.strength++;
    }

    public void incrementAgility() {
        this.agility++;
    }

    public void incrementStamina() {
        this.stamina++;
    }

    public void incrementEnergy() {
        this.energy++;
    }

    public void incrementStrengthWithX(int stat) {
        this.strength += stat;
    }

    public void incrementAgilityWithX(int stat) {
        this.agility += stat;
    }

    public void incrementStaminaWithX(int stat) {
        this.stamina += stat;
    }

    public void incrementEnergyWithX(int stat) {
        this.energy += stat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatSet statSet = (StatSet) o;
        return strength == statSet.strength &&
                agility == statSet.agility &&
                stamina == statSet.stamina &&
                energy == statSet.energy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(strength, agility, stamina, energy);
    }

    @Override
    public String toString() {
        return "strength: " + strength +
                ", agility:" + agility +
                ", stamina:" + stamina +
                ", energy:" + energy +
                "\n";
    }
}
