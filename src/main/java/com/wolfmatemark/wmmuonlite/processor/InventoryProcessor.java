package com.wolfmatemark.wmmuonlite.processor;

import com.wolfmatemark.wmmuonlite.MuOnliteApp;
import com.wolfmatemark.wmmuonlite.character.PlayerCharacter;
import com.wolfmatemark.wmmuonlite.exception.InvalidItemTypeException;
import com.wolfmatemark.wmmuonlite.exception.InventoryIsFullException;
import com.wolfmatemark.wmmuonlite.inventory.Item;

import java.util.Optional;
import java.util.stream.Collectors;

public class InventoryProcessor {
    private static final String LIST = "LIST";
    private static final String EQUIP = "EQUIP";
    private static final String DROP = "DROP";
    private static final String INVALID_INV_COMMAND = "Invalid INVENTORY command! See HELP for INVENTORY command usage.\n";
    private static final String NO_ITEMS = "No items found with that name in your inventory!\n";
    private static final String CHAR_CLASS_ERR = "Your character can't use this type of weapon. DARK KNIGHTs can equip only SWORDs, DARK MAGEs can use only staffs and ELVes only BOWs!\n";
    private static final String INV_FULL_ERR = "Your inventory is full, can't equip item until the unequipped item has space in there!\n";
    private static final String EQUIP_SUC = " equipped successfully!\n";
    private static final String UNEQUIP_SUC = " removed successfully!\n";
    private static final String EMPTY_INV = "Your inventory is empty.\n";

    private final PlayerCharacter player = MuOnliteApp.getPlayerCharacter();

    public Optional<String> process(String command) {
        if (!command.equals(LIST)) {
            return Optional.of(INVALID_INV_COMMAND);
        } else {
            PlayerCharacter player = MuOnliteApp.getPlayerCharacter();
            if (player.getInventory().getItemNumber() == 0) {
                return Optional.of(EMPTY_INV);
            } else {
                return Optional.of(player.getInventory().getItems().stream().map(Item::toString).collect(Collectors.joining("\n")));
            }
        }
    }

    public Optional<String> process(String command, String itemName) {
        switch (command) {
            case EQUIP:
                return equip(itemName);
            case DROP:
                return drop(itemName);
            default:
                return Optional.of(INVALID_INV_COMMAND);
        }
    }

    private Optional<String> equip(String itemName) {
        try {
            Optional<Item> item = player.getInventory().getItemByName(itemName);
            if (item.isPresent()) {
                Optional<Item> unequippedItem = player.getCurrentItems().equipItem(item.get());
                if (unequippedItem.isPresent()) {
                    player.getInventory().addItem(unequippedItem.get());
                }
                return Optional.of(itemName.toUpperCase() + EQUIP_SUC);
            } else {
                return Optional.of(NO_ITEMS);
            }
        } catch (InvalidItemTypeException ex) {
            return Optional.of(CHAR_CLASS_ERR);
        } catch (InventoryIsFullException ex) {
            return Optional.of(INV_FULL_ERR);
        }
    }

    private Optional<String> drop(String itemName) {
        Optional<Item> item = player.getInventory().getItemByName(itemName);
        if (item.isPresent()) {
            player.getInventory().removeItemByName(itemName);
            return Optional.of(itemName.toUpperCase() + UNEQUIP_SUC);
        } else {
            return Optional.of(NO_ITEMS);
        }
    }
}
