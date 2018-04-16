package com.wolfmatemark.wmmuonlite.character;

import com.wolfmatemark.wmmuonlite.RandomStatProvider;
import com.wolfmatemark.wmmuonlite.StatSet;
import com.wolfmatemark.wmmuonlite.character.Monster.MonsterBuilder;

public class MonsterProvider {
    private static final int STATPOINT = 20;

    public Monster buildMonster(MonsterType monsterType, int level) {
        StatSet statSet = RandomStatProvider.generateRandomStats(
                level * monsterType.getLevelMultiplier() + STATPOINT);
        int monsterLevel;
        switch (monsterType) {
            case SPIDER:
                monsterLevel = (level - 5 > 0) ? (level - 5) : 1;
                break;
            case BULL:
                monsterLevel = level;
                break;
            case DRAGON:
                monsterLevel = level + 5;
                break;
            default:
                monsterLevel = 1;
        }
        return new MonsterBuilder(monsterType)
                .withLevel(monsterLevel)
                .withStrength(statSet.getStrength())
                .withAgility(statSet.getAgility())
                .withStamina(statSet.getStamina())
                .withEnergy(statSet.getEnergy())
                .build();
    }
}
