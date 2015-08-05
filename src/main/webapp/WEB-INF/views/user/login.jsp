<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;UTF-8"%>
<jsp:include page="../common/base.jsp"></jsp:include>

<html>

<head>
	<title>北洋信息墙-登录</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${base}static/css/base.css" />
	<script type="text/javascript" src="${base}static/js/jquery-1.7.2.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${base}static/css/img.css" />
	<script type="text/javascript" src="${base}static/js/jquery.slides.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${base}static/css/interface.css" />
	<script type="text/javascript" src="${base}static/js/interface.js"></script>
</head>

<body>
	<jsp:include page="../common/header.jsp"></jsp:include>

	<div class="login user_login">
		<ul>
			<li><span class="titleline"></span></li>
			<li><h3>用户登陆</h3></li>
			<li><p class="engtitle">User Login</p></li>
		</ul>
		<div class="c"></div>
		<form id="loginform" action="${base }j_spring_security_check" method="post"
			class="hrs_formHorizontal">
			<table width="385px" border="0" cellspacing="0" cellpadding="0">
				<tbody>
					<tr>
						<td>用户名：</td>
						<td><input id="UserName" name="j_username" type="text" value=""></td>
						<td><a href="${base }user/before_register">注册新用户</a></td>
					</tr>
					<tr>
						<td>登录密码：</td>
						<td><input autocomplete="off" id="Password" name="j_password"
							type="password" value=""></td>
						<td><a href="${base }user/findpw">忘记密码?</a></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><a class="btn_login" href="javascript:void(0)">登&nbsp;录</a></td>
						<td>&nbsp;</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<div class="c"></div>

	<script type="text/javascript" src="${base}static/js/md5.js"></script>
	<script type="text/javascript">
		$(function() {
			$('.btn_login')
					.live(
							'click',
							function() {
								//document.getElementById("Password").value = hex_md5(document
								//		.getElementById("Password").value);
								document.getElementById("loginform").submit();
							});

		})
	</script>

	<div class="c"></div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
