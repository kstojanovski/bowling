package name.stojanovski.kosta.bowling.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import name.stojanovski.kosta.bowling.model.FrameInfo;
import name.stojanovski.kosta.bowling.model.Result;
import name.stojanovski.kosta.bowling.model.Results;

public class TestResults {

	@Test
	public void testResults() {
		int currentIndex = 0;

		Results Results = new Results();
		Result firstResult = new Result(
				new FrameInfo(currentIndex, 0, 10));
		Results.getResults().add(firstResult);

		int secondAttempt = 5;
		Result secondResult = new Result(firstResult,
				new FrameInfo(++currentIndex, 0, secondAttempt));
		firstResult.previousAddition(secondAttempt);
		secondResult.addition(secondAttempt);
		firstResult.previousAddition(secondAttempt);
		Results.getResults().add(secondResult);

		int thirdAttempt = 5;
		Result thirdResult = new Result(secondResult,
				new FrameInfo(++currentIndex, 0, thirdAttempt));
		secondResult.previousAddition(secondAttempt);
		thirdResult.addition(4);
		Results.getResults().add(thirdResult);

		Result prePreviousResult = Results
				.getPrePreviousResult(currentIndex);
		assertTrue(prePreviousResult.isStrike());
		assertEquals((Integer) 20, prePreviousResult.getValue());
		assertEquals((Integer) 20, prePreviousResult.getAggValue());
		Result previousResult = Results
				.getPreviousResult(currentIndex);
		assertEquals((Integer) 15, previousResult.getValue());
		assertEquals((Integer) 35, previousResult.getAggValue());
		assertTrue(previousResult.isSpare());
		Result lastResult = Results.getLastResult();
		assertTrue(lastResult.isNormal());
		assertEquals((Integer) 9, lastResult.getValue());
		assertEquals((Integer) 44, lastResult.getAggValue());

		for (Result Result : Results.getResults()) {
			int indexOf = Results.getResults().indexOf(Result);
			switch (indexOf) {
			case 0:
				assertTrue(Result.isStrike());
				assertEquals((Integer) 20, Result.getValue());
				assertEquals((Integer) 20, Result.getAggValue());
				break;
			case 1:
				assertEquals((Integer) 15, Result.getValue());
				assertEquals((Integer) 35, Result.getAggValue());
				assertTrue(Result.isSpare());
				break;
			case 2:
				assertTrue(Result.isNormal());
				assertEquals((Integer) 9, Result.getValue());
				assertEquals((Integer) 44, Result.getAggValue());
				break;
			}
		}
		
		assertFalse(Results.isLastResultNotNormal());
		assertFalse(Results.isLastResultSpare());
		assertFalse(Results.isLastResultStrike());		
	}
}
