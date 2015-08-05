package cn.edu.tju.ina.estuary.vo.info;

import java.util.ArrayList;
import java.util.List;

import cn.edu.tju.ina.estuary.domain.info.Album;
import cn.edu.tju.ina.estuary.domain.info.Photo;

public class FullAlbumVo extends FullInfoVo {

	private List<SimplePhotoVo> photos;
	
	public FullAlbumVo(Album info) {
		super(info);
		photos = new ArrayList<SimplePhotoVo>();
		for (Photo p : info.getPhotos()) {
			photos.add(new SimplePhotoVo(p));
		}
	}
	public List<SimplePhotoVo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<SimplePhotoVo> photos) {
		this.photos = photos;
	}
}