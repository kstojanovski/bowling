package name.stojanovski.kosta.bowling.eval;

import name.stojanovski.kosta.bowling.model.FrameInfo;
import name.stojanovski.kosta.bowling.model.Results;

/**
 * Interface for the strategies.
 */ 
public interface IStrategy {
	
  /**
   * Method for which triggers the calculation of the previous values.  
   */     
	void recalculatePrevouses(FrameInfo frameInfo, Results results);
}
