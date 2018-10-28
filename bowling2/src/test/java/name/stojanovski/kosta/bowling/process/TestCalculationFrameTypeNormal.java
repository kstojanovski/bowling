package name.stojanovski.kosta.bowling.process;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import name.stojanovski.kosta.bowling.eval.ResultObserver;
import name.stojanovski.kosta.bowling.model.Frame;
import name.stojanovski.kosta.bowling.model.FrameInfo;
import name.stojanovski.kosta.bowling.model.Frames;
import name.stojanovski.kosta.bowling.model.Result;
import name.stojanovski.kosta.bowling.model.Results;

public class TestCalculationFrameTypeNormal {
	Results Results = new Results();
	Frames frames;
	
	@Before
	public void init() {
		Results = new Results(); 
		 ResultObserver ResultObserver = new ResultObserver(Results);
		frames = new Frames(ResultObserver);
	}
	
	/**
	 * 
	 */
	@Test
	public void testCalculateValuesNormal() {
		//Arrange
		Integer frameIndex = 0;
		Integer attemptIndex = 0;
		
		//Act
		Integer attemptValue = 5;
		Frame firstFrame = frames.getFrameCollection().get(0);
		firstFrame.addAttempt(new FrameInfo(frameIndex, attemptIndex, attemptValue));
		attemptValue = 4;
		firstFrame.addAttempt(new FrameInfo(frameIndex, ++attemptIndex, attemptValue));
		
		//Assert
		Result lastResult = Results.getLastResult();
		assertEquals((Integer)9, lastResult.getValue());
		assertEquals((Integer)9, lastResult.getAggValue());
		assertFalse(Results.isLastResultNotNormal());
	}


}
