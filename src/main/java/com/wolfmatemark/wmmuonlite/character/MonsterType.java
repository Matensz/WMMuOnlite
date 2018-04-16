package com.wolfmatemark.wmmuonlite.character;

public enum MonsterType {
    SPIDER(5, 3, 4, 2, 1, 5, 25),
    BULL(6, 4, 3, 2, 1, 10, 60),
    DRAGON(7, 3, 1, 2, 4, 20, 140);

    private int levelMultiplier;
    private int strengthMod;
    private int agilityMod;
    private int staminaMod;
    private int energyMod;
    private int loseExp;
    private int winExp;

    MonsterType(int levelMultiplier, int strengthMod, int agilityMod, int staminaMod, int energyMod, int loseExp, int winExp) {
        this.levelMultiplier = levelMultiplier;
        this.strengthMod = strengthMod;
        this.agilityMod = agilityMod;
        this.staminaMod = staminaMod;
        this.energyMod = energyMod;
        this.loseExp = loseExp;
        this.winExp = winExp;
    }

    public int getLevelMultiplier() {
        return levelMultiplier;
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

    public int getWinExp() { return winExp; }

    public int getLoseExp() { return loseExp; }
}
