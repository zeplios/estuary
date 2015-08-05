package cn.edu.tju.ina.estuary.service;

import java.util.List;

import cn.edu.tju.ina.estuary.domain.user.Comment;
import cn.edu.tju.ina.estuary.service.generic.GenericService;

public interface CommentService extends GenericService<Comment, Integer> {
	
	void delete(int cid);
	
	boolean isCommentPublishedByUser(int uid, int cid);

	/**
	 * 获取某用户收藏的资源
	 * @param uid
	 * @param type 类型，0表示不指定
	 * @param endid 表示收藏id，从哪一个收藏开始获取（不包括该资源），如果是获取最新的，可设为0
	 * @param length 获取多少条
	 * @return
	 */
	List<Comment> findOldestCommentsByInfo(int infoid, int endid, int length);
	
	/**
	 * 获取某用户最新收藏
	 * @param uid
	 * @param type 类型，0表示不指定
	 * @param startid 收藏id。从哪一个资源开始获取（不包括该资源），返回比这个更新的
	 * @return
	 */
	List<Comment> findNewestCommentsByInfo(int infoid, int startid);
	
}
