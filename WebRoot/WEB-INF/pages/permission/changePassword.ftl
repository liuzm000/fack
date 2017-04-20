<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>广东移百管理系统</title>
<link href="${request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
<script language="JavaScript"  type="text/javascript" src="${request.contextPath}/javascript/common/calendar.js"></script>
<script language="JavaScript"  type="text/javascript" src="${request.contextPath}/javascript/common/base.jsp"></script>
<script language="JavaScript" type="text/JavaScript">
function checkForm(){
	if(document.getElementById("password").value.trim() == ""){
		alert("请输入旧登录密码");
    	document.getElementById("password").focus();
      	return;
	}
	if(document.getElementById("passwordNew").value.trim() == ""){
		alert("请输入新密码");
    	document.getElementById("passwordNew").focus();
      	return;
	}
	if(document.getElementById("passwordNew2").value.trim() == ""){
		alert("请重复新密码");
    	document.getElementById("passwordNew2").focus();
      	return;
	}
	if(document.getElementById("passwordNew").value.trim() != document.getElementById("passwordNew2").value.trim()){
		alert("新密码两次输入不一致");
		return;
	}
	searchForm.submit();
}

String.prototype.trim = function()  
{
    return this.replace(/(^\s*)|(\s*$)/g,"");   
}

</script>
</head>

<body style="padding-top:5px;padding-bottom:3px;">
<table width="100%" height="99%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="3%"  height="36"  align="left" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="../images/main/all_t_bg.gif"><img src="../images/icon/baobiao.gif"><span class="title"><strong>管理员管理</strong> - 修改登录密码</span></td>
    <td width="3%"  height="36"  align="right" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_r.gif" ></td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
    <form id="searchForm" name="searchForm" method="post" action="${request.contextPath}/permission/changePassword.action" enctype="multipart/form-data">
    <table width="98%" border="0" cellspacing="0" cellpadding="0" class="boxsearch">
      <tr>
        <td colspan="3">&nbsp;</td>
        </tr>
      <tr>
        <td colspan="3" valign="top" ><table width="100%" border="0" cellpadding="0" cellspacing="5" class="boxsearch2">
            <tr>
              <td width="9%" align="right" nowrap="nowrap">帐号：</td>
              <td width="30%" align="left">${account?if_exists}</td>
              <td width="9%" align="right" nowrap="nowrap">旧登录密码：</td>
              <td width="52%" align="left"><input name="password" type="password" class="input" id="password"  size="15" maxlength="20"/><font color=red>*</font> 
              </td>
            </tr>
            
            <tr>                          
              <td width="9%" align="right" nowrap="nowrap">新密码：</td>
              <td width="30%" align="left">
              	<input name="passwordNew" type="password" class="input" id="passwordNew"  size="15" maxlength="20"/><font color=red>*</font>
              </td>  
              <td width="9%" align="right" nowrap="nowrap">重复新密码：</td>
              <td width="52%" align="left"><input name="passwordNew2" type="password" class="input" id="passwordNew2"  size="15" maxlength="20"/><font color=red>*</font></td>          
            </tr>
            
            <#if errorInfo?exists && !errorInfo.equals("")>
            <tr>
              <td width="9%" align="right" nowrap="nowrap">更新状态：</td>
              <td width="30%" align="left"><font color=red>${errorInfo?if_exists}</font></td>
              <td width="9%" align="right" nowrap="nowrap"></td>
              <td width="52%" align="left"></td>
            </tr>
            </#if>
          </table></td>
       </tr>
       
       <tr>
        <td width="1%" height="1"><img src="../images/space.gif" width="1" height="1"></td>
        <td width="98%" bgcolor="#F3F3F3"><img src="../images/space.gif" width="1" height="1"></td>
        <td width="1%"><img src="../images/space.gif" width="1" height="1"></td>
       </tr>
		
      <tr>
        <td height="44" colspan="3" align="center" valign="middle" class="down">
         <INPUT class=denglu1 type=button value="保 存" onclick="checkForm()"/>
         <INPUT class=denglu1 type=button value="重 置" onclick="javascript:document.searchForm.reset();"/>
        </td>
      </tr>
    </table>
	</form>
	
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
