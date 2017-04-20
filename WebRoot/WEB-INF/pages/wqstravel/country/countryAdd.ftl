<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<#assign rootPath=request.scheme+"://"+request.serverName+":"+request.serverPort+request.contextPath />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>添加美丽乡村</title>
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
		var countryName = $("#countryName");
		if(countryName.val() == "" || countryName.val() == null){
			alert("请填写乡村名称!");
			countryName.focus();
			return;
		}
		
		
		
		var iconImg = document.getElementById("contentFileUrls_1");
		if(iconImg.value == "" || iconImg.value == null){
			alert("请选择乡村图片文件1!");
			iconImg.focus();
			return;
		}
		
		
		var countryDesc = $("#countryDesc");
		if(countryDesc.val() == "" || countryDesc.val() == null){
			alert("请填写乡村简介!");
			countryDesc.focus();
			return;
		}
		
		
		var content = CKEDITOR.instances.countryContent.getData();
		if(content == "" || content == null){
			alert("请填写正文!");
			return;
		}
		
		$("#editForm").attr("action","doCountrySave.action");
		$("#editForm").submit();
	}
</script>
<style>
.opacity {opacity:0;FILTER:Alpha(Opacity=0);}
.text {border: 1px solid #c0c1b3;height:22px;}
.text.opacity {position:absolute;height:22px;}
.text.file {width:129px!important;width:135px;z-index:100;}
.text.file_btn {height:22px;margin:0 0 -1px 0;width:auto!important;width:60px;background:#f0f0f0;padding:;}
</style> 

</head>
<body>
  <!-- 头部 -->
  <div class="top">
  <div class="topright"></div>
  <div class="topleft"></div>
    <img src="../images/icon/baobiao.gif"><span class="title"><strong>美丽乡村信息 - 增加</strong></span>
  </div>
  <!-- 主体部分 -->
  <div class="tmiddle">&nbsp;
  	<div class="boxsearch">
		<div class="boxsearch3"></div>
	</div>
	
  	<div class="boxsearch">
  		<form id="editForm" name="editForm" method="post" action="" enctype="multipart/form-data">
  		<input type="hidden" name="searchCountryName" value="${searchCountryName?if_exists}"/>
  		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tab">
				<tr>
					<td width="20%" align="right" valign="top" style="padding-right:10px"><b>乡村名称<font color="red">*</font>：</b></td>
					<td width="50%" align="left" style="padding-left:10px">
					<input id="countryName" name="countryName"   maxlength="50" size="60" type="text" value=""/>
					</td>
					<td width="30%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				
				
				
				
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片1<font color="red">*</font>：</b></td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="contentFileUrls" id="contentFileUrls_1" size="21" onchange="document.getElementById('file_1').value=this.value" class="text opacity">
						<input name="file_1" id="file_1" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片2：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="contentFileUrls" id="contentFileUrls_2" size="21" onchange="document.getElementById('file_2').value=this.value" class="text opacity">
						<input name="file_2" id="file_2" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片3：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="contentFileUrls" id="contentFileUrls_3" size="21" onchange="document.getElementById('file_3').value=this.value" class="text opacity">
						<input name="file_3" id="file_3" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片4：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="contentFileUrls" id="contentFileUrls_4" size="21" onchange="document.getElementById('file_4').value=this.value" class="text opacity">
						<input name="file_4" id="file_4" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片5：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="contentFileUrls" id="contentFileUrls_5" size="21" onchange="document.getElementById('file_5').value=this.value" class="text opacity">
						<input name="file_5" id="file_5" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片6：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="contentFileUrls" id="contentFileUrls_6" size="21" onchange="document.getElementById('file_6').value=this.value" class="text opacity">
						<input name="file_6" id="file_6" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片7：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="contentFileUrls" id="contentFileUrls_7" size="21" onchange="document.getElementById('file_7').value=this.value" class="text opacity">
						<input name="file_7" id="file_7" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片8：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="contentFileUrls" id="contentFileUrls_8" size="21" onchange="document.getElementById('file_8').value=this.value" class="text opacity">
						<input name="file_8" id="file_8" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片9：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="contentFileUrls" id="contentFileUrls_9" size="21" onchange="document.getElementById('file_9').value=this.value" class="text opacity">
						<input name="file_9" id="file_9" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片10：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="contentFileUrls" id="contentFileUrls_10" size="21" onchange="document.getElementById('file_10').value=this.value" class="text opacity">
						<input name="file_10" id="file_10" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				
				
				
				<tr>
					<td align="right" style="padding-right:10px"><b>乡村视频图标：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="videoIconUrl" id="videoIconFileUrls_11" size="21" onchange="document.getElementById('file_11').value=this.value" class="text opacity">
						<input name="file_11" id="file_11" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				
				
				<tr>
					<td align="right" style="padding-right:10px"><b>乡村视频：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="videoFileUrl" id="videoIconFileUrls_12" size="21" onchange="document.getElementById('file_12').value=this.value" class="text opacity">
						<input name="file_12" id="file_12" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				
				
				<tr>
					<td align="right" style="padding-right:10px"><b>乡村简介<font color="red">*</font>：</b></b></td>
					<td align="left" style="padding-left:10px">
					<textarea id="countryDesc" name="countryDesc"  maxlength="2000" cols="40" rows="10" ></textarea>
					</td>
					<td align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				
				
				<tr>
					<td align="right" valign="top" style="padding-right:10px"><b>正文<font color="red">*</font>：</b></td>
					<td colspan="2" align="center" style="padding-left:10px" width="100%">
					<textarea id="countryContent" name="countryContent"></textarea>
					</td>
					<script type="text/javascript">
					if (typeof CKEDITOR == 'undefined') {
						document.write('加载CKEditor失败!');
					}else {
						var vareditor = CKEDITOR.replace('countryContent');
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