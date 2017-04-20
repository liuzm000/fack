<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>SP软件审核</title>
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.add li {
	height:25px;
}
.add li span{
	float:left;
	width:150px;
	text-align:right;
}
#myinfo{
	text-align:right;
	color:#0C0;
}
#ownerdiv,#websitediv{
	float:left;
	width:45%;
	}
#outdiv,#indiv{
	display:none;
	width:600px;
	margin:5px auto;
}
.redtext{
	color:#F00
}
-->
</style>
<script type="text/javascript">
function $(id)
{
return document.getElementById(id);
}
function setout(){
	$('outdiv').style.display='block';
	$('indiv').style.display='none';
	$('remark').focus();
}
function setoutcheck(){
	if ($('remark').value==''){
		alert('请您填写审核不通过的原因！');
		$('remark').focus();
		return;
	}else if($('remark').value.length>300){
		alert('审核原因填写过长，请勿超过300字符！');
		$('remark').focus();
		return;
	}
	else
	{$('vertifyout').submit();}
}
function setincheck(){

	$('vertifyin').submit();
}
function set2(){
	$('indiv').style.display='block';
	$('outdiv').style.display='none';
}
</script>

</head>

<body>
<div class="top">
<div class="topright"></div>
<div class="topleft"></div>
<img src="../images/icon/shuju.gif"/><span class="title">SP软件审核</span>
</div>
<div class="tmiddle">
&nbsp;
<div class="boxsearch">
<div style="height:280px">
  <div id="ownerdiv" class="add">
  <ul><li><span><strong>软件版本信息：</strong></span></li>
         <li><span>产品：</span>[${rl.sv.st.pro.pro_id?if_exists}]${rl.sv.st.pro.pro_name?if_exists}</li>
         <li><span>软件名称：</span>${rl.sv.st.st_name?if_exists}</li>
          <li><span>版本号：</span>${rl.soft_version?if_exists}</li>
         <li><span>版本描述：</span>${rl.soft_desc?if_exists}</li>
         <li><span>申请时间：</span>${rl.create_time}</li>
         <li><span>强制更新：</span><#if rl.soft_forceupdate==0><font color='blue'>是</font><#else>否</#if></li>
          <#if ver?exists>
         <li><span>审核人：</span>${rl.audit_by?if_exists}</li>
         <li><span>审核时间：</span>${rl.audit_time?if_exists}</li>
         <li><span>审核结果：</span><#if rl.audit_status==2><font color=green>通过</font><#else><font color=red>未通过</font></#if></li>
        <#if rl.audit_status==3><li><span>不通过的原因：</span>${rl.audit_desc}</li></#if>
          </#if>
  </ul>
</div>

<div id="websitediv" class="add">
<ul><li><span><strong>SP信息：</strong></span></li>
<li><span>SP编号：</span>${rl.sv.st.pro.sp.sp_code?if_exists}</li>
<li><span>SP名称：</span>${rl.sv.st.pro.sp.sp_name?if_exists}</li>
<li><span>省份：</span>${rl.sv.st.pro.sp.sp_local_name?if_exists}</li>
<li><span>级别：</span><#if rl.sv.st.pro.sp.sp_type==1>全国<#else>省级</#if></li>
<li><span>描述：</span>${rl.sv.st.pro.sp.sp_desc?if_exists}</li>
</ul>
</div>

</div>
<div class="clear"></div>
      <div class="grayline"></div>
      <div class="boxsearch2">
      <input type="button" value="通过" onclick="set2()"/>&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" value="不通过" onclick="setout()"/>
      &nbsp;&nbsp;&nbsp;&nbsp;
      <input type="button" value="返回" onclick="window.history.back()"/>
      </div>
      <div id='outdiv'>
      <div class="grayline"></div>
      <form name='vertifyout' id='vertifyout' action='svAudit.action?id=${id}&action=no' method="post">
      请填写<span class="redtext">审核不通过</span>的原因:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="setoutcheck();" value="确定"/><br />
      <textarea cols="50" rows="5" name='remark' id="remark"></textarea><br/>
      </form>
      </div>    <div id='indiv'>
          <div class="grayline"></div>
      <form name='vertifyin' id='vertifyin' action='svAudit.action?id=${id}' method="post">
     <center><input type="button" onclick="setincheck();" value="确定通过"/></center>
      </form>
      </div>
      <div class="clear"></div>
</div>
</div>
<div class="bottom">
<div class="bottomright"></div>
<div class="bottomleft"></div>
</div>


</body>
</html>