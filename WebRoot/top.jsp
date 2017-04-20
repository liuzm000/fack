<%@ page contentType="text/html;charset=utf-8"%>
<%
String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>集团移百管理平台</title>
<link href="<%=basePath%>css/top.css" rel="stylesheet" type="text/css">
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="15" colspan="3" background="<%=basePath%>images/top/line_bg.gif">&nbsp;</td>
  </tr>
  <tr>
    <td width="537"  style="background:#8DC6EB;"><img src="<%=basePath%>images/top/title_l.gif" ></td>
    <td width="683" align="right" valign="bottom" style="background:#8DC6EB;" class="hui">
        <%=request.getSession().getAttribute("account")!=null?request.getSession().getAttribute("account"):""%>  您好！[<%=request.getSession().getAttribute("roleName")!=null?request.getSession().getAttribute("roleName"):""%>] | <a href="<%=basePath%>logout.action">安全退出</a></td>
    <td width="31" align="right" valign="bottom" style="background:#8DC6EB;"></td>
  </tr>
</table>
</body>
</html>
