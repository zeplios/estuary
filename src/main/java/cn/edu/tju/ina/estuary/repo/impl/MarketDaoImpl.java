package cn.edu.tju.ina.estuary.repo.impl;

import org.springframework.stereotype.Repository;

import cn.edu.tju.ina.estuary.domain.info.Market;
import cn.edu.tju.ina.estuary.repo.InfoDao;

@Repository("marketDao")
public class MarketDaoImpl extends UntypedInfoDaoImpl<Market> implements InfoDao<Market> {
}
