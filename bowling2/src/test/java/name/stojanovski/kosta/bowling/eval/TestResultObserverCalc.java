package name.stojanovski.kosta.bowling.eval;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import name.stojanovski.kosta.bowling.eval.ResultObserver;
import name.stojanovski.kosta.bowling.model.FrameInfo;
import name.stojanovski.kosta.bowling.model.Result;
import name.stojanovski.kosta.bowling.model.Results;

public class TestResultObserverCalc {

	private Results Results = new Results();
	private ResultObserver ResultObserver = new ResultObserver(Results);

	@Test
	public void testThreeSpares() {
		//Arrange
		Integer frameIndex = 0;
		Integer attemptIndex = 0;
		Integer attemptValue = 5;
		
		//Act
		ResultObserver.createModifyResult(new FrameInfo(frameIndex, attemptIndex, attemptValue));
		Result firstResult = Results.getResults().get(0);
		assertTrue(firstResult.isNormal());
		assertEquals((Integer)5, firstResult.getValue());
		assertEquals((Integer)5, firstResult.getAggValue());
		ResultObserver.createModifyResult(new FrameInfo(frameIndex, ++attemptIndex, attemptValue));
		assertTrue(firstResult.isSpare());
		assertEquals((Integer)10, firstResult.getValue());
		assertEquals((Integer)10, firstResult.getAggValue());
		
		attemptIndex = 0;
		ResultObserver.createModifyResult(new FrameInfo(++frameIndex, attemptIndex, attemptValue));
		assertEquals((Integer)15, firstResult.getValue());
		assertEquals((Integer)15, firstResult.getAggValue());
		Result secondResult = Results.getResults().get(1);
		assertTrue(secondResult.isNormal());
		assertEquals((Integer)5, secondResult.getValue());
		assertEquals((Integer)20, secondResult.getAggValue());
		ResultObserver.createModifyResult(new FrameInfo(frameIndex, ++attemptIndex, attemptValue));
		assertTrue(secondResult.isSpare());
		assertEquals((Integer)15, firstResult.getValue());
		assertEquals((Integer)15, firstResult.getAggValue());
		assertEquals((Integer)10, secondResult.getValue());
		assertEquals((Integer)25, secondResult.getAggValue());
		
		attemptIndex = 0;
		ResultObserver.createModifyResult(new FrameInfo(++frameIndex, attemptIndex, attemptValue));
		assertEquals((Integer)15, firstResult.getValue());
		assertEquals((Integer)15, firstResult.getAggValue());
		assertEquals((Integer)15, secondResult.getValue());
		assertEquals((Integer)30, secondResult.getAggValue());
		Result thirdResult = Results.getResults().get(2);
		assertTrue(thirdResult.isNormal());
		assertEquals((Integer)5, thirdResult.getValue());
		assertEquals((Integer)35, thirdResult.getAggValue());
		ResultObserver.createModifyResult(new FrameInfo(frameIndex, ++attemptIndex, attemptValue));
		assertTrue(thirdResult.isSpare());
		assertEquals((Integer)15, firstResult.getValue());
		assertEquals((Integer)15, firstResult.getAggValue());
		assertEquals((Integer)15, secondResult.getValue());
		assertEquals((Integer)30, secondResult.getAggValue());
		assertEquals((Integer)10, thirdResult.getValue());
		assertEquals((Integer)40, thirdResult.getAggValue());
	}
	
	@Test
	public void testStrikes() {
		//Arrange
		Integer frameIndex = 0;
		Integer attemptIndex = 0;
		Integer attemptValue = 10;
		
		//Act
		ResultObserver.createModifyResult(new FrameInfo(frameIndex, attemptIndex, attemptValue));
		Result firstResult = Results.getResults().get(0);
		assertTrue(firstResult.isStrike());
		assertEquals((Integer)10, firstResult.getValue());
		assertEquals((Integer)10, firstResult.getAggValue());		
				
		attemptIndex = 0;
		ResultObserver.createModifyResult(new FrameInfo(++frameIndex, attemptIndex, attemptValue));
		Result secondResult = Results.getResults().get(1);
		assertTrue(secondResult.isStrike());
		assertEquals((Integer)20, firstResult.getValue());
		assertEquals((Integer)20, firstResult.getAggValue());
		assertEquals((Integer)10, secondResult.getValue());
		assertEquals((Integer)30, secondResult.getAggValue());
		
		attemptIndex = 0;
		ResultObserver.createModifyResult(new FrameInfo(++frameIndex, attemptIndex, attemptValue));
		Result thirdResult = Results.getResults().get(2);
		assertTrue(thirdResult.isStrike());
		assertEquals((Integer)30, firstResult.getValue());
		assertEquals((Integer)30, firstResult.getAggValue());
		assertEquals((Integer)20, secondResult.getValue());
		assertEquals((Integer)50, secondResult.getAggValue());
		assertEquals((Integer)10, thirdResult.getValue());
		assertEquals((Integer)60, thirdResult.getAggValue());		
	}

}
