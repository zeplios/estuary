package cn.edu.tju.ina.estuary.service;

import java.util.List;

import cn.edu.tju.ina.estuary.domain.info.Info;

public interface InfoService {
	/**
	 * 按类型获取
	 * @param type 1=>Activity, 2=>Recurity
	 * @param start
	 * @param length
	 * @return
	 */
	List<Info> findInfosByPageOrderByTime(int type, int start, int length);
	List<Info> findNewestOrderByTime(int type, int fmsg);
	List<Info> findOldestOrderByTime(int type, int lmsg, int length);
	
	List<Info> findAllInfosByUser(int type, int userid);
	List<Info> findInfosByUserByPage(int type, int start, int length, int userid);
	List<Info> findNewestByUser(int type, int fmsg, int userid);
	List<Info> findOldestByUser(int type, int lmsg, int length, int userid);
	
	List<Info> searchByTitleOrDesc(int type, String name);
	List<Info> searchByTitle(int type, String title);
	
	int save(Info info);
	void update(Info info);
	void delete(Info info);
	void evict(Info i);
	void flush();
	
	Info findInfoById(int type, int id);
}
