$(document).ready(function() {
	if (document.getElementById("searchFlag").value == 'true') {
		document.getElementById("search").style.display = '';
	} else {
		document.getElementById("search").style.display = 'none';
	}
	if (document.getElementById("importFlag").value == 'true') {
		document.getElementById("import").style.display = '';
	} else {
		document.getElementById("import").style.display = 'none';
	}
});

function checkForm(){
	var phoneNums = document.getElementById("phoneNums");
	var phoneArr = phoneNums.value.trim().split(",");
	var content = document.getElementById("msgContent");
	if(phoneNums.value.trim() == ""){
		alert("手机号码不能为空");
    	phoneNums.focus();
      	return false;
	}
	
	for(var i=0;i<phoneArr.length;i++){
		//alert(phoneArr[i].trim());
		if(isNaN(phoneArr[i].trim())){
			alert( "手机号码（"+phoneArr[i].trim() + "）必须是数字");
    		phoneNums.focus();
	      	return false;
		}	
		if(phoneArr[i].trim().length > 11){
			alert("手机号码（"+phoneArr[i].trim() + "）不能超过11位");
    		phoneNums.focus();
	      	return false;
		}
	}	

	if(content.value.trim() == ""){
		alert("消息内容不能为空");
    	content.focus();
      	return false;
	}
	if(content.value.trim().length > 200){
		alert("消息内容不能超过200字符");
    	content.focus();
      	return false;
	}
	
	return true;
}

String.prototype.trim = function()  
{
    return this.replace(/(^\s*)|(\s*$)/g,"");   
}

function terMap()　{
	var facId = document.getElementById("facId").value;
    send_request("POST", "../sms/terMap.action?facId=" + facId, null, "text", feedback);
}

function search()　{
	var terId = document.getElementById("terId").value;
    var terPlat = document.getElementById("terPlat").value;
    var startTime = document.getElementById("startTime").value;
    var endTime = document.getElementById("endTime").value;
    send_request("POST", "../sms/search.action?terId=" + terId + "&terPlat=" + terPlat + "&startTime=" + startTime + "&endTime=" + endTime, null, "text", feedback);
}

function add()　{
	var phoneNum = document.getElementById("phoneNum").value;
	var phoneNums =document.getElementById("phoneNums").value;
	if (phoneNum.trim() != "") {
		if (phoneNums.trim() == "") {
			document.getElementById("phoneNums").value = phoneNum;
		} else {
			document.getElementById("phoneNums").value = phoneNums + ',' + phoneNum;
		}
	}
}

function importFile() {
	var filepath = document.getElementById("datafile").value;
	if (filepath == ""){
		alert("请选择要导入的文件!");
		return;	
	}
	if (filepath.substring(filepath.lastIndexOf('.')).toLowerCase() != ".xls") {
		alert("请导入.xls格式的文件！");
		return;
	}
	if (document.getElementById("search").style.display == 'none') {
		document.getElementById("searchFlag").value = "";
	} else {
		document.getElementById("searchFlag").value = "true";
	}
	document.getElementById("importFlag").value = "true";
	var smsForm = document.getElementById("smsForm");
	smsForm.action = "importFile.action";
	smsForm.submit();
}

function showhide(id) {
	if (document.getElementById(id).style.display == 'none') {
		document.getElementById(id).style.display = '';
	} else if (document.getElementById(id).style.display != 'none') {
		document.getElementById(id).style.display = 'none';
	}
}

function feedback() {
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {
			var objLeftPage = window.parent.contactMenu;
			var responseText = http_request.responseText;
			if (responseText.indexOf('TerMap:') > -1) {
				var terId = document.getElementById("terId");
				for (var i = terId.options.length - 1; i >= 1; i--) {
					terId.remove(i);
				}
				var termapStr = responseText.substring(responseText.indexOf('{') + 1, responseText.length - 1);
				var termap = termapStr.split(',');
				for (var i = 0; i < termap.length; i++) {
					tid = termap[i].substring(0, termap[i].indexOf(':'));
					tname = termap[i].substring(termap[i].indexOf(':') + 1);
					terId.options[terId.options.length] = new Option(tname, tid);
				}
			} else if (responseText.indexOf('PhoneNum:') > -1) {
				var phoneNumStr = responseText.substring(responseText.indexOf('{') + 1, responseText.length - 1);
				var phoneNumArray = phoneNumStr.split(',');
				var total = phoneNumStr.trim() == '' ? 0 : phoneNumArray.length
				document.getElementById("phoneNum").value = phoneNumStr;
				document.getElementById("total").innerHTML = "共查询到" + total + "个用户号码";
			} else {
				alert("error");
			}
		} else {
			alert("您所请求的页面有异常！");
		}
	}
}
    
    