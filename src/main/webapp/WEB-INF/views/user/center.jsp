<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;UTF-8"%>
<jsp:include page="../common/base.jsp"></jsp:include>
<html>
<head>
<title>北洋信息墙-个人中心</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${base}static/css/base.css" />
<script type="text/javascript" src="${base}static/js/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="${base}static/css/img.css" />
<script type="text/javascript" src="${base}static/js/jquery.slides.min.js"></script>
<link rel="stylesheet" type="text/css" href="${base}static/css/interface.css" />
<script type="text/javascript" src="${base}static/js/interface.js"></script>

<!-- 用户中心专用 -->
<script type="text/javascript" src="${base}static/js/jquery-ui-min.js"></script>
<link rel="stylesheet" type="text/css" href="${base}static/css/jquery-ui.css">
<script type="text/javascript" src="${base}static/js/datepicker_zh.js"></script>
<script type="text/javascript" src="${base}static/js/jquery-ui-timepicker-addon.js"></script>
<link rel="stylesheet" type="text/css" href="${base}static/css/usercenter.css" />
<link rel="stylesheet" type="text/css" href="${base}static/css/personal.css" />
<script type="text/javascript" src="${base}static/js/kindeditor/kindeditor.js"></script>
<script type="text/javascript" src="${base}static/js/kindeditor/lang/zh_CN.js"></script>
</head>

<body>
	<jsp:include page="../common/header.jsp"></jsp:include>

	<div class="user_main" style="min-height: 600px; overflow: auto">
		<div class="user_left">

			<div class="sidebar_head">
				<span class="head_portrait"><img
					src="/avatar/${currentUser.avatar}" alt="用户名" width="60px"
					height="60px"></span> <span class="sidebar_username"><a
					href="#">${currentUser.username}</a></span>
			</div>

			<div class="tb_" id="tb_">
				<ul>
					<!--   <li  id="tb_1"  class="hovertab"   ><span></span><a  href="__GROUP__/User/notice">活动小秘</a></li> -->
					<!-- <li  id="tb_2"  class="hovernomal" ><span></span><a  href="#">我的关注</a></li> -->
					<li id="tb_3" class="hovernomal"><span></span><a
						href="${base }user/upload/activity">发布信息</a></li>
					<!--                             <li  id="tb_4"  class="hovernomal" ><span></span><a  href="__GROUP__/User/mact">活动管理</a></li>
                            <li  id="tb_5"  class="hovernomal" ><span></span><a  href="__GROUP__/User/comment">评论管理</a></li> -->
					<li id="tb_6" class="hovernomal"><span></span><a
						href="${base }user/center/config">个人设置</a></li>
				</ul>
			</div>

		</div>
		<!--侧边栏结束-->
		<div class="user_right">
			<jsp:include page="center_${pageType }.jsp"></jsp:include>
		</div>
	</div>

	<div class="c"></div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
