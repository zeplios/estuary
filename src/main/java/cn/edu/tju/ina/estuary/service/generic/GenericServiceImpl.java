package cn.edu.tju.ina.estuary.service.generic;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.tju.ina.estuary.repo.generic.GenericDao;

@Transactional
public class GenericServiceImpl<T, ID extends Serializable> implements GenericService<T, ID> {
	
	private GenericDao<T, ID> genericDao;
	
	public GenericServiceImpl(GenericDao<T, ID> genericDao) {
		this.genericDao = genericDao;
	}

	@Override
	public ID save(T entity) {
		return genericDao.save(entity);
	}

	@Override
	public void saveOrUpdate(T entity) {
		genericDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(T entity) {
		genericDao.delete(entity);
	}

	@Override
	public void update(T entity) {
		genericDao.update(entity);
	}

	@Override
	public void evict(T entity) {
		genericDao.evict(entity);
	}

	@Override
	public void flush() {
		genericDao.flush();
	}

	@Override
	public T loadById(ID id) {
		return genericDao.findById(id);
	}

}
