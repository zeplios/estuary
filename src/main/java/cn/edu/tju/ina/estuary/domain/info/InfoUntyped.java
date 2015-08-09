package cn.edu.tju.ina.estuary.domain.info;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

@Entity
@Table(name="info_info")
@Inheritance(strategy=InheritanceType.JOINED)
@Polymorphism(type=PolymorphismType.EXPLICIT)
@DynamicInsert
@DynamicUpdate
public class InfoUntyped extends Info implements Serializable {
	/** Default value included to remove warning. Remove or modify at will. **/
	private static final long serialVersionUID = 1L;

}