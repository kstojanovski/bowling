package name.stojanovski.kosta.bowling.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import name.stojanovski.kosta.bowling.eval.ResultObserver;
import name.stojanovski.kosta.bowling.generator.BowingAttemptsGenerator;
import name.stojanovski.kosta.bowling.generator.IBowingAttemptsGenerator;
import name.stojanovski.kosta.bowling.model.Frame;
import name.stojanovski.kosta.bowling.model.Frames;
import name.stojanovski.kosta.bowling.model.Result;
import name.stojanovski.kosta.bowling.model.Results;

/**
 * Main Process class where whole game is executed expect the output.
 */ 
public class Bowling {

	private static  Logger LOGGER = LoggerFactory.getLogger(Bowling.class);
	
  /**
   * Results objects for the frame results 
   */     
	private  Results results = new Results();
  /**
   * Observer class which triggers when in an frame an attempt is added.
   */     
	private  ResultObserver resultObserver = new ResultObserver(
			results);
  /**
   * Creating the frames.
   */     
	private  Frames frames = new Frames(resultObserver);
  /**
   * Defining the process objects
   */     
	private FrameBowlingProcess frameBowlingProcess;
	private LastFrameBowlingProcess lastFrameBowlingProcess;

  /**
   * Initializing the Bowling with the default bowling generator.
   */
	public Bowling() {
		this(new BowingAttemptsGenerator());
	}
	
  /**
   * Initializing the Bowling with the custom bowling generator.
   */     
	public Bowling(IBowingAttemptsGenerator bowingAttemptsGenerator) {
		this.frameBowlingProcess = new FrameBowlingProcess(
				results, bowingAttemptsGenerator);
		this.lastFrameBowlingProcess = new LastFrameBowlingProcess(
				results, bowingAttemptsGenerator);		
	}
	
  /**
   * method which starts the game.
   */     
	public void startTheGame() {
		iterateTroughFrames();
	}

  /**
   * Iterating through all 10 frames.
   */     
	public void iterateTroughFrames() {
		int frameNumber = frames.getFrameCollection().size();
		for (Frame frame : frames.getFrameCollection()) {
			int index = frames.getFrameCollection().indexOf(frame);
			iterateTroughAttempts(frame, index, index == frameNumber - 1);
		}
	}

  /**
   * Iterating through the attempts.
   */     
	protected void iterateTroughAttempts(Frame frame, int frameIndex,
			boolean lastframe) {
		processFrame(frame, frameIndex);
		if (lastframe) {
			extraProcessFrame(frame, frameIndex);
		}
	}

  /**
   * Method for printing the results and frames.
   */     
	public void print() {
		for (Frame frame : frames.getFrameCollection()) {
			LOGGER.debug("{}", frame.getAttempts());
			int index = frames.getFrameCollection()
					.indexOf(frame);
			Result result = results
					.getResults().get(index);
			LOGGER.debug("{}", result.getBowlingEnum());
			LOGGER.debug("     +  {}", result.getValue());
			LOGGER.debug("        {}", result.getAggValue());
		}
	}
	
  /**
   * Invocation of the extra process for the last frames.
   */     
	protected void extraProcessFrame(Frame frame, int frameIndex) {
		lastFrameBowlingProcess.extraFramesEvaluation(frame, frameIndex);
	}

  /**
   * Invocation of the method for processing frames. 
   */
	protected void processFrame(Frame frame, int frameIndex) {
		frameBowlingProcess.casualFramesEvaluation(frame, frameIndex);
	}

  /**
   * Get the all result.
   */
	public Results getResults() {
		return results;
	}

  /**
   * Get the all frames.
   */
	public Frames getFrames() {
		return frames;
	}
}
