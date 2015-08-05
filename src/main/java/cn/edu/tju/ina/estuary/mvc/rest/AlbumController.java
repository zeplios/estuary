package cn.edu.tju.ina.estuary.mvc.rest;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.tju.ina.estuary.doc.annotation.Description;
import cn.edu.tju.ina.estuary.doc.annotation.Param;
import cn.edu.tju.ina.estuary.doc.annotation.Return;
import cn.edu.tju.ina.estuary.doc.annotation.Title;
import cn.edu.tju.ina.estuary.domain.info.Album;
import cn.edu.tju.ina.estuary.domain.info.Info;
import cn.edu.tju.ina.estuary.domain.info.InfoSet;
import cn.edu.tju.ina.estuary.domain.info.Photo;
import cn.edu.tju.ina.estuary.domain.user.User;
import cn.edu.tju.ina.estuary.service.InfoService;
import cn.edu.tju.ina.estuary.util.ResponseBuilder;

@Controller("albumRestController")
@RequestMapping("/rest/album")
public class AlbumController extends BaseController {
	
    @Autowired
    private InfoService infoService;
    
    @Return("{'errcode':0, 'errmsg':'xxx', 'id':�´��������id}")
    @Title("�������")
    @Description("��Ƭǽ�������Ľӿ�")
    @Param(name={"bigPoster", "photos����ѡ��", "album.xxx"}, desc={"����ͼƬ", "�����ͼƬ",
    		"�ϴ����ĸ�������Ϣ��xxx������<a href='#vo-FullAlbum'>Album</a>�������ֶ�"})
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
	public Map<String, Object> add(@RequestParam(required=false) String [] photos, 
				@ModelAttribute InfoSet infoSet, Model model, HttpSession session) {
		User u = new User(getCurrentUserId());
		
		Info info = infoSet.getInfo();
		info.setUser(u);
		info.setStatus(2);
		int id = infoService.save(info);
		
		if (photos != null) {
			add(id, photos, session);
		}
		ResponseBuilder resp = new ResponseBuilder();
    	resp.code(ResponseBuilder.SUCCESS);
    	resp.add("id", id);
		return resp.build();
	}
    
    @RequestMapping(value="/photo", method = RequestMethod.POST)
    @ResponseBody
    @Return("{'errcode':0, 'errmsg':'xxx'}")
    @Title("�ϴ���Ƭ")
    @Description("�����е�����������Ƭ")
    @Param(name={"albumid", "photos"}, desc={"����id", "�����Ƕ��ͬ�����������������Ƭ"})
	public Map<String, Object> add(@RequestParam int albumid, @RequestParam String [] photos, HttpSession session) {
		User u = new User(getCurrentUserId());
		Album a = new Album(albumid);
		
		for (String photo : photos) {
			Photo i = new Photo();
			i.setPicture(photo);
			i.setAlbum(a);
			i.setUser(u);
			i.setType(6);
			infoService.save(i);
		}
		
		ResponseBuilder resp = new ResponseBuilder();
    	resp.code(ResponseBuilder.SUCCESS);
		return resp.build();
	}
    
}
