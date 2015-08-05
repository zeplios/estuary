<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../common/base.jsp"></jsp:include>

<!DOCTYPE html>
<html lang="zh-cn">
<head>

<title>信息墙接口文档</title>

<link href="static/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="data:text/css;charset=utf-8,"
	data-href="static/dist/css/bootstrap-theme.min.css" rel="stylesheet"
	id="bs-theme-stylesheet">

<link href="static/dist/patch.css" rel="stylesheet">
<link href="static/dist/docs.min.css" rel="stylesheet">

<style>
table {font-size:14px;}
</style>
</head>
<body>
	<a class="sr-only sr-only-focusable" href="#content">Skip to main
		content</a>
		
	<header class="navbar navbar-static-top bs-docs-nav" id="top" role="banner">
      <div class="container">
        <div class="navbar-header">
          <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target=".bs-navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a href="../" class="navbar-brand">接口文档</a>
        </div>
        <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
          <ul class="nav navbar-nav">
            <li class="active">
              <a href="${base }">信息墙首页</a>
            </li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#">INA</a></li>
          </ul>
        </nav>
      </div>
    </header>

	<!-- Docs page layout -->
    <div class="bs-docs-header" id="content">
      <div class="container">
        <h1>信息墙接口文档</h1>
        <p>信息墙移动端访问REST服务的接口、参数、动作和返回值说明。</p>
      </div>
    </div>

	<div class="container bs-docs-container">
		<div class="row">
			<div class="col-md-9" role="main">
				<div class="bs-docs-section">
					<h1 id="api" class="page-header">接口说明</h1>
					<p class="lead">这里描述所有的rest接口，供移动端访问。</p>
					<c:forEach items="${apis }" var="api">
						<h3 id="api-${api.name }">${api.title }</h3>
						<p>${api.desc }</p>
						<div class="panel panel-default">
							<div class="panel-heading">${api.method }  ${api.url }</div>
							<div class="panel-body">
								返回JSON串：${api.jsonRet }<br /> 参数列表:
							</div>
							<table class="table">
								<c:forEach items="${api.params }" var="reqparam">
									<tr>
										<td>&nbsp;</td>
										<td>${reqparam.name }</td>
										<td>${reqparam.desc }</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</c:forEach>
				</div>

				<div class="bs-docs-section">
					<h1 id="vo" class="page-header">实体对象说明</h1>
					<p class="lead">服务端返回的数据，中间会包含一些JSON对象或JSON对象的数组，比如代表user的JSON对象，在本接口文档中出现该JSON对象时，统一用一个如User的标识符替代，这些Xxx中具体有哪些字段，这一部分给出全部的说明。</p>
					<c:forEach items="${vos }" var="vo">
						<h3 id="vo-${vo.name }">${vo.name }
							<c:if test="${not empty vo.base }">继承自 <a
									href="#vo-${vo.base }"> ${vo.base }</a>
							</c:if>
						</h3>
						<p>${vo.desc }</p>
						<table class="table table-striped">
							<tr>
								<th>字段</th>
								<th>类型</th>
								<th>备注</th>
							</tr>
							<c:forEach items="${vo.fields }" var="field">
								<tr>
									<td>${field.name }</td>
									<td><c:if test="${field.innerType == false}">
											<a href="#vo-${field.type }">
										</c:if>${field.type }<c:if test="${field.innerType == false}">
											</a>
										</c:if></td>
									<td>${field.desc }</td>
								</tr>
							</c:forEach>
						</table>
					</c:forEach>
				</div>


			</div>
			<div class="col-md-3">
				<div class="bs-docs-sidebar hidden-print hidden-xs hidden-sm"
					role="complementary">
					<ul class="nav bs-docs-sidenav">
						<li><a href="#api">接口说明</a>
							<ul class="nav">
								<c:forEach items="${apis }" var="api">
									<li><a href="#api-${api.name }">${api.title }</a></li>
								</c:forEach>
							</ul></li>
						<li><a href="#vo">实体对象说明</a>
							<ul class="nav">
								<c:forEach items="${vos }" var="vo">
									<li><a href="#vo-${vo.name }">${vo.name }</a></li>
								</c:forEach>
							</ul></li>


					</ul>
					<a class="back-to-top" href="#top"> 返回顶部 </a> <a href="#"
						class="bs-docs-theme-toggle" role="button"> 主题预览 </a>

				</div>
			</div>
		</div>
	</div>

	<script src="static/dist/jquery-1.11.1.min.js"></script>
	<script src="static/dist/js/bootstrap.min.js"></script>
	<script src="static/dist/docs.min.js"></script>

</body>
</html>