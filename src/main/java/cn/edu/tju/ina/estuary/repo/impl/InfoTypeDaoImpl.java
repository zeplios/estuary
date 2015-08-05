package cn.edu.tju.ina.estuary.repo.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.edu.tju.ina.estuary.domain.info.InfoType;
import cn.edu.tju.ina.estuary.repo.InfoTypeDao;
import cn.edu.tju.ina.estuary.repo.generic.GenericDaoImpl;

@Repository
public class InfoTypeDaoImpl extends GenericDaoImpl<InfoType, Integer> implements InfoTypeDao {
	
	@Override
	public List<InfoType> findAll() {
		return super.findAll();
	}
}
