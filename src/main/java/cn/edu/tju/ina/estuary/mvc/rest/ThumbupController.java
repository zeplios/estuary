package cn.edu.tju.ina.estuary.mvc.rest;

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
import cn.edu.tju.ina.estuary.domain.user.Thumbup;
import cn.edu.tju.ina.estuary.domain.user.User;
import cn.edu.tju.ina.estuary.service.InfoService;
import cn.edu.tju.ina.estuary.service.ThumbupService;
import cn.edu.tju.ina.estuary.util.PoToVo;
import cn.edu.tju.ina.estuary.util.ResponseBuilder;

@Controller("thumbupRestController")
@RequestMapping("/rest/thumbup")
public class ThumbupController extends BaseController {

    @Autowired
    private ThumbupService thumbupService;
    @Autowired
    private InfoService infoService;
    private Object lock = new Object();
    
	@RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @Return("{'errcode':0, 'errmsg':'xxx', 'thumbups':[{<a href='#vo-Thumbup'>Thumbup</a>},{<a href='#vo-Thumbup'>Thumbup</a>},...]}")
    @Title("����")
    @Description("��ǰ�ѵ�¼�û��ĵ���޵Ļ")
    @Param(name={"type", "startid/endid"}, desc={"��Ϣ���ͣ�Activity=1��Recruit=2��Lost=3��Market=4��Album=5��Photo=6", "��startid���µ���/��endid���ϵ���"})
    public Map<String, Object> list(@RequestParam(required=false, defaultValue="0") int type, 
    		@RequestParam(required=false, defaultValue="-1") int startid, 
    		@RequestParam(required=false, defaultValue="-1") int endid) {
    	int uid = getCurrentUserId();
    	List<Thumbup> thumbups = null;
    	ResponseBuilder resp = new ResponseBuilder();
    	if (endid > -1) {
    		thumbups = thumbupService.findOldestThumbupsByUser(uid, type, endid, 10);
    	} else if (startid >= -1) {
    		thumbups = thumbupService.findNewestThumbupsByUser(uid, type, startid);
    	}
    	resp.code(ResponseBuilder.SUCCESS);
    	resp.add("thumbups", PoToVo.transformThumbupListToSimple(thumbups));
        return resp.build();
    }
    
    /**
     * ��Ҫ��������
     * @param info.id ���ղص���Դid
     * @param type ���ղص���Դ����
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @Return("{'errcode':0, 'errmsg':'xxx'}")
    @Title("�����")
    @Description("��ǰ�ѵ�¼�û�����µ���")
    @Param(name={"info.id", "type.id"}, desc={"��������Ϣ��id", "��������Ϣ������"})
    public Map<String, Object> add(@ModelAttribute Thumbup t) {
    	int uid = getCurrentUserId();
    	User user = new User(uid);
    	t.setUser(user);
    	ResponseBuilder resp = new ResponseBuilder();
    	boolean hasCollected = (thumbupService.getThumbuped(uid, t.getInfo().getId()) != null);
    	if (hasCollected) {
    		resp.code(ResponseBuilder.HAVE_THUMBUPED);
            return resp.build();
    	} else {
    		int id = thumbupService.save(t);
    		synchronized (lock) {
				Info info = infoService.findInfoById(0, t.getInfo().getId());
				info.setThumbupCount(info.getThumbupCount() + 1);
				infoService.save(info);
			}
    		if (id > 0) {
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
    @Title("ȡ����")
    @Description("��ǰ�ѵ�¼�û�ȡ����")
    @Param(name={"info.id"}, desc={"��ȡ��������Ϣ��id"})
    public Map<String, Object> del(@ModelAttribute Thumbup t) {
    	int uid = getCurrentUserId();
    	ResponseBuilder resp = new ResponseBuilder();
    	t = thumbupService.getThumbuped(uid, t.getInfo().getId());
    	if (t != null) {
    		thumbupService.delete(t);
    		synchronized (lock) {
				Info info = infoService.findInfoById(0, t.getInfo().getId());
				info.setThumbupCount(info.getThumbupCount() - 1);
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
