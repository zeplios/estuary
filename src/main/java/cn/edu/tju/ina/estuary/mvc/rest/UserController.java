package cn.edu.tju.ina.estuary.mvc.rest;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.tju.ina.estuary.doc.annotation.Description;
import cn.edu.tju.ina.estuary.doc.annotation.Param;
import cn.edu.tju.ina.estuary.doc.annotation.Return;
import cn.edu.tju.ina.estuary.doc.annotation.Title;
import cn.edu.tju.ina.estuary.domain.info.Info;
import cn.edu.tju.ina.estuary.domain.user.User;
import cn.edu.tju.ina.estuary.security.SSOLoginLogic;
import cn.edu.tju.ina.estuary.security.SecurityUtils;
import cn.edu.tju.ina.estuary.service.CollectService;
import cn.edu.tju.ina.estuary.service.InfoService;
import cn.edu.tju.ina.estuary.service.ThumbupService;
import cn.edu.tju.ina.estuary.service.UserService;
import cn.edu.tju.ina.estuary.util.FileHelper;
import cn.edu.tju.ina.estuary.util.PoToVo;
import cn.edu.tju.ina.estuary.util.ResponseBuilder;

@Controller("userRestController")
@RequestMapping("/rest/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	@Autowired
	@Qualifier("org.springframework.security.authenticationManager")
	protected AuthenticationManager authenticationManager;
	@Autowired
	private SSOLoginLogic ssoLogin;
	
	@Autowired
    private InfoService infoService;
    @Autowired
    private CollectService collectService;
    @Autowired
    private ThumbupService thumbupService;

	@Return("{'errcode':0, 'errmsg':'xxx'�� 'user':{fields in <a href='#vo-User'>User</a>}}")
	@Title("�û���¼")
	@Description("��Ϣǽ�û���¼������ǵ�һ����У԰����¼�����صĴ����벻��0������13������ʾ���ǵ�һ�ΰ�")
	@Param(name = { "username", "password" }, desc = { "�û���", "ԭʼ����" })
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> login(@RequestParam String username,
			@RequestParam String password, HttpServletRequest request) {

		ResponseBuilder resp = new ResponseBuilder();
		User u = null;
		
		// if the user has been signed in
		UserDetails currentUser = getCurrentUser();
		if (currentUser != null && currentUser.getUsername().equals(username)) {
			u = userService.findByUsername(username);
			// TODO u is impossible to be true in theory
			resp.code(ResponseBuilder.SUCCESS);
		} else {
			// login to SSO
			if (ssoLogin.login(username, password)) {
				u = userService.findByUsername(username);
				if (u != null) {
					resp.code(ResponseBuilder.SUCCESS);
				} else {
					u = new User();
					u.setUsername(username);
					userService.save(u);
					u = userService.findByUsername(username);
					resp.code(ResponseBuilder.BOUND_FIRST_TIME);
				}
			} else {
				// detailed error message can be get from the response of SSO
				resp.code(ResponseBuilder.USERNAME_OR_PASSWORD_ERROR);
				return resp.build();
			}
		}
		
		if (u != null) {
			SecurityUtils.setAuthInSpringSecuity(authenticationManager, 
					username, username, request);
			resp.add("user", PoToVo.transformUserToFull(u));
			return resp.build();
		} else {
			// can't happen
			return resp.build();
		}
	}
	
	@Return("{'errcode':0, 'errmsg':'xxx'}")
	@Title("�û�������Ϣ����")
	@Description("�����û�ͷ��֮���������Ϣ")
	@Param(name = { "xxx" }, desc = { "fields in <a href='#vo-User'>User</a>" })
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(@ModelAttribute User user) {
		ResponseBuilder resp = new ResponseBuilder();
		resp.code(ResponseBuilder.SUCCESS);
		user.setId(getCurrentUserId());
		userService.update(user);
		return resp.build();
	}
	
	@Return("{'errcode':0, 'errmsg':'xxx', 'path':'avatar/xxx.jpg'}")
	@Title("�û��ϴ�����ͷ��")
	@Description("�����û�ͷ��")
	@Param(name = { "avatar" }, desc = { "�û���ͷ��" })
	@RequestMapping(value = "/avatar", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> avatar(@RequestParam MultipartFile avatar, HttpSession session) {
		ResponseBuilder resp = new ResponseBuilder();
		int uid = getCurrentUserId();
		String filename = "info_avatar_100" + uid + ".jpg";
		boolean success = new FileHelper(session).save(avatar, "avatar", filename);
		if (success) {
			User u = new User();
			u.setId(uid);
			u.setAvatar("avatar/" + filename);
			userService.update(u);
			resp.code(ResponseBuilder.SUCCESS);
			resp.add("path", "avatar/" + filename);
		} else {
			resp.code(ResponseBuilder.UNKOWN_ERROR);
			resp.add("path", "avatar/default.jpg");
		}
		return resp.build();
	}
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	@ResponseBody
	@Return("{'errcode':0, 'errmsg':'xxx'�� 'infos':[{<a href='#vo-FullActivity'>Activity</a>},{<a href='#vo-FullRecruit'>Recruit</a>},...]}")
    @Title("��ȡ�ҷ�������Ϣ�б�")
    @Description("��ȡ����һ�ַ�������Ϣ���б����ҵ���ᣬ�ҵĶ�����Ʒ��")
    @Param(name={"type", "page/fmsg/lmsg"}, desc={"��Ϣ���ͣ�Activity=1��Recruit=2��Lost=3��Market=4��Album=5��Photo=6", 
    		"�ڼ�ҳ��0��ʾ��ȡȫ����/��ȡ��fmsg���µ�/��ȡ��lmsg���ɵ�"})
	public Map<String, Object> listInfo(@RequestParam int type, 
    		@RequestParam(required=false, defaultValue="-1") int page, 
    		@RequestParam(required=false, defaultValue="-1") int fmsg, 
    		@RequestParam(required=false, defaultValue="-1") int lmsg) {
		int uid = getCurrentUserId();
		ResponseBuilder resp = new ResponseBuilder();
    	
    	List<Info> infos = null;
    	if (page > 0) {
    		infos = infoService.findInfosByUserByPage(type, (page-1)*10, 10, uid);
    	} else if (page == 0) {
    		infos = infoService.findAllInfosByUser(type, uid);
    	} else if (fmsg >= 0) {
    		infos = infoService.findNewestByUser(type, fmsg, uid);
    	} else if (lmsg >= 0) {
    		infos = infoService.findOldestByUser(type, lmsg, 10, uid);
    	} else {
    		resp.code(ResponseBuilder.LOST_PARAMETER);
    		return resp.build();
    	}
    	resp.code(ResponseBuilder.SUCCESS);
    	if (uid > -1) {
    		for (Info i : infos) {
        		i.setHasCollected(collectService.getCollected(uid, i.getId()) != null);
        		i.setHasThumbuped(thumbupService.getThumbuped(uid, i.getId()) != null);
        	}
    	}
    	resp.add("infos", PoToVo.transformInfoListToSimple(infos));
        return resp.build();
	}
}
