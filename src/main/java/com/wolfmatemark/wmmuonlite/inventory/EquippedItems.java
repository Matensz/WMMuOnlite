package com.wolfmatemark.wmmuonlite.inventory;

import com.wolfmatemark.wmmuonlite.MuOnliteApp;
import com.wolfmatemark.wmmuonlite.StatSet;
import com.wolfmatemark.wmmuonlite.character.Character;
import com.wolfmatemark.wmmuonlite.character.CharacterType;
import com.wolfmatemark.wmmuonlite.exception.InvalidItemTypeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EquippedItems {
    private List<Item> equippedItems;

    public EquippedItems() {
        this.equippedItems = new ArrayList<>();
    }

    public Optional<Item> equipItem(Item item) throws InvalidItemTypeException {
        Optional<Item> itemToUnequip = equippedItems.stream().filter(x -> x.getItemType().equals(item.getItemType())).findFirst();
        if (itemToUnequip.isPresent()) {
            equippedItems.remove(itemToUnequip.get());
            equippedItems.add(item);
            return itemToUnequip;
        } else {
            CharacterType playerClass = MuOnliteApp.getPlayerCharacter().getCharacterType();
            if ((item.getItemType().equals(ItemType.SWORD) && !playerClass.equals(CharacterType.DARK_KNIGHT)) ||
                    (item.getItemType().equals(ItemType.STAFF) && !playerClass.equals(CharacterType.DARK_WIZARD)) ||
                    (item.getItemType().equals(ItemType.BOW) && !playerClass.equals(CharacterType.ELF))) {
                throw new InvalidItemTypeException();
            }
            equippedItems.add(item);
            return Optional.empty();
        }
    }

    public List<Item> getEquippedItems() {
        return equippedItems;
    }

    public StatSet getEquipStats(){
        StatSet equipStats = new StatSet();
        for (Item item : equippedItems){
            equipStats.incrementStrengthWithX(item.getStatSet().getStrength());
            equipStats.incrementAgilityWithX(item.getStatSet().getAgility());
            equipStats.incrementStaminaWithX(item.getStatSet().getStamina());
            equipStats.incrementEnergyWithX(item.getStatSet().getEnergy());
        }
        return equipStats;
    }
}
