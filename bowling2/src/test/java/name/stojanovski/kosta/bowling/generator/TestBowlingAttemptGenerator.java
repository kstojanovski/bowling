package name.stojanovski.kosta.bowling.generator;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import name.stojanovski.kosta.bowling.generator.BowingAttemptsGenerator;

@RunWith(MockitoJUnitRunner.class)
public class TestBowlingAttemptGenerator {
	
	@Spy
	private BowingAttemptsGenerator bowingAttemptsGenerator = new BowingAttemptsGenerator();
	
	@Test
	public void testInit() {
		int attemptVaue = bowingAttemptsGenerator.generateAttemptValue(0, 0, 0);
		assertTrue(attemptVaue > -1 && attemptVaue < 11);
		verify(bowingAttemptsGenerator, times(1)).createFirst();
	}
	
	@Test
	public void testStrike() {
		int attemptVaue = bowingAttemptsGenerator.generateAttemptValue(0, 0, 10);
		assertTrue(attemptVaue > -1 && attemptVaue < 11);
		verify(bowingAttemptsGenerator, times(1)).createFirst();
	}
	
	@Test
	public void testValue() {
		int firstBowl = 7;
		int attemptVaue = bowingAttemptsGenerator.generateAttemptValue(0, 0, firstBowl);
		assertTrue(attemptVaue > -1 && attemptVaue < 4);
		verify(bowingAttemptsGenerator, times(1)).createSecond(firstBowl);
	}
}
