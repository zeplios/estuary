package cn.edu.tju.ina.estuary.domain.user;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.edu.tju.ina.estuary.domain.info.InfoSingle;

@Entity
@Table(name="info_comment")
public class Comment implements Serializable {
	/** Default value included to remove warning. Remove or modify at will. **/
	private static final long serialVersionUID = 1L;

	private int id;
	private User fromUser;
	private User toUser;
	private InfoSingle info;
	private String content;
	private Timestamp addTime;
	private Boolean hasRead;

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="fromuid")
	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}
	
	@ManyToOne
	@JoinColumn(name="touid")
	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="infoid")
	public InfoSingle getInfo() {
		return info;
	}

	public void setInfo(InfoSingle info) {
		this.info = info;
	}

	@Column(nullable=false, columnDefinition="varchar(50) default ''")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name="addtime", nullable=false, columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	@Column(nullable=false, columnDefinition="bit default 0")
	public Boolean getHasRead() {
		return hasRead;
	}

	public void setHasRead(Boolean hasRead) {
		this.hasRead = hasRead;
	}

}