package name.stojanovski.kosta.bowling.model;

/**
 * Factory for creating Result objects.
 **/ 
public class ResultFactory {

  /**
   * create result method which invoked different create methods depend on the 
   * previous result. If the result is for the first frame the previous result
   * object is null.      
   */     
	public Result createResult(FrameInfo frameInfo,
			Result prevResult) {
		return prevResult == null ? createFirstResult(frameInfo)
				: createFurtherResult(frameInfo, prevResult);
	}

  /**
   * Is invoked from the second to the last result.
   */     
	protected Result createFurtherResult(FrameInfo frameInfo,
			Result prevResult) {
		return new Result(prevResult, frameInfo);
	}

  /**
   * Is invoked for the first result.
   */     
	protected Result createFirstResult(FrameInfo frameInfo) {
		return new Result(frameInfo);
	}
}
