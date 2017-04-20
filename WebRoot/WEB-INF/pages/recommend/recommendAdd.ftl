<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<#assign rootPath=request.scheme+"://"+request.serverName+":"+request.serverPort+request.contextPath />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>首页推荐位(通版)信息添加</title>
<link href="${request.contextPath}/css/global.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/javascript/ui/css/ui.core.css"/>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/javascript/ui/css/ui.theme.css"/>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/javascript/ui/css/ui.dialog.css"/>
<!-- import jquery -->
<script type="text/javascript" src="${request.contextPath}/javascript/jquery-core/jquery-1.3.js"></script>
<!-- import jquery ui -->
<script type="text/javascript" src="${request.contextPath}/javascript/ui/ui.core.js"></script>
<script type="text/javascript" src="${request.contextPath}/javascript/ui/ui.draggable.js"></script>
<script type="text/javascript" src="${request.contextPath}/javascript/ui/ui.resizable.js"></script>
<script type="text/javascript" src="${request.contextPath}/javascript/ui/ui.dialog.js"></script>
<script type="text/javascript" src="${request.contextPath}/javascript/ui/jquery.bgiframe.js"></script>
<script type="text/javascript" src="${request.contextPath}/javascript/DatePicker/WdatePicker.js"></script>

<script>
	function checkSaveForm(){
		var subjectName = document.getElementById("hrModel.subjectName");
		var subjectId = document.getElementById("hrModel.subjectId");
		if(subjectName.value == "" || subjectName.value == null){
			alert("请填写主题名称!");
			subjectName.focus();
			return;
		}
		if(subjectId.value == "" || subjectId.value == null){
			alert("请填写映射属性!");
			subjectId.focus();
			return;
		}
		var iconUrl = document.getElementById("recommendPicFileUrl");
		if(iconUrl.value == "" || iconUrl.value == null){
			alert("请选择图片!");
			iconUrl.focus();
			return;
		}
		document.forms["editForm"].action = "saveHomeRecommend.action";
		document.forms["editForm"].submit();
	}
	
	/*图标预览*/
	function viewImg(){
		var iconUrl = document.getElementById("recommendPicFileUrl");
		var iconImg = document.getElementById("iconImg");
		var iconImgTr = document.getElementById("iconImgTr");
		if(iconUrl.value != ""){
			iconImg.src = iconUrl.value;
			iconImg.style.display = "";
			iconImgTr.style.display = "";
		}
	}
	
</script>
<style type="text/css">
<!--
	.tab tr {
		height:30px;
	}
	.paramTab {
		 width:80%;
		 border:1px #afccea solid;
		 border-collapse: collapse
	 }
	 .paramTab tr {
		 height:20px;
	 }
	.paramTab th{
		 border:1px #b2bbcc solid; 
		 background:url(../images/main/listTabTitleBg.gif) no-repeat right; 
		 line-height:24px; 
		 color:#323433; 
		 font-size:12px; 
		 word-break:keep-all; 
		 white-space:nowrap;
		  padding:0px 10px; 
	}
	.paramTab td {
		border:1px #cfd3ee solid; 
		line-height:20px; 
		color:#323433;  
		padding:0px 4px; 
	}
-->
</style>

