package cn.edu.tju.ina.estuary.repo.impl;

import org.springframework.stereotype.Repository;

import cn.edu.tju.ina.estuary.domain.info.Recruit;
import cn.edu.tju.ina.estuary.repo.InfoDao;

@Repository("recruitDao")
public class RecruitDaoImpl extends UntypedInfoDaoImpl<Recruit> implements InfoDao<Recruit> {
}
