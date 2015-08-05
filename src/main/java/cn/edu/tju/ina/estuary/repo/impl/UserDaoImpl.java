package cn.edu.tju.ina.estuary.repo.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.edu.tju.ina.estuary.domain.user.User;
import cn.edu.tju.ina.estuary.repo.UserDao;
import cn.edu.tju.ina.estuary.repo.generic.GenericDaoImpl;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao
{
	@Override
	public List<User> findByPageByRandom(String property, Object value, int start, int length) {
		// TODO make the query random for future
		return findListByProperty(property, value, start, length, ADDTIME, true);
	}
}
