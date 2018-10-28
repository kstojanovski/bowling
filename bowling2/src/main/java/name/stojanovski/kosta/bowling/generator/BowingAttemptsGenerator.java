package name.stojanovski.kosta.bowling.generator;

import java.util.concurrent.ThreadLocalRandom;

public class BowingAttemptsGenerator implements IBowingAttemptsGenerator {

  /**
   * Generates attempt values when all pins are up. 
   */
	protected int createFirst() {
		return ThreadLocalRandom.current().nextInt(Attempts.MIN.getRangeValue(),
				Attempts.MAX.getRangeValue());
	}

  /**
   * Generates attempt values when some pins are down.
   */     
	protected int createSecond(int firstBowl) {
		return ThreadLocalRandom.current().nextInt(Attempts.MIN.getRangeValue(),
				Attempts.MAX.getRangeValue() - firstBowl);
	}

  /**
   * Generates attempt values depend on previous value. 
   */
	public int generateAttemptValue(int frameIndex, int attemptIndex,
			int attemptValue) {
		if (attemptValue == 0 || attemptValue == 10) {
			return createFirst();
		} else {
			return createSecond(attemptValue);
		}
	}

}
