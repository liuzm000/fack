 <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/version/taglibs.jsp"%>
<html>
<head>
<title>校园创业---软件上传</title>
<%@ include file="/version/meta.jsp"%>
<link href="<c:out value="${root}"/>/css/main.css" rel="stylesheet" type="text/css">
<link type="text/css" href="<c:out value='${root}'/>/version/js/ui/css/ui.all.css" rel="stylesheet" />
<link type="text/css" href="<c:out value='${root}'/>/version/js/ui/css/demos.css" rel="stylesheet" />
	<script type="text/javascript" src="<c:out value='${root}'/>/version/js/ui/ui.core.js"></script>
	<script type="text/javascript" src="<c:out value='${root}'/>/version/js/ui/ui.tabs.js"></script>
	<script type="text/javascript" src="<c:out value='${root}'/>/version/js/ui/ui.draggable.js"></script>
	<script type="text/javascript" src="<c:out value='${root}'/>/version/js/ui/ui.resizable.js"></script>
	<script type="text/javascript" src="<c:out value='${root}'/>/version/js/ui/ui.dialog.js"></script>
	<script type="text/javascript" src="<c:out value='${root}'/>/version/js/ui/effects.core.js"></script>
	<script type="text/javascript" src="<c:out value='${root}'/>/version/js/ui/effects.highlight.js"></script>
	<script type="text/javascript" src="<c:out value='${root}'/>/version/js/external/bgiframe/jquery.bgiframe.js"></script>
