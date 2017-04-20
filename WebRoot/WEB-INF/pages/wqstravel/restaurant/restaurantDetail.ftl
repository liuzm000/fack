<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<#assign rootPath=request.scheme+"://"+request.serverName+":"+request.serverPort+request.contextPath />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>餐馆信息管理</title>
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../javascript/jquery-core/jquery-1.3.js"></script>
<script type="text/javascript" src="../javascript/jquery-core/jquery.blockUI.js"></script>
<style type="text/css">

</style>

</head>

<body>
<!-- TOP -->
<div class="top">
	<div class="topright"></div>
	<div class="topleft"></div>
	<img src="../images/icon/shuju.gif" /><span class="title">餐馆详细信息</span>
</div>
<!-- TOP END -->

<!-- MIDDLE -->
<div class="tmiddle">&nbsp;
	<div class="tablebox">
		<form id="detailForm" name="detailForm" method="post" action=""></form>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab">
			<tr>
				<td align="right" width="15%">餐馆名称</td>
				<td align="center" width="85%">${hotel.name?if_exists}</td>
			</tr>
			<tr>
				<td align="right" width="15%">餐馆地址</td>
				<td align="center" width="85%">${hotel.addr?if_exists}</td>
			</tr>
			<tr>
				<td align="right" width="15%">联系电话</td>
				<td align="center" width="85%">${hotel.tele?if_exists}</td>
			</tr>
			<tr>
				<td align="right" width="15%">内容图片</td>
				<td align="left" width="85%">
					<#if iconList?exists>
						<#list iconList as icon>
							<img width="100px" height="100px" src="${rootPath}/download${icon.path}"/>					
						</#list>
					</#if>
				</td>
			</tr>
			<tr>
				<td align="right" width="15%">HTML页面</td>
				<td align="center" width="85%">
					<a style="cursor: pointer;" href="${rootPath}/download${hotel.htmlInfo?if_exists}"><font color="blue">查看</font></a>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<a href='javascript:window.history.back();'><img src="../images/buttom/buttom_fanhui.gif" alt="返回"/></a>
				</td>
			</tr>
		</table>
	</div><!-- tablebox DIV END -->
</div><!-- MIDDLE DIV END -->

<div class="bottom" align="center">
	<div class="bottomright"></div>
	<div class="bottomleft"></div>
</div>

</body>
</html>
