package cn.edu.tju.ina.estuary.doc.entity;

public class FieldObject {
	// name of field in value object
	private String name = "";;
	// type of the field
	private String type = "";
	private boolean innerType = true;
	// description of the field
	private String desc = "";
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isInnerType() {
		return innerType;
	}
	public void setInnerType(boolean innerType) {
		this.innerType = innerType;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
