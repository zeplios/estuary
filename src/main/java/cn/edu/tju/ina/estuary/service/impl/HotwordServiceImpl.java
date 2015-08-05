package cn.edu.tju.ina.estuary.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.tju.ina.estuary.domain.info.Hotword;
import cn.edu.tju.ina.estuary.repo.HotwordDao;
import cn.edu.tju.ina.estuary.service.HotwordService;
import cn.edu.tju.ina.estuary.service.generic.GenericServiceImpl;

@Service("hotwordService")
@Transactional
public class HotwordServiceImpl extends GenericServiceImpl<Hotword, Integer> implements HotwordService {

	private HotwordDao hotWordDao;

	@Autowired
	public HotwordServiceImpl(HotwordDao hotWordDao) {
		super(hotWordDao);
		this.hotWordDao = hotWordDao;
	}

	@Override
	public Integer save(Hotword h) {
		return hotWordDao.save(h);
	}

	@Override
	public void delete(int hid) {
		Hotword h = new Hotword();
		h.setId(hid);
		hotWordDao.delete(h);
	}

	@Override
	public Hotword findByWord(String word) {
		return hotWordDao.findByProperty("word", word);
	}

	@Override
	public List<Hotword> findHotest(int num) {
		return hotWordDao.findList(0, num, "count", true);
	}
	
}
