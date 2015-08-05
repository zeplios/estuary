package cn.edu.tju.ina.estuary.domain.info;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="_info")
@DynamicInsert
@DynamicUpdate
public class InfoSingle extends Info implements Serializable {
	/** Default value included to remove warning. Remove or modify at will. **/
	private static final long serialVersionUID = 1L;

	public InfoSingle() {
	}
	
	public InfoSingle(int id) {
		this.id = id;
	}
}