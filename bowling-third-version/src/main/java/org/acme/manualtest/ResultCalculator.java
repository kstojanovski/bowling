package org.acme.manualtest;

import org.acme.model.Frame;
import org.acme.model.Frames;

public class ResultCalculator {

    final private Frames frames;

    public ResultCalculator(Frames frames) {
        this.frames = frames;
    }

    public void addAndCalculateResult(Frames frames, Frame currentFrame) {
        int frameIndex = frames.getFrameList().indexOf(currentFrame);
        SpareCalculator.calculateSpare(frames, frameIndex);
        StrikeCalculator.calculateStrike(frames, frameIndex);


    }

    public Frames getFrames() {
        return frames;
    }
}
