<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/version/taglibs.jsp"%>
<html>
<head>
<title>软件类型列表</title>
<%@ include file="/version/meta.jsp"%>
<link href="../css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
<table width="100%" height="99%" border="0" cellspacing="0" cellpadding="0">
  <!-- 头部 -->
  <tr>
    <td width="3%"  height="36"  align="left" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="../images/main/all_t_bg.gif"><img src="../images/icon/baobiao.gif"><span class="title"><strong>软件版本管理</strong> - 查看版本列表</span></td>
    <td width="3%"  height="36"  align="right" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_r.gif" ></td>
  </tr>
  <!-- 按钮部分 -->
  <tr height="30">
  	<td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
  	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab">
  		<tr>
  			<td width="88%"><font style="font-family: 微软雅黑;">软件类型：<font color="coral"><c:out value="${stName}" escapeXml="true"/></font></font></td>
  			<td width="*"><INPUT class="input" type="button" value="添加新版本" onclick="addNewVersion('<c:out value='${stId}'/>','<c:out value='${stName}'/>')" style="cursor: pointer;"/></td>
  			<td width="2%"><INPUT class="input" type="button" value="返回" onclick="javascript:window.history.go(-1);" style="cursor: pointer;"/></td>
  		</tr>
  	</table>
  	</td>
  </tr>
  <!-- 列表部分 -->
  <tr>
  	<td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
  	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab">
  		<tr>
           <th colspan="10">软件版本列表</th>
        </tr>
     	<tr>
          <td width="5%" align="center"><b>ID</td>
          <td width="10%" align="center"><b>版本号</td>
          <td width="10%" align="center"><b>版本名</td>
          <td width="15%" align="center"><b>版本路径</td>
          <td width="8%" align="center"><b>强制更新(是/否)</td>
          <td width="10%" align="center"><b>描述</td>
          <td width="10%" align="center"><b>状态</td>
          <td width="10%" align="center"><b>添加时间</td>
          <td width="10%" align="center"><b>发布类型</td>
          <td width="12%" align="center"><b>操作</td>
        </tr>
        <c:forEach items="${verList}" var="verList">
        <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'" align="center">
          <td align="center"><c:out value="${verList.svId}"/></td>
          <td align="center" style="word-break:break-all;"><c:out value="${verList.svVersion}"/></td>
          <td align="center" style="word-break:break-all;"><c:out value="${verList.svName}"/></td>
          <td align="center" style="word-break:break-all;"><c:out value="${verList.svFilePath}"/></td>
          <td align="center">
          <c:choose>
          	<c:when test="${verList.svForceupdate == 0}">
          		是
          	</c:when>
          	<c:otherwise>
				否
			</c:otherwise>
          </c:choose>
          </td>
          <td align="center" style="word-break:break-all;"><c:out value="${verList.svDesc}"/></td>
          <td align="center" style="word-break:break-all;">
          <c:choose>
          	<c:when test="${verList.svStatus == 1}">
          		未审核
          	</c:when>
          	<c:when test="${verList.svStatus == 2}">
          		审核通过
          	</c:when>
          	<c:when test="${verList.svStatus == 4}">
          		暂停
          	</c:when>
          	<c:otherwise>
				审核未通过
			</c:otherwise>
          </c:choose>
          </td>
          <td align="center"><fmt:formatDate value="${verList.svCreatetime}" type="date" pattern="yyyy-MM-dd"/></td>
          <td align="center" style="word-break:break-all;">
          <c:choose>
          	<c:when test="${verList.publishType == 0}">
          		<font color="red">预上线</font>
          	</c:when>
          	<c:otherwise>
				<font color="green">正式上线</font>
			</c:otherwise>
          </c:choose>
          </td>
          <td align="center">
          <a href="javascript:doMatchTerUserGroupForVersion('<c:out value="${verList.svId}"/>','<c:out value="${stId}"/>','<c:out value="${stName}"/>')" class="redfontvha">规则</a>
          <a href="javascript:editVersion('<c:out value="${verList.svId}"/>','<c:out value='${stId}'/>','<c:out value='${stName}'/>','<c:out value='${verList.svStatus}'/>')" class="redfontvha">修改</a>
          <a href="javascript:deleteVersion('<c:out value="${verList.svId}"/>','<c:out value='${stId}'/>','<c:out value='${stName}'/>','<c:out value='${verList.svStatus}'/>')" class="redfontvha">删除</a>
          </td>
        </tr>
        </c:forEach>
  	</table>
  	<c:set var="url" value="/version/operator.do?action=showVersionList"/>
	<c:set var="flag" value="sv"/>
	<%@ include file="/version/pagenation.jsp"%>
  	</td>
  </tr>
  <!-- 尾部 -->
  <tr>
    <td height="26" align="left"background="../images/main/all_d_bg.gif"><img src="../images/main/all_d_l.gif" ></td>
    <td height="26" background="../images/main/all_d_bg.gif">&nbsp;</td>
    <td height="26" align="right" background="../images/main/all_d_bg.gif"><img src="../images/main/all_d_r.gif" ></td>
  </tr>
</table>
</body>
</html>