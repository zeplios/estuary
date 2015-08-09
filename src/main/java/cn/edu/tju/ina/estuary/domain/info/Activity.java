package cn.edu.tju.ina.estuary.domain.info;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

@Entity
@Table(name="info_activity")
@PrimaryKeyJoinColumn(name="id")
@Polymorphism(type=PolymorphismType.EXPLICIT)
@DynamicInsert
@DynamicUpdate
public class Activity extends InfoUntyped {
	/** Default value included to remove warning. Remove or modify at will. **/
	private static final long serialVersionUID = 1L;

	private String area;
	private Timestamp time;
	private String orgName;
	private String orgIntro;
	
	@Column(nullable=false, columnDefinition="varchar(50) default ''")
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	@Column(nullable=false, columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	@Column(nullable=false, columnDefinition="varchar(50) default ''")
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	@Column(nullable=false, columnDefinition="varchar(500) default ''")
	public String getOrgIntro() {
		return orgIntro;
	}
	public void setOrgIntro(String orgIntro) {
		this.orgIntro = orgIntro;
	}

}