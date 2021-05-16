package org.acme.model;

import org.acme.manualtest.ResultCalculator;

public class PlayerFrames {

    private final Player player;
    private final Frames frames;
    private ResultCalculator resultCalculator;


    public PlayerFrames(Player player) {
        this.player = player;
        this.frames = new Frames();
        resultCalculator = new ResultCalculator(frames);
    }

    public Frames getFrames() {
        return frames;
    }

    public ResultCalculator getResultCalculator() {
        return resultCalculator;
    }

    public Player getPlayer() {
        return player;
    }
}
