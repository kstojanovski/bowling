package name.stojanovski.kosta.bowling.eval;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import name.stojanovski.kosta.bowling.input.BowlsGenerator;
import name.stojanovski.kosta.bowling.input.model.ProfileFrame;
import name.stojanovski.kosta.bowling.input.model.ProfileFrames;
import name.stojanovski.kosta.bowling.model.BowlingEnum;
import name.stojanovski.kosta.bowling.model.Frame;

public class BowlingProcess {

	private static final int FRAME_SIZE = 10;

	private BowlingProcess() {
	}
	
	public static List<Frame> eval() {
		return eval(null);
	}
	
	public static List<Frame> eval(ProfileFrames profileFrames) {
		boolean testMode = Objects.nonNull(profileFrames);
		
		List<Frame> frames = new ArrayList<>();

		// iterate bowls / set and evaluate
		for (int i = 0; i < FRAME_SIZE; i++) {
			// create bowl randomly or load it from profile
			ProfileFrame profileFrame = !testMode ? null : profileFrames.getProfileFrame().get(i);
			
			Frame frame = new Frame();
			frames.add(frame);

			// first attempt
			Integer firstBowl = !testMode ? BowlsGenerator.createFirst() : profileFrame.getBowls().get(0);
			frame.getBowls().add(firstBowl);

			// evaluate previous for spare and strike-first-addition or eventually second
			// addition.
			modifiyPreviousesForSpareStrike(i, firstBowl, frames);
			
			Integer secondBowl = 0;
			if (firstBowl != 10) {
				secondBowl = !testMode ? BowlsGenerator.createSecond(firstBowl) : profileFrame.getBowls().get(1);
				// second attempt
				setAndEvalSecondBowl(i, firstBowl, secondBowl, frame, frames);
			} else {
				frame.setExtraGood(BowlingEnum.STRIKE);
			}			

			// evaluate current
			setCurrentResult(i, firstBowl, secondBowl, frame, frames);

			// extra calculation for SPARE STRIKE on last frame.
			if (i == FRAME_SIZE - 1 && Objects.nonNull(frame.getExtraGood())) {
				Integer extraBowl = null;
				if (testMode) {
					int index = BowlingEnum.STRIKE.equals(frame.getExtraGood()) ? 1 : 2;
					extraBowl = profileFrame.getBowls().get(index);
				} else {
					extraBowl = BowlsGenerator.createFirst();
				}
				extraBowl(frame, extraBowl);

				modifyPreviousesForPenultimateStrike(extraBowl, frame, frames.get(i - 1));

				if (BowlingEnum.STRIKE.equals(frame.getExtraGood())) {
					Integer secondExtraBowl = !testMode ? BowlsGenerator.createFirst() : profileFrame.getBowls().get(2);
					extraBowl(frame, secondExtraBowl);
				}
			}
		}
		return frames;
	}

	/**
	 * Extra Bowl on last item if it SPARE or 2 attempts if STRIKE
	 * 
	 * @param frame
	 * @return
	 */
	private static void extraBowl(Frame frame, Integer extraBowl) {
		frame.getBowls().add(extraBowl);
		frame.addResult(extraBowl);
		frame.addAggResult(extraBowl);
	}

	/**
	 * 
	 * 
	 * @param i
	 * @param firstBowl
	 * @param secondBowl
	 * @param frame
	 * @param frames
	 */
	private static void setCurrentResult(int i, Integer firstBowl, Integer secondBowl, Frame frame,
			List<Frame> frames) {
		frame.addResult(firstBowl + secondBowl);
		// initialization of the aggregate result
		if (i == 0) {
			frame.addAggResult(frame.getResult());
		} else {
			Frame previousFrame = frames.get(i - 1);
			frame.addAggResult(previousFrame.getAggResult() + frame.getResult());
		}
	}

