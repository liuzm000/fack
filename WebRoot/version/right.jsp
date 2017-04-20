<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/version/taglibs.jsp"%>
<html>
<head>
<title>集团移百管理平台</title>
<%@ include file="/version/meta.jsp"%>
<link href="../css/main.css" rel="stylesheet" type="text/css">
</head>

<body style="padding-top:5px;padding-bottom:3px;">
<table width="100%" height="99%" border="0" cellspacing="0"
	cellpadding="0">
	<tr>
		<td width="3%" height="36" align="left"
			background="../images/main/all_t_bg.gif"><img
			src="../images/main/all_t_l.gif"></td>
		<td width="94%" height="36" background="../images/main/all_t_bg.gif"><img
			src="../images/icon/home.gif" width="14" height="14"><span
			class="title">管理中心</span></td>
		<td width="3%" height="36" align="right"
			background="../images/main/all_t_bg.gif"><img
			src="../images/main/all_t_r.gif"></td>
	</tr>
	<tr>
		<td colspan="3" align="center" valign="top" bgcolor="#FFFFFF"
			class="allbox">
		<table width="96%" border="0" align="center" cellpadding="0"
			style="margin-top:10 ">
			<tr>
				<td align="left"><font class="orange"><c:out
					value="${sessionScope.account}" /></font>您好，欢迎进入管理平台！</td>
			</tr>
		</table>
		</td>
	</tr>



	<tr>
		<td height="26" align="left" background="../images/main/all_d_bg.gif"><img
			src="../images/main/all_d_l.gif"></td>
		<td height="26" background="../images/main/all_d_bg.gif">&nbsp;</td>
		<td height="26" align="right" background="../images/main/all_d_bg.gif"><img
			src="../images/main/all_d_r.gif"></td>
	</tr>
</table>


</body>

</html>
