<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>设备厂商管理 - 添加设备厂商</title>
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
	alert('请输入设备厂商名称！');
	$('name').focus();
	return;
}
if (!fucCheckTEL($('phone').value)){
	alert('电话号码输入不正确！');
	$('phone').focus();
	return;
}
$("facAdd").submit();
}
//函数名：fucCheckTEL    
//功能介绍：检查是否为电话号码    
//参数说明：要检查的字符串    
//返回值：1为是合法，0为不合法    
function fucCheckTEL(TEL)    
{    
var i,j,strTemp;    
strTemp="0123456789-()# ";    
for (i=0;i<TEL.length;i++)    
{    
j=strTemp.indexOf(TEL.charAt(i));    
if (j==-1)    
{    
//说明有字符不合法    
return 0;    
}    
}    
//说明合法    
return 1;    
}

</script>

</head>

<body>

<div class="top">
<div class="topright"></div>
<div class="topleft"></div>
<img src="../images/icon/shuju.gif"/><span class="title">设备厂商管理 - 添加设备厂商</span>
</div>
<div class="tmiddle">
&nbsp;
<div class="boxsearch">
  <form id="facAdd" name="facAdd" method="post" action="facAdd.action?flag=1">
  <div class="add">
  <ul>
  <li>
    <span>*设备厂商名称：</span><input type="text" maxlength="100" name="fac.fac_name" id="name" />
    </li>
  <li>
    <span>描述：</span><input type="text" maxlength="100" name="fac.fac_desc" />
    </li>
      <li>
    <span>地址：</span><input type="text" maxlength="50" name="fac.fac_addr" />
    </li>
      <li>
    <span>联系电话：</span><input type="text" maxlength="15" name="fac.fac_phone" id="phone" /><font color=gray>（示例：020-12345678或18912345678，可不填）</font>
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