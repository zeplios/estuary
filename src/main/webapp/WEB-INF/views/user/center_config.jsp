<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;UTF-8"%>
<link rel="stylesheet" type="text/css" href="${base}static/css/jquery.Jcrop.min.css" media="all">
<link rel="stylesheet" type="text/css" href="${base}static/js/uploadify-v3.1/uploadify.css" media="all">

<script type="text/javascript" src="${base}static/js/uploadify-v3.1/jquery.uploadify-3.1.min.js"></script>
<script type="text/javascript" src="${base}static/js/jquery.Jcrop.min.js"></script>
<script type="text/javascript" src="${base}static/js/ThinkBox/jquery.ThinkBox.js"></script>
<link rel="stylesheet" type="text/css" href="${base}static/js/ThinkBox/css/ThinkBox.css" media="all">
<script type="text/javascript" src="${base}static/js/jquery.form.js" ></script>
<script type="text/javascript" src="${base}static/js/md5.js" ></script>
<link rel="stylesheet" type="text/css" href="${base}static/css/home_set.css"/>
<style type="text/css">
.config{
	margin: 0 auto;
	padding: 15px;
	width: 750px;
	font-family: "microsoft yahei";
	background-color: #F5F5F5;
}
.cf:before,.cf:after {
	display: table;
	content: "";
	line-height: 0;
}
.cf:after {
	clear: both;
}
.cf {
	*zoom: 1;
}
.upload-area {
	position: relative;
	float: left;
	margin-right: 30px;
	width: 200px;
	height: 200px;
	background-color: #F5F5F5;
    border: 2px solid #E1E1E1;
}
.upload-area .file-tips {
	position: absolute;
	top: 90px;
	left: 0;
    padding: 0 15px;
    width: 170px;
    line-height: 1.4;
    font-size: 12px;
	color: #A8A8A3;
    text-align: center;
}
.userup-icon {
    display: inline-block;
    margin-right: 3px;
    width: 16px;
    height: 16px;
    vertical-align: -2px;
	background: url("${base}static/images/userup_icon.png") no-repeat;
}
.uploadify-button {
	line-height: 120px!important;
	text-align: center;
}
.preview-area {
	float: left;
}
.tcrop {
    clear: right;
    font-size: 14px;
    font-weight: bold;
}
.update-pic .crop {
    background: url("${base}static/images/mystery.png") no-repeat scroll center center #EEEEEE;
    float: left;
    margin-bottom: 20px;
    margin-top: 10px;
    overflow: hidden;
}
.crop100 {
    height: 100px;
    width: 100px;
}
.crop60 {
    height: 60px;
    margin-left: 20px;
    width: 60px;
}
.update-pic .save-pic {
    clear: left;
    margin-right: 20px;
}
.update-pic .uppic-btn {
    background-color: #348FD4;
    color: #FFFFFF;
    display: block;
    float: left;
    font-size: 16px;
    height: 30px;
    line-height: 30px;
    text-align: center;
    vertical-align: middle;
    width: 89px;
}
.preview {
	position: absolute;
	top: 0;
	left: 0;
	z-index: 11;
	width: 200px;
	height: 200px;
	overflow: hidden;
	background:#fff;
	display: none;
}
</style>

<script type="text/javascript">
	/*修改个人信息部分*/
	function modifyInfo() {

		$("#personal_info_form").ajaxSubmit({
			beforeSubmit : function() {
			},
			dataType : 'json',
			complete : function() {
			},
			success : function(json) {
				
				if (json.error=='0'){
					alert("修改成功！");
				} else {
					alert("Unknow Error");
				}
				
				//location.reload();
			}
		});
	}
