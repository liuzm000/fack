
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>业务支撑系统-模块管理</title>
<link href="${request.contextPath}/css/tel.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
</head>

<body onload="document.all.Form1.save.disabled=true;" style="padding-top:5px;padding-bottom:3px;padding-left:5px;">
<table width="100%" height="99%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="3%"  height="36"  align="left" background="${request.contextPath}/images/main/all_t_bg.gif"><img src="${request.contextPath}/images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="${request.contextPath}/images/main/all_t_bg.gif"><img src="${request.contextPath}/images/icon/gl.gif">
	<span class="title">系统管理 - <strong>添加模块</strong></span></td>
    <td width="3%"  height="36"  align="right" background="${request.contextPath}/images/main/all_t_bg.gif"><img src="${request.contextPath}/images/main/all_t_r.gif" ></td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
	<!--xxxxxx-->
<form name="Form1" method=post >
<input type="hidden" name="subjectlist" >

<table  border="0" cellpadding="6" cellspacing="1" style="margin:10px 0px 0px 10px;width:70%;background-color:#B7CDE4">
  <tr bgcolor="#FFFFFF">
    <th width="70%" align="center">次序调整</th>
    <th width="30%" align="center">使用说明</th> 
  </tr>
  <tr bgcolor="#FFFFFF">
    <td width="70%" align="center">
    <table border=0>
    <tr><td>初始次序<br>
    	<select name="DefaultOrder" size="18"  multiple>
          <#list moduleList as ml>
            <option value='${ml.module_id}'>${ml.module_name}</option>
          </#list>
    	</select></td><td><table border=0>
	<tr><td>&nbsp;</td></tr>
	<tr><td align="buttom"><input type="button" name="rightAll" value=" >>" onclick="javascript:fun_select_addall(this.form);"></td></tr>
	<tr><td align="top"><input type="button" name="rightSelect" value=" > " onclick="javascript:fun_select_addany(this.form);"></td></tr>
	<tr><td>&nbsp;</td></tr>
	<tr><td align="buttom"><input type="button" name="leftSelect" value=" < " onclick="fun_select_dltany(this.form);"></td></tr>
	<tr><td align="top"><input type="button" name="leftAll" value="<< " onclick="javascript:fun_select_dltall(this.form);"></td></tr>
	<tr><td>&nbsp;</td></tr>
	</table></td><td>最终次序<br>
    	<select name="FinalOrder" size="18"  multiple>
    	</select></td></tr>
    </table>
		
	</td>
    <td width="30%" align="center">
	&nbsp;&nbsp;次序以最终次序为准<br>
     &gt;&gt;将左方的各项全部转移到右方<br>
     &gt;&nbsp;将左方所选项转移到右方<br>
     &lt;&nbsp;将右方所选项转移到左方<br>
	 &lt;&lt;将右方的各项全部转移到左方
	</td></td>
  </tr>
  <tr bgcolor="#FFFFFF"><td colspan="2" align="center"><INPUT class=denglu1 name="save" type=button value="提交" onclick="submitform(this.form,'${request.contextPath}/permission/orderModule.action')">　<INPUT class=denglu1 type=button value="返回" onclick="javascript:window.history.go(-1);"></td></tr>
</table>
</form>
<script language="JavaScript" type="text/JavaScript">
<!--
 var list="";
 function submitform(form,operation){
  fun_getAllSubjectList(form);  
  form.action=operation; 
  form.submit();
 }

function fun_select_addany(theform){
    var i; 
    var a;
    for (i=0;i<theform.DefaultOrder.length;i++){
        if (theform.DefaultOrder.options[i].selected == true){
           if (theform.DefaultOrder.options[i].text !=""){
           	  a=theform.FinalOrder.length;
              theform.FinalOrder.options[a]=new Option(theform.DefaultOrder.options[i].text);
              theform.FinalOrder.options[a].value=theform.DefaultOrder.options[i].value;
              theform.DefaultOrder.options[i] =new Option("");
              }
       } 
    }
    for (i=0;i<theform.DefaultOrder.length;i++){
        if (theform.DefaultOrder.options[i].text ==""){theform.DefaultOrder.options.remove(i); i--;}
    } 
    if(theform.DefaultOrder.length==0)
      theform.save.disabled=false;   
} 

function fun_select_addall(theform){
    var i;   
    var a;
    for (i=0;i<theform.DefaultOrder.length;i++){
        if (theform.DefaultOrder.options[i].text !=""){
           a=theform.FinalOrder.length;
           theform.FinalOrder.options[a]=new Option(theform.DefaultOrder.options[i].text);
           theform.FinalOrder.options[a].value=theform.DefaultOrder.options[i].value;
           }       
    }
    theform.DefaultOrder.length=0; 
    if(theform.DefaultOrder.length==0)
      theform.save.disabled=false;   
}  

function fun_select_dltany(theform){
   var i; 
   var a;
    for (i=0;i<theform.FinalOrder.length;i++){
        if (theform.FinalOrder.options[i].selected == true){
           if (theform.FinalOrder.options[i].text !=""){
              a=theform.DefaultOrder.length;
              theform.DefaultOrder.options[a]=new Option(theform.FinalOrder.options[i].text);
              theform.DefaultOrder.options[a].value=theform.FinalOrder.options[i].value;
              theform.FinalOrder.options[i] =new Option("");
              }
       } 
    }
    for (i=0;i<theform.FinalOrder.length;i++){
        if (theform.FinalOrder.options[i].text ==""){theform.FinalOrder.options.remove(i);i--;}
    } 
    if(theform.DefaultOrder.length>0)
      theform.save.disabled=true;
}

function fun_select_dltall(theform){
    var i; 
    var a;  
    for (i=0;i<theform.FinalOrder.length;i++){
        if (theform.FinalOrder.options[i].text !=""){
           a=theform.DefaultOrder.length;
           theform.DefaultOrder.options[a]=new Option(theform.FinalOrder.options[i].text);
           theform.DefaultOrder.options[a].value=theform.FinalOrder.options[i].value;
        }
    }
    theform.FinalOrder.length=0;   
    if(theform.DefaultOrder.length>0)
      theform.save.disabled=true;         
}    

function fun_getAllSubjectList(theform){
  for (i=0;i<theform.FinalOrder.length;i++){
     if(theform.FinalOrder.options[i]=="") break;
     list+=theform.FinalOrder.options[i].value+",";
 }
  if(list.substring(list.length-1)==",")
    list = list.substring(0,list.length-1);
  //  alert(list);
   theform.subjectlist.value=list;
 }
// -->
</script>

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
