package com.wolfmatemark.wmmuonlite;

import com.wolfmatemark.wmmuonlite.exception.GameOverException;
import com.wolfmatemark.wmmuonlite.processor.CharacterProcessor;
import com.wolfmatemark.wmmuonlite.processor.HelpProcessor;
import com.wolfmatemark.wmmuonlite.processor.InventoryProcessor;
import com.wolfmatemark.wmmuonlite.processor.QuestProcessor;

import java.util.Optional;

public class CommandLineProcessor {
    private static final String QUEST = "QUEST";
    private static final String CHARACTER = "CHARACTER";
    private static final String INVENTORY = "INVENTORY";
    private static final String HELP = "HELP";
    private static final String WRONG_COMMAND_FORMAT = "Wrong input format, see HELP for %s usage\n";
    private static final String WRONG_COMMAND = "Wrong command, see HELP for available commands\n";

    private final QuestProcessor questProcessor;
    private final CharacterProcessor characterProcessor;
    private final InventoryProcessor inventoryProcessor;
    private final HelpProcessor helpProcessor;

    public CommandLineProcessor() {
        this.questProcessor = new QuestProcessor();
        this.characterProcessor = new CharacterProcessor();
        this.inventoryProcessor = new InventoryProcessor();
        this.helpProcessor = new HelpProcessor();
    }

    public Optional<String> process(String line) throws GameOverException {
        String[] inputArray = line.split(" ");
        switch (inputArray[0].toUpperCase()) {
            case QUEST:
                if (inputArray.length < 2) {
                    return Optional.of(String.format(WRONG_COMMAND_FORMAT, QUEST));
                }
                try {
                    return questProcessor.process(inputArray[1].toUpperCase());
                } catch (GameOverException ex) {
                    throw new GameOverException();
                }
            case CHARACTER:
                return characterProcessor.process();
            case INVENTORY:
                if (inputArray.length == 2) {
                    return inventoryProcessor.process(inputArray[1].toUpperCase());
                }
                if (inputArray.length == 3) {
                    return inventoryProcessor.process(inputArray[1].toUpperCase(), inputArray[2].toUpperCase());
                }
                return Optional.of(String.format(WRONG_COMMAND_FORMAT, INVENTORY));
            case HELP:
                return helpProcessor.returnHelp();
            default:
                return Optional.of(WRONG_COMMAND);
        }
    }
}
