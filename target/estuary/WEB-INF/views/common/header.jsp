<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;UTF-8"%>
<%-- <p>Reason:${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p> --%>
<div class="header">
	<div class="mainwidth">
		<c:choose>
		<%-- <c:when test="${auth != 'anonymousUser'}"> use: ${auth.username } --%>
		<c:when test="${!empty currentUser}">
		<ul>
			<li><a href="${base }j_spring_security_logout">注销登录</a></li>
			<li class="headline"></li>
			<li><a href="${base }user/center/config">个人主页</a></li>
			<li class="headline"></li>
			<li><a href="javascript:void(0);" target="_blank">您好：${currentUser.username }</a></li>
		</ul>
		</c:when>
		<c:otherwise>
		<ul>
			<li><a href="http://ina.tju.edu.cn" target="_blank">关于INA Studio</a></li>
			<li class="headline"></li>
			<li><a href="${base }user/before_register">注册</a></li>
			<li class="headline"></li>
			<li><a href="${base }user/login">登录</a></li>
		</ul>
		</c:otherwise>
		</c:choose>
		<span class="logo"></span>
		<ul class="nav">
			<li><a href="">首页</a></li>
			<li><a href="${base }info/list?type=1">社团活动</a></li>
			<li><a href="${base }info/list?type=2">招聘信息</a></li>
			<li><a href="http://info.app.tju.edu.cn/bbs">论坛</a></li>
		</ul>
	</div>
</div>


