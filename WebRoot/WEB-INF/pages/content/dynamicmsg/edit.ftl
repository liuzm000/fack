<#assign rootPath=request.scheme+"://"+request.serverName+":"+request.serverPort+request.contextPath />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>广东移百管理系统</title>
<link href="${rootPath}/css/main.css" rel="stylesheet" type="text/css">
<script language="JavaScript"  type="text/javascript" src="${rootPath}/javascript/common/calendar.js"></script>
<script language="JavaScript"  type="text/javascript" src="${rootPath}/javascript/common/base.jsp"></script>
<script language="JavaScript" type="text/JavaScript">
function checkForm(){
	var phoneNum = document.getElementById("dynamicMsg.phoneNum");
	var productId = document.getElementById("dynamicMsg.product.id");
	var content = document.getElementById("dynamicMsg.content");
	var param = document.getElementById("dynamicMsg.param");
	if(phoneNum.value.trim() == ""){
		alert("手机号码不能为空");
    	phoneNum.focus();
      	return false;
	}
	if(isNaN(phoneNum.value.trim())){
		alert("手机号码必须是数字");
    	phoneNum.focus();
      	return false;
	}
	if(phoneNum.value.trim().length > 11){
		alert("手机号码不能超过11位");
    	phoneNum.focus();
      	return false;
	}
	if(productId.value.trim() == ""){
		alert("请选择插件类型");
    	productId.focus();
      	return false;
	}
	if(content.value.trim() == ""){
		alert("消息内容不能为空");
    	content.focus();
      	return false;
	}
	if(content.value.trim().length > 500){
		alert("消息内容不能超过500字符");
    	content.focus();
      	return false;
	}
	if(param.value.trim() == ""){
		alert("消息参数不能为空");
    	param.focus();
      	return false;
	}
	if(param.value.trim().length > 500){
		alert("消息参数不能超过500字符");
    	param.focus();
      	return false;
	}
	return true;
}

String.prototype.trim = function()  
{
    return this.replace(/(^\s*)|(\s*$)/g,"");   
}


</script>
</head>

<body style="padding-top:5px;padding-bottom:3px;">
    <form id="updateForm" name="updateForm" method="post" action="${rootPath}/content/dynamicmsg/update.action" onSubmit="return checkForm();">
<table width="100%" height="99%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="3%"  height="36"  align="left" background="${rootPath}/images/main/all_t_bg.gif"><img src="${rootPath}/images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="${rootPath}/images/main/all_t_bg.gif"><img src="${rootPath}/images/icon/baobiao.gif"><span class="title"><strong>动态消息管理</strong> - 更新管理</span></td>
    <td width="3%"  height="36"  align="right" background="${rootPath}/images/main/all_t_bg.gif"><img src="${rootPath}/images/main/all_t_r.gif" ></td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
    <input type="hidden" name="dynamicMsg.id" id="dynamicMsg.id" value="${dynamicMsg.id?if_exists}"/>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="boxsearch">
      <tr>
        <td colspan="3" valign="top" >
        <table width="100%" border="0" cellpadding="0" cellspacing="5" class="boxsearch">
        	<tr>                          
              <td width="20%" align="left" nowrap="nowrap">&nbsp;</td>
              <td width="30%" align="right">手机号码：
              </td>  
              <td width="30%" align="left" nowrap="nowrap">
             	 <input class="input" type="text" name="dynamicMsg.phoneNum" id="dynamicMsg.phoneNum" value="${dynamicMsg.phoneNum?if_exists}"/><font color=red>*</font>
	          </td>
              <td width="20%" align="left">&nbsp;</td>
             </tr>
        
            <tr>                          
              <td width="9%" align="left" nowrap="nowrap">&nbsp;</td>
              <td width="30%" align="right">插件名称：
              </td>  
              <td width="9%" align="left" nowrap="nowrap">
              <select name="dynamicMsg.product.id"  id="dynamicMsg.product.id">
	                <option value="">请选择插件类型...</option>
	                <#if productList?exists && productList.size() != 0>
			        <#list productList as product>
				        <option value="${product.id?if_exists}" <#if dynamicMsg.product?if_exists.id?if_exists.equals(product.id?if_exists)>selected</#if>>${product.name?if_exists}</option>
			        </#list>
			        </#if>
	            </select> 
	          </td>
              <td width="52%" align="left">&nbsp;</td>
             </tr>
            
            <tr>     
              <td width="20%" align="left">&nbsp;</td>                      
              <td width="30%" align="right" nowrap="nowrap">消息内容：</td>
              <td width="30%" align="left">
              	<textarea type="text" name="dynamicMsg.content" id="dynamicMsg.content" rows="3" cols="67" style="border:1px solid #7F9DB9;
				background-color:#FFF;
				color:#363636;
				font-size:12px;
				padding-left:3px;">${dynamicMsg.content?if_exists}</textarea>		
              </td>
              <td width="20%" align="left">&nbsp;</td>          
            </tr>
            
            <tr>    
              <td width="20%" align="left">&nbsp;</td>                       
              <td width="20%" align="right" nowrap="nowrap">消息参数：</td>
              <td width="30%" align="left">
              	<textarea type="text" name="dynamicMsg.param" id="dynamicMsg.param" rows="3" cols="67" style="border:1px solid #7F9DB9;
				background-color:#FFF;
				color:#363636;
				font-size:12px;
				padding-left:3px;">${dynamicMsg.param?if_exists}</textarea>		
              </td>
              <td width="20%" align="left">&nbsp;</td>          
            </tr>
           </table>
           </td>
           </tr>
                 
       <tr>
        <td width="1%" height="1"><img src="${rootPath}/images/space.gif" width="1" height="1"></td>
        <td width="98%" bgcolor="#F3F3F3"><img src="${rootPath}/images/space.gif" width="1" height="1"></td>
        <td width="1%"><img src="${rootPath}/images/space.gif" width="1" height="1"></td>
       </tr>
		
      <tr>
        <td height="44" colspan="3" align="center" valign="middle" class="down">
         <INPUT class=denglu1 type="submit" value="保 存"/>
         <INPUT class=denglu1 type="reset" value="重 置"/>
         <INPUT class=denglu1 type=button value="查看列表" onClick="javascript:window.location.href = '${rootPath}/content/dynamicmsg/list.action';"/>
        </td>
      </tr>
    </table>
	
    </td>
  </tr>
    
  <tr>
    <td height="26" align="left"background="${rootPath}/images/main/all_d_bg.gif"><img src="${rootPath}/images/main/all_d_l.gif" ></td>
    <td height="26" background="${rootPath}/images/main/all_d_bg.gif">&nbsp;</td>
    <td height="26" align="right" background="${rootPath}/images/main/all_d_bg.gif"><img src="${rootPath}/images/main/all_d_r.gif" ></td>
  </tr>
</table>

	</form>

</body>
</html>
