<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/version/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>软件类型</title>
		<%@ include file="/version/meta.jsp"%>
		<link href="../css/global.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
		function doAddStPreview(stId,proId){
			var url = this.requestUrl();
			window.location = url + "/version/operator.do?action=doAddStPreview&stId=" + stId + "&proId=" + proId;
		}
		function doDeleteStPreview(id,stId){
			var msg = "确定删除此条信息吗？";
			if (confirm(msg)) {
				var url = this.requestUrl();
				window.location = url + "/version/operator.do?action=doDeleteStPreviewById&id=" + id + "&stId=" + stId;
			}
		}
		function returnToRightPage(proId){
			var url = this.requestUrl();
			window.location = url + "/spManage.do?action=showSoftTypeList&pageNum=1&pageSize=10&proId=" + proId;
		}
		</script>
		
	</head>
	<body>
		<div class="top">
			<div class="topright"></div>
			<div class="topleft"></div>
			<img src="../images/icon/shuju.gif" /><span class="title">应用预览图 -- 管理</span>
		</div>

		<div class="tmiddle">
		    &nbsp;
			<div class="boxsearch">
				<div class="boxsearch2">
					<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab">
						<tr>
							<th width="10%" align="center"><b>ST_ID</th>
							<th width="10%" align="center"><b>图标</th>
							<th width="10%" align="center"><b>软件类型名</th>
							<th width="10%" align="center"><b>软件英文名</th>
							<%--<th width="10%" align="center"><b>消息推送功能(有/无)</th>--%>
							<th width="50%" align="center"><b>描述</th>
							<th width="10%" align="center"><b>添加时间</th>
						</tr>
						<tr onMouseOver="this.className='row_over'"	onMouseOut="this.className='row_out'" align="center">
							<td align="center"><c:out value="${stVo.stId}" /></td>
							<td align="center"><img	src="<c:out value="${root}"/>/download/<c:out value='${stVo.stIconPath}'/>" /></td>
							<td align="center"><c:out value="${stVo.stName}" /></td>
							<td align="center"><c:out value="${stVo.stEngName}" /></td>
							<%--<td align="center">
							<c:choose>
								<c:when test="${stVo.stMsgPush == 0}">
          						有
          						<c:choose>
									<c:when test="${stVo.stMsgType == 0}">
          							(插件消息)
          							</c:when>
									<c:when test="${stVo.stMsgType == 1}">
          							(文本消息)
          							</c:when>
								<c:otherwise>
								(浏览器消息)
								</c:otherwise>
								</c:choose>
								</c:when>
								<c:otherwise>
								无
								</c:otherwise>
							</c:choose>
							</td>--%>
							<td align="center"><c:out value="${stVo.stDesc}" /></td>
							<td align="center"><fmt:formatDate value="${stVo.stCreatetime}" type="date"	pattern="yyyy-MM-dd" /></td>
						</tr>
					</table>
					<div class="grayline"></div>
					<a href="javascript:doAddStPreview('<c:out value="${stVo.stId}" />','<c:out value="${proId}" />')"><img alt="新增" src="../images/buttom/buttom_xinzen.gif" />
					</a>&nbsp;&nbsp;
					<a href="javascript:returnToRightPage('<c:out value="${proId}" />')"><img	alt="返回" src="../images/buttom/buttom_fanhui.gif" />
					</a>
				</div>
			</div>
			<div class="tablebox">
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab">
					<tr>
						<th width="10%" align="center"><b>ID(标识)</th>
						<th width="50%" align="center"><b>截图</th>
						<th width="10%" align="center"><b>文件名</th>
						<th width="10%" align="center"><b>ST_ID</th>
						<th width="10%" align="center"><b>操作</th>
					</tr>
					<c:forEach items="${stPreList}" var="preList">
						<tr onMouseOver="this.className='row_over'"	onMouseOut="this.className='row_out'" align="center">
							<td align="center"><c:out value="${preList.id}" /></td>
							<td align="center"><img	src="<c:out value="${root}"/>/download<c:out value='${preList.filePath}'/>" width="240" height="320"/></td>
							<td align="center"><c:out value="${preList.fileName}" /></td>
							<td align="center"><c:out value="${preList.stId}" /></td>
							<td align="center">
							<a	href="javascript:doDeleteStPreview('<c:out value="${preList.id}"/>','<c:out value="${preList.stId}"/>')" class="redfontvha">删除</a>
							</td>
						</tr>
					</c:forEach>
				</table>
				<c:set var="url"
					value="/version/operator.do?action=doListStPreview&stId=<c:out value='${stVo.stId}'/>" />
				<c:set var="flag" value="st" />
				<%@ include file="/version/pagenation.jsp"%>
			</div>
		</div>

		<div class="bottom">
			<div class="bottomright"></div>
			<div class="bottomleft"></div>
		</div>
	</body>
</html>