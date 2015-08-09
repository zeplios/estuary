<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;UTF-8"%>
<jsp:include page="./common/base.jsp"></jsp:include>

<html>
<head>
	<title>北洋信息墙</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${base}static/css/index.css" />
	<link rel="stylesheet" type="text/css" href="${base}static/css/base.css" />
	<script type="text/javascript" src="${base}static/js/jquery-1.7.2.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${base}static/css/img.css" />
	<script type="text/javascript" src="${base}static/js/jquery.slides.min.js"></script>
	<script>
		$(function() {
			var aLi = $('#brand .bd-box li');
			var aImg = $('#brand .bd-box li img');
			var aSpan = $('#brand .bd-box li span');
			aLi.each(function(index) {
				$(this).mouseover(function() {
					aSpan.eq(index).stop();
					aImg.eq(index).stop();
					aImg.eq(index).css({
						zIndex : 1
					}).animate({
						top : 37,
						height : 0
					}, 80, '', function() {
						$(this).hide();
						aSpan.eq(index).show().css({
							zIndex : 2
						}).animate({
							top : 0,
							height : 150
						}, 80);
					});
				});
				$(this).mouseout(function() {
					aSpan.eq(index).stop();
					aImg.eq(index).stop();
					aSpan.eq(index).css({
						zIndex : 1
					}).animate({
						top : 37,
						height : 0
					}, 80, '', function() {
						$(this).hide();
						aImg.eq(index).show().css({
							zIndex : 2
						}).animate({
							top : 0,
							height : 150
						}, 80);
					});
				});
			});
		});
	</script>
</head>

