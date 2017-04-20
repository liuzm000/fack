<html>
<head><#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>MISMP</title>
<link href="${request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
<script language="JavaScript"  type="text/javascript" src="${request.contextPath}/javascript/common/calendar.js"></script>
<script language="JavaScript"  type="text/javascript" src="${request.contextPath}/javascript/common/jquery1.2.6.js"></script>
<script language="JavaScript"  type="text/javascript" src="${request.contextPath}/javascript/common/base.jsp"></script>
<script language="JavaScript"  type="text/javascript" src="${request.contextPath}/javascript/common/alex.js"></script>
<script language="JavaScript" type="text/JavaScript">
	//提交审核
	function checkForm(){
		var tgName = document.getElementById("terUserGroupVO.tgName");
		if ($.trim(tgName.value) == "") {
			alert("群组名不能为空!");
			tgName.focus();
			return;
		}
		if(!checkTextArea()){
			return;
		}
		editForm.submit();
	}
	
	//验证是否含有非法字符
	function checkTextArea(){
		var friendsNum = document.getElementById("terUserGroupVO.tgPhone").value;
		var numsArr = new Array();
		if(friendsNum != null && friendsNum != ""){
			numsArr = friendsNum.split(",");
			for(var i = 0;i < numsArr.length;i++){
				var nums2Arr = numsArr[i].split("-");
				for(var j = 0;j < nums2Arr.length;j++){
					if(!checkMobile(nums2Arr[j])){
						window.alert("号码段号码不是电信号码！");
						return false;
					}
					if(!checkLength(nums2Arr[j])){
						window.alert("号码段号码不能超过11位！");
						return false;
					}
					if(!checkIsInt(nums2Arr[j])){
						window.alert("号码段号码中包含非法字符！");
						return false;
					}
				}
				if(nums2Arr.length > 1){
					if(!checkNumSection(numsArr[i])){
						window.alert("号码段号码中【"+ numsArr[i] +"】包含不一致的号码段！");
						return false;
					}
				}
				if(nums2Arr.length > 1){
					if(!checkNumsCompare(numsArr[i])){
						window.alert("号码段号码段【"+ numsArr[i] +"】设置错误！");
						return false;
					}
				}
				if(!checkSameNums(friendsNum)){
					window.alert("号码段号码段设置包含重复的号码！");
					return false;
				}
			}
		}else{
			window.alert("号码段号码资源不能为空！");
			return false;
		}
		return true;
	}

	//检查号码长度
	function checkLength(str){
		if(str.length > 11){
			return false;
		}
		return true;
	}
	//检查是否是数字
	function checkIsInt(str){
		var numArr = str;
	    if(isNaN(numArr)){
	        return false;
	    }
	    if(numArr.indexOf(".") != -1){
	    	return false;
	    }
	    return true;
	}
	
	//检查号码段区号是否一致
	function checkNumSection(numSection){
		var numsSingal = numSection.split("-");
		var zoneCode0 = "";
		var zoneCode1 = "";
		if(numsSingal.length > 1){
			zoneCode0 = numsSingal[0].substring(0,3);
			zoneCode1 = numsSingal[1].substring(0,3);
			if(zoneCode0 != zoneCode1){
				return false;
			}
		}
		return true;
	}
	//检查号码段号码重复
	function checkSameNums(textArea){
		if(textArea != null && textArea != ""){
			numsArr = textArea.split(",");
			var numBegin = "";
			var numEnd = "";
			for(var i = 0;i < numsArr.length;i++){
				var numsStr = numsArr[i].split("-");
				if(numsStr.length > 1){
					numBegin = numsStr[0];
					numEnd = numsStr[1];
				}else{
					numBegin = numEnd = numsStr[0];
				}
				for(var j = 0;j < numsArr.length;j++){
					if(i != j){
						var numsStr2 = numsArr[j].split("-");
						if(numsStr2.length > 1){
							numBegin2 = numsStr2[0];
							numEnd2 = numsStr2[1];
						}else{
							numBegin2 = numEnd2 = numsStr2[0];
						}
						if(numBegin >= numBegin2 && numBegin <= numEnd2 || numEnd >= numBegin2 && numEnd <= numEnd2){
							return false;
						}
					}
				}
			}
			return true;
		}
	}
	//检查号段是否后面后面号码大于前面号码。
	function checkNumsCompare(numSection){
		var numsSingal = numSection.split("-");
		var cityCode0 = "";
		var cityCode1 = "";
		var num0 = "";
		var num1 = "";
		if(numsSingal.length > 1){
			num0 = numsSingal[0].substring(3);
			num1 = numsSingal[1].substring(3);
			if(parseInt(num1,10) <= parseInt(num0,10)){
				return false;
			}
		}
		return true;
	}
	
	function checkMobile(mobile){
	  	var reg0=/^133\d{8}$/;   
	 	var reg1=/^153\d{8}$/;  
	 	var reg2=/^180\d{8}$/; 
	 	var reg3=/^189\d{8}$/;
	 	var my=false;
	 	if (reg0.test(mobile))my=true;
	 	if (reg1.test(mobile))my=true;
	 	if (reg2.test(mobile))my=true;
	 	if (reg3.test(mobile))my=true;
	 	return my;
	}
