package cn.edu.tju.ina.estuary.doc.entity;

import java.util.ArrayList;
import java.util.List;

public class ValueObject {
	// name of value object
	private String name;
	private String base;
	private List<FieldObject> fields;
	private String desc;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public List<FieldObject> getFields() {
		return fields;
	}
	public void setFields(List<FieldObject> fields) {
		this.fields = fields;
	}
	public void addField(FieldObject field) {
		if (fields == null) {
			fields = new ArrayList<FieldObject>();
		}
		this.fields.add(field);
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
