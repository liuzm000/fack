/**
 * 添加新的软件类型
 * 
 * @param {}
 *            obj 产品ID
 */
function addNewSoftType(obj) {
	var url = this.requestUrl();
	window.location = url + "/version/operator.do?action=doNew&proId=" + obj;
}

/**
 * 添加新版本
 * 
 * @param {}
 *            obj 软件类型ID
 */
function addNewVersion(obj1, obj2) {
	var url = this.requestUrl();
	window.location = url + "/version/operator.do?action=doAddVersion&stId="
			+ obj1 + "&stName=" + obj2;;
}
/**
 * 显示版本列表
 * 
 * @param {}
 *            obj 软件类型ID
 */
function showVersionList(obj1, obj2) {
	var url = this.requestUrl();
	window.location = url + "/version/operator.do?action=showVersionList&stId="
			+ obj1 + "&stName=" + encodeURI(obj2) + "&pageNum=1&pageSize=10";
}

/**
 * 适配手机型号
 * 
 * @param {}
 *            obj1 stId 软件类型ID
 * @param {}
 *            obj2 stName 软件类型名
 */
function doMatchPhoneType(obj1, obj2) {
	var url = this.requestUrl();
	window.location = url
			+ "/version/operator.do?action=doMatchPhoneType&stId=" + obj1
			+ "&proId=" + obj2;
}

function doMatchTerUserGroup(obj1, obj2) {
	var url = this.requestUrl();
	window.location = url
			+ "/version/operator.do?action=doMatchTerUserGroup&stId=" + obj1
			+ "&proId=" + obj2;
}

function doMatchTerUserGroupForVersion(obj1, obj2, obj3) {
	var url = this.requestUrl();
	window.location = url
			+ "/version/operator.do?action=doMatchTerUserGroupForVersion&svId="
			+ obj1 + "&stId=" + obj2 + "&stName=" + obj3;
}

/**
 * 软件类型添加验证。
 */
function checkAddSoftTypeForm() {
	var stName = $("#stName").val();
	var theStFile = $("#theStFile").val();
	var checkValue = $("#checkboxAttr");
	var hasMsgPush = $("#stMsgPush");
	if (checkValue.attr("checked")) {
		hasMsgPush.val("0");
	} else {
		hasMsgPush.val("1");
	}
	if ($.trim(stName) == "") {
		alert("软件类型名称不能为空!");
		$("#stName").focus();
		return;
	}
	if ($.trim(theStFile) == "") {
		alert("请上传一个图标!");
		$("#theStFile").focus();
		return;
	}
	editForm.submit();
}

function checkAddVersionForm() {
	var svName = $("#svName").val();
	var svVersion = $("#svVersion").val();
	var theSvFile = $("#theSvFile").val();
	var checkValue = $("#checkboxAttr");
	var svForceupdate = $("#svForceupdate");
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
	if ($.trim(theSvFile) == "") {
		alert("请上传一个版本文件!");
		$("#theSvFile").focus();
		return;
	}
	var type = theSvFile.substring(theSvFile.lastIndexOf(".") + 1);
	if (type != "apk" && type != "APK" && type != "bpk" && type != "BPK") {
		alert("版本文件类型必须为‘apk|APK|bpk|BPK’类型!");
		$("#theSvFile").focus();
		return;
	}
	if (thePreviewFile1 != "" && thePreviewFile1 != null) {
		var imgtype = thePreviewFile1.substring(thePreviewFile1
				.lastIndexOf(".")
				+ 1);
		if (imgtype != "jpg" && imgtype != "JPG" && imgtype != "png" && imgtype != "PNG"
				&& imgtype != "jpeg" && imgtype != "JPEG" && imgtype != "BMP"
				&& imgtype != "bmp" && imgtype != "gif" && imgtype != "GIF") {
			alert("预览1文件类型必须为‘jpg|JPG|png|PNG|jpeg|JPEG|bmp|BMP|gif|GIF’类型!");
			$("#thePreviewFile1").focus();
			return;
		}
	}
	if (thePreviewFile2 != "" && thePreviewFile2 != null) {
		var imgtype = thePreviewFile2.substring(thePreviewFile2
				.lastIndexOf(".")
				+ 1);
		if (imgtype != "jpg" && imgtype != "JPG" && imgtype != "png" && imgtype != "PNG"
				&& imgtype != "jpeg" && imgtype != "JPEG" && imgtype != "BMP"
				&& imgtype != "bmp" && imgtype != "gif" && imgtype != "GIF") {
			alert("预览2文件类型必须为‘jpg|JPG|png|PNG|jpeg|JPEG|bmp|BMP|gif|GIF’类型!");
			$("#thePreviewFile2").focus();
			return;
		}
	}
	editForm.submit();
}

