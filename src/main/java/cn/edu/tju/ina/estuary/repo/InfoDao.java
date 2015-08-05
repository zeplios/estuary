package cn.edu.tju.ina.estuary.repo;

import java.util.List;

import cn.edu.tju.ina.estuary.domain.info.Info;
import cn.edu.tju.ina.estuary.repo.generic.GenericDao;

public interface InfoDao<T extends Info> extends GenericDao<T, Integer> {
	List<T> findAllListOrderByTime(Object ... params);
	List<T> findListByPageOrderByTime(int start, int offset, Object ... params);
	List<T> findNewestByPageOrderByTime(int fmsg, Object ... params);
	List<T> findOldestByPageOrderByTime(int lmsg, int offset, Object ... params);
	
	List<T> searchByOneProperty(String fieldName, String value);
	List<T> searchByTwoDisjunction(String fieldName1, String fieldName2, String value);
}