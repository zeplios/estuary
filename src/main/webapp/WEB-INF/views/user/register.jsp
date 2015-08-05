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
<link rel="stylesheet" type="text/css" href="css/img.css" />
<script type="text/javascript" src="${base}static/js/jquery.slides.min.js"></script>
<link rel="stylesheet" type="text/css" href="${base}static/css/interface.css" />
<script type="text/javascript" src="${base}static/js/interface.js"></script>
</head>

<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="login user_register">
		<form id="myregform" method="post" action="${base}user/register">
			<ul>
				<li><span class="titleline"></span></li>
				<li><h3>用户注册</h3></li>
				<li><p class="engtitle">User Register</p></li>
			</ul>
			<div class="c"></div>
			<table width="655px" border="0" cellspacing="0" cellpadding="0">
				<tbody>
					<tr>
						<td><em>*</em>&nbsp;用户名：</td>
						<td><input type="text" name="username" id="UserName"/></td>
						<td><form:errors class="invalid" path="username"/></td>
						<td class="sizeright">6-20位，由字母、数字以及下划线组成</td>
					</tr>
					<tr>
						<td><em>*</em>&nbsp;邮箱地址：</td>
						<td><input type="text" name="email" id="Email"/></td>
						<td><form:errors class="invalid" path="email"/></td>
						<td class="sizeright">注：此邮件将作为接收通知的邮件地址且不可变更，请仔细填写</td>
					</tr>
					<tr>
						<td><em>*</em>&nbsp;密码：</td>
						<td><input type="password" name="password" id="Password"/></td>
						<td><form:errors class="invalid" path="password"/></td>
						<td class="sizeright">8-20位，必须包含字母和数字，区分大小写</td>
					</tr>
					<tr>
						<td><em>*</em>&nbsp;确认密码：</td>
						<td><input type="password" name="confirmPassword" id="Password"/></td>
						<td><form:errors class="invalid" path="password"/></td>
						<td class="sizeright">重复输入密码</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><a id="btn_register" class="btn_login"
							href="javascript:void(0);">确&nbsp;定</a></td>
						<td>&nbsp;</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

	<script type="text/javascript">
		$(function() {
			$('.btn_login').live('click', function() {
				document.getElementById("myregform").submit();
			});

		})
	</script>
	<div class="c"></div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
