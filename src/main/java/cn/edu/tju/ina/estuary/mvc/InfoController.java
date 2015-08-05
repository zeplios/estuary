package cn.edu.tju.ina.estuary.mvc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.tju.ina.estuary.domain.info.Info;
import cn.edu.tju.ina.estuary.domain.info.InfoSet;
import cn.edu.tju.ina.estuary.domain.user.User;
import cn.edu.tju.ina.estuary.service.CollectService;
import cn.edu.tju.ina.estuary.service.InfoService;
import cn.edu.tju.ina.estuary.service.ThumbupService;
import cn.edu.tju.ina.estuary.util.Constants;
import cn.edu.tju.ina.estuary.util.FileHelper;
import cn.edu.tju.ina.estuary.util.IdAssistant;
import cn.edu.tju.ina.estuary.util.PoToVo;
import cn.edu.tju.ina.estuary.util.ResponseBuilder;

@Controller
@RequestMapping("/info")
public class InfoController {
	@Autowired
	private InfoService infoService;
	@Autowired
    private CollectService collectService;
	@Autowired
    private ThumbupService thumbupService;

	@RequestMapping(value="/list")
	public String list(@RequestParam int type, Model model) {
		model.addAttribute("type", type);
		return "info/info_list";
	}
	
	@RequestMapping(value="/detail/{id}.html")
	public String detail(@PathVariable int id, @RequestParam int type, Model model) {
		Info info = infoService.findInfoById(type, id);
		model.addAttribute("info", info);
		String infoTypeName = IdAssistant.getInstance().getInfoTypeName(type).toLowerCase();
		return "info/" + infoTypeName + "_detail";
	}
	
	/**
     * 前台列表页按页获取列表展示出来
     * @param type 1=校内新闻，2=招聘
     * @param page 第几页，0开始
     * @return {"errcode":xx,"errmsg":xx,"infos":xx}
     */
    @RequestMapping(value="/asyn/list", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> list(@RequestParam int type, @RequestParam int page, HttpSession session)
    {
    	ResponseBuilder resp = new ResponseBuilder();
    	resp.code(ResponseBuilder.SUCCESS);
    	List<Info> infos = infoService.findInfosByPageOrderByTime(type, (page-1)*10, 10);
    	Object uidObj = session.getAttribute(Constants.USER_ID_IN_SESSION);
    	if (uidObj != null) {
    		int uid = (Integer) uidObj;
    		for (Info i : infos) {
        		i.setHasCollected(collectService.getCollected(uid, i.getId()) != null);
        		i.setHasThumbuped(thumbupService.getThumbuped(uid, i.getId()) != null);
        	}
    	}
    	resp.add("infos", PoToVo.transformInfoListToSimple(infos));
        return resp.build();
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@RequestParam MultipartFile bigPoster, @RequestParam MultipartFile [] subImages, 
			@ModelAttribute InfoSet infoSet, Model model, HttpSession session) {
		User u = (User) session.getAttribute(Constants.USER_IN_SESSION);
		String subPath = new SimpleDateFormat("YYYYMM").format(new Date());
		String filename = Integer.toHexString((int) System.nanoTime()) + u.getId() + ".jpg";
		
		Info info = infoSet.getInfo();
		FileHelper fileHelper = new FileHelper(session);
		fileHelper.save(bigPoster, "uploads/" + subPath, filename);
		fileHelper.save(bigPoster, "uploads/" + subPath, "s_" + filename, 300);
		info.setPicture("uploads/" + subPath + "/" + filename);
		info.setUser(u);
		info.setStatus(2);
		List<String> images = new ArrayList<String>();
		for (MultipartFile mf : subImages) {
			subPath = new SimpleDateFormat("YYYYMM").format(new Date());
			filename = Integer.toHexString((int) System.nanoTime()) + u.getId() + ".jpg";
			fileHelper.save(mf, "userdata/" + subPath, filename);
			fileHelper.save(mf, "userdata/" + subPath, "s_" + filename, 300);
			images.add("uploads/" + subPath + "/" + filename);
		}
		info.setImages(images);
		infoService.save(info);
		return "redirect:/admin/user/upload/activity";
	}
}
