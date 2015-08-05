package cn.edu.tju.ina.estuary.repo.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.tju.ina.estuary.domain.user.Notice;
import cn.edu.tju.ina.estuary.repo.NoticeDao;
import cn.edu.tju.ina.estuary.repo.generic.GenericDaoImpl;

@Repository
public class NoticeDaoImpl extends GenericDaoImpl<Notice, Integer> implements NoticeDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Notice> findNewestOrderByTime(String name, Object value, int startid, boolean joinInfo) {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		setFetchMode(criteria, joinInfo);
		criteria.add(Restrictions.eq(name, value));
		criteria.add(Restrictions.gt("id", startid));
		setOrderBy(criteria, ADDTIME, true);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Notice> findOldestOrderByTime(String name, Object value, int endid, int offset, boolean joinInfo) {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		setFetchMode(criteria, joinInfo);
		criteria.add(Restrictions.eq(name, value));
		criteria.add(Restrictions.lt("id", endid));
		criteria.setMaxResults(offset);
		setOrderBy(criteria, ADDTIME, true);
		return criteria.list();
	}
	
	private void setFetchMode(Criteria criteria, boolean joinInfo) {
		if (joinInfo) {
			criteria.setFetchMode("info", FetchMode.JOIN);
		}
	}
}
