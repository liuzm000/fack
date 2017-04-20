<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<#assign rootPath=request.scheme+"://"+request.serverName+":"+request.serverPort+request.contextPath />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>首页天翼汇列表</title>
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../javascript/jquery-core/jquery-1.3.js"></script>
<script type="text/javascript" src="../javascript/jquery-core/jquery.blockUI.js"></script>
<script type="text/javascript">
	function checkSearchForm(){
		document.forms["searchForm"].action = "listHomeRecommend.action";
		document.forms["searchForm"].submit();
	}

	function checkAddForm(){
		document.forms["searchForm"].action = "addHomeRecommend.action";
		document.forms["searchForm"].submit();
	}

	function editHomeRecommend(obj){
		document.forms["searchForm"].action = "editHomeRecommend.action?hrId=" + obj;
		document.forms["searchForm"].submit();
	}
	
	function deleteHomeRecommend(obj){
		var str = "确定删除该条信息?";
		if(confirm(str)){
			document.forms["detailForm"].action = "deleteHomeRecommend.action?hrId=" + obj;
			document.forms["detailForm"].submit();
		}
	}
	
	function matchHomeRecommend(obj){
		document.forms["searchForm"].action = "matchHomeRecommend.action?hrId=" + obj;
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
	<img src="../images/icon/shuju.gif" /><span class="title">首页推荐位(3.3版)信息 - 列表</span>
</div>
<!-- TOP END -->

<!-- MIDDLE -->
<div class="tmiddle">&nbsp;
	<div class="boxsearch">
		<div class="boxsearch2">
			<form id="searchForm" name="searchForm" method="post" action="searchRssItem.action">
			<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" class="">
				<tr>
					<td colspan="10">&nbsp;</td>
				</tr>
				<tr>
					<td width="10%" align="right">主题名称：</td>
					<td width="10%" align="left">
					<input id="hrModel.subjectName" name="hrModel.subjectName"  maxlength="50" size="15" type="text" value=""/>
					</td>
					<td width="10%" align="right">主题类型：</td>
					<td width="10%" align="left">
					<select name="hrModel.subjectType" id="hrModel.subjectType" style="width:15">
					    <option value="-2">--请选择--</option>
						<option value="0">无点击动作</option>
						<option value="1">客户端内嵌页</option>
						<option value="2">WAP页面</option>
						<option value="3">应用详情页</option>
						<option value="4">专题应用列表页</option>
					</select>
					</td>
					<td width="10%" align="right">分类：</td>
					<td width="10%" align="left">
					<select name="hrModel.appTypeId" id="hrModel.appTypeId" style="width:15">
					<option value="-2">--请选择--</option>
					<option value="1">最新</option>
					<option value="2">最热</option>
					<option value="3">金融导航</option>
					<!--<#if appTypeList?exists>
					<#list appTypeList as cl>
						<option value="${cl.appTypeId}">${cl.appTypeName}</option>
					</#list>
					</#if>-->
					</select>
					</td>
					<td width="10%" align="right">映射属性：</td>
					<td width="10%" align="left">
					<input id="hrModel.subjectId" name="hrModel.subjectId" type="text" value="" size="15"/>
					</td>
					<td width="10%" align="right">状态：</td>
					<td width="10%" align="left">
					<select name="hrModel.subjectWapUrl" id="hrModel.subjectWapUrl" style="width:15">
					    <option value="-2">--请选择--</option>
					    <option value="3">最新</option>
					    <option value="2">加精</option>
					    <option value="1">热门</option>
						<option value="0">无状态</option>
					</select>
					</td>
				</tr>
				<tr>
					<td colspan="10">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="10">
					<a href="javascript:checkSearchForm()"><img src="../images/buttom/buttom_search.gif" /></a>
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
				<th width="20%">&nbsp;</th>
				<th width="5%">ID</th>
				<th width="20%">主题名称</th>
				<th width="10%">主题类型</th>
				<th width="10%">分类</th>
				<th width="10%">映射属性</th>
				<th width="5%">状态</th>
				<th width="10%">发布时间</th>
				<th width="10%">操作</th>
			</tr>
			<#if recommendList?exists>
			<#list recommendList as al>
			<tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
				<td align="center">
				<#if al.appTypeId != -1>
				<img src="${rootPath}${al.subjectIconUrl?if_exists}" width="249" height="79"/>
				<#else>
				<img src="${rootPath}${al.subjectIconUrl?if_exists}" width="125" height="79"/>
				</#if>
				</td>
				<td align="center">${al.hrId?if_exists}</td>
				<td align="center"><font color="blue">${al.subjectName?if_exists}</td>
				<td align="center">
				<#if al.subjectType == 0>无点击动作</#if>
				<#if al.subjectType == 1>客户端内嵌页</#if>
				<#if al.subjectType == 2>WAP页面</#if>
				<#if al.subjectType == 3>应用详情页</#if>
				<#if al.subjectType == 4>专题应用列表</#if>
				</td>
				<td align="center">
				<#if appTypeList?exists>
					<#assign appTypeName=al.appTypeId?if_exists />
					<!--<#list appTypeList as atl>
					<#if atl.appTypeId == al.appTypeId>
						<#assign appTypeName = atl.appTypeName/>
					</#if>
					</#list>-->
					<#if al.appTypeId == 1>
						<#assign appTypeName = '最新'/>
					</#if>
					<#if al.appTypeId == 2>
						<#assign appTypeName = '最热'/>
					</#if>
					<#if al.appTypeId == 3>
						<#assign appTypeName = '金融导航'/>
					</#if>
				</#if>
				${appTypeName?if_exists}
				</td>
				<td align="center">${al.subjectId?if_exists}</td>
				<td align="center">
				<#if al.subjectWapUrl == 0></#if>
				<#if al.subjectWapUrl == 1><img src="../images/tykj/hot.png" width="25" height="25"/></#if>
				<#if al.subjectWapUrl == 2><img src="../images/tykj/highlight.png" width="25" height="25"/></#if>
				<#if al.subjectWapUrl == 3><img src="../images/tykj/ic_new.png" width="25" height="25"/></#if>
				</td>
				<td align="center">${al.createTime?date}</td>
				<td align="center">
				<a href="javascript:editHomeRecommend('${al.hrId?if_exists}')"><font color="blue"><u>修改</u></font></a>
				<a href="javascript:matchHomeRecommend('${al.hrId?if_exists}')"><font color="blue"><u>适配</u></font></a>
				<a href="javascript:deleteHomeRecommend('${al.hrId?if_exists}')"><font color="red"><u>删除</u></font></a>
				</td>
			</tr>
			</#list>
			</#if>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="box_fanye">
			<tr>
				<td>
				<!--分页-->
				<#if recommendList?exists>
				<#if recommendList.recordCount?exists>
					<#assign recordCount=recommendList.recordCount />
					<#assign lastPage=recommendList.lastPage />
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
