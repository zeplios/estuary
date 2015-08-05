package cn.edu.tju.ina.estuary.repo.impl;

import org.springframework.stereotype.Repository;

import cn.edu.tju.ina.estuary.domain.info.Hotword;
import cn.edu.tju.ina.estuary.repo.HotwordDao;
import cn.edu.tju.ina.estuary.repo.generic.GenericDaoImpl;

@Repository
public class HotwordDaoImpl extends GenericDaoImpl<Hotword, Integer> implements HotwordDao {
	
}
