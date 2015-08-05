<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;UTF-8"%>
<jsp:include page="../common/base.jsp"></jsp:include>
<html>
<head>
<title>北洋信息墙-${info.title}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${base}static/css/base.css" />
<script type="text/javascript" src="${base}static/js/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="${base}static/css/img.css" />
<script type="text/javascript" src="${base}static/js/jquery.slides.min.js"></script>
<link rel="stylesheet" type="text/css" href="${base}static/css/interface.css" />
<script type="text/javascript" src="${base}static/js/interface.js"></script>
<!-- 弹出对话框-->
<link rel="stylesheet" type="text/css" href="${base}static/js/artDialog/default.css">
<script type="text/javascript" src="${base}static/js/artDialog/artDialog.min.js"></script>
<script type="text/javascript" src="${base}static/js/artDialog/artDialog.plugins.min.js"></script>
<!-- 这是主体的样式 -->
<link rel="stylesheet" type="text/css" href="${base}static/css/style.css"/>
</head>

<body>
	<jsp:include page="../common/header.jsp"></jsp:include>

	<script src="${base}static/js/jquery.wookmark.js"></script>
	<!-- <script src="${base}static/js/jquery.imagesloaded.js"></script> -->
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
	<link rel="stylesheet" type="text/css"
		href="${base}static/css/active-detail.css" />
	<script type="text/javascript">
// JavaScript Document
/**
 * args是一个字典型参数，其中必须包括targetId和resourceId这两个属性，来添加收藏的点击事件
 * @param args
 * @returns
 */
function checkCollected(args) {
	$.ajax( {
		cache : false,
		url : '${base}static/Api/checkcol',
		type : 'post',
		dataType : 'json',
		data : args,
		beforeSend : function() {
			$(".self_collect").html("");
		},
		success : function(data) {
			if (data) {
				$(".self_collect").attr("onclick","").html("&nbsp;&nbsp;&nbsp;&nbsp;已收藏");
			} else {
				var func = "addCollection({aid:" + args.aid + "})";
				$(".self_collect").html("<a href='javascript:void(0);' onclick='" + func 
						+ "'>&nbsp;收藏</a>");
			}
		},
		error : function() {
			$(".self_collect").attr("onclick","").html("&nbsp;&nbsp;&nbsp;&nbsp;收藏暂不可用");
		}
	});
}

function addCollection(args) {
	$.ajax( {
		cache : false,
		url : '${base}static/Api/addcol',
		type : 'post',
		dataType : 'json',
		data : args,
		beforeSend : function() {
			$(".self_collect").html("&nbsp;&nbsp;&nbsp;&nbsp;稍候...");
		},
		success : function(data) {
			$(".self_collect").html("&nbsp;&nbsp;&nbsp;&nbsp;已收藏").attr("onclick","");
			alertDiv("收藏成功!");
		},
		error : function() {
			alertDiv("收藏失败!");
		}
	});
}

$(document).ready(function(){

	var aid='{$aid}';
	$(function(){
		<present name="Think.session.user">
			checkCollected({aid:aid});
		</present>
	});
})

