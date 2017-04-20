<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>SP产品接入管理 - 修改产品</title>
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.add li {
	height:30px;
}
.add li span{
	float:left;
	width:150px;
	text-align:right;
}
-->
</style>
<script type="text/javascript">
function $(id)
{
return document.getElementById(id);
}

function dosubmit()
{
if ($('name').value==''){
	alert('请输入SP产品名称！');
	$('name').focus();
	return;
}
//试用期策略设置
if($("proValid").checked){
	if($("profee.days").value!="0" && $("profee.days").value!=""){
	   if($("profee.times").value!="0" && $("profee.times").value!=""){
	   	alert("试用期只能按天数或次数使用，不能同时设置两种使用方式！");
 		return;
	   }
	}
	$("profee.proValid").value = "0";
 }
 else{
 	$("profee.proValid").value = "1";
 }

//正式期策略设置
if($("forValid").checked){
	if($("profee.order").value==""){
 	alert("请输入扣费指令！");
 	return;
 }
 
 if($("profee.forFee").value=="" || $("profee.forFee").value=="0"){
 	alert("请输入扣费费率，正式期费率不能为0！");
 	return;
 }
  $("profee.forValid").value = "0";
}
else{
$("profee.forValid").value = "1";
}

if(!$("forValid").checked && !$("proValid").checked){
	alert("请勾选试用期策略或试用期策略！");
 	return;
}

$("proAdd").submit();
}

function initCheckBox(){
	var forValid = document.getElementById("profee.forValid");
	var proValid = document.getElementById("profee.proValid");
	if(forValid.value == '0'){
	document.getElementById("forValid").checked = true;
	}
	if(proValid.value == '0'){
	document.getElementById("proValid").checked = true;
	}

}

</script>

</head>

<body onload="initCheckBox();">

