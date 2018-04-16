package com.wolfmatemark.wmmuonlite.processor;

import com.wolfmatemark.wmmuonlite.MuOnliteApp;
import com.wolfmatemark.wmmuonlite.character.PlayerCharacter;
import com.wolfmatemark.wmmuonlite.exception.GameOverException;
import com.wolfmatemark.wmmuonlite.exception.InventoryIsFullException;
import com.wolfmatemark.wmmuonlite.quest.QuestDifficulty;
import com.wolfmatemark.wmmuonlite.quest.QuestProvider;

import java.util.Optional;

public class QuestProcessor {
    private static final String CONGRAT = "Congratulations! Quest completed, you defeated a ";
    private static final String ITEM_FOUND = "And you found a new item too! Check your inventory!\n";
    private static final String BATTLE_LOST = "It was an epic battle, but you lost to the %s, now take a deep breath and continue your adventure, hero!\nYou still have %s life points left.\n";
    private static final String INV_FULL = "You got an item from the monster, but your inventory is full, so you have to leave it tot he next lucky person who comes here\n";
    private static final String INVALID_DIFF = "Invalid difficulty level! See HELP for QUEST command usage.\n";

    private static final QuestProvider questProvider = MuOnliteApp.getQuestProvider();

    public Optional<String> process(String questLevel) throws GameOverException {
        PlayerCharacter player = MuOnliteApp.getPlayerCharacter();
        QuestDifficulty difficulty;
        boolean questResult;
        int inventorySize = player.getInventory().getItemNumber();
        try {
            difficulty = QuestDifficulty.valueOf(questLevel);
            questResult = questProvider.generateQuest(player, difficulty);
            if (questResult) {
                StringBuilder sb = new StringBuilder(CONGRAT + difficulty.getMonsterType().name() + ".\n");
                if (player.getInventory().getItemNumber() > inventorySize) {
                    sb.append(ITEM_FOUND);
                }
                return Optional.of(sb.toString());
            } else {
                return Optional.of(String.format(BATTLE_LOST, difficulty.getMonsterType().name(), player.getLifePoint()));
            }
        } catch (GameOverException ex) {
            throw new GameOverException();
        } catch (InventoryIsFullException ex) {
            return Optional.of(INV_FULL);
        } catch (IllegalArgumentException ex) {
            return Optional.of(INVALID_DIFF);
        }
    }
}
