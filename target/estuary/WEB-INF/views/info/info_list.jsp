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

	<script src="${base}static/js/jquery.wookmark.js"></script>
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
	<!-- <div style="width:100%;height:30px;margin:50px 0px;">
<form action="__APP__/Index/search" method="post" style="display:inline">
<input name="key" type="text">
<input name="submit" value="搜索" type="submit">
</form>|
<form action="__APP__/Index/recent" method="post" style="display:inline">
<input name="submit" type="submit" value="最近三天">
</form>
</div> -->
	<div class="mainwidth">
		<!-- 开始循环输出图片-->
		<div id="main" role="main">
			<ul id="lxf-box" class="tiles">
				<!--**********************************图片列表模版*********************************
                  <li data-filter-class="[1]" style="position: absolute; left: 0px; top: 0px;"><div class="img_"><a href="/Index/actInfo?aid=164"><div class="img_contenner"><div class="actions" style="display: none;"><div class="left"><span class="btn"><strong><em></em>关注</strong></span></div><div class="right"><span class="btn"><strong><em></em>喜欢</strong></span></div></div><img class="img" src="/uploads/s_52466298d6874.jpg" height="262"></div></a><div class="img_introduce"><h3><span class="cell_tit">天大天大好声音天大好声音天大好声音天大好声音好声音</span> </h3><div class="img_footer"><a href="#"><span class="tx-clip"><img src="/avatar/info_avatar_10070.jpg "></span></a><div class="cont"><a href="#">qingcu</a><p>发布于2013-09-26 00:00:00</p></div></div></div></div></li>
                  **********************************************************************************-->
			</ul>
			<div class="clearfix"></div>
		</div>
		<div id="nomore"
			style="display: none; margin-top: 100px; text-align: center; font-size: 30px;">
			<b>没有更多</b>
		</div>
	</div>
	<!-- 开始循环输出图片end-->
	</div>
	<script type="text/javascript">
		var handler = null;
		var page = 1;
		var isLoading = false;
		//var apiURL = '<?php echo $apiURL ?>';
		var apiURL = "${base}info/asyn/list?type=${type}";
		// var atitle= '{$atitle}';  搜索关键字？
		// var smalltype="{$smalltype}";

		// Prepare layout options.
		var wookmark_options = {
			autoResize : true, // This will auto-update the layout when the browser window is resized.
			container : $('#main'), // Optional, used for some extra CSS styling
			offset : 11, // Optional, the distance between grid items
			itemWidth : 236
		// Optional, the width of a grid item
		};

		/**
		 * When scrolled all the way to the bottom, add more tiles.
		 */
		function onScroll(event) {
			// Only check when we're not still waiting for data.
			if (!isLoading) {
				// Check if we're within 100 pixels of the bottom edge of the broser window.
				var closeToBottom = ($(window).scrollTop() + $(window).height() > $(
						document).height() - 100);
				if (closeToBottom) {
					loadData();
				}
			}
		};

		/**
		 * Refreshes the layout.
		 */
		function applyLayout() {
			// Clear our previous layout handler.
			//if(handler) handler.wookmarkClear();

			// Create a new layout handler.
			handler = $('.tiles li');
			handler.wookmark(wookmark_options);
		};

		/**
		 * Loads data from the API.
		 */
		function loadData() {
			isLoading = true;
			//$('#loaderCircle').show();
			$.ajax({
				url : apiURL,
				dataType : 'json',
				data : {
					page : page
				},// Page parameter to make sure we load new data
				success : onLoadData
			});

		};

		/**
		 * Receives data from the API, creates HTML for images and updates the layout
		 */
		function onLoadData(data) {
			isLoading = false;
			// $('#loaderCircle').hide();
			if (data.infos.length == 0) {
				$('#nomore').css('display', 'block');
				data = "";
			}
			// Increment page index for future calls.
			page++;
			// Create HTML for the images.
			var html = '';
			var i = 0, length = data.infos.length, info;
			for (; i < length; i++) {
				info = data.infos[i];
				html += '<li><div class="img_"><div class="img_contenner" class="ic_container">';
				if (true/*info.piclevel == 1*/) {
					html += '<div class="maketop_"></div>';
				} else if (info.status == 1) {
					html += '<div class="makecheck_"></div>';
				} else if (info.status == 0) {
					html += '<div class="makebanned_"></div>';
				} else if (info.status == 3) {
					html += '<div class="maketimeout_"></div>';
				} else {
					html += '';
				}
				;
				html += '<div class="actions"><div class="left">';
				html += '<a href="javascript:" coaid='+info.id+' class="sc">';
				if (info.hasCollected == 1) {
					html += '<span class="btn afterclick">已收藏</span></a></div>';
				} else {
					html += '<span class="btn beforeclick">收藏</span></a></div>';
				}
				;
				html += '<div class="right"><a  href="javascript:" aid='+info.id+' class="zan">';
				if (info.zan == 1) {
					html += '<span class="btn afterclick">已赞</span>';
				} else {
					html += '<span class="btn beforeclick">赞</span>';
				}
				;
				html += '</a></div></div>';
				html += '<a href="${base}info/detail/'+info.id+'.html?type=${type}" >';

				if (info.picture == "0") {
					html += '<img class="img" src="/uploads/default.jpeg" ';
					html += 'height="'
							+ Math.round(info.height / info.width * 208)
							+ '">';
				} else {
					var tmp = info.picture.split('/');
					var path = tmp[0];
					var picname = tmp[1];
					if (info.gray == "1") {
						html += '<img class="img imggray" src="/uploads/'
								+ path + '/s_' + picname + '" ';
					} else {
						html += '<img class="img" src="/uploads/'
								+ path + '/s_' + picname + '" ';
					}
					html += 'height="'
							+ Math.round(info.height / info.width * 208)
							+ '">';
				}

				html += '</a><div class="timearea"><div class="toptime"> 时间:'
						+ info.time + '</div><div class="bottomarea">地点:'
						+ info.area + '</div></div></div>';
				html += '<div  class="img_introduce"><h3>';
				html += '<span class="cell_tit">' + info.title + '</span>';
				html += ' </h3>';
				html += '<div class="img_footer">';
				html += '<a href="#"><span class="tx-clip">';
				html += '<img src="/avatar/'+info.user.avatar+' "/></span></a>';
				html += '<div class="cont">';
				if (info.renzheng == '1') {
					html += '<a  href="#">' + info.user.username + '</a>'
				} else {
					html += '<a  href="#">' + info.user.username + '</a>'
				}

				html += '<p >发布于' + info.time + '</p></div></div>';
				html += '</div></li>';
			}
			// Add image HTML to the page.
			$('#lxf-box').append(html);
			// Apply layout.
			applyLayout();
		};

		$(document).ready(function() {
			// Capture scroll event.
			$(document).bind('scroll', onScroll);
			// Load first data from the API.
			loadData();
		});
	</script>
	<script type="text/javascript">
		$('.zan').click(function() {
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

		$('.sc').click(function() {

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

		$('.yizan').click(function() {
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

		$('.yisc').click(function() {
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
	var li=$("li");//这里是区块名称
	var li_W = li[0].offsetWidth+margin;//取区块的实际宽度（包含间距，这里使用源生的offsetWidth函数，不适用jQuery的width()函数是因为它不能取得实际宽度，例如元素内有pandding就不行了）
	function endless(){//定义成函数便于调用
	  var h=[];//记录区块高度的数组
	  var n = document.documentElement.offsetWidth/li_W|0;// 窗口的宽度除以区块宽度就是一行能放几个区块
	  for(var i = 0;i < li.length;i++) {//有多少个li就循环多少次
	    li_H = li[i].offsetHeight;//获取每个li的高度
	    if(i < n) {//n是一行最多的li，所以小于n就是第一行了
	      h[i]=li_H;//把每个li放到数组里面
	      li.eq(i).css("top",0);//第一行的Li的top值为0
	      li.eq(i).css("left",i * li_W);//第i个li的左坐标就是i*li的宽度
	      }else{
	      min_H =Math.min.apply(null,h) ;//取得数组中的最小值，区块中高度值最小的那个
	      minKey = getarraykey(h, min_H);//最小的值对应的指针
	      h[minKey] += li_H+margin ;//加上新高度后更新高度值
	      li.eq(i).css("top",min_H+margin);//先得到高度最小的Li，然后把接下来的li放到它的下面
	      li.eq(i).css("left",minKey * li_W); //第i个li的左坐标就是i*li的宽度
	    }
	    //把区块的序号和它的高度值写入对应的区块H3标题里面
	  }
	}
	/* 使用for in运算返回数组中某一值的对应项数(比如算出最小的高度值是数组里面的第几个) */
	function getarraykey(s, v) {for(k in s) {if(s[k] == v) {return k;}}}
	/*这里一定要用onload，因为图片不加载完就不知道高度值*/
	window.onload = function() {endless();};
	/*浏览器窗口改变时也运行函数*/
	window.onresize = function() {endless();};
	</script>

	<div class="c"></div>

	<script type="text/javascript" src="${base}static/js/md5.js"></script>
	<script type="text/javascript">
		$(function() {
			$('.btn_login').click(function() {
				document.getElementById("Password").value = hex_md5(document
						.getElementById("Password").value);
				document.getElementById("loginform").submit();
			});
		})
	</script>

	<div class="c"></div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
