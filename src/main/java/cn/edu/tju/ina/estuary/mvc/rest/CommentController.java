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
import cn.edu.tju.ina.estuary.domain.user.Comment;
import cn.edu.tju.ina.estuary.domain.user.User;
import cn.edu.tju.ina.estuary.service.CommentService;
import cn.edu.tju.ina.estuary.service.InfoService;
import cn.edu.tju.ina.estuary.util.PoToVo;
import cn.edu.tju.ina.estuary.util.ResponseBuilder;

@Controller("commentRestController")
@RequestMapping("/rest/comment")
public class CommentController extends BaseController {
	@Autowired
    private CommentService commentService;
	@Autowired
	private InfoService infoService;
	private Object lock = new Object();
	
	@RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @Return("{'errcode':0, 'errmsg':'xxx', 'comments':[{<a href='#vo-Comment'>Comment</a>},{<a href='#vo-Comment'>Comment</a>},...]}")
    @Title("�����б�")
    @Description("��ȡĳ��Ϣ�������б�")
    @Param(name={"infoid", "startid/endid"}, desc={"Ҫ��ȡ���۵���Ϣ��id", "��startid���µ��ղ�/��endid���ϵ��ղ�"})
    public Map<String, Object> list(@RequestParam(required=true) int infoid, 
    		@RequestParam(required=false, defaultValue="-1") int startid, 
    		@RequestParam(required=false, defaultValue="-1") int endid) {
    	List<Comment> comments = null;
    	ResponseBuilder resp = new ResponseBuilder();
    	if (startid >= -1) {
    		comments = commentService.findNewestCommentsByInfo(infoid, startid);
    	} else if (endid > -1) {
    		comments = commentService.findOldestCommentsByInfo(infoid, endid, 20);
    	}
    	resp.code(ResponseBuilder.SUCCESS);
    	resp.add("comments", PoToVo.transformCommentListForInfo(comments));
        return resp.build();
    }
    
    /**
     * ��Ҫ��������
     * @param toUser.id ���ظ����û�id
     * @param info.id ���ղص���Դid
     * @param content ��������
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @Return("{'errcode':0, 'errmsg':'xxx', 'comments':[{<a href='#vo-Comment'>Comment</a>},{<a href='#vo-Comment'>Comment</a>},...]}")
    @Title("��������")
    @Description("�ѵ�¼�û���������")
    @Param(name={"toUser.id", "info.id", "content"}, desc={"���ظ����û�id��������ǻظ����˵��򲻴��������", "���ղص���Դid", "��������"})
    public Map<String, Object> add(@ModelAttribute Comment c) {
    	int uid = getCurrentUserId();
    	User user = new User(uid);
    	c.setFromUser(user);
    	ResponseBuilder resp = new ResponseBuilder();
		int id = commentService.save(c);
		if (id > 0) {
    		synchronized (lock) {
				Info info = infoService.findInfoById(0, c.getInfo().getId());
				info.setCommentCount(info.getCommentCount() + 1);
				infoService.save(info);
			}
			resp.code(ResponseBuilder.SUCCESS);
		} else {
			resp.code(ResponseBuilder.UNKOWN_ERROR);
		}
		return resp.build();
    }
    
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    @Return("{'errcode':0, 'errmsg':'xxx'}")
    @Title("ɾ������")
    @Description("�ѵ�¼�û�ɾ������")
    @Param(name={"id"}, desc={"���۵�id"})
    public Map<String, Object> del(@ModelAttribute Comment c) {
    	int uid = getCurrentUserId();
    	boolean canDel = commentService.isCommentPublishedByUser(uid, c.getId());
    	ResponseBuilder resp = new ResponseBuilder();
    	if (canDel) {
    		commentService.delete(c);
    		synchronized (lock) {
				Info info = infoService.findInfoById(0, c.getInfo().getId());
				info.setCommentCount(info.getCommentCount() - 1);
				infoService.save(info);
			}
    		resp.code(ResponseBuilder.SUCCESS);
    	} else {
    		resp.code(ResponseBuilder.NO_COMMENT_PUBLISHED);
    	}
		return resp.build();
    }
}
