package cn.edu.tju.ina.estuary.repo.impl;

import org.springframework.stereotype.Repository;

import cn.edu.tju.ina.estuary.domain.info.Photo;
import cn.edu.tju.ina.estuary.repo.InfoDao;

@Repository("photoDao")
public class PhotoDaoImpl extends UntypedInfoDaoImpl<Photo> implements InfoDao<Photo> {
}
