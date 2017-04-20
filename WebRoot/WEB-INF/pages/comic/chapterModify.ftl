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

var filePath = $("datafile").value;
var fileType = filePath.substring(filePath.lastIndexOf(".")+1,filePath.length);
fileType = fileType.toLowerCase();
$("fileType").value=fileType;
document.addForm.submit();
}

function checkNum(value){
	var Letters = "0123456789";
	for (i=0; i<value.length; i++){ 
		var CheckChar = value.charAt(i); 
		if (Letters.indexOf(CheckChar) == -1){ 
			return false; 
		} 
	}	
	return true;
}

function checkpage()
{
if ($('chapter_id').value=='' || $('name').value=='' || $("datafile").value=='' ){
	alert('带*号不能为空');
	return;
}
if ( !checkNum($('chapter_id').value) ){
	alert("章节编号必须为数字!");
	return;
}
var filePath = $("datafile").value;
if ( filePath.lastIndexOf(".") == -1){
	alert("不支持的文件格式!");
	return ;
}
var fileType = filePath.substring(filePath.lastIndexOf(".")+1,filePath.length);
fileType = fileType.toLowerCase();
			
if ( fileType !='bpk' && fileType !='zip'  ){		
	alert("不支持的文件格式:"	+fileType);
	return  ;
}

return true;
}
</script>
</head>

<body style="padding-top:5px;padding-bottom:3px;">
<form action="${request.contextPath}/comic/chapterModify.action" method="post" enctype="multipart/form-data" name="addForm">
<input type="hidden" name="id" value="${id?if_exists}"/>
<input type="hidden" name="comic_id" value="${chapter.comic_id?if_exists}"/>
<input type="hidden" name="fileType" id="fileType" value=""/>
<table width="100%" height="99%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="3%"  height="36"  align="left" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="../images/main/all_t_bg.gif"><img src="../images/icon/gl.gif"><span class="title">动漫管理 - 修改章节</span></td>
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
            <td align="right">&nbsp;</td>
            <td align="left"><font color=red>${showinfo?if_exists}</font></td>
          </tr>
         <tr>
           <td align="right">动漫编号：</td>
           <td align="left">${chapter.comic_id?if_exists}</td>
         </tr>
         <tr>
           <td align="right">章节编号：</td>
           <td align="left"><input name="chapter_id" type="text" id="chapter_id" value="${chapter.chapter_id?if_exists}" size="50" maxlength="20">
               <font color="red">*</font></td>
         </tr>
          <tr>
            <td width="20%" align="right">章节名称：</td>
            <td width="80%" align="left"><input name="name" type="text" id="name" value="${chapter.chapter_name?if_exists}" size="50" maxlength="20">
              <font color="red">*</font></td>
            </tr>
          <tr>
            <td align="right" id="f_right">章节文件上传：</td>
            <td align="left"  id="f_left"><input type="file" name="datafile" id="datafile" />
              ${chapter.file_path?if_exists}<font color="red">*</font></td>
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
