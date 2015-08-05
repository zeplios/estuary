package cn.edu.tju.ina.estuary.vo.info;

import cn.edu.tju.ina.estuary.domain.info.Info;
import cn.edu.tju.ina.estuary.vo.user.SimpleUserVo;

public class SimpleInfoVo {

	private int id;
	private String title;
	private int status;
	private String picture;
	private int viewCount;
	private int commentCount;
	private int thumbupCount;
	private int collectCount;
	private String addTime;
	private int type;

	private SimpleUserVo user;

	private boolean hasCollected;
	private boolean hasThumbuped;
	private boolean deleted;

	public SimpleInfoVo(Info info) {
		this.id = info.getId();
		this.title = info.getTitle();
		this.status = info.getStatus();
		this.picture = info.getPicture();
		this.viewCount = info.getViewCount();
		this.commentCount = info.getCommentCount();
		this.collectCount = info.getCollectCount();
		this.addTime = info.getAddTime().toString();
		this.type = info.getType();

		if (info.getUser() != null) {
			this.user = new SimpleUserVo(info.getUser());
		}

		this.hasCollected = info.isHasCollected();
		this.hasThumbuped = info.isHasThumbuped();
		this.deleted = info.getDeleted();
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getThumbupCount() {
		return thumbupCount;
	}

	public void setThumbupCount(int thumbupCount) {
		this.thumbupCount = thumbupCount;
	}

	public int getCollectCount() {
		return collectCount;
	}

	public void setCollectCount(int collectCount) {
		this.collectCount = collectCount;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public SimpleUserVo getUser() {
		return user;
	}

	public void setUser(SimpleUserVo user) {
		this.user = user;
	}

	public boolean isHasCollected() {
		return hasCollected;
	}

	public void setHasCollected(boolean hasCollected) {
		this.hasCollected = hasCollected;
	}

	public boolean isHasThumbuped() {
		return hasThumbuped;
	}

	public void setHasThumbuped(boolean hasThumbuped) {
		this.hasThumbuped = hasThumbuped;
	}

}