</script>
	<div class="mainwidth">
		<!--     左侧信息栏开始 -->
		<div class="view_left">
			<div class="user_icon">
				<div class="user_icon_url">
					<img src="${base}static/images/icons/user_icon.png" width="30px"
						height="30px">
				</div>
				<div class="user_icon_title">发布者信息</div>
			</div>
			<div class="user_detail">
				<div class="detail-title">
					<div class="user_img">
						<a href="#"><img src="/avatar/${info.user.avatar}"
							width="40" alt="" /></a>
					</div>

					<div class="user-info">
						<div class="user-name">
							<a href="#">${info.user.username}</a>
						</div>
						<div class="bm-from">
							<span>发布时间：${info.time}</span>
						</div>
					</div>
					<!--收藏已经可以用了，不过我不会美工……-->
					<div class="self_collect" sytle="display:none"></div>
					<!--     这是收藏关注<div  class="user-operate">
                                <a  href="#">收藏</a>
                                <a  href="#">赞</a>
                                <a  href="#">评论</a>
                    </div> -->
				</div>
			</div>

			<!--    活动详细信息开始   -->
			<div class="user_icon">
				<div class="user_icon_url">
					<img src="${base}static/images/icons/intro_icon.png" width="30px"
						height="30px">
				</div>
				<div class="user_icon_title">活动详细信息</div>
			</div>
			<!-- 图标导航结束 -->
			<div class="activities_detail">
				<div class="active-introduce">
					<p>
						<span class="intro_icons">活动时间:</span><span class="intro_main"><span
							style="padding: 5px; float: left;"><img
								src="${base}static/images/icons/intro_time.png" width="20px"
								height="20px"></span>${info.time}</span>
					</p>
					<p>
						<span class="intro_icons">活动地点:</span><span class="intro_main"><span
							style="padding: 5px; float: left;"><img
								src="${base}static/images/icons/intro_area.png" width="20px"
								height="20px"></span>${info.area}</span>
					</p>
				</div>
			</div>

			<!--  点赞狂魔爱好者 -->
			<div class="user_icon">
				<div class="user_icon_url">
					<img src="${base}static/images/icons/like_icon.png" width="30px"
						height="30px">
				</div>
				<div class="user_icon_title">点赞狂魔</div>
				<span class="zannum">已有 ${fn:length(info.thumbupUsers)}人点赞</span>
			</div>
			<!-- 图标导航结束 -->
			<div class="view_like">


				<ul>
					<!--  循环输出点赞狂魔前20个 -->
					<c:forEach items="${info.thumbupUsers }" var="thUser">
					<li><a href="#"><img src="/avatar/${thUser.avatar}"
							width="55px" height="55px" title="${thUser.username}" /></a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<!-- 左侧信息栏结束 -->
		<!--         右侧信息栏开始 -->

		<div class="view_right">
			<!-- 管理面板开始 -->
			<c:if test="${user.rank } >= 13">
			<div class="dock" id="dock2">
				<div class="adm">操控面板</div>
				<div class="dock-container2">
					<a class="dock-item2" operate="1" href="javascript:void('0');"><span>允许</span><img
						src="${base}static/images/admpannel/allow.png" alt="允许" /></a> <a
						class="dock-item2" operate="2" href="javascript:void('0');"><span>编辑</span><img
						src="${base}static/images/admpannel/edit.png" alt="编辑" /></a> <a
						class="dock-item2" operate="3" href="javascript:void('0');"><span>禁止</span><img
						src="${base}static/images/admpannel/banned.png" alt="禁止" /></a> <a
						class="dock-item2" operate="4" href="javascript:void('0');"><span>置顶</span><img
						src="${base}static/images/admpannel/go_top.png" alt="置顶" /></a> <a
						class="dock-item2" operate="5" href="javascript:void('0');"><span>取消置顶</span><img
						src="${base}static/images/admpannel/go_bottom.png" alt="取消置顶" /></a>
					<egt name="Think.session.user.rank" value="14"> <a
						class="dock-item2" operate="6" href="javascript:void('0');"><span>删除</span><img
						src="${base}static/images/admpannel/delete.png" alt="删除" /></a> </egt>
				</div>
			</div>
			</c:if>
			<!--   管理面板结束 -->
			<div class="icon_title">
				<div class="title_icon_url">
					<img src="${base}static/images/icons/title_icon.png" width="40px"
						height="40px">
				</div>
				<div class="title_icon_title">${info.title}</div>

				<span class="title_icon_count"
					style="display: block; float: right; margin-right: 30px; line-height: 40px;">浏览数：${info.viewCount}</span>
			</div>
			<!--         标题图标结束 -->
			<div class="layout_main">
				<div class="inner-pic  inner">
					<eq name="data.apicture" value="0"> <else />
					<div class="content">
						<img src="/uploads/${info.picture}" alt="" />
					</div>
					</eq>
					<div class="icon_title" style="border: none;">
						<div class="title_icon_url"></div>
						<div class="title_icon_title">活动特别说明</div>
					</div>
					<div class="view_intro" style="padding: 20px;">${info.intro}</div>
				</div>
			</div>
		</div>
		<!-- 右侧信息栏结束 -->

		<div class="clear"></div>
	</div>

	<egt name="Think.session.user.rank" value="13"> <!--dock menu JS options -->
	<script type="text/javascript">
  
  $(document).ready(
    function()
    {
      $('#dock2').Fisheye(
        {
          maxWidth: 60,
          items: 'a',
          itemsText: 'span',
          container: '.dock-container2',
          itemWidth: 40,
          proximity: 80,
          alignment : 'left',
          valign: 'bottom',
          halign : 'center'
        }
      )
    }
  );
