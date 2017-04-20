
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>业务支撑系统-角色管理-修改角色</title>
		<link href="${request.contextPath}/css/tel.css" rel="stylesheet"
			type="text/css">
		<link href="${request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/JavaScript">
		function submitform(form,operation)
   		{
		  	if(operation=="${request.contextPath}/permission/modifyAdminRole.action"){
						   if(form.role_name.value == '')
						   {
						   	 	alert("请填写角色名称！");
						   	 	form.account.focus();
						   	 	return;
						   }
						   if(form.description.value == '')
						   {
						   	 	alert("请填写角色描述！");
						   	 	form.password.focus();
						   	 	return;
						   }
				}
		   		//alert(operation);
		   		form.action=operation;
		   		form.submit();
   		}
   		
   		  function validate(){
             if(document.getElementById("role_name").value.length<1){
	          alert("请填写角色名称！");
	          document.getElementById("role_name").focus();
	          return false;
	         }
	         if(document.getElementById("description").value.length<1){
	          alert("请填写角色描述！");
	          document.getElementById("description").focus();
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
	<span class="title">系统管理 - <strong>修改管理员角色</strong></span></td>
    <td width="3%"  height="36"  align="right" background="${request.contextPath}/images/main/all_t_bg.gif"><img src="${request.contextPath}/images/main/all_t_r.gif" ></td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
	<!--xxxxxx-->
		<FORM method="post" action="${request.contextPath}/permission/modifyAdminRole.action" name="Form1" id="Form1" class="savehis" enctype="multipart/form-data" onSubmit="return validate();">  
			<input type="hidden" name="role_id" value="${role.role_id}">
			<table width="98%" border="0" cellspacing="0" cellpadding="0" class="boxsearch">
      <tr>
        <td colspan="3">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="3" valign="top" >
			<table width="100%" border="0" cellpadding="0" cellspacing="5" class="boxsearch2">
				<tr>
					<td width="20%" align="right">
						* 角色名
					</td>
					<td width="80%" align="left">
						<input class="input" type="text" id="role_name" name="role_name" value="${role.role_name}">
					</td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td width="20%" align="right">
						* 角色描述
					</td>
					<td width="80%" align="left">
						<textarea name="description" id="description" >${role.description}</textarea>
					</td>
				</tr>
                 <tr>
				<td height="20"></td>
				<td></td>
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
        <input type="submit"  value="修 改" class="denglu3">
&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="button"  value="返 回" class="denglu3" onclick="javascript:window.history.go(-1);">
&nbsp;&nbsp;&nbsp;&nbsp;</td>
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
