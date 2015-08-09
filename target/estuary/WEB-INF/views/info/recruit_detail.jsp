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
<!-- 弹出对话框-->
<link rel="stylesheet" type="text/css"
	href="${base}static/js/artDialog/default.css">
<script type="text/javascript"
	src="${base}static/js/artDialog/artDialog.min.js"></script>
<script type="text/javascript"
	src="${base}static/js/artDialog/artDialog.plugins.min.js"></script>
<!-- 这是主体的样式 -->
<link rel="stylesheet" type="text/css" href="${base}static/css/style.css" />
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

    <div class="boxstyle maincontent applyposition">
      <ul class="contitle contitleline">
        <li><h3>【行程有变，时间地点待定】广州广电计量检测股份有限公司2014校园招聘宣讲会</h3></li>
    
      </ul>
            <ul class="contitle contitleline">
        <li style="float:right;">
          <p class="chititle" style="float:right;">发布人：<em><font title="北京市">职业导航协会</font></em>
            &nbsp;&nbsp;&nbsp;发布时间：
            <em>       
                  2013-10-10  23:00:00
                </em> &nbsp;&nbsp;&nbsp;查看数：
            <em>       
                 110
                </em></p>
        </li>
      </ul>
      <div class="c"></div>
      <div class="contxt">

        <p style="font-weight: bold;">职位描述：</p>
        <p>1、负责设计微博产品的原型界面及交互流程；
<br>2、结合用户研究进行原有界面及流程的优化；
<br>3、组织梳理交互设计规范并负责维护和实施；
<br>4、参与微博商业新产品的策划及设计。</p>
        <br>
        <p style="font-weight: bold;">任职要求：</p>
        <p>1、热衷于体验互联网产品，对用户体验有深刻的认识；
