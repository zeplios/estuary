package cn.edu.tju.ina.estuary.service;

import java.util.List;

import cn.edu.tju.ina.estuary.domain.user.Notice;
import cn.edu.tju.ina.estuary.service.generic.GenericService;

public interface NoticeService extends GenericService<Notice, Integer> {
	
	void delete(int nid);
	
	/**
	 * 获取未读消息
	 * @param userid 当前用户id，即消息接收者id
	 * @return
	 */
	List<Notice> findUnreadNotices(int userid);
}
