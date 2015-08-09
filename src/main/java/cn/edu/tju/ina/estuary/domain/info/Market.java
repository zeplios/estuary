package cn.edu.tju.ina.estuary.domain.info;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="info_market")
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
	
	@Column(nullable=false, columnDefinition="double default 0.0")
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Column(nullable=false, columnDefinition="varchar(50) default ''")
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public void setComplete(Boolean complete) {
		this.complete = complete;
	}
	
	@Column(nullable=false, columnDefinition="bit default 0")
	public Boolean getComplete() {
		return complete;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
}