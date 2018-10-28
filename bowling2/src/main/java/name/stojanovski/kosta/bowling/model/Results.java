package name.stojanovski.kosta.bowling.model;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Object for the storing the frame results (10 on size).
 */ 
public class Results {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Results.class);
	
  /**
   * The array with the resul objects.
   */     
	private  List<Result> resultsCollection = new ArrayList<>();

  /**
   * Returns the results.
   */     
	public List<Result> getResults() {
		return resultsCollection;
	}

  /**
   * Returns the last result from the array.
   */     
	public Result getLastResult() {
		Result Result = null;
		if (!resultsCollection.isEmpty()) {
			try {
				Result = resultsCollection.get(resultsCollection.size() - 1);
			} catch (ArrayIndexOutOfBoundsException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		return Result;
	}

  /**
   * Returns the result one index before the current index from the array.
   */
	public Result getPreviousResult(int currentIndex) {
		return currentIndex < 1 ? null
				: resultsCollection.get(currentIndex - 1);
	}

  /**
   * Returns the result two indexes before the current index from the array.
   */
	public Result getPrePreviousResult(int currentIndex) {
		return currentIndex < 2 ? null
				: resultsCollection.get(currentIndex - 2);
	}

  /**
   * Is the last result from the array an strike.
   */     
	public boolean isLastResultStrike() {
		return BowlingEnum.STRIKE.equals(getLastResult().getBowlingEnum());
	}

  /**
   * Is the last result from the array an spare.
   */
	public boolean isLastResultSpare() {
		return BowlingEnum.SPARE.equals(getLastResult().getBowlingEnum());
	}

  /**
   * Is the last result from the array an normal result.
   */
	public boolean isLastResultNotNormal() {
		return !BowlingEnum.NORMAL
				.equals(getLastResult().getBowlingEnum());
	}

}
