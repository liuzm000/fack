<html>
<head>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache"> 
<META HTTP-EQUIV="Expires" CONTENT="0"> 

<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>广东移百管理系统</title>
<link href="../css/main.css" rel="stylesheet" type="text/css">
<script language='javascript'>
function dosubmit(){
	document.search.submit();
}
function changetype(){
	document.search.submit();
}
function dodel(id){
	if (!confirm("确定要删除该动漫信息吗？"))return;
	document.search.action="${request.contextPath}/comic/comicList.action?id="+id;
	document.search.submit();
}
function doadd(){
	document.search.action="${request.contextPath}/comic/comicAdd.action";
	document.search.submit();
}
</script>
</head>

<body style="padding-top:5px;padding-bottom:3px;">
<table width="100%" height="99%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="3%"  height="36"  align="left" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="../images/main/all_t_bg.gif"><img src="../images/icon/gl.gif"><span class="title">动漫管理 - 动漫列表</span></td>
    <td width="3%"  height="36"  align="right" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_r.gif" ></td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
	<form name="search" method="post" action="comicList.action">
	
	<table width="98%" border="0" cellspacing="0" cellpadding="0" class="boxsearch">
      <tr>
        <td colspan="4" ><table width="100%" border="0" cellpadding="0" cellspacing="5" class="boxsearch2" >
            <tr>
              <td width="18%" align="right">名称：</td>
              <td width="14%" align="left"><input name="name" type="text" class="input" id="name" value="${name?if_exists}" />
              <td width="9%" align="right">动漫类型：</td>
              <td width="8%" align="left">
				<select name="type" class="select" onChange="changetype()">
				<option value="0" <#if (type)?exists && type == 0>selected</#if>>请选择动漫类型</option>
				<option value="1" <#if (type)?exists && type == 1>selected</#if>>连载漫画</option>
				<option value="2" <#if (type)?exists && type == 2>selected</#if>>四格漫画</option>
				</select>			  
			  </td>

			  <td width="11%" align="right">所属动漫：</td>
			  <td width="11%" align="left">
					<select name="base_id" id="base_id" class="select" width="20">
							<option value="0" selected>请选择动漫</option>
							<#if baseComicList?exists>
								<#list baseComicList as bCL>
								<option value="${bCL.base_id?if_exists}" 
								<#if base_id?exists && base_id==bCL.base_id >selected</#if> 
								>${bCL.base_name?if_exists}</option>
								</#list>
							</#if>
					</select>	
              </td>

			  <td width="29%" align="left">&nbsp;</td>
            </tr>
        </table>
		
		</td>
      </tr>
      <tr>
        <td width="2%" height="1"><img src="../images/space.gif" width="1" height="1"></td>
        <td colspan="2" bgcolor="#F3F3F3" ><img src="../images/space.gif" width="1" height="1"></td>
        <td width="2%" height="1"><img src="../images/space.gif" width="1" height="1"></td>
      </tr>
      <tr>
        <td height="44" colspan="4" align="center" valign="middle" class="down">
		<a href="#" onClick="dosubmit()"><img src="../images/buttom/buttom_search.gif" border="0" ></a>
		 &nbsp;&nbsp;&nbsp;&nbsp;
		 <a href="#" onClick="doadd()"><img src=../images/buttom/buttom_xinzen.gif border="0"></a>
		 &nbsp;&nbsp;&nbsp;&nbsp;
			</td>
      </tr>
      <tr>
        </table>
	  </form>
      <font color=red>${showinfo?if_exists}</font>

    <#if comicList?exists>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab" style="margin-top:5px;">
        <tr>
           <th width="54">编号ID</th>
		   <th width="130">描述名称</th>
		   <th width="100">所属动漫名称</th>
		   <th width="104">封面文件</th>
		   <th width="173">适配信息</th>
		   <th width="187">创建时间</th>
		   <th align="center" class="listTabTh2">操作</th>
        </tr>
      
  <#list comicList as cL>
   <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
    <td height="14">${cL.id}</td>
   
	<td>${cL.name?if_exists}</td>
	 <td>${cL.base_name?if_exists}</td>
	<td><img src="${request.contextPath}/download/comic_source/comic/${cL.cover_path?if_exists}?rd=${cL.update_date?string('HH:mm:ss')}" border="0" >	</td>
	<td>${cL.match_info?if_exists}	</td>
	<td>${cL.create_date?string('yyyy-MM-dd')}  </td>
	<td align="center" width="126">
	
	<a href="comicModify.action?id=${cL.id?if_exists}" class="redfontvha">修改</a> 
	<a href="#" onClick="dodel(${cL.id})" class="redfontvha">删除</a>

	<a href="comicMatch.action?id=${cL.id?if_exists}" class="redfontvha">适配</a>	</td>
  </tr>
  </#list>  </table>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="box_fanye">
        <tr>
          <td>
 	 <!--分页-->
		<#if comicList?exists>
		<!-- 模拟设置几个参数-->
			<#assign tab="[]"/>
			<#assign urltemp=request.contextPath+"/comic/comicList.action" />
			<#assign formnameTemp="" />
			<#if comicList.recordCount?exists>
				<#assign recordCount=comicList.recordCount />
			    <#assign maxindexpages=recordCount/pageSize+1 />
			    <#if (maxindexpages>10)>
			        <#assign maxindexpages=10 />
			    </#if>
				<@pg.pager isOffset=true encoder="UTF-8" maxPageItems=pageSize maxIndexPages=maxindexpages items=recordCount url="${request.contextPath}/comic/comicList.action" export="offset,currentPageNumber=pageNumber" scope = "request">
				<@pg.index>	
				<@pg.param name="base_id"  value="${base_id?if_exists}"/>	
				<@pg.param name="name"  value="${name?if_exists}"/>	
					<#include "/WEB-INF/pages/common/jsp_tags.ftl"/> 
				</@pg.index>
			    </@pg.pager>
			</#if>
		</#if>           
          </td>
        </tr>
      </table>
      </#if>
    </td>
  </tr>
  
  
  
  <tr>
    <td height="26" align="left"background="../images/main/all_d_bg.gif"><img src="../images/main/all_d_l.gif" ></td>
    <td height="26" background="../images/main/all_d_bg.gif">&nbsp;</td>
    <td height="26" align="right" background="../images/main/all_d_bg.gif"><img src="../images/main/all_d_r.gif" ></td>
  </tr>
</table>


</body>

</html>
