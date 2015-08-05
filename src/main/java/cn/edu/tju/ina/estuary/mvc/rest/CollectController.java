package cn.edu.tju.ina.estuary.mvc.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import cn.edu.tju.ina.estuary.domain.info.Info;
import cn.edu.tju.ina.estuary.domain.user.Collect;
import cn.edu.tju.ina.estuary.domain.user.User;
import cn.edu.tju.ina.estuary.service.CollectService;
import cn.edu.tju.ina.estuary.service.InfoService;
import cn.edu.tju.ina.estuary.util.PoToVo;
import cn.edu.tju.ina.estuary.util.ResponseBuilder;

@Controller("collectRestController")
@RequestMapping("/rest/collect")
public class CollectController extends BaseController {
	
    @Autowired
    private CollectService collectService;
    @Autowired
    private InfoService infoService;
    private Object lock = new Object();
	
	@RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @Return("{'errcode':0, 'errmsg':'xxx', 'collects':[{<a href='#vo-Collect'>Collect</a>},{<a href='#vo-Collect'>Collect</a>},...]}")
    @Title("收藏")
    @Description("当前已登录用户的收藏")
    @Param(name={"type", "biggestid/smallestid"}, desc={"信息类型，Activity=1，Recruit=2，Lost=3，Market=4，Album=5，Photo=6", "比startid更新的收藏/比endid更老的收藏"})
    public Map<String, Object> list(@RequestParam(required=false, defaultValue="0") int type, 
    		@RequestParam(required=false, defaultValue="-1") int biggestid, 
    		@RequestParam(required=false, defaultValue="-1") int smallestid) {
    	int uid = getCurrentUserId();
    	List<Collect> collects;
    	ResponseBuilder resp = new ResponseBuilder();
    	if (biggestid > -1) {
    		collects = collectService.findNewestCollectsByUser(uid, type, biggestid);
    	} else {
    		if (smallestid == -1) {
    			smallestid = Integer.MAX_VALUE;
    		}
    		collects = collectService.findOldestCollectsByUser(uid, type, smallestid, 10);
    	} 
    	resp.code(ResponseBuilder.SUCCESS);
    	resp.add("collects", PoToVo.transformCollectListToSimple(collects));
        return resp.build();
    }
    
    /**
     * 需要三个参数
     * @param info.id 被收藏的资源id
     * @param type.id 被收藏的资源类型
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @Return("{'errcode':0, 'errmsg':'xxx'}")
    @Title("添加收藏")
    @Description("当前已登录用户添加新的收藏")
    @Param(name={"info.id", "type"}, desc={"待收藏信息的id", "待收藏信息的类型"})
    public Map<String, Object> add(@ModelAttribute Collect c) {
    	int uid = getCurrentUserId();
    	User user = new User(uid);
    	c.setUser(user);
    	ResponseBuilder resp = new ResponseBuilder();
    	boolean hasCollected = (collectService.getCollected(uid, c.getInfo().getId()) != null);
    	if (hasCollected) {
    		resp.code(ResponseBuilder.HAVE_COLLECTED);
            return resp.build();
    	} else {
    		int id = collectService.save(c);
    		if (id > 0) {
    			synchronized (lock) {
					Info info = infoService.findInfoById(0, c.getInfo().getId());
					info.setCollectCount(info.getCollectCount() + 1);
					infoService.save(info);
				}
    			resp.code(ResponseBuilder.SUCCESS);
    		} else {
    			resp.code(ResponseBuilder.UNKOWN_ERROR);
    		}
    		return resp.build();
    	}
    }
    
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    @Return("{'errcode':0, 'errmsg':'xxx'}")
    @Title("取消收藏")
    @Description("当前已登录用户取消收藏")
    @Param(name={"info.id"}, desc={"待取消收藏信息的id"})
    public Map<String, Object> del(@ModelAttribute Collect c) {
    	int uid = getCurrentUserId();
    	ResponseBuilder resp = new ResponseBuilder();
    	c = collectService.getCollected(uid, c.getInfo().getId());
    	if (c != null) {
    		collectService.delete(c);
    		synchronized (lock) {
				Info info = infoService.findInfoById(0, c.getInfo().getId());
				info.setCollectCount(info.getCollectCount() - 1);
				infoService.save(info);
			}
    		resp.code(ResponseBuilder.SUCCESS);
    		return resp.build();
    	} else {
    		resp.code(ResponseBuilder.NON_COLLECTED);
            return resp.build();
    	}
    }
}
