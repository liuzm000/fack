
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>业务支撑系统-模块管理-修改模块</title>
<link href="${request.contextPath}/css/tel.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
<script src="${request.contextPath}/javascript/common/operation.js" type="text/javascript"></script>
<script type="text/javascript">
   function submitform(form,operation){
   		if(operation=="${request.contextPath}/permission/modifyModule.action")
   		{
      		if(form.module_name=='')
      		{
         		alert('模块名不能为空!');
         		return;
      		}
   		}
   		//alert(operation);
   		form.action=operation;
   		form.submit();
   }
   
   function validate(){
        if(document.getElementById("module_name").value.length<1){
	          alert("模块名不能为空!");
	          document.getElementById("module_name").focus();
	          return ;
	         }
	    document.forms["Form1"].action = "modifyModule.action";
		document.forms["Form1"].submit();
   }
</script>
</head>

<body style="padding-top:5px;padding-bottom:3px;padding-left:5px;">
<table width="100%" height="99%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="3%"  height="36"  align="left" background="${request.contextPath}/images/main/all_t_bg.gif"><img src="${request.contextPath}/images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="${request.contextPath}/images/main/all_t_bg.gif"><img src="${request.contextPath}/images/icon/gl.gif">
	<span class="title">系统管理 - <strong>修改模块</strong></span></td>
    <td width="3%"  height="36"  align="right" background="${request.contextPath}/images/main/all_t_bg.gif"><img src="${request.contextPath}/images/main/all_t_r.gif" ></td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
	<!--xxxxxx-->
<form method="post" action="" name="Form1" id="Form1" class="savehis">  
<input type="hidden" name="module_id" value="${module.module_id?if_exists}">
<table width="98%" border="0" cellspacing="0" cellpadding="0" class="boxsearch">
      <tr>
        <td colspan="3">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="3" valign="top" >
<table border="0" cellpadding="1" cellspacing="5" class="boxsearch2">
<tr bgcolor="#FFFFFF">
    <td width="50%" align="right">* 模块名</td>
    <td width="50%" align="left"><input class="input" type="text" id="module_name" name="module_name" value="${module.module_name?if_exists}"></td> 
  </tr>
  <tr bgcolor="#FFFFFF">
    <td width="50%" align="right">模块描述</td>
    <td width="50%" align="left"><textarea name="description" width="40" height="40">${module.description?if_exists}</textarea></td>
  </tr>
  <input type="hidden" name="parent_module_id" value="${module.parent_module_id?if_exists}">
  <input type="hidden" name="module_type" value="${module.module_type?if_exists}">
  <tr bgcolor="#FFFFFF">
    <td width="50%" align="right"> 模块地址</td>
    <td width="50%" align="left"><input class="input" type="text" name="module_url" value="${module.module_url?if_exists}" size="40"></td>
  </tr>
 

</table>
</td>
      </tr>
      <tr>
        <td width="1%" height="1"><img src="../images/space.gif" width="1" height="1"></td>
        <td width="98%" bgcolor="#F3F3F3"><img src="../images/space.gif" width="1" height="1"></td>
        <td width="1%"><img src="../images/space.gif" width="1" height="1"></td>
      </tr>
      <tr>
        <td height="44" colspan="3" align="center" valign="middle" class="down">
           <INPUT class="denglu1" type="button" value="修改" onclick ="validate();" >
     <INPUT class=denglu1 type=button value="排序" onclick="submitform(this.form,'${request.contextPath}/permission/goOrderModule.action')">
      <INPUT class=denglu1 type=button value="返回" onclick="javascript:window.history.go(-1);"> 
       </td>
      </tr>
    </table>
</form>

	</td>
  </tr>
  <tr>
    <td height="26" align="left"background="${request.contextPath}/images/main/all_d_bg.gif"><img src="${request.contextPath}/images/main/all_d_l.gif" ></td>
    <td height="26" background="${request.contextPath}/images/main/all_d_bg.gif">&nbsp;</td>
    <td height="26" align="right" background="${request.contextPath}/images/main/all_d_bg.gif"><img src="${request.contextPath}/images/main/all_d_r.gif" ></td>
  </tr>
</table>
</body>
</html>
