package cn.edu.tju.ina.estuary.repo.impl;

import org.springframework.stereotype.Repository;

import cn.edu.tju.ina.estuary.domain.info.Activity;
import cn.edu.tju.ina.estuary.repo.InfoDao;

@Repository("activityDao")
public class ActivityDaoImpl extends UntypedInfoDaoImpl<Activity> implements InfoDao<Activity> {
}
