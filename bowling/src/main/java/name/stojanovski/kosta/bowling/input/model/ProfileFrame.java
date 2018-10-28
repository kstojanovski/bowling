package name.stojanovski.kosta.bowling.input.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class ProfileFrame {

	private List<Integer> bowls = new ArrayList<>();

	@XmlElement(name = "bowl")
	public List<Integer> getBowls() {
		return bowls;
	}

	public void setBowls(List<Integer> bowls) {
		this.bowls = bowls;
	}
	
}
