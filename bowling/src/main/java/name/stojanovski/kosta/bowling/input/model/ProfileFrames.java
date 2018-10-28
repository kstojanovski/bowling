package name.stojanovski.kosta.bowling.input.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "frames")
public class ProfileFrames {

	private List<ProfileFrame> profileFrame = new ArrayList<>();

	@XmlElement(name = "frame")
	public List<ProfileFrame> getProfileFrame() {
		return profileFrame;
	}

	public void setProfileFrame(List<ProfileFrame> profileFrame) {
		this.profileFrame = profileFrame;
	}
}
