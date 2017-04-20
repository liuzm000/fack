<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>发送彩信</title>
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<script src="${request.contextPath}/javascript/DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${request.contextPath}/javascript/common/ajax_func.js" type="text/javascript"></script>
<script src="${request.contextPath}/javascript/common/jquery1.2.6.js" type="text/javascript"></script>
<script src="${request.contextPath}/javascript/sms.js" type="text/javascript"></script>

<style type="text/css">
<!--
.add li{
	padding:5px 0;
}
.add li span{
	float:left;
	width:150px;
	text-align:right;
}
input{
height:24px;
}
#left_b {
display:inline;
float:left;
height:300px;
margin:12px 8px 0 28px;
width:157px;
background:url(../images/mms/bg_mms_b.png) no-repeat;
text-align:center;
}

#left_b #pre_content{
    text-align:center;
	height:154px;
	overflow:hidden;
	width:129px; 
	margin:23px 0 2px 14px; 
	*margin-left:0;
	}
#preview_fake{ /* 该对象用户在IE下显示预览图片 */      
    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale); 
}      
#preview_size_fake{ /* 该对象只用来在IE下获得图片的原始尺寸，无其它用途 */      
    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);        
    visibility:hidden;      
}      
#preview{ /* 该对象用户在FF下显示预览图片 */      
    width:128px;      
    height:154px;      
}      

-->
</style>
<script type="text/javascript">
function $(id)
{
return document.getElementById(id);
}
</script>

</head>

<body>

<div class="top">
<div class="topright"></div>
<div class="topleft"></div>
<blockquote>
  <p><img src="../images/icon/shuju.gif"/><span class="title">短信管理 - 发送彩信</span>
  </p>
</blockquote>
</div>
<div class="tmiddle">
&nbsp;

<table style='width:96%;margin:0 20px;border:1px solid #F3F3F3'>
       <tr>
        <td height="30" colspan="3">
        	<span onclick="showhide('search');" style="cursor: pointer;"><img src="../images/icon/icon_redarrow.gif"/>&nbsp;<font style="font-weight: bold">用户号码查询</font></span>
        </td>
       </tr>
       <tr id="search" style="display: none;"><td align="center" colspan="3"><table>
       <tr>
       <form id='getNum'>
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
            <select name="terPlat" id="terPlat" style="width:70px">
	        	<option value="">----------</option>
				<option value="brew">brew</option>
				<option value="mobile">mobile</option>
            </select>
            &nbsp;&nbsp;&nbsp;申请时间：
            <input type="text" size="12" id="startTime" name="startTime" class="Wdate" value="${startTime?if_exists}" onFocus="WdatePicker({startDate:'%y-%M-01',readOnly:true})"/>
   			至
   			<input type="text" size="12" id="endTime" name="endTime" class="Wdate" value="${endTime?if_exists}" onFocus="WdatePicker({readOnly:true})"/>（留空则不限制）
        </td>
       </tr>
       <tr>
        <td align="center" colspan="3">
        	<textarea name="phoneNum" id="phoneNum" cols="45" rows="3" style="width:85%; color:#b9bbd3; font-size:12px; margin-bottom:10px; overflow:auto" readonly='readonly'></textarea>
        	<br><font id="total" color="red"></font>
        </td>
       </tr>
       <tr>
        <td height="30" align="center" colspan="3">
        	<input type="button" value="查 询" class="denglu1" onclick="search();" />
        	&nbsp;&nbsp;&nbsp;<input type="button" value="添 加" class="denglu1" onclick='asd();' /> 
        	</form>
        	<script type='text/javascript'>
        	function asd(){
        		phoneNums=$('facAdd').phoneNums.value;
        		phoneNum=$('getNum').phoneNum.value;
        		if (phoneNums.trim() !=""){
        			$('facAdd').phoneNums.value=phoneNums+","+phoneNum;
        		}else{
        			$('facAdd').phoneNums.value=phoneNums+phoneNum;
        		}
        		
        	}
        	</script>
        	
        </td>
       </tr>
       </table></td></tr>
              <tr>
        <td width="1%" height="1"><img src="${request.contextPath}/images/space.gif" width="1" height="1"></td>
        <td width="98%" bgcolor="#F3F3F3"><img src="${request.contextPath}/images/space.gif" width="1" height="1"></td>
        <td width="1%"><img src="${request.contextPath}/images/space.gif" width="1" height="1"></td>
       </tr>
       <tr>
        <td height="30" colspan="3">
        	<span onclick="showhide('import');" style="cursor: pointer;">
        	<img src="../images/icon/icon_redarrow.gif"/>&nbsp;<strong>批量号码导入</strong></span>
        </td>
       </tr>
       <tr id="import" style="display: none;">
       <td align="center" colspan="3">
       <form method="post" id='smsForm' name='smsForm' enctype="multipart/form-data">
       <table>
       <tr>
        <td height="30" align="center" colspan="3">
			<span>请选择.xls文件：</span><input type="file" name="excelfile" id="datafile" />
			<input type='hidden' name='phoneNums' id='copyPhoneNums' value="" />
			<br><font id="fileprom" color="red"></font>
        </td>
       </tr>
       <tr>
        <td height="30" align="center" colspan="3">
        	<input type="button" value="导 入" class="denglu1" onclick="$('copyPhoneNums').value=$('phoneNums').value;importFile();" />
        	<a style='margin-left:30px;text-decoration: underline;' href='${request.contextPath}/SMSModel.xls'>下载Excel模板文件</a>
        </td>
       </tr>
       </table>
       </form>
