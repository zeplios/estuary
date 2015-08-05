package cn.edu.tju.ina.estuary.security;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.edu.tju.ina.estuary.domain.user.User;
import cn.edu.tju.ina.estuary.service.UserService;

public class EncodePassword implements Filter {
	
	UserService userService;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
//		String password = request.getParameter("j_password");
//		User u = userService.findByUsername(request.getParameter("j_username"));
//		if (u == null) {
//			chain.doFilter(request, response);
//			return;
//		}
//		String md5pw = PasswordUtil.encodeWithMd5pw(password, u.getId(), u.getUsername());
//		@SuppressWarnings("unchecked")
//		Map<String, String[]> m = new HashMap<String, String[]>(request.getParameterMap());
//		m.put("j_password", new String[]{md5pw});
//		request = new ParameterRequestWrapper((HttpServletRequest)request, m);
		String username = request.getParameter("j_username");
		String password = request.getParameter("j_password");
		SSOLoginLogic sso = new SSOLoginLogic();
		if (sso.login(username, password)) {
			User u = userService.findByUsername(username);
			if (u == null) {
				u = new User();
				u.setUsername(username);
				userService.save(u);
			}
			@SuppressWarnings("unchecked")
			Map<String, String[]> m = new HashMap<String, String[]>(request.getParameterMap());
			m.put("j_password", new String[]{username});
			request = new ParameterRequestWrapper((HttpServletRequest)request, m);
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		/**
		 * Filter����Servlet����������������Spring������������Ҫ������ķ�������Spring����֪�������Ĵ��ڣ���������ע��Service
		 */
		ServletContext servletContext = config.getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(servletContext);
//		AutowireCapableBeanFactory autowireCapableBeanFactory = wac.getAutowireCapableBeanFactory();
//		autowireCapableBeanFactory.configureBean(this, "userService");
		this.userService = (UserService) wac.getBean("userService");
	}
	
	class ParameterRequestWrapper extends HttpServletRequestWrapper {  
		  
	    private Map<String, String[]> params;  
	  
	    public ParameterRequestWrapper(HttpServletRequest request,  
	            Map<String, String[]> newParams) {  
	        super(request);  
	        this.params = newParams;  
	        // RequestDispatcher.forward parameter  
	        addQueryStringParameter(request);  
	    }  
	  
	    @Override  
	    public String getParameter(String name) {  
	        String result = "";  
	          
	        Object v = params.get(name);  
	        if (v == null) {  
	            result = null;  
	        } else if (v instanceof String[]) {  
	            String[] strArr = (String[]) v;  
	            if (strArr.length > 0) {  
	                result =  strArr[0];  
	            } else {  
	                result = null;  
	            }  
	        } else if (v instanceof String) {  
	            result = (String) v;  
	        } else {  
	            result =  v.toString();  
	        }  
	          
	        return result;  
	    }  
	  
	    @Override  
	    public Map<String, String[]> getParameterMap() {  
	        return params;  
	    }  
	  
	    @Override  
	    public Enumeration<String> getParameterNames() {  
	        return new Vector<String>(params.keySet()).elements();  
	    }  
	  
	    @Override  
	    public String[] getParameterValues(String name) {  
	        String[] result = null;  
	        Object v = params.get(name);  
	        if (v == null) {  
	            result =  null;  
	        } else if (v instanceof String[]) {  
	            result =  (String[]) v;  
	        } else if (v instanceof String) {  
	            result =  new String[] { (String) v };  
	        } else {  
	            result =  new String[] { v.toString() };  
	        }  
	        return result;  
	    }
	    /**
	     * ���������Ҫ�Ǵ���forward�����ʱ��url���queryStringû����ӵ�ParameterMap������
	     * @param req
	     */
	    private void addQueryStringParameter(HttpServletRequest req) {  
	        String queryString = req.getQueryString();  
	        if (queryString != null && queryString.trim().length() > 0) {  
	            String[] params = queryString.split("&");  
	            for (int i = 0; i < params.length; i++) {  
	                int splitIndex = params[i].indexOf("=");  
	                if (splitIndex == -1) {  
	                    continue;  
	                }  
	                String key = params[i].substring(0, splitIndex);  
	                if (!this.params.containsKey(key)) {  
	                    if (splitIndex < params[i].length()) {  
	                        String value = params[i].substring(splitIndex + 1);  
	                        this.params.put(key, new String[] { value });  
	                    }  
	                }  
	            }  
	        }
	    }  
	  
	}

}
