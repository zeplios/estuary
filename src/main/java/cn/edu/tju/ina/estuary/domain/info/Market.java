package cn.edu.tju.ina.estuary.domain.info;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="_market")
@PrimaryKeyJoinColumn(name="id")
@DynamicInsert
@DynamicUpdate
public class Market extends InfoUntyped {
	/** Default value included to remove warning. Remove or modify at will. **/
	private static final long serialVersionUID = 1L;

	private MarketCategory category;
	private Double price;
	private String contact;
	private Boolean complete;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	public MarketCategory getCategory() {
		return category;
	}
	public void setCategory(MarketCategory category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public void setComplete(Boolean complete) {
		this.complete = complete;
	}
	public Boolean getComplete() {
		return complete;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
}