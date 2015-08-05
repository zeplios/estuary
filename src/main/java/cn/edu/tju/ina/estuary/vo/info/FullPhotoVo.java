package cn.edu.tju.ina.estuary.vo.info;

import cn.edu.tju.ina.estuary.domain.info.Photo;

public class FullPhotoVo extends FullInfoVo {

	private SimpleAlbumVo album;
	
	public FullPhotoVo(Photo info) {
		super(info);
		this.album = new SimpleAlbumVo(info.getAlbum());
	}
	
	public SimpleAlbumVo getAlbum() {
		return album;
	}
	public void setAlbum(SimpleAlbumVo album) {
		this.album = album;
	}
	
}