package cn.edu.tju.ina.estuary.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.tju.ina.estuary.domain.user.User;
import cn.edu.tju.ina.estuary.repo.UserDao;
import cn.edu.tju.ina.estuary.service.UserService;
import cn.edu.tju.ina.estuary.service.generic.GenericServiceImpl;

@Service("userService")
@Transactional
public class UserServiceImpl extends GenericServiceImpl<User, Integer> implements UserService {
	
	private UserDao userDao;
	
	@Autowired
	public UserServiceImpl(UserDao userDao) {
		super(userDao);
		this.userDao = userDao;
	}

	@Override
	public Integer save(User u) {
		u.setAddTime(new Timestamp(System.currentTimeMillis()));
		if (u.getNickname() == null || "".equals(u.getNickname())) {
			u.setNickname(u.getUsername());
		}
		int id = userDao.save(u);
		userDao.evict(u);
		return id;
	}

	@Override
	public User findByEmail(String email) {
		return userDao.findByProperty("email", email);
	}
	
	@Override
	public User findByUsername(String username) {
		return userDao.findByProperty("username", username);
	}

	@Override
	public User findByRealname(String realname) {
		return userDao.findByProperty("realname", realname);
	}

	@Override
	public List<User> findAuthOrgnizations(int start, int length) {
		return userDao.findByPageByRandom("auth", 1, start, length);
	}
}
