package cn.edu.tju.ina.estuary.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.tju.ina.estuary.domain.user.Comment;
import cn.edu.tju.ina.estuary.repo.CommentDao;
import cn.edu.tju.ina.estuary.service.CommentService;
import cn.edu.tju.ina.estuary.service.generic.GenericServiceImpl;

@Service("commentService")
@Transactional
public class CommentServiceImpl extends GenericServiceImpl<Comment, Integer> implements CommentService {

	private CommentDao commentDao;

	@Autowired
	public CommentServiceImpl(CommentDao commentDao) {
		super(commentDao);
		this.commentDao = commentDao;
	}
	
	@Override
	public Integer save(Comment c) {
		c.setAddTime(new Timestamp(System.currentTimeMillis()));
		return commentDao.save(c);
	}

	@Override
	public void delete(Comment c) {
		commentDao.delete(c);
	}

	@Override
	public void delete(int cid) {
		Comment c = new Comment();
		c.setId(cid);
		commentDao.delete(c);
	}
	
	@Override
	public boolean isCommentPublishedByUser(int uid, int cid) {
		Comment c = commentDao.findByTwoProperty("id", cid, "fromUser.id", uid);
		if (c != null) {
			commentDao.evict(c);
		}
		return (c != null);
	}

	@Override
	public List<Comment> findOldestCommentsByInfo(int infoid, int endid, int length) {
		return commentDao.findOldestOrderByTime("info.id", infoid, endid, length, true);
	}

	@Override
	public List<Comment> findNewestCommentsByInfo(int infoid, int startid) {
		return commentDao.findNewestOrderByTime("info.id", infoid, startid, true);
	}

}
