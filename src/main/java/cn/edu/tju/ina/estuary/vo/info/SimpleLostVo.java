package cn.edu.tju.ina.estuary.vo.info;

import cn.edu.tju.ina.estuary.domain.info.Lost;

public class SimpleLostVo extends SimpleInfoVo {

	private String place;
	private boolean owner;
	private boolean complete;
	
	public SimpleLostVo(Lost info) {
		super(info);
		this.place = info.getPlace();
		this.complete = info.getComplete();
		this.owner = info.getOwner();
	}

	public boolean isOwner() {
		return owner;
	}

	public void setOwner(boolean owner) {
		this.owner = owner;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	
}