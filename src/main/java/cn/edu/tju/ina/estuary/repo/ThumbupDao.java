package cn.edu.tju.ina.estuary.repo;

import java.util.List;

import cn.edu.tju.ina.estuary.domain.user.Thumbup;
import cn.edu.tju.ina.estuary.repo.generic.GenericDao;

public interface ThumbupDao extends GenericDao<Thumbup, Integer> {
	
	public List<Thumbup> findNewestOrderByTime(String name, Object value, int startid, boolean joinUser, boolean joinInfo);
	
	public List<Thumbup> findOldestOrderByTime(String name, Object value, int endid, int offset, boolean joinUser, boolean joinInfo);
	
	public List<Thumbup> findNewestByTypeOrderByTime(String name, Object value, int type, int startid, boolean joinUser, boolean joinInfo);
	
	public List<Thumbup> findOldestByTypeOrderByTime(String name, Object value, int type, int endid, int offset, boolean joinUser, boolean joinInfo);
}