/**
 * 添加软件类型时的图标预览
 */
function viewIcon(obj) {
	if (obj == 'stFile') {
		var iconUrl = document.getElementById("theStFile");
		var iconImg = document.getElementById("iconImg");
		// document.getElementById("pic").filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src
		// = "./images/logo.gif";
		if (iconUrl.value != "") {
			iconImg.src = iconUrl.value;
			iconImg.style.display = "";
		}
	} else if (obj == 'listStFile') {
		var iconUrl = document.getElementById("theListStFile");
		var iconImg = document.getElementById("listIconImg");
		if (iconUrl.value != "") {
			iconImg.src = iconUrl.value;
			iconImg.style.display = "";
		}
	}
}
/**
 * 获取请求头 http://xxx.xx.xxx.xx:xxxx/xxxxx
 * 
 * @param {}
 *            paras
 * @return {}
 */
function requestUrl(paras) {
	var url = location.href;
	var urlArr = url.split("\/");
	var urlFinal = urlArr[0] + "\/" + urlArr[1] + "\/" + urlArr[2] + "\/"
			+ urlArr[3];
	return urlFinal;
}

/**
 * 机型适配
 */
function chooseTerminal(obj, obj1) {
	var selectedIds = $("#terIds").val();// 选中的ID集合
	var selectedArr = selectedIds.split(",");
	var canceledIds = $("#cancelIds").val();// 取消的ID集合
	var canceledArr = canceledIds.split(",");
	var matchedIds = obj1;// 已经适配了的ID集合
	var matchedArr = matchedIds.split(",");
	var before = "【已选中】：" + selectedIds + "\n" + "【已取消】：" + canceledIds + "\n"
			+ "【已适配】：" + matchedIds;
	if (obj.checked) {
		// 选中一个勾选
		// 1.判断选中的ID是否是适配过的ID
		var isMatchedId = false;
		for (var i = 0; i < matchedArr.length; i++) {
			if (matchedArr[i] == obj.value) {
				isMatchedId = true;// 选中的ID是一个已经适配的ID
				break;
			}
		}
		// 如果是适配过的，do nothing
		if (!isMatchedId) {
			// 如果没适配过此ID，则需要加入到已选ID集合中去。
			// 2.在已经选择的ID中判断是否重复
			var isSelectedId = false;
			for (var j = 0; j < selectedArr.length; j++) {
				if (selectedArr[j] == obj.value) {
					isSelectedId = true;// 有重复ID存在,do nothing.
					break;
				}
			}
			if (!isSelectedId) {
				// 没有重复ID，添加进去
				if ($.trim(selectedIds) != "") {
					selectedIds += "," + obj.value;
				} else {
					selectedIds += obj.value;
				}
				$("#terIds").val(selectedIds);
			}
			// 3.检查取消选中ID集合，如存在此ID，去除此ID
			var tempCanceled = "";
			for (var k = 0; k < canceledArr.length; k++) {
				// 重新组合选中的ID集合
				if (canceledArr[k] != obj.value) {
					if ($.trim(tempSelected) == "") {
						tempCanceled += canceledArr[k];
					} else {
						tempCanceled += "," + canceledArr[k];
					}
				}
			}
			canceledIds = tempCanceled;
			$("#cancelIds").val(canceledIds);
		} else {
			// 如果是适配集合中的ID,去取消集合中去除该ID
			var tempCanceled = "";
			for (var k = 0; k < canceledArr.length; k++) {
				// 重新组合选中的ID集合
				if (canceledArr[k] != obj.value) {
					if ($.trim(tempSelected) == "") {
						tempCanceled += canceledArr[k];
					} else {
						tempCanceled += "," + canceledArr[k];
					}
				}
			}
			canceledIds = tempCanceled;
			$("#cancelIds").val(canceledIds);
		}
	} else {
		// 取消一个勾选
		// 1.判断取消的ID是否是适配过的ID
		var isMatchedId = false;
		for (var i = 0; i < matchedArr.length; i++) {
			if (matchedArr[i] == obj.value) {
				isMatchedId = true;// 选中的ID是一个已经适配的ID
				break;
			}
		}
		if (isMatchedId) {
			// 如果是适配过的ID，则需要添加进取消的ID集合中去。
			// 2.在取消的ID集合中判断是否重复
			var isCancelId = false;
			for (var j = 0; j < canceledArr.length; j++) {
				if (canceledArr[j] == obj.value) {
					isCancelId = true;// 有重复ID存在,do nothing.
					break;
				}
			}
			if (!isCancelId) {
				// 取消ID集合中没有此ID，添加进去
				if ($.trim(canceledIds) != "") {
					canceledIds += "," + obj.value;
				} else {
					canceledIds += obj.value;
				}
				$("#cancelIds").val(canceledIds);
			}
			// 3.检查已选中ID集合，如存在此ID，去除此ID
			var tempSelected = "";
			for (var k = 0; k < selectedArr.length; k++) {
				// 重新组合选中的ID集合
				if (selectedArr[k] != obj.value) {
					if ($.trim(tempSelected) == "") {
						tempSelected += selectedArr[k];
					} else {
						tempSelected += "," + selectedArr[k];
					}
				}
			}
			selectedIds = tempSelected;
			$("#terIds").val(selectedIds);
		} else {
			// 选中的ID是一个未适配的ID
			var tempSelected = "";
			for (var k = 0; k < selectedArr.length; k++) {
				// 重新组合选中的ID集合
				if (selectedArr[k] != obj.value) {
					if ($.trim(tempSelected) == "") {
						tempSelected += selectedArr[k];
					} else {
						tempSelected += "," + selectedArr[k];
					}
				}
			}
			selectedIds = tempSelected;
			$("#terIds").val(selectedIds);
		}
	}
	var after = "【已选中】：" + selectedIds + "\n" + "【已取消】：" + canceledIds + "\n"
			+ "【已适配】：" + matchedIds;
	// alert("before:\n" + before + "\n" + "after:\n" + after);
}

