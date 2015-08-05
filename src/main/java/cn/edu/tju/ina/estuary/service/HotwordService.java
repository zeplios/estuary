package cn.edu.tju.ina.estuary.service;

import java.util.List;

import cn.edu.tju.ina.estuary.domain.info.Hotword;
import cn.edu.tju.ina.estuary.service.generic.GenericService;

public interface HotwordService extends GenericService<Hotword, Integer> {
	
	void delete(int hid);
	
	Hotword findByWord(String word);
	List<Hotword> findHotest(int num);

}
