package cn.edu.tju.ina.estuary.domain.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "info_role")
public class Role implements Serializable {
	/** Default value included to remove warning. Remove or modify at will. **/
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	
	@Id
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
}