</head>
<body>
  <!-- 头部 -->
  <div class="top">
  <div class="topright"></div>
  <div class="topleft"></div>
    <img src="../images/icon/baobiao.gif"><span class="title"><strong>首页推荐位(3.3版)信息 - 添加</strong></span>
  </div>
  <!-- 主体部分 -->
  <div class="tmiddle">&nbsp;
  	<div class="boxsearch">
		<div class="boxsearch3"></div>
	</div>
	
  	<div class="boxsearch">
  		<form id="editForm" name="editForm" method="post" action="" enctype="multipart/form-data">
  		<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0" class="tab">
				<tr>
					<td width="20%" align="right" style="padding-right:10px"><b>主题名<font color="red">*</font>：</b></td>
					<td width="30%" align="left" style="padding-left:10px">
					<input id="hrModel.subjectName" name="hrModel.subjectName"  maxlength="50" size="40" type="text" value=""/>
					</td>
					<td width="40%" align="left" style="padding-left:20px"><font color="green">为推荐位信息设置一个主题名，主题名不得超过50个中文字符。</font></td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>主题类型<font color="red">*</font>：</b></td>
					<td align="left" style="padding-left:10px">
					<select name="hrModel.subjectType" id="hrModel.subjectType" style="width:150px">
						<option value="0">无点击动作</option>
						<option value="1">客户端内嵌页</option>
						<option value="2">WAP页面</option>
						<option value="3">应用详情页</option>
						<option value="4">专题应用列表页</option>
					</select>
					</td>
					<td align="left" style="padding-left:20px"><font color="green">为推荐位图片信息选择一个展现方式。</font></td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>映射属性<font color="red">*</font>：</b></td>
					<td align="left" style="padding-left:10px">
					<input id="hrModel.subjectId" name="hrModel.subjectId"  maxlength="200" size="40" type="text" value=""/>
					</td>
					<td align="left" style="padding-left:20px"><font color="green">为推荐位映射一个属性，属性信息随类型变化，具体如下：1.客户端内嵌页对应属性为：跑马灯公告ID；2.WAP页面对应属性：WAP地址；3.应用详情页对应属性：应用ID；4.对应专题ID</font></td>
				</tr>
				<!--
				<tr>
					<td align="right" style="padding-right:10px"><b>WAP地址:</b></td>
					<td align="left" style="padding-left:10px">
					<input id="hrModel.subjectWapUrl" name="hrModel.subjectWapUrl"  maxlength="200" size="60" type="text" value=""/>
					</td>
					<td align="left"><font color="green">如果对应WAP地址，此处填写WAP地址</font></td>
				</tr>
				-->
				<tr>
					<td align="right" style="padding-right:10px"><b>分类目录下：</b></td>
					<td align="left" style="padding-left:10px">
					<select name="hrModel.appTypeId" id="hrModel.appTypeId" style="width:150px">
					<option value="1">最新</option>
					<option value="2">最热</option>
					<option value="3">金融导航</option>
					<!--<#if appTypeList?exists>
					<#list appTypeList as cl>
						<option value="${cl.appTypeId}">${cl.appTypeName}</option>
					</#list>
					</#if>-->
					</select>
					</td>
					<td align="left" style="padding-left:20px"><font color="green">为推荐位信息选择一个展现位置，选择分类游戏，则在游戏分类下显示推荐位信息。</font></td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>图片文件<font color="red">*</font>：</td>
					<td align="left" style="padding-left:10px">
					<input type="file" name="recommendPicFileUrl" id="recommendPicFileUrl" size="40" class="input" onpropertychange="viewImg()">
					</td>
					</td>
					<td align="left" style="padding-left:20px"><font color="green">上传展示图片文件，标准大小为250×80.</font></td>
				</tr>
				<tr id="iconImgTr" style="display:none">
					<td align="right" style="padding-right:10px"><b>图片文件预览</b></td>
					<td align="left" style="padding-left:10px" colspan=2>
					<img id="iconImg" src="" width="224" height="79" style="display:none"/>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>显示状态<font color="red">*</font>：</b></td>
					<td align="left" style="padding-left:10px">
					<select name="hrModel.subjectWapUrl" id="hrModel.subjectWapUrl" style="width:150px">
						<option value="0">无状态</option>
						<option value="3">最新</option>
					    <option value="2">加精</option>
					    <option value="1">热门</option>
					</select>
					</td>
					<td align="left" style="padding-left:20px"><font color="green">选择发布状态，此状态直接体现在客户端上。</font></td>
				</tr>
			</table>
		<div class="grayline"></div>
		<div class="boxsearch2">
			<a href="javascript:checkSaveForm()"><img src="../images/buttom/buttom_baocun.gif" alt="保存" /></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:window.history.back()'><img src="../images/buttom/buttom_fanhui.gif" alt="返回"/></a>
		</div>
	  	</form>
  	</div>
  </div>
  <!-- 尾部 -->
  <div class="bottom">
	<div class="bottomright"></div>
	<div class="bottomleft"></div>
  </div>
  
</body>
</html>