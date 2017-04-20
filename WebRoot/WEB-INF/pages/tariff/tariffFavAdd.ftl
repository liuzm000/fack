<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>资费类型列表</title>
<link href="${request.contextPath}/css/global.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
	.add li {
		height:30px;
	}
	.add li span{
		float:left;
		width:200px;
		text-align:right;
	}
-->
</style>
<!-- import jquery -->
<script type="text/javascript" src="${request.contextPath}/version/js/jquery-core/jquery-1.3.js"></script>
<script>
	function checkAddForm(){
		var favName      = document.getElementById("tariffFavModel.favName");
		var favContext   = document.getElementById("tariffFavModel.favContext");
		var favTypeId    = document.getElementById("tariffFavModel.favTypeId");
		var discount     = document.getElementById("tariffFavModel.discount");
		var presentTime  = document.getElementById("tariffFavModel.presentTime");
		var presentTimes = document.getElementById("tariffFavModel.presentTimes");
		var remarks      = document.getElementById("tariffFavModel.remarks");
		if(favName.value == ""){
			alert("策略名称不能为空！");
			favName.focus();
			return;
		}
		if(favContext.value == ""){
			alert("说明不能为空！");
			favContext.focus();
			return;
		}
		if(favTypeId.value == '0'){
			if(presentTime.value == ""){
				alert("赠送时间不能为空！");
				presentTime.focus();
				return;
			}
		}
		if(favTypeId.value == '1'){
			if(presentTimes.value == ""){
				alert("赠送次数不能为空！");
				presentTimes.focus();
				return;
			}
		}
		if(favTypeId.value == '2'){
			if(discount.value == ""){
				alert("折扣率不能为空！");
				discount.focus();
				return;
			}
		}
		addForm.submit();
	}
	
	function switchType(obj){
		var time = $("#time");
		var times = $("#times");
		var discounts = $("#discounts");
		if(obj == 0){
			time.css("display","");
			times.css("display","none");
			discounts.css("display","none");
		}else if(obj == 1){
			time.css("display","none");
			times.css("display","");
			discounts.css("display","none");
		}else{
			time.css("display","none");
			times.css("display","none");
			discounts.css("display","");
		}
	}
</script>
</head>
<body>
  <!-- 头部 -->
  <div class="top">
  <div class="topright"></div>
  <div class="topleft"></div>
    <img src="../images/icon/baobiao.gif"><span class="title"><strong>资费类型管理</strong> - 资费类型添加</span>
  </div>
  <!-- 主体部分 -->
  <div class="tmiddle">
  	&nbsp;
  	<div class="boxsearch">
	  	<form id="addForm" name="addForm" method="post" action="saveTariffFav.action">
	  	<div class="add">
		  	<ul>
		  		<li>
		  			<span>优惠策略名称：</span><input type="text" id="tariffFavModel.favName" name="tariffFavModel.favName" maxlength="100"/><font color=red>*</font></li>
				<li>
					<span>优惠策略说明：</span><input type="text" id="tariffFavModel.favContext" name="tariffFavModel.favContext" maxlength="200"/><font color=red>*</font></li>
				<li>
					<span>优惠类型：</span>
					<select type="text" id="tariffFavModel.favTypeId" name="tariffFavModel.favTypeId" onpropertychange="javascript:switchType(this.value);">
						<option value="0" selected>时间类优惠类</option>
						<option value="1">使用次数优惠类</option>
						<option value="2">资费优惠折扣类</option>
					</select><font color=red>*</font>
				</li>
				<li>
					<div id="time"><span>赠送时间：</span><input type="text" id="tariffFavModel.presentTime" name="tariffFavModel.presentTime" /><font color=red>*</font><font color="grey">（以秒为单位）</font></div>
					<div id="times" style="display:none"><span>赠送使用次数：</span><input type="text" id="tariffFavModel.presentTimes" name="tariffFavModel.presentTimes" /><font color=red>*</font></div>
					<div id="discounts" style="display:none"><span>折扣率：</span><input type="text" id="tariffFavModel.discount" name="tariffFavModel.discount" /><font color=red>*</font></div>
				</li>
				<li>
					<span>备注：</span>
					<textarea type="text" id="tariffFavModel.remarks" name="tariffFavModel.remarks" rows="3" cols="40" maxlength="200"
							  style="border:1px solid #7F9DB9;
									 background-color:#FFF;
									 color:#363636;
									 font-size:12px;
									 padding-left:3px;"></textarea>
			</ul>
		</div>
		&nbsp;
		<div class="grayline"></div>
		<div class="boxsearch2">
			<a href="javascript:checkAddForm()"><img src="../images/buttom/buttom_tijiao.gif" alt="提交" /></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:window.history.back()'><img src="../images/buttom/buttom_fanhui.gif" alt="返回"/></a>
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