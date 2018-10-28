package name.stojanovski.kosta.bowling.process;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import name.stojanovski.kosta.bowling.eval.ResultObserver;
import name.stojanovski.kosta.bowling.model.Frame;
import name.stojanovski.kosta.bowling.model.FrameInfo;
import name.stojanovski.kosta.bowling.model.Frames;
import name.stojanovski.kosta.bowling.model.Result;
import name.stojanovski.kosta.bowling.model.Results;

public class TestSettingAttemptsForAllFrames {

	Results Results = new Results();
	Frames frames;
	
	@Before
	public void init() {
		Results = new Results(); 
		 ResultObserver ResultObserver = new ResultObserver(Results);
		frames = new Frames(ResultObserver);
	}
	
	/**
	 * Testing for an game where all attempts are strikes.
	 * The inputs are made manually by adding directly to the frame.  
	 */
	@Test
	public void testPerfectGame() {
		//Arrange - creating the expected
		Map<Integer, Integer> expected = new LinkedHashMap<Integer, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7501338796629156377L;
		{
			int distance = 30;
			for (int i = distance; i < 310; i = i + distance) {
				put(i, distance);
			}
		}};

		//Act - creating the actual
		for (Frame frame : frames.getFrameCollection()) {
			Integer frameNr = frames.getFrameCollection().indexOf(frame);
			frame.addAttempt(new FrameInfo(frameNr, 0, 10));
			
			if (frameNr == 9) {
				for (int attemptNr = 1; attemptNr < 3; attemptNr++) {
					frame.addAttempt(new FrameInfo(frameNr, attemptNr, 10));
				}
			}
		}

		//Assert - compare
		compare(expected);
		assertEquals((Integer)300, Results.getLastResult().getAggValue());
	}
	
	@Test
	public void testSpecificationGame() {
		//Arrange - creating the expected
		Map<Integer, Integer> expected = new LinkedHashMap<Integer, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7501338796629156377L;
		{
			put(5, 5);
			put(14, 9);
			put(29, 15);
			put(49, 20);
			put(60, 11);
			put(61, 1);
			put(77, 16);
			put(97, 20);
			put(117, 20);
			put(133, 16);
		}};
		
		//Act
		for (Frame frame : frames.getFrameCollection()) {
			int frameNr = frames.getFrameCollection().indexOf(frame);
			switch (frameNr) {
			case 0:
				frame.addAttempt(new FrameInfo(frameNr, 0, 1));
				frame.addAttempt(new FrameInfo(frameNr, 1, 4));				
				break;
			case 1:
				frame.addAttempt(new FrameInfo(frameNr, 0, 4));
				frame.addAttempt(new FrameInfo(frameNr, 1, 5));				
				break;
			case 2:
				frame.addAttempt(new FrameInfo(frameNr, 0, 6));
				frame.addAttempt(new FrameInfo(frameNr, 1, 4));				
				break;
			case 3:
				frame.addAttempt(new FrameInfo(frameNr, 0, 5));
				frame.addAttempt(new FrameInfo(frameNr, 1, 5));				
				break;
			case 4:
				frame.addAttempt(new FrameInfo(frameNr, 0, 10));
				break;
			case 5:
				frame.addAttempt(new FrameInfo(frameNr, 0, 0));
				frame.addAttempt(new FrameInfo(frameNr, 1, 1));				
				break;
			case 6:
				frame.addAttempt(new FrameInfo(frameNr, 0, 7));
				frame.addAttempt(new FrameInfo(frameNr, 1, 3));				
				break;
			case 7:
				frame.addAttempt(new FrameInfo(frameNr, 0, 6));
				frame.addAttempt(new FrameInfo(frameNr, 1, 4));				
				break;
			case 8:
				frame.addAttempt(new FrameInfo(frameNr, 0, 10));
				break;
			case 9:
				frame.addAttempt(new FrameInfo(frameNr, 0, 2));
				frame.addAttempt(new FrameInfo(frameNr, 1, 8));				
				frame.addAttempt(new FrameInfo(frameNr, 2, 6));
				break;
			default:
				break;
			}
		}
		
		//Assert
		compare(expected);
		assertEquals((Integer)133, Results.getLastResult().getAggValue());
	}
	
	private void compare(Map<Integer, Integer> expected) {
		Iterator<Result> actualIter = Results.getResults().iterator();
		Iterator<Integer> expectedIter = expected.keySet().iterator();
		while (expectedIter.hasNext()) {
			Integer expAggValue = (Integer) expectedIter.next();
			Result Result = actualIter.next();
			assertEquals(expected.get(expAggValue), Result.getValue());
			assertEquals(expAggValue, Result.getAggValue());
		}
	}
}
