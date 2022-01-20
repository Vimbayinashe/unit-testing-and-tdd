package com.example.bowling;

public class Game {

    private int score;
    private int frames;
    private static final int MAXIMUM_FRAMES = 10;
    private int actualRolls;
    private int expectedRolls;
    private int currentFrameScore;
    private int maximumFrameScore;
    private boolean bonus;
    private int actualBonusCount;
    private int maximumBonusCount;
    private boolean doubleStrike;

    public Game() {
        expectedRolls = 2;
        maximumFrameScore = 10;
    }

    public int score() {
        return score;
    }

    public void roll(int value) {
        if(rollIsDisallowed())
           return;

        if(bonusHasBeenCalculated())
            resetBonus();

        if(strike(value)) {
            handleStrike(value);
        } else {
            calculateScore(value);
            nextRoll();
        }
    }

    private boolean rollIsDisallowed() {
        return frames >= MAXIMUM_FRAMES || actualRolls >= expectedRolls;
    }

    private boolean bonusHasBeenCalculated() {
        return bonus && actualBonusCount >= maximumBonusCount;
    }

    private void resetBonus() {
        bonus = false;
        actualBonusCount = 0;
        maximumBonusCount = 0;
    }

    private boolean strike(int value) {
        return actualRolls == 0 && value == 10;
    }

    private void handleStrike(int value) {
        if(bonus) {
            doubleStrike = true;
            score += 2 * value;
        } else {
            bonus = true;
            score += value;

        }
        maximumBonusCount = 2;
        frames++;
    }

    private void calculateScore(int value) {
        if((value + currentFrameScore) < maximumFrameScore) {
            calculateDoubleStrike(value);
            score += bonus ? 2 * value : value;
            currentFrameScore += value;
        } else {
            int difference = maximumFrameScore - currentFrameScore;
            score += bonus ? 2 * value : difference;
            currentFrameScore += difference;
        }
    }

    private void nextRoll() {
        actualRolls++;

        if(actualRolls == expectedRolls) {
            frames++;
            actualRolls = 0;
            currentFrameScore = 0;
        }

        if(bonus)
            actualBonusCount++;
    }

    private void calculateDoubleStrike(int value) {
        if(doubleStrike) {
            score += value;
            doubleStrike = false;
        }
    }

}
