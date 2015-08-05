package cn.edu.tju.ina.estuary.vo.info;

import cn.edu.tju.ina.estuary.domain.info.Market;

public class FullMarketVo extends FullInfoVo {

	private MarketCategoryVo category;
	private double price;
	private String contact;
	
	public FullMarketVo(Market info) {
		super(info);
		this.category = new MarketCategoryVo(info.getCategory());
		this.price = info.getPrice();
		this.contact = info.getContact();
	}
	
	public MarketCategoryVo getCategory() {
		return category;
	}
	public void setCategory(MarketCategoryVo category) {
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
	
}