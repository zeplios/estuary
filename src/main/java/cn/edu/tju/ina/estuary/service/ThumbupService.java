package cn.edu.tju.ina.estuary.service;

import java.util.List;

import cn.edu.tju.ina.estuary.domain.user.Thumbup;
import cn.edu.tju.ina.estuary.service.generic.GenericService;

public interface ThumbupService extends GenericService<Thumbup, Integer> {
	
	void delete(int cid);

//	/**
//	 * �ж�ĳ�û��Ƿ��й�ĳ�ղ�
//	 * @param uid �û�id
//	 * @param cid �ղ�id
//	 * @return
//	 */
//	boolean getThumbupedForUser(int uid, int tid);
	
	/**
	 * �ж�һ����Դ�Ƿ��ղع�
	 * @param uid �û�id
	 * @param infoid ��Դid
	 * @return
	 */
	Thumbup getThumbuped(int uid, int infoid);
	
	/**
	 * �ж�һ����Դ�Ƿ��ղع�
	 * @param cid �жϸ��ղ��Ƿ����
	 * @return
	 */
	boolean getThumbuped(int tid);
	
	/**
	 * ��ȡĳ�û��ղص���Դ
	 * @param uid
	 * @param type ���ͣ�0��ʾ��ָ��
	 * @param endid ��ʾ�ղ�id������һ���ղؿ�ʼ��ȡ������������Դ��������ǻ�ȡ���µģ�����Ϊ0
	 * @param length ��ȡ������
	 * @return
	 */
	List<Thumbup> findOldestThumbupsByUser(int uid, int type, int endid, int length);
	
	/**
	 * ��ȡĳ�û������ղ�
	 * @param uid
	 * @param type ���ͣ�0��ʾ��ָ��
	 * @param startid �ղ�id������һ����Դ��ʼ��ȡ������������Դ�������ر�������µ�
	 * @return
	 */
	List<Thumbup> findNewestThumbupsByUser(int uid, int type, int startid);
	
	List<Thumbup> findThumbupsByInfo(int infoid);
}
