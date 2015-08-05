package cn.edu.tju.ina.estuary.repo.generic;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import cn.edu.tju.ina.estuary.util.ReflectUtils;

@SuppressWarnings("unchecked")
public class GenericDaoImpl<T, ID extends Serializable> implements
		GenericDao<T, ID> {

	protected Class<T> persistentClass;

	@Autowired
	protected SessionFactory sessionFactory;

	public GenericDaoImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public T findById(ID id) {
		return (T) getCurrentSession().get(persistentClass, id);
	}

	@SuppressWarnings("deprecation")
	@Override
	public T findById(ID id, boolean lock) {
		if (lock) {
			return (T) getCurrentSession().get(persistentClass, id,
					LockMode.UPGRADE);
		} else
			return findById(id);
	}

	@Override
	public T loadById(ID id) {
		return (T) getCurrentSession().load(persistentClass, id);
	}

	@Override
	public ID save(T entity) {
		return (ID) getCurrentSession().save(entity);
	}

	@Override
	public void update(T entity) {
		Session session = getCurrentSession();
		if (session.contains(entity)) {
			getCurrentSession().update(entity);
		} else {
			updateNotNull(entity);
		}
	}
	
	private void updateNotNull(T entity) {
		// entity is not in session
		T entityInSession = null;
		Method [] ms = entity.getClass().getMethods();
		for (Method m : ms) {
			if (m.isAnnotationPresent(Id.class)) {
				ID idValue = (ID) ReflectUtils.invokeMethod(m, entity, (Object[])null);
				if (idValue == null) {
					return;
				}
				entityInSession = loadById(idValue);
				if (entityInSession == null) {
					return;
				}
				break;
			}
		}
		for (Method m : ms) {
			if (m.isAnnotationPresent(Id.class) || m.isAnnotationPresent(Transient.class)) {
				continue;
			}
			String methodName = m.getName();
			if (!methodName.startsWith("get") && !methodName.startsWith("is") || "getClass".equals(methodName)) {
				continue;
			}
			Object value = ReflectUtils.invokeMethod(m, entity, (Object[])null);
			if (value == null) {
				continue;
			}
			String setterName;
			if (methodName.startsWith("get")) {
				setterName = methodName.replace("get", "set");
			} else {
				setterName = methodName.replace("is", "set");
			}
			Method setter = ReflectUtils.findMethod(entity.getClass(), setterName);
			ReflectUtils.invokeMethod(setter, entityInSession, value);
		}
		getCurrentSession().update(entityInSession);
	}

	@Override
	public void saveOrUpdate(T entity) {
		getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<T> entityCollection) {
		getCurrentSession().saveOrUpdate(entityCollection);
	}

	@Override
	public void delete(T entity) {
		getCurrentSession().delete(entity);
	}

	@Override
	public void deleteAll(Collection<T> entityCollection) {
		getCurrentSession().delete(entityCollection);
	}

	@Override
	public T merge(T entity) {
		return (T) getCurrentSession().merge(entity);
	}

	@Override
	public void refresh(T entity) {
		getCurrentSession().refresh(entity);
	}

	@Override
	public void evict(T entity) {
		getCurrentSession().evict(entity);
	}

	@Override
	public void flush() {
		getCurrentSession().flush();
	}
	
	@Override
	public T findByProperty(String name, Object value) {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		criteria.add(Restrictions.eq(name, value));
		return (T) criteria.uniqueResult();
	}

	@Override
	public T findByTwoProperty(String name1, Object value1, String name2, Object value2) {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		criteria.add(Restrictions.eq(name1, value1));
		criteria.add(Restrictions.eq(name2, value2));
		return (T) criteria.uniqueResult();
	}

	@Override
	public List<T> findAll() {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		return criteria.list();
	}

	@Override
	public List<T> findAll(String orderBy, boolean desc) {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		setOrderBy(criteria, orderBy, desc);
		return criteria.list();
	}
	
	@Override
	public List<T> findList(int start, int length) {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		setLimit(criteria, start, length);
		return criteria.list();
	}
	
	@Override
	public List<T> findList(int start, int length, String orderBy, boolean desc) {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		setLimit(criteria, start, length);
		setOrderBy(criteria, orderBy, desc);
		return criteria.list();
	}

	@Override
	public List<T> findListByProperty(String name, Object value) {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		criteria.add(Restrictions.eq(name, value));
		return criteria.list();
	}

	@Override
	public List<T> findListByProperty(String name, Object value, int start, int length) {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		criteria.add(Restrictions.eq(name, value));
		setLimit(criteria, start, length);
		return criteria.list();
	}

	@Override
	public List<T> findListByProperty(String name, Object value, int start, int length, String orderBy, boolean desc) {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		criteria.add(Restrictions.eq(name, value));
		setLimit(criteria, start, length);
		setOrderBy(criteria, orderBy, desc);
		return criteria.list();
	}

	@Override
	public List<T> findListByTwoProperty(String name1, Object value1, String name2, Object value2) {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		criteria.add(Restrictions.eq(name1, value1));
		criteria.add(Restrictions.eq(name2, value2));
		return criteria.list();
	}

	@Override
	public List<T> findListByTwoProperty(String name1, Object value1, String name2,
			Object value2, int start, int length) {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		criteria.add(Restrictions.eq(name1, value1));
		criteria.add(Restrictions.eq(name2, value2));
		setLimit(criteria, start, length);
		return criteria.list();
	}

	@Override
	public List<T> findListByTwoProperty(String name1, Object value1, String name2,
			Object value2, int start, int length, String orderBy, boolean desc) {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		criteria.add(Restrictions.eq(name1, value1));
		criteria.add(Restrictions.eq(name2, value2));
		setLimit(criteria, start, length);
		setOrderBy(criteria, orderBy, desc);
		return criteria.list();
	}

	@Override
	public List<T> findListByQuery(String queryString, int start, int length, String orderBy, boolean desc) {
//		return getCurrentSession().createQuery("from xxx").list();
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		criteria.add(Restrictions.sqlRestriction(queryString));
		setLimit(criteria, start, length);
		setOrderBy(criteria, orderBy, desc);
		return criteria.list();
	}

	@Override
	public List<T> findListByQuery(String queryString, Object[] values,
			Type[] types, int start, int length, String orderBy, boolean desc) {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		criteria.add(Restrictions.sqlRestriction(queryString, values, types));
		setLimit(criteria, start, length);
		setOrderBy(criteria, orderBy, desc);
		return criteria.list();
	}

	@Override
	public List<T> findListByQuery(String queryString, Object value, Type type, 
			int start, int length, String orderBy, boolean desc) {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		criteria.add(Restrictions.sqlRestriction(queryString, value, type));
		setLimit(criteria, start, length);
		setOrderBy(criteria, orderBy, desc);
		return criteria.list();
	}

	@Override
	public List<T> findListByMap(Map<String, Object> params, 
			int start, int length, String orderBy, boolean desc) {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		if (params != null) {
			criteria.add(Restrictions.allEq(params));
		}
		setLimit(criteria, start, length);
		setOrderBy(criteria, orderBy, desc);
		return criteria.list();
	}

	@Override
	public int findCount(Map<String, Object> params) {
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		if (params != null) {
			criteria.add(Restrictions.allEq(params));
		}
		Long total = (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();
		return Integer.parseInt(Long.toString(total));
	}

	@Override
	public String getNextAvaliableId(int start) {
		// TODO Auto-generated method stub
		String tablename = persistentClass.getSimpleName();
		String sql = "SELECT (CASE WHEN EXISTS(SELECT * FROM " + tablename
				+ " WHERE id=" + start + ")" + "THEN MIN(id)+1 ELSE " + start
				+ " END) FROM " + tablename + " WHERE id NOT IN"
				+ "(SELECT id-1 FROM " + tablename + ") AND id>=" + start;
		;
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		criteria.add(Restrictions.sqlRestriction(sql));
		return null;
	}
	
	protected static void setOrderBy(Criteria criteria, String orderBy, boolean desc) {
		if (orderBy != null && !StringUtils.isEmpty(orderBy)) {
			if (desc) {
				criteria.addOrder(Order.desc(orderBy));
			} else {
				criteria.addOrder(Order.asc(orderBy));
			}
		}
	}
	
	protected static void setLimit(Criteria criteria, int start, int length) {
		if (length != 0) {
			criteria.setFirstResult(start).setMaxResults(length);
		}
	}

}
