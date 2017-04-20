<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>SP软件审核管理</title>
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<style>
.sec{
	margin:0 20px;
	padding-top:10px;
	height:25px;
}
.sec1 {
	background: #FFFFFF url(../images/buttom/botton_sec1.gif) no-repeat;
	width: 105px;
	height: 23px;
	color: #0066CC;
	FONT-WEIGHT: bold;
	text-align:left;padding-left:25px;
	line-height:23px;
	float:left;
	cursor:hand;
}
.sec2 {
	line-height:23px;
	background: #FFFFFF url(../images/buttom/botton_sec2.gif) no-repeat;
	width: 105px;
	height:23px;
	color: white;
	FONT-WEIGHT: bold;
	text-align:left;padding-left:25px;
	cursor:hand;
	float:left;
}
</style>

<SCRIPT language=JavaScript type=text/javascript>
function $(id)
{
return document.getElementById(id);
}
function secBoard(n)
{
if (n==1){
$('query').action='svInfo.action';

}
else
{
$('query').action='svInfo.action?ver=1';
}
$('query').submit();
}

</SCRIPT>


</head>
<body>
<script src="../javascript/DatePicker/WdatePicker.js" type="text/javascript"></script>
<div class="top">
<div class="topright"></div>
<div class="topleft"></div>
<img src="../images/icon/yhgl.gif"/><span class="title">SP软件审核管理</span>
</div>
<div class="tmiddle">
<div class="sec"><div class="<#if ver?exists>sec1<#else>sec2</#if>" href="#" onclick="secBoard(1)">待审核</div><div class="<#if ver?exists>sec2<#else>sec1</#if>" href="#" onclick="secBoard(2)">已审核</div></div>
<div class="clear"></div>
<div class="boxsearch">
	<div class="boxsearch2">
	  <form id="query" name="query" method="post" action="svInfo.action<#if ver?exists>?ver=1</#if>">
	软件名：
        <input type="text" name="name" id="name" value="${name?if_exists}" />
  &nbsp;&nbsp;&nbsp;  
    软件版本描述：
        <input type="text" name="description" id="description" value="${description?if_exists}" />
 <div class="clear" style='margin:5px 0'></div>  
  申请时间：<input type="text" size="14" id="starttime" name="starttime" class="Wdate" value="${starttime?if_exists}" onFocus="WdatePicker({startDate:'%y-%M-01',readOnly:true})"/>
   至
   <input type="text" size=14 id="endtime" name="endtime" class="Wdate" value="${endtime?if_exists}" onFocus="WdatePicker({readOnly:true})"/>（留空则不限制）
<div class="grayline"></div>
	<a href="javascript:$('query').submit();" ><img alt="查询" src="../images/buttom/buttom_search.gif"/></a>
	  </form>
</div>
</div>
<div class="tablebox"><#if reslist?exists>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab">
        <tr>
          <th>审核ID</th>
          <th>SP编号</th>
          <th>SP名称</th>
          <th>产品</th>
          <th>软件名称</th>
          <th>版本号</th>
          <th>版本描述</th>
          <th>申请时间</th>
          <th>强制更新</th>
          <#if ver?exists>
          <th>审核人</th>
          <th>审核时间</th>
          <th>审核状态</th>
          </#if>
          <th>操作</th>
        </tr>
        <#list reslist as rl>
        <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
          <td align="center">${rl.audit_id?if_exists}</td>
          <td align="center">${rl.sv.st.pro.sp.sp_code?if_exists}
          <td align="center">${rl.sv.st.pro.sp.sp_name}</td>
          <td align="center">[${rl.sv.st.pro.pro_id?if_exists}]${rl.sv.st.pro.pro_name?if_exists}</td>
          <td align="center">${rl.sv.st.st_name?if_exists}</td>
           <td align="center">${rl.soft_version?if_exists}</td>
          <td align="center">${rl.soft_desc?if_exists}</td>
          <td align="center">${rl.create_time}</td>
          <td align="center"><#if rl.soft_forceupdate==0><font color='blue'>是</font><#else>否</#if></td>
          <#if ver?exists>
          <td align="center">${rl.audit_by?if_exists}</td>
          <td align="center">${rl.audit_time?if_exists}</td>
          <td align="center"><#if rl.audit_status==2><font color=green>通过</font><#else><font color=red>未通过</font></#if></td>
          </#if>
          <td align="center"><a href="svDetail.action?id=${rl.audit_id?if_exists}<#if ver?exists>&ver=1</#if>" class="redfontvha"><#if ver?exists>详细<#else>审核</#if></a></td>
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
				<@pg.pager isOffset=true encoder="utf-8" maxPageItems=pageSize maxIndexPages=maxindexpages items=recordCount url="svInfo.action" export="offset,currentPageNumber=pageNumber" scope = "request">
				<@pg.index>
					<@pg.param name="description"  value="${description?if_exists}"/>
					<@pg.param name="name" value="${name?if_exists}"/>		
					<@pg.param name="starttime" value="${starttime?if_exists}"/>	
					<@pg.param name="endtime" value="${endtime?if_exists}"/>	
					<#if ver?exists><@pg.param name="ver" value="1"/></#if>	 
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
</div>

</body>
</html>
