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
@Table(name="info_thumbup")
public class Thumbup implements Serializable {

	private static final long serialVersionUID = -1839802699353935021L;
	
	private Integer id;
	private User user;
	private InfoSingle info;
	private Integer type;
	private Timestamp addTime;
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="uid")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="infoid")
	public InfoSingle getInfo() {
		return info;
	}
	public void setInfo(InfoSingle info) {
		this.info = info;
	}
	
	@Column(name="infotype_id")
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	@Column(name="thtime", nullable=false, columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	
}
