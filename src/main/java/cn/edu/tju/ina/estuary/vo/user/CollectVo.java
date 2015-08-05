package cn.edu.tju.ina.estuary.vo.user;

import cn.edu.tju.ina.estuary.domain.user.Collect;
import cn.edu.tju.ina.estuary.vo.info.SimpleInfoVo;

public class CollectVo {
	private int id;
	private SimpleUserVo user;
	private SimpleInfoVo info;
	private String addTime;
	
	public CollectVo(Collect c) {
		this.id = c.getId();
		this.addTime = c.getAddTime().toString();
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
