<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<#assign rootPath=request.scheme+"://"+request.serverName+":"+request.serverPort+request.contextPath />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>产品类型</title>
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
    var produceName = $("#produceName");
    if(produceName.val() == "" || produceName.val() == null){
	  alert("请填写产品名称!");
	  produceName.focus();
	  return;
    }
	var iconImg = document.getElementById("produceIconFileUrl");
	if(iconImg.value == "" || iconImg.value == null){
		alert("请选择产品图标文件!");
		iconImg.focus();
		return;
	}
	
	//--start检查是否有选择产品内容图片--
	var count = 0;
	for(var i = 1;i<=10;i++){
		var id = "produceContentFileUrls_"+i;
		var contentImag = document.getElementById(id);
		if(contentImag.value == "" || contentImag.value == null){
			count++;
		}
	}
	if(10==count){
		alert("请至少选择一张产品内容图片!");
		var contentImag = document.getElementById("produceContentFileUrls_1");
		contentImag.focus();
		return;
	}
	//--end--
	
	/*
	var contentImgs = document.getElementById("produceContentFileUrls");
	if(contentImgs.value == "" || contentImgs.value == null){
		alert("请至少选择一张产品内容图片!");
		contentImgs.focus();
		return;
	}
	*/
	document.forms["editForm"].action = "doFarmProduceSave.action";
	document.forms["editForm"].submit();
  }
	
</script>
</head>
<body>
  <div class="top">
  <div class="topright"></div>
  <div class="topleft"></div>
    <img src="../images/icon/baobiao.gif"><span class="title"><strong>产品类型- 添加</strong></span>
  </div>
  <!-- 主体部分 -->
  <div class="tmiddle">&nbsp;
  	<div class="boxsearch">
		<div class="boxsearch3"></div>
	</div>
	
  	<div class="boxsearch">
  		<form id="editForm" name="editForm" method="post" action="" enctype="multipart/form-data">
  		<input id="searchName" name="searchName"  maxlength="50" size="40" type="hidden" value="${searchName?if_exists}"/>
  		<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0" class="tab">
				<tr>
					<td width="20%" align="right" style="padding-right:10px"><b>产品名称<font color="red">*</font>：</b></td>
					<td width="30%" align="left" style="padding-left:10px">
						<input id="produceName" name="algGoodsModel.name"  maxlength="50" size="40" type="text" value=""/>
					</td>
					<td width="40%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>产品图标<font color="red">*</font>：</td>
					<td align="left" style="padding-left:10px">
						<#--<input type="file" name="produceIconFileUrl" id="produceIconFileUrl" size="40" class="input" >-->
						<input type="file" name="produceIconFileUrl" id="produceIconFileUrl" size="21" onchange="document.getElementById('file_icon').value=this.value" class="text opacity">
						<input name="file_icon" id="file_icon" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片1<font color="red">*</font>：</td>
					<td align="left" style="padding-left:10px">
						<#--<input type="file" name="produceContentFileUrls" id="produceContentFileUrls_1" size="40" class="input">-->
						<input type="file" name="produceContentFileUrls" id="produceContentFileUrls_1" size="21" onchange="document.getElementById('file_1').value=this.value" class="text opacity">
						<input name="file_1" id="file_1" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片2：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="produceContentFileUrls" id="produceContentFileUrls_2" size="21" onchange="document.getElementById('file_2').value=this.value" class="text opacity">
						<input name="file_2" id="file_2" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片3：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="produceContentFileUrls" id="produceContentFileUrls_3" size="21" onchange="document.getElementById('file_3').value=this.value" class="text opacity">
						<input name="file_3" id="file_3" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片4：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="produceContentFileUrls" id="produceContentFileUrls_4" size="21" onchange="document.getElementById('file_4').value=this.value" class="text opacity">
						<input name="file_4" id="file_4" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片5：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="produceContentFileUrls" id="produceContentFileUrls_5" size="21" onchange="document.getElementById('file_5').value=this.value" class="text opacity">
						<input name="file_5" id="file_5" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片6：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="produceContentFileUrls" id="produceContentFileUrls_6" size="21" onchange="document.getElementById('file_6').value=this.value" class="text opacity">
						<input name="file_6" id="file_6" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片7：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="produceContentFileUrls" id="produceContentFileUrls_7" size="21" onchange="document.getElementById('file_7').value=this.value" class="text opacity">
						<input name="file_7" id="file_7" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片8：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="produceContentFileUrls" id="produceContentFileUrls_8" size="21" onchange="document.getElementById('file_8').value=this.value" class="text opacity">
						<input name="file_8" id="file_8" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片9：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="produceContentFileUrls" id="produceContentFileUrls_9" size="21" onchange="document.getElementById('file_9').value=this.value" class="text opacity">
						<input name="file_9" id="file_9" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:10px"><b>内容图片10：</td>
					<td align="left" style="padding-left:10px">
						<input type="file" name="produceContentFileUrls" id="produceContentFileUrls_10" size="21" onchange="document.getElementById('file_10').value=this.value" class="text opacity">
						<input name="file_10" id="file_10" value="" class="text"> 
						<input type="button" value="浏览..." class="text file_btn">
					</td>
					</td>
				</tr>
				
				<tr>
					<td align="right" style="padding-right:10px"><b>产品介绍<font color="red">*</font>：</b></td>
					<td align="left" style="padding-left:10px">
					<textarea id="produceIntroduction" name="algGoodsModel.descript"  maxlength="2000" cols="40" rows="10" ></textarea>
					</td>
					<td align="left" style="padding-left:20px"><font color="green"></font></td>
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