package com.wolfmatemark.wmmuonlite;

import com.wolfmatemark.wmmuonlite.character.CharacterType;
import com.wolfmatemark.wmmuonlite.character.MonsterProvider;
import com.wolfmatemark.wmmuonlite.character.PlayerCharacter;
import com.wolfmatemark.wmmuonlite.character.PlayerCharacterProvider;
import com.wolfmatemark.wmmuonlite.exception.GameOverException;
import com.wolfmatemark.wmmuonlite.inventory.RandomItemProvider;
import com.wolfmatemark.wmmuonlite.quest.CharacterBrawler;
import com.wolfmatemark.wmmuonlite.quest.QuestProvider;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class MuOnliteApp {
    private static final String EXIT = "EXIT";

    private static String name;
    private static PlayerCharacter player;
    private static final PlayerCharacterProvider playerCharacterProvider = new PlayerCharacterProvider();
    private static final MonsterProvider monsterProvider = new MonsterProvider();
    private static final CharacterBrawler characterBrawler = new CharacterBrawler();
    private static final NameReader nameReader = new NameReader();
    private static final RandomItemProvider randomItemProvider = new RandomItemProvider(nameReader);
    private static final QuestProvider questProvider = new QuestProvider(characterBrawler, monsterProvider, randomItemProvider);
    private static final CommandLineProcessor commandLineProcessor = new CommandLineProcessor();
    private static final LineByLineParser lineByLineParser = new LineByLineParser(commandLineProcessor);

    public static void main(String... args) {
        System.out.println("-----MuOnlite v1.0 created by Mate Mark Wolf-----");
        System.out.println("Enter your name, brave adventurer!");
        Scanner scanner = new Scanner(System.in);
        name = scanner.nextLine();
        printIntro(name);

        System.out.println("Now choose a class for your character, " + name + "!");
        System.out.println("You can be a DARK KNIGHT, a DARK WIZARD, or an ELF");
        CharacterType playerCharacterType;
        String playerClass = scanner.nextLine();
        while (true) {
            try {
                playerCharacterType = CharacterType.valueOf(playerClass.replace(' ', '_').toUpperCase());
                player = playerCharacterProvider.buildCharacter(playerCharacterType);
                break;
            } catch (IllegalArgumentException ex) {
                System.out.println("Character class doesn't exists. Choose from: DARK KNIGHT, DARK WIZARD or ELF!");
                playerClass = scanner.nextLine();
            }
        }
        System.out.println("Very well, " + name + "! You are a(n) " + playerCharacterType.name().replace('_', ' ').toUpperCase() + ". Now let's the adventure begin!");
        System.out.println("Enter a command or use the help command to browse the commands available in the game!");

        String line;
        while (!(line = scanner.nextLine()).toUpperCase().equals(EXIT)) {
            try {
                lineByLineParser.start(System.out, new ByteArrayInputStream(line.getBytes()));
            } catch (GameOverException ex) {
                System.out.println("GAME OVER!");
                System.exit(0);
            }
        }
    }

    private static void printIntro(String name) {
        System.out.println("  __   __    __   __    __    __       __   _______     _____  __   \n" +
                " /\\_\\ /_/\\  /\\_\\ /\\_\\  /_/\\  /\\_\\     /\\_\\/\\_______)\\ /\\_____\\/_/\\  \n" +
                "/ / / ) ) \\/ ( (( ( (  ) ) )( ( (     \\/_/\\(___  __\\/( (_____/\\ \\ \\ \n" +
                "\\ \\_\\/_/ \\  / \\_\\\\ \\ \\/ / /  \\ \\_\\     /\\_\\ / / /     \\ \\__\\  /_/ / \n" +
                " \\/_/\\ \\ \\\\// / / \\ \\  / /   / / /__  / / /( ( (      / /__/_ \\_\\/  \n" +
                "      )_) )( (_(  ( (__) )  ( (_____(( (_(  \\ \\ \\    ( (_____\\      \n" +
                "      \\_\\/  \\/_/   \\/__\\/    \\/_____/ \\/_/  /_/_/     \\/_____/     \n\n");
        System.out.println("Welcome to Mulite, Hero! You are in a land of ancient magic, dark forces and adventures.");
        System.out.println("To stay alive here, you need weapons and armor to protect yourself from monsters lurking around every corner.");
        System.out.println("But be very careful, even the spiders native to Mulite are a threat to unexperienced warriors.");
        System.out.println("Bulls are more dangerous and really test your skills in combat");
        System.out.println("But the mightiest of them all are the ancient dragons hiding in caves, protecting their precious hordes of treasures.");
        System.out.println("Only the strongest and bravest warriors can ave a chance to defeat them, but the rewards are similarly great!");
        System.out.println("So good luck, and may the Gods' blessing follow you!");
    }

    public static String getPlayerName() {
        return name;
    }

    public static PlayerCharacter getPlayerCharacter() {
        return player;
    }

    public static QuestProvider getQuestProvider() {
        return questProvider;
    }
}
