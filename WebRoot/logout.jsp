<%@ page contentType="text/html;charset=utf-8"%>
<%
String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>

<script language="javaScript">
	//alert("您已安全退出！");
	window.parent.location.href="<%=basePath%>";
//	window.opener = null;
//	window.close();
</script>
