<html>
<head><#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>MISMP</title>
<link href="${request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
<script language="JavaScript"  type="text/javascript" src="${request.contextPath}/javascript/common/calendar.js"></script>
<script language="JavaScript"  type="text/javascript" src="${request.contextPath}/javascript/common/jquery1.2.6.js"></script>
<script language="JavaScript"  type="text/javascript" src="${request.contextPath}/javascript/common/base.jsp"></script>
<script language="JavaScript"  type="text/javascript" src="${request.contextPath}/javascript/common/alex.js"></script>
<script language="JavaScript" type="text/JavaScript">
	function addNewGroup(){
		window.location = document.getElementById("addURL").value;
	}
	
	function editGroup(obj){
		var tgId = obj;
		var form = document.getElementById("editForm");
		if(tgId != null){
			document.getElementById("idForEdit").value = tgId;
			form.submit();
		}
	}

	function deleteGroup(obj){
		var tgId = obj;
		var form = document.getElementById("editForm");
		if(tgId != null){
			if( window.confirm("确定删除该条信息吗?")){
				document.getElementById("idForDel").value = tgId;
			    form.action = document.getElementById("deleteURL").value;
				form.submit();
			}
		}
	}
</script>
</head>

<body style="padding-top:5px;padding-bottom:3px;">
<table width="100%" height="99%" border="0" cellspacing="0" cellpadding="0">
  <form id="editForm" name="editForm" method="post" action="${request.contextPath}/customer/editTerUserGroup.action">
      <input type="hidden" name="idForEdit" id="idForEdit"/>
      <input type="hidden" name="idForDel" id="idForDel"/>
  <!-- 头部 -->
  <tr>
    <td width="3%"  height="36"  align="left" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="../images/main/all_t_bg.gif"><img src="../images/icon/baobiao.gif"><span class="title"><strong>终端用户群组管理</strong> - 终端用户群组列表</span></td>
    <td width="3%"  height="36"  align="right" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_r.gif" ></td>
  </tr>
  <!-- 按钮部分 -->
  <tr height="30">
  	<td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
  	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab">
  		<tr>
  			<td width="88%">&nbsp;</td>
  			<td width="*"><INPUT class="input" type="button" value="添加新群组" onclick="addNewGroup()"/></td>
  			<td width="2%"><input id="addURL" name="addURL" type="text" value="${request.contextPath}/customer/addTerUserGroup.action" style="display:none">
  			<input id="deleteURL" name="deleteURL" type="text" value="${request.contextPath}/customer/deleteTerUserGroup.action" style="display:none"></td>
  		</tr>
  	</table>
  	</td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab" style="word-break:break-all;">
        <tr>
          <th width="8%">&nbsp;</th>
           <th colspan="9">终端用户群组列表</th>
        </tr>
        <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
         <td><div align="center"><strong>序号</strong></div></td>
          <td width="10%"><div align="center"><strong>群组名称</strong></div></td>
          <td width="10%" align="center"><strong>群组ID</strong></td>
          <td width="10%"><div align="center"><strong>描述</strong></div></td>
          <td><div align="center"><strong>号码段</strong></div></td>
          <td width="10%"><div align="center"><strong>操作</strong></div></td>
        </tr>
        <#if dataList?exists && dataList.size() != 0>
        <#list dataList as vo>
        <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
          <td><div align="center">${vo_index + 1 + (pageNum-1) * pageSize}</div></td>
          <td><div align="center">${vo.tgName?if_exists}</div></td>
          <td align="center"><div align="center">${vo.tgId?if_exists}</div></td>
          <td><div align="center">${vo.tgDesc?if_exists}</div></td>
          <td><div align="">${vo.tgPhone?if_exists}</td>
		  <td><div align="center">
		  	<a href="javascript:editGroup(${vo.tgId?if_exists})">修改</a>
		  	<a href="javascript:deleteGroup(${vo.tgId?if_exists})">删除</a>
		  </td>
        </tr>
        </#list>
        </#if>
      </table>
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
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
						<@pg.pager isOffset=true encoder="UTF-8" maxPageItems=pageSize maxIndexPages=maxindexpages items=recordCount url="${request.contextPath}/customer/${actionName?if_exists}" export="offset,currentPageNumber=pageNumber" scope = "request">
						<@pg.index>
							<#include "/WEB-INF/pages/common/jsp_tags.ftl"/> 
						</@pg.index>
					    </@pg.pager>
					</#if>
				</#if>
		    </td>
        </tr>
      </table></td>
  </tr>
    
  <tr>
    <td height="26" align="left"background="../images/main/all_d_bg.gif"><img src="../images/main/all_d_l.gif" ></td>
    <td height="26" background="../images/main/all_d_bg.gif">&nbsp;</td>
    <td height="26" align="right" background="../images/main/all_d_bg.gif"><img src="../images/main/all_d_r.gif" ></td>
  </tr>
  </form>
</table>
</body>
</html>
