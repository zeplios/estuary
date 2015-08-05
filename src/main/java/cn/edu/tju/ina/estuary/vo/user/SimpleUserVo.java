package cn.edu.tju.ina.estuary.vo.user;

import cn.edu.tju.ina.estuary.doc.annotation.Description;
import cn.edu.tju.ina.estuary.doc.annotation.Export;
import cn.edu.tju.ina.estuary.domain.user.User;

@Export("SimpleUser")
@Description("SimpleUser����һ����򵥵�User����User������ֶβ�����ȫ��Ҫ�����ģ��е�����ֻ���Լ����Կɵ������SimpleUser�ʹ���User����������ԣ���Ҷ����Կ��������ԡ�")
public class SimpleUserVo {

	private int id;
	private String username;
	private String nickname;
	private String realname;
	private int gender;
	private String email;
	private String phone;
	@Description("��վ��Ŀ¼��������Ŀ��Ŀ¼��+ ������ֵ")
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