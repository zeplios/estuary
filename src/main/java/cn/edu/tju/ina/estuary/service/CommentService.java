package cn.edu.tju.ina.estuary.service;

import java.util.List;

import cn.edu.tju.ina.estuary.domain.user.Comment;
import cn.edu.tju.ina.estuary.service.generic.GenericService;

public interface CommentService extends GenericService<Comment, Integer> {
	
	void delete(int cid);
	
	boolean isCommentPublishedByUser(int uid, int cid);

	/**
	 * ��ȡĳ�û��ղص���Դ
	 * @param uid
	 * @param type ���ͣ�0��ʾ��ָ��
	 * @param endid ��ʾ�ղ�id������һ���ղؿ�ʼ��ȡ������������Դ��������ǻ�ȡ���µģ�����Ϊ0
	 * @param length ��ȡ������
	 * @return
	 */
	List<Comment> findOldestCommentsByInfo(int infoid, int endid, int length);
	
	/**
	 * ��ȡĳ�û������ղ�
	 * @param uid
	 * @param type ���ͣ�0��ʾ��ָ��
	 * @param startid �ղ�id������һ����Դ��ʼ��ȡ������������Դ�������ر�������µ�
	 * @return
	 */
	List<Comment> findNewestCommentsByInfo(int infoid, int startid);
	
}
