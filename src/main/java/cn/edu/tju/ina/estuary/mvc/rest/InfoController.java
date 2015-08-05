package cn.edu.tju.ina.estuary.mvc.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import cn.edu.tju.ina.estuary.service.CollectService;
import cn.edu.tju.ina.estuary.service.InfoService;
import cn.edu.tju.ina.estuary.service.ThumbupService;
import cn.edu.tju.ina.estuary.util.FileHelper;
import cn.edu.tju.ina.estuary.util.PoToVo;
import cn.edu.tju.ina.estuary.util.ResponseBuilder;

@Controller("infoRestController")
@RequestMapping("/rest/info")
public class InfoController extends BaseController {
	
    @Autowired
    private InfoService infoService;
    @Autowired
    private CollectService collectService;
    @Autowired
    private ThumbupService thumbupService;
    @Autowired
    private NoticeController noticeRestController;
    @Autowired
    private HotwordController hotwordRestController;
    private Object lock = new Object();
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @Return("{'errcode':0, 'errmsg':'xxx'}")
    @Title("�ϴ�������")
    @Description("������������������ϴ���Ƭ֮�⣬���е�����Ϣ�����ӿ�")
    @Param(name={"activity.images/recruit.xxx/..."}, desc={"�����Ƕ��ͬ��������������Ÿ�ͼ��url", 
    		"������ϴ�У�ڻ�����������activity.xxx���������Ƹ��Ϣ�������������recruit.xxx���Դ����ƣ�xxx������<a href='#vo-FullActivity'>Activity</a>��ʵ����������ֶ�"})
	public Map<String, Object> add(@ModelAttribute InfoSet infoSet, HttpSession session) {
		User u = new User(getCurrentUserId());
		Info info = infoSet.getInfo();
		info.setUser(u);
		info.setStatus(2);
		infoService.save(info);
		noticeRestController.add(info);
		
		ResponseBuilder resp = new ResponseBuilder();
    	resp.code(ResponseBuilder.SUCCESS);
		return resp.build();
	}
    
