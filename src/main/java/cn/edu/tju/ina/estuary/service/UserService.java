package cn.edu.tju.ina.estuary.service;

import java.util.List;

import cn.edu.tju.ina.estuary.domain.user.User;
import cn.edu.tju.ina.estuary.service.generic.GenericService;

public interface UserService extends GenericService<User, Integer> {
	
	User findByUsername(String username);
	User findByRealname(String realname);
	User findByEmail(String email);
	
	/**
	 * 获取认证用户
	 * @param start
	 * @param length
	 * @return
	 */
	List<User> findAuthOrgnizations(int start, int length);
}
