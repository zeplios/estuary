package cn.edu.tju.ina.estuary.repo.impl;

import org.springframework.stereotype.Repository;

import cn.edu.tju.ina.estuary.domain.info.Activity;
import cn.edu.tju.ina.estuary.repo.InfoDao;

@Repository("deletedDao")
public class DeletedDaoImpl extends UntypedInfoDaoImpl<Activity> implements InfoDao<Activity> {
}
