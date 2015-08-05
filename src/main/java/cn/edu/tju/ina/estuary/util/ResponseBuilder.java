package cn.edu.tju.ina.estuary.util;

import java.util.HashMap;
import java.util.Map;

public class ResponseBuilder {
	public static final int UNKOWN_ERROR = -2;
	public static final int NEED_SIGN_IN = -1;
	public static final int SUCCESS = 0;
	
	public static final int USERNAME_ERROR = 1;
	public static final int PASSWORD_ERROR = 2;
	public static final int EMAIL_ERROR = 3;
	public static final int REPEATED_USERNAME = 4;
	public static final int REPEATED_EMAIL = 5;
	public static final int USERNAME_OR_PASSWORD_ERROR = 12;
	public static final int BOUND_FIRST_TIME = 13;
	
	public static final int LOST_PARAMETER = 100;
	public static final int HAVE_COLLECTED = 101;
	public static final int NON_COLLECTED = 102;
	public static final int HAVE_THUMBUPED = 106;
	public static final int NON_THUMBUPED = 107;
	public static final int NO_COMMENT_PUBLISHED = 109;
	public static final int ERROR_FILESIZE = 200;
	public static final int ACCESS_DENIED = 403;
	private static Map<Integer, String> constants = new HashMap<Integer, String>();
	static {
		constants.put(UNKOWN_ERROR, "未知错误");
		constants.put(NEED_SIGN_IN, "请先登录");
		constants.put(SUCCESS, "请求成功");
		
		constants.put(USERNAME_ERROR, "用户名错误");
		constants.put(PASSWORD_ERROR, "密码错误");
		constants.put(EMAIL_ERROR, "邮箱错误");
		constants.put(REPEATED_USERNAME, "该用户名已被注册");
		constants.put(REPEATED_EMAIL, "该邮箱已被注册");
		constants.put(USERNAME_OR_PASSWORD_ERROR, "用户名或密码错误");
		constants.put(BOUND_FIRST_TIME, "第一次绑定校园网账号");
		
		constants.put(LOST_PARAMETER, "缺少参数");
		constants.put(HAVE_COLLECTED, "已收藏，不允许重复收藏");
		constants.put(NON_COLLECTED, "未收藏，无法删除功");
		constants.put(HAVE_THUMBUPED, "已赞过，不允许重复赞");
		constants.put(NON_THUMBUPED, "已赞过，无法取消");
		constants.put(NO_COMMENT_PUBLISHED, "该回复不存在或者不是您发布的");
		constants.put(ERROR_FILESIZE, "文件大小不符合要求");
		constants.put(ACCESS_DENIED, "您没有访问这个接口的权限");
	}
	
	private Map<String, Object> maps = new HashMap<String, Object>();
	
	public ResponseBuilder() {
	}
	
	public ResponseBuilder(int errcode) {
		maps.put("errcode", errcode);
		maps.put("errmsg", constants.get(errcode));
	}
	
	public void code(int errcode) {
		maps.put("errcode", errcode);
		maps.put("errmsg", constants.get(errcode));
	}
	
	public void add(String key, Object value) {
		maps.put(key, value);
	}
	
	public Map<String, Object> build() {
		return maps;
	}
}
