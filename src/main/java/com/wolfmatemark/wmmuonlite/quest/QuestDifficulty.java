package com.wolfmatemark.wmmuonlite.quest;

import com.wolfmatemark.wmmuonlite.character.MonsterType;

public enum QuestDifficulty {
    EASY(MonsterType.SPIDER),
    NORMAL(MonsterType.BULL),
    HARD(MonsterType.DRAGON);

    private MonsterType monsterType;

    QuestDifficulty(MonsterType monsterType) {
        this.monsterType = monsterType;
    }

    public MonsterType getMonsterType() {
        return this.monsterType;
    }
}
