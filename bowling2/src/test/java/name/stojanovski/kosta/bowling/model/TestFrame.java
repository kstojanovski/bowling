package name.stojanovski.kosta.bowling.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import name.stojanovski.kosta.bowling.eval.ResultObserver;
import name.stojanovski.kosta.bowling.model.Frame;
import name.stojanovski.kosta.bowling.model.FrameInfo;
import name.stojanovski.kosta.bowling.model.Results;

public class TestFrame {

	private  Results Results = new Results();
	private  ResultObserver ResultObserver = new ResultObserver(
			Results);
	
	@Test
	public void testFrameStrike() {
		//Arrange
		Frame frame = new Frame(ResultObserver);
		
		//Act
		FrameInfo frameInfo = new FrameInfo(0, 0, 10);
		frame.addAttempt(frameInfo);

		//Assert
		assertEquals(1, frame.getAttempts().size());
		assertEquals((Integer)10, frame.getAttempts().get(0));
			
		assertEquals(1, Results.getResults().size());
	}

	@Test
	public void testFrameSpare() {
		//Arrange
		Frame frame = new Frame(ResultObserver);

		//Act
		int attemptIndex = 0;
		FrameInfo frameInfo = new FrameInfo(0, attemptIndex, 5);
		frame.addAttempt(frameInfo);
		frameInfo = new FrameInfo(0, ++attemptIndex, 5);
		frame.addAttempt(frameInfo);

		//Assert
		assertEquals(2, frame.getAttempts().size());
		assertEquals((Integer)5, frame.getAttempts().get(0));
		assertEquals((Integer)5, frame.getAttempts().get(1));
		
		assertEquals(1, Results.getResults().size());	
	}
	
	@Test
	public void testFrameNormal() {
		//Arrange
		Frame frame = new Frame(ResultObserver);

		//Act
		int attemptIndex = 0;
		FrameInfo frameInfo = new FrameInfo(0, attemptIndex, 5);
		frame.addAttempt(frameInfo);
		frameInfo = new FrameInfo(0, ++attemptIndex, 4);
		frame.addAttempt(frameInfo);
		
		//Assert
		assertEquals(2, frame.getAttempts().size());
		assertEquals((Integer)5, frame.getAttempts().get(0));
		assertEquals((Integer)4, frame.getAttempts().get(1));
		
		assertEquals(1, Results.getResults().size());		
	}
}
