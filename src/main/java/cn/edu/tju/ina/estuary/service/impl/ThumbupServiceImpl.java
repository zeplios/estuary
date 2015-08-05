package cn.edu.tju.ina.estuary.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.tju.ina.estuary.domain.user.Thumbup;
import cn.edu.tju.ina.estuary.repo.ThumbupDao;
import cn.edu.tju.ina.estuary.service.ThumbupService;
import cn.edu.tju.ina.estuary.service.generic.GenericServiceImpl;

@Service("thumbupService")
@Transactional
public class ThumbupServiceImpl extends GenericServiceImpl<Thumbup, Integer> implements ThumbupService {
	
	private ThumbupDao thumbupDao;
	
	@Autowired
	public ThumbupServiceImpl(ThumbupDao thumbupDao) {
		super(thumbupDao);
		this.thumbupDao = thumbupDao;
	}
	
	@Override
	public Integer save(Thumbup t) {
		t.setAddTime(new Timestamp(System.currentTimeMillis()));
		return thumbupDao.save(t);
	}

	@Override
	public void delete(int tid) {
		Thumbup t = new Thumbup();
		t.setId(tid);
		thumbupDao.delete(t);
	}
	
//	@Override
//	public boolean getThumbupedForUser(int uid, int tid) {
//		Thumbup t = thumbupDao.findByTwoProperty("user.id", uid, "id", tid);
//		return (t != null);
//	}

	@Override
	public Thumbup getThumbuped(int uid, int infoid) {
		Thumbup t = thumbupDao.findByTwoProperty("user.id", uid, "info.id", infoid);
		if (t != null) {
			thumbupDao.evict(t);
		}
		return t;
	}
	
	@Override
	public boolean getThumbuped(int id) {
		Thumbup t = thumbupDao.loadById(id);
		return (t != null);
	}

	@Override
	public List<Thumbup> findNewestThumbupsByUser(int uid, int type, int startid) {
		if (type == 0) {
			return thumbupDao.findNewestOrderByTime("user.id", uid, startid, false, true);
		} else {
			return thumbupDao.findNewestByTypeOrderByTime("user.id", uid, type, startid, false, true);
		}
	}

	@Override
	public List<Thumbup> findOldestThumbupsByUser(int uid, int type, int endid, int length) {
		if (type == 0) {
			return thumbupDao.findOldestOrderByTime("user.id", uid, endid, length, false, true);
		} else {
			return thumbupDao.findOldestByTypeOrderByTime("user.id", uid, type, endid, length, false, true);
		}
	}
	
	@Override
	public List<Thumbup> findThumbupsByInfo(int infoid) {
		return thumbupDao.findListByProperty("info.id", infoid);
	}
	
}
