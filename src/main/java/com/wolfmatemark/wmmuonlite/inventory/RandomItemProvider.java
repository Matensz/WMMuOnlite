package com.wolfmatemark.wmmuonlite.inventory;

import com.wolfmatemark.wmmuonlite.NameReader;
import com.wolfmatemark.wmmuonlite.RandomStatProvider;

import java.util.List;
import java.util.Random;

public class RandomItemProvider {
    private static final Random RANDOM = new Random();

    private final NameReader nameReader;
    private final List<String> itemFirstNames;
    private final List<String> itemSecondNames;

    public RandomItemProvider(NameReader nameReader) {
        this.nameReader = nameReader;
        this.itemFirstNames = nameReader.getListOfFirstNames();
        this.itemSecondNames = nameReader.getListOfSecondNames();
    }

    public Item generateARandomItem(int monsterLevel) {
        ItemType randomItemType = ItemType.values()[RANDOM.nextInt(ItemType.values().length)];
        return new Item.ItemBuilder()
                .withName(itemFirstNames.get(RANDOM.nextInt(itemFirstNames.size())).toUpperCase() + " " +
                        itemSecondNames.get(RANDOM.nextInt(itemSecondNames.size())).toUpperCase() + " " +
                        randomItemType.toString().toUpperCase())
                .withItemType(randomItemType)
                .withStatSet(RandomStatProvider.generateRandomStats(RANDOM.nextInt(monsterLevel * 5)))
                .build();
    }
}
