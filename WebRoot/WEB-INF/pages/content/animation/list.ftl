<#assign rootPath=request.scheme+"://"+request.serverName+":"+request.serverPort+request.contextPath />
<html>
<head>
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>广东移百管理系统</title>
<link href="${rootPath}/css/main.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript">
function addNew(){
	window.location.href = "${rootPath}/content/animation/preadd.action";
}

</script>
</head>

<body style="padding-top:5px;padding-bottom:3px;">
<table width="100%" height="99%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="3%"  height="36"  align="left" background="${rootPath}/images/main/all_t_bg.gif"><img src="${rootPath}/images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="${rootPath}/images/main/all_t_bg.gif"><img src="${rootPath}/images/icon/baobiao.gif"><span class="title"><strong>桌面精灵管理</strong> - 查看列表</span></td>
    <td width="3%"  height="36"  align="right" background="${rootPath}/images/main/all_t_bg.gif"><img src="${rootPath}/images/main/all_t_r.gif" ></td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
      
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab">
        <tr>
           <th colspan="10">桌面精灵列表</th>
        </tr>
      <tr>
          <td width="5%" align="center"><b>ID</td>
          <td width="10%" align="center"><b>终端</td>
          <td width="10%" align="center"><b>背景分类</td>
          <td width="10%" align="center"><b>背景名</td>
          <td width="10%" align="center"><b>背景价格(分)</td>
          <td width="20%" align="center"><b>文件路径</td>
          <td width="10%" align="center"><b>上传时间</td>
          <td width="5%" align="center"><b>操作</td>
       </tr>
        <#if dataList?exists && dataList.size() != 0>
        <#list dataList as vo>
        <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'" align="center">
          <td align="center">${vo.id?if_exists}</td>
          <td>${vo.phoneType?if_exists}</td>
          <td>${vo.typeName?if_exists}</td>
          <td>${vo.title?if_exists}</td>
          <td>${vo.price?if_exists}</td>
          <td>${rootPath}/download${vo.filePath?if_exists}</td>
          <td>${vo.createDate?if_exists}</td>
          <td align="center">
          <a href="${rootPath}/content/animation/delete.action?id=${vo.id?if_exists}" class="redfontvha">删除</a>
          </td>
        </tr>
        </#list>
        </#if>
        <tr>
	      <td colspan="11" align="center" valign="middle" bgcolor="#FFFFFF" class="down">
	         <INPUT class="denglu1" type="button" value="添加" onClick="addNew();"/>
	      </td>
  	  	</tr>
      </table>
      
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="box_fanye">
        <tr>
          <td bgcolor="#FFFFFF" style="width:100%" colspan="6" align="center">  
			    <!--分页-->
				<#if dataList?exists>
					<#if dataList.recordCount?exists>
						<#assign recordCount=dataList.recordCount />
						<#assign lastPage=dataList.lastPage />
					    <#assign maxindexpages=recordCount/pageSize+1 />
					    <#if (maxindexpages>10)>
					        <#assign maxindexpages=10 />
					    </#if>
						<@pg.pager isOffset=true encoder="UTF-8" maxPageItems=pageSize maxIndexPages=maxindexpages items=recordCount url="${request.contextPath}/content/animation/list.action" export="offset,currentPageNumber=pageNumber" scope = "request">
						<@pg.index>			
							<#include "/WEB-INF/pages/common/jsp_tags.ftl"/> 
						</@pg.index>
					    </@pg.pager>
					</#if>
				</#if>
		    </td>
        </tr>
      </table>
     </td>
  </tr>
    
  <tr>
    <td height="26" align="left"background="${rootPath}/images/main/all_d_bg.gif"><img src="${rootPath}/images/main/all_d_l.gif" ></td>
    <td height="26" background="${rootPath}/images/main/all_d_bg.gif">&nbsp;</td>
    <td height="26" align="right" background="${rootPath}/images/main/all_d_bg.gif"><img src="${rootPath}/images/main/all_d_r.gif" ></td>
  </tr>
</table>


</body>
</html>
