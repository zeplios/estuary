package cn.edu.tju.ina.estuary.domain.info;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

@Entity
@Table(name="_activity")
@PrimaryKeyJoinColumn(name="id")
@Polymorphism(type=PolymorphismType.EXPLICIT)
@DynamicInsert
@DynamicUpdate
public class Activity extends InfoUntyped {
	/** Default value included to remove warning. Remove or modify at will. **/
	private static final long serialVersionUID = 1L;

	private String area;
	private String time;
	private String orgName;
	private String orgIntro;
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgIntro() {
		return orgIntro;
	}
	public void setOrgIntro(String orgIntro) {
		this.orgIntro = orgIntro;
	}

}