
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误信息提示</title>
<link href="${request.contextPath}/css/tel.css" rel="stylesheet" type="text/css">
<script src="${request.contextPath}/javascript/common/operation.js" type="text/javascript"></script>
</head>

<body>

	<table  border="0" cellspacing="0" cellpadding="0" style="margin:30px 0px 0px 10px;width:98%">
	  <tr>
	    <td><img src="${request.contextPath}/images/information_error.gif" width="750" height="30"></td>
	  </tr>
	</table>
	<table  border="0" cellspacing="0" cellpadding="0" style="margin:10px 0px 0px 10px;width:98%; background-image:url(${request.contextPath}/images/message_bg.gif); height:320px">
	  <tr>
	    <td valign="top"><table width="96%"  border="0" align="center" cellpadding="0" cellspacing="0">
	        <tr>
	          <td width="65%" height="120" align="center">
					<font color="red">出错了 ${errorMessage?if_exists} 请检验操作是否有误或与管理员联系！<a href="javascript:history.go(-1);">返回</a></font>
			  </td>
	          <td width="35%" align="center"><img src="${request.contextPath}/images/m_pic2.jpg" width="156" height="103"></td>
	        </tr>
	    </table></td>
	  </tr>
	</table>

</body>
</html>
