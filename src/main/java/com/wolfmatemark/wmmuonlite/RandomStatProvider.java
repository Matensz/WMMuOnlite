package com.wolfmatemark.wmmuonlite;

import java.util.Random;

public final class RandomStatProvider {
    private static final Random RANDOM = new Random();

    public static StatSet generateRandomStats(int sumOfPoints) {
        StatSet resultStatSet = new StatSet();
        int[] stats = RANDOM.ints(sumOfPoints, 1, 5).toArray();
        for (int stat : stats) {
            switch (stat) {
                case 1:
                    resultStatSet.incrementStrength();
                    break;
                case 2:
                    resultStatSet.incrementAgility();
                    break;
                case 3:
                    resultStatSet.incrementStamina();
                    break;
                case 4:
                    resultStatSet.incrementEnergy();
                    break;
            }
        }
        return resultStatSet;
    }
}
