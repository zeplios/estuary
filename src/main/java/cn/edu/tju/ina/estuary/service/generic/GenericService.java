package cn.edu.tju.ina.estuary.service.generic;

import java.io.Serializable;


public interface GenericService<T, ID extends Serializable> {
	ID save(T entity);
	void saveOrUpdate(T entity);
	void delete(T c);
	void update(T entity);
	void evict(T entity);
	void flush();
	T loadById(ID id);
}
