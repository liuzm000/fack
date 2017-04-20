<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<#assign rootPath=request.scheme+"://"+request.serverName+":"+request.serverPort+request.contextPath />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>渔家乐信息管理</title>
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
			alert("请填写名称!");
			title.focus();
			return;
		}
		
		
		var content = CKEDITOR.instances.contentStr.getData();
		if(content == "" || content == null){
			alert("请填写正文!");
			return;
		}
		$("#editForm").attr("action","doYuJiaLeEdit.action");
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
    <img src="../images/icon/baobiao.gif"><span class="title"><strong>渔家乐信息 - 修改</strong></span>
  </div>
  <!-- 主体部分 -->
  <div class="tmiddle">&nbsp;
  	<div class="boxsearch">
		<div class="boxsearch3"></div>
	</div>
	
  	<div class="boxsearch">
  		<form id="editForm" name="editForm" method="post" action="" enctype="multipart/form-data">
  		<input  name="id"  maxlength="100" size="60" type="hidden" value="${model.id?if_exists}"/>
  		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tab">
				<tr>
					<td width="20%" align="right" valign="top" style="padding-right:10px"><b>渔家乐名称<font color="red">*</font>：</b></td>
					<td width="50%" align="left" style="padding-left:10px">
					<input id="name" name="name"   maxlength="50" size="60" type="text" value="${model.name?if_exists}"/>
					</td>
					<td width="30%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				
				<tr>
					<td align="right" style="padding-right:10px"><b>渔家乐图片集：</td>
					<td align="left" style="padding-left:10px">
						<#if model.icons?exists>
							<#list model.icons?split(",") as s>
								<img src="${rootPath}/download${s}" width="48" height="48"/>
							</#list>
						</#if>
					</td>
			   </tr>
				
				
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片1：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="hotelIconUrls" id="hotelIconUrls_1" size="21" onchange="document.getElementById('file_1').value=this.value" class="text opacity">
						<input name="file_1" id="file_1" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片2：</td>
					<td align="left" style="padding-left:10px">
						<#--<input type="file" name="hotelIconUrls" id="hotelIconUrls_2" size="40" class="input">-->
						<input type="file" name="hotelIconUrls" id="hotelIconUrls_2" size="21" onchange="document.getElementById('file_2').value=this.value" class="text opacity">
						<input name="file_2" id="file_2" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片3：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="hotelIconUrls" id="hotelIconUrls_3" size="21" onchange="document.getElementById('file_3').value=this.value" class="text opacity">
						<input name="file_3" id="file_3" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片4：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="hotelIconUrls" id="hotelIconUrls_4" size="21" onchange="document.getElementById('file_4').value=this.value" class="text opacity">
						<input name="file_4" id="file_4" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片5：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="hotelIconUrls" id="hotelIconUrls_5" size="21" onchange="document.getElementById('file_5').value=this.value" class="text opacity">
						<input name="file_5" id="file_5" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片6：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="hotelIconUrls" id="hotelIconUrls_6" size="21" onchange="document.getElementById('file_6').value=this.value" class="text opacity">
						<input name="file_6" id="file_6" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片7：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="hotelIconUrls" id="hotelIconUrls_7" size="21" onchange="document.getElementById('file_7').value=this.value" class="text opacity">
						<input name="file_7" id="file_7" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片8：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="hotelIconUrls" id="hotelIconUrls_8" size="21" onchange="document.getElementById('file_8').value=this.value" class="text opacity">
						<input name="file_8" id="file_8" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片9：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="hotelIconUrls" id="hotelIconUrls_9" size="21" onchange="document.getElementById('file_9').value=this.value" class="text opacity">
						<input name="file_9" id="file_9" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片10：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="hotelIconUrls" id="hotelIconUrls_10" size="21" onchange="document.getElementById('file_10').value=this.value" class="text opacity">
						<input name="file_10" id="file_10" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				
				<tr>
					<td align="right" valign="top" style="padding-right:10px"><b>正文<font color="red">*</font>：</b></td>
					<td colspan="2" align="center" style="padding-left:10px" width="100%">
					<textarea id="contentStr" name="contentStr">${contentStr?if_exists}</textarea>
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