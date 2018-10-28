package name.stojanovski.kosta.bowling.input.validation;

import java.util.List;

import name.stojanovski.kosta.bowling.input.model.ProfileFrame;
import name.stojanovski.kosta.bowling.input.model.ProfileFrames;

public class ProfileValidator {

	private ProfileValidator() {
		
	}
	
	/**
	 * Input method for profile validation. The structure needs to be well formed
	 * also the values and the count of the attempts need to be correct.
	 * 
	 * @param profileFrames
	 * @return
	 * @throws InvalidProfileException
	 */
	protected static ProfileFrames validate(ProfileFrames profileFrames) throws InvalidProfileException {
		List<ProfileFrame> profileFrameCollection = profileFrames.getProfileFrame();
		int size = profileFrameCollection.size();
		if (size != 10) {
			throw new InvalidProfileException();
		}
		for (int i = 0; i < size; i++) {
			ProfileFrame profileFrame = profileFrameCollection.get(i);
			List<Integer> bowls = profileFrame.getBowls();
			int bowlSize = bowls.size();
			checkForInvalidRange(bowls);
			if (i == size - 1) {
				validateLastElement(bowlSize, bowls);				
			} else {
				validateToLastElement(bowlSize, bowls);
			}
		}		
		return profileFrames;
	}

	/**
	 * Checks if the bowls values are correct.
	 * 
	 * @param bowls
	 * @throws InvalidProfileException
	 */
	private static void checkForInvalidRange(List<Integer> bowls) throws InvalidProfileException {
		for (Integer bowl : bowls) {
			if (checkInvalidRange(bowl)) {
				throw new InvalidProfileException();
			}
		}
	}

	/**
	 * Validates the frames but not the last.
	 * 
	 * @param bowlSize
	 * @param bowls
	 * @throws InvalidProfileException
	 */
	private static void validateToLastElement(int bowlSize, List<Integer> bowls) throws InvalidProfileException {
		switch (bowlSize) {
		case 1: //STRIKE
			if (!checkTen(bowls.get(0))) {
				throw new InvalidProfileException();
			}
			break;
		case 2: //SPARE or less
			if (checkInvalidRange(bowls.get(0) + bowls.get(1))) {
				throw new InvalidProfileException();
			}
			break;
		default:
			throw new InvalidProfileException();
		}
	}

	/**
	 * Validates the last frame.
	 * 
	 * @param bowlSize
	 * @param bowls
	 * @throws InvalidProfileException
	 */
	private static void validateLastElement(int bowlSize, List<Integer> bowls) throws InvalidProfileException {
		switch (bowlSize) {
		case 2: //not STRIKE and not SPARE
			if (
				checkTen(bowls.get(0)) || checkTen(bowls.get(0) + bowls.get(1))
			) {
				throw new InvalidProfileException();
			} 
			break;
		case 3: //STRIKE or SPARE
			if (
				!checkTen(bowls.get(0)) && !checkTen(bowls.get(0) + bowls.get(1))
			) {
				throw new InvalidProfileException();	
			}
			break;			
		default:
			throw new InvalidProfileException();
		}
	}

	/**
	 * Returns true if the value is out of range.
	 * 
	 * @param bowl
	 * @return
	 */
	private static boolean checkInvalidRange(Integer bowl) {
		return bowl > 10 || bowl < 0;
	}
	
	/**
	 * Returns true if the bowl is 10.
	 * 
	 * @param bowl
	 * @return
	 */
	private static boolean checkTen(Integer bowl) {
		return bowl == 10;
	}
}
