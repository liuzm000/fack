<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>产品类型管理 - 修改产品类型</title>
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
	alert('请输入产品类型名称！');
	$('name').focus();
	return;
}

$("ptAdd").submit();
}


</script>

</head>

<body>

<div class="top">
<div class="topright"></div>
<div class="topleft"></div>
<img src="../images/icon/shuju.gif"/><span class="title">产品类型管理 - 修改产品类型</span>
</div>
<div class="tmiddle">
&nbsp;
<div class="boxsearch">
  <form id="ptAdd" name="ptAdd" method="post" action="ptModify.action?flag=1" enctype="multipart/form-data">
   <input type="hidden" name="protype.id" value="${protype.id?if_exists}"/>
  <div class="add">
  <ul>
  <li>
    <span>产品类型ID：</span>${protype.id?if_exists}
    </li>
  <li>
    <span>产品类型名称：</span><input type="text" maxlength="100" value="${protype.name?if_exists}" name="protype.name" id="name" />
    </li>
  <li>
    <span>产品类型描述：</span><input type="text" maxlength="100" value="${protype.desc?if_exists}" name="protype.desc" />
    </li>
<li>
    <span>类型图标上传：</span><input type="file" id="iconFile" name="iconFile" size="40" class="input">
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