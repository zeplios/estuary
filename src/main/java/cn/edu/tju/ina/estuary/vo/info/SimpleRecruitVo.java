package cn.edu.tju.ina.estuary.vo.info;

import cn.edu.tju.ina.estuary.domain.info.Recruit;

public class SimpleRecruitVo extends SimpleInfoVo {

	private String area;
	private String time;
	private String comName;
	
	public SimpleRecruitVo(Recruit info) {
		super(info);
		this.area = info.getArea();
		this.time = info.getTime();
		this.comName = info.getComName();
	}
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}

}