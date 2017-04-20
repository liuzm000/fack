<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/version/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>软件类型-截图添加</title>
		<%@ include file="/version/meta.jsp"%>
		<link href="../css/global.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
		function doSaveStPreview(stId){
			var thePreFile = $("#theStPreviewFile").val();
			if ($.trim(thePreFile) == "") {
				alert("请上传一个图片文件!");
				$("#theStPreviewFile").focus();
				return;
			}
			editForm.submit();
		}
		function returnToStPreviewList(stId,proId){
			var url = this.requestUrl();
			window.location = url + "/version/operator.do?action=doListStPreview&stId=" + stId + "&proId=" + proId;
		}
		</script>
		<style type="text/css">
		.listTab td{
			white-space:nowrap;
		}
		</style>
	</head>
	<body>
		<div class="top">
			<div class="topright"></div>
			<div class="topleft"></div>
			<img src="../images/icon/shuju.gif" /><span class="title">应用预览图 -- 添加</span>
		</div>

		<div class="tmiddle">
		    &nbsp;
			<div class="boxsearch">
				<div class="boxsearch2">
					<div class="grayline"></div>
					<a href="javascript:doSaveStPreview('<c:out value="${stVo.stId}" />')"><img alt="保存" src="../images/buttom/buttom_baocun.gif" />
					</a>&nbsp;&nbsp;
					<a href="javascript:returnToStPreviewList('<c:out value="${stId}" />','<c:out value="${proId}" />')"><img	alt="返回" src="../images/buttom/buttom_fanhui.gif" />
					</a>
				</div>
			</div>
			<div class="tablebox">
				<form id="editForm" name="editForm" method="post" action="<c:url value="/version/operator.do">
																				<c:param name="action" value="doSaveStPreview"/>
																				<c:param name="stId" value="${stId}"/>
																		  	</c:url>"
						enctype="multipart/form-data">
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab">
					<tr>
						<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
						<td width="30%" align="right">ST_ID：</td>
						<td width="30%" align="left" nowrap="nowrap"><c:out value="${stId}"/></td>
						<td width="20%" align="left">&nbsp;</td>
						<td width="10%" align="left">&nbsp;</td>
					</tr>
					<tr>
						<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
						<td width="30%" align="right">预览图：</td>
						<td width="30%" align="left" nowrap="nowrap"><input type="file"
							id="theStPreviewFile" name="theStPreviewFile" size="40" class="input"><font
							color=red>*</font></td>
						<td width="30%" align="left" colspan="2"></td>
					</tr>
				</table>
				</form>
			</div>
		</div>

		<div class="bottom">
			<div class="bottomright"></div>
			<div class="bottomleft"></div>
		</div>
	</body>
</html>