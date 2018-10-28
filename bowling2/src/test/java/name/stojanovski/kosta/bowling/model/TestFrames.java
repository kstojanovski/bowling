package name.stojanovski.kosta.bowling.model;

import static org.junit.Assert.assertEquals;

import name.stojanovski.kosta.bowling.eval.ResultObserver;
import name.stojanovski.kosta.bowling.model.Frames;
import name.stojanovski.kosta.bowling.model.Results;

public class TestFrames {

	private  Results Results = new Results();
	private  ResultObserver ResultObserver = new ResultObserver(
			Results);
	
	public void testTrames() {
		Frames frames = new Frames(ResultObserver);
		assertEquals(10, frames.getFrameCollection().size());		
	}
}
