package name.stojanovski.kosta.bowling.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Frame class which contains attempts.
 */ 
public class Frame extends Observable {

  /**
   * Attempts list. 
   */     
  private List<Integer> attempts = new ArrayList<>();

  /**
   * Constructor of the Frame which needs an observer object.
   * The observer triggers on the addAttempts method   
   */     
	public Frame(Observer observer) {
		addObserver(observer);
	}

  /**
   * Add attempt value to the frame. Invoking the observer for further evaluation which is the result of the frame.
   */     
	public void addAttempt(FrameInfo frameInfo) {
		attempts.add(frameInfo.getAttemptValue());
		setChanged();
		notifyObservers(frameInfo);
	}

  /**
   * Returns the attempts list.
   */      
	public List<Integer> getAttempts() {
		return attempts;
	}
	
	
}
