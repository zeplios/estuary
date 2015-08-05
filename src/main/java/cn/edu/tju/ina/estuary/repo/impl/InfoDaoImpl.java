package cn.edu.tju.ina.estuary.repo.impl;

import org.springframework.stereotype.Repository;

import cn.edu.tju.ina.estuary.domain.info.InfoSingle;
import cn.edu.tju.ina.estuary.repo.InfoDao;

@Repository("infoDao")
public class InfoDaoImpl extends UntypedInfoDaoImpl<InfoSingle> implements InfoDao<InfoSingle> {
}
