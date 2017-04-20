<#assign rootPath=request.scheme+"://"+request.serverName+":"+request.serverPort+request.contextPath />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>广东移百管理系统</title>
<link href="${rootPath}/css/main.css" rel="stylesheet" type="text/css">
<script language="JavaScript"  type="text/javascript" src="${rootPath}/javascript/common/calendar.js"></script>
<script language="JavaScript"  type="text/javascript" src="${rootPath}/javascript/common/base.jsp"></script>
<script src="${rootPath}/javascript/DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${rootPath}/javascript/common/ajax_func.js" type="text/javascript"></script>
<script src="${rootPath}/javascript/common/jquery1.2.6.js" type="text/javascript"></script>
<script src="${rootPath}/javascript/sms.js" type="text/javascript"></script>
</head>

<body style="padding-top:5px;padding-bottom:3px;">
    <form id="smsForm" name="smsForm" method="post" action="send.action" enctype="multipart/form-data" onSubmit="return checkForm();">
<table width="100%" height="99%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="3%"  height="36"  align="left" background="${rootPath}/images/main/all_t_bg.gif"><img src="${rootPath}/images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="${rootPath}/images/main/all_t_bg.gif"><img src="${rootPath}/images/icon/baobiao.gif"><span class="title"><strong>短信管理</strong> -发送短信</span></td>
    <td width="3%"  height="36"  align="right" background="${rootPath}/images/main/all_t_bg.gif"><img src="${rootPath}/images/main/all_t_r.gif" ></td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="boxsearch">
       <tr>
        <td height="30" colspan="3">
        	<span onclick="showhide('search');" style="cursor: pointer;"><img src="../images/icon/icon_redarrow.gif"/>&nbsp;<font style="font-weight: bold">用户号码查询</font></span>
        </td>
       </tr>
       <tr id="search" style="display: none;"><td align="center" colspan="3"><table>
       <tr>
        <td height="30" align="center" colspan="3">厂商：
        	<select id="facId" name="facId" onchange="terMap();" style="width:70px">
	        	<option value="0">----------</option>
				<#list faclist as fac>
					<option value="${fac.fac_id?if_exists}">${fac.fac_name?if_exists}</option>
				</#list>
            </select>
            &nbsp;&nbsp;&nbsp;型号：
            <select id="terId" name="terId" style="width:70px">
	        	<option value="0">----------</option>
            </select>
            &nbsp;&nbsp;&nbsp;平台：
            <select name="terPlat" style="width:70px">
	        	<option value="">----------</option>
				<option value="brew">brew</option>
				<option value="mobile">mobile</option>
            </select>
            &nbsp;&nbsp;&nbsp;申请时间：
            <input type="text" size="10" id="startTime" name="startTime" class="Wdate" value="${startTime?if_exists}" onFocus="WdatePicker({startDate:'%y-%M-01',readOnly:true})"/>
   			至
   			<input type="text" size="10" id="endTime" name="endTime" class="Wdate" value="${endTime?if_exists}" onFocus="WdatePicker({readOnly:true})"/>（留空则不限制）
        </td>
       </tr>
       <tr>
        <td align="center" colspan="3">
        	<textarea name="phoneNum" id="phoneNum" cols="45" rows="2" style="width:440px; color:#b9bbd3; font-size:12px; margin-bottom:10px; overflow:auto"></textarea>
        	<br><font id="total" color="red"></font>
        </td>
       </tr>
       <tr>
        <td height="30" align="center" colspan="3">
        	<INPUT type="button" value="查 询" class="denglu1" onclick="search();" />
        	&nbsp;&nbsp;&nbsp;<INPUT type="button" value="添 加" class="denglu1" onclick="add();" />
        </td>
       </tr>
       </table></td></tr>
              <tr>
        <td width="1%" height="1"><img src="${rootPath}/images/space.gif" width="1" height="1"></td>
        <td width="98%" bgcolor="#F3F3F3"><img src="${rootPath}/images/space.gif" width="1" height="1"></td>
        <td width="1%"><img src="${rootPath}/images/space.gif" width="1" height="1"></td>
       </tr>
       <tr>
        <td height="30" colspan="3">
        	<span onclick="showhide('import');" style="cursor: pointer;"><img src="../images/icon/icon_redarrow.gif"/>&nbsp;<font style="font-weight: bold">批量号码导入</font></span>
        </td>
       </tr>
       <tr id="import" style="display: none;"><td align="center" colspan="3"><table>
       <tr>
        <td height="30" align="center" colspan="3">
			<span>请选择.xls文件：</span><input type="file" name="datafile" id="datafile" />
			<br><font id="fileprom" color="red"></font>
        </td>
       </tr>
       <tr>
        <td height="30" align="center" colspan="3">
        	<INPUT type="button" value="导 入" class="denglu1" onclick="importFile();" />
        </td>
       </tr>
       </table></td></tr>
       <tr>
        <td width="1%" height="1"><img src="${rootPath}/images/space.gif" width="1" height="1"></td>
        <td width="98%" bgcolor="#F3F3F3"><img src="${rootPath}/images/space.gif" width="1" height="1"></td>
        <td width="1%"><img src="${rootPath}/images/space.gif" width="1" height="1"></td>
       </tr>
      <tr>
        <td colspan="3" valign="top" >
        <table width="100%" border="0" cellpadding="0" cellspacing="5" class="boxsearch">
        	<tr>                          
              <td width="20%" align="left" nowrap="nowrap">&nbsp;</td>
              <td width="15%" align="right"><font color=red>*</font>手机号码：
              </td>  
              <td width="40%" align="left" nowrap="nowrap">
             	 <textarea name="phoneNums" id="phoneNums" cols="67" rows="2" style="color:#363636; font-size:12px; margin-bottom:10px; overflow:auto">${phoneNums?if_exists}</textarea>
	          	 <br>&nbsp;&nbsp;<font valign="top" color="red">注：如群发多个号码，则以半角逗号“,”分割。</font>
	          </td>
              <td width="35%" align="left">&nbsp;</td>
            </tr>
            <tr>     
              <td width="20%" align="left">&nbsp;</td>                      
              <td width="15%" align="right" nowrap="nowrap"><font color=red>*</font>消息内容：</td>
              <td width="40%" align="left">
              	<textarea type="text" name="msgContent" id="msgContent" rows="5" cols="67" style="border:1px solid #7F9DB9;
				background-color:#FFF;
				color:#363636;
				font-size:12px;
				padding-left:3px;
				overflow:auto">${msgContent?if_exists}</textarea>	
              </td>
              <td width="35%" align="left">&nbsp;</td>          
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
         <INPUT class="denglu1" type="submit" value="发 送"/>
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
<input type="hidden" id="importFlag" name="importFlag" value="${importFlag?if_exists}" />
<input type="hidden" id="searchFlag" name="searchFlag" value="${searchFlag?if_exists}" />
</form>
</body>
</html>
