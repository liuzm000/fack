<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<#assign rootPath=request.scheme+"://"+request.serverName+":"+request.serverPort+request.contextPath />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>首页推荐信息适配</title>
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../javascript/jquery-core/jquery-1.3.js"></script>
<script type="text/javascript" src="../javascript/jquery-core/jquery.blockUI.js"></script>
<script type="text/javascript">
	//初始化匹配值的终端机型。匹配过的就勾选。
	$(document).ready(function() {
		var cArr = $(":checkbox");
		var matchedIds = "${matchedTerIds?if_exists}";
		for(var i=0;i<cArr.length;i++){
			var idArr = matchedIds.split(",");
			for(var j=0;j<idArr.length;j++){
				if($(cArr[i]).val() == idArr[j]){
					$(cArr[i]).attr("checked","true");
				}
			}
		}
	});	
	
	function checkSaveMatchForm(hrId){
		var cArr = $(":checkbox[checked=true]");
		var terIdMatched = "";
		for(var i=0;i<cArr.length;i++){
			var terId = cArr[i].value;
			if( terId != 0){
				if(terIdMatched == ""){
					terIdMatched = terId;
				}else{
					terIdMatched = terIdMatched + "," + terId;
				}
			}
		}
		//alert("ids:" + terIdMatched);
		document.forms["searchForm"].action = "updateMatchHomeRecommend.action?hrId=" + hrId + "&terIdMatched=" + terIdMatched;
		document.forms["searchForm"].submit();
	}
	
	function selectAll(){
		var selectAll = $("#selectAll");
		if(selectAll.attr("checked") == true){
			var cArr = $(":checkbox[name!='selectAll']");
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

<style type="text/css">
   .listTab td {}
<!--
	.listTab tr span{
		float:left;
		width:200px;
		text-align:right;
	}
-->
</style>

</head>

<body>
<!-- TOP -->
<div class="top">
	<div class="topright"></div>
	<div class="topleft"></div>
	<img src="../images/icon/shuju.gif" /><span class="title">推荐位(3.3版)信息 - 适配</span>
</div>
<!-- TOP END -->

<!-- MIDDLE -->
<div class="tmiddle">&nbsp;
	<div class="boxsearch">
		<div class="boxsearch2">
			<form id="searchForm" name="searchForm" method="post" action="">
			<a href="javascript:checkSaveMatchForm('${vo.hrId?if_exists}')"><img src="../images/buttom/buttom_baocun.gif" /></a>
			<a href="listHomeRecommend.action"><img src="../images/buttom/buttom_fanhui.gif" /></a>
			</form>
		</div>
	</div>
	<div class="tablebox">
		<form id="detailForm" name="detailForm" method="post" action=""></form>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab">
			<tr>
				<th width="5%">&nbsp;</th>
				<th width="5%">ID</th>
				<th width="10%">主题名称</th>
				<th width="10%">主题类型</th>
				<th width="10%">分类</th>
				<th width="10%">映射属性</th>
			</tr>
			<tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
				<td align="center" style="">
				<img src="${rootPath}${vo.subjectIconUrl?if_exists}" width="224" height="79" />
				</td>
				<td align="center">${vo.hrId?if_exists}</td>
				<td align="center"><font color="blue">${vo.subjectName?if_exists}</td>
				<td align="center">
					<#if vo.subjectType == 0>无点击动作</#if>
					<#if vo.subjectType == 1>客户端内嵌页</#if>
					<#if vo.subjectType == 2>WAP页面</#if>
					<#if vo.subjectType == 3>应用详情页</#if>
					<#if vo.subjectType == 4>专题应用列表</#if>
				</td>
				<td align="center">
				<#if appTypeList?exists>
					<#assign appTypeName=vo.appTypeId?if_exists />
					<#list appTypeList as atl>
					<#if atl.appTypeId == vo.appTypeId>
						<#assign appTypeName = atl.appTypeName/>
					</#if>
					</#list>
					<#if vo.appTypeId == 0>
						<#assign appTypeName = '首页'/>
					</#if>
					<#if vo.appTypeId == -1>
						<#assign appTypeName = '专题栏'/>
					</#if>
				</#if>
				${appTypeName?if_exists}
				</td>
				<td align="center">${vo.subjectId?if_exists}</td>
			</tr>
		</table>
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab">
  		<tr>
           <th colspan="9">终端信息表</th>
        </tr>
     	<tr>
          <td width="10%" align="center"><b>厂商</td>
          <td width="90%" align="left"><b><input id="selectAll" name="selectAll" type="checkbox" value="0" onclick="selectAll()">勾选机型(全选)</input>
          </td>
        </tr>
        <#if faclist?exists>
		<#list faclist as fac>
        <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'" align="center">
          <td align="center">${fac.facName?if_exists}</td>
          <td align="left">
          	<#if fac.terList?exists>
			<#list fac.terList as ter>
          		<input type="checkbox" value="${ter.terId?if_exists}">${ter.terName?if_exists}</input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	</#list>
        	</#if>
          </td>
        </tr>
        </#list>
        </#if>
  	</table>
	</div><!-- tablebox DIV END -->
</div><!-- MIDDLE DIV END -->

<div class="bottom" align="center">
	<div class="bottomright"></div>
	<div class="bottomleft"></div>
</div>

</body>
</html>
