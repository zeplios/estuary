package cn.edu.tju.ina.estuary.vo.info;

import cn.edu.tju.ina.estuary.domain.info.MarketCategory;

public class MarketCategoryVo {

	private int id;
	private String name;
	
	public MarketCategoryVo(MarketCategory mc) {
		this.id = mc.getId();
		this.name = mc.getName();
	}

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
	
}