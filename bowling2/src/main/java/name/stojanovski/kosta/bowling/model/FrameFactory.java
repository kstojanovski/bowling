package name.stojanovski.kosta.bowling.model;

import java.util.Observer;

/**
 * Factory class for building a frame.
 */ 
public class FrameFactory {

	private FrameFactory() {            
	}

  /**
   * Returns an instance of an Frame with observer as parameter.  
   */     
	public static Frame build(Observer observer) {
		return new Frame(observer);
	}
}
