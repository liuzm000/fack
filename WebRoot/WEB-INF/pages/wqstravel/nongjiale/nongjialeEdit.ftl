<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<#assign rootPath=request.scheme+"://"+request.serverName+":"+request.serverPort+request.contextPath />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${title?if_exists}修改餐馆</title>
<link href="${request.contextPath}/css/global.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/javascript/ui/css/ui.core.css"/>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/javascript/ui/css/ui.theme.css"/>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/javascript/ui/css/ui.dialog.css"/>
<script type="text/javascript" src="${request.contextPath}/javascript/jquery-core/jquery-1.3.js"></script>
<script type="text/javascript" src="${request.contextPath}/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${request.contextPath}/ckfinder/ckfinder.js"></script>
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
		var title = $("#name");
		if(title.val() == "" || title.val() == null){
			alert("请填写餐馆名称!");
			title.focus();
			return;
		}
		var time = $("#address");
		if(time.attr("value") == "" || time.attr("value") ==null) {
			alert("请填写餐馆地址!");
			time.focus();
			return;
		}
		var unit = $("#tele");
		if(unit.attr("value") == "" || unit.attr("value") ==null) {
			alert("请填写联系电话");
			unit.focus();
			return;
		}
		$("#editForm").attr("action","doNongJiaLeEdit.action");
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
    <img src="../images/icon/baobiao.gif"><span class="title"><strong>${title?if_exists}信息管理 - 修改</strong></span>
  </div>
  <!-- 主体部分 -->
  <div class="tmiddle">&nbsp;
  	<div class="boxsearch">
		<div class="boxsearch3"></div>
	</div>
	
  	<div class="boxsearch">
  		<form id="editForm" name="editForm" method="post" action="" enctype="multipart/form-data">
  		<input type="hidden" name="type" value="${type?if_exists}"/>
  		<input  name="searchName"  maxlength="100" size="60" type="hidden" value="${searchName}"/>
  		<input  name="searchAdd"  maxlength="100" size="60" type="hidden" value="${searchAdd}"/>
  		<input  name="id"  maxlength="100" size="60" type="hidden" value="${nongJiaLeModel.id?if_exists}"/>
  		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tab">
				<tr>
					<td width="20%" align="right" valign="top" style="padding-right:10px"><b>餐馆名称<font color="red">*</font>：</b></td>
					<td width="50%" align="left" style="padding-left:10px">
					<input id="name" name="nongJiaLeModel.name"   maxlength="50" size="60" type="text" value="${nongJiaLeModel.name?if_exists}"/>
					</td>
					<td width="30%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				<tr>
					<td width="20%" align="right" valign="top" style="padding-right:10px"><b>餐馆地址<font color="red">*</font>：</b></td>
					<td width="50%" align="left" style="padding-left:10px">
					<input id="address" name="nongJiaLeModel.addr"  maxlength="50" size="60" type="text" value="${nongJiaLeModel.addr?if_exists}"/>
					</td>
					<td width="30%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				
				<tr>
					<td width="20%" align="right" valign="top" style="padding-right:10px"><b>联系电话<font color="red">*</font>：</b></td>
					<td width="50%" align="left" style="padding-left:10px">
					<input id="tele" name="nongJiaLeModel.tele"  maxlength="50" size="60" type="text" value="${nongJiaLeModel.tele?if_exists}"/>
					</td>
					<td width="30%" align="left" style="padding-left:20px"><font color="green"></font></td>
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