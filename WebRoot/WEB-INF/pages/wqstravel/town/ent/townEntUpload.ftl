<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<#assign rootPath=request.scheme+"://"+request.serverName+":"+request.serverPort+request.contextPath />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>企业名录</title>
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
		$("#editForm").attr("action","doTownEntUploadSubmit.action");
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
    <img src="../images/icon/baobiao.gif"><span class="title"><strong>${id?if_exists} - ${name?if_exists} - 插图上传</strong></span>
  </div>
  <!-- 主体部分 -->
  <div class="tmiddle">&nbsp;
  	<div class="boxsearch">
		<div class="boxsearch3"></div>
	</div>
	
  	<div class="boxsearch">
  		<form id="editForm" name="editForm" method="post" action="" enctype="multipart/form-data">
  		<input id="searchName" name="searchName" value="${searchName?if_exists}" maxlength="50" size="15" type="hidden" />
  		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tab">
  				<tr>
					<td width="20%" align="right" valign="top" style="padding-right:10px"><b>ID：</b></td>
					<td width="50%" align="left" style="padding-left:10px">${id?if_exists}
					<input id="name" name="entModel.id"   maxlength="50" size="60" type="hidden" value="${id?if_exists}"/>
					</td>
					<td width="30%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				<tr>
					<td width="20%" align="right" valign="top" style="padding-right:10px"><b>企业名称：</b></td>
					<td width="50%" align="left" style="padding-left:10px">${name?if_exists}
					<input id="name" name="entModel.name"   maxlength="50" size="60" type="hidden" value="${name?if_exists}"/>
					</td>
					<td width="30%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				
				<tr>
					<td align="right" style="padding-right:10px"><b>图片/序号：1：</b></td>
					<td align="left" style="padding-left:10px">
						<#--<input type="file" name="entImgFileUrl1" id="entImgFileUrl1" size="60" class="input"">-->
						<input type="file" name="entImgFileUrl1" id="entImgFileUrl1" size="21" onchange="document.getElementById('file_1').value=this.value" class="text opacity">
						<input name="file_1" id="file_1" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					<td align="left" style="padding-left:20px"><font color="green">上传图片，序号为当前图片排序编号，请按顺序上传。如需修改，请重新上传替换当前图片。</font></td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>图片/序号：2：</b></td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="entImgFileUrl2" id="entImgFileUrl2" size="21" onchange="document.getElementById('file_2').value=this.value" class="text opacity">
						<input name="file_2" id="file_2" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>图片/序号：3：</b></td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="entImgFileUrl3" id="entImgFileUrl3" size="21" onchange="document.getElementById('file_3').value=this.value" class="text opacity">
						<input name="file_3" id="file_3" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>图片/序号：4：</b></td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="entImgFileUrl4" id="entImgFileUrl4" size="21" onchange="document.getElementById('file_4').value=this.value" class="text opacity">
						<input name="file_4" id="file_4" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>图片/序号：5：</b></td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="entImgFileUrl5" id="entImgFileUrl5" size="21" onchange="document.getElementById('file_5').value=this.value" class="text opacity">
						<input name="file_5" id="file_5" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>图片/序号：6：</b></td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="entImgFileUrl6" id="entImgFileUrl6" size="21" onchange="document.getElementById('file_6').value=this.value" class="text opacity">
						<input name="file_6" id="file_6" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>图片/序号：7：</b></td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="entImgFileUrl7" id="entImgFileUrl7" size="21" onchange="document.getElementById('file_7').value=this.value" class="text opacity">
						<input name="file_7" id="file_7" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>图片/序号：8：</b></td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="entImgFileUrl8" id="entImgFileUrl8" size="21" onchange="document.getElementById('file_8').value=this.value" class="text opacity">
						<input name="file_8" id="file_8" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>图片/序号：9：</b></td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="entImgFileUrl9" id="entImgFileUrl9" size="21" onchange="document.getElementById('file_9').value=this.value" class="text opacity">
						<input name="file_9" id="file_9" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>图片/序号：10：</b></td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="entImgFileUrl10" id="entImgFileUrl10" size="21" onchange="document.getElementById('file_10').value=this.value" class="text opacity">
						<input name="file_10" id="file_10" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
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