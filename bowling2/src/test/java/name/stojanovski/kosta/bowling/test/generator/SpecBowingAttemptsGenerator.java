package name.stojanovski.kosta.bowling.test.generator;

import name.stojanovski.kosta.bowling.generator.IBowingAttemptsGenerator;

public class SpecBowingAttemptsGenerator implements IBowingAttemptsGenerator {

	public int generateAttemptValue(int frameIndex, int attemptIndex, int attemptValue) {
		switch (frameIndex) {
		case 0:
			if (attemptIndex == 0) {
				return 1;
			} else {
				return 4;
			}
		case 1:
			if (attemptIndex == 0) {
				return 4;
			} else {
				return 5;
			}
		case 2:
			if (attemptIndex == 0) {
				return 6;
			} else  {
				return 4;
			}
		case 3:
			if (attemptIndex == 0) {
				return 5;
			} else  {
				return 5;
			}
		case 4:
			if (attemptIndex == 0) {
				return 10;
			}
		case 5:
			if (attemptIndex == 0) {
				return 0;
			} else  {
				return 1;
			}
		case 6:
			if (attemptIndex == 0) {
				return 7;
			} else  {
				return 3;
			}
		case 7:
			if (attemptIndex == 0) {
				return 6;
			} else  {
				return 4;
			}
		case 8:
			if (attemptIndex == 0) {
				return 10;
			}
		case 9:
			if (attemptIndex == 0) {
				return 2;
			} else if (attemptIndex == 1) {
				return 8;
			} else {
				return 6;
			}
		default:
			return 0;
		}
	}
}
