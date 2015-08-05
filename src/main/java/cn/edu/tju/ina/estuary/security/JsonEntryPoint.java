package cn.edu.tju.ina.estuary.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class JsonEntryPoint implements AuthenticationEntryPoint {
	private String needSigninUrl = "/";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException e) throws IOException, ServletException {
        request.getRequestDispatcher(needSigninUrl).include(request, response);
    }

    public void setNeedSigninUrl(String needSigninUrl) {
        this.needSigninUrl = needSigninUrl;
    }
}