</td></tr></table>
<div class="boxsearch">
  <form id="facAdd" name="facAdd" method="post" action="sendMms.action" enctype="multipart/form-data" onsubmit='return dosubmit()'>
  <div>
  <div id='left_b'><!-- 彩信发送图片预览-->
 <div id='pre_content'>
        <div id="preview_fake">
            <img id="preview" alt="彩信上传图片" onload="onPreviewLoad(this)"/>
        </div>
       </div>
       <strong>预览</strong>
     </div>
  <div class="add">
  <ul>
  <li>
    <span><font color='red'>*</font>手机号码：</span>
    <textarea id="phoneNums"  name="phoneNums" style='width:415px;font-size:12px;' rows="4" onkeyup="value=value.replace(/[^\d,]+/,'')" >${phoneNums?if_exists}</textarea><br/><center>(手机号码请用英文逗号 [ , ] 隔开)</center>
    </li>
    <li><span><font color='red'>*</font>彩信标题：</span><input type='text' name='title' id='title' style='width:415px;height:20px;padding-top:3px' maxlength='100' /></li>
  <li>
   <span>短信内容：</span><textarea  name="msgContent"  id="msgContent" style='width:415px;font-size:12px;' rows="3" /></textarea><br/><center>(最多输入2000字)</center>
    </li>
<li><span>上传图片：</span><input size='35' type="file"" name="datafile" id="datafile1" onchange="onUploadImgChange(this)" /><br/><center>(请上传格式为JPG、JPEG或者GIF的图片，大小不超过50k)</center>
</li>
<li id='musicupload'><span>上传音乐：</span><input type='button' onclick='upmusic();return false;' value='上传音乐'/></li>
<li id='music' style='display:none'>
</li>
  </ul>
</div>
     <div class="grayline"></div>
     <div class="boxsearch2"><input type='submit'value="发送彩信" style='width:80px;height:20px' /></div>
  </form>
