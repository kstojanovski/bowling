package name.stojanovski.kosta.bowling.model;

/**
 * FrameInfo class which contains inforamtion which frame and attempts it is
 * played and which value was scored for the given attempt. 
 */ 
public class FrameInfo {

	public FrameInfo(Integer frameIndex,
			Integer attemptIndex, Integer attemptValue) {
		super();
		this.frameIndex = frameIndex;
		this.attemptIndex = attemptIndex;
		this.attemptValue = attemptValue;
	}

	private Integer frameIndex;
	private Integer attemptIndex;
	private Integer attemptValue;

	public Integer getFrameIndex() {
		return frameIndex;
	}

	public Integer getAttemptIndex() {
		return attemptIndex;
	}

	public Integer getAttemptValue() {
		return attemptValue;
	}

}
