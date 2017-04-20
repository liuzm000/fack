<#assign rootPath=request.scheme+"://"+request.serverName+":"+request.serverPort+request.contextPath />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>广东移百管理系统</title>
<link href="${rootPath}/css/main.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript">
function checkForm(){
	var title = document.getElementById("animation.title");
	var price = document.getElementById("animation.price");
	var phoneId = document.getElementById("animation.phoneId");
	var typeId = document.getElementById("animation.typeId");
	var iconUrl = document.getElementById("fileUrl");
	if(title.value.trim() == ""){
		alert("背景名称不能为空");
    	title.focus();
      	return false;
	}
	if(title.value.trim().length > 30){
		alert("背景名称不能超过30个字符");
    	title.focus();
      	return false;
	}
	if(price.value.trim() == ""){
		alert("价格不能为空");
    	price.focus();
      	return false;
	}
	if(isNaN(price.value.trim())){
		alert("价格必须是数字");
    	price.focus();
      	return false;
	}
	if(phoneId.value.trim() == ""){
		alert("请选择对应手机型号！");
    	phoneId.focus();
      	return false;
	}
	if(typeId.value.trim() == ""){
		alert("请选择对应背景分类！");
    	typeId.focus();
      	return false;
	}
	if(iconUrl.value.trim() == ""){
		alert("请选择背景文件上传");
    	iconUrl.focus();
      	return false;
	} 
	return true;
}

String.prototype.trim = function()  
{
    return this.replace(/(^\s*)|(\s*$)/g,"");   
}

/*图标预览*/
function viewImg(){
	var iconUrl = document.getElementById("fileUrl");
	var iconImg = document.getElementById("iconImg");
	if(iconUrl.value.trim() != ""){
		iconImg.src = iconUrl.value;
		iconImg.style.display = "";
	}
}
</script>
</head>

<body style="padding-top:5px;padding-bottom:3px;">
    <form id="form1" name="form1" method="post" action="${rootPath}/content/animation/add.action" enctype="multipart/form-data" onSubmit=" return checkForm()">
<table width="100%" height="99%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="3%"  height="36"  align="left" background="${rootPath}/images/main/all_t_bg.gif"><img src="${rootPath}/images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="${rootPath}/images/main/all_t_bg.gif"><img src="${rootPath}/images/icon/baobiao.gif"><span class="title"><strong>桌面背景管理</strong> - 背景添加操作</span></td>
    <td width="3%"  height="36"  align="right" background="${rootPath}/images/main/all_t_bg.gif"><img src="${rootPath}/images/main/all_t_r.gif" ></td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="boxsearch">
      <tr>
        <td colspan="3" valign="top" >
        <table width="100%" border="0" cellpadding="0" cellspacing="5" class="boxsearch">
           <tr>                          
              <td width="20%" align="left" nowrap="nowrap">&nbsp;</td>
              <td width="20%" align="right">背景名：
              </td>  
              <td width="40%" align="left" nowrap="nowrap">
             	 <input class="input" type="text" name="animation.title" id="animation.title"/><font color=red>*</font>
	          </td>
              <td width="20%" align="left">&nbsp;</td>
             </tr>
           
           <tr>                          
              <td width="20%" align="left" nowrap="nowrap">&nbsp;</td>
              <td width="20%" align="right">价格(单位:分)：
              </td>  
              <td width="40%" align="left" nowrap="nowrap">
             	 <input class="input" type="text" name="animation.price" id="animation.price"/><font color=red>*</font>
	          </td>
              <td width="20%" align="left">&nbsp;</td>
             </tr>
           
            <tr>                          
              <td width="20%" align="left" nowrap="nowrap">&nbsp;</td>
              <td width="20%" align="right">手机类型：
              </td>  
              <td width="40%" align="left" nowrap="nowrap">
              <select name="animation.phoneId"  id="animation.phoneId">
	                <option value="">请选择手机类型...</option>
	                <#if phoneTypesList?exists && phoneTypesList.size() != 0>
			        <#list phoneTypesList as phoneTypesVo>
				        <option value="${phoneTypesVo.phoneId?if_exists}">${phoneTypesVo.phoneType?if_exists}</option>
			        </#list>
			        </#if>
	            </select> 
	            <font color=red>*</font>
	          </td>
              <td width="20%" align="left"></td>
             </tr>
            
            <tr>                          
              <td width="20%" align="left" nowrap="nowrap">&nbsp;</td>
              <td width="20%" align="right">背景分类：
              </td>  
              <td width="40%" align="left" nowrap="nowrap">
              <select name="animation.typeId"  id="animation.typeId">
	                <option value="">请选择背景分类...</option>
	                <#if animationTypeList?exists && animationTypeList.size() != 0>
			        <#list animationTypeList as animationTypeVo>
				        <option value="${animationTypeVo.typeId?if_exists}">${animationTypeVo.typeName?if_exists}</option>
			        </#list>
			        </#if>
	            </select> 
	            <font color=red>*</font>
	          </td>
              <td width="20%" align="left"></td>
             </tr>
            
            <tr>
              <td width="20%" align="right" nowrap="nowrap">&nbsp;</td>
              <td width="20%" align="right">背景文件： </td>
              <td width="40%" align="center" height="39">
              <input type="file" name="fileUrl" id="fileUrl" size="50" class="input" onpropertychange="viewImg()"><font color=red>*</font>
              <img id="iconImg" src="" width="54" height="39" style="display:none"/></td>
              <td width="20%" align="left"></td>
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
         <INPUT class=denglu1 type=button value="返回列表" onclick="javascript:window.location.href ='${rootPath}/content/animation/list.action';"/>
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
