package name.stojanovski.kosta.bowling.eval;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import name.stojanovski.kosta.bowling.model.FrameInfo;
import name.stojanovski.kosta.bowling.model.Result;
import name.stojanovski.kosta.bowling.model.ResultFactory;
import name.stojanovski.kosta.bowling.model.Results;

/**
 * This class is an observer which observes the changes of an frame on adding new attempt. 
 */
public class ResultObserver implements Observer {

	private Results results;

	public ResultObserver(Results results) {
		this.results = results;
	}

	/**
	 * Method which invokes when on frame object new attempt is added.
	 */
	@Override
	public void update(Observable observable, Object argumentsObject) {
		createModifyResult((FrameInfo) argumentsObject);
	}

	/**
	 * If it is new attempt (index=0) and result object is created.
	 * Otherwise the result object is modified.
	 * Invocation of recalculate previous values if spare or strike is done at the end,
	 *  
	 * @param frameInfo
	 */
	protected void createModifyResult(FrameInfo frameInfo) {
		List<Result> resultCollection = results
				.getResults();
		if (frameInfo.getAttemptIndex() == 0) {
			createResult(frameInfo, resultCollection);
		} else {
			modifyResult(frameInfo, resultCollection);
		}
		recalculatePrevious(frameInfo);
	}

	/**
	 * Addition of the attempt value of the previous frames if they were spare
	 * or strike.
	 * 
	 * @param frameInfo
	 */
	protected void recalculatePrevious(FrameInfo frameInfo) {
		RulesStrategyFactory.createStrategy(frameInfo.getAttemptIndex())
			.recalculatePrevouses(frameInfo, results);
	}

	/**
	 * Modification of the result object.
	 * Is invoked when the attempt index is not 0.
	 * 
	 * @param frameInfo
	 * @param resultCollection
	 */
	protected void modifyResult(FrameInfo frameInfo,
			List<Result> resultCollection) {
		Result result = resultCollection
				.get(frameInfo.getFrameIndex());
		result.addition(frameInfo.getAttemptValue());
	}

	/**
	 * Creation of the result object.
	 * Is invoked when the attempt index is 0. 
	 * 
	 * @param frameInfo
	 * @param resultCollection
	 */
	protected void createResult(FrameInfo frameInfo,
			List<Result> resultCollection) {
		Result prevResult = results
				.getPreviousResult(frameInfo.getFrameIndex());
		resultCollection.add(new ResultFactory()
				.createResult(frameInfo, prevResult));
	}

}