</div>
</div>
</div>
<div class="bottom">
<div class="bottomright"></div>
<div class="bottomleft"></div>
</div>
<script type='text/javascript'>
String.prototype.Trim = function() 
{ 
return this.replace(/(^\s*)|(\s*$)/g, ""); 
} 
function upmusic(){
$('musicupload').innerHTML="<span>上传音乐：<\/span><input size='35'  type='file' name='datafile' id='datafile2' onchange='onUploadMusic(this)' \/> <input type='button' onclick='removemusic();return false;' value='不上传音乐'\/><\/br><center>(请上传格式为MID或者MIDI的音乐，大小不超过50k)<\/center>";

}
function removemusic(){
var rid='music';
$('musicupload').innerHTML="<span>上传音乐：<\/span><input type='button' onclick='upmusic();return false;' value='上传音乐'/>";
}
function dosubmit()
{
var phoneNums=$('facAdd').phoneNums;
var datafile1=$('facAdd').datafile1;
var datafile2=$('facAdd').datafile2;
if (phoneNums.value.Trim().length==0){
	alert('请输入手机号码！');
	phoneNums.focus();
	return false;
}
/*if ($('title').value.Trim().length==0){
   alert('请输入彩信标题！');
   $('title').focus();
   return false;
}*/
if ($('msgContent').value.length>2000){
	alert('短信内容不能超过2000字');
	$('msgContent').focus();
	return false;
}
/*if ($('msgContent').value.Trim().length==0){
	alert('短信内容不能为空！');
	$('msgContent').focus();
	return false;
}*/
if(datafile1.value!=''){
  if (!datafile1.value.match(/.jpg|.jpeg|.gif/i)){
	alert('图片格式无效！请重新选择图片文件。');return false;
   }
}
if (datafile2){
	if(!datafile2.value.match( /.mid|.midi/i)){
		alert('音乐格式无效！请重新选择音乐文件。');return false;
	}
 }
return true;
}
function onUploadMusic(sender){
    if( !sender.value.match( /.mid|.midi/i) ){      
        alert('音乐格式无效！请重新选择音乐文件。'); 
        return false;
    }  
   }
function onUploadImgChange(sender){      
    if( !sender.value.match( /.jpg|.jpeg|.gif/i) ){      
        alert('图片格式无效！请重新选择图片文件。');     
        return false;
    }      
          
    var objPreview = document.getElementById( 'preview' );      
    var objPreviewFake = document.getElementById( 'preview_fake' );      
    var objPreviewSizeFake = document.getElementById( 'preview_size_fake' );      
          
    if( sender.files &&  sender.files[0] ){      
        objPreview.style.display = 'block';      
        objPreview.style.width = 'auto';      
        objPreview.style.height = 'auto';      
              
        // Firefox 因安全性问题已无法直接通过 input[file].value 获取完整的文件路径      
        objPreview.src = sender.files[0].getAsDataURL();          
    }else if( objPreviewFake.filters ){       
        // IE7,IE8 在设置本地图片地址为 img.src 时出现莫名其妙的后果      
        //（相同环境有时能显示，有时不显示），因此只能用滤镜来解决      
              
        // IE7, IE8因安全性问题已无法直接通过 input[file].value 获取完整的文件路径      
        sender.select();      
        var imgSrc = document.selection.createRange().text;      
              
        objPreviewFake.filters.item(      
            'DXImageTransform.Microsoft.AlphaImageLoader').src = imgSrc;      
        objPreviewSizeFake.filters.item(      
            'DXImageTransform.Microsoft.AlphaImageLoader').src = imgSrc;      
              
        autoSizePreview( objPreviewFake,       
            objPreviewSizeFake.offsetWidth, objPreviewSizeFake.offsetHeight );      
        objPreview.style.display = 'none';      
    }      
}      
     
function onPreviewLoad(sender){      
    autoSizePreview( sender, sender.offsetWidth, sender.offsetHeight );      
}      
     
function autoSizePreview( objPre, originalWidth, originalHeight ){      
    var zoomParam = clacImgZoomParam( 128, 154, originalWidth, originalHeight );      
    objPre.style.width = zoomParam.width + 'px';      
    objPre.style.height = zoomParam.height + 'px';      
    objPre.style.marginTop = zoomParam.top + 'px';      
    objPre.style.marginLeft = zoomParam.left + 'px';      
}      
     
function clacImgZoomParam( maxWidth, maxHeight, width, height ){      
    var param = { width:width, height:height, top:0, left:0 };      
          
    if( width>maxWidth || height>maxHeight ){      
        rateWidth = width / maxWidth;      
        rateHeight = height / maxHeight;      
              
        if( rateWidth > rateHeight ){      
            param.width =  maxWidth;      
            param.height = height / rateWidth;      
        }else{      
            param.width = width / rateHeight;      
            param.height = maxHeight;      
        }      
    }      
          
    param.left = (maxWidth - param.width) / 2;      
    param.top = (maxHeight - param.height) / 2;      
          
    return param;      
} 
</script>
<img id="preview_size_fake" style='position:absolute;top:0;left:0;' />
<input type="hidden" id="importFlag" name="importFlag" value="${importFlag?if_exists}" />
<input type="hidden" id="searchFlag" name="searchFlag" value="${searchFlag?if_exists}" />
</body>
</html>