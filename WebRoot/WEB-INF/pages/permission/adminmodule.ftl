
<html>
<head>
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>业务支撑系统-模块管理-模块列表</title>
<link href="${request.contextPath}/css/tel.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
<script src="${request.contextPath}/javascript/common/operation.js" type="text/javascript"></script>
<script type="text/javascript">
   function submitform(form,operation)
   {
   		if(operation=='${request.contextPath}/permission/deleteModule.action'){
    		if(!confirmDelete(form,'ids','你确定要删除这些记录吗？')) return;
  		}
   		form.action=operation;
   		form.submit();
   }
</script>
</head>

<body style="padding-top:5px;padding-bottom:3px;padding-left:5px;">
<table width="100%" height="99%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="3%"  height="36"  align="left" background="${request.contextPath}/images/main/all_t_bg.gif"><img src="${request.contextPath}/images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="${request.contextPath}/images/main/all_t_bg.gif"><img src="${request.contextPath}/images/icon/gl.gif">
	<span class="title">系统管理 - <strong>模块管理</strong></span></td>
    <td width="3%"  height="36"  align="right" background="${request.contextPath}/images/main/all_t_bg.gif"><img src="${request.contextPath}/images/main/all_t_r.gif" ></td>
  </tr>
  
  <tr>
			
  
  </tr>
  
  <tr>
    <td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
	<!--xxxxxx-->
	
	

<form id="admin_module_form" name="form1" method="post">
<input type="hidden" name="idforEdit"/>
 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab" style="margin-top:50px;">
  <tr>
    <th width="8%" align="left">全选<input type="checkbox" name="checkbox2" value="checkbox" onclick="javascript:setChecked(this.form,this.checked,'ids')"></th>
    <th width="12%">模块名</th>
    <th width="24%">模块描述</th>
    <th width="8%">上级模块</th>
    <th>模块类型</th>
    <th>模块地址</th>
    <th width="70"></th>
  </tr>
<#list moduleList as ml>
  <tr  onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
    <td><input type="checkbox" name="ids" value="${ml.module_id?if_exists}"></td>
    <td>${ml.module_name?if_exists}</td>
    <td>${ml.description?if_exists}</td>
    <td nowrap>${ml.parent_module_name?if_exists}</td>
    <td>${ml.module_type_name?if_exists}</td>
    <td>${ml.module_url?if_exists}</td>
    <td align="center">
    <a href="javascript:document.getElementById('admin_module_form').idforEdit.value='${ml.module_id?if_exists}';submitform(document.getElementById('admin_module_form'),'${request.contextPath}/permission/goModifyModule.action')" class="redfontvha">修改信息</a>
   <!-- <INPUT class=denglu1 type=button value="修改" onclick="this.form.idforEdit.value='${ml.module_id?if_exists}';submitform(this.form,'${request.contextPath}/system/goModifyModule.action')">-->
   </td>
  </tr>
</#list>
   <tr>
    <td bgcolor="#FFFFFF" style="width:80%" colspan="9" align="center">  
	    <!--分页-->
		<#if moduleList?exists>
		<!-- 模拟设置几个参数-->
			<#assign tab="[]"/>
			<#assign urltemp=request.contextPath+"/permission/listModule.action" />
			<#assign formnameTemp="" />
			<#if moduleList.recordCount?exists>
				<#assign recordCount=moduleList.recordCount />
				<#assign lastPage=moduleList.lastPage />
			    <#assign maxindexpages=recordCount/pageSize+1 />
			    <#if (maxindexpages>10)>
			        <#assign maxindexpages=10 />
			    </#if>
				<@pg.pager isOffset=true encoder="UTF-8" maxPageItems=pageSize maxIndexPages=maxindexpages items=recordCount url="${actionName?if_exists}" export="offset,currentPageNumber=pageNumber" scope = "request">
				<@pg.index>					  				 
					<#include "/WEB-INF/pages/common/jsp_tags.ftl"/> 
				</@pg.index>
			    </@pg.pager>
			</#if>
		</#if>
    </td>
  </tr>
</table>
<table align="center">
<tr align="center">
    <td height="20"></td>
  </tr>
<tr align="center">
    <td><INPUT class=denglu1 type=button value="添加" onclick="javascript:window.location.href='${request.contextPath}/permission/goAddModule.action'">　<INPUT class=denglu1 type=button value="删除" onClick="submitform(this.form,'${request.contextPath}/permission/deleteModule.action')"></td>
  </tr>
</table>
</form>
			
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
