package com.wolfmatemark.wmmuonlite.character;

public final class Monster implements Character {
    private final MonsterType monsterType;
    private int strength;
    private int agility;
    private int stamina;
    private int energy;
    private int level;

    private Monster(MonsterBuilder builder) {
        this.monsterType = builder.monsterType;
        this.strength = builder.strength;
        this.agility = builder.agility;
        this.stamina = builder.stamina;
        this.energy = builder.energy;
        this.level = builder.level;
    }

    @Override
    public int calculateWinRate() {
        return level * (strength * monsterType.getStrengthMod() +
                agility * monsterType.getAgilityMod() +
                stamina * monsterType.getStaminaMod() +
                energy * monsterType.getEnergyMod());
    }

    public MonsterType getMonsterType() {
        return monsterType;
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

    public static final class MonsterBuilder {
        private final MonsterType monsterType;
        private int strength;
        private int agility;
        private int stamina;
        private int energy;
        private int level;

        public MonsterBuilder(MonsterType monsterType) {
            this.monsterType = monsterType;
        }

        public MonsterBuilder withLevel(int level) {
            this.level = level;
            return this;
        }

        public MonsterBuilder withStrength(int strength) {
            this.strength = strength;
            return this;
        }

        public MonsterBuilder withAgility(int agility) {
            this.agility = agility;
            return this;
        }

        public MonsterBuilder withStamina(int stamina) {
            this.stamina = stamina;
            return this;
        }

        public MonsterBuilder withEnergy(int energy) {
            this.energy = energy;
            return this;
        }

        public Monster build() {
            return new Monster(this);
        }
    }
}
