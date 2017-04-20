<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp"%>
<html>
	<head>
		<title>错误信息提示</title>
		<link href="<%=basePath %>/css/tel.css" rel="stylesheet" type="text/css">
	</head>

	<body>
<link href="<%=basePath %>/css/tel.css" rel="stylesheet" type="text/css">
	<table  border="0" cellspacing="0" cellpadding="0" style="margin:30px 0px 0px 10px;width:98%">
	  <tr>
	    <td><img src="<%=basePath %>/images/information_error.gif" width="750" height="30"></td>
	  </tr>
	</table>
	<table  border="0" cellspacing="0" cellpadding="0" style="margin:10px 0px 0px 10px;width:98%; background-image:url(<%=basePath %>/images/message_bg.gif); height:320px">
	  <tr>
	    <td valign="top"><table width="96%"  border="0" align="center" cellpadding="0" cellspacing="0">
	        <tr>
	          <td width="65%" height="120" align="center">
					<font color="red">系统数据库出错，请检验操作是否有误或与管理员联系！<a href="javascript:history.go(-1);">返回</a></font>
			  </td>
	          <td width="35%" align="center"><img src="<%=basePath %>/images/m_pic2.jpg" width="156" height="103"></td>
	        </tr>
	    </table></td>
	  </tr>
	</table>
	
		<!--
	error messages :
		<ww:property value="exceptionStack"/>
-->
</body>
</html>
