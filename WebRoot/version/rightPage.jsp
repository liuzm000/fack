<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/version/taglibs.jsp"%>
<html>
<head>
<title>软件类型列表����У԰</title>
<%@ include file="/version/meta.jsp"%>
<link href="css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <!-- 头部 -->
  <tr>
    <td width="3%"  height="36"  align="left" background="images/main/all_t_bg.gif"><img src="images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="images/main/all_t_bg.gif"><img src="images/icon/baobiao.gif"><span class="title"><strong>软件类型管理</strong> - 查看列表</span></td>
    <td width="3%"  height="36"  align="right" background="images/main/all_t_bg.gif"><img src="images/main/all_t_r.gif" ></td>
  </tr>
  <!-- 按钮部分 -->
  <tr height="30">
  	<td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
  	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab">
  		<tr>
  			<td width="88%">&nbsp;</td>
  			<td width="*"><INPUT class="input" type="button" value="添加新类型" onclick="addNewSoftType(<c:out value='${proId}'/>)"/></td>
  			<td width="2%">&nbsp;</td>
  		</tr>
  	</table>
  	</td>
  </tr>
  <!-- 列表部分 -->
  <tr>
  	<td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
  	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab">
  		<tr>
           <th colspan="9">软件类型列表</th>
        </tr>
     	<tr>
          <td width="5%" align="center"><b>ID</td>
          <td width="10%" align="center"><b>图标</td>
          <td width="10%" align="center"><b>列表图标</td>
          <td width="10%" align="center"><b>软件类型名</td>
          <td width="10%" align="center"><b>软件英文名</td>
          <td width="10%" align="center"><b>消息推送功能(有/无)</td>
          <td width="15%" align="center"><b>描述</td>
          <td width="15%" align="center"><b>添加时间</td>
          <td width="25%" align="center"><b>操作</td>
        </tr>
        <c:forEach items="${softTypeList}" var="proList">
        <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'" align="center">
          <td align="center" id="stId"><c:out value="${proList.stId}"/></td>
          <td align="center"><img src="<c:out value="${root}"/>/download<c:out value='${proList.stIconPath}'/>" width="70" height="70" /></td>
          <td align="center"><img src="<c:out value="${root}"/>/download<c:out value='${proList.stListIconPath}' />" width="70" height="70"/></td>
          <td align="center"><c:out value="${proList.stName}"/></td>
          <td align="center"><c:out value="${proList.stEngName}"/></td>
          <td align="center">
          <c:choose>
          	<c:when test="${proList.stMsgPush == 0}">
          		有
          		<c:choose>
          		<c:when test="${proList.stMsgType == 0}">
          			(插件消息)
          		</c:when>
          		<c:when test="${proList.stMsgType == 1}">
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
          </td>
          <td align="center"><c:out value="${proList.stDesc}"/></td>
          <td align="center"><fmt:formatDate value="${proList.stCreatetime}" type="date" pattern="yyyy-MM-dd"/></td>
          <td align="center">
          <a href="javascript:doEditSoftType('<c:out value="${proList.stId}"/>','<c:out value="${proId}"/>')" class="redfontvha">修改</a>
          <a href="javascript:doMatchPhoneType('<c:out value="${proList.stId}"/>','<c:out value="${proId}"/>')" class="redfontvha">适配</a>
          <!-- <a href="javascript:doMatchTerUserGroup('<c:out value="${proList.stId}"/>','<c:out value="${proId}"/>')" class="redfontvha">规则</a> -->
          <a href="javascript:showVersionList('<c:out value="${proList.stId}"/>','<c:out value="${proList.stName}"/>')" class="redfontvha">版本</a>
          </td>
        </tr>
        </c:forEach>
  	</table>
  	<c:set var="url" value="/spManage.do?action=showSoftTypeList"/>
	<c:set var="flag" value="st"/>
	<%@ include file="/version/pagenation.jsp"%>
  </tr>
  <!-- 尾部 -->
  <tr>
    <td height="26" align="left"background="images/main/all_d_bg.gif"><img src="images/main/all_d_l.gif" ></td>
    <td height="26" background="images/main/all_d_bg.gif">&nbsp;</td>
    <td height="26" align="right" background="images/main/all_d_bg.gif"><img src="images/main/all_d_r.gif" ></td>
  </tr>
</table>
</body>
</html>