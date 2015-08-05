package cn.edu.tju.ina.estuary.vo.info;

import cn.edu.tju.ina.estuary.domain.info.Lost;

public class FullLostVo extends FullInfoVo {

	private boolean owner;
	private String ownerid;
	private String contact;
	private String place;
	private boolean complete;

	public FullLostVo(Lost info) {
		super(info);
		this.owner = info.getOwner();
		this.ownerid = info.getOwnerid();
		this.contact = info.getContact();
		this.place = info.getPlace();
	}
	
	public boolean isOwner() {
		return owner;
	}
	public void setOwner(boolean owner) {
		this.owner = owner;
	}
	public String getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
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