/*
 * function updateMatch(obj,obj2) { var addUrl = this.requestUrl(); var
 * selectedIds = $("#terIds").val();// 选中的ID集合 var canceledIds =
 * $("#cancelIds").val();// 取消的ID集合 var stId = obj; var proId = obj2; var action =
 * "/version/operator.do?action=updateMatch&selectedIds=" + selectedIds +
 * "&canceledIds=" + canceledIds + "&stId=" + stId + "&proId=" + proId; $.ajax({
 * url : addUrl + action, dataType : "html", cache : false, success :
 * function(html) { if (html == "SUCCESS") { alert("保存成功！"); } else if (html ==
 * "NOCHANGE"){ alert("保存无变化！"); }else{ alert("保存失败！"); } } }); }
 */

function returnStList(obj) {
	var addUrl = this.requestUrl();
	window.location = addUrl + "/spManage.do?action=showSoftTypeList&proId="
			+ obj;
}

/**
 * 点击修改软件类型
 */
function doEditSoftType(obj, obj1) {
	var addUrl = this.requestUrl();
	window.location = addUrl
			+ "/version/operator.do?action=doEditSoftType&stId=" + obj
			+ "&proId=" + obj1;
}

function editVersion(obj1, obj2, obj3, obj4) {
	if (obj4 == 2) {
		alert("已审核的信息不可修改！");
		return;
	}
	var addUrl = this.requestUrl();
	window.location = addUrl
			+ "/version/operator.do?action=doEditVersion&svId=" + obj1
			+ "&stId=" + obj2 + "&stName=" + obj3;
}

