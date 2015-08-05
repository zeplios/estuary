package cn.edu.tju.ina.estuary.domain.info;

import cn.edu.tju.ina.estuary.util.IdAssistant;

public class InfoSet {
	
	private Activity activity;
	private Recruit recruit;
	private Lost lost;
	private Album album;
	private Market market;
	private Photo photo;
	
	public Info getInfo() {
		Info info = null;
		String infoTypeName = null;
		if (activity != null) {
			info = activity;
			infoTypeName = "Activity";
		} else if (recruit != null) {
			info = recruit;
			infoTypeName = "Recruit";
		} else if (lost != null) {
			info = lost;
			infoTypeName = "Lost";
		} else if (album != null) {
			info = album;
			infoTypeName = "Album";
		} else if (market != null) {
			info = market;
			infoTypeName = "Market";
		} else if (photo != null) {
			info = photo;
			infoTypeName = "Photo";
		}
		
		if (info != null && infoTypeName != null) {
			int type = IdAssistant.getInstance().getInfoTypeId(infoTypeName);
			info.setType(type);
		}
		return info;
	}
	
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	public Market getMarket() {
		return market;
	}
	public void setMarket(Market market) {
		this.market = market;
	}
	public Photo getPhoto() {
		return photo;
	}
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	public Recruit getRecruit() {
		return recruit;
	}
	public void setRecruit(Recruit recruit) {
		this.recruit = recruit;
	}
	public Lost getLost() {
		return lost;
	}
	public void setLost(Lost lost) {
		this.lost = lost;
	}
	
}
