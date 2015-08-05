package cn.edu.tju.ina.estuary.mvc.rest;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.tju.ina.estuary.util.ResponseBuilder;

@Controller("homeRestController")
@RequestMapping("/rest")
public class HomeController {

	@RequestMapping(value="/needsignin", method={RequestMethod.GET, RequestMethod.POST})
	public Map<String, Object> needSignin(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
		ResponseBuilder resp = new ResponseBuilder(ResponseBuilder.NEED_SIGN_IN);
		return resp.build();
	}
	
	@RequestMapping(value="/accessdenied", method={RequestMethod.GET, RequestMethod.POST})
	public Map<String, Object> accessDenied(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
		ResponseBuilder resp = new ResponseBuilder(ResponseBuilder.ACCESS_DENIED);
		return resp.build();
	}
}