function deleteVersion(obj1, obj2, obj3, obj4) {
	if (obj4 == 2) {
		alert("已审核的信息不可删除！");
		return;
	}
	if (confirm("确定删除版本信息吗？")) {
		var addUrl = this.requestUrl();
		window.location = addUrl
				+ "/version/operator.do?action=deleteVersion&svId=" + obj1
				+ "&stId=" + obj2 + "&stName=" + obj3;
	}
}

function toNextPage(url, flag, pageNum, param, param2) {
	var addUrl = this.requestUrl();
	if (flag != null && flag != "") {
		if (flag == "st") {
			window.location = addUrl + url + "&proId=" + param + "&pageNum="
					+ pageNum + "&pageSize=10";
		} else {
			if (flag == "sv") {
				window.location = addUrl + url + "&stId=" + param + "&stName="
						+ param2 + "&pageNum=" + pageNum + "&pageSize=10";
			}
		}
	}

}

function chooseGroups(obj, obj1) {
	var selectedIds = $("#terIds").val();// 选中的ID集合
	var selectedArr = selectedIds.split(",");
	var canceledIds = $("#cancelIds").val();// 取消的ID集合
	var canceledArr = canceledIds.split(",");
	var matchedIds = obj1;// 已经适配了的ID集合
	var matchedArr = matchedIds.split(",");
	var before = "【已选中】：" + selectedIds + "\n" + "【已取消】：" + canceledIds + "\n"
			+ "【已适配】：" + matchedIds;
	if (obj.checked) {
		// 选中一个勾选
		// 1.判断选中的ID是否是适配过的ID
		var isMatchedId = false;
		for (var i = 0; i < matchedArr.length; i++) {
			if (matchedArr[i] == obj.value) {
				isMatchedId = true;// 选中的ID是一个已经适配的ID
				break;
			}
		}
		// 如果是适配过的，do nothing
		if (!isMatchedId) {
			// 如果没适配过此ID，则需要加入到已选ID集合中去。
			// 2.在已经选择的ID中判断是否重复
			var isSelectedId = false;
			for (var j = 0; j < selectedArr.length; j++) {
				if (selectedArr[j] == obj.value) {
					isSelectedId = true;// 有重复ID存在,do nothing.
					break;
				}
			}
			if (!isSelectedId) {
				// 没有重复ID，添加进去
				if ($.trim(selectedIds) != "") {
					selectedIds += "," + obj.value;
				} else {
					selectedIds += obj.value;
				}
				$("#terIds").val(selectedIds);
			}
			// 3.检查取消选中ID集合，如存在此ID，去除此ID
			var tempCanceled = "";
			for (var k = 0; k < canceledArr.length; k++) {
				// 重新组合选中的ID集合
				if (canceledArr[k] != obj.value) {
					if ($.trim(tempSelected) == "") {
						tempCanceled += canceledArr[k];
					} else {
						tempCanceled += "," + canceledArr[k];
					}
				}
			}
			canceledIds = tempCanceled;
			$("#cancelIds").val(canceledIds);
		} else {
			// 如果是适配集合中的ID,去取消集合中去除该ID
			var tempCanceled = "";
			for (var k = 0; k < canceledArr.length; k++) {
				// 重新组合选中的ID集合
				if (canceledArr[k] != obj.value) {
					if ($.trim(tempSelected) == "") {
						tempCanceled += canceledArr[k];
					} else {
						tempCanceled += "," + canceledArr[k];
					}
				}
			}
			canceledIds = tempCanceled;
			$("#cancelIds").val(canceledIds);
		}
	} else {
		// 取消一个勾选
		// 1.判断取消的ID是否是适配过的ID
		var isMatchedId = false;
		for (var i = 0; i < matchedArr.length; i++) {
			if (matchedArr[i] == obj.value) {
				isMatchedId = true;// 选中的ID是一个已经适配的ID
				break;
			}
		}
		if (isMatchedId) {
			// 如果是适配过的ID，则需要添加进取消的ID集合中去。
			// 2.在取消的ID集合中判断是否重复
			var isCancelId = false;
			for (var j = 0; j < canceledArr.length; j++) {
				if (canceledArr[j] == obj.value) {
					isCancelId = true;// 有重复ID存在,do nothing.
					break;
				}
			}
			if (!isCancelId) {
				// 取消ID集合中没有此ID，添加进去
				if ($.trim(canceledIds) != "") {
					canceledIds += "," + obj.value;
				} else {
					canceledIds += obj.value;
				}
				$("#cancelIds").val(canceledIds);
			}
			// 3.检查已选中ID集合，如存在此ID，去除此ID
			var tempSelected = "";
			for (var k = 0; k < selectedArr.length; k++) {
				// 重新组合选中的ID集合
				if (selectedArr[k] != obj.value) {
					if ($.trim(tempSelected) == "") {
						tempSelected += selectedArr[k];
					} else {
						tempSelected += "," + selectedArr[k];
					}
				}
			}
			selectedIds = tempSelected;
			$("#terIds").val(selectedIds);
		} else {
			// 选中的ID是一个未适配的ID
			var tempSelected = "";
			for (var k = 0; k < selectedArr.length; k++) {
				// 重新组合选中的ID集合
				if (selectedArr[k] != obj.value) {
					if ($.trim(tempSelected) == "") {
						tempSelected += selectedArr[k];
					} else {
						tempSelected += "," + selectedArr[k];
					}
				}
			}
			selectedIds = tempSelected;
			$("#terIds").val(selectedIds);
		}
	}
	var after = "【已选中】：" + selectedIds + "\n" + "【已取消】：" + canceledIds + "\n"
			+ "【已适配】：" + matchedIds;
	// alert("before:\n" + before + "\n" + "after:\n" + after);
}

