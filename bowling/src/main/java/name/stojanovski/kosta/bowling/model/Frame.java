package name.stojanovski.kosta.bowling.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Frame model 
 */
public class Frame {

	private List<Integer> bowls = new ArrayList<>();

	private Integer result = 0;	
	
	private BowlingEnum extraGood;
	
	private int strikeResult;
	
	private Integer aggResult = 0;
	
	public List<Integer> getBowls() {
		return bowls;
	}

	public void setBowls(List<Integer> bowls) {
		this.bowls = bowls;
	}

	public Integer getResult() {
		return result;
	}

	public void addResult(Integer previous) {
		this.result += previous;
	}

	public BowlingEnum getExtraGood() {
		return extraGood;
	}

	public void setExtraGood(BowlingEnum extraGood) {
		this.extraGood = extraGood;
	}

	public int getStrikeResult() {
		return strikeResult;
	}

	public void incStrikeResult() {
		++strikeResult;
	}

	public Integer getAggResult() {
		return aggResult;
	}

	public void addAggResult(Integer previous) {
		this.aggResult += previous;
	}
	
}
