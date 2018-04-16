package com.wolfmatemark.wmmuonlite.processor;

import java.util.Optional;

public class HelpProcessor {
    public Optional<String> returnHelp() {
        return Optional.of("Available commands:\n" +
                "QUEST [difficulty] - initiates a quest with the selected difficulty [EASY, NORMAL, HARD]\n" +
                "CHARACTER - returns a summary of the player's character (level, stats, etc.)\n" +
                "INVENTORY\n" +
                "\tLIST - lists items in your inventory\n" +
                "\tEQUIP [ITEM NAME] - equips the item from your inventory with name [ITEM NAME]\n" +
                "\tDROP [ITEM NAME] - drops the item from your inventory with the name [ITEM NAME]\n" +
                "HELP - opens up help menu\n");
    }
}
