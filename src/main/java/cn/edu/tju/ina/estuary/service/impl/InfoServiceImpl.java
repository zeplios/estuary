package cn.edu.tju.ina.estuary.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.tju.ina.estuary.domain.info.Info;
import cn.edu.tju.ina.estuary.repo.InfoDao;
import cn.edu.tju.ina.estuary.service.InfoService;

@Service("infoService")
@Transactional
public class InfoServiceImpl implements InfoService {

	private InfoDao<Info> infoDao;
	
	@Override
	public List<Info> findInfosByPageOrderByTime(int type, int start, int length) {
		return infoDao.findListByPageOrderByTime(start, length);
	}
	
	@Override
	public List<Info> findNewestOrderByTime(int type, int fmsg) {
		return infoDao.findNewestByPageOrderByTime(fmsg);
	}
	
	@Override
	public List<Info> findOldestOrderByTime(int type, int lmsg, int length) {
		return infoDao.findOldestByPageOrderByTime(lmsg, length);
	}
	
	@Override
	public List<Info> findAllInfosByUser(int type, int userid) {
		return infoDao.findAllListOrderByTime("user.id", userid);
	}
	@Override
	public List<Info> findInfosByUserByPage(int type, int start, int length, int userid){
		return infoDao.findListByPageOrderByTime(start, length, "user.id", userid);
	}
	@Override
	public List<Info> findNewestByUser(int type, int fmsg, int userid){
		return infoDao.findNewestByPageOrderByTime(fmsg, "user.id", userid);
	}
	@Override
	public List<Info> findOldestByUser(int type, int lmsg, int length, int userid){
		return infoDao.findOldestByPageOrderByTime(lmsg, length, "user.id", userid);
	}
	
	@Override
	public List<Info> searchByTitleOrDesc(int type, String name) {
		return infoDao.searchByTwoDisjunction("title", "desc", name);
	}
	
	@Override
	public List<Info> searchByTitle(int type, String title) {
		return infoDao.searchByOneProperty("title", title);
	}

	@Override
	public int save(Info info) {
		info.setAddTime(new Timestamp(System.currentTimeMillis()));
		return infoDao.save(info);
	}

	@Override
	public void update(Info i) {
		infoDao.update(i);
	}

	@Override
	public void delete(Info i) {
		infoDao.delete(i);
	}
	
	@Override
	public void evict(Info i) {
		infoDao.evict(i);
	}
	
	@Override
	public void flush() {
		infoDao.flush();
	}
	
	@Override
	public Info findInfoById(int type, int id) {
		return infoDao.findById(id);
	}

}
