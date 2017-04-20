<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>机型设备管理 - 修改机型设备</title>
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
	alert('请输入机型设备名称！');
	$('name').focus();
	return;
}
if ($('code').value==''){
	alert('请输入机型编码！');
	$('code').focus();
	return;
}
if ($('des').value.length>100){
	alert('描述不能超过100字符！');
	$('des').focus();
	return;
}

$("terAdd").submit();
}
</script>

</head>

<body>

<div class="top">
<div class="topright"></div>
<div class="topleft"></div>
<img src="../images/icon/shuju.gif"/><span class="title">机型设备管理 - 修改机型设备</span>
</div>
<div class="tmiddle">
&nbsp;
<div class="boxsearch">
  <form id="terAdd" name="terAdd" method="post" action="terModify.action?flag=1" enctype="multipart/form-data">
  <input type='hidden' name='ter.ter_id' value='${ter.ter_id?if_exists}'/>
  <div class="add">
  <ul>
  <li>
    <span>机型ID：</span>${ter.ter_id?if_exists}
   </li>
  <li>
    <span>机型名称：</span><input value='${ter.ter_name?if_exists}' type="text" name="ter.ter_name" id="name" maxlength="20"/>
    </li>
  <li>
    <span>终端编码：</span><input value='${ter.ter_code?if_exists}' type="text" name="ter.ter_code" id="code" maxlength="30"/>
    </li>
    <li><span>厂商：</span><select name='ter.fac_id'><#list faclist as fl>
   <option value='${fl.fac_id?if_exists}' <#if ter.fac_id?exists && ter.fac_id==fl.fac_id> selected</#if>>${fl.fac_name?if_exists}</option></#list>
   </select>
   </li>
   <li><span>Java支持：</span>
    <input type="radio" class='notext' name="ter.ter_java" value="0" checked />不支持
    <input type="radio" class='notext' name="ter.ter_java" value="1"<#if ter.ter_java?exists && ter.ter_java==1> checked</#if>/>支持
   </li>
   <li><span>彩信支持：</span>
    <input type="radio" class='notext' name="ter.ter_mms" value="0" checked />不支持
    <input type="radio" class='notext' name="ter.ter_mms" value="1"<#if ter.ter_mms?exists && ter.ter_mms==1> checked</#if>/>支持
   </li>
      <li><span>WAP支持：</span>
    <input type="radio" class='notext' name="ter.ter_wap" value="0" checked />不支持
    <input type="radio" class='notext' name="ter.ter_wap" value="1"<#if ter.ter_wap?exists && ter.ter_wap==1> checked</#if>/>支持
   </li>
        <li><span>EVDO支持：</span>
    <input type="radio" class='notext' name="ter.ter_evdo" value="0" checked />不支持
    <input type="radio" class='notext' name="ter.ter_evdo" value="1"<#if ter.ter_evdo?exists && ter.ter_evdo==1> checked</#if>/>支持
   </li>
     <li><span>平台：</span>
    <select name='ter.ter_plat'>
    <option value='mobile'>mobile</option>
    <option value='wince'<#if ter.ter_plat?exists && ter.ter_plat=="wince"> selected</#if>>wince</option>
    <option value='brew'<#if ter.ter_plat?exists && ter.ter_plat=="brew"> selected</#if>>brew</option>
    <option value='android'<#if ter.ter_plat?exists && ter.ter_plat=="android"> selected</#if>>android</option>
    <option value='blackberry'<#if ter.ter_plat?exists && ter.ter_plat=="blackberry"> selected</#if>>blackberry</option>
    <option value='palm'<#if ter.ter_plat?exists && ter.ter_plat=="palm"> selected</#if>>palm</option>
    <option value='mtk'<#if ter.ter_plat?exists && ter.ter_plat=="mtk"> selected</#if>>mtk</option>
    <option value='ios'<#if ter.ter_plat?exists && ter.ter_plat=="ios"> selected</#if>>ios</option>
    </select>
   </li>
      <li><span>分辨率：</span><select name='ter.vir_id'><#list virlist as vl>
   <option value='${vl.vir_id?if_exists}'<#if ter.vir_id?exists && ter.vir_id==vl.vir_id> selected</#if>>${vl.vir_name?if_exists}</option></#list>
   </select>
   </li>
   <li><span>图片：</span><a style='text-decoration: underline;' target='_blank' href="${request.contextPath}/uploadpic/${ter.ter_picpath?if_exists}">点击查看原图</a>
   </li>
   <li style='height:auto'><span>&nbsp;</span><img src='${request.contextPath}/uploadpic/${ter.ter_picpath?if_exists}' width="180" height="200" />
   </li>
   <li><span>描述：</span><textarea name="ter.ter_des" id='des'>${ter.ter_des?if_exists}</textarea>
   </li>
  </ul>
</div>
      <div class="grayline"></div>
      <div class="boxsearch2"><a href="javascript:dosubmit()"><img src="../images/buttom/buttom_tijiao.gif" alt="提交" /></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:window.history.back()'><img src="../images/buttom/buttom_fanhui.gif" alt="返回"/></a></div>
  </form>
</div>
</div>
<div class="bottom">
<div class="bottomright"></div>
<div class="bottomleft"></div>
</div>
</body>
</html>