package org.acme.model;

import org.acme.calculation.ResultCalculator;

import java.util.List;

public class Frame {

    private FrameType frameType;

    final Score score;
    final Result result;
    final Frames frames;
    final ResultCalculator resultCalculator;

    public FrameType getFrameType() {
        return frameType;
    }

    public Frame(ResultCalculator resultCalculator) {
        this.score = new Score();
        this.result = new Result();
        this.frameType = FrameType.NORMAL;
        this.frames = resultCalculator.getFrames();
        frames.getFrameList().add(this);
        this.resultCalculator = resultCalculator;
    }

    public void addValue(Integer value) {
        List<Integer> scoredValues = score.getScoredValues();
        scoredValues.add(value);
        result.addBaseValue(value);
        if (scoredValues.size() == 1 && value == 10) {
            frameType = FrameType.STRIKE;
        } else if (scoredValues.size() == 2) {
            if (scoredValues.stream().mapToInt(o -> o).sum() == 10) {
                frameType = FrameType.SPARE;
            } else {
                result.addFinalValue(result.getBaseValue());
                result.setCalculated(true);
            }
        }
        resultCalculator.addAndCalculateResult(frames, this);
        result.setAccumulatedValue(
                frames.getFrameList().size() > 1
                        ? frames.getPreFrame(frames.getFrameList().indexOf(this)).getResult().getAccumulatedValue() + result.getFinalValue()
                        : result.getFinalValue()
        );
    }

    public Score getScore() {
        return score;
    }

    public Result getResult() {
        return result;
    }

}
