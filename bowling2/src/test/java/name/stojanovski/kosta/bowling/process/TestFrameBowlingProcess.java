package name.stojanovski.kosta.bowling.process;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import name.stojanovski.kosta.bowling.eval.ResultObserver;
import name.stojanovski.kosta.bowling.generator.BowingAttemptsGenerator;
import name.stojanovski.kosta.bowling.model.Frame;
import name.stojanovski.kosta.bowling.model.Frames;
import name.stojanovski.kosta.bowling.model.Results;
import name.stojanovski.kosta.bowling.process.FrameBowlingProcess;

@RunWith(MockitoJUnitRunner.class)
public class TestFrameBowlingProcess {

	private static  Logger LOGGER = LoggerFactory.getLogger(TestFrameBowlingProcess.class);
	
	Results Results = new Results();
	ResultObserver ResultObserver = new ResultObserver(Results);
	Frames frames = new Frames(ResultObserver);
	
	@Spy
	FrameBowlingProcess frameBowlingProcess = new FrameBowlingProcess(Results, new BowingAttemptsGenerator());
	
	@Test
	public void testFrameBowlingProcess() {
		//Arrange
		Frame frame = frames.getFrameCollection().get(0);
		
		//Act
		frameBowlingProcess.casualFramesEvaluation(frame, 0);
		
		//Assert
		verify(frameBowlingProcess, times(1)).casualFramesEvaluation(Mockito.anyObject(), Mockito.anyInt());
		int expected = 0;
		if (Results.isLastResultStrike()) {
			LOGGER.debug("strike");
			expected = 1;
		} else {
			LOGGER.debug("not strike");
			expected = 2;
		}
		assertEquals(expected, frame.getAttempts().size());
	}
}
