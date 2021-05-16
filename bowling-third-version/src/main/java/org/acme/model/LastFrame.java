package org.acme.model;

import org.acme.manualtest.ResultCalculator;

import java.util.ArrayList;
import java.util.List;

public class LastFrame extends Frame {

    private List<FrameType> frameTypes;

    public LastFrame(ResultCalculator resultCalculator) {
        super(resultCalculator);
        frameTypes = new ArrayList<>();
    }

    @Override
    public void addValue(Integer value) {
        List<Integer> scoredValues = score.getScoredValues();
        scoredValues.add(value);
        result.addBaseValue(value);
        result.addFinalValue(value);
        frameTypes.add(FrameType.NORMAL);
        result.setAccumulatedValue(frames.getPreFrame(frames.getFrameList().indexOf(this)).getResult().getAccumulatedValue() + result.getFinalValue());
        if (value == 10) {
            frameTypes.set(frameTypes.size() - 1, FrameType.STRIKE);
        } else if (scoredValues.size() == 2 && scoredValues.stream().mapToInt(o -> o).sum() == 10) {
            frameTypes.set(frameTypes.size() - 1, FrameType.SPARE);
        }
        resultCalculator.addAndCalculateResult(frames, this);
    }

}
