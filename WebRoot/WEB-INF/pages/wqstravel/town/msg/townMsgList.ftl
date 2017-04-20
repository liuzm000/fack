<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<#assign rootPath=request.scheme+"://"+request.serverName+":"+request.serverPort+request.contextPath />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>名镇信息</title>
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../javascript/jquery-core/jquery-1.3.js"></script>
<script type="text/javascript" src="../javascript/jquery-core/jquery.blockUI.js"></script>
<script type="text/javascript">
	function search(){
		document.forms["searchForm"].action = "getTownMsgQuery.action";
		document.forms["searchForm"].submit();
	}

	function add(){
		document.forms["searchForm"].action = "doTownMsgAdd.action";
		document.forms["searchForm"].submit();
		
	}

	function edit(obj){
		document.forms["searchForm"].action = "doTownMsgEdit.action?id=" + obj;
		document.forms["searchForm"].submit();
	}
	
	function del(obj,obj1){
		if(confirm("你确定要删除这条记录吗？")){
			document.forms["searchForm"].action = "doTownMsgDelete.action?id=" + obj+"&url="+obj1;
			document.forms["searchForm"].submit();
		}
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
	<img src="../images/icon/shuju.gif" /><span class="title">名镇信息 - 列表</span>
</div>
<!-- TOP END -->

<!-- MIDDLE -->
<div class="tmiddle">&nbsp;
	<div class="boxsearch">
		<div class="boxsearch2">
			<form id="searchForm" name="searchForm" method="post" action="searchRssItem.action">
			<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" class="">
				<tr>
					<td colspan="8">&nbsp;</td>
				</tr>
				<tr>
					<td width="10%" align="right">名称：</td>
					<td width="10%" align="left">
					<input id="msgModel.title" name="msgModel.title" value="${model?if_exists.title?if_exists}" maxlength="50" size="15" type="text" value=""/>
					</td>
					<td width="10%" align="right">&nbsp;</td>
					<td width="10%" align="left">&nbsp;</td>
					<td width="20%" align="left">&nbsp;</td>
					<td width="10%" align="left">&nbsp;</td>
					<td width="20%" align="left">&nbsp;</td>
					<td width="10%" align="left">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="8">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="8">
					<a href="javascript:search()"><img src="../images/buttom/buttom_search.gif" /></a>
					<a href="javascript:add()"><img src="../images/buttom/buttom_xinzen.gif" /></a>
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
				<th width="10%">ID</th>
				<th width="20%">主题名</th>
				<th width="15%">单位</th>
				<th width="15%">HTML页面</th>
				<th width="20%">发布时间</th>
				<th width="15%">操作</th>
			</tr>
			<#if pageList?exists>
			<#list pageList as page>
			<tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
				<td align="center">${page.id?if_exists}</td>
				<td align="center">${page.title?if_exists}</td>
				<td align="center">${page.unit?if_exists}</td>
				<td align="center"><a style="cursor: pointer;" href="${rootPath}/download${page.fileUrl?if_exists}"><font color="blue">查看</font></a></td>
				<td align="center">${page.createTime?if_exists}</td>
				<td align="center">
				<a href="javascript:edit('${page.id?if_exists}')"><font color="blue"><u>修改</u></font></a>
				<a href="javascript:del('${page.id?if_exists}','${page.fileUrl?if_exists}')"><font color="red"><u>删除</u></font></a>
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
							<@pg.param name="msgModel.title"  value="${model?if_exists.title?if_exists}"/>
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
