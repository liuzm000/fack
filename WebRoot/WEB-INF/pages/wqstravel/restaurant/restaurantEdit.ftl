<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<#assign rootPath=request.scheme+"://"+request.serverName+":"+request.serverPort+request.contextPath />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>酒店信息管理</title>
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
		var unit = $("#tele1");
		if(unit.attr("value") == "" || unit.attr("value") ==null) {
			alert("请至少填写一个电话");
			unit.focus();
			return;
		}
		
		
		var content = CKEDITOR.instances.contentStr.getData();
		if(content == "" || content == null){
			alert("请填写正文!");
			return;
		}
		$("#editForm").attr("action","doRestaurantEdit.action");
		$("#editForm").submit();
	}
	
	
	$(function(){
	//设置电话号码
		var teles='${hotel.tele?if_exists}';
		var arr=new Array();
		arr=teles.split(',');//注split可以用字符或字符串分割
		for(var i=0;i<arr.length;i++){
			var id = "#tele"+(i+1);
			var tel = $(id).val(arr[i]);
		}
	})
</script>
<style type="text/css">

</style>

</head>
<body>
  <!-- 头部 -->
  <div class="top">
  <div class="topright"></div>
  <div class="topleft"></div>
    <img src="../images/icon/baobiao.gif"><span class="title"><strong>餐馆信息管理 - 修改</strong></span>
  </div>
  <!-- 主体部分 -->
  <div class="tmiddle">&nbsp;
  	<div class="boxsearch">
		<div class="boxsearch3"></div>
	</div>
	
  	<div class="boxsearch">
  		<form id="editForm" name="editForm" method="post" action="" enctype="multipart/form-data">
  		<input  name="searchName"  maxlength="100" size="60" type="hidden" value="${searchName}"/>
  		<input  name="searchAdd"  maxlength="100" size="60" type="hidden" value="${searchAdd}"/>
  		<input  name="id"  maxlength="100" size="60" type="hidden" value="${hotel.id?if_exists}"/>
  		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tab">
				<tr>
					<td width="20%" align="right" valign="top" style="padding-right:10px"><b>餐馆名称<font color="red">*</font>：</b></td>
					<td width="50%" align="left" style="padding-left:10px">
					<input id="name" name="hotel.name"   maxlength="50" size="60" type="text" value="${hotel.name?if_exists}"/>
					</td>
					<td width="30%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				<tr>
					<td width="20%" align="right" valign="top" style="padding-right:10px"><b>餐馆地址<font color="red">*</font>：</b></td>
					<td width="50%" align="left" style="padding-left:10px">
					<input id="address" name="hotel.addr"  maxlength="50" size="60" type="text" value="${hotel.addr?if_exists}"/>
					</td>
					<td width="30%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				
				<tr>
					<td width="20%" align="right" valign="top" style="padding-right:10px"><b>电话1<font color="red">*</font>：</b></td>
					<td width="50%" align="left" style="padding-left:10px">
					<input id="tele1" name="teles"  maxlength="50" size="60" type="text" value=""/>
					</td>
					<td width="30%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				<tr>
					<td width="20%" align="right" valign="top" style="padding-right:10px"><b>电话2：</b></td>
					<td width="50%" align="left" style="padding-left:10px">
					<input id="tele2" name="teles"  maxlength="50" size="60" type="text" value=""/>
					</td>
					<td width="30%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				<tr>
					<td width="20%" align="right" valign="top" style="padding-right:10px"><b>电话3：</b></td>
					<td width="50%" align="left" style="padding-left:10px">
					<input id="tele3" name="teles"  maxlength="50" size="60" type="text" value=""/>
					</td>
					<td width="30%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				<tr>
					<td width="20%" align="right" valign="top" style="padding-right:10px"><b>电话4：</b></td>
					<td width="50%" align="left" style="padding-left:10px">
					<input id="tele4" name="teles"  maxlength="50" size="60" type="text" value=""/>
					</td>
					<td width="30%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				<tr>
					<td width="20%" align="right" valign="top" style="padding-right:10px"><b>电话5：</b></td>
					<td width="50%" align="left" style="padding-left:10px">
					<input id="tele5" name="teles"  maxlength="50" size="60" type="text" value=""/>
					</td>
					<td width="30%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				
				
				<#--餐馆的房间类型暂时不提供，客户端写死大厅或包厢
				<tr>
					<td width="20%" align="right" valign="top" style="padding-right:10px"><b>房间类型1<font color="red">*</font>：</b></td>
					<td width="50%" align="left" style="padding-left:10px">
						<input id="roomType1" name="roomTypes"  maxlength="25" size="30" type="text" value=""/>
						<b>房间价格<font color="red">*</font>：</b>
						<input id="price1" name="prices"  maxlength="10" size="15" type="text" value=""/><b>元</b>
					</td>
					<td width="30%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				<tr>
					<td width="20%" align="right" valign="top" style="padding-right:10px"><b>房间类型2：</b></td>
					<td width="50%" align="left" style="padding-left:10px">
						<input id="roomType2" name="roomTypes"  maxlength="25" size="30" type="text" value=""/>
						<b>房间价格  ：</b>
						<input id="price2" name="prices"  maxlength="10" size="15" type="text" value=""/><b>元</b>
					</td>
					<td width="30%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				<tr>
					<td width="20%" align="right" valign="top" style="padding-right:10px"><b>房间类型3：</b></td>
					<td width="50%" align="left" style="padding-left:10px">
						<input id="roomType3" name="roomTypes"  maxlength="25" size="30" type="text" value=""/>
						<b>房间价格  ：</b>
						<input id="price3" name="prices"  maxlength="10" size="15" type="text" value=""/><b>元</b>
					</td>
					<td width="30%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				<tr>
					<td width="20%" align="right" valign="top" style="padding-right:10px"><b>房间类型4：</b></td>
					<td width="50%" align="left" style="padding-left:10px">
						<input id="roomType4" name="roomTypes"  maxlength="25" size="30" type="text" value=""/>
						<b>房间价格  ：</b>
						<input id="price4" name="prices"  maxlength="10" size="15" type="text" value=""/><b>元</b>
					</td>
					<td width="30%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				<tr>
					<td width="20%" align="right" valign="top" style="padding-right:10px"><b>房间类型5：</b></td>
					<td width="50%" align="left" style="padding-left:10px">
						<input id="roomType5" name="roomTypes"  maxlength="25" size="30" type="text" value=""/>
						<b>房间价格  ：</b>
						<input id="price5" name="prices"  maxlength="10" size="15" type="text" value=""/><b>元</b>
					</td>
					<td width="30%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				-->
				<tr>
					<td align="right" style="padding-right:10px"><b>餐馆图片集：</td>
					<td align="left" style="padding-left:10px">
						<#if iconList?exists>
							<#list iconList as icon>
								<img width="100px" height="100px" src="${rootPath}/download${icon.path}"/>					
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