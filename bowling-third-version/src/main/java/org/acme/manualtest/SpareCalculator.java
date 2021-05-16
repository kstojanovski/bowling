package org.acme.manualtest;

import org.acme.model.Frame;
import org.acme.model.FrameType;
import org.acme.model.Frames;
import org.acme.model.Result;

import java.util.List;

public class SpareCalculator {

    private SpareCalculator() {

    }

    /**
     * Check if previous frame is spare, calculate and add the final value.
     * The value is calculated from the base value of the previous frame
     * and the first value of the current frame independently what kind of the value the current is.
     *
     * @param frames     all previous frames until the current one.
     * @param frameIndex the current frame index from the frame list.
     */
    public static void calculateSpare(Frames frames, int frameIndex) {
        List<Frame> frameList = frames.getFrameList();
        Frame currentFrame = frameList.get(frameIndex);
        int thisFrameSize = currentFrame.getScore().getScoredValues().size();
        if (frameList.size() > 1) {
            Frame preFrame = frames.getPreFrame(frameIndex);
            if (thisFrameSize == 1
                    && FrameType.SPARE.equals(preFrame.getFrameType())
            ) {
                Result preFrameResult = preFrame.getResult();
                preFrameResult.setFinalValue(preFrameResult.getBaseValue() + currentFrame.getResult().getBaseValue());
                preFrameResult.setCalculated(true);
                preFrameResult.setAccumulatedValue(
                        frameList.size() > 2
                                ? frames.getPrePreFrame(frameIndex).getResult().getAccumulatedValue() + preFrameResult.getFinalValue()
                                : preFrameResult.getFinalValue()
                );
            }
        }
    }

}
