<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/version/taglibs.jsp"%>
<html>
<head>
<title>适配终端����У԰</title>
<%@ include file="/version/meta.jsp"%>
<link href="../css/main.css" rel="stylesheet" type="text/css">
<script>
	//初始化匹配值的终端机型。匹配过的就勾选。
	$(document).ready(function() {
		var cArr = $(":checkbox");
		var matchedIds = "<c:out value='${matchedTerIds}'/>";
		for(var i=0;i<cArr.length;i++){
			var idArr = matchedIds.split(",");
			for(var j=0;j<idArr.length;j++){
				if($(cArr[i]).val() == idArr[j]){
					$(cArr[i]).attr("checked","true");
				}
			}
		}
	});

	function updateMatch(obj,obj2) {
		var addUrl = this.requestUrl();
		var selectedIds = "";// 选中的ID集合
		var canceledIds = $("#cancelIds").val();// 取消的ID集合
		var stId = obj;
		var proId = obj2;
		var cArr = $(":checkbox[checked=true]");
		for(var i=0;i<cArr.length;i++){
			var terId = cArr[i].value;
			if(selectedIds == ""){
				selectedIds = terId;
			}else{
				selectedIds = selectedIds + "," + terId;
			}
		}
		//alert(selectedIds);
		var action = "/version/operator.do?action=updateMatch&selectedIds="
				+ selectedIds + "&canceledIds=" + canceledIds + "&stId=" + stId + "&proId=" + proId;
		$.ajax({
					url : addUrl + action,
					dataType : "html",
					cache : false,
					success : function(html) {
						if (html == "SUCCESS") {
							alert("保存成功！");
						} else if (html == "NOCHANGE"){
							alert("保存无变化！");
						}else{
							alert("保存失败！");
						}
					}
				});
	}
	
	function selectAll(){
		var selectAll = $("#selectAll");
		if(selectAll.attr("checked") == true){
			var cArr = $(":checkbox");
			for(var i=0;i<cArr.length;i++){
				$(cArr[i]).attr("checked","true");
			}
		}else{
			var cArr2 = $(":checkbox[checked=true]");
			for(var j=0;j<cArr2.length;j++){
				$(cArr2[j]).attr("checked","");
			}
		}
	}
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
  			<td width="90%"><font style="font-family: 微软雅黑;">软件类型：<font color="coral"><c:out value="${stVo.stName}"/></font></font></td>
  			<td width="5%"><INPUT class="input" type="button" value="保存" onclick="updateMatch('<c:out value='${stVo.stId}'/>','<c:out value='${proId}'/>')" style="cursor: pointer;"/></td>
  			<td width="5%"><INPUT class="input" type="button" value="返回" onclick="returnStList(<c:out value='${proId}'/>)" style="cursor: pointer;"/></td>
  		</tr>
  	</table>
  	</td>
  </tr>
  <!-- 软件类型信息 -->
  <tr>
  	<td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
  	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab">
  		<tr>
           <th colspan="9">软件类型信息</th>
        </tr>
     	<tr>
          <td width="5%" align="center"><b>ID</td>
          <td width="10%" align="center"><b>图标</td>
          <td width="10%" align="center"><b>软件类型名</td>
          <td width="10%" align="center"><b>软件英文名</td>
          <td width="10%" align="center"><b>消息推送功能(有/无)</td>
          <td width="15%" align="center"><b>描述</td>
          <td width="15%" align="center"><b>添加时间</td>
        </tr>
        <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'" align="center">
          <td align="center"><c:out value="${stVo.stId}"/></td>
          <td align="center"><img src="<c:out value="${root}"/>/download<c:out value='${stVo.stIconPath}'/>" width="70" height="70"/></td>
          <td align="center"><c:out value="${stVo.stName}"/></td>
          <td align="center"><c:out value="${stVo.stEngName}"/></td>
          <td align="center">
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
          </td>
          <td align="center"><c:out value="${stVo.stDesc}"/></td>
          <td align="center"><fmt:formatDate value="${stVo.stCreatetime}" type="date" pattern="yyyy-MM-dd"/></td>
        </tr>
  	</table>
  	</td>
  </tr>
  <!-- 列表部分 -->
  <tr>
  	<td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
  	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab">
  		<tr>
           <th colspan="9">终端信息表</th>
        </tr>
     	<tr>
          <td width="10%" align="center"><b>厂商</td>
          <td width="90%" align="left"><b><input id="selectAll" name="selectAll" type="checkbox" value="0" onclick="selectAll()">勾选机型(全选)</input>
        </tr>
        <c:forEach items="${faclist}" var="facList">
        <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'" align="center">
          <td align="center"><c:out value="${facList.facName}"/></td>
          <td align="left">
          	<c:forEach items="${facList.terList}" var="terList">
          		<input type="checkbox" value="<c:out value='${terList.terId}'/>" <c:if test="">checked</c:if>><c:out value="${terList.terName}"/></input>
          	</c:forEach>
          </td>
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