<script>
	var uniqueImspId = false;
	var uniqueSvName = false;

	$(document).ready(function() {
		var receiver = "<c:out value='${receiver}'/>";
		receiver = hideRealNum(receiver);
		$("#realFriendsNum").text(receiver);
		var type = "<c:out value='${type}'/>";
		var softId = "<c:out value='${softId}'/>";
		if(softId == null || softId == ""){
			$("#classIdTr").css("display","block");
		}else{
			//把mobileType填入值
			var mobileType = "";
			<c:forEach items="${list}" var="list">
				if(mobileType != null && mobileType != ""){
					mobileType += "," + '<c:out value="${list.facName}"/>';
				}else{
					mobileType += '<c:out value="${list.facName}"/>';
				}
			</c:forEach>
			$("#mobileType").val(mobileType);
		}
//		if(receiver != ""){
//			$("#friendConfigCheck").attr("checked","true");
//			showText();
//		}
		if(type == "1" || type == "2"){
			$("#friendsCheckBox").css("display","block");
			$("#friendConfigCheck").attr("checked","true");
			showText();
		};
		var flag = "<c:out value='${flag}'/>";
		if(flag != ""){
			if(flag == "SUCCESS"){
				window.alert("上传成功!");
				window.open('','_self','');
				window.close();
			}else {
				window.alert("上传失败!请重新上传");
				window.location = this.request() + "/college.do?action=doUpload"
			}
		}
		//################ 初始化DIALOG ##############
		initJQueryTabs();
		$("#dialog").dialog({
			bgiframe: true,
			width: 510,
			autoOpen:false,
			modal: true,
			buttons: {
				'取消': function() {
					$(this).dialog('close');
				},
				'确定': function() {
					//处理checkbox数据，获取当前选中的tabs
					var $tabs = $('#tabs').tabs();
					var selected = $tabs.data('selected.tabs');
					if(selected == '0'){//mobile
						var pagehtml = "";
						var terminalsIds = "";
						var mobileType = "";
						//①获取brew的选项卡,将该选项卡上的所有checkbox置为checked==false
						$("#tabs-2 :checkbox[checked=true]").attr("checked",false);
						//②获取当前选项卡上的所有checked==true的checkbox的值。
						var length = $("#tabs-1 :checkbox[checked=true]").length;
						$("#tabs-1 :checkbox[checked=true]").each(function(i){
							//③在页面显式的显示所选取的值。
							var facName = $(this).parent().parent().find("td:first").text();
							var terName = $(this).next().text();
							var plat = "Mobile";
							pagehtml += "<tr class='addedByJs'><td>" + facName + "</td><td>" + terName + "</td><td>" + plat + "</td>";
							var id = $(this).val();
							//调用setFileSuffix()。
							setFileSuffix(id);
							//④将这些值组成一个以逗号隔开的字符串，存入页面hidden参数，等表单提交时处理。
							if(terminalsIds != null && terminalsIds != ""){
								terminalsIds += "," + id;
							}else{
								terminalsIds += id;
							}
							if(mobileType != null && mobileType != ""){
								mobileType += "," + terName;
							}else{
								mobileType += terName;
							}
							$("#terminalsIds").val(terminalsIds);
							$("#mobileType").val(mobileType);
							//alert($("#terminalsIds").val());
 						});
 						pagehtml += "</tr>";
 						if(length > 0){
 							$(".addedByJs").empty();
 							$("#ter-show-tab").append(pagehtml);
 							$("#ter-show-tr").css("display","block");
 						}
					}
					if(selected == '1'){//brew
						var pagehtml = "";
						var terminalsIds = "";
						var mobileType = "";
						$("#tabs-1 :checkbox[checked=true]").attr("checked",false);
						var length = $("#tabs-2 :checkbox[checked=true]").length;
						$("#tabs-2 :checkbox[checked=true]").each(function(i){
							var facName = $(this).parent().parent().find("td:first").text();
							var terName = $(this).next().text();
							var plat = "Brew";
							pagehtml += "<tr class='addedByJs'><td>" + facName + "</td><td>" + terName + "</td><td>" + plat + "</td>";
							var id = $(this).val();
							setFileSuffix(id);
							if(terminalsIds != null && terminalsIds != ""){
								terminalsIds += "," + id;
							}else{
								terminalsIds += id;
							}
							if(mobileType != null && mobileType != ""){
								mobileType += "," + terName;
							}else{
								mobileType += terName;
							}
							$("#terminalsIds").val(terminalsIds);
							$("#mobileType").val(mobileType);
 						});
 						pagehtml += "</tr>";
 						if(length > 0){
 							$(".addedByJs").empty();
 							$("#ter-show-tab").append(pagehtml);
 							$("#ter-show-tr").css("display","block");
 						}
					}
					$(this).dialog('close');
				}
			}
		});
	});

	//验证CLASS-ID的唯一性
	function checkUniqueIsmpId(obj){
		var ismpId = obj;
		//验证非空
		if(ismpId.length <= 0){
			$("#errorInfo").text("ERROR!CLASS-ID不能为空!");
			$("#errorInfo").css("color","red");
			return false;
		}
		//验证是否为"0x开头"
		var char2 = ismpId.substring(0,2);
		if(char2 != "0x" && char2 != "0X"){
			$("#errorInfo").text("ERROR!不是以0x或0X开头!");
			$("#errorInfo").css("color","red");
			return false;
		}
		//验证是否是10位
		if(ismpId.length != 10){
			$("#errorInfo").text("ERROR!必须是10位字符!");
			$("#errorInfo").css("color","red");
			return false;
		}
		//验证是否后8为数字
		var numCheck = ismpId.substring(2,ismpId.length);
		var reg = /^[A-Za-z0-9]+$/;
		if (!reg.test(numCheck)){
			$("#errorInfo").text("ERROR!CLASS-ID不合法!");
			$("#errorInfo").css("color","red");
		    return false;
	    }
		return true;
	}
	
	function setResult(bl){
		this.uniqueImspId = bl;
	}
	
	function checkUniqueSvName(obj){
	    //验证软件名称是否唯一
	    var addUrl = this.requestUrl();
	    //验证非空
	    var svName = $("#svName").val();
	    if ($.trim(svName) == "")
	    {
	    	$("#svNameErrorInfo").text("软件名称不能为空!");
			$("#svNameErrorInfo").css("color","red");
			return;
		}
		else{
			$("#svNameErrorInfo").text("");
		}
		//后台校验是否唯一
		var action = "/college.do?action=checkUniqueSvName&svName=" + encodeURI($.trim(svName));
	    	$.ajax({
				url : addUrl + action,
				dataType : "html",
				cache : false,
				success : function(html) {
					if (html == "TRUE") {
						uniqueSvName = true;
						$("#svNameErrorInfo").text("SUCCESS!");
						$("#svNameErrorInfo").css("color","green");
					} else if(html == "FALSE") {
						uniqueSvName = false;
						$("#svNameErrorInfo").text("已有同名软件，请修改软件名称");
						$("#svNameErrorInfo").css("color","red");
						$("#svName").focus();
					}else{
					    uniqueSvName = false;
						$("#svNameErrorInfo").text("软件名称不能为空!");
						$("#svNameErrorInfo").css("color","red");
					}
				}
			});
	
		
	}
	
	function checkUniqueIsmpId2(obj) {
		//验证数据库是否是唯一
	    var addUrl = this.requestUrl();
	    //验证非空
		if(obj.length <= 0){
			$("#errorInfo").text("ERROR!CLASS-ID不能为空!");
			$("#errorInfo").css("color","red");
			setResult(false);
			return;
		}
		//验证是否为"0x开头"
		var char2 = obj.substring(0,2);
		if(char2 != "0x" && char2 != "0X"){
			$("#errorInfo").text("ERROR!不是以0x或0X开头!");
			$("#errorInfo").css("color","red");
			setResult(false);
			return;
		}
		//验证是否是10位
		if(obj.length != 10){
			$("#errorInfo").text("ERROR!必须是10位字符!");
			$("#errorInfo").css("color","red");
			setResult(false);
			return;
		}
	    //验证是否后8为数字
		var numCheck = obj.substring(2,obj.length);
		var reg = /^[A-Za-z0-9]+$/;
		if (!reg.test(numCheck)){
			$("#errorInfo").text("ERROR!CLASS-ID不合法!");
			$("#errorInfo").css("color","red");
			setResult(false);
		    return;
	    }
	    var action = "/college.do?action=checkUniqueIsmpId&ismpId=" + obj;
	    	$.ajax({
				url : addUrl + action,
				dataType : "html",
				cache : false,
				success : function(html) {
					if (html == "TRUE") {
						$("#errorInfo").text("SUCCESS!");
						$("#errorInfo").css("color","green");
						setResult(true);
					} else if(html == "FALSE") {
						$("#errorInfo").text("ERROR!该CLASS-ID已存在!");
						$("#errorInfo").css("color","red");
						setResult(false);
					}else{
						$("#errorInfo").text("ERROR!CLASS-ID不能为空!");
						$("#errorInfo").css("color","red");
						setResult(false);
					}
				}
			});
	}
	
	function showText(){
		var textTr = $("#friendsText");
		var friendsConfig = $("#friendsConfig");
		var friendConfigCheck = $("#friendConfigCheck");
		var friendsNum = $("#friendsNum");
		if(friendConfigCheck.attr("checked")){
			textTr.css("display","");
			friendsConfig.val("1");
		}else{
			textTr.css("display","none");
			friendsConfig.val("0");
		}	
	}
	
	function checkThisForm() {
		var type = "<c:out value='${type}'/>";
		var svName = $("#svName").val();
		var stEngName = $("#stEngName").val();
		var svVersion = $("#svVersion").val();
		var preVersion = $("#preVersion").text();
		var theSvFile = $("#theSvFile").val();
		var checkValue = $("#checkboxAttr");
		var svForceupdate = $("#svForceupdate");
		var terId = $("#terId").val();
		var friendsConfig = $("#friendsConfig");
		var friendConfigCheck = $("#friendConfigCheck");
		if (checkValue.attr("checked")) {
			svForceupdate.val("1");
		} else {
			svForceupdate.val("0");
		}
		if (friendConfigCheck.attr("checked")) {
			friendsConfig.val("1");
		} else {
			friendsConfig.val("0");
		}
		if ($.trim(svName) == "") {
			alert("软件名称不能为空!");
			$("#svName").focus();
			return;
		}
		<c:if test="${empty vo}">
		//版本添加
		if(type == "1")
		{
			if(!uniqueSvName){
			alert("已有同名软件，请修改软件名称!");
			$("#svName").focus();
			return;
			}
		}
		</c:if>
		
		
		if ($.trim(stEngName) == "") {
			alert("软件英文名不能为空!");
			$("#stEngName").focus();
			return;
		}
		if ($.trim(svVersion) == "") {
			alert("版本号不能为空!");
			$("#svVersion").focus();
			return;
		}
		if(!checkVersionNum($.trim(svVersion))){
			alert("版本号不合法!");
			$("#svVersion").focus();
			return;
		}
		if(preVersion != "" && preVersion != null){
			if(!compareVersionNum(preVersion,svVersion)){
				alert("当前版本号必须大于前一版本!");
				$("#svVersion").focus();
				return;
			}
		}
		if($("#classIdTr").css("display") != "none"){
			if(!checkUniqueIsmpId($("#ismpId").val())){
				$("#ismpId").focus();
				return;
			}
			if(!this.uniqueImspId){
				$("#ismpId").focus();
				return;
			}
		}
		if ($("#ter-show-tr").css("display") == "none") {
			alert("请选择手机型号!");
			$("#terId").focus();
			return;
		}
		if ($.trim(theSvFile) == "") {
			alert("请上传一个版本文件!");
			$("#theSvFile").focus();
			return;
		}
		if(friendsConfig.val() == "1"){
			if(!checkTextArea()){
				return;
			}
		}
		
		var s = this.editForm.theSvFile.value.lastIndexOf(".");   
		var curFileSuffix = this.editForm.theSvFile.value.substring(s+1).toLowerCase();   
		if(this.editForm.fileSuffix.value != curFileSuffix)
		{
			alert("您上传的版本文件格式不符合要求，版本文件必须为" +this.editForm.fileSuffix.value　+ "格式，请核对后重新上传！");
			return;
		}
		
		editForm.submit();
	}
	
	function checkVersionNum(str){
		var regm = /^[0-9][.][0-9][.][0-9][.][0-9]$/;
		if (!str.match(regm) && str!=""){
			return false;
		}
		return true;
	}
	
	function compareVersionNum(pre,next){
		var strmInteger = pre.replace(".","").replace(".","").replace(".","");
		var strmNewInteger = next.replace(".","").replace(".","").replace(".","");
		if(strmInteger >= strmNewInteger){
		 	return false;
		}
		return true;
	}
	
	function hideRealNum(str){
		var friendsNum = str;
		var numsArr = new Array();
		if(friendsNum != null && friendsNum != ""){
			numsArr = friendsNum.split(",");
			var newNumsArr = "";
			for(var i = 0;i < numsArr.length;i++){
				var nums2Arr = numsArr[i].split("-");
				var newNums2Arr = "";
				for(var j = 0;j < nums2Arr.length;j++){
					var head = nums2Arr[j].substring(0,3);
					var middle = nums2Arr[j].substring(3,7);
					var tail = nums2Arr[j].substring(7,11);
					nums2Arr[j] = head + "****" + tail;
					if(newNums2Arr == ""){
					 	newNums2Arr = nums2Arr[j];
					}else{
						newNums2Arr = newNums2Arr + "-" + nums2Arr[j];
					}
				}
				if(newNumsArr == ""){
					if(newNums2Arr != ""){
						newNumsArr = newNums2Arr;
					}
				}else{
					if(newNums2Arr != ""){
						newNumsArr = newNumsArr + "," + newNums2Arr;
					}
				}
			}
		}
		return newNumsArr;
	}
	
	//验证是否含有非法字符
	function checkTextArea(){
		var friendsNum = document.getElementById("friendsNum").value;
		var numsArr = new Array();
		if(friendsNum != null && friendsNum != ""){
			numsArr = friendsNum.split(",");
			for(var i = 0;i < numsArr.length;i++){
				var nums2Arr = numsArr[i].split("-");
				for(var j = 0;j < nums2Arr.length;j++){
					if(!checkMobile(nums2Arr[j])){
						window.alert("好友号码不是电信号码！");
						return false;
					}
					if(!checkLength(nums2Arr[j])){
						window.alert("好友号码不能超过11位！");
						return false;
					}
					if(!checkIsInt(nums2Arr[j])){
						window.alert("好友号码中包含非法字符！");
						return false;
					}
				}
				if(nums2Arr.length > 1){
					if(!checkNumSection(numsArr[i])){
						window.alert("好友号码中【"+ numsArr[i] +"】包含不一致的号码段！");
						return false;
					}
				}
				if(nums2Arr.length > 1){
					if(!checkNumsCompare(numsArr[i])){
						window.alert("好友号码段【"+ numsArr[i] +"】设置错误！");
						return false;
					}
				}
				if(!checkSameNums(friendsNum)){
					window.alert("好友号码段设置包含重复的号码！");
					return false;
				}
			}
		}else{
			window.alert("好友号码资源不能为空！");
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
	
	function setFileSuffix(terId){
		var proPlatDes = $("#proPlatDes").val();
		var brands=new Array(); //创建数组
		brands = proPlatDes.split(",");
		var platBrands=new Array(); //创建数组 
		var id = "";
		var plat = "";
	
		for(i=0;i<proPlatDes.split(",").length;i++){
			platBrands = brands[i].split("|");
			id = platBrands[0];
			plat = platBrands[1];
			if(id == terId){
		  		if(plat == "brew")
				{
		  			this.editForm.fileSuffix.value = "bpk";
		  		}
		 	 	else
				{
		  			this.editForm.fileSuffix.value = "zip";
		  		}
				
				break;
			}
		
		}
 	}
</script>
</head>
<body>
<table width="100%" height="99%" border="0" cellspacing="0"
	cellpadding="0">
	<!-- 头部 -->
	<tr>
		<td width="3%" height="36" align="left"
			background="<c:out value="${root}"/>/images/main/all_t_bg.gif"><img
			src="<c:out value="${root}"/>/images/main/all_t_l.gif"></td>
		<td width="94%" height="36" background="<c:out value="${root}"/>/images/main/all_t_bg.gif"><img
			src="<c:out value="${root}"/>/images/icon/baobiao.gif"><span class="title"><strong>创业园应用上传</strong></span></td>
		<td width="3%" height="36" align="right"
			background="<c:out value="${root}"/>/images/main/all_t_bg.gif"><img
			src="<c:out value="${root}"/>/images/main/all_t_r.gif"></td>
	</tr>
	<!-- 中间部分 -->
	<form id="editForm" name="editForm" method="post"
		action="<c:url value="/college.do">
					<c:param name="action" value="upload"/>
			  	</c:url>"
		enctype="multipart/form-data">
		<c:if test="${not empty vo}">
			<input type="hidden" value="<c:out value="${vo.svId}"/>" id="svId" name="svId"/>
		</c:if>
	<tr>
		<td colspan="3" align="center" valign="top" bgcolor="#FFFFFF"
			class="allbox">
		<table width="100%" border="0" cellpadding="0" cellspacing="5"
			class="boxsearch">
			<tr>
				<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
				<td width="20%" align="right">应用名称：</td>
				<td width="30%" align="left" nowrap="nowrap"><input
					class="input" type="text" id="svName" name="svName" value="<c:out value="${vo.svName}"/>" <c:if test="${empty vo}">onBlur="checkUniqueSvName(this.value);"</c:if> <c:if test="${not empty vo}">readonly="readonly"</c:if>/><font color=red>*</font><span id="svNameErrorInfo" style="word-break:break-all;"></span></td>
				<td width="40%" align="left" class="greenfont" valign="top">应用名称是向所有用户展示的，请规范填写，可以使用中文。</td>
			</tr>
			<tr>
				<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
				<td width="20%" align="right">文件名称：</td>
				<td width="30%" align="left" nowrap="nowrap"><input
					class="input" type="text" id="stEngName" name="stEngName" value="<c:out value="${vo.stEngName}"/>" <c:if test="${not empty vo and not empty vo.stEngName}">readonly="readonly"</c:if>/><font color=red>*</font></td>
				<td width="40%" align="left" class="greenfont" valign="top">文件名称应与程序运行文件名称一致,且不含后缀，只能是数字和字母组合。例如hello.bpk,其文件名称为hello，注意区分大小写。</td>
			</tr>
			<tr>
				<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
				<td width="20%" align="right">版本号：</td>
				<td width="30%" align="left" nowrap="nowrap"><input
					class="input" type="text" id="svVersion" name="svVersion" value="" /><c:if test="${not empty vo}">&nbsp;&nbsp;上一版本号:<span id="preVersion"><c:out value="${vo.svVersion}"/></c:if></span><font color=red>*</font></td>
				<td width="40%" align="left" class="greenfont" valign="top">请按规范输入软件版本号，示例：1.0.0.0</td>
			</tr>
			<tr id="classIdTr" style="display: none;" >
				<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
				<td width="20%" align="right">CLASS-ID：</td>
				<td width="30%" align="left" nowrap="nowrap"><input
					class="input" type="text" id="ismpId" name="ismpId" value="" onBlur="checkUniqueIsmpId2(this.value);"/><font color=red>*</font><span id="errorInfo" style="word-break:break-all;"></span></td>
				<td width="40%" align="left" class="greenfont" valign="top">CLASS-ID是以0x开头的数字或字母组合，共10位长度的产品编号，是创业园中应用程序的唯一标识。brew版本为应用程序在编译时生成的CLASS-ID，mobile版本请按规定格式输入。例如0x12345678（注：开头是数字0不是字母O）。</td>
			</tr>
			<tr>
				<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
				<td width="20%" align="right">手机型号：</td>
				<td width="30%" align="left">
					<c:choose>
					<c:when test="${empty softId}">
					<a href="javascript:openDialog()"><font color="coral">[选择]</font></a>
					</c:when>
					<c:otherwise>
					<a href="javascript:openDialog()"><font color="coral">[修改]</font></a>
					</c:otherwise>
					</c:choose>
				</td>
				<td width="40%" align="left" class="greenfont" valign="top">请选择适配机型，如找不到想要的机型，请联系网站管理员</td>
			</tr>
			<c:choose>
			<c:when test="${empty softId}">
			<tr id="ter-show-tr" style="display:none">
				<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
				<td width="20%" align="right">当前适配：</td>
				<td width="30%" align="left">
					<table id='ter-show-tab' width="100%" height="90%" class="listTab">
						<tr>
							<th width="25%">厂商</th>
							<th width="40%">型号</th>
							<th width="35%">平台</th>
						</tr>
					</table>
				</td>
				<td width="40%" align="left" class="greenfont" valign="top">&nbsp;<td>
			</tr>
			</c:when>
			<c:otherwise>
			<tr id="ter-show-tr" style="display:block">
				<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
				<td width="20%" align="right">当前适配：</td>
				<td width="30%" align="left">
					<table id='ter-show-tab' width="100%" height="90%" class="listTab">
						<tr>
							<th width="25%">厂商</th>
							<th width="40%">型号</th>
							<th width="35%">平台</th>
						</tr>
						<c:forEach items="${list}" var="list">
						<tr class="addedByJs">
							<td width="25%"><c:out value="${list.facName}"/></td>
							<td width="40%"><c:out value="${list.terName}"/></td>
							<td width="35%"><c:out value="${list.terPlat}"/></td>
						</tr>
						<input id="terIdForSetFileSuffix" name="terIdForSetFileSuffix" type="hidden" value="${list.terId}"/>
						</c:forEach>
					</table>
				</td>
				<td width="40%" align="left" class="greenfont" valign="top">&nbsp;<td>
			</tr>
			</c:otherwise>
			</c:choose>
			<!-- START 添加应用分类 added by wanglei 2009-11-12 -->
			<tr>
				<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
				<td width="20%" align="right">应用分类：</td>
				<td width="30%" align="left" nowrap="nowrap">
					<select id="appType" name="appType">
						<c:choose>
						<c:when test="${empty appType}">
							<option value="2">软件</option>
							<option value="1">游戏</option>
							<option value="3">主题</option>
						</c:when>
						<c:otherwise>
							<c:if test="${not empty appType and appType == 2}">
								<option value="2">软件</option>
							</c:if>
							<c:if test="${not empty appType and appType == 1}">
								<option value="1">游戏</option>
							</c:if>
							<c:if test="${not empty appType and appType == 3}">
								<option value="3">主题</option>
							</c:if>
							<c:if test="${not empty appType and appType == ''}">
								<option value="2">软件</option>
							</c:if>
						</c:otherwise>
						</c:choose>
					</select>
				</td>
				<td width="40%" align="left" class="greenfont" valign="top"></td>
			</tr>
			<!-- END -->
			<tr>
				<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
				<td width="20%" align="right">描述：</td>
				<td width="30%" align="left" nowrap="nowrap"><textarea
					type="text" id="svDesc" name="svDesc" rows="3" cols="40"
					style="border:1px solid #7F9DB9;
				background-color:#FFF;
				color:#363636;
				font-size:12px;
				padding-left:3px;"><c:if test="${not empty vo}"><c:out value="${vo.svDesc}"/></c:if></textarea></td>
				<td width="40%" align="left" class="greenfont" valign="top">请输入尽量详细的软件描述。</td>
			</tr>
			<tr>
				<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
				<td width="20%" align="right">版本文件：</td>
				<td width="30%" align="left" nowrap="nowrap"><input type="file"
					id="theSvFile" name="theSvFile" size="40" class="input"><font
					color=red>*</font></td>
			  <td width="40%" align="left" class="greenfont" valign="top">请选择要发布的应用软件包，brew版本必须为bpk格式，mobile版本必须为压缩包（ZIP）格式。mobile版本上传的压缩包名称须与程序运行文件名一致。<br><a href="${root}\resource\brew-help.rar"><font color="orange">[brew打包工具及示例]</font></a><br><a href="${root}\resource\WinMobile-help.rar"><font color="orange">[WinMobile应用打包示例]</font></a><br><a href="http://www.surfingcenter.cn/toolsdl/Microsoft+.NET+Framework+2.0.exe"><font color="orange">[Microsoft .NET Framework 2.0]</font></a></td>
			</tr>
			<tr>
				<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
				<td width="20%" align="right">图标上传：</td>
				<td width="30%" align="left" nowrap="nowrap"><input type="file"
					id="theStFile" name="theStFile" size="40" class="input"><font
					color=red>*</font></td>
				<td width="40%" align="left" class="greenfont" valign="top">Brew程序请上传’程序名.png‘图标，WinMobile请上传‘ico_软件名称_h.png‘图标。图标说明请参考<a href="${root}\resource\doc_V1.01.rar"><font color="orange">[创业园打包帮助文档]</font></a>。</td>
			</tr>
			<tr style="display: none;">
				<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
				<td width="20%" align="right">强制更新：</td>
				<td width="30%" align="left" nowrap="nowrap">
					<input type="text" id="svForceupdate" name="svForceupdate" style="display:none"/>
					<input type="checkbox" id="checkboxAttr" value="0"/>
				</td>
				<td width="40%" align="left">&nbsp;</td>
			</tr>
			<tr style="">
				<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
				<td width="20%" align="right">发布者号码：</td>
				<td width="30%" align="left" nowrap="nowrap">
					<input class="input" type="text" id="svSender" name="svSender" readonly="readonly" value="<c:out value='${sender}'/>"/>
				</td>
				<td width="40%" align="left">&nbsp;</td>
			</tr>
			<tr style="">
				<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
				<td width="20%" align="right">发布者ID：</td>
				<td width="30%" align="left" nowrap="nowrap">
					<input class="input" type="text" id="svSubmitid" name="svSubmitid" readonly="readonly" value="<c:out value='${fn:trim(submitId)}'/>"/>
				</td>
				<td width="40%" align="left">&nbsp;</td>
			</tr>
			<tr>
				<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
				<td width="20%" align="right">发布类型：</td>
				<td width="30%" align="left" nowrap="nowrap">
					<select disabled="disabled">
						<option value="1" <c:if test="${type == '1'}">selected</c:if> >自我测试</option>
						<option value="2" <c:if test="${type == '2'}">selected</c:if> >好友测试</option>
						<option value="3" <c:if test="${type == '3'}">selected</c:if> >创业园发布</option>
					</select>
					<input type="hidden" id="type" name="type" value="<c:out value='${type}'/>"/> 
					<input type="hidden" id="softId" name="softId" value="<c:out value='${softId}'/>"/> 
				</td>
				<td width="40%" align="left" class="greenfont" valign="top">
			</tr>
			<tr id="friendsCheckBox" style="display: none;">
				<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
				<td width="20%" align="right">好友设置：</td>
				<td width="30%" align="left" nowrap="nowrap">
					<input type="checkbox" id="friendConfigCheck" name="friendConfigCheck" value="0" onClick="showText()" disabled="disabled"/>
					<input type="hidden" id="friendsConfig" name="friendsConfig"/>
				</td>
				<td width="40%" align="left" class="greenfont" valign="top"></td>
			</tr>
			<tr id="friendsText" style="display: none;">
				<td width="10%" align="right" nowrap="nowrap">&nbsp;</td>
				<td width="20%" align="right">号码：</td>
				<td width="30%" align="left" nowrap="nowrap"><textarea
					type="text" id="realFriendsNum" name="realFriendsNum" rows="3" cols="40"
					style="border:1px solid #7F9DB9;
				background-color:#FFF;
				color:#363636;
				font-size:12px;
				padding-left:3px;" readonly="readonly"></textarea>
				<input id="friendsNum" name="friendsNum" type="hidden" value="<c:out value='${fn:trim(receiver)}'/>"/>
				</td>
				<td width="40%" align="left" class="greenfont" valign="top">注：只有安装了创业园应用框架的天翼手机才可下载使用创业园内发布的软件。</td>
			</tr>
			<tr>
				<td colspan="4" align="center" valign="top" bgcolor="#FFFFFF"
					class="allbox"><INPUT class=denglu1 type=button value="提 交"
					onclick="checkThisForm()" /> <INPUT class=denglu1
					type=button value="重 置"
					onclick="javascript:document.editForm.reset();" /></td>
			</tr>
		</table>
		</td>
	</tr>
<input type="hidden" id="proPlatDes" name="proPlatDes" value="<c:out value="${proPlatDes}"/>"/>
<input type="hidden" id="fileSuffix" name="fileSuffix" value="<c:out value="${fileSuffix}"/>"/>
	<input type="hidden" id="terminalsIds" name="terminalsIds" value=""/>
	<input type="hidden" id="mobileType" name="mobileType" value=""/>		
	</form>
	<!-- 尾部 -->
	<tr>
		<td height="26" align="left" background="<c:out value="${root}"/>/images/main/all_d_bg.gif"><img
			src="<c:out value="${root}"/>/images/main/all_d_l.gif"></td>
		<td height="26" background="<c:out value="${root}"/>/images/main/all_d_bg.gif">&nbsp;</td>
		<td height="26" align="right" background="<c:out value="${root}"/>/images/main/all_d_bg.gif"><img
			src="<c:out value="${root}"/>/images/main/all_d_r.gif"></td>
	</tr>
</table>
<!-- ################################################################################### -->
<!-- dialog:terminal match -->
<script>
	//弹出机型适配窗口
	function openDialog(){
		$("#dialog").dialog('open');
	}
	
	function initJQueryTabs(){
		$("#tabs").tabs();
		var headUrl = requestUrl();
		var action1 = "/college.do?action=getMobileTerminal";
		//############# init mobile terminals ###################
		$.ajax({
	        url: headUrl + action1,
	        dataType :"json",
	        cache: false,
	        type:"POST",
	        data:{},
	        success: function(json) {
	        	var result = json.result;
	        	for(var i = 0;i < result.length;i++){
	        		var tablehtml = "";
	        		var facId = result[i].facId;
	        		var facName = result[i].facName;
	        		var terminals = result[i].terminals;
	        		var terId = "";
	        		var terName = "";
	        		tablehtml += "<tr><td>" + facName + "</td><td>";
	        		if(terminals != undefined){
	        			for(var j = 0;j < terminals.length;j++){
	        				terId = terminals[j].terId;
	        				terName = terminals[j].terName;
	        				tablehtml += "<input type='checkbox' value='"+ terId +"' /><label>" + terName + "</label>";
	        			}
	        		}else{
	        			tablehtml += "&nbsp;";
	        		}
	        		tablehtml += "</td></tr>";
	        		$('#terminals-mobile tbody').append(tablehtml);
	        	}
	        }
    	});
    	//############# init brew terminals ###################
    	var action2 = "/college.do?action=getBrewTerminal";
    	$.ajax({
	        url: headUrl + action2,
	        dataType :"json",
	        cache: false,
	        type:"POST",
	        data:{},
	        success: function(json) {
	        	var result = json.result;
	        	for(var i = 0;i < result.length;i++){
	        		var tablehtml = "";
	        		var facId = result[i].facId;
	        		var facName = result[i].facName;
	        		var terminals = result[i].terminals;
	        		var terId = "";
	        		var terName = "";
	        		tablehtml += "<tr><td>" + facName + "</td><td>";
	        		if(terminals != undefined){
	        			for(var j = 0;j < terminals.length;j++){
	        				terId = terminals[j].terId;
	        				terName = terminals[j].terName;
	        				tablehtml += "<input type='checkbox' value='"+ terId +"' /><label>" + terName + "</label>";
	        			}
	        		}else{
	        			tablehtml += "&nbsp;";
	        		}
	        		tablehtml += "</td></tr>";
	        		$('#terminals-brew tbody').append(tablehtml);
	        	}
	        }
    	});
	}
	
	setFileSuffix($("#terIdForSetFileSuffix").val());
</script>
<style type="text/css">
		div#tabs-1,div#tabs-2 { 
		 	width: 480px;
		}
		div#tabs-1 table,div#tabs-2 table{ 
			margin: 1em 0; 
			border-collapse: collapse; 
			width: 100%; 
		}
		div#tabs-1 table td, div#tabs-1 table th, div#tabs-2 table td, div#tabs-2 table th{
			border: 1px solid #eee; 
			text-align: left;
		}
</style>
<div id="dialog" title="机型适配" style="display:none;">
	<div id="tabs">
		<p><font color="brown">说明:<br>Mobile机型和Brew机型不能同时适配.<br>系统最终会自动过滤掉非当前选择平台的机型的勾选.</font></p>
		<ul>
			<li><a href="#tabs-1">Mobile</a></li>
			<li><a href="#tabs-2">Brew</a></li>
		</ul>
		<div id="tabs-1">
			<table id="terminals-mobile" border="0" class="ui-widget ui-widget-content">
				<thead>
				<tr class="ui-widget-header " >
					<th width="20%">厂商</th>
					<th width="80%">型号</th>
				</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<div id="tabs-2">
			<table id="terminals-brew" border="0" class="ui-widget ui-widget-content">
				<thead>
				<tr class="ui-widget-header " >
					<th width="20%">厂商</th>
					<th width="80%">型号</th>
				</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
</div>
</body>
</html>