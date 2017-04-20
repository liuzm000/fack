<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>SP信息管理</title>
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function $(id)
{
return document.getElementById(id);
}
function dodel(id){
if (confirm('你确定要删除此SP吗？')){
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
<img src="../images/icon/shuju.gif"/><span class="title">SP信息管理</span>
</div>
<div class="tmiddle">
&nbsp;
<div class="boxsearch">
	<div class="boxsearch2">
  <form id="query" name="query" method="post" action="spInfo.action">
  SP名：<input type="text" name="name" id="name" value="${name?if_exists}" />
  &nbsp;&nbsp;&nbsp;
   描述：
      <input type="text" name="description" id="description" value="${description?if_exists}" />
  &nbsp;&nbsp;&nbsp;
    省份：
      <select name="prov_id"><option value="all">--不限--</option>
   <#list province as pro><option value="${pro.id?if_exists}" <#if prov_id?exists && pro.id==prov_id>selected</#if>>${pro.name?if_exists}</option></#list>
        </select>
        <div class="grayline"></div>
       <a href="javascript:$('query').submit();" ><img alt="查询" src="../images/buttom/buttom_search.gif"/></a>&nbsp;&nbsp;<a href="spAdd.action<#if prov_id?exists>?prov_id=${prov_id}</#if>"><img alt="新增" src="../images/buttom/buttom_xinzen.gif"/></a>
  </form>
  
</div>
</div>
<div class="tablebox"><#if reslist?exists>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab">
        <tr>
          <th>SP编号</th>
          <th>SP名称</th>
          <th>描述</th>
          <th>级别</th>
          <th>省份</th>
          <th>操作</th>
        </tr>
        <#list reslist as rl>
        <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
          <td align="center">${rl.sp_code?if_exists}</td>
          <td align="center">${rl.sp_name?if_exists}</td>
          <td align="center">${rl.sp_desc?if_exists}</td>
          <td align="center"><#if rl.sp_type==1>全国<#else>省级</#if></td>
          <td align="center">${rl.sp_local_name?if_exists}</td>

          <td align="center">
          <a href="spModify.action?id=${rl.sp_id?if_exists}" class="redfontvha">修改</a>  | <a href="javascript:dodel(${rl.sp_id?if_exists})" class="redfontvha">删除</a></td>
     </tr>
        </#list>
        
      </table>
     <table width="100%" border="0" cellpadding="0" cellspacing="0" class="box_fanye">
        <tr><td>
         	 <!--分页-->
			<#if reslist.recordCount?exists>
				<#assign recordCount=reslist.recordCount />
				<#assign lastPage=reslist.lastPage />
			    <#assign maxindexpages=recordCount/pageSize+1 />
			    <#if (maxindexpages>10)>
			        <#assign maxindexpages=10 />
			    </#if>
				<@pg.pager isOffset=true encoder="utf-8" maxPageItems=pageSize maxIndexPages=maxindexpages items=recordCount url="spInfo.action" export="offset,currentPageNumber=pageNumber" scope = "request">
				<@pg.index>
				<@pg.param name="description"  value="${description?if_exists}"/>
				<@pg.param name="prov_id"  value="${prov_id?if_exists}"/>	
				<@pg.param name="name"  value="${name?if_exists}"/>			 
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
