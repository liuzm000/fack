<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>业务支撑系统-管理员管理-添加管理员</title>
		<link href="${request.contextPath}/css/tel.css" rel="stylesheet" type="text/css">
        <link href="${request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
         <script type="text/javascript" src="${request.contextPath}/javascript/jquery-core/jquery-1.3.js"></script>
		<script language="JavaScript" type="text/JavaScript">

		function validate()
   		{
   		   var form=document.getElementById("adminform");
   		   
		   if(form.account.value == '')
		   {
			   alert("请填写你的帐号！");
			   form.account.focus();
			   return false;
		   }
		   if(form.password.value == '')
		   {
			   alert("请填写你的密码！");
			   form.password.focus();
			   return false;
		   }else{
		       if(form.re_password.value == '')
		       {
		       alert("请填写你的重复密码！");
			   form.re_password.focus();
			   return false;
		       }
	           var pass1=form.password.value;
	           var pass2=form.re_password.value;
	           if(pass1.length >16 || pass1.length<6 ){
	               alert("密码输入长度在6到16之间，请重新输入！");
	               return false;
	           }
	           if(pass1 != pass2){
	               alert("两次输入的密码不一致，请重新输入!")
	               return false;
	           }
	        }		
       }
       
       
       $(function(){
			$('#role_id').change(function(){
				var val=$('#role_id').val();
				if(val=='251'){//酒店
					 $('#hotelId').attr("disabled",false);
					 $('#hotelId').attr("style","display:block;");
					 $('#restaurantId').attr("disabled",true);
					 $('#restaurantId').attr("style","display:none;");
				}else if(val=='252'){//餐馆
					 $('#restaurantId').attr("disabled",false);
					 $('#restaurantId').attr("style","display:block;");
					 $('#hotelId').attr("disabled",true);
					 $('#hotelId').attr("style","display:none;");
				}else{
					 $('#hotelId').attr("disabled",true);
					 $('#hotelId').attr("style","display:none;");
					 $('#restaurantId').attr("disabled",true);
					 $('#restaurantId').attr("style","display:none;");
				}
			});
		}) ;
</script>
	</head>

	<body style="padding-top:5px;padding-bottom:3px;padding-left:5px;">
<table width="100%" height="99%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="3%"  height="36"  align="left" background="${request.contextPath}/images/main/all_t_bg.gif"><img src="${request.contextPath}/images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="${request.contextPath}/images/main/all_t_bg.gif"><img src="${request.contextPath}/images/icon/gl.gif">
	<span class="title">系统管理 - <strong>添加管理员</strong></span></td>
    <td width="3%"  height="36"  align="right" background="${request.contextPath}/images/main/all_t_bg.gif"><img src="${request.contextPath}/images/main/all_t_r.gif" ></td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
	<!--xxxxxx-->
		<form id="adminform" name="adminform" method="post" action="${request.contextPath}/permission/addAdmin.action" onSubmit="return validate();">
			<table width="98%" border="0" cellspacing="0" cellpadding="0" class="boxsearch">
      <tr>
        <td colspan="3">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="3" valign="top" >
			<table border="0" cellpadding="0" cellspacing="5" class="boxsearch2" align="center">
				
				
				
				<tr>
					<td width="30%" align="right">
						* 登录帐号
					</td>
					<td width="70%" align="left">
						<input class="input" type="text" name="account" id="account">
					</td>
				</tr>
				<tr>
					<td width="30%" align="right">
						* 登录密码
					</td>
					<td width="70%" align="left">
						<input class="input" type="password" name="password" id="password">
					</td>
				</tr>
				<tr>
					<td width="30%" align="right">
						* 密码确认
					</td>
					<td width="70%" align="left">
						<input class="input" type="password" name="re_password" id="re_password">
					</td>
				</tr>
				<tr>
					<td width="30%" align="right">
						* 管理员角色
					</td>
					<td width="70%" align="left">
						<select name="role_id" id="role_id">
						    <#list roleList as role>	
						    	<option value="${role.role_id?if_exists}">${role.role_name?if_exists}</option>
						    </#list>
						 </select>
					</td>
				</tr>
				<tr>
					<td width="40%" align="right">
						所属酒店/餐馆
					</td>
					<td width="60%" align="left">
						<select name="hotelId" id="hotelId" disabled="disabled" style="display: none;">
						    <#list hotelList as hotel>	
						    	<option value="${hotel.id?if_exists}_${hotel.name?if_exists}">${hotel.name?if_exists}</option>
						    </#list>
						 </select>
						<select name="restaurantId" id="restaurantId" disabled="disabled" style="display: none;">
						    <#list restaurantList as hotel>	
						    	<option value="${hotel.id?if_exists}_${hotel.name?if_exists}">${hotel.name?if_exists}</option>
						    </#list>
						 </select>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right">
						描述
					</td>
					<td width="70%" align="left">
						<textarea name="description" id="description"></textarea>
					</td>
				</tr>
				<tr align="center">
					<td colspan="2" height="20"></td>
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
           <INPUT class="denglu1" type="submit" value="添加">
		   <INPUT class=denglu1 type=button value="返回"	onclick="javascript:window.history.go(-1);">
       </td>
      </tr>
    </table>
		</form>
			<!--xxxxxx-->
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
