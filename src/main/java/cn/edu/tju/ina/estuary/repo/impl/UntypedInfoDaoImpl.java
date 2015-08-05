package cn.edu.tju.ina.estuary.repo.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import cn.edu.tju.ina.estuary.domain.info.Info;
import cn.edu.tju.ina.estuary.repo.InfoDao;
import cn.edu.tju.ina.estuary.repo.generic.GenericDaoImpl;

public class UntypedInfoDaoImpl<T extends Info> extends GenericDaoImpl<T, Integer> implements InfoDao<T>
{
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllListOrderByTime(Object ... params) {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		if (params != null && params.length >= 2) {
			for (int i = 1 ; i < params.length ; i += 2) {
				criteria.add(Restrictions.eq(params[i-1].toString(), params[i]));
			}
		}
		criteria.add(Restrictions.eq("deleted", false));
		setOrderBy(criteria, ADDTIME, true);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findListByPageOrderByTime(int start, int length, Object ... params) {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		if (params != null && params.length >= 2) {
			for (int i = 1 ; i < params.length ; i += 2) {
				criteria.add(Restrictions.eq(params[i-1].toString(), params[i]));
			}
		}
		criteria.add(Restrictions.eq("deleted", false));
		setLimit(criteria, start, length);
		setOrderBy(criteria, ADDTIME, true);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findNewestByPageOrderByTime(int fmsg, Object ... params) {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		if (params != null && params.length >= 2) {
			for (int i = 1 ; i < params.length ; i += 2) {
				criteria.add(Restrictions.eq(params[i-1].toString(), params[i]));
			}
		}
		criteria.add(Restrictions.eq("deleted", false));
		criteria.add(Restrictions.gt("id", fmsg));
		setOrderBy(criteria, ADDTIME, true);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findOldestByPageOrderByTime(int lmsg, int offset, Object ... params) {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		if (params != null && params.length >= 2) {
			for (int i = 1 ; i < params.length ; i += 2) {
				criteria.add(Restrictions.eq(params[i-1].toString(), params[i]));
			}
		}
		criteria.add(Restrictions.eq("deleted", false));
		criteria.add(Restrictions.lt("id", lmsg));
		criteria.setMaxResults(offset);
		setOrderBy(criteria, ADDTIME, true);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> searchByOneProperty(String fieldName, String value) {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		criteria.add(Restrictions.ilike(fieldName, value, MatchMode.ANYWHERE));
		criteria.add(Restrictions.eq("deleted", false));
		setOrderBy(criteria, ADDTIME, true);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> searchByTwoDisjunction(String fieldName1, String fieldName2, String value) {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		criteria.add(Restrictions.disjunction()
				.add(Restrictions.ilike(fieldName1, value, MatchMode.ANYWHERE))
				.add(Restrictions.ilike(fieldName2, value, MatchMode.ANYWHERE)));
		criteria.add(Restrictions.ne("type", 6));
		criteria.add(Restrictions.eq("deleted", false));
		setOrderBy(criteria, ADDTIME, true);
		return criteria.list();
	}
}
