package cn.edu.tju.ina.estuary.repo.impl;

import org.springframework.stereotype.Repository;

import cn.edu.tju.ina.estuary.domain.info.Lost;
import cn.edu.tju.ina.estuary.repo.InfoDao;

@Repository("lostDao")
public class LostDaoImpl extends UntypedInfoDaoImpl<Lost> implements InfoDao<Lost> {
}
