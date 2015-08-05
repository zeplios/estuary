package cn.edu.tju.ina.estuary.mvc.rest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import cn.edu.tju.ina.estuary.security.EssentialUser;

public class BaseController {
	
	public UserDetails getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth == null) {
			return null;
		}
		Object obj = auth.getPrincipal();
		if (obj == null || obj instanceof String) {
			return null;
		}
		return (EssentialUser) obj;
	}
	
	public int getCurrentUserId() {
		EssentialUser eu = (EssentialUser) getCurrentUser();
		return (eu == null) ? -1 : eu.getId();
	}
	
}
