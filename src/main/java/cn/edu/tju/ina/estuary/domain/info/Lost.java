package cn.edu.tju.ina.estuary.domain.info;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="_lost")
@PrimaryKeyJoinColumn(name="id")
@DynamicInsert
@DynamicUpdate
public class Lost extends InfoUntyped {
	/** Default value included to remove warning. Remove or modify at will. **/
	private static final long serialVersionUID = 1L;

	private Boolean owner;
	private String ownerid;
	private String contact;
	private String place;
	private Boolean complete;
	
	public Boolean getOwner() {
		return owner;
	}
	public void setOwner(Boolean owner) {
		this.owner = owner;
	}
	public Boolean getComplete() {
		return complete;
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
	public void setComplete(Boolean complete) {
		this.complete = complete;
	}
}