<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<script type='text/javascript'>
function redirect(){
<#if myurl?exists>
window.location.href='${myurl}';
<#else>
window.history.back();
</#if>
}
var i=3;
function secChange(){
 i--;
 if (i==0) redirect();
 document.getElementById('secnum').innerHTML=i;
}
window.setInterval("secChange();",1000);

</script>
</head>
<body>
<div class="top">
<div class="topright"></div>
<div class="topleft"></div>
<img src="../images/icon/shuju.gif"/><span class="title">操作已完成</span>
</div>
<div class="tmiddle">
&nbsp;
<div class="boxsearch">
	<div class="boxsearch2">
	<p style='font-size:16px'>${myinfo?if_exists}</p><p style='color:blue'><span style='color:red;font-weight:bold' id='secnum'>3</span> 秒钟后自动跳转，如果没有自动跳转，请点击“确定”按钮。</p>
	 <div class="grayline"></div>
	 <a href='javascript:redirect()'><img src='../images/buttom/buttom_queding.gif' alt="确定"/></a>
</div>
</div>
</div>
<div class="bottom">
<div class="bottomright"></div>
<div class="bottomleft"></div>
</div></body>
</html>


</body>
</html>