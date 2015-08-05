package cn.edu.tju.ina.estuary.service;

import java.util.List;

import cn.edu.tju.ina.estuary.domain.user.Collect;
import cn.edu.tju.ina.estuary.service.generic.GenericService;

public interface CollectService extends GenericService<Collect, Integer> {
	
	void delete(int cid);
//
//	/**
//	 * �ж�ĳ�û��Ƿ��й�ĳ�ղ�
//	 * @param uid �û�id
//	 * @param cid �ղ�id
//	 * @return
//	 */
//	boolean getCollectedForUser(int uid, int cid);
	
	/**
	 * �ж�һ����Դ�Ƿ��ղع�
	 * @param uid �û�id
	 * @param infoid ��Դid
	 * @return
	 */
	Collect getCollected(int uid, int infoid);
	
	/**
	 * �ж�һ����Դ�Ƿ��ղع�
	 * @param cid �жϸ��ղ��Ƿ����
	 * @return
	 */
	boolean getCollected(int cid);
	
	/**
	 * ��ȡĳ�û��ղص���Դ
	 * @param uid
	 * @param type ���ͣ�0��ʾ��ָ��
	 * @param endid ��ʾ�ղ�id������һ���ղؿ�ʼ��ȡ������������Դ��������ǻ�ȡ���µģ�����Ϊ0
	 * @param length ��ȡ������
	 * @return
	 */
	List<Collect> findOldestCollectsByUser(int uid, int type, int endid, int length);
	
	/**
	 * ��ȡĳ�û������ղ�
	 * @param uid
	 * @param type ���ͣ�0��ʾ��ָ��
	 * @param startid �ղ�id������һ����Դ��ʼ��ȡ������������Դ�������ر�������µ�
	 * @return
	 */
	List<Collect> findNewestCollectsByUser(int uid, int type, int startid);
	
	List<Collect> findCollectsByInfo(int infoid);
}
