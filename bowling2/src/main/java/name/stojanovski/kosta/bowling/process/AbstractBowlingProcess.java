package name.stojanovski.kosta.bowling.process;

import name.stojanovski.kosta.bowling.generator.IBowingAttemptsGenerator;
import name.stojanovski.kosta.bowling.model.Results;

/**
 * Bowling process which needs results and bowling generator to work.
 *
 */
public abstract class AbstractBowlingProcess {

	protected int attemptValue;

	protected Results Results;
	protected IBowingAttemptsGenerator bowingAttemptsGenerator;

	protected AbstractBowlingProcess(Results results,
			IBowingAttemptsGenerator bowingAttemptsGenerator) {
		this.Results = results;
		this.bowingAttemptsGenerator = bowingAttemptsGenerator;
	}

	/**
	 * Initializing the attempt value
	 */
	public void initAttemptValue() {
		attemptValue = 0;
	}
}
