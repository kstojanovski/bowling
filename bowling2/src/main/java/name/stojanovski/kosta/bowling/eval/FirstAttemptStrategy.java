package name.stojanovski.kosta.bowling.eval;

import name.stojanovski.kosta.bowling.model.FrameInfo;
import name.stojanovski.kosta.bowling.model.Result;
import name.stojanovski.kosta.bowling.model.Results;

public class FirstAttemptStrategy implements IStrategy {

	/**
	 * Calculate previous if attempt is 0 if the previous frame is spare or
	 * strike. Also if the previous frame is strike then it has only one attempt
	 * which means if the pre-previous frame is strike it also needs this
	 * attempt value added to its frame value.
	 */
	@Override
	public void recalculatePrevouses(FrameInfo frameInfo, Results results) {
		if (frameInfo.getFrameIndex() > 0) {
			Result previousResult = results
					.getPreviousResult(frameInfo.getFrameIndex());
			if (frameInfo.getAttemptIndex() == 0
					&& previousResult.isNotNormal()) {
				previousResult.previousAddition(frameInfo.getAttemptValue());
				if (previousResult.isStrike()) {
					twoPreviousStrikeFramesRule(frameInfo, results);
				}
			}
		}
	}

	/**
	 * Adds the current attempt value to the pre-previous frame.
	 * 
	 * @param frameInfo
	 * @param results
	 */
	private void twoPreviousStrikeFramesRule(FrameInfo frameInfo,
			Results results) {
		if (frameInfo.getFrameIndex() > 1) {
			Result prePreviousResult = results
					.getPrePreviousResult(frameInfo.getFrameIndex());
			if (prePreviousResult.isStrike()) {
				prePreviousResult.previousAddition(frameInfo.getAttemptValue());
			}
		}
	}

}