</script>

 <section class="radiotabs">
    <input type="radio" name="tab" id="tab1" class="tabs" checked="checked">
        <label for="tab1">
            基本设置
        </label>
    <input type="radio" name="tab" id="tab2" class="tabs">
        <label for="tab2">
            修改密码
		</label>                      
    <section id="view1" class="tabcontent">
        <div  class="form_box">
        	<div  class="title-div">
            	<strong>头像设置</strong>
            </div>
         <!-- 修改头像 -->
	<table>
		<tbody>
	    	<tr>                                        
	          <td  style="margin-left:40px;">
				<form action="${base}user/avatar" method="post" id="pic" class="update-pic cf">
					<div class="upload-area">
						<input type="file" id="user-pic" name="file"/>
						<div class="file-tips">支持JPG,PNG,GIF，图片小于1MB，尺寸不小于100*100,真实高清头像更受欢迎！</div>
						<div class="preview hidden" id="preview-hidden"></div>
					</div>
					<div class="preview-area">
						<input type="hidden" id="x" name="x" />
						<input type="hidden" id="y" name="y" />
						<input type="hidden" id="w" name="w" />
						<input type="hidden" id="h" name="h" />
						<input type="hidden" id='img_src' name='src'/>
						<div class="tcrop">头像预览</div>
						<div class="crop crop100"><img id="crop-preview-100" src="" alt=""></div>
						<div class="crop crop60"><img id="crop-preview-60" src="" alt=""></div>
						<a class="uppic-btn save-pic" href="javascript:;">保存</a>
						<a class="uppic-btn reupload-img" href="javascript:$('#user-pic').uploadify('cancel','*');">重新上传</a>
					</div>
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<div  class="title-div">
					<strong>个人资料</strong>
				</div>
				<table  border="0" style="margin-left:120px;">
				<tbody>
				<form:form commandName="user" id="personal_info_form" action="${base}user/asyn/update">
					<form:hidden path="id" />
					<tr>
						<td class="c-td">用户名：</td>
						<td class="text" >${user.username}</td>
					</tr>
					<tr>
						<td class="c-td'">电话：</td>
						<td class="text"><form:input path="phone" /></td>
					</tr>
					<tr>
						<td class="c-td'">邮箱：</td>
						<td class="text">${user.email}</td>
					</tr>
					<tr>
				      <td><a href="javascript:modifyInfo()">修改</a></td>
					</tr>
				</form:form>
				</tbody>
				</table>
			</td>
		</tr>
	</tbody>
	</table>
</div>
<script type="text/javascript">
    function _doform(){
        if(document.getElementById("oldpass").value==""){
            document.getElementById("notify").innerHTML="原密码不能为空";
        }else if(document.getElementById("newpass").value==""){
            document.getElementById("notify").innerHTML="新密码不能为空";
        }else if(document.getElementById("newpass").value==document.getElementById("oldpass").value){
            document.getElementById("notify").innerHTML="新密码不能与原密码相同";
        }else if(document.getElementById("newpass").value==document.getElementById("renewpass").value){
            document.getElementById("notify").innerHTML="";
            document.getElementById("oldpass").value=hex_md5(document.getElementById("oldpass").value);
            document.getElementById("newpass").value=hex_md5(document.getElementById("newpass").value);
            document.getElementById("renewpass").value="";
            document.getElementById("changepass").submit();
        }else{
            document.getElementById("notify").innerHTML="两次输入的新密码不一致，请仔细检查。";
        }
    }
</script>
</section>

	<section id="view2" class="tabcontent">
	<div  style="padding:30px 60px;">
	<form id="changepass" method="post" action="${base }user/changepw">
	    <table>
	        <tr>
	            <td></td>
	            <td class="c-td"><span id="notify"></span></td>
	        </tr>
	    	<tr>
	        	<td class="c-td">原始密码:</td>
	            <td>
	            	<input id="oldpass" class="text" type="password"  name="oldpass"  />
	            </td>
	        </tr>
	        <tr>
	        	<td class="c-td">新密码:</td>
	            <td>
	            	<input id="newpass" class="text" type="password"  name="newpass"  />
	            </td>
	        </tr>
	        <tr>
	        	<td class="c-td">重复新密码:</td>
	            <td>
	            	<input id="renewpass" class="text" type="password"  name="renewpass"  />
	            </td>
	        </tr>
	        <tr>
	        	<td><a href="javascript:void(0)" onclick="_doform()">提交</td>
	        </tr>
	    </table>
		
	</form>
	</div>
	</section>

</section>


