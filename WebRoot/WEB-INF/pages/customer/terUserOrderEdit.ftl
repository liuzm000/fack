<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>MISMP</title>
		<link href="${request.contextPath}/css/tel.css" rel="stylesheet" type="text/css">
		<link href="${request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/JavaScript">		
		
		function validate()
   		{
   		   var form=document.getElementById("adminform");
   		   return true;
       }
		</script>
	</head>

	<body style="padding-top:5px;padding-bottom:3px;padding-left:5px;">
<table width="100%" height="99%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="3%"  height="36"  align="left" background="${request.contextPath}/images/main/all_t_bg.gif"><img src="${request.contextPath}/images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="${request.contextPath}/images/main/all_t_bg.gif"><img src="${request.contextPath}/images/icon/gl.gif">
	<span class="title"><strong>终端用户订购状态修改</strong> - <strong>订购信息详情</strong></span></td>
    <td width="3%"  height="36"  align="right" background="${request.contextPath}/images/main/all_t_bg.gif"><img src="${request.contextPath}/images/main/all_t_r.gif" ></td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
	<!--xxxxxx-->
		<form id="terUserOrderform" name="terUserOrderform" method="post" action="${request.contextPath}/customer/terUserOrderEdit.action" onSubmit="return validate();">
			<table width="98%" border="0" cellspacing="0" cellpadding="0" class="boxsearch">
      <tr>
        <td colspan="3">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="3" valign="top" >
			<table border="0" cellpadding="1" cellspacing="5" class="boxsearch2" align="center">
				
				<input type="hidden" name="terUserOrderVO.id" value="${terUserOrderVO.id?if_exists}"/>
				<tr bgcolor="#FFFFFF">
					<td width="20%" align="right">
						ID
					</td>
					<td width="80%" align="left">
						${terUserOrderVO.id?if_exists}
					</td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td width="20%" align="right">
						手机号码
					</td>
					<td width="80%" align="left">
						${terUserOrderVO.phoneNum?if_exists}
					</td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td width="20%" align="right">产品名称</td>
					<td width="80%" align="left">
						${terUserOrderVO.proName?if_exists}
					</td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td width="20%" align="right">创建时间</td>
					<td width="80%" align="left">
						${terUserOrderVO.createTime?if_exists}
					</td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td width="20%" align="right">
						订购状态</td>
					<td width="80%" align="left">
					 	<#if terUserOrderVO.status==1>
						<input type="radio" class='notext' name="terUserOrderVO.status" id="r1" value="1" <#if terUserOrderVO.status==1>checked</#if> />申请
						</#if>
						 <input type="radio" class='notext' name="terUserOrderVO.status" id="r0" value="0" <#if terUserOrderVO.status==0>checked</#if> />开通
     					 <input type="radio" class='notext' name="terUserOrderVO.status" id="r2" value="2" <#if terUserOrderVO.status==3>checked</#if> />暂停
    					 <input type="radio" class='notext' name="terUserOrderVO.status" id="r3" value="3" <#if terUserOrderVO.status==4>checked</#if> />预注销
     					 <input type="radio" class='notext' name="terUserOrderVO.status" id="r4" value="4" <#if terUserOrderVO.status==6>checked</#if> />注销

					</td>
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
           <#if terUserOrderVO.status!=4>
           <INPUT class="denglu1" type="submit" value="修改">
		   </#if>
		   <INPUT class=denglu1 type=button value="返回" 	onclick="javascript:window.history.go(-1);">
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
