package cn.edu.tju.ina.estuary.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.tju.ina.estuary.domain.user.Collect;
import cn.edu.tju.ina.estuary.repo.CollectDao;
import cn.edu.tju.ina.estuary.service.CollectService;
import cn.edu.tju.ina.estuary.service.generic.GenericServiceImpl;

@Service("collectService")
@Transactional
public class CollectServiceImpl extends GenericServiceImpl<Collect, Integer> implements CollectService {

	private CollectDao collectDao;
	
	@Autowired
	public CollectServiceImpl(CollectDao collectDao) {
		super(collectDao);
		this.collectDao = collectDao;
	}
	
	@Override
	public Integer save(Collect c) {
		c.setAddTime(new Timestamp(System.currentTimeMillis()));
		return collectDao.save(c);
	}

	@Override
	public void delete(int cid) {
		Collect c = new Collect();
		c.setId(cid);
		collectDao.delete(c);
	}
	
//	@Override
//	public boolean getCollectedForUser(int uid, int cid) {
//		Collect c = collectDao.findByTwoProperty("user.id", uid, "id", cid);
//		if (c != null) {
//			collectDao.evict(c);
//		}
//		return (c != null);
//	}

	@Override
	public Collect getCollected(int uid, int infoid) {
		Collect c = collectDao.findByTwoProperty("user.id", uid, "info.id", infoid);
		if (c != null) {
			collectDao.evict(c);
		}
		return c;
	}
	
	@Override
	public boolean getCollected(int id) {
		Collect c = collectDao.loadById(id);
		if (c != null) {
			collectDao.evict(c);
		}
		return (c != null);
	}

	@Override
	public List<Collect> findNewestCollectsByUser(int uid, int type, int startid) {
		if (type == 0) {
			return collectDao.findNewestOrderByTime("user.id", uid, startid, false, true);
		} else {
			return collectDao.findNewestByTypeOrderByTime("user.id", uid, type, startid, false, true);
		}
	}

	@Override
	public List<Collect> findOldestCollectsByUser(int uid, int type, int endid, int length) {
		if (type == 0) {
			return collectDao.findOldestOrderByTime("user.id", uid, endid, length, false, true);
		} else {
			return collectDao.findOldestByTypeOrderByTime("user.id", uid, type, endid, length, false, true);
		}
	}
	
	@Override
	public List<Collect> findCollectsByInfo(int infoid) {
		return collectDao.findListByProperty("info.id", infoid);
	}
	
}
