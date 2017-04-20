<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<#assign rootPath=request.scheme+"://"+request.serverName+":"+request.serverPort+request.contextPath />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>画卷类型</title>
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
<style>
.opacity {opacity:0;FILTER:Alpha(Opacity=0);}
.text {border: 1px solid #c0c1b3;height:22px;}
.text.opacity {position:absolute;height:22px;}
.text.file {width:129px!important;width:135px;z-index:100;}
.text.file_btn {height:22px;margin:0 0 -1px 0;width:auto!important;width:60px;background:#f0f0f0;padding:;}
</style> 
<script>
  function checkSaveForm(){
   var descript = $("#descript");
    if(descript.val() == "" || descript.val() == null){
	  alert("请填写画卷标题!");
	  descript.focus();
	  return;
    }
	document.forms["editForm"].action = "doPictureEdit.action";
	document.forms["editForm"].submit();
  }
	
</script>
</head>
<body>
  <div class="top">
  <div class="topright"></div>
  <div class="topleft"></div>
    <img src="../images/icon/baobiao.gif"><span class="title"><strong>画卷- 修改</strong></span>
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
					<td width="20%" align="right" style="padding-right:10px"><b>画卷标题<font color="red">*</font>：</b></td>
					<td width="30%" align="left" style="padding-left:10px">
						<input id="descript" name="descript"  maxlength="50" size="40"   value="${model.descript?if_exists}" />
						<input id="searchName" name="searchName"  maxlength="50" size="40" type="hidden" value="${searchName?if_exists}" />
						<input id="id" name="id"  maxlength="50" size="40" type="hidden" value="${model.id?if_exists}" />
						<input id="typeId" name="typeId"  maxlength="50" size="40" type="hidden" value="${typeId?if_exists}" />
					</td>
					<td width="40%" align="left" style="padding-left:10px"><font color="green"></font></td>
				</tr>
				<tr>
					<td width="20%" align="right" style="padding-right:10px"><b>画卷图片：</b></td>
					<td width="80%" align="left" style="padding-left:20px"><img src="${rootPath}/download${model.filePath?if_exists}" width="48" height="48"/> </td>
				</tr>
				<tr>
					<td width="20%"  align="right" style="padding-right:10px"><b>替换图片：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="produceContentFileUrls" id="produceContentFileUrls_1" size="21" onchange="document.getElementById('file_1').value=this.value" class="text opacity">
						<input name="file_1" id="file_1" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
			</table>
		<div class="grayline"></div>
		<div class="boxsearch2">
			<a href="javascript:checkSaveForm()"><img src="../images/buttom/buttom_baocun.gif" alt="保存" /></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:window.history.back();'><img src="../images/buttom/buttom_fanhui.gif" alt="返回"/></a>
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