    /**
     * ǰ̨�б�ҳ��ҳ��ȡ�б�չʾ����
     * @param type 1=У�����ţ�2=��Ƹ
     * @param page �ڼ�ҳ��0��ʼ
     * @return {"errcode":xx,"errmsg":xx,"infos":xx}
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @Return("{'errcode':0, 'errmsg':'xxx'�� 'infos':[{<a href='#vo-FullActivity'>Activity</a>},{<a href='#vo-FullRecruit'>Recruit</a>},...]}")
    @Title("��ȡ��Ϣ�б�")
    @Description("��ȡ����һ����Ϣ���б�")
    @Param(name={"type", "page/biggestid/smallestid"}, desc={"��Ϣ���ͣ�Activity=1��Recruit=2��Lost=3��Market=4��Album=5��Photo=6", 
    		"�ڼ�ҳ/��ȡ��fmsg���µ�/��ȡ��lmsg���ɵ�"})
    public Map<String, Object> list(@RequestParam int type, 
    		@RequestParam(required=false, defaultValue="-1") int page, 
    		@RequestParam(required=false, defaultValue="-1") int biggestid, 
    		@RequestParam(required=false, defaultValue="-1") int smallestid) {
    	ResponseBuilder resp = new ResponseBuilder();
    	
    	List<Info> infos = null;
    	if (page > 0) {
    		infos = infoService.findInfosByPageOrderByTime(type, (page-1)*10, 10);
    	} else if (biggestid >= 0) {
    		infos = infoService.findNewestOrderByTime(type, biggestid);
    	} else if (smallestid >= 0) {
    		infos = infoService.findOldestOrderByTime(type, smallestid, 10);
    	} else {
    		resp.code(ResponseBuilder.LOST_PARAMETER);
    		return resp.build();
    	}
    	resp.code(ResponseBuilder.SUCCESS);
    	int uid = getCurrentUserId();
    	if (uid > -1) {
    		for (Info i : infos) {
        		i.setHasCollected(collectService.getCollected(uid, i.getId()) != null);
        		i.setHasThumbuped(thumbupService.getThumbuped(uid, i.getId()) != null);
        	}
    	}
    	resp.add("infos", PoToVo.transformInfoListToSimple(infos));
        return resp.build();
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    @Return("{'errcode':0, 'errmsg':'xxx'}")
    @Title("�޸���Ϣ���ݣ��ݲ�֧��ͼƬ�޸ģ�")
    @Description("������������������ϴ���Ƭ֮�⣬���е�����Ϣ�����ӿ�")
    @Param(name={"activity.xxx/recruit.xxx/..."}, desc={
    		"������ϴ�У�ڻ�����������activity.xxx���������Ƹ��Ϣ�������������recruit.xxx���Դ����ƣ�xxx������<a href='#vo-FullActivity'>Activity</a>��ʵ����������ֶ�"})
	public Map<String, Object> update(@ModelAttribute InfoSet infoSet, HttpSession session) {
		User u = new User(getCurrentUserId());
		Info info = infoSet.getInfo();
		Info origin = infoService.findInfoById(info.getType(), info.getId());
		if (origin.getUser().getId().equals(u.getId())) {
			infoService.update(info);
		}
		
		ResponseBuilder resp = new ResponseBuilder();
    	resp.code(ResponseBuilder.SUCCESS);
		return resp.build();
	}
    
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    @Return("{'errcode':0, 'errmsg':'xxx'}")
    @Title("ɾ����Ϣ")
    @Description("������Ϣidɾ����Ϣ")
    @Param(name={"id", "type"}, desc={"��Ϣid", "��Ϣ����"})
	public Map<String, Object> delete(@RequestParam int id, @RequestParam int type, HttpSession session) {
		User u = new User(getCurrentUserId());
		Info origin = infoService.findInfoById(type, id);
		FileHelper fileHelper = new FileHelper(session);
		if (origin.getUser().getId().equals(u.getId())) {
			fileHelper.delete(origin.getImages());
			fileHelper.delete(origin.getPicture());
			if (origin instanceof Album) {
				for (Photo p : ((Album) origin).getPhotos()) {
					delete(p.getId(), p.getType(), session);
				}
			}
			try {
				infoService.delete(origin);
			} catch (Exception e) {
				origin.setDeleted(true);
				origin.setImages(new ArrayList<String>());
				origin.setPicture("uploads/default.jpg");
				infoService.update(origin);
			}
		}
		
		ResponseBuilder resp = new ResponseBuilder();
    	resp.code(ResponseBuilder.SUCCESS);
		return resp.build();
	}
    
    @RequestMapping(value="/search")
    @ResponseBody
    @Return("{'errcode':0, 'errmsg':'xxx'�� 'infos':[{<a href='#vo-FullActivity'>Activity</a>},{<a href='#vo-FullRecruit'>Recruit</a>},...]}")
    @Title("����")
    @Description("��������ĳ���ؼ��ʵ���Դ")
    @Param(name={"title"}, desc={"�����Ĺؼ���"})
    public Map<String, Object> search(@RequestParam String title) {
    	ResponseBuilder resp = new ResponseBuilder();
    	List<Info> infos = infoService.searchByTitleOrDesc(0, title);
    	hotwordRestController.add(title);
    	resp.code(ResponseBuilder.SUCCESS);
    	resp.add("infos", PoToVo.transformInfoListToSimple(infos));
        return resp.build();
    }
    
    @RequestMapping(value="/detail")
    @ResponseBody
    @Return("{'errcode':0, 'errmsg':'xxx'�� 'info':{<a href='#vo-FullActivity'>Activity</a>/<a href='#vo-FullRecruit'>Recruit</a>/...}}")
    @Title("����")
    @Description("��������ĳ���ؼ��ʵ���Դ")
    @Param(name={"id", "type"}, desc={"��Ϣid", "��Ϣ����"})
    public Map<String, Object> detail(@RequestParam int id, @RequestParam int type) {
    	ResponseBuilder resp = new ResponseBuilder();
    	Info info = infoService.findInfoById(type, id);
    	resp.code(ResponseBuilder.SUCCESS);
    	int uid = getCurrentUserId();
    	if (uid > -1) {
    		info.setHasCollected(collectService.getCollected(uid, info.getId()) != null);
    		info.setHasThumbuped(thumbupService.getThumbuped(uid, info.getId()) != null);
    	}
		synchronized (lock) {
			info.setViewCount(info.getViewCount() + 1);
			infoService.save(info);
		}
    	resp.add("info", PoToVo.transformInfoToFull(info));
        return resp.build();
    }
    
}
