<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>业务支撑系统-管理员管理-修改管理员</title>
		<link href="${request.contextPath}/css/tel.css" rel="stylesheet" type="text/css">
		<link href="${request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/JavaScript">		
		
		function validate()
   		{
   		   var form=document.getElementById("adminform");
   		   return true;
       }
		</script>
	</head>

	<body style="padding-top:5px;padding-bottom:3px;padding-left:5px;">
<table width="100%" height="99%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="3%"  height="36"  align="left" background="${request.contextPath}/images/main/all_t_bg.gif"><img src="${request.contextPath}/images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="${request.contextPath}/images/main/all_t_bg.gif"><img src="${request.contextPath}/images/icon/gl.gif">
	<span class="title">系统管理 - <strong>修改管理员</strong></span></td>
    <td width="3%"  height="36"  align="right" background="${request.contextPath}/images/main/all_t_bg.gif"><img src="${request.contextPath}/images/main/all_t_r.gif" ></td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
	<!--xxxxxx-->
		<form id="adminform" name="adminform" method="post" action="${request.contextPath}/permission/modifyAdmin.action" onSubmit="return validate();">
			<table width="98%" border="0" cellspacing="0" cellpadding="0" class="boxsearch">
      <tr>
        <td colspan="3">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="3" valign="top" >
			<table border="0" cellpadding="1" cellspacing="5" class="boxsearch2" align="center">
				
				<input type="hidden" name="administrator_id" value="${administrator.userId?if_exists}"/>
				<tr bgcolor="#FFFFFF">
					<td width="20%" align="right">
						账号
					</td>
					<td width="80%" align="left">
						${administrator.account?if_exists}
					</td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td width="20%" align="right">
						管理员角色
					</td>
					<td width="80%" align="left">
						<select name="role_id">
		               <#list roleList as role>
			               <option value="${role.role_id?if_exists}" <#if role.role_id == administrator.roleId?if_exists>selected</#if>>${role.role_name?if_exists}</option>	
		                </#list>
	                   </select>
					</td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td width="20%" align="right">
						描述
					</td>
					<td width="80%" align="left">
						<textarea name="description" id="description">${administrator.description?if_exists}</textarea>
					</td>
				</tr>
			</table>
			</td>
      </tr>
      <tr>
        <td width="1%" height="1"><img src="../images/space.gif" width="1" height="1"></td>
        <td width="98%" bgcolor="#F3F3F3"><img src="../images/space.gif" width="1" height="1"></td>
        <td width="1%"><img src="../images/space.gif" width="1" height="1"></td>
      </tr>
      <tr>
        <td height="44" colspan="3" align="center" valign="middle" class="down">
           <INPUT class="denglu1" type="submit" value="修改">
		   <INPUT class=denglu1 type=button value="返回" 	onclick="javascript:window.history.go(-1);">
       </td>
      </tr>
    </table>
		</form>
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
