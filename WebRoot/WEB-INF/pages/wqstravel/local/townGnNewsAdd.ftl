<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<#assign rootPath=request.scheme+"://"+request.serverName+":"+request.serverPort+request.contextPath />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>国内新闻</title>
<link href="${request.contextPath}/css/global.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/javascript/ui/css/ui.core.css"/>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/javascript/ui/css/ui.theme.css"/>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/javascript/ui/css/ui.dialog.css"/>
<script type="text/javascript" src="${request.contextPath}/javascript/jquery-core/jquery-1.3.js"></script>
<script type="text/javascript" src="${request.contextPath}/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${request.contextPath}/ckfinder/ckfinder.js"></script>
<script type="text/javascript" src="${request.contextPath}/javascript/DatePicker/WdatePicker.js"></script>

<script>
	function checkSaveForm(){
		var title = $("#title");
		if(title.val() == "" || title.val() == null){
			alert("请填写主题名称!");
			title.focus();
			return;
		}
		var time = $("#createTime");
		if(time.attr("value") == "" || time.attr("value") ==null) {
			alert("请填写发布时间!");
			time.focus();
			return;
		}
		var unit = $("#unit");
		if(unit.attr("value") == "" || unit.attr("value") ==null) {
			alert("请填写发布单位!");
			unit.focus();
			return;
		}
		$("#editForm").attr("action","doTownGnNewsSave.action?typeId=3");
		$("#editForm").submit();
	}
</script>
<style type="text/css">

</style>

</head>
<body>
  <!-- 头部 -->
  <div class="top">
  <div class="topright"></div>
  <div class="topleft"></div>
    <img src="../images/icon/baobiao.gif"><span class="title"><strong>国内新闻 - 增加</strong></span>
  </div>
  <!-- 主体部分 -->
  <div class="tmiddle">&nbsp;
  	<div class="boxsearch">
		<div class="boxsearch3"></div>
	</div>
	
  	<div class="boxsearch">
  		<form id="editForm" name="editForm" method="post" action="" enctype="multipart/form-data">
  		<input id="contentStr" name="msgModel.contentStr"  maxlength="50" size="60" type="hidden" value=""/>
  		<input  name="searchName"  maxlength="100" size="60" type="hidden" value="${searchName}"/>
  		<input  name="searchUnit"  maxlength="100" size="60" type="hidden" value="${searchUnit}"/>
  		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tab">
				<tr>
					<td width="20%" align="right" valign="top" style="padding-right:10px"><b>主题<font color="red">*</font>：</b></td>
					<td width="50%" align="left" style="padding-left:10px">
					<input id="title" name="msgModel.title"   maxlength="50" size="60" type="text" value=""/>
					</td>
					<td width="30%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				
				<tr>
					<td width="20%" align="right" valign="top" style="padding-right:10px"><b>时间<font color="red">*</font>：</b></td>
					<td width="50%" align="left" style="padding-left:10px">
					<input type="text" size="14" id="createTime" name="msgModel.createTime" class="Wdate"   maxlength="50" size="60"  onFocus="WdatePicker({startDate:'%y-%M-01',readOnly:true})" size="40"/>
					</td>
					<td width="30%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				
				<tr>
					<td width="20%" align="right" valign="top" style="padding-right:10px"><b>单位<font color="red">*</font>：</b></td>
					<td width="50%" align="left" style="padding-left:10px">
					<input id="unit" name="msgModel.unit"  maxlength="50" size="60" type="text" value=""/>
					</td>
					<td width="30%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				
				<tr>
					<td align="right" valign="top" style="padding-right:10px"><b>链接地址<font color="red">*</font>：</b></td>
					<td width="50%" align="left" style="padding-left:10px">
					<input id="fileUrl" name="msgModel.fileUrl"  maxlength="50" size="60" type="text" value=""/>
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