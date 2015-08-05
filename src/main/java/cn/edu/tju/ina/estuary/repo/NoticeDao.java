package cn.edu.tju.ina.estuary.repo;

import java.util.List;

import cn.edu.tju.ina.estuary.domain.user.Notice;
import cn.edu.tju.ina.estuary.repo.generic.GenericDao;

public interface NoticeDao extends GenericDao<Notice, Integer> {

	List<Notice> findNewestOrderByTime(String name, Object value, int startid, boolean joinInfo);
	List<Notice> findOldestOrderByTime(String name, Object value, int endid, int offset, boolean joinInfo);
	
}
