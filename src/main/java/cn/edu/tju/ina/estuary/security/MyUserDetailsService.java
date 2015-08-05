package cn.edu.tju.ina.estuary.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import cn.edu.tju.ina.estuary.domain.user.User;
import cn.edu.tju.ina.estuary.service.UserService;

public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User u = userService.findByUsername(username);
		// user username as a password used in SpringSecurity
		EssentialUser eu = new EssentialUser(u, username);
		return eu;
	}

}
