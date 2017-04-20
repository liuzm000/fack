<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<#assign rootPath=request.scheme+"://"+request.serverName+":"+request.serverPort+request.contextPath />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>名镇信息</title>
<link href="${request.contextPath}/css/global.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/javascript/ui/css/ui.core.css"/>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/javascript/ui/css/ui.theme.css"/>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/javascript/ui/css/ui.dialog.css"/>
<!-- import jquery -->
<script type="text/javascript" src="${request.contextPath}/javascript/jquery-core/jquery-1.3.js"></script>
<!-- import jquery ui -->


<script type="text/javascript" src="${request.contextPath}/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${request.contextPath}/ckfinder/ckfinder.js"></script>
<script type="text/javascript" src="${request.contextPath}/javascript/DatePicker/WdatePicker.js"></script>

<script>
	function checkSaveForm(){
		
		var title = document.getElementById("entModel.name");
		if(title.value == "" || title.value == null){
			alert("请填写企业名称!");
			title.focus();
			return;
		}
		
		var content = CKEDITOR.instances.contentStr.getData();
		if(content == "" || content == null){
			alert("请填写正文!");
			document.getElementById("contentStr").focus();
			return;
		}
		document.forms["editForm"].action = "doTownEntEditSubmit.action";
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
    <img src="../images/icon/baobiao.gif"><span class="title"><strong>企业名录 - 修改</strong></span>
  </div>
  <!-- 主体部分 -->
  <div class="tmiddle">&nbsp;
  	<div class="boxsearch">
		<div class="boxsearch3"></div>
	</div>
	
  	<div class="boxsearch">
  		<form id="editForm" name="editForm" method="post" action="" enctype="multipart/form-data">
  		<input id="searchName" name="searchName"  maxlength="50" size="60" type="hidden" value="${searchName}"/>
  		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tab">
				<tr>
					<td width="20%" align="right" valign="top" style="padding-right:10px"><b>企业名称<font color="red">*</font>：</b></td>
					<td width="50%" align="left" style="padding-left:10px">
					<input id="entModel.name" name="entModel.name" value=${model.name}  maxlength="50" size="60" type="text" value=""/>
					</td>
					<td width="30%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				
				
				<tr>
					<td align="right" valign="top" style="padding-right:10px"><b>正文<font color="red">*</font>：</b></td>
					<td colspan="2" align="center" style="padding-left:10px" width="100%">
					<textarea id="contentStr" name="contentStr">${model.contentStr?if_exists}</textarea>
					</td>
					<script type="text/javascript">
					if (typeof CKEDITOR == 'undefined') {
						document.write('加载CKEditor失败!');
					}else {
						var vareditor = CKEDITOR.replace('contentStr');
						CKFinder.setupCKEditor(vareditor, '/ckfinder/');
					}
					</script>
				</tr>
			</table>
			<input type="hidden" name="entModel.id" value="${model.id}">
			<input type="hidden"  name="entModel.htmlPath" value="${model.htmlPath}">
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