</script> <!-- dock menu js  END --> </egt>

	<!-- 评论页内容 -->
	<!-- <div  class="inner-album  inner">
                        <div  class="comment-total"><b>2324235</b>条评论</div>
                        <div  class="coment">                           
                            <ul  class="comment-list">
                                <li>                                    
                                    <a  href=""><img src="../img/用户头像2.png" alt=""/></a>
                                    <div  class="user-info">
                                         <a  href="#">信息与网络协会</a><span class="coment-time">2013.12.08&nbsp;20:36&nbsp;说</span>
                                    </div>                                       
                                    <p  class="coment-info">评论</p>
                                    <div class="clear"></div>
                                    
                                </li>
                                
                                <li>                                    
                                    <a  href=""><img src="../img/用户头像2.png" alt=""/></a>
                                    <div  class="user-info">
                                         <a  href="#">信息与网络协会</a><span class="coment-time">2013.12.08&nbsp;20:36&nbsp;说</span>
                                    </div>                                       
                                    <p  class="coment-info">评论</p>
                                    <div class="clear"></div>
                                    
                                </li>
                            </ul>
                            <div  class="coment-area">
                                <a  href="#"><img src="../img/用户头像2.png" width="36"   alt=""/></a>
                                <div>
                                    <textarea>我也说一句</textarea>
                                </div>
                                <a  style="float:right;margin:20px 15px auto auto;border-radius:2px;color:#000;padding:2px 5px;width:40px;height:18px;line-height:18px;font-size:14px;text-align:center;background:#F00;" href="#">评论</a>
                                <div class="clear"></div>
                            </div>
                        </div>
                        
                    </div>   -->

	<!-- 评论结束 -->



	<!-- 操作BEGIN -->
	<egt name="Think.session.user.rank" value="13"> <script
		type="text/javascript">
$('.dock-item2').live('click',function(){
  var aid=${data.id};
  var oid =$(this).attr('operate');
    $.ajax({
      url:'__APP__/Sthd/operate',
      type:'post',
      data:{aid:aid,oid:oid},
      datatype:'json',
      async: false,
      success:function(json){    
       var d2 = art.dialog({
        title:"操作结果",
        width:300,
        height:50,
        content:json['info'],
        lock:true,
        beforeunload:function(){
          // window.location.href="__APP__/Index/login";
        }
    });
    d2.time(1500);
        }
      });
 });
</script> </egt>




	<!-- 操作END -->

	<!-- Baidu Button BEGIN -->
	<script type="text/javascript" id="bdshare_js"
		data="type=slide&amp;img=6&amp;pos=left&amp;uid=2448334"></script>
	<script type="text/javascript" id="bdshell_js"></script>
	<script charset="utf-8" src="${base}static/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="${base}static/kindeditor/lang/zh_CN.js"></script>
	<script type="text/javascript">
var mm=${rel};
var title=mm.atitle;
var info=mm.aintro;

function cutstr(str,len)  
{  
   var str_length = 0;  
   var str_len = 0;  
      str_cut = new String();  
      str_len = str.length;  
      for(var i = 0;i<str_len;i++)  
     {  
        a = str.charAt(i);  
        str_length++;  
        if(escape(a).length > 4)  
        {  
         //中文字符的长度经编码之后大于4  
         str_length++;  
         }  
         str_cut = str_cut.concat(a);  
         if(str_length>=len)  
         {  
         str_cut = str_cut.concat("...");  
         return str_cut;  
         }  
    }  
    //如果给定字符串小于指定长度，则返回源字符串；  
    if(str_length<len){  
     return  str;  
    }  
}  
info=cutstr(info,150);
var picurl="http://info.app.tju.edu.cn/uploads/"+mm.apicture;
var bds_config={'bdTop':90,'bdText':title,'bdDesc':info,'bdPic':picurl}
document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000);
</script>

	<!-- Baidu Button END -->

	<div class="c"></div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
