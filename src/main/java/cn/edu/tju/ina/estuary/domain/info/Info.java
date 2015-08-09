package cn.edu.tju.ina.estuary.domain.info;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.web.util.HtmlUtils;

import cn.edu.tju.ina.estuary.domain.user.User;

@MappedSuperclass
public abstract class Info {
	protected Integer id;
	protected String title;
	protected String desc;
	protected User user;
	protected Integer type;
	protected Integer status;
	protected String picture;
	protected Integer viewCount;
	protected Integer commentCount;
	protected Integer thumbupCount;
	protected Integer collectCount;
	protected Timestamp addTime;
	
	protected Boolean deleted;

	protected boolean hasCollected = false;
	protected boolean hasThumbuped = false;
	
	protected Set<User> thumbupUsers;
	protected List<String> images;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(nullable=false, columnDefinition="varchar(50) default ''")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="description", columnDefinition="varchar(500) default ''")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = HtmlUtils.htmlUnescape(desc);;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name="type_id")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(nullable=false, columnDefinition="int(6) default 0")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(nullable=false, columnDefinition="varchar(50) default ''")
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Column(name="viewcount", columnDefinition="int default 0")
	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	@Column(name="commentcount", columnDefinition="int default 0")
	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	@Column(name="thumbupcount", columnDefinition="int default 0")
	public Integer getThumbupCount() {
		return thumbupCount;
	}

	public void setThumbupCount(Integer thumbupCount) {
		this.thumbupCount = thumbupCount;
	}

	@Column(name="collectcount", columnDefinition="int default 0")
	public Integer getCollectCount() {
		return collectCount;
	}

	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}

	@Column(name="addtime", updatable=false, nullable=false, columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	@Transient
	public boolean isHasCollected() {
		return hasCollected;
	}

	public void setHasCollected(boolean hasCollected) {
		this.hasCollected = hasCollected;
	}

	@Transient
	public boolean isHasThumbuped() {
		return hasThumbuped;
	}

	public void setHasThumbuped(boolean hasThumbuped) {
		this.hasThumbuped = hasThumbuped;
	}

	@ManyToMany
    @JoinTable(name = "info_thumbup", joinColumns = {@JoinColumn(name = "infoid") }, inverseJoinColumns = { @JoinColumn(name = "uid") })
	public Set<User> getThumbupUsers() {
		return thumbupUsers;
	}

	public void setThumbupUsers(Set<User> thumbupUsers) {
		this.thumbupUsers = thumbupUsers;
	}
	
	@ElementCollection(fetch=FetchType.LAZY)
	@CollectionTable(name="info_image", joinColumns=@JoinColumn(name="infoid"))
	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}
	
	@Column(nullable=false, columnDefinition="bit default 0")
	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
