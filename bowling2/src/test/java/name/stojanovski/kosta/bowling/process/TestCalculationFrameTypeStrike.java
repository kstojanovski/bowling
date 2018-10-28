package name.stojanovski.kosta.bowling.process;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import name.stojanovski.kosta.bowling.eval.ResultObserver;
import name.stojanovski.kosta.bowling.model.Frame;
import name.stojanovski.kosta.bowling.model.FrameInfo;
import name.stojanovski.kosta.bowling.model.Frames;
import name.stojanovski.kosta.bowling.model.Result;
import name.stojanovski.kosta.bowling.model.Results;

public class TestCalculationFrameTypeStrike {
	Results Results = new Results();
	Frames frames;
	
	@Before
	public void init() {
		Results = new Results(); 
		 ResultObserver ResultObserver = new ResultObserver(Results);
		frames = new Frames(ResultObserver);
	}

	/**
	 * Consecutive strikes
	 */
	@Test
	public void testCalculateValuesStrikeFollowedByStrikes() {
		//Arrange
		Integer frameIndex = 0;
		Integer attemptIndex = 0;
		Integer attemptValue = 10;
		
		//Act
		Frame firstFrame = frames.getFrameCollection().get(0);
		
		//Assert
		Result firstlastResult0 = Results.getLastResult();
		assertNull(firstlastResult0);
		
		//Act
		firstFrame.addAttempt(new FrameInfo(frameIndex, attemptIndex, attemptValue));
		
		//Assert
		firstlastResult0 = Results.getLastResult();
		assertEquals((Integer)10, firstlastResult0.getValue());
		assertEquals((Integer)10, firstlastResult0.getAggValue());
		assertTrue(firstlastResult0.isStrike());		
		
		//Act
		Frame secondFrame = frames.getFrameCollection().get(1);
		++frameIndex;
		attemptIndex = 0;
		secondFrame.addAttempt(new FrameInfo(frameIndex, attemptIndex, attemptValue));
		
		//Assert
		Result previousResult1 = Results.getPreviousResult(frameIndex);
		assertEquals((Integer)20, previousResult1.getValue());
		assertEquals((Integer)20, previousResult1.getAggValue());
		assertTrue(previousResult1.isStrike());
		//Assert
		Result lastResult1 = Results.getLastResult();
		assertEquals((Integer)10, lastResult1.getValue());
		assertEquals((Integer)30, lastResult1.getAggValue());
		assertTrue(lastResult1.isStrike());
		
		//Act
		Frame thirdFrame = frames.getFrameCollection().get(2);
		++frameIndex;
		attemptIndex = 0;
		thirdFrame.addAttempt(new FrameInfo(frameIndex, attemptIndex, attemptValue));
		
		//Assert
		Result prePreviousResult2 = Results.getPrePreviousResult(frameIndex);
		assertEquals((Integer)30, prePreviousResult2.getValue());
		assertEquals((Integer)30, prePreviousResult2.getAggValue());
		//Assert
		Result previousResult2 = Results.getPreviousResult(frameIndex);
		assertEquals((Integer)20, previousResult2.getValue());
		assertEquals((Integer)50, previousResult2.getAggValue());
		//Assert
		Result lastResult2 = Results.getLastResult();
		assertEquals((Integer)10, lastResult2.getValue());
		assertEquals((Integer)60, lastResult2.getAggValue());
		assertTrue(Results.isLastResultStrike());
	}

	@Test
	public void testCalculateValuesStrikeFollowedByNormal() {
		//Arrange
		Integer frameIndex = 0;
		Integer attemptIndex = 0;
		Integer attemptValue = 10;
		
		//Act
		Frame firstFrame = frames.getFrameCollection().get(0);
		
		//Assert
		Result firstlastResult0 = Results.getLastResult();
		assertNull(firstlastResult0);
		
		//Act
		firstFrame.addAttempt(new FrameInfo(frameIndex, attemptIndex, attemptValue));
		
		//Assert
		firstlastResult0 = Results.getLastResult();
		assertEquals((Integer)10, firstlastResult0.getValue());
		assertEquals((Integer)10, firstlastResult0.getAggValue());
		assertTrue(firstlastResult0.isStrike());		
		
		//Act
		Frame secondFrame = frames.getFrameCollection().get(1);
		++frameIndex;
		attemptIndex = 0;
		attemptValue = 5;
		secondFrame.addAttempt(new FrameInfo(frameIndex, attemptIndex, attemptValue));
		
		//Assert
		Result previousResult1 = Results.getPreviousResult(frameIndex);
		assertEquals((Integer)15, previousResult1.getValue());
		assertEquals((Integer)15, previousResult1.getAggValue());
		assertTrue(previousResult1.isStrike());
		//Assert
		Result lastResult1 = Results.getLastResult();
		assertEquals((Integer)5, lastResult1.getValue());
		assertEquals((Integer)20, lastResult1.getAggValue());
		assertTrue(lastResult1.isNormal());
		
		//Act
		attemptValue = 3;
		secondFrame.addAttempt(new FrameInfo(frameIndex, ++attemptIndex, attemptValue));		

		//Assert
		assertEquals((Integer)18, previousResult1.getValue());
		assertEquals((Integer)18, previousResult1.getAggValue());
		assertTrue(previousResult1.isStrike());
		//Assert
		assertEquals((Integer)8, lastResult1.getValue());
		assertEquals((Integer)26, lastResult1.getAggValue());
		assertTrue(lastResult1.isNormal());
		
		//Act
		Frame thirdFrame = frames.getFrameCollection().get(2);
		++frameIndex;
		attemptIndex = 0;
		attemptValue = 3;
		thirdFrame.addAttempt(new FrameInfo(frameIndex, attemptIndex, attemptValue));
		
		//Assert
		Result prePreviousResult2 = Results.getPrePreviousResult(frameIndex);
		assertEquals((Integer)18, prePreviousResult2.getValue());
		assertEquals((Integer)18, prePreviousResult2.getAggValue());
		//Assert
		Result previousResult2 = Results.getPreviousResult(frameIndex);
		assertEquals((Integer)8, previousResult2.getValue());
		assertEquals((Integer)26, previousResult2.getAggValue());
		//Assert
		Result lastResult2 = Results.getLastResult();
		assertEquals((Integer)3, lastResult2.getValue());
		assertEquals((Integer)29, lastResult2.getAggValue());
		
		//Act
		attemptValue = 1;
		thirdFrame.addAttempt(new FrameInfo(frameIndex, ++attemptIndex, attemptValue));
		
		//Assert
		assertEquals((Integer)18, prePreviousResult2.getValue());
		assertEquals((Integer)18, prePreviousResult2.getAggValue());
		//Assert
		assertEquals((Integer)8, previousResult2.getValue());
		assertEquals((Integer)26, previousResult2.getAggValue());
		//Assert
		assertEquals((Integer)4, lastResult2.getValue());
		assertEquals((Integer)30, lastResult2.getAggValue());
	}
	