<div class="top">
<div class="topright"></div>
<div class="topleft"></div>
<img src="../images/icon/shuju.gif"/><span class="title">SP产品接入管理 - 修改产品</span>
</div>
<div class="tmiddle">
&nbsp;
<div class="boxsearch">
  <form id="proAdd" name="proAdd" method="post" action="proModify.action?flag=1">
  <input type='hidden' name="pro.pro_id" value="${pro.pro_id}"/>
  <div class="add">
  <ul>
  	<li>
	    <span>选择SP：</span>
	    	<select name='pro.sp_id'>
	    	<#list splist as sl>
	    		<option value='${sl.sp_id?if_exists}'<#if pro.sp_id==sl.sp_id> selected</#if>>
	    		[${sl.sp_code?if_exists}]${sl.sp_name?if_exists}</option>
	    	</#list>
	    	</select>
    </li>
    <li>
    	<span>产品名：</span><input type="text" maxlength="20" name="pro.pro_name" id="name" value='${pro.pro_name?if_exists}' size="30"/>
    </li>
    <li>
    	<span>ISMP ID：</span><input type='text' maxlength="" name="pro.ismp_id" value='${pro.ismp_id?if_exists}' size="30"/>（对应软件CLASSID值）</li>
 	<li>
  		<span>产品注册ID：</span><input type='text' maxlength="" name="pro.registerId"  value='${pro.registerId?if_exists}' size="30"/></li>
  	<li>
    	<span>产品描述：</span><input type="text" maxlength="100" name="pro.pro_desc"  value='${pro.pro_desc?if_exists}' size="30"/>
    </li>
    <li>
    	<span>精品推荐：</span>
      	<#assign vouchlist=["<font color='green'>推荐</font>","<font color='blue'>非推荐</font>"] />
      	<#assign i=0 />
	        <#list vouchlist as vl>
	 			<input type="radio" class='notext' name="pro.proIsVouch" value="${i}" <#if i == pro.proIsVouch>checked</#if>/>${vl}
			<#assign i=i+1>
			</#list> 
    </li>
    <li>
    	<span>推荐类型：</span>
      	<select name='pro.proVouchType'>
      		<option value='1' <#if pro.proVouchType == 1>selected</#if>>最热</option>
      		<option value='0' <#if pro.proVouchType == 0>selected</#if>>最新</option>
      	</select>
    </li>
     <li>
     	<span>产品开发者：</span><input type="text" maxlength="100" name="pro.proDeveloper"  value='${pro.proDeveloper?if_exists}' size="30"/>
    </li>
    <li>
    	<span>产品提供者：</span><input type="text" maxlength="100" name="pro.proSupplier"  value='${pro.proSupplier?if_exists}' size="30"/>
    </li>
    <li>
    	<span>产品类型：</span><select name="pro.ptId">
    <#list proTypeList as pl><option value="${pl.id}" <#if pro.ptId==pl.id> selected</#if>>${pl.name}</option></#list></select>
    </li>
    <li><span>父产品：</span><select name="pro.pro_parent_id"><option value="0">--无--</option>
    <#list prolist as pl><#if pl.pro_id!=pro.pro_id><option value="${pl.pro_id}" <#if pro.pro_parent_id==pl.pro_id> selected</#if>>${pl.pro_name}</option></#if></#list></select>
    </li>
    <li>
    <span>试用期策略<input type="checkbox" name="proValid" id="proValid" value="0"></span>
    </li>
    <li>
    <span>试用天数：</span><input type="text" maxlength="20" id="profee.days" name="profee.days" value='${profee.days?if_exists}' size="30" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>天
    </li>
     <li>
    <span>试用次数：</span><input type="text" maxlength="20" id="profee.times" name="profee.times" value='${profee.times?if_exists}' size="30"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>次  （试用天数与试用次数同时设置为0则产品永久免费）
    </li>
     <li>
    <span>正式期策略<input type="checkbox" name="forValid" id="forValid" value="0"></span>
    </li>
      <li>
    <span>收费类型：</span><select name="profee.forType" id="type"> <#assign typelist=["包月","按次"] /><#assign i=1 />
    <#list typelist as tl><option value="${i}"<#if profee.forType==i> selected</#if>>${tl}</option><#assign i=i+1></#list>
    </select>
    </li>
     <li>
    <span>扣费指令：</span><input type="text" maxlength="20" name="profee.order" id="profee.order" value='${profee.order?if_exists}' size="30"/>
    </li>
      <li>
    <span>扣费费率：</span><input id='fee' type="text" maxlength="20" name="profee.forFee" id="profee.forFee" value='${profee.forFee?if_exists}' onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"  size="30"/>分
    </li>
    <li><span>状态：</span> 
      <#assign typelist=["<font color='green'>开通</font>","<font color='blue'>申请</font>","<font color='red'>暂停</font>","预注销","<font color='grey'>注销</font>"] /><#assign i=0 />
    <#list typelist as tl>
 	<input type="radio" class='notext' name="pro.pro_status" value="${i}" <#if i==pro.pro_status>checked</#if>/>${tl}
		<#assign i=i+1></#list> 
    </li>
    <li>
    <span>备注：</span>
    <textarea type="text" id="pro.pro_remark" name="pro.pro_remark" rows="2" cols="40"
		style="border:1px solid #7F9DB9;
				background-color:#FFF;
				color:#363636;
				font-size:12px;
				padding-left:3px;">${pro.pro_remark?if_exists}</textarea>
    </li>
    
    <li>
    <span>标签：</span>
    <input type="text" id="pro.proTag" name="pro.proTag" size="100" value="${pro.proTag?if_exists}"/>
    </li>
  </ul>
</div>
      <div class="grayline"></div>
      <div class="boxsearch2"><a href="javascript:dosubmit()"><img src="../images/buttom/buttom_tijiao.gif" alt="提交" /></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:window.history.back()'><img src="../images/buttom/buttom_fanhui.gif" alt="返回"/></a></div>
   	  <input type="hidden" id="profee.proValid" name="profee.proValid" value="${profee.proValid}"/>
  	  <input type="hidden" id="profee.forValid" name="profee.forValid" value="${profee.forValid}"/>
  	  <input type="hidden" id="profee.id" name="profee.id" value="${profee.id}"/>
  	  <input type="hidden" id="profee.proId" name="profee.proId" value="${profee.proId}"/>
  </form>
</div>
</div>
<div class="bottom">
<div class="bottomright"></div>
<div class="bottomleft"></div>
</div>
</body>
</html>