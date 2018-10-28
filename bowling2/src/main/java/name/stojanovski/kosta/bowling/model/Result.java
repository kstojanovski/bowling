package name.stojanovski.kosta.bowling.model;

import java.util.Observable;
import java.util.Observer;

/**
 * Result object for frame results.
 */ 
public class Result extends Observable implements Observer {

	@SuppressWarnings("unused")
	//for debugging purposes
	private int index;
	private static int globalIndex;

	private Integer value;
	private int aggValue;

	private BowlingEnum bowlingEnum;

  /**
   * Constructor with an observer and frame info object.
   * The observer is needed for the addition of values when previous values are 
   * calculated.   
   */     
	public Result(Observable observable, FrameInfo frameInfo) {
		observable.addObserver(this);
		this.aggValue = ((Result) observable).getAggValue();
		init(frameInfo);
	}

  /**
   * Constructor for the first frame which doesn't have previous Frame element.
   */     
	public Result(FrameInfo frameInfo) {
		init(frameInfo);
	}

  /**
   * Evaluation of the frame info object by adding the values to this result.
   */
	protected void init(FrameInfo frameInfo) {
		index = globalIndex++;
		this.bowlingEnum = BowlingEnum.NORMAL;
		if (frameInfo.getAttemptIndex() == 0) {
			this.value = frameInfo.getAttemptValue();
			this.aggValue += value;
			if (frameInfo.getAttemptValue() == 10) {
				this.bowlingEnum = BowlingEnum.STRIKE;
			}
		}
	}

  /**
   * Returns the value of the this result
   */     
	public Integer getValue() {
		return value;
	}

  /**
   * Returns the result type of this result.
   */     
	public BowlingEnum getBowlingEnum() {
		return bowlingEnum;
	}

  /**
   * Adds value to the previous object.
   * Also invokes observer which is the next bound frame (update method).
   */     
	public void previousAddition(Integer value) {
		this.value += value;
		this.aggValue += value;
		setChanged();
		notifyObservers(value);
	}

  /**
   * Adds values to the current object.
   */     
	public void addition(Integer value) {
		if (value != 0 && (this.value + value) == 10) {
			bowlingEnum = BowlingEnum.SPARE;
		}
		this.value += value;
		this.aggValue += value;
	}

  /**
   * Returns the aggregate value.
   */     
	public Integer getAggValue() {
		return aggValue;
	}

  /**
   * Invokes this content for addition of the values to the next bound frame.
   * Is invoked twice on pre-previous which is need in case of strike.   
   */     
	@Override
  public void update(Observable observable, Object argumentsObject) {
		Integer previousAttemptValue = (Integer) argumentsObject;
		this.aggValue += previousAttemptValue;
		setChanged();
		notifyObservers(previousAttemptValue);
	}
	
  /**
   * Returns true if this frame result is spare.
   */     
	public boolean isSpare() {
		return BowlingEnum.SPARE.equals(bowlingEnum);
	}

  /**
   * Returns true if this frame result is strike.
   */	
	public boolean isStrike() {
		return BowlingEnum.STRIKE.equals(bowlingEnum);
	}

  /**
   * Returns true if this frame result is normal.
   */	
	public boolean isNormal() {
		return BowlingEnum.NORMAL.equals(bowlingEnum);
	}

  /**
   * Returns true if this frame result is not normal.
   */	
	public boolean isNotNormal() {
		return !BowlingEnum.NORMAL.equals(bowlingEnum);
	}
}
