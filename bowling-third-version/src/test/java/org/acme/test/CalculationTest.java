package org.acme.test;

import org.acme.model.Frame;
import org.acme.model.LastFrame;
import org.acme.model.Player;
import org.acme.model.PlayerFrames;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CalculationTest {


    private final List<PlayerFrames> playerFramesList = new ArrayList<>();

    @Before
    public void preparation() {
        //Arrange
        PlayerFrames playerFrames = new PlayerFrames(
                new Player("John", "Doe", "dummy")
        );
        playerFramesList.add(playerFrames);
    }


    @Test
    public void testCalculationHighScore() {
        //Act
        PlayerFrames playerFrames = playerFramesList.get(0);
        for (int i = 0; i < 10; i++) {
            Frame frame;
            if (i == 9) {
                frame = new LastFrame(playerFrames.getResultCalculator());
                frame.addValue(10);
                frame.addValue(10);
                frame.addValue(10);
                frame.getResult().setCalculated(true);
            } else {
                frame = new Frame(playerFrames.getResultCalculator());
                frame.addValue(10);
            }
        }

        //Assert
        List<Frame> frameList = playerFrames.getFrames().getFrameList();
        for (Frame frame : frameList) {
            int index = frameList.indexOf(frame);
            if (index == 9) {
                Assert.assertEquals(30, frame.getResult().getFinalValue());
                Assert.assertEquals(300, frame.getResult().getAccumulatedValue());
                Assert.assertEquals(3, frame.getScore().getScoredValues().size());
            } else {
                Assert.assertEquals(30, frame.getResult().getFinalValue());
                if (index == 0) {
                    Assert.assertEquals(30, frame.getResult().getAccumulatedValue());
                } else if (index == 1) {
                    Assert.assertEquals(60, frame.getResult().getAccumulatedValue());
                } else if (index == 2) {
                    Assert.assertEquals(90, frame.getResult().getAccumulatedValue());
                } else if (index == 3) {
                    Assert.assertEquals(120, frame.getResult().getAccumulatedValue());
                } else if (index == 4) {
                    Assert.assertEquals(150, frame.getResult().getAccumulatedValue());
                } else if (index == 5) {
                    Assert.assertEquals(180, frame.getResult().getAccumulatedValue());
                } else if (index == 6) {
                    Assert.assertEquals(210, frame.getResult().getAccumulatedValue());
                } else if (index == 7) {
                    Assert.assertEquals(240, frame.getResult().getAccumulatedValue());
                } else if (index == 8) {
                    Assert.assertEquals(270, frame.getResult().getAccumulatedValue());
                }
                Assert.assertEquals(1, frame.getScore().getScoredValues().size());
            }
        }
    }

    @Test
    public void testCalculationFiveSeven() {
        //Act
        PlayerFrames playerFrames = playerFramesList.get(0);
        for (int i = 0; i < 10; i++) {
            Frame frame;
            if (i == 9) {
                frame = new LastFrame(playerFrames.getResultCalculator());
                frame.addValue(3);
                frame.addValue(4);
                frame.getResult().setCalculated(true);
            } else {
                frame = new Frame(playerFrames.getResultCalculator());
                frame.addValue(2);
                frame.addValue(3);
            }
        }

        //Assert
        List<Frame> frameList = playerFrames.getFrames().getFrameList();
        for (Frame frame : frameList) {
            int index = frameList.indexOf(frame);
            if (index == 9) {
                Assert.assertEquals(7, frame.getResult().getFinalValue());
                Assert.assertEquals(52, frame.getResult().getAccumulatedValue());
            } else {
                Assert.assertEquals(5, frame.getResult().getFinalValue());
                if (index == 0) {
                    Assert.assertEquals(5, frame.getResult().getAccumulatedValue());
                } else if (index == 1) {
                    Assert.assertEquals(10, frame.getResult().getAccumulatedValue());
                } else if (index == 2) {
                    Assert.assertEquals(15, frame.getResult().getAccumulatedValue());
                } else if (index == 3) {
                    Assert.assertEquals(20, frame.getResult().getAccumulatedValue());
                } else if (index == 4) {
                    Assert.assertEquals(25, frame.getResult().getAccumulatedValue());
                } else if (index == 5) {
                    Assert.assertEquals(30, frame.getResult().getAccumulatedValue());
                } else if (index == 6) {
                    Assert.assertEquals(35, frame.getResult().getAccumulatedValue());
                } else if (index == 7) {
                    Assert.assertEquals(40, frame.getResult().getAccumulatedValue());
                } else if (index == 8) {
                    Assert.assertEquals(45, frame.getResult().getAccumulatedValue());
                }
            }
            Assert.assertEquals(2, frame.getScore().getScoredValues().size());
        }
    }

    @Test
    public void testCalculationOne() {
        //Act
        PlayerFrames playerFrames = playerFramesList.get(0);
        for (int i = 0; i < 10; i++) {
            Frame frame;
            if (i == 9) {
                frame = new LastFrame(playerFrames.getResultCalculator());
                frame.addValue(1);
                frame.addValue(1);
                frame.getResult().setCalculated(true);
            } else {
                frame = new Frame(playerFrames.getResultCalculator());
                frame.addValue(1);
                frame.addValue(1);
            }
        }

        //Assert
        List<Frame> frameList = playerFrames.getFrames().getFrameList();
        for (Frame frame : frameList) {
            int index = frameList.indexOf(frame);
            if (index == 9) {
                Assert.assertEquals(20, frame.getResult().getAccumulatedValue());
            } else {
                if (index == 0) {
                    Assert.assertEquals(2, frame.getResult().getAccumulatedValue());
                } else if (index == 1) {
                    Assert.assertEquals(4, frame.getResult().getAccumulatedValue());
                } else if (index == 2) {
                    Assert.assertEquals(6, frame.getResult().getAccumulatedValue());
                } else if (index == 3) {
                    Assert.assertEquals(8, frame.getResult().getAccumulatedValue());
                } else if (index == 4) {
                    Assert.assertEquals(10, frame.getResult().getAccumulatedValue());
                } else if (index == 5) {
                    Assert.assertEquals(12, frame.getResult().getAccumulatedValue());
                } else if (index == 6) {
                    Assert.assertEquals(14, frame.getResult().getAccumulatedValue());
                } else if (index == 7) {
                    Assert.assertEquals(16, frame.getResult().getAccumulatedValue());
                } else if (index == 8) {
                    Assert.assertEquals(18, frame.getResult().getAccumulatedValue());
                }
            }
            Assert.assertEquals(2, frame.getResult().getFinalValue());
            Assert.assertEquals(2, frame.getScore().getScoredValues().size());
        }
    }

}
