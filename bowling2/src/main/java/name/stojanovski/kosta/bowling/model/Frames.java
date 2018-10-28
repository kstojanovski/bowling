package name.stojanovski.kosta.bowling.model;

import java.util.ArrayList;
import java.util.List;

import name.stojanovski.kosta.bowling.eval.ResultObserver;

/**
 * Contains 10 Frames. 
 */ 
public class Frames {

	private static  int FRAME_SIZE = 10;
	private  List<Frame> frameCollection = new ArrayList<>(FRAME_SIZE);

  /**
   * Invoking the constructor adds 10 frames to the list.
   */     
	public Frames(ResultObserver ResultObserver) {
		for (int i = 0; i < FRAME_SIZE; i++) {
			frameCollection.add(FrameFactory.build(ResultObserver));
		}
	}

  /**
   * Returns the frames as list.
   */     
	public List<Frame> getFrameCollection() {
		return frameCollection;
	}

}
