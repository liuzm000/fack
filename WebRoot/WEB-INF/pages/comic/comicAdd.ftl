<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>广东移百管理系统</title>
<link href="../css/main.css" rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript">
function $(id){
	return document.getElementById(id);
}

function dosubmit()
{
if (!checkpage())return;
document.addForm.submit();
}

function changetype()
{
	document.addForm.submit();
}

function checkpage()
{
if ($('name').value=='' || $('type').value==0 || $('base_id').value==0 || $("datafile").value=='' ){
	alert('带*号不能为空');
	return;
}

var filePath = $("datafile").value;
if ( filePath.lastIndexOf(".") == -1){
	alert("不支持的文件格式!");
	return ;
}
var fileType = filePath.substring(filePath.lastIndexOf(".")+1,filePath.length);
fileType = fileType.toLowerCase();
			
if ( fileType !='jpg' && fileType !='gif' && fileType!='png' && fileType!='jpeg' ){			
	alert("不支持的文件格式:"	+fileType);
	return  ;
}

return true;
}
</script>
</head>

<body style="padding-top:5px;padding-bottom:3px;">
<form action="${request.contextPath}/comic/comicAdd.action" method="post" enctype="multipart/form-data" name="addForm">

<table width="100%" height="99%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="3%"  height="36"  align="left" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="../images/main/all_t_bg.gif"><img src="../images/icon/gl.gif"><span class="title">动漫管理 - 添加动漫</span></td>
    <td width="3%"  height="36"  align="right" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_r.gif" ></td>
  </tr>
  <tr>
  
    <td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox"><table width="98%" border="0" cellspacing="0" cellpadding="0" class="boxsearch">
		<tr>
        <td colspan="3">&nbsp;</td>
        </tr>
      <tr>
        <td colspan="3" valign="top" >
        <table width="100%" border="0" cellpadding="0" cellspacing="5" class="boxsearch2" >
         <tr>
            <td width="20%" align="right">&nbsp;</td>
            <td width="80%" align="left"><font color=red>${showinfo?if_exists}</font></td>
          </tr>
          <tr>
            <td align="right">动漫类型：</td>
            <td align="left"><select name="type" id="type" class="select" onChange="changetype()">
			  <option value="0" <#if type?exists && type==0>selected</#if> >请选择动漫类型</option>
              <option value="1" <#if type?exists && type==1>selected</#if> >连载漫画</option>
              <option value="2" <#if type?exists && type==2>selected</#if> >四格漫画</option>
            </select>
              <font color="red">*</font></td>					
          </tr>
          <tr>
            <td align="right">动漫基本信息名称：</td>
            <td align="left">
				<select name="base_id" id="base_id" class="select" width="20">
						<option value="0" selected>请选择动漫</option>
						<#if baseComicList?exists>
							<#list baseComicList as bCL>
							<option value="${bCL.base_id?if_exists}">${bCL.base_name?if_exists}</option>
							</#list>
						</#if>
				</select>	
				<font color="red"> *</font></td>
          </tr>
          <tr>
            <td align="right">描述名称：</td>
            <td align="left"><input name="name" type="text" id="name" size="50" maxlength="50">
                <font color="red">* </font> 描述匹配机型 </td>
          </tr>
          <tr>
            <td align="right" id="f_right">封面文件上传：</td>
            <td align="left"  id="f_left"><input type="file" name="datafile" id="datafile" />
              <font color="red">*</font></td>
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
        <td height="44" colspan="3" align="center" valign="middle" class="down"><a href="#" onClick="dosubmit()"><img src="../images/buttom/buttom_tijiao.gif" width="64" height="21" border="0" ></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:history.go(<#if count_back?exists>${count_back}<#else>-1</#if>);'><img src="../images/buttom/buttom_fanhui.gif" width="64" height="21" border="0" ></a>
        
        </td>
        </tr>
    </table>
    </td>
  </tr>
  
  <tr>
    <td height="26" align="left"background="../images/main/all_d_bg.gif"><img src="../images/main/all_d_l.gif" ></td>
    <td height="26" background="../images/main/all_d_bg.gif">&nbsp;</td>
    <td height="26" align="right" background="../images/main/all_d_bg.gif"><img src="../images/main/all_d_r.gif" ></td>
  </tr>
</table>
<input type="hidden" name="count_back" value="<#if count_back?exists>${count_back}<#else>-1</#if>"/>
</form>

</body>

</html>
