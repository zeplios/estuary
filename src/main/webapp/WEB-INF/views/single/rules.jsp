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
<link rel="stylesheet" type="text/css" href="static/css/base.css" />
<script type="text/javascript" src="static/js/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="static/css/img.css" />
<script type="text/javascript" src="static/js/jquery.slides.min.js"></script>
<link rel="stylesheet" type="text/css" href="static/css/interface.css" />
<script type="text/javascript" src="static/js/interface.js"></script>
</head>

<body>
	<jsp:include page="../common/header.jsp"></jsp:include>

	<script src="static/js/jquery.wookmark.js"></script>
	<!-- <script src="__ROOT__/js/jquery.imagesloaded.js"></script> -->
	<script type="text/javascript">
		$(function() {
			//*********首页广告图上显示隐藏赞和关注 *********
			$('li .img_ ').live({
				mouseover : function() {
					$(this).find('.actions').css("display", "block");
					$(this).find('.timearea').css("display", "block");
				},
				mouseout : function() {
					$(this).find('.actions').css("display", "none");
					$(this).find('.timearea').css("display", "none");
				}
			});
			//****************end*****************
		})
	</script>

	<div class="mainwidth joblist">
		<div class="boxstyle maincontent">
			<ul class="contitle">
				<li><h3>
						活动发布规则<a name="no1"></a>
					</h3></li>
			</ul>
			<span class="subtitle"></span>
			<div class="c"></div>
			<div class="txtintro">
				<p class="text">1、禁止发布法律禁止的图片和信息</p>
				<p class="text">
					2、社团认证用户只允许发布社团组织的活动，禁止发布商业广告，一旦发现违规，撤销社团认证等级并永久黑名单</p>
				<p class="text">发布的活动，管理组有权进行禁止、删除、宣传等一系列操作</p>
				<p class="text">一切解释权归管理组所有</p>
			</div>


			<ul class="contitle">
				<li><h3>
						社团认证有什么好处<a name="no2"></a>
					</h3></li>
			</ul>
			<div class="c"></div>
			<div class="txtintro">
				<p class="text">我们是学生社团，致力于打造信息网络化的校园</p>
				<p class="text">我们也是社团，知道宣传的重要性，也遇到过灯箱布没地挂的尴尬境界</p>
				<p class="text">你可以直接发布活动信息不需要经过审核</p>
				<p class="text">我们后期会选择性的通过人人、微信公众账号、手机APP、网站banner等多种方式帮助您对活动进行推广</p>
				<p class="text">其他人可以通过认证社团页了解您的社团</p>

				<p class="text">还有其他福利，活动反馈良好的话会在首页人气社团栏显示，而且我们每学期会免费为部分社团制作宣传单页挂在我们的服务器上，让更多的人了解您的社团等！</p>
				<p class="text">如何进行社团认证，请接着往下看</p>
			</div>
			<ul class="contitle">
				<li><h3>
						如何申请社团认证<a name="no3"></a>
					</h3></li>
			</ul>
			<div class="c"></div>
			<div class="txtintro">
				<p class="text">1.您需要先在网站注册一个账号</p>
				<p class="text">2.注册账号后需要将认证资料打包发送到邮箱ina#tju.edu.cn(注意将#换成@)，邮件主题格式：【申请认证】+社团名</p>
				<p class="text">3.加入INA外联QQ群：318448802咨询相关问题</p>
				<p class="text">认证资料包括注册用户名、社团头像、不多于五行每行字数不多于20字的展示信息（包括但不限于人人主页连接、QQ群）、社团负责人或者社团代表的联系方式</p>
				<p class="text">
					查看<a href="__APP__/vip" target="_blank">认证社团单页连接</a>(排名顺序暂时按照认证时间，先认证的在最上方)
				</p>
			</div>

			<ul class="contitle">
				<li><h3>
						管理组是干什么的<a name="no4"></a>
					</h3></li>
			</ul>
			<div class="c"></div>
			<div class="txtintro">
				<p class="text">职责：INFO管理组成员即平台小编，负责网站的管理</p>
				<p class="text">部门：INFO管理组成员属于信息与网络协会运营部</p>
				<p class="text">
					加入方式：通过<a href="http://ina.tju.edu.cn" target="_blank"><img
						src="__ROOT__/images/btn_apply.png"></a>填写自己的信息，请注意我们社团是全校社团中没有年级之分的社团(部门填写运营部info墙管理组)
				</p>
			</div>


			<ul class="contitle">
				<li><h3>
						INA广告位<a name="no5"></a>
					</h3></li>
			</ul>
			<div class="c"></div>
			<div class="txtintro">
				<p class="text">INA旗下seeworld视频中心目前已有注册用户9000名左右，日均浏览量3000；iptv日均浏览量5000</p>
				<p class="text">为了充分发挥网站的作用，IINA决定开放seeworld和iptv的广告位，任何组织和个人均可申请</p>
				<p class="text">广告位将会从2013年11月18日开始正式运行，我们的人人主页“天大INA"将会在第一时间公布具体的广告位细则和申请流程</p>
				<p class="text">有任何问题可以发送邮件到ina#tju.edu.cn(将#换成@)</p>
			</div>
		</div>
		<div class="rightbox">
			<span class="rightitle"><h3>平台规范</h3></span>
			<ul class="rightqa qastyle">
				<li><a href="rules#no1">活动发布规则</a></li>
				<li><a href="rules#no2">申请社团认证有什么好处</a></li>
				<li><a href="rules#no3">如何申请社团认证</a></li>
				<li><a href="rules#no4">管理组是干什么的？</a></li>
				<li><a href="rules#no5">INA网站广告位即将开放申请</a></li>
			</ul>
		</div>
	</div>

	<script type="text/javascript">
		$('.zan').click(
				function() {
					var uid = "{$Think.session.user.uid}";
					//alert(typeof(uid));
					if (uid == null || uid.length == 0) {
						var d1 = art.dialog({
							title : "错误信息",
							width : 300,
							height : 50,
							content : "请先登录",
							lock : true,
							beforeunload : function() {
								// window.location.href="__APP__/Index/login";
							}
						});
						d1.time(1500);
					}

					var aid = $(this).attr("aid");
					//alert(aid);
					$.ajax({
						url : '__APP__/Api/addzan',
						type : 'post',
						data : {
							aid : aid
						},
						datatype : 'json',
						async : false,
						success : function(json) {
							if (json.error == '0') {
								//alert('a[aid='+aid+']');
								//伪类选择器,满足aid条件下中的span的样式
								$('a[aid="' + aid + '"] span').removeClass(
										'beforeclick').addClass('afterclick')
										.html('已赞');
								$('a[aid="' + aid + '"]').removeClass('zan')
										.addClass('yizan');
							}
						}
					});
				});

		$('.sc').click(
				function() {

					var uid = "{$Think.session.user.uid}";
					//alert(typeof(uid));
					if (uid == null || uid.length == 0) {
						var d1 = art.dialog({
							title : "错误信息",
							width : 300,
							height : 50,
							content : "请先登录",
							lock : true,
							beforeunload : function() {

							}
						});
						d1.time(1500);
					}

					var aid = $(this).attr("coaid");
					//alert(aid);
					$.ajax({
						url : '__APP__/Api/addcol',
						type : 'post',
						data : {
							aid : aid
						},
						datatype : 'json',
						async : false,
						success : function(json) {
							if (json.error == '0') {
								//alert('a[aid='+aid+']');
								//伪类选择器,满足aid条件下中的span的样式
								$('a[coaid="' + aid + '"] span').removeClass(
										'beforeclick').addClass('afterclick')
										.html('已收藏');
								$('a[coaid="' + aid + '"]').removeClass('sc')
										.addClass('yisc');
							}
						}
					});
				});

		$('.yizan').click(
				function() {
					var uid = "{$Think.session.user.uid}";
					//alert(typeof(uid));
					if (uid == null || uid.length == 0) {
						var d2 = art.dialog({
							title : "错误信息",
							width : 300,
							height : 50,
							content : "请先登录",
							lock : true,
							beforeunload : function() {
								window.location.href = "__APP__/Index/login";
							}
						});
						d2.time(1500);
					}

					var aid = $(this).attr("aid");
					//alert(aid);
					$.ajax({
						url : '__APP__/Api/delzan',
						type : 'post',
						data : {
							aid : aid
						},
						datatype : 'json',
						async : false,
						success : function(json) {
							if (json.error == '0') {
								//alert('a[aid='+aid+']');
								//伪类选择器,满足aid条件下中的span的样式
								$('a[aid="' + aid + '"] span').removeClass(
										'afterclick').addClass('beforeclick')
										.html('赞');
								$('a[aid="' + aid + '"]').removeClass('yizan')
										.addClass('zan');
							}
						}
					});
				});

		$('.yisc').click(
				function() {
					var uid = "{$Think.session.user.uid}";
					//alert(typeof(uid));
					if (uid == null || uid.length == 0) {
						var d2 = art.dialog({
							title : "错误信息",
							width : 300,
							height : 50,
							content : "请先登录",
							lock : true,
							beforeunload : function() {
								// window.location.href="__APP__/Index/login";
							}
						});
						d2.time(1500);
					}

					var aid = $(this).attr("coaid");
					//alert(aid);
					$.ajax({
						url : '__APP__/Api/delcol',
						type : 'post',
						data : {
							aid : aid
						},
						datatype : 'json',
						async : false,
						success : function(json) {
							if (json.error == '0') {
								//alert('a[aid='+aid+']');
								//伪类选择器,满足aid条件下中的span的样式
								$('a[coaid="' + aid + '"] span').removeClass(
										'afterclick').addClass('beforeclick')
										.html('收藏');
								$('a[coaid="' + aid + '"]').removeClass('yisc')
										.addClass('sc');
							}
						}
					});
				});
	</script>
	<script type="text/javascript">
		/*
		原理：1.把所有的li的高度值放到数组里面
		     2.第一行的top都为0
		   3.计算高度值最小的值是哪个li
		   4.把接下来的li放到那个li的下面
		作者：刘晓帆
		博客地址：[url]http://liuxiaofan.com[/url]
		编写时间：2012年6月9日
		 */
		var margin = 10;//这里设置间距
		var li = $("li");//这里是区块名称
		var li_W = li[0].offsetWidth + margin;//取区块的实际宽度（包含间距，这里使用源生的offsetWidth函数，不适用jQuery的width()函数是因为它不能取得实际宽度，例如元素内有pandding就不行了）
		function endless() {//定义成函数便于调用
			var h = [];//记录区块高度的数组
			var n = document.documentElement.offsetWidth / li_W | 0;// 窗口的宽度除以区块宽度就是一行能放几个区块
			for (var i = 0; i < li.length; i++) {//有多少个li就循环多少次
				li_H = li[i].offsetHeight;//获取每个li的高度
				if (i < n) {//n是一行最多的li，所以小于n就是第一行了
					h[i] = li_H;//把每个li放到数组里面
					li.eq(i).css("top", 0);//第一行的Li的top值为0
					li.eq(i).css("left", i * li_W);//第i个li的左坐标就是i*li的宽度
				} else {
					min_H = Math.min.apply(null, h);//取得数组中的最小值，区块中高度值最小的那个
					minKey = getarraykey(h, min_H);//最小的值对应的指针
					h[minKey] += li_H + margin;//加上新高度后更新高度值
					li.eq(i).css("top", min_H + margin);//先得到高度最小的Li，然后把接下来的li放到它的下面
					li.eq(i).css("left", minKey * li_W); //第i个li的左坐标就是i*li的宽度
				}
				//把区块的序号和它的高度值写入对应的区块H3标题里面
			}
		}
		/* 使用for in运算返回数组中某一值的对应项数(比如算出最小的高度值是数组里面的第几个) */
		function getarraykey(s, v) {
			for (k in s) {
				if (s[k] == v) {
					return k;
				}
			}
		}
		/*这里一定要用onload，因为图片不加载完就不知道高度值*/
		window.onload = function() {
			endless();
		};
		/*浏览器窗口改变时也运行函数*/
		window.onresize = function() {
			endless();
		};
	</script>

	<div class="c"></div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
