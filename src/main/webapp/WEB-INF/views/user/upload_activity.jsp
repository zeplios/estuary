<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" pageEncoding="UTF-8"%><%@ page
	contentType="text/html;UTF-8"%>
<style type="text/css">
.ui-timepicker-div .ui-widget-header {
	margin-bottom: 8px;
}

.ui-timepicker-div dl {
	text-align: left;
}

.ui-timepicker-div dl dt {
	float: left;
	clear: left;
	padding: 0 0 0 5px;
}

.ui-timepicker-div dl dd {
	margin: 0 10px 10px 45%;
}

.ui-timepicker-div td {
	font-size: 90%;
}

.ui-tpicker-grid-label {
	background: none;
	border: none;
	margin: 0;
	padding: 0;
}

.ui-timepicker-rtl {
	direction: rtl;
}

.ui-timepicker-rtl dl {
	text-align: right;
	padding: 0 5px 0 0;
}

.ui-timepicker-rtl dl dt {
	float: right;
	clear: right;
}

.ui-timepicker-rtl dl dd {
	margin: 0 45% 10px 10px;
}
</style>
<script type="text/javascript">
	$(function() {
		var pickerOpts = {
			changeMonth : true,
			changeYear : true,
			dateFormat : "yy-mm-dd"
		};
		$("#atime").datetimepicker(pickerOpts);
	});

	KindEditor.ready(function(K) {
		window.editor = K.create('#aintro', {
			items : [ 'undo', 'redo', '|', 'preview', 'print', 'cut', 'copy',
					'paste', 'plainpaste', 'wordpaste', '|', 'justifyleft',
					'justifycenter', 'justifyright', 'justifyfull',
					'insertorderedlist', 'insertunorderedlist', 'indent',
					'outdent', 'subscript', 'superscript', 'clearhtml',
					'quickformat', 'selectall', '|', 'fullscreen', '/',
					'formatblock', 'fontname', 'fontsize', '|', 'forecolor',
					'hilitecolor', 'bold', 'italic', 'underline',
					'strikethrough', 'lineheight', 'removeformat', '|',
					'insertfile', 'table', 'hr', 'emoticons', 'baidumap',
					'pagebreak', 'anchor', 'link', 'unlink' ]

		});
	});
</script>

<link rel="stylesheet" type="text/css"
	href="${base}static/css/home_active_add.css" />
<!-- <notempty name="data"><img src="__ROOT__/uploads/m_{$data.apicture}" /> <img src="__ROOT__/uploads/s_{$data.apicture}" /></notempty> -->

<div class="main_title">
	<span><img src="${base}static/img/home/my_att.png"
		style="width: 30px; height: 30px;" /></span> <span class="main_actitle">发布活动</span>
</div>
<div class="main-content">
	<!--图片和海报介绍-->
	<div class="about-active">
		<!--海报介绍-->
		<h4>活动内容编辑</h4>
		<div
			style="border-bottom: 1px solid #ccc; margin: -8px auto 25px 100px;"></div>
 		<form action="${base }admin/info/add" method="post" enctype="multipart/form-data">
			<table border="0">
				<tr>
					<td class="c_td" width="100">活动名称：</td>
					<td class="c_input"><input type="text" name="activity.title" /></td>
				</tr>
				<tr>
					<td class="c_td">活动时间：</td>
					<td class="c_input"><input type="text" name="activity.time" id="atime"
						readonly="true" /></td>
				</tr>
				<tr>
					<td class="c_td">活动地点：</td>
					<td class="c_input"><input type="text" name="activity.area" /></td>
				</tr>
				<tr>
					<td class="c_td">活动类别：</td>
					<td class="c_input"><select name="activity.type">
							<option value="1">校内活动</option>
							<option value="2">招聘信息</option>
					</select></td>
				</tr>
				<tr>
					<td class="c_td">活动海报：</td>
					<td class="c_input"><input type="file" name="bigPoster"
						id="bigPoster"></td>
				</tr>
				<tr>
					<td class="c_td">详细介绍</td>
				</tr>
				<tr>
					<td colspan="2" style="width: 643px;"><textarea name="activity.intro"
							id="aintro" style="width: 643px; height: 300px;"></textarea></td>
				</tr>
			</table>
			<input class="submitbutton" type="submit" value="确认提交" />
		</form>
	</div>
	<!--海报介绍-->
</div>
<!--图片和海报介绍-->
