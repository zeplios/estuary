package cn.edu.tju.ina.estuary.doc.entity;

public class MethodObject {
	private String url;
	private String name; // must in english
	private String title;
	private String desc;
	private String method;
	private ParamObject [] params;
	private String jsonRet;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public ParamObject[] getParams() {
		return params;
	}
	public void setParams(ParamObject[] params) {
		this.params = params;
	}
	public String getJsonRet() {
		return jsonRet;
	}
	public void setJsonRet(String jsonRet) {
		this.jsonRet = jsonRet;
	}
	
}
