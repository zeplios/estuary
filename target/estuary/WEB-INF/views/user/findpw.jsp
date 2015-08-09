<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;UTF-8"%>
<jsp:include page="../common/base.jsp"></jsp:include>
<html>
<head>
<title>北洋信息墙-登录</title>
<base href="${base }">
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

	<div class="login user_register">
		<ul>
			<li><span class="titleline"></span></li>
			<li><h3>忘记密码</h3></li>
			<li><p class="engtitle">Forgot Password</p></li>
		</ul>
		<div class="c"></div>
		<form id="loginform" action="${base }user/resetpw" method="post">
			<table width="450px" border="0" cellspacing="0" cellpadding="0">
				<tbody>
					<tr>
						<td><em>*</em>&nbsp;用户名：</td>
						<td><input id="userName" name="userName" type="text" value="">
						</td>
						<td class="tl"><a class="sizeright">请填写您注册时的用户名</a></td>
					</tr>
					<tr>
						<td><em>*</em>&nbsp;邮箱地址：</td>
						<td><input id="forgetEmail" name="email" type="text" value="">
						</td>
						<td class="tl"><a class="sizeright">请填写您注册时的电子邮件</a></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><a class="btn_login" id="btn_resetPwd">确&nbsp;定</a></td>
						<td>&nbsp;</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

	<script type="text/javascript">
		$(function() {
			$('.btn_login').live('click', function() {
				document.getElementById("loginform").submit();

			});

		})
	</script>
	<div class="c"></div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
