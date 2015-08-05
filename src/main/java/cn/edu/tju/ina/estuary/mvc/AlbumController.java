package cn.edu.tju.ina.estuary.mvc;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.tju.ina.estuary.domain.info.InfoSet;
import cn.edu.tju.ina.estuary.mvc.rest.BaseController;
import cn.edu.tju.ina.estuary.service.InfoService;

@Controller
@RequestMapping("/album")
public class AlbumController extends BaseController {
	
    @Autowired
    private InfoService infoService;
    
    @Autowired
    private cn.edu.tju.ina.estuary.mvc.rest.AlbumController albumRestController;
    
    @RequestMapping(method = RequestMethod.POST)
	public String add(@RequestParam(required=false) String [] photos, 
				@ModelAttribute InfoSet infoSet, Model model, HttpSession session) {
    	albumRestController.add(photos, infoSet, model, session);
		return "redirect:/user/upload/album";
	}
    
    @RequestMapping(value="/photo", method = RequestMethod.POST)
	public String add(@RequestParam int albumid, @RequestParam String [] photos, HttpSession session) {
		albumRestController.add(albumid, photos, session);
		return "redirect:/user/upload/album";
	}
    
}
