package com.wolfmatemark.wmmuonlite.processor;

import com.wolfmatemark.wmmuonlite.MuOnliteApp;
import com.wolfmatemark.wmmuonlite.character.PlayerCharacter;
import com.wolfmatemark.wmmuonlite.inventory.Item;

import java.util.Optional;
import java.util.stream.Collectors;

public class CharacterProcessor {
    private static final String HEADER = "~~~~~~~~~~~~~~~CHARACTER INFO~~~~~~~~~~~~~~~\n";
    private static final String HP_LEFT = "Life points left: ";
    private static final String EITEMS = "Equipped items\n";
    private static final String NO_EQUIPPED_ITEMS = "No items equipped\n";
    private static final String INV_STAT = "Inventory status: ";

    public Optional<String> process() {
        PlayerCharacter player = MuOnliteApp.getPlayerCharacter();
        String playerName = MuOnliteApp.getPlayerName();
        StringBuilder sb = new StringBuilder();
        sb.append(HEADER);
        sb.append(playerName + ", level " + player.getLevel() + " " + player.getCharacterType() + "\n");
        sb.append("Experience: " + player.getExperience() + "/100\n");
        sb.append(HP_LEFT + player.getLifePoint() + "\n");
        sb.append("Stats\n");
        sb.append("\tStrength: " + player.getStrength() + "\n");
        sb.append("\tAgility: " + player.getAgility() + "\n");
        sb.append("\tStamina: " + player.getStamina() + "\n");
        sb.append("\tEnergy:" + player.getEnergy() + "\n");
        sb.append(EITEMS);
        sb.append(player.getCurrentItems().getEquippedItems().size() == 0 ? NO_EQUIPPED_ITEMS : player.getCurrentItems().getEquippedItems().stream().map(Item::toString).collect(Collectors.joining("\n")));
        sb.append(INV_STAT + player.getInventory().getItemNumber() + "/20\n");
        return Optional.of(sb.toString());
    }
}
