<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>机型设备管理 - 添加机型设备</title>
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.add li {
	height:30px;
}
.add li span{
	float:left;
	width:150px;
	text-align:right;
}
-->
</style>
<script type="text/javascript">
function $(id)
{
return document.getElementById(id);
}

function dosubmit()
{
if ($('name').value==''){
	alert('请输入机型设备名称！');
	$('name').focus();
	return;
}
if ($('code').value==''){
	alert('请输入机型编码！');
	$('code').focus();
	return;
}
if ($('des').value.length>100){
	alert('描述不能超过100字符！');
	$('des').focus();
	return;
}

var filePath = $("datafile").value;
		if (filePath == ""){
			alert("你还没有选择图片文件!");
			return  ;	
		}
		if ( filePath.lastIndexOf(".") == -1){
	    	alert("不支持的文件格式!仅支持上传jpg/jpeg/gif/png文件");
			return ;
		}
		var fileType = filePath.substring(filePath.lastIndexOf(".")+1,filePath.length);
		fileType = fileType.toLowerCase();
					
		if ( fileType !='jpg' && fileType !='gif' && fileType!='png' && fileType!='jpeg' ){			
		    alert("仅支持上传jpg/jpeg/gif/png文件。不支持的文件格式:"	+fileType);
			return  ;
		}

$("terAdd").submit();
}
</script>

</head>

<body>

<div class="top">
<div class="topright"></div>
<div class="topleft"></div>
<img src="../images/icon/shuju.gif"/><span class="title">机型设备管理 - 添加机型设备</span>
</div>
<div class="tmiddle">
&nbsp;
<div class="boxsearch">
  <form id="terAdd" name="terAdd" method="post" action="terAdd.action?flag=1" enctype="multipart/form-data">
  <div class="add">
  <ul>
  <li>
    <span>机型名称：</span><input type="text" name="ter.ter_name" id="name" maxlength="20"/>
    </li>
  <li>
    <span>终端编码：</span><input type="text" name="ter.ter_code" id="code" maxlength="30"/>
    </li>
    <li><span>厂商：</span><select name='ter.fac_id'><#list faclist as fl>
   <option value='${fl.fac_id?if_exists}'>${fl.fac_name?if_exists}</option></#list>
   </select>
   </li>
   <li><span>Java支持：</span>
    <input type="radio" class='notext' name="ter.ter_java" value="0" checked />不支持
    <input type="radio" class='notext' name="ter.ter_java" value="1"/>支持
   </li>
   <li><span>彩信支持：</span>
    <input type="radio" class='notext' name="ter.ter_mms" value="0" checked />不支持
    <input type="radio" class='notext' name="ter.ter_mms" value="1"/>支持
   </li>
      <li><span>WAP支持：</span>
    <input type="radio" class='notext' name="ter.ter_wap" value="0" checked />不支持
    <input type="radio" class='notext' name="ter.ter_wap" value="1"/>支持
   </li>
        <li><span>EVDO支持：</span>
    <input type="radio" class='notext' name="ter.ter_evdo" value="0" checked />不支持
    <input type="radio" class='notext' name="ter.ter_evdo" value="1"/>支持
   </li>
     <li><span>平台：</span>
    <select name='ter.ter_plat'>
    <option value='mobile'>mobile</option>
    <option value='wince'>wince</option>
    <option value='brew'>brew</option>
    <option value='android'>android</option>
    <option value='blackberry'>blackberry</option>
    <option value='palm'>palm</option>
    <option value='mtk'>mtk</option>
    <option value='ios'>ios</option>
    </select>
   </li>
      <li><span>分辨率：</span><select name='ter.vir_id'><#list virlist as vl>
   <option value='${vl.vir_id?if_exists}'>${vl.vir_name?if_exists}</option></#list>
   </select>
   </li>
   <li><span>图片：</span><input type="file" name="datafile" id="datafile" />
   </li>
   <li><span>描述：</span><textarea name="ter.ter_des" id='des'></textarea>
   </li>
  </ul>
</div>
      <div class="grayline"></div>
      <div class="boxsearch2"><a href="javascript:dosubmit()"><img src="../images/buttom/buttom_tijiao.gif" alt="提交" /></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:window.history.back()'><img src="../images/buttom/buttom_fanhui.gif" alt="返回"/></a></div>
  </form>
</div>
</div>
<div class="bottom">
<div class="bottomright"></div>
<div class="bottomleft"></div>
</div>
</body>
</html>