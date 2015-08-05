package cn.edu.tju.ina.estuary.mvc.rest;

import java.util.List;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.tju.ina.estuary.doc.annotation.Description;
import cn.edu.tju.ina.estuary.doc.annotation.Param;
import cn.edu.tju.ina.estuary.doc.annotation.Return;
import cn.edu.tju.ina.estuary.doc.annotation.Title;
import cn.edu.tju.ina.estuary.domain.info.Hotword;
import cn.edu.tju.ina.estuary.service.HotwordService;

@Controller("hotwordRestController")
@RequestMapping("/rest/hotword")
public class HotwordController {

	@Autowired
    private HotwordService hotwordService;
	
	@RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @Return("{'errcode':0, 'errmsg':'xxx', 'notification':[{<a href='#vo-Notification'>Notification</a>}]}")
    @Title("��ȡ����10����������")
    @Description("��ȡ����������10������")
    @Param(name={}, desc={})
    public JSONPObject list(@RequestParam String callback) {
    	List<Hotword> hws = hotwordService.findHotest(10);
    	// hotword����û�������û�зǻ������͵ı�����Ҫ��Ūһ��VO�������
        return new JSONPObject(callback, hws);
    }
	
	public void add(String word) {
    	Hotword hw = hotwordService.findByWord(word);
    	if (hw == null) {
    		hw = new Hotword();
    		hw.setCount(1);
    		hw.setWord(word);
    	} else {
    		hw.setCount(hw.getCount() + 1);
    	}
    	hotwordService.saveOrUpdate(hw);
    }
}
