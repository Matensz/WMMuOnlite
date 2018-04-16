package com.wolfmatemark.wmmuonlite.character;

import com.wolfmatemark.wmmuonlite.character.PlayerCharacter.CharacterBuilder;

public class PlayerCharacterProvider {
    public PlayerCharacter buildCharacter(CharacterType characterType) {
        return new CharacterBuilder(characterType)
                .withStrength(characterType.getStrength())
                .withAgility(characterType.getAgility())
                .withStamina(characterType.getStamina())
                .withEnergy(characterType.getEnergy())
                .build();
    }
}
