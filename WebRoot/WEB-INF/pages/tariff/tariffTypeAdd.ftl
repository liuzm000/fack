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
		var etypeName    = document.getElementById("tariffTypeModel.etypeName");
		var etypeContext = document.getElementById("tariffTypeModel.etypeContext");
		var proFee       = document.getElementById("tariffTypeModel.proFee");
		var unit         = document.getElementById("tariffTypeModel.unit");
		var remarks      = document.getElementById("tariffTypeModel.remarks");
		if(etypeName.value == ""){
			alert("类型名称不能为空！");
			etypeName.focus();
			return;
		}
		if(etypeContext.value == ""){
			alert("说明不能为空！");
			etypeContext.focus();
			return;
		}
		if(proFee.value == ""){
			alert("费率不能为空！");
			proFee.focus();
			return;
		}
		if(unit.value == ""){
			alert("计量单位不能为空！");
			unit.focus();
			return;
		}
		addForm.submit();
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
	  	<form id="addForm" name="addForm" method="post" action="saveTariffType.action">
	  	<div class="add">
		  	<ul>
		  		<li>
		  			<span>类型名称：</span><input type="text" id="tariffTypeModel.etypeName" name="tariffTypeModel.etypeName" maxlength="50"/><font color=red>*</font></li>
				<li>
					<span>类型说明：</span><input type="text" id="tariffTypeModel.etypeContext" name="tariffTypeModel.etypeContext" maxlength="200"/><font color=red>*</font></li>
				<li>
					<span>费率：</span><input type="text" id="tariffTypeModel.proFee" name="tariffTypeModel.proFee" maxlength="8"/><font color=red>*</font></li>
				<li>
					<span>计量单位：</span><input type="text" id="tariffTypeModel.unit" name="tariffTypeModel.unit" maxlength="100"/><font color=red>*</font></li>
				<li>
					<span>备注：</span>
					<textarea type="text" id="tariffTypeModel.remarks" name="tariffTypeModel.remarks" rows="3" cols="40" maxlength="100"
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