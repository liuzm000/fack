<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<#assign rootPath=request.scheme+"://"+request.serverName+":"+request.serverPort+request.contextPath />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>热门搜索词</title>
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../javascript/jquery-core/jquery-1.3.js"></script>
<script type="text/javascript" src="../javascript/jquery-core/jquery.blockUI.js"></script>
<script type="text/javascript">
	function checkAddForm(){
		document.forms["searchForm"].action = "addHotKeyword.action";
		document.forms["searchForm"].submit();
	}

	function editHotKeyword(obj){
		document.forms["searchForm"].action = "editHotKeyword.action?hkId=" + obj;
		document.forms["searchForm"].submit();
	}
	
	function deleteHotKeyword(obj){
		var str = "确定删除该条信息?";
		if(confirm(str)){
			document.forms["detailForm"].action = "deleteHotKeyword.action?hkId=" + obj;
			document.forms["detailForm"].submit();
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
	<img src="../images/icon/shuju.gif" /><span class="title">热门搜索词 - 列表</span>
</div>
<!-- TOP END -->

<!-- MIDDLE -->
<div class="tmiddle">&nbsp;
	<div class="boxsearch">
		<div class="boxsearch2">
			<form id="searchForm" name="searchForm" method="post" action="searchRssItem.action">
				<tr>
					<td colspan="10">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="10">
					<!--<a href="javascript:checkSearchForm()"><img src="../images/buttom/buttom_search.gif" /></a>-->
					<a href="javascript:checkAddForm()"><img src="../images/buttom/buttom_xinzen.gif" /></a>
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
				<th width="20%">搜索词</th>
				<th width="15%">搜索次数</th>
				<th width="20%">词汇描述</th>
				<th width="20%">发布时间</th>
				<th width="15%">操作</th>
			</tr>
			<#if hotkeyList?exists>
			<#list hotkeyList as al>
			<tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
				<td align="center">${al.hkId?if_exists}</td>
				<td align="center"><font color="blue">${al.keyword?if_exists}</td>
				<td align="center">${al.searchCount?if_exists}</td>
				<td align="center">${al.keyDesc?if_exists}</td>
				<td align="center">${al.createTime?date}</td>
				<td align="center">
				<a href="javascript:editHotKeyword('${al.hkId?if_exists}')"><font color="blue"><u>修改</u></font></a>
				<a href="javascript:deleteHotKeyword('${al.hkId?if_exists}')"><font color="red"><u>删除</u></font></a>
				</td>
			</tr>
			</#list>
			</#if>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="box_fanye">
			<tr>
				<td>
				<!--分页-->
				<#if hotkeyList?exists>
				<#if hotkeyList.recordCount?exists>
					<#assign recordCount=hotkeyList.recordCount />
					<#assign lastPage=hotkeyList.lastPage />
					<#assign maxindexpages=recordCount/pageSize+1 />
					<#if (maxindexpages>10)>
						<#assign maxindexpages=10 />
					</#if>
					<@pg.pager isOffset=true encoder="utf-8" maxPageItems=pageSize maxIndexPages=maxindexpages items=recordCount url="${actionName?if_exists}" export="offset,currentPageNumber=pageNumber" scope = "request">
						<@pg.index>
							<@pg.param name="hrModel.subjectName"  value="${hrModel?if_exists.subjectName?if_exists}"/>
							<@pg.param name="hrModel.subjectType" value="${hrModel?if_exists.subjectType?if_exists}"/>
							<@pg.param name="hrModel.appTypeId" value="${hrModel?if_exists.appTypeId?if_exists}"/>
							<@pg.param name="hrModel.subjectId" value="${hrModel?if_exists.subjectId?if_exists}"/>
							<@pg.param name="hrModel.subjectWapUrl" value="${hrModel?if_exists.subjectWapUrl?if_exists}"/>		
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
