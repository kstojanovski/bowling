package name.stojanovski.kosta.bowling.generator;

/**
 * Attempt ranges for the bowling generator.
 */ 
public enum Attempts {

	MIN(0), MAX(11);

	private int rangeValue;

	Attempts(int rangeValue) {
		this.rangeValue = rangeValue;
	}

	public int getRangeValue() {
		return rangeValue;
	}

}
