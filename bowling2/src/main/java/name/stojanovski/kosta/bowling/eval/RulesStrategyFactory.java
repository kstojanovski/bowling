package name.stojanovski.kosta.bowling.eval;

public class RulesStrategyFactory {

	private RulesStrategyFactory() {
	}

	/**
	 * Returns strategy for calculation of the previous values depend on the
	 * attempt index.
	 * 
	 * @param attemptIndex
	 * @return
	 */
	public static IStrategy createStrategy(int attemptIndex) {
		return attemptIndex == 0 ? new FirstAttemptStrategy()
				: new SecondAttemptStrategy();
	}

}
