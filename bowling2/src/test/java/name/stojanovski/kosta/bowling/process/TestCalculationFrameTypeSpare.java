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

public class TestCalculationFrameTypeSpare {
	Results Results = new Results();
	Frames frames;
	
	@Before
	public void init() {
		Results = new Results(); 
		 ResultObserver ResultObserver = new ResultObserver(Results);
		frames = new Frames(ResultObserver);
	}
	
	/**
	 * Calculating the spare value followed by normal frame
	 */
	@Test
	public void testCalculateValuesSpareFollowedByNormal() {
		//Arrange
		Integer frameIndex = 0;
		Integer attemptIndex = 0;
		Integer attemptValue = 5;
				
		//Act
		Frame firstFrame = frames.getFrameCollection().get(0);
		
		//Assert
		Result firstlastResult = Results.getLastResult();
		assertNull(firstlastResult);

		//Act
		firstFrame.addAttempt(new FrameInfo(frameIndex, attemptIndex, attemptValue));
		
		//Assert
		firstlastResult = Results.getLastResult();
		assertEquals((Integer)5, firstlastResult.getValue());
		assertEquals((Integer)5, firstlastResult.getAggValue());
		assertTrue(firstlastResult.isNormal());
		
		//Act
		firstFrame.addAttempt(new FrameInfo(frameIndex, ++attemptIndex, attemptValue));

		//Assert
		assertEquals((Integer)10, firstlastResult.getValue());
		assertEquals((Integer)10, firstlastResult.getAggValue());
		assertTrue(firstlastResult.isSpare());

		//Act
		Frame secondFrame = frames.getFrameCollection().get(1);
		++frameIndex;
		attemptIndex = 0;
		secondFrame.addAttempt(new FrameInfo(frameIndex, attemptIndex, attemptValue));
		
		//Assert
		Result previousResult = Results.getPreviousResult(frameIndex);
		assertEquals((Integer)15, previousResult.getValue());
		assertEquals((Integer)15, previousResult.getAggValue());
		assertTrue(previousResult.isSpare());
		//Assert
		Result lastResult = Results.getLastResult();
		assertEquals((Integer)5, lastResult.getValue());
		assertEquals((Integer)20, lastResult.getAggValue());
		assertTrue(lastResult.isNormal());
		
		//Act
		attemptValue = 3;
		secondFrame.addAttempt(new FrameInfo(frameIndex, ++attemptIndex, attemptValue));

		//Assert
		assertEquals((Integer)8, lastResult.getValue());
		assertEquals((Integer)23, lastResult.getAggValue());
		assertTrue(lastResult.isNormal());
		
		assertEquals(2, firstFrame.getAttempts().size());
		assertEquals(2, secondFrame.getAttempts().size());
	}

	/**
	 * Calculating the spare value on consecutive spares
	 */
	@Test
	public void testCalculateValuesSpareFollowedBySpare() {
		//Arrange
		Integer frameIndex = 0;
		Integer attemptIndex = 0;
		Integer attemptValue = 5;
				
		//Act
		Frame firstFrame = frames.getFrameCollection().get(0);
		
		//Assert
		Result firstlastResult = Results.getLastResult();
		assertNull(firstlastResult);
	
		//Act
		firstFrame.addAttempt(new FrameInfo(frameIndex, attemptIndex, attemptValue));
		
		//Assert
		firstlastResult = Results.getLastResult();
		assertEquals((Integer)5, firstlastResult.getValue());
		assertEquals((Integer)5, firstlastResult.getAggValue());
		assertTrue(firstlastResult.isNormal());
		
		//Act
		firstFrame.addAttempt(new FrameInfo(frameIndex, ++attemptIndex, attemptValue));
	
		//Assert
		assertEquals((Integer)10, firstlastResult.getValue());
		assertEquals((Integer)10, firstlastResult.getAggValue());
		assertTrue(firstlastResult.isSpare());
	
		//Act
		Frame secondFrame = frames.getFrameCollection().get(1);
		++frameIndex;
		attemptIndex = 0;
		secondFrame.addAttempt(new FrameInfo(frameIndex, attemptIndex, attemptValue));
		
		//Assert
		Result previousResult = Results.getPreviousResult(frameIndex);
		assertEquals((Integer)15, previousResult.getValue());
		assertEquals((Integer)15, previousResult.getAggValue());
		assertTrue(previousResult.isSpare());
		//Assert
		Result lastResult = Results.getLastResult();
		assertEquals((Integer)5, lastResult.getValue());
		assertEquals((Integer)20, lastResult.getAggValue());
		assertTrue(lastResult.isNormal());
		
		//Act
		secondFrame.addAttempt(new FrameInfo(frameIndex, ++attemptIndex, attemptValue));
	
		//Assert
		assertEquals((Integer)10, lastResult.getValue());
		assertEquals((Integer)25, lastResult.getAggValue());
		assertTrue(lastResult.isSpare());
	}


	/**
	 * Calculating the spare value on following strike
	 */
	@Test
	public void testCalculateValuesSpareFollowedByStrike() {
		//Arrange
		Integer frameIndex = 0;
		Integer attemptIndex = 0;
		Integer attemptValue = 5;
				
		//Act
		Frame firstFrame = frames.getFrameCollection().get(0);
		
		//Assert
		Result firstlastResult = Results.getLastResult();
		assertNull(firstlastResult);

		//Act
		firstFrame.addAttempt(new FrameInfo(frameIndex, attemptIndex, attemptValue));
		
		//Assert
		firstlastResult = Results.getLastResult();
		assertEquals((Integer)5, firstlastResult.getValue());
		assertEquals((Integer)5, firstlastResult.getAggValue());
		assertTrue(firstlastResult.isNormal());
		
		//Act
		firstFrame.addAttempt(new FrameInfo(frameIndex, ++attemptIndex, attemptValue));

		//Assert
		assertEquals((Integer)10, firstlastResult.getValue());
		assertEquals((Integer)10, firstlastResult.getAggValue());
		assertTrue(firstlastResult.isSpare());

		//Act
		Frame secondFrame = frames.getFrameCollection().get(1);
		++frameIndex;
		attemptIndex = 0;
		attemptValue = 10;
		secondFrame.addAttempt(new FrameInfo(frameIndex, attemptIndex, attemptValue));
		
		//Assert
		Result previousResult = Results.getPreviousResult(frameIndex);
		assertEquals((Integer)20, previousResult.getValue());
		assertEquals((Integer)20, previousResult.getAggValue());
		assertTrue(previousResult.isSpare());
		//Assert
		Result lastResult = Results.getLastResult();
		assertEquals((Integer)10, lastResult.getValue());
		assertEquals((Integer)30, lastResult.getAggValue());
		assertTrue(lastResult.isStrike());
	}
	
	
}
