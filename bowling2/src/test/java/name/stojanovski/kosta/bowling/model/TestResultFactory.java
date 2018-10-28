package name.stojanovski.kosta.bowling.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import name.stojanovski.kosta.bowling.model.FrameInfo;
import name.stojanovski.kosta.bowling.model.Result;
import name.stojanovski.kosta.bowling.model.ResultFactory;

@RunWith(MockitoJUnitRunner.class)
public class TestResultFactory {

	@Spy
	ResultFactory ResultFactory = new ResultFactory();
	
	@Test
	public void testCreateFirstResult() {
		//Arrange
		Integer firstAttemptValue = 5;
		FrameInfo frameInfo = new FrameInfo(0, 0, firstAttemptValue);
		
		//Act
		Result Result = ResultFactory.createResult(frameInfo, null);
		
		//Assert
		verify(ResultFactory, times(1)).createFirstResult(frameInfo);
		assertEquals(firstAttemptValue, Result.getValue());
		assertEquals(firstAttemptValue, Result.getAggValue());
		assertTrue(Result.isNormal());
	}
	
	@Test
	public void testCreateResult() {
		//Arrange
		Integer firstAttemptValue = 10;
		FrameInfo frameInfo = new FrameInfo(0, 0, firstAttemptValue);

		//Act
		Result firstResult = ResultFactory.createResult(frameInfo, null);
		
		//Assert
		verify(ResultFactory, times(1)).createFirstResult(frameInfo);
		assertEquals(firstAttemptValue, firstResult.getValue());
		assertEquals(firstAttemptValue, firstResult.getAggValue());
		assertTrue(firstResult.isStrike());

		//Act
		Result Result = ResultFactory.createResult(frameInfo, firstResult);
		
		//Assert
		verify(ResultFactory, times(1)).createFurtherResult(frameInfo, firstResult);
		assertEquals(firstAttemptValue, firstResult.getValue());
		assertEquals(firstAttemptValue, firstResult.getAggValue());
		assertEquals(firstAttemptValue, Result.getValue());
		assertEquals((Integer)(firstAttemptValue + firstAttemptValue), Result.getAggValue());
		assertTrue(Result.isStrike());
		
		//Act
		firstResult.previousAddition(firstAttemptValue);
		
		//Assert		
		assertEquals((Integer)(firstAttemptValue + firstAttemptValue), firstResult.getValue());
		assertEquals((Integer)(firstAttemptValue + firstAttemptValue), firstResult.getAggValue());
		assertEquals(firstAttemptValue, Result.getValue());
		assertEquals((Integer)(firstAttemptValue + firstAttemptValue + firstAttemptValue), Result.getAggValue());
		assertTrue(Result.isStrike());
	}
}
