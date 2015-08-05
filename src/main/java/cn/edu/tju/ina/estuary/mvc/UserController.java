package cn.edu.tju.ina.estuary.mvc;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.tju.ina.estuary.domain.user.User;
import cn.edu.tju.ina.estuary.service.UserService;
import cn.edu.tju.ina.estuary.util.Constants;
import cn.edu.tju.ina.estuary.util.FileHelper;
import cn.edu.tju.ina.estuary.util.ResponseBuilder;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	@Qualifier("org.springframework.security.authenticationManager")
	protected AuthenticationManager authenticationManager;

	/** 跳转到登录界面的action */
	@RequestMapping(value = "/login")
	public String login() {
		return "user/login";
	}

	/** 跳转到注册界面的action */
	@RequestMapping(value = "/before_register")
	public String beforeRegister(Model model) {
		return "user/register";
	}

	/** 跳转到个人中心的action */
	@RequestMapping(value = "/center/{type}")
	public String center(@PathVariable String type, Model model, HttpSession session) {
		model.addAttribute("user", session.getAttribute(Constants.USER_IN_SESSION));
		model.addAttribute("pageType", type);
		return "user/center";
	}
	
	@RequestMapping(value = "/upload/{type}")
	public String login(@PathVariable String type, Model model) {
		StringBuilder sb = new StringBuilder(type);
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		sb.insert(0, Constants.ROOT_PACKAGE + ".domain.info.");
		String className = sb.toString();
		try {
			model.addAttribute("info", Class.forName(className).newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		model.addAttribute("pageType", type);
		return "user/upload";
	}
	
	/** 处理头像上传之后用户框选了范围的action */
	@RequestMapping(value = "/avatar")
	public String avatar(@RequestParam String src, @RequestParam int x, 
			@RequestParam int y, @RequestParam int w, @RequestParam int h, 
			Model model, HttpSession session) {
		int uid = (Integer) session.getAttribute(Constants.USER_ID_IN_SESSION);
		
		String filename = src.substring(src.lastIndexOf("/") + 1);
		boolean success = new FileHelper(session).transformImage(
					"avatar/temp", filename, "avatar", filename, x, y, w, h);
		if (success) {
			User u = userService.loadById(uid);
			u.setAvatar(filename);
			userService.update(u);
			session.setAttribute(Constants.USER_IN_SESSION, u);
		}
		
		return "redirect:/user/center/config";
	}
	
	/** 处理异步更新的action */
	/**
	 * 移到/rest/user中
	 */
//	@RequestMapping(value = "/asyn/update", method = { RequestMethod.POST,
//			RequestMethod.PUT }, produces = "application/json;charset=UTF-8")
//	public @ResponseBody
//	Map<String, Object> update(@ModelAttribute User user, HttpSession session) {
//		ResponseBuilder resp = new ResponseBuilder();
//		resp.code(ResponseBuilder.SUCCESS);
//		userService.update(user);
//		session.setAttribute(Constants.USER_IN_SESSION, userService.findById(user.getId()));
//		return resp.build();
//	}

	/** 处理头像异步上传的action */
	@RequestMapping(value = "/asyn/avatar")
	public @ResponseBody
	Map<String, Object> avatar(@RequestParam MultipartFile Filedata,
			Model model, HttpSession session) {
		ResponseBuilder resp = new ResponseBuilder();
		try {
			int uid = (Integer) session.getAttribute(Constants.USER_ID_IN_SESSION);
			String filename = "info_avatar_100" + uid + ".jpg";
			BufferedImage bi = ImageIO.read(Filedata.getInputStream());
			if (bi.getWidth() < 100 || bi.getHeight() < 100) {
				resp.add("filename", filename);
				resp.code(ResponseBuilder.ERROR_FILESIZE);
			} else {
				boolean success = new FileHelper(session).save(Filedata, "avatar/temp", filename);
				if (success) {
					resp.code(ResponseBuilder.SUCCESS);
					resp.add("filename", "temp/" + filename);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resp.build();
	}

}
