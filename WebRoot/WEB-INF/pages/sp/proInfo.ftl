<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>SP产品接入管理</title>
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function $(id)
{
return document.getElementById(id);
}
function dodel(id){
if (confirm('删除此SP产品会删除对应的产品类型和发布的版本信息。你确定要删除此SP产品吗？')){
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
<img src="../images/icon/shuju.gif"/><span class="title">SP产品接入管理</span>
</div>
<div class="tmiddle">
&nbsp;
<div class="boxsearch">
	<div class="boxsearch2">
  <form id="query" name="query" method="post" action="proInfo.action">
  产品名：<input type="text" name="name" id="name" value="${name?if_exists}" />
  &nbsp;&nbsp;&nbsp;
   产品编号：
      <input type="text" name="pro_code" id="pro_code" value="${pro_code?if_exists}" />
  &nbsp;&nbsp;&nbsp;
  状态：
      <select name="pro_state"><option value=-1>--不限--</option>
      <option value=0<#if pro_state?exists && pro_state==0> selected</#if>>开通</option>
      <option value=1<#if pro_state?exists && pro_state==1> selected</#if>>申请</option>
      <option value=2<#if pro_state?exists && pro_state==2> selected</#if>>暂停</option>
      <option value=3<#if pro_state?exists && pro_state==3> selected</#if>>预注销</option>
      <option value=4<#if pro_state?exists && pro_state==4> selected</#if>>注销</option>
      </select>
        <div class="grayline"></div>
       <a href="javascript:$('query').submit();" ><img alt="查询" src="../images/buttom/buttom_search.gif"/></a>&nbsp;&nbsp;<a href="proAdd.action"><img alt="新增" src="../images/buttom/buttom_xinzen.gif"/></a>
  </form>
  
</div>
</div>
<div class="tablebox"><#if reslist?exists>
       <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab" style="word-break:break-all;">
       <tr style="word-break:break-all;">
          <th>序号</th>
          <th>SP编号</th>
          <th>SP名称</th>
          <th>产品编号</th>
          <th>产品ID</th>
          <th>产品名称</th>
          <th>描述</th>
          <th>推荐到</th>
          <!--
          <th>类型</th>
          <th>费率(分)</th>
          -->
          <th>状态</th>
          <th>父产品</th>
          <th>级别</th>
          <th>省份</th>
          <th>操作</th>
        </tr>
        <#list reslist as rl>
        <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
         <td><div align="center">${rl_index + 1 + (pageNum-1) * pageSize}</div></td>
          <td align="center">${rl.sp.sp_code?if_exists}</td>
          <td align="center">${rl.sp.sp_name?if_exists}</td>
          <td align="center">${rl.ismp_id?if_exists}</td>
          <td align="center">${rl.pro_id?if_exists}</td>
          <#if rl.proIsVouch == 0>
          	<td align="left"><font color="red"><strong>[荐]</strong></font>${rl.pro_name?if_exists}</td>
          <#else>
          	<td align="center">${rl.pro_name?if_exists}</td>
          </#if>
          <td align="center">${rl.pro_desc?if_exists}</td>
          <td align="center"><font color="red"><strong>
          <#if rl.proIsVouch == 0 && rl.proVouchType == 0>
          		最新
          <#elseif rl.proIsVouch == 0 && rl.proVouchType == 1 >
          		最热
          <#else>
                
          </#if>
          </strong></font>
          </td>
          <!--
          <#assign typelist=["包月","按次","免费"] /><#assign i=1 />
          <td align="center"><#list typelist as tl><#if rl.pro_type==i>${tl}</#if><#assign i=i+1></#list></td>
          <td align="center">${rl.pro_fee?if_exists}</td>
          -->
     <#assign typelist=["<font color='green'>开通</font>","<font color='blue'>申请</font>","<font color='red'>暂停</font>","预注销","<font color='grey'>注销</font>"] /><#assign i=0 />
          <td align="center"><#list typelist as tl><#if rl.pro_status==i>${tl}</#if><#assign i=i+1></#list></td>
          
          <td align="center"><#if rl.pro_parent_id==rl.pro_id>-<#else>[${rl.pro_parent_id}]${rl.pro_parent_name}</#if></td>
          <td align="center"><#if rl.sp.sp_type==1>全国<#else>省级</#if></td>
          <td align="center">${rl.sp.sp_local_name?if_exists}</td>
          <td align="center">
          <a href="proModify.action?id=${rl.pro_id?if_exists}" class="redfontvha">修改</a>  | <a href="javascript:dodel(${rl.pro_id?if_exists})" class="redfontvha">删除</a> |  <a href="${request.contextPath}/spManage.do?action=showSoftTypeList&proId=${rl.pro_id?if_exists}&pageNum=1&pageSize=10" class="redfontvha">配置</a></td>
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
				<@pg.pager isOffset=true encoder="utf-8" maxPageItems=pageSize maxIndexPages=maxindexpages items=recordCount url="proInfo.action" export="offset,currentPageNumber=pageNumber" scope = "request">
				<@pg.index>
				<@pg.param name="pro_code"  value="${pro_code?if_exists}"/>
				<@pg.param name="pro_state"  value="${pro_state?if_exists}"/>	
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