	/**
	 * If not STRIKE: * generate the value. * check for possible SPARE and mark the
	 * frame if matches. * modify the previous depend on the value if it is STRIKE.
	 * else then mark the frame as STRIKE.
	 * 
	 * @param i
	 * @param firstBowl
	 * @param frame
	 * @param frames
	 * @return
	 */
	private static void setAndEvalSecondBowl(int i, Integer firstBowl, Integer secondBowl, Frame frame,
			List<Frame> frames) {
		// if the first is strike ignore the next code.
		frame.getBowls().add(secondBowl);
		if (firstBowl + secondBowl == 10) {
			frame.setExtraGood(BowlingEnum.SPARE);
		}
		// if the previous was strike consider the second bowl for the calculation
		if (i > 0) {
			modifyPreviousStrikeSecond(secondBowl, frames.get(i - 1));
		}
	}

	/**
	 * Modify previous for STRIKE if the current has second bowl attempt.
	 * 
	 * @param secondBowl
	 * @param previousFrame
	 */
	private static void modifyPreviousStrikeSecond(Integer secondBowl, Frame previousFrame) {
		if (BowlingEnum.STRIKE.equals(previousFrame.getExtraGood())) {
			previousFrame.addResult(secondBowl);
			previousFrame.addAggResult(secondBowl);
			previousFrame.incStrikeResult();
		}
	}

	/**
	 * Adjust the current and previous frame because the previous is an STRIKE and
	 * the second addition is made on the extra bowl.
	 * 
	 * @param i
	 * @param extraBowl
	 * @param frame
	 * @param previousFrame
	 */
	private static void modifyPreviousesForPenultimateStrike(Integer extraBowl, Frame frame, Frame previousFrame) {
		// what about spare - do we need extra if condition
		if (previousFrame.getStrikeResult() == 1) {
			previousFrame.addResult(extraBowl);
			previousFrame.addAggResult(extraBowl);
			frame.addAggResult(extraBowl);
		}
	}

	/**
	 * Modify the previous results depend on the rules for SPARE and STRIKE: * SPARE
	 * and first addition for STRIKE * Second addition for STRIKE
	 * 
	 * @param i
	 * @param firstBowl
	 * @param frames
	 */
	private static void modifiyPreviousesForSpareStrike(int i, Integer firstBowl, List<Frame> frames) {
		if (i > 0) {
			Frame previousFrame = frames.get(i - 1);
			modifyPreviousForSpareStrike(firstBowl, previousFrame);
			if (i > 1) {
				Frame prePreviousFrame = frames.get(i - 2);
				modifyPreviousesForStrike(firstBowl, previousFrame, prePreviousFrame);
			}
		}
	}

	/**
	 * Modify the two previous results for STRIKE because it is the second addition.
	 * 
	 * @param firstBowl
	 * @param previousFrame
	 * @param prePreviousFrame
	 */
	private static void modifyPreviousesForStrike(Integer firstBowl, Frame previousFrame, Frame prePreviousFrame) {
		if (BowlingEnum.STRIKE.equals(prePreviousFrame.getExtraGood()) && prePreviousFrame.getStrikeResult() == 1) {
			prePreviousFrame.addResult(firstBowl);
			previousFrame.addAggResult(firstBowl);
			prePreviousFrame.addAggResult(firstBowl);
			prePreviousFrame.incStrikeResult();
		}
	}

	/**
	 * Modify the previous results if they where SPARE or STRIKE (first addition).
	 * 
	 * @param firstBowl
	 * @param previousFrame
	 */
	private static void modifyPreviousForSpareStrike(Integer firstBowl, Frame previousFrame) {
		if (Objects.nonNull(previousFrame.getExtraGood())) {
			previousFrame.addResult(firstBowl);
			previousFrame.addAggResult(firstBowl);
			if (BowlingEnum.STRIKE.equals(previousFrame.getExtraGood())) {
				previousFrame.incStrikeResult();
			}
		}
	}
}
