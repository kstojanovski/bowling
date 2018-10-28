package name.stojanovski.kosta.bowling.eval;

import name.stojanovski.kosta.bowling.model.FrameInfo;
import name.stojanovski.kosta.bowling.model.Result;
import name.stojanovski.kosta.bowling.model.Results;

public class SecondAttemptStrategy implements IStrategy {

	/**
	 * Adds the attempt value (index 1) to the previous strike frame, because
	 * the current frame has second attempts which can be normal or strike.
	 */
	@Override
	public void recalculatePrevouses(FrameInfo frameInfo, Results results) {
		if (frameInfo.getFrameIndex() > 0) {
			Result previousResult = results
					.getPreviousResult(frameInfo.getFrameIndex());
			if (previousResult.isStrike() && frameInfo.getAttemptIndex() == 1) {
				previousResult.previousAddition(frameInfo.getAttemptValue());
			}
		}
	}

}
