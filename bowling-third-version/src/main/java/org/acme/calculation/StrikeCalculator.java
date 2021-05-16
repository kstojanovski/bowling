package org.acme.calculation;

import org.acme.model.Frame;
import org.acme.model.FrameType;
import org.acme.model.Frames;
import org.acme.model.Result;

import java.util.List;

public class StrikeCalculator {

    private StrikeCalculator() {

    }

    /**
     * Two cases are here:
     * - When the current frame is on the second value check if the previous
     * frame is STRIKE and calculate the final value like both of the current
     * plus the base of the previous.
     * - When the current frame is on the first value and the previous frame
     * was STRIKE and also the pre previous was also STRIKE then calculate
     * the value from the current base value plus the previous base plus the
     * pre previous base value.
     *
     * @param frames     all previous frames until the current one.
     * @param frameIndex the current frame index from the frame list.
     */
    public static void calculateStrike(Frames frames, int frameIndex) {
        List<Frame> frameList = frames.getFrameList();
        Frame currentFrame = frameList.get(frameIndex);
        int thisFrameSize = currentFrame.getScore().getScoredValues().size();
        if (frameList.size() > 1) {
            Frame preFrame = frames.getPreFrame(frameIndex);
            if (thisFrameSize == 2
                    && FrameType.STRIKE.equals(preFrame.getFrameType())
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
            if (frameList.size() > 2) {
                Frame prePreFrame = frames.getPrePreFrame(frameIndex);
                if (thisFrameSize == 1
                        && FrameType.STRIKE.equals(preFrame.getFrameType())
                        && FrameType.STRIKE.equals(prePreFrame.getFrameType())
                ) {
                    int preBaseResult = prePreFrame.getResult().getBaseValue();
                    Result prePreFrameResult = prePreFrame.getResult();
                    prePreFrameResult.setFinalValue(prePreFrameResult.getBaseValue() + preBaseResult + currentFrame.getResult().getBaseValue());
                    prePreFrameResult.setCalculated(true);
                    prePreFrameResult.setAccumulatedValue(
                            frameList.size() > 3
                                    ? frames.getPrePrePreFrame(frameIndex).getResult().getAccumulatedValue() + prePreFrameResult.getFinalValue()
                                    : prePreFrameResult.getFinalValue()
                    );
                }
            }
        }
    }

}
