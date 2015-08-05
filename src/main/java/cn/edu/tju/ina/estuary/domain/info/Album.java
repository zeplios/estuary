package cn.edu.tju.ina.estuary.domain.info;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="_album")
@PrimaryKeyJoinColumn(name="id")
@DynamicInsert
@DynamicUpdate
public class Album extends InfoUntyped {
	/** Default value included to remove warning. Remove or modify at will. **/
	private static final long serialVersionUID = 1L;

	private List<Photo> photos;
	
	public Album() {}
	
	public Album(int id) {this.setId(id);}
	
	@OneToMany(mappedBy="album", fetch=FetchType.LAZY)
	public List<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	
}