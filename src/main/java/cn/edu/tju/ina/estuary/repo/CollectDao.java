package cn.edu.tju.ina.estuary.repo;

import java.util.List;

import cn.edu.tju.ina.estuary.domain.user.Collect;
import cn.edu.tju.ina.estuary.repo.generic.GenericDao;

public interface CollectDao extends GenericDao<Collect, Integer> {
	
	public List<Collect> findNewestOrderByTime(String name, Object value, int startid, boolean joinUser, boolean joinInfo);
	
	public List<Collect> findOldestOrderByTime(String name, Object value, int endid, int offset, boolean joinUser, boolean joinInfo);
	
	public List<Collect> findNewestByTypeOrderByTime(String name, Object value, int type, int startid, boolean joinUser, boolean joinInfo);
	
	public List<Collect> findOldestByTypeOrderByTime(String name, Object value, int type, int endid, int offset, boolean joinUser, boolean joinInfo);
}
