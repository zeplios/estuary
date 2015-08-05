package cn.edu.tju.ina.estuary.repo.impl;

import org.springframework.stereotype.Repository;

import cn.edu.tju.ina.estuary.domain.info.Album;
import cn.edu.tju.ina.estuary.repo.InfoDao;

@Repository("albumDao")
public class AlbumDaoImpl extends UntypedInfoDaoImpl<Album> implements InfoDao<Album> {
}
