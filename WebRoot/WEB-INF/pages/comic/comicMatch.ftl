<html>
<head>
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>广东移百管理系统</title>
<link href="../css/main.css" rel="stylesheet" type="text/css">
<script language='javascript'>
function dosubmit()
{
if (!checkpage())return;
document.addForm.submit();
}
function checkpage()
{
	var el = document.getElementsByTagName('input');     
	var len = el.length;   
	var num=0;  
	for(var i=0; i<len; i++){         
		if((el[i].type=="checkbox") && (el[i].name=="ter") && (el[i].checked == true)  )         {             
			  num++;
		}   
	} 
	if ( num == 0 ){
		alert("请选择机型!");
		return false;
	}
return true;
}

function checkAll() {    
	var str_ter_id="|"+"${ter_id_str?if_exists}";
	//var str_ter_id="2|3|4|";
	var el = document.getElementsByTagName('input');     
	var len = el.length;     
	for(var i=0; i<len; i++){         
		if((el[i].type=="checkbox") && (el[i].name=="ter") && (str_ter_id.indexOf("|"+el[i].value+"|")>-1) )         {             
			el[i].checked = true;         
		}   
	} 
} 
</script>
</head>
<body style="padding-top:5px;padding-bottom:3px;" onLoad="checkAll();" >
<form action="${request.contextPath}/comic/comicMatch.action" method="post" name="addForm">
<input type="hidden" name="id" value="${id?if_exists}"/>
<table width="100%" height="99%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="3%"  height="36"  align="left" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="../images/main/all_t_bg.gif"><img src="../images/icon/gl.gif"><span class="title">动漫管理 - 动漫信息</span></td>
    <td width="3%"  height="36"  align="right" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_r.gif" ></td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox"><font color=red>${showinfo?if_exists}</font>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab" style="margin-top:5px;">
        <tr>
          <th colspan="9">动漫信息</th>
        </tr>
        <tr>
          <th width="129">编号ID</th>
          <th width="150">描述名称</th>
          <th width="175">所属动漫名称</th>
          <th width="186">封面文件</th>
          <th width="234">添加时间</th>
        </tr>

  <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
    <td height="14">${comic.id?if_exists}</td>
    <td>${comic.name?if_exists}</td>
    <td>${comic.base_name?if_exists}</td>
    <td><img src="${request.contextPath}/download/comic_source/comic/${comic.cover_path?if_exists}?rd=${comic.update_date?string('HH:mm:ss')}" border="0" ></td>
    
    <td>${comic.create_date?string('yyyy-MM-dd')} </td>
  </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab" style="margin-top:5px;">
        <tr>
          <th colspan="9">终端信息表</th>
        </tr>
        <tr>
          <th width="275">厂商</th>
          <th width="955">勾选机型</th>
        </tr>

<#list facList as fL>
  <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
    <td height="14">${fL.fac_name?if_exists}</td>
    <td>
	<#if (fL.terList)?exists>
	<#list fL.terList as tL>
		<input name="ter" type="checkbox" id="ter"  value="${tL.ter_id?if_exists}" >${tL.ter_name?if_exists}
	</#list>
	</#if>
	</td>
  </tr>
</#list>
	<tr>
        <td height="44" colspan="3" align="center" valign="middle" class="down"><br/>
		<a href="#" onClick="dosubmit()"><img src="../images/buttom/buttom_tijiao.gif" width="64" height="21" border="0" ></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:history.go(<#if count_back?exists>${count_back}<#else>-1</#if>);'><img src="../images/buttom/buttom_fanhui.gif" width="64" height="21" border="0" ></a>
        </td>
        </tr>
		
      </table>
      <p>&nbsp;</p></td>
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
