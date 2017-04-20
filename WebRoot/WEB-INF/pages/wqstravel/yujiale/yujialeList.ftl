<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<#assign rootPath=request.scheme+"://"+request.serverName+":"+request.serverPort+request.contextPath />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>渔家乐简介信息管理</title>
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../javascript/jquery-core/jquery-1.3.js"></script>
<script type="text/javascript" src="../javascript/jquery-core/jquery.blockUI.js"></script>
<script type="text/javascript">

	function edit(obj){
		document.forms["searchForm"].action = "toEditYuJiaLe.action?id=" + obj;
		document.forms["searchForm"].submit();
	}
</script>

<style type="text/css">

</style>

</head>

<body>
<!-- TOP -->
<div class="top">
	<div class="topright"></div>
	<div class="topleft"></div>
	<img src="../images/icon/shuju.gif" /><span class="title">${title?if_exists}渔家乐简介信息</span>
</div>
<!-- TOP END -->

<!-- MIDDLE -->
<div class="tmiddle">&nbsp;
	<div class="boxsearch">
		<div class="boxsearch2">
			<form id="searchForm" name="searchForm" method="post" action="searchRssItem.action">
			</form>
		</div>
	</div>
	<div class="tablebox">
		<form id="detailForm" name="detailForm" method="post" action=""></form>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab">
			<tr>
				<th width="5%">ID</th>
				<th width="10%">名称</th>
				<th width="30%">渔家乐图集</th>
				<th width="30%">详细介绍</th>
				<th width="15%">操作</th>
			</tr>
			<#if pageList?exists>
				<#list pageList as page>
				<tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
					<td align="center">${page.id?if_exists}</td>
					<td align="center">${page.name?if_exists}</td>
					<td align="center">
						<#if page.icons?exists>
							<#list page.icons?split(",") as s>
								<img src="${rootPath}/download${s}" width="48" height="48"/>
							</#list>
						</#if>
					</td>
					<td align="center">
						<#if page.desc?exists>
							<a href="${rootPath}/download${page.desc?if_exists}" target="_blank"><font color="blue"><u>查看</u></font></a>
						</#if>
					</td>
					<td align="center">
					<a href="javascript:edit('${page.id?if_exists}')"><font color="blue"><u>修改</u></font></a>
					</td>
				</tr>
				</#list>
			</#if>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="box_fanye">
			<tr>
				<td>
				<!--分页-->
				<#if pageList?exists>
				<#if pageList.recordCount?exists>
					<#assign recordCount=pageList.recordCount />
					<#assign lastPage=pageList.lastPage />
					<#assign maxindexpages=recordCount/pageSize+1 />
					<#if (maxindexpages>10)>
						<#assign maxindexpages=10 />
					</#if>
					<@pg.pager isOffset=true encoder="utf-8" maxPageItems=pageSize maxIndexPages=maxindexpages items=recordCount url="${actionName?if_exists}" export="offset,currentPageNumber=pageNumber" scope = "request">
						<@pg.index>
							<#include "/WEB-INF/pages/common/jsp_tags.ftl"/>
						</@pg.index>
					</@pg.pager>
				</td>
				</#if>
				</#if>
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
