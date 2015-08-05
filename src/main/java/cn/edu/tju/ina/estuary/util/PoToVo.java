package cn.edu.tju.ina.estuary.util;

import java.util.ArrayList;
import java.util.List;

import cn.edu.tju.ina.estuary.domain.info.Activity;
import cn.edu.tju.ina.estuary.domain.info.Album;
import cn.edu.tju.ina.estuary.domain.info.Info;
import cn.edu.tju.ina.estuary.domain.info.Lost;
import cn.edu.tju.ina.estuary.domain.info.Market;
import cn.edu.tju.ina.estuary.domain.info.Photo;
import cn.edu.tju.ina.estuary.domain.info.Recruit;
import cn.edu.tju.ina.estuary.domain.user.Collect;
import cn.edu.tju.ina.estuary.domain.user.Comment;
import cn.edu.tju.ina.estuary.domain.user.Notice;
import cn.edu.tju.ina.estuary.domain.user.Thumbup;
import cn.edu.tju.ina.estuary.domain.user.User;
import cn.edu.tju.ina.estuary.vo.info.FullActivityVo;
import cn.edu.tju.ina.estuary.vo.info.FullAlbumVo;
import cn.edu.tju.ina.estuary.vo.info.FullInfoVo;
import cn.edu.tju.ina.estuary.vo.info.FullLostVo;
import cn.edu.tju.ina.estuary.vo.info.FullMarketVo;
import cn.edu.tju.ina.estuary.vo.info.FullPhotoVo;
import cn.edu.tju.ina.estuary.vo.info.FullRecruitVo;
import cn.edu.tju.ina.estuary.vo.info.SimpleActivityVo;
import cn.edu.tju.ina.estuary.vo.info.SimpleAlbumVo;
import cn.edu.tju.ina.estuary.vo.info.SimpleInfoVo;
import cn.edu.tju.ina.estuary.vo.info.SimpleLostVo;
import cn.edu.tju.ina.estuary.vo.info.SimpleMarketVo;
import cn.edu.tju.ina.estuary.vo.info.SimplePhotoVo;
import cn.edu.tju.ina.estuary.vo.info.SimpleRecruitVo;
import cn.edu.tju.ina.estuary.vo.user.CollectVo;
import cn.edu.tju.ina.estuary.vo.user.CommentVo;
import cn.edu.tju.ina.estuary.vo.user.NoticeVo;
import cn.edu.tju.ina.estuary.vo.user.SimpleUserVo;
import cn.edu.tju.ina.estuary.vo.user.ThumbupVo;
import cn.edu.tju.ina.estuary.vo.user.UserVo;

public class PoToVo {
	
	public static SimpleUserVo transformUserToSimple(User u) {
		return new SimpleUserVo(u);
	}
	
	public static UserVo transformUserToFull(User u) {
		return new UserVo(u);
	}
	
	public static SimpleInfoVo transformInfoToSimple(Info info) {
		if (info instanceof Activity)
			return new SimpleActivityVo((Activity)info);
		else if (info instanceof Recruit)
			return new SimpleRecruitVo((Recruit)info);
		else if (info instanceof Lost)
			return new SimpleLostVo((Lost)info);
		else if (info instanceof Market)
			return new SimpleMarketVo((Market)info);
		else if (info instanceof Photo)
			return new SimplePhotoVo((Photo)info);
		else if (info instanceof Album)
			return new SimpleAlbumVo((Album)info);
		else 
			return new SimpleInfoVo(info);
	}
	
	public static FullInfoVo transformInfoToFull(Info info) {
		if (info instanceof Activity)
			return new FullActivityVo((Activity)info);
		else if (info instanceof Recruit)
			return new FullRecruitVo((Recruit)info);
		else if (info instanceof Lost)
			return new FullLostVo((Lost)info);
		else if (info instanceof Market)
			return new FullMarketVo((Market)info);
		else if (info instanceof Photo)
			return new FullPhotoVo((Photo)info);
		else if (info instanceof Album)
			return new FullAlbumVo((Album)info);
		else 
			return new FullInfoVo(info);
	}
	
	public static List<SimpleInfoVo> transformInfoListToSimple(List<Info> infos) {
		List<SimpleInfoVo> simpleInfos = new ArrayList<SimpleInfoVo>();
		for (Info i : infos) {
			simpleInfos.add(transformInfoToSimple(i));
		}
		return simpleInfos;
	}
	
	public static List<CollectVo> transformCollectListToSimple(List<Collect> collects) {
		List<CollectVo> colls = new ArrayList<CollectVo>();
		for (Collect c : collects) {
			CollectVo cv = new CollectVo(c);
			cv.setInfo(transformInfoToSimple(c.getInfo()));
			colls.add(cv);
		}
		return colls;
	}
	
	public static List<SimpleUserVo> transformCollectListToUsers(List<Collect> collects) {
		List<SimpleUserVo> users = new ArrayList<SimpleUserVo>();
		for (Collect c : collects) {
			users.add(new SimpleUserVo(c.getUser()));
		}
		return users;
	}
	
	public static List<ThumbupVo> transformThumbupListToSimple(List<Thumbup> thumbups) {
		List<ThumbupVo> thums = new ArrayList<ThumbupVo>();
		for (Thumbup t : thumbups) {
			ThumbupVo tv = new ThumbupVo(t);
			tv.setInfo(transformInfoToSimple(t.getInfo()));
			thums.add(tv);
		}
		return thums;
	}
	
	public static List<SimpleUserVo> transformThumbupListToUsers(List<Thumbup> thumbups) {
		List<SimpleUserVo> users = new ArrayList<SimpleUserVo>();
		for (Thumbup t : thumbups) {
			users.add(new SimpleUserVo(t.getUser()));
		}
		return users;
	}

	public static List<CommentVo> transformCommentListForInfo(List<Comment> comments) {
		List<CommentVo> comms = new ArrayList<>();
		for (Comment c : comments) {
			CommentVo cv = new CommentVo(c);
//			cv.setInfo(PoVoTransformator.transformInfoToSimple(c.getInfo()));
			comms.add(cv);
		}
		return comms;
	}
	
	public static List<NoticeVo> transformNoticeListForInfo(List<Notice> notification) {
		List<NoticeVo> nots = new ArrayList<>();
		for (Notice n : notification) {
			NoticeVo nv = new NoticeVo(n);
//			cv.setInfo(PoVoTransformator.transformInfoToSimple(c.getInfo()));
			nots.add(nv);
		}
		return nots;
	}
}
