package cn.edu.tju.ina.estuary.service;

import java.util.List;

import cn.edu.tju.ina.estuary.domain.user.Collect;
import cn.edu.tju.ina.estuary.service.generic.GenericService;

public interface CollectService extends GenericService<Collect, Integer> {
	
	void delete(int cid);
//
//	/**
//	 * 判断某用户是否有过某收藏
//	 * @param uid 用户id
//	 * @param cid 收藏id
//	 * @return
//	 */
//	boolean getCollectedForUser(int uid, int cid);
	
	/**
	 * 判断一个资源是否收藏过
	 * @param uid 用户id
	 * @param infoid 资源id
	 * @return
	 */
	Collect getCollected(int uid, int infoid);
	
	/**
	 * 判断一个资源是否收藏过
	 * @param cid 判断改收藏是否存在
	 * @return
	 */
	boolean getCollected(int cid);
	
	/**
	 * 获取某用户收藏的资源
	 * @param uid
	 * @param type 类型，0表示不指定
	 * @param endid 表示收藏id，从哪一个收藏开始获取（不包括该资源），如果是获取最新的，可设为0
	 * @param length 获取多少条
	 * @return
	 */
	List<Collect> findOldestCollectsByUser(int uid, int type, int endid, int length);
	
	/**
	 * 获取某用户最新收藏
	 * @param uid
	 * @param type 类型，0表示不指定
	 * @param startid 收藏id。从哪一个资源开始获取（不包括该资源），返回比这个更新的
	 * @return
	 */
	List<Collect> findNewestCollectsByUser(int uid, int type, int startid);
	
	List<Collect> findCollectsByInfo(int infoid);
}
