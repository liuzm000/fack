<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<#assign rootPath=request.scheme+"://"+request.serverName+":"+request.serverPort+request.contextPath />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>招聘信息</title>
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
		var content = CKEDITOR.instances.editor1.getData();
		if(content == "" || content == null){
			alert("请填写正文!");
			return;
		}
		 $("#jobStr").attr("value",content);
		
		var content2 = CKEDITOR.instances.editor2.getData();
		if(content2 == "" || content2 == null){
			alert("请填写正文!");
			return;
		}
		 $("#infoStr").attr("value",content2);
		
		$("#editForm").attr("action","doTownJobsUpdate.action");
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
    <img src="../images/icon/baobiao.gif"><span class="title"><strong>招聘信息 - 修改</strong></span>
  </div>
  <!-- 主体部分 -->
  <div class="tmiddle">&nbsp;
  	<div class="boxsearch">
		<div class="boxsearch3"></div>
	</div>
	
  	<div class="boxsearch">
  		<form id="editForm" name="editForm" method="post" action="" enctype="multipart/form-data">
  		<input id="jobStr" name="jobModel.jobStr"  maxlength="50" size="60" type="hidden" value=""/>
  		<input id="infoStr" name="jobModel.infoStr"  maxlength="50" size="60" type="hidden" value=""/>
  		<input  name="jobModel.infoUrl"  maxlength="100" size="60" type="hidden" value="${jobModel.infoUrl}"/>
  		<input  name="jobModel.jobUrl"  maxlength="100" size="60" type="hidden" value="${jobModel.jobUrl}"/>
  		<input  name="jobModel.id"  maxlength="100" size="60" type="hidden" value="${jobModel.id}"/>
  		<input  name="searchName"  maxlength="100" size="60" type="hidden" value="${searchName}"/>
  		<input  name="searchUnit"  maxlength="100" size="60" type="hidden" value="${searchUnit}"/>
  		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="tab">
				<tr>
					<td width="20%" align="right" valign="top" style="padding-right:10px"><b>主题<font color="red">*</font>：</b></td>
					<td width="50%" align="left" style="padding-left:10px">
					<input id="title" name="jobModel.title"   maxlength="50" size="60" type="text" value="${jobModel.title?if_exists}"/>
					</td>
					<td width="30%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				
				<tr>
					<td width="20%" align="right" valign="top" style="padding-right:10px"><b>时间<font color="red">*</font>：</b></td>
					<td width="50%" align="left" style="padding-left:10px">
					<input type="text" size="14" id="createTime" name="jobModel.createTime" class="Wdate"   maxlength="50" size="60"  onFocus="WdatePicker({startDate:'%y-%M-01',readOnly:true})" size="40" value = "${jobModel.createTime}"/>
					</td>
					<td width="30%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				<tr>
					<td align="right" valign="top" style="padding-right:10px"><b>招聘信息<font color="red">*</font>：</b></td>
					<td colspan="1" align="center" style="padding-left:10px" width="100%">
					<textarea id="editor1" name="editor1">${jobModel.jobStr?if_exists}</textarea>
					</td>
					
					
					<script type="text/javascript">
					if (typeof CKEDITOR == 'undefined') {
						document.write('加载CKEditor失败!');
					}else {
						var vareditor = CKEDITOR.replace('editor1');
						CKFinder.setupCKEditor(vareditor, '/ckfinder/');
					}
					</script>
				</tr>
				<tr>
					<td width="20%" align="right" valign="top" style="padding-right:10px"><b>单位<font color="red">*</font>：</b></td>
					<td width="50%" align="left" style="padding-left:10px">
					<input id="unit" name="jobModel.unit"  maxlength="50" size="60" type="text" value="${jobModel.unit}"/>
					</td>
					<td width="30%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				<tr>
					<td width="20%" align="right" valign="top" style="padding-right:10px"><b>联系人<font color="red">*</font>：</b></td>
					<td width="50%" align="left" style="padding-left:10px">
					<input id="unit" name="jobModel.contactUser"  maxlength="50" size="60" type="text" value="${jobModel.contactUser}"/>
					</td>
					<td width="30%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				<tr>
					<td width="20%" align="right" valign="top" style="padding-right:10px"><b>电话号码<font color="red">*</font>：</b></td>
					<td width="50%" align="left" style="padding-left:10px">
					<input id="unit" name="jobModel.contactPhone"  maxlength="50" size="60" type="text" value="${jobModel.contactPhone}"/>
					</td>
					<td width="30%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				
				<tr>
					<td align="right" valign="top" style="padding-right:10px"><b>单位信息<font color="red">*</font>：</b></td>
					<td colspan="2" align="center" style="padding-left:10px" width="100%">
					<textarea id="editor2" name="editor2">${jobModel.infoStr?if_exists}</textarea>
					</td>
					
					
					<script type="text/javascript">
					if (typeof CKEDITOR == 'undefined') {
						document.write('加载CKEditor失败!');
					}else {
						var vareditor = CKEDITOR.replace('editor2');
						CKFinder.setupCKEditor(vareditor, '/ckfinder/');
					}
					</script>
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