function chooseVersionGroups(obj, obj1) {
	var selectedIds = $("#terIds").val();// 选中的ID集合
	var selectedArr = selectedIds.split(",");
	var canceledIds = $("#cancelIds").val();// 取消的ID集合
	var canceledArr = canceledIds.split(",");
	var matchedIds = obj1;// 已经适配了的ID集合
	var matchedArr = matchedIds.split(",");
	var before = "【已选中】：" + selectedIds + "\n" + "【已取消】：" + canceledIds + "\n"
			+ "【已适配】：" + matchedIds;
	if (obj.checked) {
		// 选中一个勾选
		// 1.判断选中的ID是否是适配过的ID
		var isMatchedId = false;
		for (var i = 0; i < matchedArr.length; i++) {
			if (matchedArr[i] == obj.value) {
				isMatchedId = true;// 选中的ID是一个已经适配的ID
				break;
			}
		}
		// 如果是适配过的，do nothing
		if (!isMatchedId) {
			// 如果没适配过此ID，则需要加入到已选ID集合中去。
			// 2.在已经选择的ID中判断是否重复
			var isSelectedId = false;
			for (var j = 0; j < selectedArr.length; j++) {
				if (selectedArr[j] == obj.value) {
					isSelectedId = true;// 有重复ID存在,do nothing.
					break;
				}
			}
			if (!isSelectedId) {
				// 没有重复ID，添加进去
				if ($.trim(selectedIds) != "") {
					selectedIds += "," + obj.value;
				} else {
					selectedIds += obj.value;
				}
				$("#terIds").val(selectedIds);
			}
			// 3.检查取消选中ID集合，如存在此ID，去除此ID
			var tempCanceled = "";
			for (var k = 0; k < canceledArr.length; k++) {
				// 重新组合选中的ID集合
				if (canceledArr[k] != obj.value) {
					if ($.trim(tempSelected) == "") {
						tempCanceled += canceledArr[k];
					} else {
						tempCanceled += "," + canceledArr[k];
					}
				}
			}
			canceledIds = tempCanceled;
			$("#cancelIds").val(canceledIds);
		} else {
			// 如果是适配集合中的ID,去取消集合中去除该ID
			var tempCanceled = "";
			for (var k = 0; k < canceledArr.length; k++) {
				// 重新组合选中的ID集合
				if (canceledArr[k] != obj.value) {
					if ($.trim(tempSelected) == "") {
						tempCanceled += canceledArr[k];
					} else {
						tempCanceled += "," + canceledArr[k];
					}
				}
			}
			canceledIds = tempCanceled;
			$("#cancelIds").val(canceledIds);
		}
	} else {
		// 取消一个勾选
		// 1.判断取消的ID是否是适配过的ID
		var isMatchedId = false;
		for (var i = 0; i < matchedArr.length; i++) {
			if (matchedArr[i] == obj.value) {
				isMatchedId = true;// 选中的ID是一个已经适配的ID
				break;
			}
		}
		if (isMatchedId) {
			// 如果是适配过的ID，则需要添加进取消的ID集合中去。
			// 2.在取消的ID集合中判断是否重复
			var isCancelId = false;
			for (var j = 0; j < canceledArr.length; j++) {
				if (canceledArr[j] == obj.value) {
					isCancelId = true;// 有重复ID存在,do nothing.
					break;
				}
			}
			if (!isCancelId) {
				// 取消ID集合中没有此ID，添加进去
				if ($.trim(canceledIds) != "") {
					canceledIds += "," + obj.value;
				} else {
					canceledIds += obj.value;
				}
				$("#cancelIds").val(canceledIds);
			}
			// 3.检查已选中ID集合，如存在此ID，去除此ID
			var tempSelected = "";
			for (var k = 0; k < selectedArr.length; k++) {
				// 重新组合选中的ID集合
				if (selectedArr[k] != obj.value) {
					if ($.trim(tempSelected) == "") {
						tempSelected += selectedArr[k];
					} else {
						tempSelected += "," + selectedArr[k];
					}
				}
			}
			selectedIds = tempSelected;
			$("#terIds").val(selectedIds);
		} else {
			// 选中的ID是一个未适配的ID
			var tempSelected = "";
			for (var k = 0; k < selectedArr.length; k++) {
				// 重新组合选中的ID集合
				if (selectedArr[k] != obj.value) {
					if ($.trim(tempSelected) == "") {
						tempSelected += selectedArr[k];
					} else {
						tempSelected += "," + selectedArr[k];
					}
				}
			}
			selectedIds = tempSelected;
			$("#terIds").val(selectedIds);
		}
	}
	var after = "【已选中】：" + selectedIds + "\n" + "【已取消】：" + canceledIds + "\n"
			+ "【已适配】：" + matchedIds;
	// alert("before:\n" + before + "\n" + "after:\n" + after);
}

