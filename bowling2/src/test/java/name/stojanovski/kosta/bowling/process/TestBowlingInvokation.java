package name.stojanovski.kosta.bowling.process;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import name.stojanovski.kosta.bowling.process.Bowling;

@RunWith(MockitoJUnitRunner.class)
public class TestBowlingInvokation {

	@Spy
	Bowling bowling = new Bowling();
	
	@Test
	public void testBowlingMainProcess() {
		bowling.startTheGame();
		verify(bowling, times(1)).iterateTroughFrames();
		verify(bowling, times(10)).iterateTroughAttempts(Mockito.anyObject(), Mockito.anyInt(), Mockito.anyBoolean());
		verify(bowling, times(10)).processFrame(Mockito.anyObject(), Mockito.anyInt());
		verify(bowling, times(1)).extraProcessFrame(Mockito.anyObject(), Mockito.anyInt());
	}
}
