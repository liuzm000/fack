<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>业务支撑系统-权限管理-修改角色权限</title>
		<link href="${request.contextPath}/css/tel.css" rel="stylesheet" type="text/css">
		<link href="${request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
		<script>
			function submitform(form,rolename,operation)
			{
				var notice ='你确定要修改'+rolename+'的权限吗？';
				var ret = confirm(notice);
			    if(!ret) 
			   		return;
				form.action=operation;
				form.submit();
 			}
		</script>
	</head>

	<body scroll="auto">
	<form action="${request.contextPath}/permission/modifyAdminRolePermission.action" method="post">
		<input type="hidden" name="role_id" <#if role?exists> value="${role.role_id?if_exists}" </#if> />
		<table border="0" cellpadding="6" cellspacing="1"
			style="margin:10px 0px 0px 10px;width:70%;background-color:#B7CDE4">

			<tr bgcolor="#FFFFFF">
				<td width="30%" align="center">
				<#if role?exists>	${role.role_name?if_exists} </#if>
				</td>
				<td>
				<script>
				</script>
				<script>
									var rolePermissionArray = new Array();
								  var permissionModuleIDArray = new Array();
								  var permissionModuleNameArray = new Array();
								  var varPermissionArray = new Array(permissionModuleIDArray, permissionModuleNameArray);
								  function writeSpace(num) 
								  {
									    var space = "";
									    for (var i = 0; i < 2 * num; i++) 
									    {
									        space = space + "&nbsp;";
									    }
									    return space;
								  }
								  
								  function writeHead()
								  {      
								       document.write("<input type='checkbox'  name='ids'  value='"+ varPermissionArray[0][0] + "' checked onclick='return false'>"+ varPermissionArray[1][0]+"</input>");
								  }
								  
								  function writeLine(tempNum,id,moduleName)
								  {  
								     //alert(id);
								     var temp  = tempNum+ "<input type='checkbox' name='ids' value='"+ id + "' onclick=javascript:checkSelect(this.checked,'" + id +"');>";
								     temp+= moduleName + "<br>";
								     //alert(temp);
								     return temp;
								  }
									function getTemp(moduleCode)
								  {
								       var temp;
									   for (var i = 1; i < permissionModuleIDArray.length; i++) 
									   {				        
									        temp = writeSpace(moduleCode.length/2*2);
									        /**
									        switch (moduleCode.length) {
									          case 1:
									            temp = writeSpace(0);
									            break;
									          case 2:
									            temp = writeSpace(2);
									            break;
									          case 4:
									            temp = writeSpace(4);
									            break;
									          case 6:
									            temp = writeSpace(6);
									            break;
									          case 8:
									            temp = writeSpace(8);
									            break;
									          case 10:
									            temp = writeSpace(10);
									            break;
									          default:
									        }
									        */
									        return temp;
									    }
									}
							  				
								  function alignModule() 
								  {
     								    writeHead();
     								    var l,k=0;
									    var tableString = "<br>";
  									     for(var i=1;i< permissionModuleIDArray.length;i++)
									    {   
										   tableString += writeLine(getTemp(varPermissionArray[0][i]),varPermissionArray[0][i],varPermissionArray[1][i]);
								        }
								        document.write(tableString);
								  }
								   
								  function setChecked(chkvalue)
								  {
										for(var k=0;k<form1.elements.length;k++)
										{   
											if(form1.elements[k].name=='ids'&&form1.elements[k].value==chkvalue)
											{
												form1.elements[k].checked=true;
											}
										}
									}
									function setChecks()
									{
										for(var i=0;i<permissionModuleIDArray.length;i++)
										{
											for(var j=0;j<rolePermissionArray.length;j++)
											{
												if(permissionModuleIDArray[i]==rolePermissionArray[j])
												{
													setChecked(rolePermissionArray[j]);
												}
											}
										}
									}
								   
									function setAllChecked(ischecked)
							 		{
								 		for(var k=0;k<form1.elements.length;k++)
										{   
											if(form1.elements[k].name=='ids')
											 {
											 	form1.elements[k].checked=ischecked;
											 }
										}
							 		}
							 		var form1=document.forms[0];
							 		function checkSelect(ischecked,chkname) 
									{	
			
										for(i=0;i<form1.elements.length;i++)
										{
									  		if(form1.elements[i].name=='ids'&&form1.elements[i].value.substring(0,chkname.length)==chkname) 
									  			form1.elements[i].checked=ischecked;
									  	}
										if(ischecked==true)
										{
											for(i=0;i<form1.elements.length;i++)
											{
												for(j=1;j<chkname.length/2;j++)
									  				if(form1.elements[i].name=='ids'&&form1.elements[i].value==chkname.substring(0,2*j)) 
									  					form1.elements[i].checked=ischecked;
									  		}	
										}
										return;
									}
							<#assign i=0/>
							<#if moduleList?exists>
							<#list moduleList as module>
								permissionModuleIDArray["${i}"] = "${module.module_id?if_exists}";
								permissionModuleNameArray["${i}"] = "${module.module_name?if_exists}";
								<#assign i=i+1> 
							</#list>
							</#if>
							alignModule();
							<#assign i=0/>
							<#if rolePermissionList?exists>
							<#list rolePermissionList as rolePermission>
								rolePermissionArray["${i}"] = "${rolePermission.module_id?if_exists}";
								<#assign i=i+1/> 
							</#list>
							</#if>
							setChecks();
						</script>
				</td>
			</tr>

			<tr align="center">
				<td colspan="2" bgcolor="#ffffff">
					<INPUT class='denglu1' type='button' value="保存"
						onclick="submitform(this.form,'${role?if_exists.role_name?if_exists}','${request.contextPath}/permission/modifyAdminRolePermission.action')"/>
					<#if actionName?exists && actionName.equals("mod")>
					<INPUT class=denglu1 type=button value="返回"
						onclick="javascript:window.history.go(-3);"/>
					<#else>
					<INPUT class=denglu1 type=button value="返回"
						onclick="javascript:window.history.go(-1);"/>
					</#if>	
						
				</td>
			</tr>
		</table>
	</form>
	</body>
</html>
