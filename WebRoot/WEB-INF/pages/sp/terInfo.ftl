<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>机型设备管理</title>
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function $(id)
{
return document.getElementById(id);
}
function dodel(id){
if (confirm('你确定要删除此机型设备吗？')){
 $("query").action+="?action=del&id="+id;
 $("query").submit();
}
return;
}
</script>
<style type="text/css">
.listTab td{
white-space:nowrap;
}
</style>
</head>

<body>

<div class="top">
<div class="topright"></div>
<div class="topleft"></div>
<img src="../images/icon/shuju.gif"/><span class="title">机型设备管理</span>
</div>
<div class="tmiddle">
&nbsp;
<div class="boxsearch">
	<div class="boxsearch2">
  <form id="query" name="query" method="post" action="terInfo.action">
  机型名称：<input type="text" name="name" id="name" value="${name?if_exists}" />
  &nbsp;&nbsp;&nbsp;
   描述：
      <input type="text" name="description" id="description" value="${description?if_exists}" />
  &nbsp;&nbsp;&nbsp;
   厂商：
   <select name='fac_id'>
   <option value='0'>--所有类型--</option>
   <#list faclist as fl>
   <option value='${fl.fac_id?if_exists}'<#if fac_id?exists && fac_id==fl.fac_id>selected</#if>>${fl.fac_name?if_exists}</option></#list>
   </select>
        <div class="grayline"></div>
       <a href="javascript:$('query').submit();" ><img alt="查询" src="../images/buttom/buttom_search.gif"/></a>&nbsp;&nbsp;<a href="terAdd.action"><img alt="新增" src="../images/buttom/buttom_xinzen.gif"/></a>
  </form>
</div>
</div>
<div class="tablebox"><#if reslist?exists>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab">
        <tr>
          <th>序号</th>
          <th>名称</th>
          <th>终端ID</th>
          <th>描述</th>
          <th>厂商</th>
          <th>平台</th>
          <th>操作</th>
        </tr>
        <#list reslist as rl>
        <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
          <td><div align="center">${rl_index + 1 + (pageNum-1) * pageSize}</div></td>
          <td align="center">${rl.ter_name?if_exists}</td>
          <td align="center">${rl.ter_id?if_exists}</td>
          <td align="center">${rl.ter_des?if_exists}</td>
          <td align="center">${rl.fac_name?if_exists}</td>
          <td align="center">${rl.ter_plat?if_exists}</td>
          <td align="center">
          <a href="terModify.action?id=${rl.ter_id?if_exists}" class="redfontvha">修改</a>  | <a href="javascript:dodel(${rl.ter_id?if_exists})" class="redfontvha">删除</a></td>
     </tr>
        </#list>
        
      </table>
     <table width="100%" border="0" cellpadding="0" cellspacing="0" class="box_fanye">
        <tr><td>
         	 <!--分页-->
			<#if reslist.recordCount?exists>
				<#assign recordCount=reslist.recordCount />
			    <#assign maxindexpages=recordCount/pageSize+1 />
			    <#assign lastPage=reslist.lastPage />
			    <#if (maxindexpages>10)>
			        <#assign maxindexpages=10 />
			    </#if>
				<@pg.pager isOffset=true encoder="utf-8" maxPageItems=pageSize maxIndexPages=maxindexpages items=recordCount url="terInfo.action" export="offset,currentPageNumber=pageNumber" scope = "request">
				<@pg.index>
				<@pg.param name="description"  value="${description?if_exists}"/>
				<@pg.param name="name" value="${name?if_exists}"/>		
				<@pg.param name="factory" value="${name?if_exists}"/>		 
				 <#include "/WEB-INF/pages/common/jsp_tags.ftl"/> 
				</@pg.index>
			    </@pg.pager>

		</td> </tr>
        </#if>
      </table></#if>
  </div>
</div>
<div class="bottom">
<div class="bottomright"></div>
<div class="bottomleft"></div>
</div></body>
</html>
