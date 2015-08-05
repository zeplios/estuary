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
	 * ���ǵĵ�¼����spring���ģ�ע��֮��Ҫ�Զ���¼������Ҫģ��spring����һϵ�ж���
	 * @param authenticationManager spring����֤������
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
