package name.stojanovski.kosta.bowling.input;

import java.util.concurrent.ThreadLocalRandom;

public class BowlsGenerator {

	private static final int MAX = 11;
	private static final int MIN = 0;
	
	private BowlsGenerator() {
	}
	
	public static Integer createFirst() {
		return ThreadLocalRandom.current().nextInt(MIN, MAX);
	}
	
	public static Integer createSecond(int firstBowl) {
		return ThreadLocalRandom.current().nextInt(MIN, MAX - firstBowl);
	}
	
}
