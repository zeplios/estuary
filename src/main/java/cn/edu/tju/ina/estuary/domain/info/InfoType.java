package cn.edu.tju.ina.estuary.domain.info;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="info_info_type")
public class InfoType implements Serializable {
	/** Default value included to remove warning. Remove or modify at will. **/
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String className;
	private String listName;
	private boolean show;
	private Timestamp addTime;

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(nullable=false, columnDefinition="varchar(50) default ''")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="class_name", nullable=false, columnDefinition="varchar(50) default ''")
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Column(name="list_name", nullable=false, columnDefinition="varchar(50) default ''")
	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	/* whether show in main page*/
	@Column(name="showinmp", nullable=false, columnDefinition="bit default 1")
	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	@Column(name="addtime", nullable=false, columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

}