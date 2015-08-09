package cn.edu.tju.ina.estuary.domain.info;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="info_recruit")
@PrimaryKeyJoinColumn(name="id")
@DynamicInsert
@DynamicUpdate
public class Recruit extends InfoUntyped {
	/** Default value included to remove warning. Remove or modify at will. **/
	private static final long serialVersionUID = 1L;

	private String area;
	private String time;
	private String comName;
	private String comIntro;
	private String website;
	
	@Column(nullable=false, columnDefinition="varchar(100) default ''")
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	@Column(nullable=false, columnDefinition="varchar(50) default ''")
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	@Column(nullable=false, columnDefinition="varchar(50) default ''")
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	
	@Column(nullable=false, columnDefinition="varchar(500) default ''")
	public String getComIntro() {
		return comIntro;
	}
	public void setComIntro(String comIntro) {
		this.comIntro = comIntro;
	}
	
	@Column(nullable=false, columnDefinition="varchar(50) default ''")
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}

}