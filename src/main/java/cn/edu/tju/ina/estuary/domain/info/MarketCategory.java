package cn.edu.tju.ina.estuary.domain.info;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="_market_category")
public class MarketCategory implements Serializable {
	/** Default value included to remove warning. Remove or modify at will. **/
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
//	private MarketCategory parent;

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	@ManyToOne(fetch=FetchType.LAZY)
//	public MarketCategory getParent() {
//		return parent;
//	}
//
//	public void setParent(MarketCategory parent) {
//		this.parent = parent;
//	}
	
}