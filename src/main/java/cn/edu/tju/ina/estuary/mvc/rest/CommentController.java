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
    @Title("评论列表")
    @Description("获取某信息的评论列表")
    @Param(name={"infoid", "startid/endid"}, desc={"要获取评论的信息的id", "比startid更新的收藏/比endid更老的收藏"})
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
     * 需要三个参数
     * @param toUser.id 被回复的用户id
     * @param info.id 被收藏的资源id
     * @param content 评论内容
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @Return("{'errcode':0, 'errmsg':'xxx', 'comments':[{<a href='#vo-Comment'>Comment</a>},{<a href='#vo-Comment'>Comment</a>},...]}")
    @Title("发布评论")
    @Description("已登录用户发布评论")
    @Param(name={"toUser.id", "info.id", "content"}, desc={"被回复的用户id，如果不是回复别人的则不传这个参数", "被收藏的资源id", "评论内容"})
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
    @Title("删除评论")
    @Description("已登录用户删除评论")
    @Param(name={"id"}, desc={"评论的id"})
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
