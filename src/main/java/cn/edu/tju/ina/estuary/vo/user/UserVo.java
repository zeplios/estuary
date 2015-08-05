package cn.edu.tju.ina.estuary.vo.user;

import cn.edu.tju.ina.estuary.domain.user.User;

public class UserVo extends SimpleUserVo {

	private int auth;
	private int status;
	private int rank;
	private String lastLoginTime;
	private String lastLoginIp;
	private int loginTimes;
	private String addTime;
	
	public UserVo(User user) {
		super(user);
		this.auth = user.getAuth();
		this.lastLoginTime = user.getLastLoginTime();
		this.loginTimes = user.getLoginTimes();
		this.addTime = user.getAddTime().toString();
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public int getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(int loginTimes) {
		this.loginTimes = loginTimes;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	
}