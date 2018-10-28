package name.stojanovski.kosta.bowling.process;

import name.stojanovski.kosta.bowling.generator.IBowingAttemptsGenerator;
import name.stojanovski.kosta.bowling.model.Frame;
import name.stojanovski.kosta.bowling.model.FrameInfo;
import name.stojanovski.kosta.bowling.model.Results;

/**
 * Processing the frames.
 */
public class FrameBowlingProcess extends AbstractBowlingProcess {

	private static final int ATTEMPTS = 2;

	public FrameBowlingProcess(Results Results,
			IBowingAttemptsGenerator bowingAttemptsGenerator) {
		super(Results, bowingAttemptsGenerator);
	}

	/**
	 * Addition of the attempts to the frame.
	 * 
	 * @param frame
	 * @param frameIndex
	 */
	public void casualFramesEvaluation(Frame frame, int frameIndex) {
		super.initAttemptValue();
		for (int attemptIndex = 0; attemptIndex < ATTEMPTS; attemptIndex++) {
			attemptValue = bowingAttemptsGenerator.generateAttemptValue(
					frameIndex, attemptIndex, attemptValue);
			frame.addAttempt(new FrameInfo(frameIndex, attemptIndex,
					attemptValue));
			if (Results.isLastResultStrike()) {
				break;
			}
		}
	}
}
