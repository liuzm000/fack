<%@ page contentType="text/html;charset=utf-8"%>
<%
String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>MISMP</title>
<link href="<%=basePath%>css/login.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function validate()
{
	var account = document.loginform.account.value;
	var password = document.loginform.password.value;
	var validateCode = document.loginform.validateCode.value;
	
	if(account=='')
	{
		alert("帐号不得为空!");
		document.loginform.account.focus();
		return false;
	}
	
	if(password=='')
	{
		alert("密码不得为空!");
		document.loginform.password.focus();
		return false;
	}
	
	if(validateCode=='')
	{
		alert("验证码不得为空!");
		document.loginform.validateCode.focus();
		return false;
	}
	return true;
}
</script>
</head>

<body>
<form action="<%=basePath%>login.action" id="loginform" name="loginform" method="post" onSubmit="return validate();">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="140" class="toptable">&nbsp;</td>
  </tr>
  <tr>
    <td align="center" valign="top"><table width="522" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><img src="<%=basePath%>images/login/title_l2.gif"></td>
      </tr>
      <tr>
        <td height="188" background="<%=basePath%>images/login/middle.jpg">
        <table width="299"  border="0" cellpadding="0" cellspacing="0" style="margin-left:210px; margin-top:20px;">
	      <% if(request.getSession().getAttribute("errorInfo")!=null) {%>
						<div align="center">
							<font color="red">
								<%=request.getSession().getAttribute("errorInfo")%>
							</font>
						</div>
					<% } %>
          <tr>
            <td width="22" height="30"><img src="<%=basePath%>images/login/icon1.gif"/></td>
            <td width="51" class="black">管理员</td>
            <td colspan="2"><span >
              <input name="account" type="text" class="input_login" id="account" style="width:160px; height:19px;" />
            </span></td>
          </tr>
		      <tr>
            <td height="30" ><img src="<%=basePath%>images/login/icon2.gif" /></td>
            <td class="black"> 密 &nbsp;码</td>
            <td colspan="2"><span>
              <input name="password" type="password" class="input_login" id="password" style="width:160px; height:19px;"/>
            </span></td>
          </tr>
          <tr>
            <td height="30"><img src="<%=basePath%>images/login/icon2.gif" /></td>
            <td class="black"> 验证码</td>
            <td width="108"><span>
              <input name="validateCode" type="text" class="input_login" id="validateCode" style="width:97px; height:19px;"/>
            </span></td>
            <td width="118"><img alt="" name="validateCodeImg" id="validateCodeImg" src="<%=basePath%>validateCode" width="51" height="17" /></td>
          </tr>
          <tr>
            <td height="40" colspan="4" align="center" valign="bottom" style="margin-right:30px;">
            <input type="image" src="<%=basePath%>images/login/buttom_1.gif" border="0"  style="cursor:hand"/> &nbsp;&nbsp;
            <img src="<%=basePath%>images/login/buttom_2.gif"  border="0" onClick="document.loginform.reset();" style="cursor:hand"/></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="30" background="<%=basePath%>images/login/down.jpg" class="hui">Copyright© 2007―2013 中国电信 版权所有</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="78" align="center" background="<%=basePath%>images/down/bg.gif"><table width="57%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><img src="<%=basePath%>images/down/logo.gif"></td>
        <td><span class="hui">
          </span>&nbsp;&nbsp;电信及增值业务经营许可证:沪ICP备06026347号</td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
<% if(request.getSession().getAttribute("errorInfo")!=null) {
	request.getSession().removeAttribute("errorInfo");
}
%>
</body>
</html>
