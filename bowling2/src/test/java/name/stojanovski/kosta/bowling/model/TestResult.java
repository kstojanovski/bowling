package name.stojanovski.kosta.bowling.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import name.stojanovski.kosta.bowling.model.FrameInfo;
import name.stojanovski.kosta.bowling.model.Result;

public class TestResult {

	@Test
	public void testCreateResultNormal() {
		int firstAttemptValue = 5;
		Result result = (new Result(new FrameInfo(0, 0, firstAttemptValue)));
		assertEquals((Integer)firstAttemptValue, result.getValue());
		assertEquals((Integer)firstAttemptValue, result.getAggValue());
		assertTrue(result.isNormal());
		
		int secondAttemptValue = 4;
		result.addition(secondAttemptValue);
		assertEquals((Integer)(firstAttemptValue + secondAttemptValue), result.getValue());
		assertEquals((Integer)(firstAttemptValue + secondAttemptValue), result.getAggValue());
		assertTrue(result.isNormal());
	}
	
	@Test
	public void testCreateResultSpare() {
		int firstAttemptValue = 5;
		Result result = (new Result(new FrameInfo(0, 0, firstAttemptValue)));
		assertEquals((Integer)firstAttemptValue, result.getValue());
		assertEquals((Integer)firstAttemptValue, result.getAggValue());
		assertTrue(result.isNormal());
		
		int secondAttemptValue = 5;
		result.addition(secondAttemptValue);
		assertEquals((Integer)(firstAttemptValue + secondAttemptValue), result.getValue());
		assertEquals((Integer)(firstAttemptValue + secondAttemptValue), result.getAggValue());
		assertTrue(result.isSpare());
	}
	
	@Test
	public void testCreateResultStrike() {
		int firstAttemptValue = 10;
		Result result = (new Result(new FrameInfo(0, 0, firstAttemptValue)));
		assertEquals((Integer)firstAttemptValue, result.getValue());
		assertEquals((Integer)firstAttemptValue, result.getAggValue());
		assertTrue(result.isStrike());		
	}
	
	@Test
	public void testCreateResultLastStrikeWithTwoZeros() {
		int firstAttemptValue = 10;
		Result result = (new Result(new FrameInfo(0, 0, firstAttemptValue)));
		assertEquals((Integer)firstAttemptValue, result.getValue());
		assertEquals((Integer)firstAttemptValue, result.getAggValue());
		assertTrue(result.isStrike());		
		
		int secondAttemptValue = 0;
		result.addition(secondAttemptValue);
		assertEquals((Integer)firstAttemptValue, result.getValue());
		assertEquals((Integer)firstAttemptValue, result.getAggValue());
		assertTrue(result.isStrike());
		
		secondAttemptValue = 0;
		result.addition(secondAttemptValue);
		assertEquals((Integer)firstAttemptValue, result.getValue());
		assertEquals((Integer)firstAttemptValue, result.getAggValue());
		assertTrue(result.isStrike());
	}
	
}
