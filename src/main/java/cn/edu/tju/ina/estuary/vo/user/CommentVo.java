package cn.edu.tju.ina.estuary.vo.user;

import cn.edu.tju.ina.estuary.domain.user.Comment;
import cn.edu.tju.ina.estuary.vo.info.SimpleInfoVo;

public class CommentVo {

	private int id;
	private SimpleUserVo fromUser;
	private SimpleUserVo toUser;
	private SimpleInfoVo info;
	private String content;
	private String addTime;
	private boolean hasRead;
	
	public CommentVo(Comment comment) {
		this.id = comment.getId();
		this.fromUser = new SimpleUserVo(comment.getFromUser());
		this.toUser = new SimpleUserVo(comment.getToUser());
		this.content = comment.getContent();
		this.addTime = comment.getAddTime().toString();
		this.hasRead = comment.getHasRead();
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