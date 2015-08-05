package cn.edu.tju.ina.estuary.vo.info;

import cn.edu.tju.ina.estuary.domain.info.Market;

public class SimpleMarketVo extends SimpleInfoVo {

	private double price;
	private boolean complete;
	
	public SimpleMarketVo(Market info) {
		super(info);
		this.price = info.getPrice();
		this.complete = info.getComplete();
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	
}