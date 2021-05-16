package org.acme.model;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private final List<Frame> frameList;

    public Frames() {
        this.frameList = new ArrayList<>();
    }

    public List<Frame> getFrameList() {
        return frameList;
    }

    public Frame getPreFrame(int currentFrameIndex) {
        return frameList.get(currentFrameIndex - 1);
    }

    public Frame getPrePreFrame(int currentFrameIndex) {
        return frameList.get(currentFrameIndex - 2);
    }

    public Frame getPrePrePreFrame(int currentFrameIndex) {
        return frameList.get(currentFrameIndex - 3);
    }
}
