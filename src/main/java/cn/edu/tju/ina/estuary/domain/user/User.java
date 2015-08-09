package cn.edu.tju.ina.estuary.domain.user;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "info_user")
@DynamicInsert
@DynamicUpdate
public class User implements Serializable {
	/** Default value included to remove warning. Remove or modify at will. **/
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String username;
	private String nickname;
	private String realname;
	private Integer gender;
	private Integer auth;
	private String email;
	private String phone;
	private String avatar;
	private String lastLoginTime;
	private Integer loginTimes;
	private Timestamp addTime;
	
	private Specialty specialty;
	private Role role;
	
	public User() {
	}
	
	public User(int id) {
		this.id = id;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(nullable=false, columnDefinition="varchar(50) default ''")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(nullable=false, columnDefinition="varchar(50) default ''")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(nullable=false, columnDefinition="varchar(50) default ''")
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Column(nullable=false, columnDefinition="int(3) default 2") // 2 == secret
	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	@Column(nullable=false, columnDefinition="int(2) default 1")
	public Integer getAuth() {
		return auth;
	}

	public void setAuth(Integer auth) {
		this.auth = auth;
	}

	@Email
	@Column(unique=true, nullable=false, columnDefinition="varchar(50) default ''")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(nullable=false, columnDefinition="varchar(15) default ''")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(nullable=false, columnDefinition="varchar(50) default ''")
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Column(name="lastlogintime", nullable=false, columnDefinition="varchar(50) default ''")
	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Column(name="logintimes", nullable=false, columnDefinition="int default 0")
	public Integer getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	@Column(name="addtime", nullable=false, columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="specialty_id")
	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="role_id")
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}