package cn.edu.tju.ina.estuary.vo.user;

import cn.edu.tju.ina.estuary.domain.user.Notice;
import cn.edu.tju.ina.estuary.vo.info.SimpleInfoVo;

public class NoticeVo {

	private int id;
	private SimpleUserVo fromUser;
	private SimpleUserVo toUser;
	private SimpleInfoVo info;
	private String content;
	private String addTime;
	private boolean hasRead;
	
	public NoticeVo(Notice notification) {
		this.id = notification.getId();
		this.fromUser = new SimpleUserVo(notification.getFromUser());
		this.toUser = new SimpleUserVo(notification.getToUser());
		this.info = new SimpleInfoVo(notification.getInfo());
		this.content = notification.getContent();
		this.addTime = notification.getAddTime().toString();
		this.hasRead = notification.getHasRead();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public SimpleUserVo getFromUser() {
		return fromUser;
	}

	public void setFromUser(SimpleUserVo fromUser) {
		this.fromUser = fromUser;
	}

	public SimpleUserVo getToUser() {
		return toUser;
	}

	public void setToUser(SimpleUserVo toUser) {
		this.toUser = toUser;
	}

	public SimpleInfoVo getInfo() {
		return info;
	}

	public void setInfo(SimpleInfoVo info) {
		this.info = info;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public boolean isHasRead() {
		return hasRead;
	}

	public void setHasRead(boolean hasRead) {
		this.hasRead = hasRead;
	}

}