</script>
</head>

<body style="padding-top:5px;padding-bottom:3px;">
<table width="100%" height="99%" border="0" cellspacing="0"
	cellpadding="0">
	<!-- 头部 -->
	<tr>
		<td width="3%" height="36" align="left"
			background="../images/main/all_t_bg.gif"><img
			src="../images/main/all_t_l.gif"></td>
		<td width="94%" height="36" background="../images/main/all_t_bg.gif"><img
			src="../images/icon/baobiao.gif">
			<span class="title"><strong>终端用户群组管理</strong>- 添加群组</span>
		</td>
		<td width="3%" height="36" align="right"
			background="../images/main/all_t_bg.gif"><img
			src="../images/main/all_t_r.gif"></td>
	</tr>
  <!-- 中间部分 -->
	<form id="editForm" name="editForm" method="post" action="${request.contextPath}/customer/updateTerUserGroup.action">
	<input type="hidden" name="terUserGroupVO.tgId" id="terUserGroupVO.tgId" value="${vo.tgId}"/>
	<tr>
		<td colspan="3" align="center" valign="top" bgcolor="#FFFFFF"
			class="allbox">
		<table width="100%" border="0" cellpadding="0" cellspacing="5"
			class="boxsearch">
			<tr>
				<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
				<td width="20%" align="right">群组名称：</td>
				<td width="40%" align="left" nowrap="nowrap"><input
					class="input" type="text" id="terUserGroupVO.tgName" name="terUserGroupVO.tgName" value="${vo.tgName}"/><font color=red>*</font></td>
				<td width="20%" align="left">&nbsp;</td>
				<td width="10%" align="left">&nbsp;</td>
			</tr>
			<tr>
				<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
				<td width="20%" align="right">描述：</td>
				<td width="40%" align="left" nowrap="nowrap"><textarea
					type="text" id="terUserGroupVO.tgDesc" name="terUserGroupVO.tgDesc" rows="3" cols="40"
					style="border:1px solid #7F9DB9;
				background-color:#FFF;
				color:#363636;
				font-size:12px;
				padding-left:3px;">${vo.tgDesc}</textarea></td>
				<td width="20%" align="left">&nbsp;</td>
				<td width="10%" align="left">&nbsp;</td>
			</tr>
			<tr>
				<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
				<td width="20%" align="right">号码段设置：</td>
				<td width="40%" align="left" nowrap="nowrap"><textarea
					type="text" id="terUserGroupVO.tgPhone" name="terUserGroupVO.tgPhone" rows="3" cols="40"
					style="border:1px solid #7F9DB9;
				background-color:#FFF;
				color:#363636;
				font-size:12px;
				padding-left:3px;">${vo.tgPhone}</textarea></td>
				<td width="30%" align="left" colspan="2">号段之间以“-”表示区间，不同号段之间以“逗号（，）”分隔开！<br>例如：13312345699,13312345601-13312345610</td>
			</tr>
    		<tr>
				<td colspan="4" align="center" valign="top" bgcolor="#FFFFFF"
					class="allbox">
					<INPUT type=button value="保 存"	onclick="checkForm()" /> 
					<INPUT type=button value="重 置"	onclick="javascript:document.editForm.reset();" /> 
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
