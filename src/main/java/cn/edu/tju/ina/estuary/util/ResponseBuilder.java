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
		constants.put(UNKOWN_ERROR, "δ֪����");
		constants.put(NEED_SIGN_IN, "���ȵ�¼");
		constants.put(SUCCESS, "����ɹ�");
		
		constants.put(USERNAME_ERROR, "�û�������");
		constants.put(PASSWORD_ERROR, "�������");
		constants.put(EMAIL_ERROR, "�������");
		constants.put(REPEATED_USERNAME, "���û����ѱ�ע��");
		constants.put(REPEATED_EMAIL, "�������ѱ�ע��");
		constants.put(USERNAME_OR_PASSWORD_ERROR, "�û������������");
		constants.put(BOUND_FIRST_TIME, "��һ�ΰ�У԰���˺�");
		
		constants.put(LOST_PARAMETER, "ȱ�ٲ���");
		constants.put(HAVE_COLLECTED, "���ղأ��������ظ��ղ�");
		constants.put(NON_COLLECTED, "δ�ղأ��޷�ɾ����");
		constants.put(HAVE_THUMBUPED, "���޹����������ظ���");
		constants.put(NON_THUMBUPED, "���޹����޷�ȡ��");
		constants.put(NO_COMMENT_PUBLISHED, "�ûظ������ڻ��߲�����������");
		constants.put(ERROR_FILESIZE, "�ļ���С������Ҫ��");
		constants.put(ACCESS_DENIED, "��û�з�������ӿڵ�Ȩ��");
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
