package cn.edu.tju.ina.estuary.repo;

import java.util.List;

import cn.edu.tju.ina.estuary.domain.user.Comment;
import cn.edu.tju.ina.estuary.repo.generic.GenericDao;

public interface CommentDao extends GenericDao<Comment, Integer> {

	List<Comment> findNewestOrderByTime(String name, Object value, int startid, boolean joinInfo);
	List<Comment> findOldestOrderByTime(String name, Object value, int endid, int offset, boolean joinInfo);
	
}
