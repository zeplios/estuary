package cn.edu.tju.ina.estuary.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import cn.edu.tju.ina.estuary.domain.user.User;
import cn.edu.tju.ina.estuary.service.UserService;
import cn.edu.tju.ina.estuary.util.Constants;

public class AfterAuthSuccess extends SimpleUrlAuthenticationSuccessHandler {
	
	@Autowired
    private UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		RequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		HttpSession session = request.getSession();
		SecurityContext sc = SecurityContextHolder.getContext();
	    String userName = sc.getAuthentication().getName();
    	User u = userService.findByUsername(userName);
    	session.setAttribute(Constants.USER_ID_IN_SESSION, u.getId());
    	session.setAttribute(Constants.USER_IN_SESSION, u);
		
        if (savedRequest == null) {
        	// if click login to open login page, savedRequest will be null.
            super.onAuthenticationSuccess(request, response, authentication);
            return;
        }
        clearAuthenticationAttributes(request);
        String targetUrl = savedRequest.getRedirectUrl();
        if(targetUrl != null && "".equals(targetUrl)){
        	super.onAuthenticationSuccess(request, response, authentication);
            return; 
        }
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}

}
