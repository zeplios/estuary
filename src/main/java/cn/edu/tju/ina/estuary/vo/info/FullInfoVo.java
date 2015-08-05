package cn.edu.tju.ina.estuary.vo.info;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.edu.tju.ina.estuary.domain.info.Info;
import cn.edu.tju.ina.estuary.domain.user.User;
import cn.edu.tju.ina.estuary.vo.user.SimpleUserVo;

public class FullInfoVo extends SimpleInfoVo {

	protected String desc;
	protected Set<SimpleUserVo> thumbupUsers;
	protected List<String> images;
	
	public FullInfoVo(Info info) {
		super(info);
		this.desc = info.getDesc();
		thumbupUsers = new HashSet<SimpleUserVo>();
		for (User u : info.getThumbupUsers()) {
			thumbupUsers.add(new SimpleUserVo(u));
		}
		images = info.getImages();
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Set<SimpleUserVo> getThumbupUsers() {
		return thumbupUsers;
	}

	public void setThumbupUsers(Set<SimpleUserVo> thumbupUsers) {
		this.thumbupUsers = thumbupUsers;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

}