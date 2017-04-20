<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<#assign rootPath=request.scheme+"://"+request.serverName+":"+request.serverPort+request.contextPath />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>预定服务</title>
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../javascript/jquery-core/jquery-1.3.js"></script>
<script type="text/javascript" src="../javascript/jquery-core/jquery.blockUI.js"></script>
<script type="text/javascript">
	function del(obj,obj1){
		alert("暂未开放...");
		return;
		if(confirm("你确定要删除这条记录吗？")){
			document.forms["searchForm"].action = "doTownEntDelete.action?id=" + obj+"&url="+obj1;
			document.forms["searchForm"].submit();
		}
	}
	
	function search(){
		document.forms["searchForm"].action = "getFoodOrderList.action";
		document.forms["searchForm"].submit();
	}
</script>

<style type="text/css">
	.wrap {word-break:break-all}
</style>

</head>

<body>
<!-- TOP -->
<div class="top">
	<div class="topright"></div>
	<div class="topleft"></div>
	<img src="../images/icon/shuju.gif" /><span class="title">餐饮订单 - 列表</span>
</div>
<!-- TOP END -->

<!-- MIDDLE -->
<div class="tmiddle">&nbsp;
	<div class="boxsearch">
		<div class="boxsearch2">
			<form id="searchForm" name="searchForm" method="post" action="getFoodOrderList.action">
			<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" class="">
				<tr>
					<td width="10%" align="right">餐馆名称：</td>
					<td width="10%" align="left">
					<input id="searchName" name="searchName" value="${searchName?if_exists}" maxlength="50" size="15" type="text" />
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
				<th width="10%">餐饮店名称</th>
				<th width="10%">联系人</th>
				<th width="10%">性别</th>
				<th width="10%">联系电话</th>
				<th width="10%">就餐时间</th>
				<th width="10%">位置</th>
				<th width="10%">数量</th>
				<th width="10%">状态</th>
				<th width="10%">操作</th>
			</tr>
			<#if pageList?exists>
			<#list pageList as page>
			<tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
				<td align="center">${page.id?if_exists}</td>
				<td align="center" class="wrap">${page.restName?if_exists}</td>
				<td align="center">${page.name?if_exists}</td>
				<td align="left">
				<#if page.gender?if_exists != ''>
					<#if page.gender?if_exists == "M">
					男
					<#else>
					女
					</#if>
					</#if>
				</td>
				<td align="center">${page.phoneNumber?if_exists}</td>
				<td align="center">${page.restTime?if_exists?date}</td>
				<td align="center">${page.place?if_exists}</td>
				<td align="center">${page.num?if_exists}</td>
				<td align="center">
					<#if page.isValid?if_exists == '0'>
					<font color="brown">过期</font>
					<#elseif page.isValid?if_exists == '1'>
					<font color="green"><b>有效</b></font>
					<#else>
					<font color="grey">取消</font>
					</#if>
				</td>
				<td align="center">
				<#--<a href="javascript:del('${page.id?if_exists}','${page.htmlPath?if_exists}')"><font color="red"><u>删除</u></font></a>-->
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
