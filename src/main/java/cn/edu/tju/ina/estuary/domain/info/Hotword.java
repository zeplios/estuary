package cn.edu.tju.ina.estuary.domain.info;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="info_hotword")
@DynamicInsert
@DynamicUpdate
public class Hotword implements Serializable {

	private static final long serialVersionUID = 1023100190427749581L;
	
	private int id;
	private String word;
	private Integer count;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(nullable=false, columnDefinition="varchar(50) default ''")
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	
	@Column(nullable=false, columnDefinition="int default 0")
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}
