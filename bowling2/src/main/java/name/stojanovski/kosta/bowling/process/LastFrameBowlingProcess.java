package name.stojanovski.kosta.bowling.process;

import name.stojanovski.kosta.bowling.generator.IBowingAttemptsGenerator;
import name.stojanovski.kosta.bowling.model.Frame;
import name.stojanovski.kosta.bowling.model.FrameInfo;
import name.stojanovski.kosta.bowling.model.Results;

/**
 * Extra processing for the last frame.
 */
public class LastFrameBowlingProcess extends AbstractBowlingProcess {

	private static final int LAST_FRAME_EXTRA_ATTEMPTS = 3;

	public LastFrameBowlingProcess(Results Results,
			IBowingAttemptsGenerator bowingAttemptsGenerator) {
		super(Results, bowingAttemptsGenerator);
	}

	/**
	 * Extra attempts addition to the frame.
	 * 
	 * @param frame
	 * @param frameIndex
	 */
	public void extraFramesEvaluation(Frame frame, int frameIndex) {
		if (Results.isLastResultNotNormal()) {
			super.initAttemptValue();
			int attemptIndex = frame.getAttempts().size();
			if (Results.isLastResultStrike()) {
				for (; attemptIndex < LAST_FRAME_EXTRA_ATTEMPTS; attemptIndex++) {
					attemptValue = bowingAttemptsGenerator.generateAttemptValue(
							frameIndex, attemptIndex, attemptValue);
					frame.addAttempt(new FrameInfo(frameIndex, attemptIndex,
							attemptValue));
				}
			} else if (Results.isLastResultSpare()) {
				attemptValue = bowingAttemptsGenerator.generateAttemptValue(
						frameIndex, attemptIndex, attemptValue);
				frame.addAttempt(
						new FrameInfo(frameIndex, attemptIndex, attemptValue));
			}
		}
	}
}