	@Test
	public void testCalculateValuesStrikeFollowedBySpareAndNormal() {
		//Arrange
		Integer frameIndex = 0;
		Integer attemptIndex = 0;
		Integer attemptValue = 10;
		
		//Act
		Frame firstFrame = frames.getFrameCollection().get(0);
		
		//Assert
		Result firstlastResult0 = Results.getLastResult();
		assertNull(firstlastResult0);
		
		//Act
		firstFrame.addAttempt(new FrameInfo(frameIndex, attemptIndex, attemptValue));
		
		//Assert
		firstlastResult0 = Results.getLastResult();
		assertEquals((Integer)10, firstlastResult0.getValue());
		assertEquals((Integer)10, firstlastResult0.getAggValue());
		assertTrue(firstlastResult0.isStrike());		
		
		//Act
		Frame secondFrame = frames.getFrameCollection().get(1);
		++frameIndex;
		attemptIndex = 0;
		attemptValue = 5;
		secondFrame.addAttempt(new FrameInfo(frameIndex, attemptIndex, attemptValue));
		
		//Assert
		Result previousResult1 = Results.getPreviousResult(frameIndex);
		assertEquals((Integer)15, previousResult1.getValue());
		assertEquals((Integer)15, previousResult1.getAggValue());
		assertTrue(previousResult1.isStrike());
		//Assert
		Result lastResult1 = Results.getLastResult();
		assertEquals((Integer)5, lastResult1.getValue());
		assertEquals((Integer)20, lastResult1.getAggValue());
		assertTrue(lastResult1.isNormal());
		
		//Act
		secondFrame.addAttempt(new FrameInfo(frameIndex, ++attemptIndex, attemptValue));		

		//Assert
		assertEquals((Integer)20, previousResult1.getValue());
		assertEquals((Integer)20, previousResult1.getAggValue());
		assertTrue(previousResult1.isStrike());
		//Assert
		assertEquals((Integer)10, lastResult1.getValue());
		assertEquals((Integer)30, lastResult1.getAggValue());
		assertTrue(lastResult1.isSpare());
		
		//Act
		Frame thirdFrame = frames.getFrameCollection().get(2);
		++frameIndex;
		attemptIndex = 0;
		attemptValue = 3;
		thirdFrame.addAttempt(new FrameInfo(frameIndex, attemptIndex, attemptValue));
		
		//Assert
		Result prePreviousResult2 = Results.getPrePreviousResult(frameIndex);
		assertEquals((Integer)20, prePreviousResult2.getValue());
		assertEquals((Integer)20, prePreviousResult2.getAggValue());
		//Assert
		Result previousResult2 = Results.getPreviousResult(frameIndex);
		assertEquals((Integer)13, previousResult2.getValue());
		assertEquals((Integer)33, previousResult2.getAggValue());
		//Assert
		Result lastResult2 = Results.getLastResult();
		assertEquals((Integer)3, lastResult2.getValue());
		assertEquals((Integer)36, lastResult2.getAggValue());
		
		//Act
		attemptValue = 1;
		thirdFrame.addAttempt(new FrameInfo(frameIndex, ++attemptIndex, attemptValue));
		
		//Assert
		assertEquals((Integer)20, prePreviousResult2.getValue());
		assertEquals((Integer)20, prePreviousResult2.getAggValue());
		//Assert
		assertEquals((Integer)13, previousResult2.getValue());
		assertEquals((Integer)33, previousResult2.getAggValue());
		//Assert
		assertEquals((Integer)4, lastResult2.getValue());
		assertEquals((Integer)37, lastResult2.getAggValue());
	}
	
	public void testCalculateValuesStrikeSpares() {
		//TODO
	}
	
	public void testCalculateValuesStrikeNormalSpare() {
		//TODO
	}
	
	public void testCalculateValuesStrikeStrikeNormal() {
		//TODO
	}
	
	public void testCalculateValuesStrikeStrikeSpare() {
		//TODO
	}
}
