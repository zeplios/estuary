package cn.edu.tju.ina.estuary.vo.user;

import cn.edu.tju.ina.estuary.domain.user.Thumbup;
import cn.edu.tju.ina.estuary.vo.info.SimpleInfoVo;

public class ThumbupVo {
	private int id;
	private SimpleUserVo user;
	private SimpleInfoVo info;
	private String addTime;
	
	public ThumbupVo(Thumbup t) {
		this.id = t.getId();
		this.addTime = t.getAddTime().toString();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public SimpleUserVo getUser() {
		return user;
	}
	public void setUser(SimpleUserVo user) {
		this.user = user;
	}
	public SimpleInfoVo getInfo() {
		return info;
	}
	public void setInfo(SimpleInfoVo info) {
		this.info = info;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	
	
}
