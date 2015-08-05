package cn.edu.tju.ina.estuary.vo.user;

import cn.edu.tju.ina.estuary.doc.annotation.Description;
import cn.edu.tju.ina.estuary.doc.annotation.Export;
import cn.edu.tju.ina.estuary.domain.user.User;

@Export("SimpleUser")
@Description("SimpleUser代表一个最简单的User对象，User对象的字段并不是全部要公开的，有的内容只有自己可以可到，这个SimpleUser就代表User最基本的属性，大家都可以看到的属性。")
public class SimpleUserVo {

	private int id;
	private String username;
	private String nickname;
	private String realname;
	private int gender;
	private String email;
	private String phone;
	@Description("网站根目录（不是项目根目录）+ 这个域的值")
	private String avatar;
	
	private String specialtyName;
	private String academyName;
	
	public SimpleUserVo(User user) {
		if (user == null) {
			return;
		}
		this.id = user.getId();
		this.username = user.getUsername();
		this.nickname = user.getNickname();
		this.gender = user.getGender();
		this.email = user.getEmail();
		this.phone = user.getPhone();
		this.avatar = user.getAvatar();
		
		if (user.getSpecialty() != null) {
			this.specialtyName = user.getSpecialty().getName();
			this.academyName = user.getSpecialty().getAcademy().getName();
		} else {
			this.specialtyName = "";
			this.academyName = "";
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getSpecialtyName() {
		return specialtyName;
	}

	public void setSpecialtyName(String specialtyName) {
		this.specialtyName = specialtyName;
	}

	public String getAcademyName() {
		return academyName;
	}

	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}
	
}