package cn.edu.tju.ina.estuary.vo.info;

import cn.edu.tju.ina.estuary.domain.info.Recruit;

public class FullRecruitVo extends FullInfoVo {

	private String area;
	private String time;
	private String comName;
	private String comIntro;
	private String website;
	
	public FullRecruitVo(Recruit info) {
		super(info);
		this.area = info.getArea();
		this.time = info.getTime();
		this.comName = info.getComName();
		this.comIntro = info.getComIntro();
		this.website = info.getWebsite();
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
	public String getComIntro() {
		return comIntro;
	}
	public void setComIntro(String comIntro) {
		this.comIntro = comIntro;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}

}