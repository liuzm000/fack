<html>
<head>
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>广东移百管理系统-管理员管理</title>
<link href="${request.contextPath}/css/tel.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript">
function submitform(form,operation)
   {
   		if(operation=='${request.contextPath}/permission/deleteAdmins.action'){
    		if(!confirmDelete1(form,'checkedIds','你确定要删除这些记录吗？')) return;
  		}
   		form.action=operation;
   		form.submit();
   }
   
function confirmDelete1(formobject,chkname,msg){
  	   selected=false;
  	   for(i=0;i<formobject.elements.length;i++){
   			if (formobject.elements[i].name==chkname) {
       			if(formobject.elements[i].checked) selected=true;
   			}
  	  }
      if(!selected){
  			alert("请选择要删除的管理员!");
   			return false;
      }
 	  if(confirm(msg)) {
         return true;
      }else{
         return false;
      }
}
   			
</script>
</head>

<body style="padding-top:5px;padding-bottom:3px;padding-left:5px;">
<table width="100%" height="99%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="3%"  height="36"  align="left" background="${request.contextPath}/images/main/all_t_bg.gif"><img src="${request.contextPath}/images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="${request.contextPath}/images/main/all_t_bg.gif"><img src="${request.contextPath}/images/icon/gl.gif">
	<span class="title">系统管理 - <strong>管理员管理</strong></span></td>
    <td width="3%"  height="36"  align="right" background="${request.contextPath}/images/main/all_t_bg.gif"><img src="${request.contextPath}/images/main/all_t_r.gif" ></td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
<form name="adminform" method="post">
<table  border="0" cellpadding="0" cellspacing="0"  class="listTab" style="margin-top:50px;">
<#if admList.recordCount?exists && admList.recordCount != 0>
  <tr>
    <th width="4%"></td>
    <th width="4%">序号</th>
    <th width="12%">帐号</th>
    <th width="4%">管理员ID</th>
    <th width="8%">描述</th>
    <th width="8%">所属酒店/餐馆</th>
    <th>所属角色</th>
    
    <th>操作</th>
  </tr>
   <#list admList as admin>
	  <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
	    <td><input type="checkbox" name="checkedIds" value="${admin.userId?if_exists}"></td>
	    <td><div align="center">${admin_index + 1 + (pageNum-1) * pageSize}</div></td>
	    <td>${admin.account?if_exists}</td>
	    <td>${admin.userId?if_exists}</td>
	    <td width="40%">${admin.description?if_exists}</td>
	    <td>${admin.hotelName}</td>
	    <td>${admin.roleName?if_exists}</td>
	    <td align="center">
		   <a href="javascript:window.location.href='${request.contextPath}/permission/goModifyAdmin.action?administrator_id=${admin.userId?if_exists}'" class="redfontvha">修改</a>&nbsp;&nbsp;
	    </td>
	  </tr>
    </#list>
   
     <tr>
    <td  colspan="8" align="center">  
	    <!--分页-->
		<#if admList?exists>
		<!-- 模拟设置几个参数-->
			<#assign tab="[]"/>
			<#assign urltemp=request.contextPath+"/permission/adminList.action" />
			<#assign formnameTemp="" />
			<#if admList.recordCount?exists>
				<#assign recordCount=admList.recordCount />
				<#assign lastPage=admList.lastPage />
			    <#assign maxindexpages=recordCount/pageSize+1 />
			    <#if (maxindexpages>10)>
			        <#assign maxindexpages=10 />
			    </#if>
				<@pg.pager isOffset=true encoder="UTF-8" maxPageItems=pageSize maxIndexPages=maxindexpages items=recordCount url="${request.contextPath}/permission/adminList.action" export="offset,currentPageNumber=pageNumber" scope = "request">
				<@pg.index>					  				 
					<#include "/WEB-INF/pages/common/jsp_tags.ftl"/> 
				</@pg.index>
			    </@pg.pager>
			</#if>
		</#if>
    </td>
  </tr>
	 </table>
	 <table  border="0" cellpadding="0" cellspacing="0"   style="margin-top:20px;">
	 <tr align="center">
	    <td>
	    <input class="denglu1" type="button" value="添加" onclick="javascript:submitform(this.form,'${request.contextPath}/permission/goAddAdmin.action')"/>　
		<input class=denglu1 type="button" value="删除" onclick="javascript:submitform(this.form,'${request.contextPath}/permission/deleteAdmins.action')"/></td>
	 </tr>
	 </table>
<#else>
  <tr bgcolor="#F3F7FD">
  <td align="center"> 没有符合条件的管理员记录!</td>
  </tr>
   
  </table>
  <table  border="0" cellpadding="0" cellspacing="0"   style="margin-top:20px;">
	 <tr align="center">
    <td colspan="9" >
    <input class=denglu1 type="button" value="添加" onclick="javascript:submitform(this.form,'${request.contextPath}/permission/goAddAdmin.action')"/>　　</td>
  </tr>
	 </table>
  </#if>


</form>
	</td>
  </tr>
  <tr>
    <td height="26" align="left"background="${request.contextPath}/images/main/all_d_bg.gif"><img src="${request.contextPath}/images/main/all_d_l.gif" ></td>
    <td height="26" background="${request.contextPath}/images/main/all_d_bg.gif">&nbsp;</td>
    <td height="26" align="right" background="${request.contextPath}/images/main/all_d_bg.gif"><img src="${request.contextPath}/images/main/all_d_r.gif" ></td>
  </tr>
</table>
</body>
</html>
