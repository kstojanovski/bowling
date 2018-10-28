package name.stojanovski.kosta.bowling.process;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import name.stojanovski.kosta.bowling.eval.ResultObserver;
import name.stojanovski.kosta.bowling.model.Frame;
import name.stojanovski.kosta.bowling.model.FrameFactory;
import name.stojanovski.kosta.bowling.model.FrameInfo;
import name.stojanovski.kosta.bowling.model.Frames;
import name.stojanovski.kosta.bowling.model.Result;
import name.stojanovski.kosta.bowling.model.Results;

public class TestRecognizeFrameType {

	Frame frameAttempts;
	Results Results = new Results();
	Frames frames;
	
	@Before
	public void init() {
		Results = new Results(); 
		 ResultObserver ResultObserver = new ResultObserver(Results);
		frameAttempts = FrameFactory.build(ResultObserver);
		frames = new Frames(ResultObserver);
	}
	
	/**
	 * 
	 */
	@Test
	public void testRecognizeNormalFrameAttempt() {
		//Arrange
		Integer frameIndex = 0;
		Integer attemptIndex = 0;
		
		//Act
		Integer attemptValue = 5;
		frameAttempts.addAttempt(new FrameInfo(frameIndex, attemptIndex, attemptValue));
		attemptValue = 4;
		frameAttempts.addAttempt(new FrameInfo(frameIndex, ++attemptIndex, attemptValue));
		
		//Assert
		Result lastResult = Results.getLastResult();
		assertEquals((Integer)9, lastResult.getValue());
		assertEquals((Integer)9, lastResult.getAggValue());
		assertFalse(Results.isLastResultNotNormal());
	}
	
	/**
	 * 
	 */
	@Test
	public void testRecognizeSpareFrameAttempt() {
		//Arrange
		Integer frameIndex = 0;
		Integer attemptIndex = 0;
		Integer attemptValue = 5;
		
		//Act
		frameAttempts.addAttempt(new FrameInfo(frameIndex, attemptIndex, attemptValue));
		frameAttempts.addAttempt(new FrameInfo(frameIndex, ++attemptIndex, attemptValue));
		
		//Assert
		Result lastResult = Results.getLastResult();
		assertEquals((Integer)10, lastResult.getValue());
		assertEquals((Integer)10, lastResult.getAggValue());
		assertTrue(Results.isLastResultSpare());
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testRecognizeStrikeFrameAttempt() {
		//Arrange
		Integer frameIndex = 0;
		Integer attemptIndex = 0;
		Integer attemptValue = 10;
		
		//Act
		frameAttempts.addAttempt(new FrameInfo(frameIndex, attemptIndex, attemptValue));
		
		//Assert
		Result lastResult = Results.getLastResult();
		assertEquals((Integer)10, lastResult.getValue());
		assertEquals((Integer)10, lastResult.getAggValue());
		assertTrue(Results.isLastResultStrike());
	}
}
