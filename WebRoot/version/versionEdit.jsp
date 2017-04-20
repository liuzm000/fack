<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/version/taglibs.jsp"%>
<html>
<head>
<title>软件版本添加</title>
<%@ include file="/version/meta.jsp"%>
<link href="../css/main.css" rel="stylesheet" type="text/css">
<script>
	function checkUpdateVersionForm() {
		var svName = $("#svName").val();
		var svVersion = $("#svVersion").val();
		var checkValue = $("#checkboxAttr");
		var svForceupdate = $("#svForceupdate");
		var theSvFile = $("#theSvFile").val();
		var thePreviewFile1 = $("#thePreviewFile1").val();
		var thePreviewFile2 = $("#thePreviewFile2").val();
		if (checkValue.attr("checked")) {
			svForceupdate.val("0");
		} else {
			svForceupdate.val("1");
		}
		if ($.trim(svName) == "") {
			alert("软件版本名不能为空!");
			$("#svName").focus();
			return;
		}
		if ($.trim(svVersion) == "") {
			alert("版本号不能为空!");
			$("#svVersion").focus();
			return;
		}
		if (!checkFileType(theSvFile)) {
			alert("版本文件类型必须为‘apk|APK|bpk|BPK’类型!");
			$("#theSvFile").focus();
			return;
		}
		if (thePreviewFile1 != "" && thePreviewFile1 != null) {
			if (!checkImgType(thePreviewFile1)) {
				alert("预览1文件类型必须为‘jpg|JPG|png|PNG|jpeg|JPEG|bmp|BMP|gif|GIF’类型!");
				$("#thePreviewFile1").focus();
				return;
			}
		}
		if (thePreviewFile2 != "" && thePreviewFile2 != null) {
			if (!checkImgType(thePreviewFile2)) {
				alert("预览2文件类型必须为‘jpg|JPG|png|PNG|jpeg|JPEG|bmp|BMP|gif|GIF’类型!");
				$("#thePreviewFile2").focus();
				return;
			}
		}
		editForm.action = "<c:url value='/version/operator.do'>"
				+ "<c:param name='action' value='updateVersion'/>"
				+ "<c:param name='stId' value='${stId}'/>"
				+ "<c:param name='stName' value='${stName}'/>" + "</c:url>";
		editForm.submit();
	}

	function checkFileType(theSvFile) {
		var type = theSvFile.substring(theSvFile.lastIndexOf(".") + 1);
		if (type != "apk" && type != "APK" && type != "bpk" && type != "BPK") {
			return false;
		}
		return true;
	}

	function checkImgType(img) {
		var imgtype = img.substring(img.lastIndexOf(".") + 1);
		if (imgtype != "jpg" && imgtype != "JPG" && imgtype != "png"
				&& imgtype != "PNG" && imgtype != "jpeg" && imgtype != "JPEG"
				&& imgtype != "BMP" && imgtype != "bmp" && imgtype != "gif"
				&& imgtype != "GIF") {
			return false;
		}
		return true;
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
				src="../images/icon/baobiao.gif"> <c:choose>
					<c:when test="${empty svVo.svId}">
						<span class="title"><strong>软件版本管理</strong>- 添加软件版本</span>
					</c:when>
					<c:otherwise>
						<span class="title"><strong>软件版本管理</strong>- 修改软件版本</span>
					</c:otherwise>
				</c:choose></td>
			<td width="3%" height="36" align="right"
				background="../images/main/all_t_bg.gif"><img
				src="../images/main/all_t_r.gif"></td>
		</tr>

		<!-- 中间部分 -->
		<form id="editForm" name="editForm" method="post"
			action="<c:url value="/version/operator.do">
					<c:param name="action" value="addNewVersion"/>
					<c:param name="stId" value="${stId}"/>
					<c:param name="stName" value="${stName}"/>
			  	</c:url>"
			enctype="multipart/form-data">
			<input id="svId" name="svId" type="hidden"
				value="<c:out value='${svVo.svId}'/>" />
			<tr>
				<td colspan="3" align="center" valign="top" bgcolor="#FFFFFF"
					class="allbox">
					<table width="100%" border="0" cellpadding="0" cellspacing="5"
						class="boxsearch">
						<tr>
							<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
							<td width="30%" align="right">版本名称：</td>
							<td width="30%" align="left" nowrap="nowrap"><input
								class="input" type="text" id="svName" name="svName"
								value="<c:out value='${svVo.svName}'/>" /><font color=red>*</font></td>
							<td width="20%" align="left">&nbsp;</td>
							<td width="10%" align="left">&nbsp;</td>
						</tr>
						<tr>
							<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
							<td width="30%" align="right">版本号：</td>
							<td width="30%" align="left" nowrap="nowrap"><input
								class="input" type="text" id="svVersion" name="svVersion"
								value="<c:out value='${svVo.svVersion}'/>" /></td>
							<td width="20%" align="left">&nbsp;</td>
							<td width="10%" align="left">&nbsp;</td>
						</tr>
						<tr>
							<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
							<td width="30%" align="right">描述：</td>
							<td width="30%" align="left" nowrap="nowrap"><textarea
									type="text" id="svDesc" name="svDesc" rows="3" cols="40"
									style="border: 1px solid #7F9DB9; background-color: #FFF; color: #363636; font-size: 12px; padding-left: 3px;">
									<c:out value='${svVo.svDesc}' />
								</textarea></td>
							<td width="20%" align="left">&nbsp;</td>
							<td width="20%" align="left">&nbsp;</td>
						</tr>
						<tr>
							<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
							<td width="30%" align="right">版本文件：</td>
							<td width="30%" align="left" nowrap="nowrap"><input
								type="file" id="theSvFile" name="theSvFile" size="40"
								class="input"><font color=red>*</font> <c:out
									value='${svVo.svFilePath}' /></td>
							<td width="30%" align="left" colspan="2"></td>
						</tr>
						<tr>
							<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
							<td width="30%" align="right">图片预览1上传：</td>
							<td width="30%" align="left" nowrap="nowrap"><input
								type="file" id="thePreviewFile1" name="thePreviewFile1"
								size="40" class="input"><font color=red>*</font> <c:out
									value='${svVo.previewPath1}' /></td>
							<td width="30%" align="left" colspan="2"></td>
						</tr>
						<tr>
							<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
							<td width="30%" align="right">图片预览2上传：</td>
							<td width="30%" align="left" nowrap="nowrap"><input
								type="file" id="thePreviewFile2" name="thePreviewFile2"
								size="40" class="input"><font color=red>*</font> <c:out
									value='${svVo.previewPath2}' /></td>
							<td width="30%" align="left" colspan="2"></td>
						</tr>
						<tr>
							<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
							<td width="30%" align="right">强制更新：</td>
							<td width="30%" align="left" nowrap="nowrap"><input
								type="text" id="svForceupdate" name="svForceupdate"
								value="<c:out value='${svVo.svForceupdate}'/>"
								style="display: none" /> <c:choose>
									<c:when test="${svVo.svForceupdate == 0}">
										<input type="checkbox" id="checkboxAttr"
											value="<c:out value='${svVo.svForceupdate}'/>" checked />
									</c:when>
									<c:otherwise>
										<input type="checkbox" id="checkboxAttr" value="1" />
									</c:otherwise>
								</c:choose></td>
							<td width="20%" align="left">&nbsp;</td>
							<td width="20%" align="left">&nbsp;</td>
						</tr>
						<tr>
							<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
							<td width="30%" align="right">发布类型：</td>
							<td width="30%" align="left" nowrap="nowrap"><input
								type="text" id="publishType" name="publishType" size="40"
								class="input"
								value="<c:out value='${svVo.publishType}' default="1"/>" /><font
								color=red>*</font></td>
							<td width="30%" align="left" colspan="2">默认为1：正式上线。0为预上线</td>
						</tr>
						<tr>
							<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
							<td width="30%" align="right">AppStore下载地址：</td>
							<td width="30%" align="left" nowrap="nowrap"><input
								type="text" id="downloadUrl" name="downloadUrl" size="40"
								class="input" value="<c:out value='${svVo.downloadUrl}'/>" /><font
								color=red>*</font></td>
							<td width="30%" align="left" colspan="2"></td>
						</tr>
						<tr>
							<td colspan="4" align="center" valign="top" bgcolor="#FFFFFF"
								class="allbox"><c:choose>
									<c:when test="${empty svVo.svId}">
										<INPUT type=button value="保 存" onclick="checkAddVersionForm()" />
										<INPUT type=button value="重 置"
											onclick="javascript:document.editForm.reset();" />
									</c:when>
									<c:otherwise>
										<INPUT type=button value="修 改"
											onclick="checkUpdateVersionForm()" />
									</c:otherwise>
								</c:choose> <INPUT type=button value="返回列表"
								onclick="javascript:window.history.go(-1);" /></td>
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
			<td height="26" align="right"
				background="../images/main/all_d_bg.gif"><img
				src="../images/main/all_d_r.gif"></td>
		</tr>
	</table>
</body>
</html>