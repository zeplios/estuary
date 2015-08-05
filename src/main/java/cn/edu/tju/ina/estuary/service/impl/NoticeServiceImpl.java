package cn.edu.tju.ina.estuary.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.tju.ina.estuary.domain.user.Notice;
import cn.edu.tju.ina.estuary.repo.NoticeDao;
import cn.edu.tju.ina.estuary.service.NoticeService;
import cn.edu.tju.ina.estuary.service.generic.GenericServiceImpl;

@Service("noticeService")
@Transactional
public class NoticeServiceImpl extends GenericServiceImpl<Notice, Integer> implements NoticeService {

	private NoticeDao noticeDao;

	@Autowired
	public NoticeServiceImpl(NoticeDao noticeDao) {
		super(noticeDao);
		this.noticeDao = noticeDao;
	}
	
	@Override
	public Integer save(Notice n) {
		n.setAddTime(new Timestamp(System.currentTimeMillis()));
		return noticeDao.save(n);
	}

	@Override
	public void delete(int nid) {
		Notice n = new Notice();
		n.setId(nid);
		noticeDao.delete(n);
	}

	@Override
	public List<Notice> findUnreadNotices(int userid) {
		return noticeDao.findListByTwoProperty("toUser.id", userid, "hasRead", false);
	}

}
