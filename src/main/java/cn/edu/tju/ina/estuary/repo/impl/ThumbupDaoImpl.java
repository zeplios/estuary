package cn.edu.tju.ina.estuary.repo.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.tju.ina.estuary.domain.user.Thumbup;
import cn.edu.tju.ina.estuary.repo.ThumbupDao;
import cn.edu.tju.ina.estuary.repo.generic.GenericDaoImpl;

@Repository
public class ThumbupDaoImpl extends GenericDaoImpl<Thumbup, Integer> implements ThumbupDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Thumbup> findNewestOrderByTime(String name, Object value, int startid, boolean joinUser, boolean joinInfo) {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		setFetchMode(criteria, joinUser, joinInfo);
		criteria.add(Restrictions.eq(name, value));
		criteria.add(Restrictions.gt("id", startid));
		setOrderBy(criteria, ADDTIME, true);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Thumbup> findOldestOrderByTime(String name, Object value, int endid, int offset, boolean joinUser, boolean joinInfo) {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		setFetchMode(criteria, joinUser, joinInfo);
		criteria.add(Restrictions.eq(name, value));
		criteria.add(Restrictions.lt("id", endid));
		criteria.setMaxResults(offset);
		setOrderBy(criteria, ADDTIME, true);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Thumbup> findNewestByTypeOrderByTime(String name, Object value, int type, int startid, boolean joinUser, boolean joinInfo) {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		setFetchMode(criteria, joinUser, joinInfo);
		criteria.add(Restrictions.eq(name, value));
		criteria.add(Restrictions.gt("id", startid));
		criteria.add(Restrictions.eq("type.id", type));
		setOrderBy(criteria, ADDTIME, true);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Thumbup> findOldestByTypeOrderByTime(String name, Object value, int type, int endid, int offset, boolean joinUser, boolean joinInfo) {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		setFetchMode(criteria, joinUser, joinInfo);
		criteria.add(Restrictions.eq(name, value));
		criteria.add(Restrictions.lt("id", endid));
		criteria.add(Restrictions.eq("type.id", type));
		criteria.setMaxResults(offset);
		setOrderBy(criteria, ADDTIME, true);
		return criteria.list();
	}
	
	private void setFetchMode(Criteria criteria, boolean joinUser, boolean joinInfo) {
		if (joinUser) {
			criteria.setFetchMode("user", FetchMode.JOIN);
		}
		if (joinInfo) {
			criteria.setFetchMode("info", FetchMode.JOIN);
		}
	}
}
