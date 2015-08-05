package cn.edu.tju.ina.estuary.mvc.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.tju.ina.estuary.doc.annotation.Description;
import cn.edu.tju.ina.estuary.doc.annotation.Param;
import cn.edu.tju.ina.estuary.doc.annotation.Return;
import cn.edu.tju.ina.estuary.doc.annotation.Title;
import cn.edu.tju.ina.estuary.domain.info.Info;
import cn.edu.tju.ina.estuary.domain.info.InfoSingle;
import cn.edu.tju.ina.estuary.domain.info.Lost;
import cn.edu.tju.ina.estuary.domain.user.Notice;
import cn.edu.tju.ina.estuary.domain.user.User;
import cn.edu.tju.ina.estuary.service.NoticeService;
import cn.edu.tju.ina.estuary.service.UserService;
import cn.edu.tju.ina.estuary.util.PoToVo;
import cn.edu.tju.ina.estuary.util.ResponseBuilder;

@Controller("noticeRestController")
@RequestMapping("/rest/notice")
public class NoticeController extends BaseController {
	@Autowired
    private NoticeService noticeService;
	@Autowired
    private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @Return("{'errcode':0, 'errmsg':'xxx', 'notification':[{<a href='#vo-Notification'>Notification</a>}]}")
    @Title("获取所有的未读通知")
    @Description("和用户相关的通知（非评论），如有捡到我的东西，被@等")
    @Param(name={}, desc={})
    public Map<String, Object> list() {
    	ResponseBuilder resp = new ResponseBuilder();
    	List<Notice> nots = noticeService.findUnreadNotices(getCurrentUserId());
    	for (Notice n : nots) {
    		n.setHasRead(true);
    		noticeService.update(n);
    	}
		noticeService.flush();
    	resp.code(ResponseBuilder.SUCCESS);
    	resp.add("notices", PoToVo.transformNoticeListForInfo(nots));
        return resp.build();
    }
    
	/**
	 * 不对外提供接口
	 * @return
	 */
    public void add(Info info) {
    	int uid = getCurrentUserId();
    	User user = new User(uid);
    	if (info instanceof Lost) {
    		Notice n = new Notice();
    		Lost lost = (Lost) info;
    		if (lost.getOwner()) {
    			return;
    		}
    		if (lost.getContact() != null && !"".equals(lost.getContact().trim())) {
    			n.setContent("捡到了您的东西，请联系 " + lost.getContact());
    		} else {
    			n.setContent("捡到了您的东西");
    		}
    		n.setFromUser(user);
    		String owner = lost.getOwnerid();
    		User lostUser = userService.findByUsername(owner);
    		if (lostUser == null) {
    			lostUser = userService.findByRealname(owner);
    		}
    		if (lostUser != null) {
    			n.setToUser(lostUser);
    			n.setInfo(new InfoSingle(info.getId()));
    			n.setHasRead(false);
    			noticeService.save(n);
    		}
    	}
    }
}
