package name.stojanovski.kosta.bowling.test.generator;

import name.stojanovski.kosta.bowling.generator.IBowingAttemptsGenerator;

public class PerfBowingAttemptsGenerator implements IBowingAttemptsGenerator {

	public int generateAttemptValue(int frameIndex, int attemptIndex, int attemptValue) {
		return 10;
	}
	
}