<script type="text/javascript">
	$(function(){
		//上传头像(uploadify插件)
		$("#user-pic").uploadify({
			'queueSizeLimit' : 1,
			'removeTimeout' : 0.5,
			'preventCaching' : true,
			'multi'    : false,
			'swf' 			: '${base}static/js/uploadify-v3.1/uploadify.swf',
			'uploader' 		: '${base}user/asyn/avatar',
			'buttonText' 	: '<i class="userup-icon"></i>上传头像',
			'width' 		: '200',
			'height' 		: '200',
			'fileTypeExts'	: '*.jpg; *.png; *.gif;',
			'onUploadSuccess' : function(file, data, response){
				var data = $.parseJSON(data);
				if(data['errcode'] == 101){
					$.ThinkBox.error(data['errmsg'],{'delayClose':1000});
					return;
				}
				var preview = $('.upload-area').children('#preview-hidden');
				preview.show().removeClass('hidden');
				//两个预览窗口赋值
				$('.crop').children('img').attr('src','/avatar/' + data['filename']+'?random='+Math.random());
				//隐藏表单赋值
				$('#img_src').val('/avatar/' + data['filename']);
				//绑定需要裁剪的图片
				var img = $('<img />');
				preview.append(img);
				preview.children('img').attr('src','/avatar/' + data['filename']+'?random='+Math.random());
				var crop_img = preview.children('img');
				crop_img.attr('id',"cropbox").show();
				var img = new Image();
				img.src = '/avatar/' + data['filename']+'?random='+Math.random();
				//根据图片大小在画布里居中
				img.onload = function(){
					var img_height = 0;
					var img_width = 0;
					var real_height = img.height;
					var real_width = img.width;
					if(real_height > real_width && real_height > 200){
						var persent = real_height / 200;
						real_height = 200;
						real_width = real_width / persent;
					}else if(real_width > real_height && real_width > 200){
						var persent = real_width / 200;
						real_width = 200;
						real_height = real_height / persent;
					}
					if(real_height < 200){
						img_height = (200 - real_height)/2;	
					}
					if(real_width < 200){
						img_width = (200 - real_width)/2;
					}
					preview.css({width:(200-img_width)+'px',height:(200-img_height)+'px'});
					preview.css({paddingTop:img_height+'px',paddingLeft:img_width+'px'});			
				}
				//裁剪插件
				$('#cropbox').Jcrop({
		            bgColor:'#333',   //选区背景色
		            bgFade:true,      //选区背景渐显
		            fadeTime:1000,    //背景渐显时间
		            allowSelect:false, //是否可以选区，
		            allowResize:true, //是否可以调整选区大小
		            aspectRatio: 1,     //约束比例
		            minSize : [100,100],//可选最小大小
		            boxWidth : 200,		//画布宽度
		            boxHeight : 200,	//画布高度
		            onChange: showPreview,//改变时重置预览图
		            onSelect: showPreview,//选择时重置预览图
		            setSelect:[ 0, 0, 100, 100],//初始化时位置
		            onSelect: function (c){	//选择时动态赋值，该值是最终传给程序的参数！
			            $('#x').val(c.x);//需裁剪的左上角X轴坐标
			            $('#y').val(c.y);//需裁剪的左上角Y轴坐标
			            $('#w').val(c.w);//需裁剪的宽度
			            $('#h').val(c.h);//需裁剪的高度
		          }
		        });
				//提交裁剪好的图片
				$('.save-pic').click(function(){
					if($('#preview-hidden').html() == ''){
						$.ThinkBox.error('请先上传图片！');
					}else{
						//由于GD库裁剪gif图片很慢，所以长时间显示弹出框
						$.ThinkBox.success('图片处理中，请稍候……',{'delayClose':30000});
						$('#pic').submit();
					}
				});
				//重新上传,清空裁剪参数
				var i = 0;
				$('.reupload-img').click(function(){
					$('#preview-hidden').find('*').remove();
					$('#preview-hidden').hide().addClass('hidden').css({'padding-top':0,'padding-left':0});
				});
		     }
		});
		//预览图
		function showPreview(coords){
			var img_width = $('#cropbox').width();
			var img_height = $('#cropbox').height();
			  //根据包裹的容器宽高,设置被除数
			  var rx = 100 / coords.w;
			  var ry = 100 / coords.h; 
			  $('#crop-preview-100').css({
			    width: Math.round(rx * img_width) + 'px',
			    height: Math.round(ry * img_height) + 'px',
			    marginLeft: '-' + Math.round(rx * coords.x) + 'px',
			    marginTop: '-' + Math.round(ry * coords.y) + 'px'
			  });
			  rx = 60 / coords.w;
			  ry = 60 / coords.h;
			  $('#crop-preview-60').css({
			    width: Math.round(rx * img_width) + 'px',
			    height: Math.round(ry * img_height) + 'px',
			    marginLeft: '-' + Math.round(rx * coords.x) + 'px',
			    marginTop: '-' + Math.round(ry * coords.y) + 'px'
			  });
		}
	})
	
</script>