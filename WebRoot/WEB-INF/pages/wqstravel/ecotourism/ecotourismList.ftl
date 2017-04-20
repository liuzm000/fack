<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<#assign rootPath=request.scheme+"://"+request.serverName+":"+request.serverPort+request.contextPath />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>简介</title>
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../javascript/jquery-core/jquery-1.3.js"></script>
<script type="text/javascript" src="../javascript/jquery-core/jquery.blockUI.js"></script>
<script type="text/javascript">


	function del(obj,typeId){
		if(confirm("你确定要删除这条记录吗？")){
			document.forms["searchForm"].action = "doEcotourismDelete.action?id=" + obj+"&typeId="+typeId;
			document.forms["searchForm"].submit();
		}
	}
	
	function edit(obj,typeId){
		document.forms["searchForm"].action = "toEcotourismEdit.action?id=" + obj+"&typeId="+typeId;
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
	<img src="../images/icon/shuju.gif" /><span class="title">生态旅游 - 列表</span>
</div>
<!-- TOP END -->

<!-- MIDDLE -->
<div class="tmiddle">&nbsp;
	<div class="boxsearch">
		<div class="boxsearch2">
			<form id="searchForm" name="searchForm" method="post" action="getEcotourismTypeList.action">
			<input type="hidden" name="searchName" value="${searchName?if_exists}"/>
			<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" class="">
				<tr>
					<td colspan="8">
					&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:window.history.back();'><img src="../images/buttom/buttom_fanhui.gif" alt="返回"/></a>
					</td>
				</tr>
			</table>
			</form>
		</div>
	</div>
	<div class="tablebox">
		<form id="detailForm" name="detailForm" method="post" action=""></form>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab">
			<tr>
				<th width="5%">ID</th>
				<th width="10%">图片</th>
				<th width="15%">图片标题</th>
				<th width="15%">创建日期</th>
				<th width="25%">操作</th>
			</tr>
			<#if pageList?exists>
				<#list pageList as page>
				<tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
					<td align="center">${page.id?if_exists}</td>
					<td align="center">
						<img src="${rootPath}/download${page.filePath?if_exists}" width="48" height="48"/>
					</td>
					<td align="center">${page.descript?if_exists}</td>
					<td align="center">${page.createTime?if_exists}</td>
					<td align="center">
					<a href="javascript:edit('${page.id?if_exists}','${typeId?if_exists}')"><font color="blue"><u>修改</u></font></a>
					<a href="javascript:del('${page.id?if_exists}','${typeId?if_exists}')"><font color="red"><u>删除</u></font></a>
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
							<@pg.param name="typeId"  value="${typeId?if_exists}"/>
							<@pg.param name="searchName"  value="${searchName?if_exists}"/>
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
