<html>
<head>
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
	if (!confirm("确定要删除此吗？"))return;
	document.search.action="${request.contextPath}/comic/chapterList.action?id="+id;
	document.search.submit();
}
function doadd(){
	document.search.action="${request.contextPath}/comic/chapterAdd.action";
	document.search.submit();
}
</script>
</head>

<body style="padding-top:5px;padding-bottom:3px;">
<table width="100%" height="99%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="3%"  height="36"  align="left" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="../images/main/all_t_bg.gif"><img src="../images/icon/gl.gif"><span class="title">动漫管理 - 章节列表</span></td>
    <td width="3%"  height="36"  align="right" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_r.gif" ></td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
	<form name="search" method="post" action="chapterList.action">
	
	<table width="98%" border="0" cellspacing="0" cellpadding="0" class="boxsearch">
      <tr>
        <td colspan="4" ><table width="100%" border="0" cellpadding="0" cellspacing="5" class="boxsearch2" >
            <tr>
              <td width="25%" align="right">动漫基本信息列表：</td>
              <td width="18%" align="left">
			  <select name="base_id" id="base_id" class="select" width="20" onChange="changetype()">
                <option value="0" selected>请选择基本信息名称</option>
                <#if baseComicList?exists> <#list baseComicList as bCL>
                	<option value="${bCL.base_id?if_exists}"
					<#if base_id?exists && base_id==bCL.base_id >selected</#if> 
					>${bCL.base_name?if_exists}</option>
                </#list> </#if>
              </select></td>
              <td width="18%" align="right">动漫适配信息列表：</td>
              <td width="39%" align="left">
			  <select name="comic_id" class="select">
                <option value="0" selected>请选择描述名称</option>
                <#if comicList?exists> <#list comicList as cL>
					<option value="${cL.id?if_exists}" 
					<#if comic_id?exists && comic_id==cL.id >selected</#if> 
					>${cL.name?if_exists}</option>
                </#list> </#if>
              </select></td>
			  
            </tr>
			
			<tr>
				<td height="44" colspan="4" align="center" valign="middle" class="down">
			    <a href="#" onClick="dosubmit()"><img src="../images/buttom/buttom_search.gif" border="0" ></a>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" onClick="doadd()"><img src=../images/buttom/buttom_xinzen.gif border="0"></a>
				</td>
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
        
      </tr>
      <tr>
        </table>
	  </form>
      <font color=red>${showinfo?if_exists}</font>

    <#if chapterList?exists>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab" style="margin-top:5px;">
        <tr>
          <th width="54">章节ID</th>
		  <th width="54">章节名称</th>
          <th width="54">动漫ID</th>
		   <th width="130">动漫名称</th>
		   <th width="245">章节文件</th>
		   <th width="245">文件大小</th>
		   <th width="54">创建时间</th>
		   <th align="center" class="listTabTh2">操作</th>
        </tr>
      
  <#list chapterList as cL>
   <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
   <td height="14">${cL.chapter_id}</td>
   <td>${cL.chapter_name?if_exists}</td>
	<td>${cL.comic_id?if_exists}</td>
	<td>${cL.comic_name?if_exists}</td>
	 <td><a style="color:#0000FF" href="${request.contextPath}/download/comic_source/chapter/${cL.file_path?if_exists}">${cL.file_path?if_exists}</a></td>
	 <td>${cL.file_size?if_exists}KB</td>
	<td>${cL.create_date?string('yyyy-MM-dd')}  </td>
	<td align="center" width="111">
	
	<a href="chapterModify.action?id=${cL.id?if_exists}" class="redfontvha">修改</a> 
	<a href="#" onClick="dodel(${cL.id?if_exists})" class="redfontvha">删除</a>	</td>
  </tr>
  </#list>  </table>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="box_fanye">
        <tr>
          <td>
 	 <!--分页-->
		<#if comicList?exists>
		<!-- 模拟设置几个参数-->
			<#assign tab="[]"/>
			<#assign urltemp=request.contextPath+"/comic/chapterList.action" />
			<#assign formnameTemp="" />
			<#if chapterList.recordCount?exists>
				<#assign recordCount=chapterList.recordCount />
			    <#assign maxindexpages=recordCount/pageSize+1 />
			    <#if (maxindexpages>10)>
			        <#assign maxindexpages=10 />
			    </#if>
				<@pg.pager isOffset=true encoder="UTF-8" maxPageItems=pageSize maxIndexPages=maxindexpages items=recordCount url="${request.contextPath}/comic/chapterList.action" export="offset,currentPageNumber=pageNumber" scope = "request">
				<@pg.index>
				<@pg.param name="comic_id"  value="${comic_id?if_exists}"/>		
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
