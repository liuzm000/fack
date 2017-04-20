<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>业务支撑系统-权限管理-修改角色权限</title>
<link href="${request.contextPath}/css/tel.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
	</head>

<body style="padding-top:5px;padding-bottom:3px;padding-left:5px;">
<table width="100%" height="99%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="3%"  height="36"  align="left" background="${request.contextPath}/images/main/all_t_bg.gif"><img src="${request.contextPath}/images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="${request.contextPath}/images/main/all_t_bg.gif"><img src="${request.contextPath}/images/icon/gl.gif">
	<span class="title">系统管理 - <strong>权限配置</strong></span></td>
    <td width="3%"  height="36"  align="right" background="${request.contextPath}/images/main/all_t_bg.gif"><img src="${request.contextPath}/images/main/all_t_r.gif" ></td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
	<!--xxxxxx-->
		<table border="0" cellpadding="4" cellspacing="0"
			style="margin:10px 0px 0px 10px;width:98%;background-color:#B7CDE4">
			<tr>
				<td width="120" bgcolor="#ffffff">
					<!--<font color="red"><a href="${request.contextPath}/system/goModifyAdminRolePermission.action?role_id=${role_id?if_exists}" target="content_frame">→&nbsp;支撑系统权限配置</a></font>-->
				</td>
				<td width="120" bgcolor="#ffffff">
					<!--<a href="${request.contextPath}/system/shdhmodadminrolepermission.htm">&nbsp;&nbsp;生活导航权限配置</a>-->
				</td>
				<td width="120" bgcolor="#ffffff">
					<!--<a href="http://sj.118114.cn/backend/right.do?role=${role_id?if_exists}&method=allocate" target="content_frame">&nbsp;&nbsp;商机网权限配置</a>-->
				</td>
				<td bgcolor="#ffffff"></td>
			</tr>
		</table>
	
		<table border="0" cellpadding="6" cellspacing="1"
			style="margin:10px 0px 0px 10px;width:70%;background-color:#B7CDE4">

			<tr bgcolor="#FFFFFF">
				<td width="100%" align="center" height="1000">
					<iframe name="content_frame" marginwidth=0 marginheight=0 width=100% height=100% src="${request.contextPath}/permission/goModifyAdminRolePermission.action?role_id=${role_id?if_exists}" frameborder=0></iframe>
				</td>
			</tr>
		</table>
<!--xxxxxx-->
	</td>
  </tr>
  <tr>
    <td height="26" align="left"background="${request.contextPath}/images/main/all_d_bg.gif"><img src="${request.contextPath}/images/main/all_d_l.gif" ></td>
    <td height="26" background="${request.contextPath}/images/main/all_d_bg.gif">&nbsp;</td>
    <td height="26" align="right" background="${request.contextPath}/images/main/all_d_bg.gif"><img src="${request.contextPath}/images/main/all_d_r.gif" ></td>
  </tr>
</table>
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
