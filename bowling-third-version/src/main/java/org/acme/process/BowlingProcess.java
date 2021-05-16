package org.acme.process;

import org.acme.model.Frame;
import org.acme.model.LastFrame;
import org.acme.model.PlayerFrames;

import java.util.List;

public class BowlingProcess {

    private static final int NUMBER_OF_FRAMES = 10;
    private final List<PlayerFrames> playerFrameList;
    private final ScoreTableDisplay scoreTableDisplay;

    public BowlingProcess(
            List<PlayerFrames> playerFrameList) {
        this.playerFrameList = playerFrameList;
        scoreTableDisplay = new ScoreTableDisplay(playerFrameList);
    }

    public void processBowling() {
        for (int i = 0; i < NUMBER_OF_FRAMES; i++) {
            for (PlayerFrames playerFrames : playerFrameList) {
                Frame frame;
                if (i == 9) {
                    frame = new LastFrame(playerFrames.getResultCalculator());
                    BowlingHitsGenerator bowlingHitsGenerator = new BowlingHitsGenerator();
                    int firstTry = bowlingHitsGenerator.firstTry();
                    frame.addValue(firstTry);
                    scoreTableDisplay.showCurrentResults();

                    int secondTry;
                    if (firstTry < 10) {
                        secondTry = bowlingHitsGenerator.getSecondTry();
                    } else {
                        BowlingHitsGenerator bowlingHitsGenerator2 = new BowlingHitsGenerator();
                        secondTry = bowlingHitsGenerator2.firstTry();
                    }
                    frame.addValue(secondTry);
                    scoreTableDisplay.showCurrentResults();

                    int thirdTry;
                    if (firstTry == 10
                        || (firstTry + secondTry) == 10) {
                        BowlingHitsGenerator bowlingHitsGenerator3 = new BowlingHitsGenerator();
                        thirdTry = bowlingHitsGenerator3.firstTry();
                        frame.addValue(thirdTry);
                        scoreTableDisplay.showCurrentResults();
                    }
                    frame.getResult().setCalculated(true);
                } else {
                    frame = new Frame(playerFrames.getResultCalculator());
                    BowlingHitsGenerator bowlingHitsGenerator = new BowlingHitsGenerator();
                    int firstTry = bowlingHitsGenerator.firstTry();
                    frame.addValue(firstTry);
                    scoreTableDisplay.showCurrentResults();
                    if (firstTry < 10) {
                        frame.addValue(bowlingHitsGenerator.getSecondTry());
                        scoreTableDisplay.showCurrentResults();
                    }
                }
            }
        }
        scoreTableDisplay.showCurrentResults();
    }

}
