package cn.edu.tju.ina.estuary.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

public class SecurityUtils {
	
	/**
	 * 我们的登录都是spring做的，注册之后要自动登录，就需要模拟spring做的一系列动作
	 * @param authenticationManager spring的认证管理器
	 * @param username
	 * @param password
	 * @param request
	 */
	public static boolean setAuthInSpringSecuity(AuthenticationManager authenticationManager, 
			String username, String password, HttpServletRequest request) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				username, password);
		try {
			token.setDetails(new WebAuthenticationDetails(request));
			Authentication authenticatedUser = authenticationManager
					.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(
					authenticatedUser);
			request.getSession()
					.setAttribute(
							HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
							SecurityContextHolder.getContext());
			return true;
		} catch (AuthenticationException e) {
			System.out.println("Authentication failed: " + e.getMessage());
			return false;
		}
	}
}
