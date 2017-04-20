<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<#assign rootPath=request.scheme+"://"+request.serverName+":"+request.serverPort+request.contextPath />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>热门搜索词</title>
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
		var keyword = document.getElementById("hwModel.keyword");
		if(keyword.value == "" || keyword.value == null){
			alert("请填写一个词汇!");
			keyword.focus();
			return;
		}
		document.forms["editForm"].action = "saveHotKeyword.action";
		document.forms["editForm"].submit();
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
    <img src="../images/icon/baobiao.gif"><span class="title"><strong>热门搜索词 - 添加</strong></span>
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
					<td width="20%" align="right" style="padding-right:10px"><b>搜索词<font color="red">*</font>：</b></td>
					<td width="30%" align="left" style="padding-left:10px">
					<input id="hwModel.keyword" name="hwModel.keyword"  maxlength="50" size="40" type="text" value=""/>
					</td>
					<td width="40%" align="left" style="padding-left:20px"><font color="green">设置一个搜索词，例如"财库管家"等</font></td>
				</tr>
				<tr>
					<td width="20%" align="right" style="padding-right:10px"><b>搜索词描述：</b></td>
					<td width="30%" align="left" style="padding-left:10px">
					<input id="hwModel.keyDesc" name="hwModel.keyDesc"  maxlength="50" size="40" type="text" value=""/>
					</td>
					<td width="40%" align="left" style="padding-left:20px"><font color="green">备用字段，可记录该词产生或者意义。可不填</font></td>
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