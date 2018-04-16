package com.wolfmatemark.wmmuonlite.quest;

import com.wolfmatemark.wmmuonlite.character.Monster;
import com.wolfmatemark.wmmuonlite.character.MonsterProvider;
import com.wolfmatemark.wmmuonlite.character.MonsterType;
import com.wolfmatemark.wmmuonlite.character.PlayerCharacter;
import com.wolfmatemark.wmmuonlite.exception.GameOverException;
import com.wolfmatemark.wmmuonlite.exception.InventoryIsFullException;
import com.wolfmatemark.wmmuonlite.inventory.RandomItemProvider;

import java.util.Random;

public class QuestProvider {
    private static final Random RANDOM = new Random();

    private final CharacterBrawler characterBrawler;
    private final MonsterProvider monsterProvider;
    private final RandomItemProvider randomItemProvider;

    public QuestProvider(CharacterBrawler characterBrawler, MonsterProvider monsterProvider,
                         RandomItemProvider randomItemProvider) {
        this.characterBrawler = characterBrawler;
        this.monsterProvider = monsterProvider;
        this.randomItemProvider = randomItemProvider;
    }

    public boolean generateQuest(PlayerCharacter player, QuestDifficulty difficulty) throws GameOverException, InventoryIsFullException {
        MonsterType monsterType = difficulty.getMonsterType();
        Monster monster = monsterProvider.buildMonster(monsterType, player.getLevel());
        if (characterBrawler.beginFightlBetweenCharacters(player, monster)) {
            player.gainExperience(
                    RANDOM.nextInt(monsterType.getWinExp() - monsterType.getLoseExp() + 1) + monsterType.getLoseExp());
            if (!player.getInventory().isFull()) {
                if (RANDOM.nextInt(5) < 2) {
                    player.getInventory().addItem(randomItemProvider.generateARandomItem(monster.getLevel()));
                }
            }
            return true;
        } else {
            player.loseALifePoint();
            if (player.getLifePoint() < 1) {
                throw new GameOverException();
            } else {
                player.gainExperience(monsterType.getLoseExp());
            }
            return false;
        }
    }
}
