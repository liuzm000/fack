<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/version/taglibs.jsp"%>
<html>
<head>
<title>软件类型添加</title>
<%@ include file="/version/meta.jsp"%>
<link href="../css/main.css" rel="stylesheet" type="text/css">
<script>
	/**
 * 软件类型修改验证
 */
function checkEditSoftTypeForm() {
	var stName = $("#stName").val();
	var checkValue = $("#checkboxAttr");
	var hasMsgPush = $("#stMsgPush");
	if (checkValue.attr("checked")) {
		hasMsgPush.val("0");
	} else {
		hasMsgPush.val("1");
	}
	if ($.trim(stName) == "") {
		alert("软件类型名称不能为空!");
		$("#stName").focus();
		return;
	}
	editForm.action = "<c:url value='/version/operator.do'>"
			+ "<c:param name='action' value='updateSoftType'/>"
			+ "<c:param name='proId' value='${proId}'/>" + "</c:url>";
	editForm.submit();
}
</script>
</head>
<body>
<table width="100%" height="99%" border="0" cellspacing="0"
	cellpadding="0">
	<!-- 头部 -->
	<tr>
		<td width="3%" height="36" align="left"
			background="../images/main/all_t_bg.gif"><img
			src="../images/main/all_t_l.gif"></td>
		<td width="94%" height="36" background="../images/main/all_t_bg.gif"><img
			src="../images/icon/baobiao.gif">
			<c:choose>
			<c:when test="${empty stVo.stId}">
			<span class="title"><strong>软件类型管理</strong>- 添加软件类型</span>
			</c:when>
			<c:otherwise>
			<span class="title"><strong>软件类型管理</strong>- 修改软件类型</span>
			</c:otherwise>
			</c:choose>
		</td>
		<td width="3%" height="36" align="right"
			background="../images/main/all_t_bg.gif"><img
			src="../images/main/all_t_r.gif"></td>
	</tr>

	<!-- 中间部分 -->
	<form id="editForm" name="editForm" method="post"
		action="<c:url value="/version/operator.do">
					<c:param name="action" value="create"/>
					<c:param name="proId" value="${proId}"/>
			  	</c:url>"
		enctype="multipart/form-data">
		<input id="stId" name="stId" type="hidden" value="<c:out value='${stVo.stId}'/>"/>
	<tr>
		<td colspan="3" align="center" valign="top" bgcolor="#FFFFFF"
			class="allbox">
		<table width="100%" border="0" cellpadding="0" cellspacing="5"
			class="boxsearch">
			<tr>
				<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
				<td width="30%" align="right">软件类型名称：</td>
				<td width="30%" align="left" nowrap="nowrap"><input
					class="input" type="text" id="stName" name="stName" value="<c:out value='${stVo.stName}'/>"/><font color=red>*</font></td>
				<td width="20%" align="left">&nbsp;</td>
				<td width="10%" align="left">&nbsp;</td>
			</tr>
			<tr>
				<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
				<td width="30%" align="right">软件英文名称：</td>
				<td width="30%" align="left" nowrap="nowrap"><input
					class="input" type="text" id="stEngName" name="stEngName" value="<c:out value='${stVo.stEngName}'/>"/></td>
				<td width="20%" align="left">&nbsp;</td>
				<td width="10%" align="left">&nbsp;</td>
			</tr>
			<tr>
				<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
				<td width="30%" align="right">描述：</td>
				<td width="30%" align="left" nowrap="nowrap"><textarea
					type="text" id="stDesc" name="stDesc" rows="3" cols="40"
					style="border:1px solid #7F9DB9;
				background-color:#FFF;
				color:#363636;
				font-size:12px;
				padding-left:3px;"><c:out value='${stVo.stDesc}'/></textarea></td>
				<td width="20%" align="left">&nbsp;</td>
				<td width="20%" align="left">&nbsp;</td>
			</tr>
			<tr>
				<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
				<td width="30%" align="right">图标文件：</td>
				<td width="30%" align="left" nowrap="nowrap"><input type="file"
					id="theStFile" name="theStFile" size="40" class="input" onpropertychange="viewIcon('stFile')"><font
					color=red>*</font></td>
				<td width="30%" align="left" colspan="2"><img id="iconImg"
					src="<c:out value='${root}'/>/download<c:out value='${stVo.stIconPath}'/>" width="54" height="39" <c:if test="${empty stVo.stIconPath}">style="display:none"</c:if>/>
					</td>
			</tr>
			<tr>
				<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
				<td width="30%" align="right">列表图标文件：</td>
				<td width="30%" align="left" nowrap="nowrap"><input type="file"
					id="theListStFile" name="theListStFile" size="40" class="input" onpropertychange="viewIcon('listStFile')"><font
					color=red>*</font></td>
				<td width="30%" align="left" colspan="2"><img id="listIconImg"
					src="<c:out value='${root}'/>/download<c:out value='${stVo.stListIconPath}'/>" width="54" height="39" <c:if test="${empty stVo.stListIconPath}">style="display:none"</c:if>/>
					</td>
			</tr>
			<tr>
				<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
				<td width="30%" align="right">消息类型：</td>
				<td width="30%" align="left" nowrap="nowrap">
					<select name="stMsgType">
						<option value="0" <c:if test='${stVo.stMsgType == 0}'>selected</c:if>>插件消息</option>
						<option value="1" <c:if test='${stVo.stMsgType == 1}'>selected</c:if>>文本消息</option>
						<option value="3" <c:if test='${stVo.stMsgType == 3}'>selected</c:if>>浏览器消息</option>
					</select>
				</td>
				<td width="30%" align="left" colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
				<td width="30%" align="right">消息推送功能：</td>
				<td width="30%" align="left" nowrap="nowrap">
					<input type="text" id="stMsgPush" name="stMsgPush" value="<c:out value='${stVo.stMsgPush}'/>" style="display:none"/>
					<c:choose>
					<c:when test="${stVo.stMsgPush == 0}">
					<input type="checkbox" id="checkboxAttr" value="<c:out value='${stVo.stMsgPush}'/>" checked/>
					</c:when>
					<c:otherwise>
					<input type="checkbox" id="checkboxAttr" value="1"/>
					</c:otherwise>
					</c:choose>
				</td>
				<td width="20%" align="left">&nbsp;</td>
				<td width="20%" align="left">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4" align="center" valign="top" bgcolor="#FFFFFF"
					class="allbox">
					<c:choose>
					<c:when test="${empty stVo.stId}">
					<INPUT type=button value="保 存"	onclick="checkAddSoftTypeForm()" /> 
					<INPUT type=button value="重 置"	onclick="javascript:document.editForm.reset();" /> 
					</c:when>
					<c:otherwise>
					<INPUT type=button value="修 改"	onclick="checkEditSoftTypeForm()" /> 
					</c:otherwise>
					</c:choose>
					<INPUT	class=denglu1 type=button value="返回列表" onclick="javascript:window.history.go(-1);" />
				</td>
			</tr>
		</table>
		</td>
	</tr>

	</form>
	<!-- 尾部 -->
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
