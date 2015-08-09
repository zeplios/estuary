package cn.edu.tju.ina.estuary.vo.info;

import cn.edu.tju.ina.estuary.domain.info.Activity;

public class FullActivityVo extends FullInfoVo {

	private String area;
	private String time;
	private String orgName;
	private String orgIntro;
	
	public FullActivityVo(Activity info) {
		super(info);
		this.area = info.getArea();
		this.time = info.getTime().toString();
		this.orgName = info.getOrgName();
		this.orgIntro = info.getOrgIntro();
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
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgIntro() {
		return orgIntro;
	}
	public void setOrgIntro(String orgIntro) {
		this.orgIntro = orgIntro;
	}

}