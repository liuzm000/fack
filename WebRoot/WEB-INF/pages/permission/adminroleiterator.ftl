<html>
	<head>
	<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>业务支撑系统-角色管理-角色列表</title>
		<link href="${request.contextPath}/css/tel.css" rel="stylesheet"
			type="text/css">
		<link href="${request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/JavaScript">
		
function submitform(form,operation)
   {
   		if(operation=='${request.contextPath}/permission/deleteAdminRole.action'){
    		if(!confirmDelete1(form,'roleIds','你确定要删除这些记录吗？')) return;
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
  	alert("请选择要删除的角色!");
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
	<span class="title">系统管理 - <strong>角色管理</strong></span></td>
    <td width="3%"  height="36"  align="right" background="${request.contextPath}/images/main/all_t_bg.gif"><img src="${request.contextPath}/images/main/all_t_r.gif" ></td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
	<!--xxxxxx-->
		<form id="admin_role_form" action="#" method="post">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab" style="margin-top:50px;">
				<tr>
					<th width="4%">
					</th>
					<th width="16%">
						角色名称
					</th>
					<th width="32%">
						角色描述
					</th>
					<th>操作
					</th>
				</tr>
				<#list roleList as role>
				<tr  onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
					<td>
						<input type="checkbox" name="roleIds" value="${role.role_id}">
					</td>
					<td>
						${role.role_name}
					</td>
					<td>
						${role.description}
					</td>
					<td align="center">
					   <a href="javascript:submitform(document.getElementById('admin_role_form'),'${request.contextPath}/permission/goModifyAdminRole.action?role_id=${role.role_id}')" class="redfontvha">修改信息</a>&nbsp;&nbsp; 
                       <a href="javascript:submitform(document.getElementById('admin_role_form'),'${request.contextPath}/permission/goModifyPermissionPage.action?role_id=${role.role_id}')" class="redfontvha">权限配置</a>
                       
						<!--<INPUT class=denglu1 type=button value="修改信息"
							onclick="javascript:submitform(this.form,'${request.contextPath}/permission/goModifyAdminRole.action?role_id=${role.role_id}')"/>
						<INPUT class=denglu1 type=button value="权限配置"
							onclick="javascript:submitform(this.form,'${request.contextPath}/permission/goModifyPermissionPage.action?role_id=${role.role_id}')"/>
					     -->
					</td>
				</tr>
				</#list>
			    <td bgcolor="#FFFFFF" style=" width:80%" colspan="8" align="center">  
				    <!--分页-->
					<#if roleList?exists>
					<!-- 模拟设置几个参数-->
						<#assign tab="[]"/>
						<#assign urltemp=request.contextPath+"/permission/adminRoleList.action" />
						<#assign formnameTemp="" />
						<#if roleList.recordCount?exists>
							<#assign recordCount=roleList.recordCount />
							<#assign lastPage=roleList.lastPage />
						    <#assign maxindexpages=recordCount/pageSize+1 />
						    <#if (maxindexpages>10)>
						        <#assign maxindexpages=10 />
						    </#if>
							<@pg.pager isOffset=true encoder="UTF-8" maxPageItems=pageSize maxIndexPages=maxindexpages items=recordCount url="${request.contextPath}/permission/adminRoleList.action" export="offset,currentPageNumber=pageNumber" scope = "request">
							<@pg.index>					  				 
								<#include "/WEB-INF/pages/common/jsp_tags.ftl"/> 
							</@pg.index>
						    </@pg.pager>
						</#if>
					</#if>
			    </td>
			  </tr>
				<tr align="center">
					<td colspan="8" bgcolor="#ffffff">
						<INPUT class=denglu1 type=button value="添加"
							onclick="javascript:submitform(this.form,'${request.contextPath}/permission/goAddRole.action')"/>
						<INPUT class=denglu1 type=button value="删除" onclick="javascript:submitform(this.form,'${request.contextPath}/permission/deleteAdminRole.action')"/>
					</td>
				</tr>
				     <tr>
			</table>
			</from>
			<!--xxxxxx-->
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
