package cn.edu.tju.ina.estuary.util;

import java.util.List;

import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import cn.edu.tju.ina.estuary.domain.info.InfoType;
import cn.edu.tju.ina.estuary.repo.InfoTypeDao;

public class IdAssistant {
	
	private Logger log = Logger.getLogger(IdAssistant.class.getName());
	
	@Autowired
	private InfoTypeDao infoTypeDao;
	
	private static IdAssistant idAssistant = new IdAssistant();

	private DualHashBidiMap<Integer, String> infoTypeMap = new DualHashBidiMap<Integer, String>();
	private List<InfoType> infoTypeList;
	
	public static IdAssistant getInstance() {
		return idAssistant;
	}

	private IdAssistant() {
		manualWireBean();
		infoTypeList = infoTypeDao.findAll();
		long currentTime = System.currentTimeMillis();
		log.info("init the resourceMap at " + currentTime);
		for (InfoType it : infoTypeList) {
			infoTypeMap.put(it.getId(), it.getClassName());
		}
	}

	public void reInitInfoTypeMap() {
		infoTypeList = infoTypeDao.findAll();
		long currentTime = System.currentTimeMillis();
		log.info("reinit the resourceMap at " + currentTime);
		infoTypeMap.clear();
		for (InfoType it : infoTypeList) {
			infoTypeMap.put(it.getId(), it.getName());
		}
	}

	public String getInfoTypeName(int id) {
		Object obj = infoTypeMap.get(id);
		return (obj == null) ? "info" : (String)obj;
	}

	public Integer getInfoTypeId(String name) {
		return (Integer) infoTypeMap.getKey(name);
	}

	public List<InfoType> getInfoTypeList() {
		return infoTypeList;
	}
	
	private void manualWireBean() {
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext(); 
		infoTypeDao = wac.getBean(InfoTypeDao.class);
	}
}