<br>2、喜欢主动思考，并对互联网产品有自己的见解；
<br>3、观察力敏锐，有较强的逻辑思维能力，有很强的口头及书面表达能力；
<br>4、良好的审美能力，了解互联网技术实现逻辑；
<br>5、本科以上学历，专业不限；
<br>6、有个人站点者优先考虑。</p>



        <div id="hrs_share">
          <span><a class="moreposition" href="/wt/sina/web/index/CompsinaRecruitSchool">查看更多职位</a></span>
          <!-- Baidu Button BEGIN -->
          <div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare">
            <label>分享到：</label> <a class="bds_tsina" title="分享到新浪微博" href="#"></a> <a class="bds_mail" title="分享到邮件分享" href="#"></a> <a class="bds_linkedin" title="分享到linkedin" href="#"></a> <a class="bds_qzone" title="分享到QQ空间" href="#"></a> <a class="bds_tieba" title="分享到百度贴吧" href="#"></a> <a class="bds_douban" title="分享到豆瓣网" href="#"></a> <span class="bds_more">更多</span>
          </div>
          <script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=6621798" src="http://bdimg.share.baidu.com/${base}static/js/bds_s_v2.js?cdnversion=384113"></script>
          
          <script type="text/javascript">
              document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/${base}static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
          </script>
          <!-- Baidu Button END -->
        </div>
        
          <a id="btnApplyJob" class="btn_applyposition" href="javascript:void(0);"></a>
        
      </div>
    </div>



    <div class="rightbox rightposition">
      <span class="rightitle"><h3>热门信息</h3></span>
      <ul class="rightqa">
        
          <li><a class="ellipsis" href="/wt/sina/web/templet1000/index/corpwebPosition1000sina!getOnePosition?postIdEnc=9DB7C00258ECB9E8079AB693104863C8&amp;brandCode=1&amp;recruitType=1&amp;lanType=1&amp;urlCorpEdition=null&amp;operational=0AF3ABBC51D677F69F55B61527BDE06C617EAF25CAA683E58F199D3F19D0E6818C33B432D6B9C8625AD4A90B846D19E757D0FF1BF56571742F50B46C2598C26176B0D9C1E2ADC6FB10C8778CED3C8D97405CCBB2A451E1D5F4E85A60494BB41DBDA759C49B241EC680ECBE66C1D8D1FCD1151EA8224FA14940E420CC198D71A0225BF0BC1F0D23C4AAC6F0D4A111B4FFA4A5BBFED0756F0BD07B7EFBD93B8003CBE3A4223DD605972783565F36BE25CC035083D108FA32E90194937C735ABDCF" title="交互设计师_微博事业部">交互设计师_微博事业部</a></li>
        
          <li><a class="ellipsis" href="/wt/sina/web/templet1000/index/corpwebPosition1000sina!getOnePosition?postIdEnc=77DB9F52D395D855D81D0CD878BAC807&amp;brandCode=1&amp;recruitType=1&amp;lanType=1&amp;urlCorpEdition=null&amp;operational=0AF3ABBC51D677F69F55B61527BDE06C617EAF25CAA683E58F199D3F19D0E6818C33B432D6B9C8625AD4A90B846D19E757D0FF1BF56571742F50B46C2598C26176B0D9C1E2ADC6FB10C8778CED3C8D97E87ABA17788ADB29C3B0D83DFB0953F73D8248A89CF46B8CECEE1BBFA40A4823D1151EA8224FA14940E420CC198D71A0225BF0BC1F0D23C4AAC6F0D4A111B4FFA4A5BBFED0756F0BD07B7EFBD93B8003CBE3A4223DD605972783565F36BE25CC035083D108FA32E90194937C735ABDCF" title="娱乐视频编辑-频道运营">娱乐视频编辑-频道运营</a></li>
        
          <li><a class="ellipsis" href="/wt/sina/web/templet1000/index/corpwebPosition1000sina!getOnePosition?postIdEnc=CA11E4AB4D09966B5A573C7754F66C1A&amp;brandCode=1&amp;recruitType=1&amp;lanType=1&amp;urlCorpEdition=null&amp;operational=0AF3ABBC51D677F69F55B61527BDE06C617EAF25CAA683E58F199D3F19D0E6818C33B432D6B9C8625AD4A90B846D19E757D0FF1BF56571742F50B46C2598C26176B0D9C1E2ADC6FB10C8778CED3C8D9718073402D0E86E6EBB262425C5967941171D1FC8EE71786B6824A7401F1F78E6D1151EA8224FA14940E420CC198D71A0225BF0BC1F0D23C4AAC6F0D4A111B4FFA4A5BBFED0756F0BD07B7EFBD93B8003CBE3A4223DD605972783565F36BE25CC035083D108FA32E90194937C735ABDCF" title="PHP开发工程师_微博事业部">PHP开发工程师_微博事业部</a></li>
        
          <li><a class="ellipsis" href="/wt/sina/web/templet1000/index/corpwebPosition1000sina!getOnePosition?postIdEnc=4D95F999816D2EE172EEA80B78D4F01A&amp;brandCode=1&amp;recruitType=1&amp;lanType=1&amp;urlCorpEdition=null&amp;operational=0AF3ABBC51D677F69F55B61527BDE06C617EAF25CAA683E58F199D3F19D0E6818C33B432D6B9C8625AD4A90B846D19E757D0FF1BF56571742F50B46C2598C26176B0D9C1E2ADC6FB10C8778CED3C8D97325297A9CC53A92F91CBB79120DC272C9D892972D75DAF5E59175EB83E31F3CED1151EA8224FA14940E420CC198D71A0225BF0BC1F0D23C4AAC6F0D4A111B4FFA4A5BBFED0756F0BD07B7EFBD93B8003CBE3A4223DD605972783565F36BE25CC035083D108FA32E90194937C735ABDCF" title="微博产品运营专员">微博产品运营专员</a></li>
        
          <li><a class="ellipsis" href="/wt/sina/web/templet1000/index/corpwebPosition1000sina!getOnePosition?postIdEnc=42D642C450E2C8C172EEA80B78D4F01A&amp;brandCode=1&amp;recruitType=1&amp;lanType=1&amp;urlCorpEdition=null&amp;operational=0AF3ABBC51D677F69F55B61527BDE06C617EAF25CAA683E58F199D3F19D0E6818C33B432D6B9C8625AD4A90B846D19E757D0FF1BF56571742F50B46C2598C26176B0D9C1E2ADC6FB10C8778CED3C8D97A527662CCFBAF8B666FFFD1AC9D97BB69D892972D75DAF5E59175EB83E31F3CED1151EA8224FA14940E420CC198D71A0225BF0BC1F0D23C4AAC6F0D4A111B4FFA4A5BBFED0756F0BD07B7EFBD93B8003CBE3A4223DD605972783565F36BE25CC035083D108FA32E90194937C735ABDCF" title="前端开发工程师-网络应用开发部">前端开发工程师-网络应用开发部</a></li>
        
          <li><a class="ellipsis" href="/wt/sina/web/templet1000/index/corpwebPosition1000sina!getOnePosition?postIdEnc=20C26E55C7511373CD9200FA4D0CA727&amp;brandCode=1&amp;recruitType=1&amp;lanType=1&amp;urlCorpEdition=null&amp;operational=0AF3ABBC51D677F69F55B61527BDE06C617EAF25CAA683E58F199D3F19D0E6818C33B432D6B9C8625AD4A90B846D19E757D0FF1BF56571742F50B46C2598C26176B0D9C1E2ADC6FB10C8778CED3C8D976DBDF2F273F3719FC09DF6637B104C39604E9681C084FFFCF488D95DA79BF78FD1151EA8224FA14940E420CC198D71A0225BF0BC1F0D23C4AAC6F0D4A111B4FFA4A5BBFED0756F0BD07B7EFBD93B8003CBE3A4223DD605972783565F36BE25CC035083D108FA32E90194937C735ABDCF" title="PHP开发工程师-网络应用开发...">PHP开发工程师-网络应用开发...</a></li>
        
          <li><a class="ellipsis" href="/wt/sina/web/templet1000/index/corpwebPosition1000sina!getOnePosition?postIdEnc=505E374BFBE80427CD9200FA4D0CA727&amp;brandCode=1&amp;recruitType=1&amp;lanType=1&amp;urlCorpEdition=null&amp;operational=0AF3ABBC51D677F69F55B61527BDE06C617EAF25CAA683E58F199D3F19D0E6818C33B432D6B9C8625AD4A90B846D19E757D0FF1BF56571742F50B46C2598C26176B0D9C1E2ADC6FB10C8778CED3C8D97883661E9A2F37C424D538B6C385BC15C604E9681C084FFFCF488D95DA79BF78FD1151EA8224FA14940E420CC198D71A0225BF0BC1F0D23C4AAC6F0D4A111B4FFA4A5BBFED0756F0BD07B7EFBD93B8003CBE3A4223DD605972783565F36BE25CC035083D108FA32E90194937C735ABDCF" title="薪酬福利专员_人力资源部">薪酬福利专员_人力资源部</a></li>
        
          <li><a class="ellipsis" href="/wt/sina/web/templet1000/index/corpwebPosition1000sina!getOnePosition?postIdEnc=F44CBE8BE98AD025CD9200FA4D0CA727&amp;brandCode=1&amp;recruitType=1&amp;lanType=1&amp;urlCorpEdition=null&amp;operational=0AF3ABBC51D677F69F55B61527BDE06C617EAF25CAA683E58F199D3F19D0E6818C33B432D6B9C8625AD4A90B846D19E757D0FF1BF56571742F50B46C2598C26176B0D9C1E2ADC6FB10C8778CED3C8D97C0C9AF5C31A518F5BF9AC15A8353A597604E9681C084FFFCF488D95DA79BF78FD1151EA8224FA14940E420CC198D71A0225BF0BC1F0D23C4AAC6F0D4A111B4FFA4A5BBFED0756F0BD07B7EFBD93B8003CBE3A4223DD605972783565F36BE25CC035083D108FA32E90194937C735ABDCF" title="系统研发工程师（通讯产品）_微...">系统研发工程师（通讯产品）_微...</a></li>
        
      </ul>
    </div>
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