function updateMatchGroup(obj) {
	var addUrl = this.requestUrl();
	var selectedIds = $("#terIds").val();// 选中的ID集合
	var canceledIds = $("#cancelIds").val();// 取消的ID集合
	var stId = obj;
	var action = "/version/operator.do?action=updateMatchGroup&selectedIds="
			+ selectedIds + "&canceledIds=" + canceledIds + "&stId=" + stId;
	$.ajax({
				url : addUrl + action,
				dataType : "html",
				cache : false,
				success : function(html) {
					if (html == "SUCCESS") {
						alert("保存成功！");
					} else {
						alert("保存失败！");
					}
				}
			});
}

function updateMatchVersionGroup(obj) {
	var addUrl = this.requestUrl();
	var selectedIds = $("#terIds").val();// 选中的ID集合
	var canceledIds = $("#cancelIds").val();// 取消的ID集合
	var svId = obj;
	var action = "/version/operator.do?action=updateMatchVersionGroup&selectedIds="
			+ selectedIds + "&canceledIds=" + canceledIds + "&svId=" + svId;
	$.ajax({
				url : addUrl + action,
				dataType : "html",
				cache : false,
				success : function(html) {
					if (html == "SUCCESS") {
						alert("保存成功！");
					} else {
						alert("保存失败！");
					}
				}
			});
}