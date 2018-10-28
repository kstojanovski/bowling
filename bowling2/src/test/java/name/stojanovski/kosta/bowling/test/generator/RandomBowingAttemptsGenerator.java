package name.stojanovski.kosta.bowling.test.generator;

import name.stojanovski.kosta.bowling.generator.IBowingAttemptsGenerator;

public class RandomBowingAttemptsGenerator implements IBowingAttemptsGenerator {

	public int generateAttemptValue(int frameIndex, int attemptIndex, int attemptValue) {
		switch (frameIndex) {
		case 0:
			if (attemptIndex == 0) {
				return 4;
			} else {
				return 6;
			}
		case 1:
			if (attemptIndex == 0) {
				return 4;
			} else {
				return 6;
			}
		case 2:
			if (attemptIndex == 0) {
				return 4;
			} else  {
				return 6;
			}
		case 3:
			if (attemptIndex == 0) {
				return 4;
			} else  {
				return 6;
			}
		case 4:
			if (attemptIndex == 0) {
				return 4;
			} else  {
				return 6;
			}
		case 5:
			if (attemptIndex == 0) {
				return 4;
			} else  {
				return 6;
			}
		case 6:
			if (attemptIndex == 0) {
				return 4;
			} else  {
				return 6;
			}
		case 7:
			if (attemptIndex == 0) {
				return 4;
			} else  {
				return 6;
			}
		case 8:
			if (attemptIndex == 0) {
				return 4;
			} else  {
				return 6;
			}
		case 9:
			if (attemptIndex == 0) {
				return 4;
			} else {
				return 6;
			}
		default:
			return 8;
		}
	}
}
