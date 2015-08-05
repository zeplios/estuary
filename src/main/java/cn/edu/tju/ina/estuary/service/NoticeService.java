package cn.edu.tju.ina.estuary.service;

import java.util.List;

import cn.edu.tju.ina.estuary.domain.user.Notice;
import cn.edu.tju.ina.estuary.service.generic.GenericService;

public interface NoticeService extends GenericService<Notice, Integer> {
	
	void delete(int nid);
	
	/**
	 * ��ȡδ����Ϣ
	 * @param userid ��ǰ�û�id������Ϣ������id
	 * @return
	 */
	List<Notice> findUnreadNotices(int userid);
}
