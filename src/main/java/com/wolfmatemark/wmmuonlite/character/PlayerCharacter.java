package com.wolfmatemark.wmmuonlite.character;

import com.wolfmatemark.wmmuonlite.RandomStatProvider;
import com.wolfmatemark.wmmuonlite.StatSet;
import com.wolfmatemark.wmmuonlite.inventory.EquippedItems;
import com.wolfmatemark.wmmuonlite.inventory.Inventory;

public final class PlayerCharacter implements Character {
    private static final String LVLUP = "Congratulations, you levelled up!";

    private final CharacterType characterType;
    private int strength;
    private int agility;
    private int stamina;
    private int energy;
    private int level;
    private int experience;
    private int lifePoint;
    private final Inventory inventory;
    private final EquippedItems currentItems;


    private PlayerCharacter(CharacterBuilder builder) {
        this.characterType = builder.characterType;
        this.strength = builder.strength;
        this.agility = builder.agility;
        this.stamina = builder.stamina;
        this.energy = builder.energy;
        this.level = builder.level;
        this.experience = builder.experience;
        this.lifePoint = builder.lifePoint;
        this.inventory = builder.inventory;
        this.currentItems = builder.currentItems;
    }

    @Override
    public int calculateWinRate() {
        return level * ((strength + currentItems.getEquipStats().getStrength()) * characterType.getStrengthMod() +
                (agility + currentItems.getEquipStats().getAgility()) * characterType.getAgilityMod() +
                (stamina + currentItems.getEquipStats().getStamina()) * characterType.getStaminaMod() +
                (energy + currentItems.getEquipStats().getEnergy()) * characterType.getEnergyMod());
    }

    public void gainExperience(int xp) {
        while (xp > 100) {
            levelUp();
            xp -= 100;
        }
        experience += xp;
        if(experience >= 100){
            levelUp();
            experience -= 100;
        }
    }

    private void levelUp() {
        level++;
        lifePoint += 3;
        increaseStats();
        System.out.println(LVLUP);
    }

    private void increaseStats() {
        StatSet statSet = RandomStatProvider.generateRandomStats(5);
        this.strength += statSet.getStrength();
        this.agility += statSet.getAgility();
        this.stamina += statSet.getStamina();
        this.energy += statSet.getEnergy();
    }

    public void loseALifePoint() {
        this.lifePoint--;
    }

    public CharacterType getCharacterType() {
        return characterType;
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

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public int getLifePoint() {
        return lifePoint;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public EquippedItems getCurrentItems() {
        return currentItems;
    }

    public static final class CharacterBuilder {
        private final CharacterType characterType;
        private int strength;
        private int agility;
        private int stamina;
        private int energy;
        private int level;
        private int experience;
        private int lifePoint;
        private final Inventory inventory;
        private final EquippedItems currentItems;

        public CharacterBuilder(CharacterType characterType) {
            this.characterType = characterType;
            this.level = 1;
            this.experience = 0;
            this.lifePoint = 5;
            this.inventory = new Inventory();
            this.currentItems = new EquippedItems();
        }

        public CharacterBuilder withStrength(int strength) {
            this.strength = strength;
            return this;
        }

        public CharacterBuilder withAgility(int agility) {
            this.agility = agility;
            return this;
        }

        public CharacterBuilder withStamina(int stamina) {
            this.stamina = stamina;
            return this;
        }

        public CharacterBuilder withEnergy(int energy) {
            this.energy = energy;
            return this;
        }

        public PlayerCharacter build() {
            return new PlayerCharacter(this);
        }
    }

}
