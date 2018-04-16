package com.wolfmatemark.wmmuonlite.quest;

import com.wolfmatemark.wmmuonlite.character.Monster;
import com.wolfmatemark.wmmuonlite.character.PlayerCharacter;

import java.util.Random;

public class CharacterBrawler {
    private static final Random RANDOM = new Random();

    public boolean beginFightlBetweenCharacters(PlayerCharacter player, Monster monster){
        int playerWinRate = player.calculateWinRate();
        int playerProbabilityToWin = (int) ((double) playerWinRate / (playerWinRate + monster.calculateWinRate()) * 100);
        if(RANDOM.nextInt(100) < playerProbabilityToWin){
            return true;
        } else {
            return false;
        }
    }
}
