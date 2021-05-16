package org.acme.manualtest;

import org.acme.model.*;
import org.acme.process.ScoreTableDisplay;

import java.util.ArrayList;
import java.util.List;

public class FillAndEvaluate {

    public static void main(String[] args) {
        List<PlayerFrames> playerFrameList = new ArrayList<>();
        playerFrameList.add(new PlayerFrames(
                new Player("Mr", "Spook", "sharp ears")
        ));
        playerFrameList.add(new PlayerFrames(
                new Player("Mickex", "Mouse", "disney hero")
        ));

        for (int i = 0; i < 10; i++) {
            for (PlayerFrames playerFrame : playerFrameList) {
                Frame frame;
                if (i == 9) {
                    frame = new LastFrame(playerFrame.getResultCalculator());
                    frame.addValue(10);
                    frame.addValue(10);
                    frame.addValue(10);
                    frame.getResult().setCalculated(true);
                } else {
                    frame = new Frame(playerFrame.getResultCalculator());
                    frame.addValue(10);
                }
            }
        }

        new ScoreTableDisplay(playerFrameList).showCurrentResults();
    }

}
