<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>策略类型列表</title>
<link href="${request.contextPath}/css/global.css" rel="stylesheet" type="text/css" />
<!-- import jquery -->
<script type="text/javascript" src="${request.contextPath}/version/js/jquery-core/jquery-1.3.js"></script>
</head>
<body>
<table width="100%" height="99%" border="0" cellspacing="0" cellpadding="0">
  <!-- 头部 -->
  <tr>
    <td width="3%"  height="36"  align="left" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="../images/main/all_t_bg.gif"><img src="../images/icon/baobiao.gif"><span class="title"><strong>优惠策略管理</strong> - 策略列表</span></td>
    <td width="3%"  height="36"  align="right" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_r.gif" ></td>
  </tr>
  <!-- 查询模块部分 -->
  <tr height="30">
  	<td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
  	<form id="query" name="query" method="post" action="searchTariffFav.action">
  	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="">
  		<tr>
  			<td colspan="6">&nbsp;</td>
  		</tr>
  		<tr>
  			<td width="20%">&nbsp;</td>
  			<td width="10%" align="right">策略名称：</td>
  			<td width="20%" align="left"><input id="tariffFavListCP.favName" name="tariffFavListCP.favName" type="text" value=""/></td>
  			<td width="10%" align="right">代号：</td>
  			<td width="20%" align="left"><input id="tariffFavListCP.favId" name="tariffFavListCP.favId" type="text" value=""/></td>
  			<td width="20%">&nbsp;</td>
  		</tr>
  		<tr>
  			<td colspan="6">&nbsp;</td>
  		</tr>
  		<tr>
  			<td colspan="6"><a href="javascript:document.query.submit();" ><img alt="查询" src="../images/buttom/buttom_search.gif"/></a>&nbsp;&nbsp;<a href="addTariffFav.action"><img alt="新增" src="../images/buttom/buttom_xinzen.gif"/></a></td>
  		</tr>
  		<tr>
  			<td colspan="6">&nbsp;</td>
  		</tr>
  	</table>
  	</form>
  	</td>
  </tr>
  <!-- 列表部分 -->
  <tr>
  	<td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
  	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab">
  		<tr>
           	<th colspan="7">资费类型列表</th>
        </tr>
        <tr>
          	<th>代号</th>
          	<th>策略名称</th>
          	<th>优惠内容</th>
          	<th>策略说明</th>
          	<th>状态</th>
          	<th>操作</th>
        </tr>
        <#list tariffFavList as ttl>
        <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
          <td align="center">${ttl.favId?if_exists}</td>
          <td align="center">${ttl.favName?if_exists}</td>
          <td align="center">
          <#if ttl.favTypeId == 0>
          		时间类优惠类
          <#elseif ttl.favTypeId == 1>
          		使用次数优惠类
          <#else>
          		资费优惠折扣类
          </#if>
          </td>
          <td align="center">${ttl.favContext?if_exists}</td>
          <td align="center">
          <#if ttl.state == 1>
          		正常
          <#else>
          		停用
          </#if>
          </td>
          <td align="center">
          <a href="modifyTariffFav.action?id=${ttl.favId?if_exists}" class="redfontvha">修改</a>  | <a href="javascript:dodel(${ttl.favId?if_exists})" class="redfontvha">删除</a></td>
     	</tr>
        </#list>
  	</table>
  	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="box_fanye">
        <tr>
        	<td>
         	 <!--分页-->
			<#if tariffFavList.recordCount?exists>
				<#assign recordCount = tariffFavList.recordCount />
				<#assign lastPage = tariffFavList.lastPage />
			    <#assign maxindexpages = recordCount/pageSize+1 />
			    <#if (maxindexpages > 10)>
			        <#assign maxindexpages = 10 />
			    </#if>
				<@pg.pager isOffset=true encoder="utf-8" maxPageItems=pageSize maxIndexPages=maxindexpages items=recordCount url="listTariffFav.action" export="offset,currentPageNumber=pageNumber" scope = "request">
				<@pg.index>
				 <#include "/WEB-INF/pages/common/jsp_tags.ftl"/> 
				</@pg.index>
			    </@pg.pager>
			</#if>
			</td>
		</tr>
      </table>
  	</td>
  </tr>
  <!-- 尾部 -->
  <tr>
    <td height="26" align="left"background="../images/main/all_d_bg.gif"><img src="../images/main/all_d_l.gif" ></td>
    <td height="26" background="../images/main/all_d_bg.gif">&nbsp;</td>
    <td height="26" align="right" background="../images/main/all_d_bg.gif"><img src="../images/main/all_d_r.gif" ></td>
  </tr>
</table>
</body>
</html>