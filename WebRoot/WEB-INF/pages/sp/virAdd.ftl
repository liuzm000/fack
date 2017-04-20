<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>虚拟类型管理 - 添加虚拟类型</title>
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
	alert('请输入虚拟类型名称！');
	$('name').focus();
	return;
}

$("virAdd").submit();
}


</script>

</head>

<body>

<div class="top">
<div class="topright"></div>
<div class="topleft"></div>
<img src="../images/icon/shuju.gif"/><span class="title">虚拟类型管理 - 添加虚拟类型</span>
</div>
<div class="tmiddle">
&nbsp;
<div class="boxsearch">
  <form id="virAdd" name="virAdd" method="post" action="virAdd.action?flag=1">
  <div class="add">
  <ul>
  <li>
    <span>*虚拟类型名称：</span><input type="text" maxlength="20" name="vir.vir_name" id="name" />
    </li>
  <li>
    <span>虚拟类型描述：</span><input type="text" maxlength="20" name="vir.vir_desc" />
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