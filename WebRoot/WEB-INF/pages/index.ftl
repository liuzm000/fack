<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>MISMP</title>
</head>

<!--
<frameset rows="85,*,76" cols="*" frameborder="no" border="0" framespacing="0">
-->
<frameset rows="85,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="${request.contextPath}/top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
  <frameset rows="*" cols="200,*" framespacing="0" frameborder="0" border="0" bordercolor="#EEF7FF"  TOPMARGIN="0" LEFTMARGIN="0" MARGINHEIGHT="0" MARGINWIDTH="0" >
	  <frame src="${request.contextPath}/leftFrame.action" name="leftFrame"  frameborder="0" scrolling="auto" noresize  id="leftFrame"  />
	  <frame src="${request.contextPath}/mainFrame.action" name="mainFrame" scrolling="auto" />
  </frameset>
  <!--
  <frame  src="${request.contextPath}/bottom.jsp" name="bottomFrame" scrolling="no" noresize="noresize" id="botttomFrame" title="bottomFrame" />
  -->
</frameset>
<noframes>
<body>
</body>
</noframes>
</html>