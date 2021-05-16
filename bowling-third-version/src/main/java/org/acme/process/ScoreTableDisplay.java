package org.acme.process;

import org.acme.model.Frame;
import org.acme.model.PlayerFrames;

import java.util.List;

public class ScoreTableDisplay {

    private List<PlayerFrames> registerPlayer;

    public ScoreTableDisplay(List<PlayerFrames> registerPlayer) {
        this.registerPlayer = registerPlayer;
    }

    public void showCurrentResults() {
        for (PlayerFrames playerFrameList : registerPlayer) {
            System.out.println(playerFrameList.getPlayer().getState());
            List<Frame> frameList = playerFrameList.getFrames().getFrameList();
            int resultSum = 0;
            for (Frame frame : frameList) {
                System.out.println(String.format("Frame:  %s", frameList.indexOf(frame) + 1));
                System.out.println(frame.getScore().getScoredValues());
                System.out.println(frame.getResult().getBaseValue());
                if (frame.getResult().isCalculated()) {
                    int finalValue = frame.getResult().getFinalValue();
                    resultSum += finalValue;
                    System.out.println(finalValue);
                } else {
                    System.out.println("*** NOT READY YET ***");
                }
                System.out.println(String.format("Summary Final Score of \"%s\" is %s!", playerFrameList.getPlayer().getAlias(), resultSum));
                System.out.println(String.format("Summary Final Score of \"%s\" is %s!", playerFrameList.getPlayer().getAlias(), frame.getResult().getAccumulatedValue()));
            }
        }
    }
}
