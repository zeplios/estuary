package cn.edu.tju.ina.estuary.mvc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.tju.ina.estuary.doc.VoHelper;
import cn.edu.tju.ina.estuary.doc.entity.MethodObject;
import cn.edu.tju.ina.estuary.doc.entity.ValueObject;
import cn.edu.tju.ina.estuary.domain.info.Info;
import cn.edu.tju.ina.estuary.domain.info.InfoType;
import cn.edu.tju.ina.estuary.domain.user.User;
import cn.edu.tju.ina.estuary.service.InfoService;
import cn.edu.tju.ina.estuary.service.UserService;
import cn.edu.tju.ina.estuary.util.IdAssistant;

@Controller
@RequestMapping(value="/")
public class HomeController
{
    @Autowired
    private InfoService infoService;
    @Autowired
    private UserService userService;
    
    @RequestMapping(method=RequestMethod.GET)
    public String index(Model model) {
    	for (InfoType it : IdAssistant.getInstance().getInfoTypeList()) {
    		if (!it.isShow()) {
    			continue;
    		}
    		List<Info> infos = infoService.findInfosByPageOrderByTime(it.getId(), 0, 6);
        	model.addAttribute(it.getListName(), infos);
    	}
		//Èë×¤ÉçÍÅ
    	List<User> orgs = userService.findAuthOrgnizations(0, 10);
    	model.addAttribute("orgs", orgs);
        return "index";
    }
    
    
    @RequestMapping(value="/rules", method=RequestMethod.GET)
    public String rules(Model model) {
        return "single/rules";
    }
    
    @RequestMapping(value="/docs", method=RequestMethod.GET)
    public String docs(Model model) {
		List<ValueObject> vodocs = new ArrayList<ValueObject>();
		List<MethodObject> modocs = new ArrayList<MethodObject>();
    	try {
			vodocs = VoHelper.voDoc("cn.edu.tju.ina.estuary.vo");
			modocs = VoHelper.apiDoc("cn.edu.tju.ina.estuary.mvc.rest");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	model.addAttribute("vos", vodocs);
    	model.addAttribute("apis", modocs);
        return "single/docs";
    }
    
    @RequestMapping(value="/test", method=RequestMethod.GET)
    public String test() {
        return "test";
    }

}
