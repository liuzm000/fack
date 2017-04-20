<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/version/taglibs.jsp"%>
<html>
<head>
<title>适配终端����У԰</title>
<%@ include file="/version/meta.jsp"%>
<link href="../css/main.css" rel="stylesheet" type="text/css">
<script>
	//初始化匹配值的群组。匹配过的就勾选。
	$(document).ready(function() {
		var cArr = $(":checkbox");
		var matchedIds = "<c:out value='${matchedVersionGroups}'/>";
		for(var i=0;i<cArr.length;i++){
			var idArr = matchedIds.split(",");
			for(var j=0;j<idArr.length;j++){
				if($(cArr[i]).val() == idArr[j]){
					$(cArr[i]).attr("checked","true");
				}
			}
		}
	});
</script>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<!-- 隐藏字段 -->
	<input id="terIds" name="terIds" type="hidden"/><!-- 已选ID集合 -->
	<input id="cancelIds" name="cancelIds" type="hidden"/><!-- 取消ID集合 -->
  <!-- 头部 -->
  <tr>
    <td width="3%"  height="36"  align="left" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="../images/main/all_t_bg.gif"><img src="../images/icon/baobiao.gif"><span class="title"><strong>软件类型管理</strong> - 查看列表</span></td>
    <td width="3%"  height="36"  align="right" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_r.gif" ></td>
  </tr>
  <!-- 按钮部分 -->
  <tr height="30">
  	<td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
  	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab">
  		<tr>
  			<td width="90%"><font style="font-family: 微软雅黑;">软件类型：<font color="coral"><c:out value="${svVo.svName}"/></font></font></td>
  			<td width="5%"><INPUT class="input" type="button" value="保存" onclick="updateMatchVersionGroup(<c:out value='${svVo.svId}'/>)" style="cursor: pointer;"/></td>
  			<td width="5%"><INPUT class="input" type="button" value="返回" onclick="showVersionList('<c:out value='${stId}'/>','<c:out value='${stName}'/>')" style="cursor: pointer;"/></td>
  		</tr>
  	</table>
  	</td>
  </tr>
  <!-- 软件类型信息 -->
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
          <td width="10%" align="center"><b>上传人</td>
          <td width="12%" align="center"><b>操作</td>
        </tr>
        <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'" align="center">
          <td align="center"><c:out value="${svVo.svId}"/></td>
          <td align="center" style="word-break:break-all;"><c:out value="${svVo.svVersion}"/></td>
          <td align="center" style="word-break:break-all;"><c:out value="${svVo.svName}"/></td>
          <td align="center" style="word-break:break-all;"><c:out value="${svVo.svFilePath}"/></td>
          <td align="center">
          <c:choose>
          	<c:when test="${svVo.svForceupdate == 0}">
          		是
          	</c:when>
          	<c:otherwise>
				否
			</c:otherwise>
          </c:choose>
          </td>
          <td align="center" style="word-break:break-all;"><c:out value="${svVo.svDesc}"/></td>
          <td align="center" style="word-break:break-all;">
          <c:choose>
          	<c:when test="${svVo.svStatus == 1}">
          		未审核
          	</c:when>
          	<c:when test="${svVo.svStatus == 2}">
          		审核通过
          	</c:when>
          	<c:when test="${svVo.svStatus == 4}">
          		暂停
          	</c:when>
          	<c:otherwise>
				审核未通过
			</c:otherwise>
          </c:choose>
          </td>
          <td align="center"><fmt:formatDate value="${svVo.svCreatetime}" type="date" pattern="yyyy-MM-dd"/></td>
          <td align="center"><c:out value="${svVo.svSubmitid}"/></td>
          </tr>
  	</table>
  	</td>
  </tr>
  <!-- 列表部分 -->
  <tr>
  	<td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
  	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab"  style="word-break:break-all;">
  		<tr>
           <th colspan="9">终端用户群组信息表</th>
        </tr>
     	<tr>
     	  <td width="5%" align="center">&nbsp;</td>
          <td width="20%" align="center"><b>群组名</td>
          <td width="20%" align="center"><b>描述</td>
          <td width="55%" align="center"><b>号码段</td>
        </tr>
        <c:forEach items="${terUserGroupList}" var="terUserGroupList">
        <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'" align="center">
          <td align="center"><input onclick="chooseVersionGroups(this,'<c:out value="${matchedVersionGroups}"/>')" type="checkbox" value="<c:out value='${terUserGroupList.tgId}'/>" ></input></td>
          <td align="left"><c:out value="${terUserGroupList.tgName}"/></td>
          <td align="left"><c:out value="${terUserGroupList.tgDesc}"/></td>
          <td align="left"><c:out value="${terUserGroupList.tgPhone}"/></td>
        </tr>
        </c:forEach>
  	</table>
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