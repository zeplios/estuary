package cn.edu.tju.ina.estuary.repo.generic;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.type.Type;

public interface GenericDao<T, ID extends Serializable> {
	
	static final String ID = "id";
	static final String ADDTIME = "addTime";

	T findById(ID id, boolean lock);

	T findById(ID id);

	T loadById(ID id);

	ID save(T entity);

	void update(T entity);
	
	void saveOrUpdate(T entity);

	void saveOrUpdateAll(Collection<T> entityCollection);

	void delete(T entity);

	void deleteAll(Collection<T> entityCollection);

	T merge(T entity);

	void refresh(T entity);

	void evict(T entity);

	void flush();

	T findByProperty(String name, Object value);

	T findByTwoProperty(String name1, Object value1, String name2, Object value2);
	
	List<T> findAll();

	List<T> findAll(String orderBy, boolean desc);
	
	List<T> findList(int start, int length);
	
	List<T> findList(int start, int length, String orderBy, boolean desc);
	
	List<T> findListByProperty(String name, Object value);
	
	List<T> findListByProperty(String name, Object value, int start, int length);
	
	List<T> findListByProperty(String name, Object value, int start, int length, String orderBy, boolean desc);
	
	List<T> findListByTwoProperty(String name1, Object value1, String name2, Object value2);
	
	List<T> findListByTwoProperty(String name1, Object value1, String name2,
			Object value2, int start, int length);
	
	List<T> findListByTwoProperty(String name1, Object value1, String name2,
			Object value2, int start, int length, String orderBy, boolean desc);

	List<T> findListByQuery(String queryString, int start, int length, String orderBy, boolean desc);

	List<T> findListByQuery(String queryString, Object[] values, Type[] types, 
			int start, int length, String orderBy, boolean desc);

	List<T> findListByQuery(String queryString, Object value, Type type, 
			int start, int length, String orderBy, boolean desc);

	List<T> findListByMap(Map<String, Object> params, 
			int start, int length, String orderBy, boolean desc);

	int findCount(Map<String, Object> params);

	String getNextAvaliableId(int start);
}