<body>
	<jsp:include page="./common/header.jsp"></jsp:include>

	<div class="slide-bg">
		<div class="slide-wp">
			<div id="slides" class="slides">
				<div>
					<div class="slideChild">
						<!-- <a class="a-jd opa js-log-login" style="top:280px;left:310px;width:230px;height:70px;" href="http://www.97zzw.com"></a> -->
					</div>
					<img class="slideImg" src="${base}static/img/ads/banner2.jpg">
				</div>
			</div>
		</div>
	</div>

	<script>
		$(function() {

			$(".section ul li .rsp").hide();

			$(".section	 ul li").hover(function() {
				$(this).find(".rsp").stop().fadeTo(500, 0.5);
				$(this).find(".text").stop().animate({
					left : '0'
				}, {
					duration : 500
				});
			}, function() {
				$(this).find(".rsp").stop().fadeTo(500, 0);
				$(this).find(".text").stop().animate({
					left : '318'
				}, {
					duration : "fast"
				});
				$(this).find(".text").animate({
					left : '-318'
				}, {
					duration : 0
				});
			});

			/* 首页－认证 */
			$('#consubnav li a').bind('mouseover', function() {
				$('#consubnav li a').removeClass("consubnav_on");
				$(this).addClass("consubnav_on");
				var type = $(this).attr("data-type");
				var table = $("#consubtable_" + type);
				if (table != null) {
					$('.joblist').hide();
					$(table).show();
				}
			});
			//修改id暂时使广告失效
			$('#slides1').slidesjs({
				play : {
					active : false,
					effect : "fade",
					auto : true,
					interval : 1000
				},
				effect : {
					fade : {
						speed : 1500,
						crossfade : true
					}
				},
				pagination : {
					active : true
				},
				navigation : {
					active : false
				}
			});
		});
	</script>

	<div class="mainwidth">
		<div class="indexleft">
			<div class="indexlbox">
				<ul class="contitle">
					<li><span class="titleline"></span></li>
					<li><h3>最新信息</h3></li>
				</ul>
				<div id="consubnav" class="consubnav">
					<ul>
						<li><a class="consubnav_on" href="javascript:void(0);" data-type="1">社团活动</a></li>
						<li><a href="javascript:void(0);" data-type="2" class="">招聘兼职</a></li>
						<li><a href="javascript:void(0);" data-type="3" class="">失物招领</a></li>
						<li><a href="javascript:void(0);" data-type="4" class="">互帮互助</a></li>
						<li><a href="javascript:void(0);" data-type="5" class="">二手买卖</a></li>
					</ul>
					<span class="morespace"><a class="more" href="#"></a></span>
				</div>

				<div class="c"></div>

				<ul id="consubtable_1" class="joblist" style="display: block;">
					<c:forEach items="${activities }" var="act">
					<li><a class="ellipsis" href="${base }info/detail/${act.id}.html?type=${act.type }"
						target="_blank">${act.title}</a><span
						style="float: right;">[${act.time}]</span></li>
					</c:forEach>
				</ul>
				<ul id="consubtable_2" class="joblist" style="display: none;">
					<c:forEach items="${recruits }" var="rec">
					<li><a class="ellipsis" href="${base }info/detail/${rec.id}.html?type=${rec.type }"
						target="_blank">${rec.title}</a><span
						style="float: right;">[${rec.time}]</span></li>
					</c:forEach>
				</ul>
				<ul id="consubtable_3" class="joblist" style="display: none;">
					<li><a class="ellipsis" href="#" title="产品经理">信息与网络协会</a></li>
					<li><a class="ellipsis" href="#" title="产品经理">信息与网络协会</a></li>
				</ul>
				<ul id="consubtable_4" class="joblist" style="display: none;">

					<li><a class="ellipsis" href="#" title="产品经理">信息与网络协会</a></li>
					<li><a class="ellipsis" href="#" title="产品经理">信息与网络协会</a></li>
					<li><a class="ellipsis" href="#" title="产品经理">信息与网络协会</a></li>
				</ul>
				<ul id="consubtable_5" class="joblist" style="display: none;">
					<li><a class="ellipsis" href="#" title="产品经理">信息与网络协会</a></li>
					<li><a class="ellipsis" href="#" title="产品经理">信息与网络协会</a></li>
					<li><a class="ellipsis" href="#" title="产品经理">信息与网络协会</a></li>
					<li><a class="ellipsis" href="#" title="产品经理">信息与网络协会</a></li>
				</ul>

			</div>

			<div class="indexlbox">
				<ul class="contitle">
					<li><h3>入驻社团</h3></li>
					<li><p class="engtitle">入驻社团</p></li>
				</ul>
				<span class="indexsubtitle"></span>

				<div class="section">
					<ul class="clearfix">
					<c:forEach items="${orgs }" var="org">
						<li>
							<div class="photo">
								<img src="/avatar/${org.avatar}" width="100"
									height="100" />
							</div>
							<div class="rsp"></div>
							<div class="text">
								<h3><a href="#">${org.username}</a></h3>
							</div>
						</li>
					</c:forEach>
					</ul>
					<div class="clear"></div>
				</div>

			</div>
		</div>

		<!-- 右侧栏开始 -->
		<div class="indexright">
			<div class="indexrbox">
				<ul class="contitle">
					<li><span class="titleline"></span></li>
					<li><h3>网站公告</h3></li>
				</ul>
				<span class="morespace"><a class="more" href=""></a></span>
				<div class="c"></div>
				<ul class="rightlist" style="min-height: 131px;">
					<li><a class="ellipsis" href="rules#no1">关于发布活动信息的规则</a></li>
					<li><a class="ellipsis" href="rules#no2">申请社团认证的好处</a></li>
					<li><a class="ellipsis" href="rules#no3">申请认证社团的方法</a></li>
					<li><a class="ellipsis" href="rules#no4">申请加入管理组</a></li>
				</ul>
				<div class="rightlistbtn">
					<span class="fl"><a class="btn_inapply" href="__APP__/vip"
						target="_blank"></a></span> <span class="fr"><a
						class="btn_studioapply" href="#" target="_blank"></a></span>
				</div>
			</div>
			<!-- 	网站公告结束 -->
		</div>
		<!-- 右侧栏结束 -->
	</div>
	<!-- main结束 -->
	<div class="c"></div>
	<jsp:include page="./common/footer.jsp"></jsp:include>
</body>
</html>
