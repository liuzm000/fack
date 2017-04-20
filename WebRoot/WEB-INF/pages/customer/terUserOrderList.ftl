<html>
<head><#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>MISMP</title>
<link href="${request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
<script language="JavaScript"  type="text/javascript" src="${request.contextPath}/javascript/common/calendar.js"></script>
<script language="JavaScript"  type="text/javascript" src="${request.contextPath}/javascript/common/jquery1.2.6.js"></script>
<script language="JavaScript"  type="text/javascript" src="${request.contextPath}/javascript/common/base.jsp"></script>
<script language="JavaScript"  type="text/javascript" src="${request.contextPath}/javascript/common/alex.js"></script>
<script language="JavaScript" type="text/JavaScript">
function checkForm(){
	var phoneNum = true;
	var proId = true;
	var status = true;
	if(document.getElementById("terUserOrderVO.phoneNum").value.trim() != ""){
		phoneNum = false;
	}
	if(document.getElementById("terUserOrderVO.proId").value.trim() != "-1"){
		proId = false;
	}
	if(document.getElementById("terUserOrderVO.status").value.trim() != "-1"){
		status = false;
	}

    if(phoneNum && proId && status){
    	alert("请至少输入一个查询条件");
    	document.getElementById("terUserVO.phoneNum").focus();
      	return;
    }
	searchForm.submit();
}

String.prototype.trim = function()  
{
    return this.replace(/(^\s*)|(\s*$)/g,"");   
}

<#if actionName?exists && actionName.equals("terUserList.action")>
$(document).ready(function(){
	setTimeout("self.location.reload();",2*60*1000); 
});
</#if>
</script>
</head>

<body style="padding-top:5px;padding-bottom:3px;">
<table width="100%" height="99%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="3%"  height="36"  align="left" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="../images/main/all_t_bg.gif"><img src="../images/icon/baobiao.gif"><span class="title"><strong>终端用户订购查询</strong> -终端用户订购列表</span></td>
    <td width="3%"  height="36"  align="right" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_r.gif" ></td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
    <form id="searchForm" name="searchForm" method="post" action="${request.contextPath}/customer/terUserOrderSearch.action">
    <table width="98%" border="0" cellspacing="0" cellpadding="0" class="boxsearch">
      <tr>
        <td colspan="3">&nbsp;</td>
        </tr>
      <tr>
        <td colspan="3" valign="top" ><table width="100%" border="0" cellpadding="0" cellspacing="5" class="boxsearch2">
            <tr>
              <td width="9%" align="right" nowrap="nowrap">手机号码：</td>
              <td width="30%" align="left">
              	<input class=input id="terUserOrderVO.phoneNum" name="terUserOrderVO.phoneNum" type="text" size="20" maxlength="20" value="${terUserOrderVO.phoneNum?if_exists}">
              </td>
              <td width="9%" align="right" nowrap="nowrap">产品名称：</td>
              <td width="52%" align="left">
					<select name="terUserOrderVO.proId"  id="terUserOrderVO.proId">
			                <option value="-1">--不限--</option>
			                <#if productList?exists && productList.size() != 0>
					        <#list productList as productVo>
						        <option value="${productVo.id?if_exists}" <#if productVo.id?exists && productVo.id==terUserOrderVO.proId>selected</#if>>${productVo.name?if_exists}
								</option>
					        </#list>
					        </#if>
			            </select> 
              </td>
            </tr>
            
            <tr>
              <td width="9%" align="right" nowrap="nowrap">订购状态：</td>
              <td width="30%" align="left">
              	<select name="terUserOrderVO.status"  id="terUserOrderVO.status">
			                <option value="-1" <#if terUserOrderVO.status == -1>selected</#if>>--不限--</option>
		                	<option value="1" <#if terUserOrderVO.status == 1>selected</#if>>申请</option>
		                	<option value="0" <#if terUserOrderVO.status == 0>selected</#if>>开通</option>
							<option value="2" <#if terUserOrderVO.status == 2>selected</#if>>暂停恢复</option>
							<option value="3" <#if terUserOrderVO.status == 3>selected</#if>>暂停</option>
							<option value="4" <#if terUserOrderVO.status == 4>selected</#if>>预注销</option>
							<option value="5" <#if terUserOrderVO.status == 5>selected</#if>>暂停预注销</option>
							<option value="6" <#if terUserOrderVO.status == 6>selected</#if>>注销</option>
			            </select>
              </td>
              <td width="9%" align="right" nowrap="nowrap"></td>
              <td width="52%" align="left">
              </td>
            </tr>
          </table></td>
       </tr>
	    
       <tr>
        <td width="1%" height="1"><img src="../images/space.gif" width="1" height="1"></td>
        <td width="98%" bgcolor="#F3F3F3"><img src="../images/space.gif" width="1" height="1"></td>
        <td width="1%"><img src="../images/space.gif" width="1" height="1"></td>
       </tr>
		
      <tr>
        <td height="44" colspan="3" align="center" valign="middle" class="down">
         <INPUT class=denglu1 type=button value="查 询" onClick="checkForm()"/>
        </td>
      </tr>
    </table>
	</form>
	
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="box_gongneng"  >
        <tr>
          <td align="right"><a href="right8.html"></a> <a href="right6.html"></a> <a href="#"></a> </td>
        </tr>
      </table>
	  
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab">
        <tr>
          <th width="8%">&nbsp;</th>
           <th colspan="9">终端用户订购列表</th>
        </tr>
        <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
          <td><div align="center"><strong>序号</strong></div></td>
          <td><div align="center"><strong>手机号码</strong></div></td>
          <td height="14" align="center"><strong>订购ID</strong></td>
          <td><div align="center"><strong>产品名称</strong></div></td>
          <td><div align="center"><strong>订购状态</strong></div></td>
		  <td><div align="center"><strong>创建时间</strong></div></td>
          <td><div align="center"><strong>动作</strong></div></td>
        </tr>
        <#if dataList?exists && dataList.size() != 0>
        <#list dataList as vo>
        <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
        <td><div align="center">${vo_index + 1 + (pageNum-1) * pageSize}</div></td>
          <td><div align="center">${vo.phoneNum?if_exists}</div></td>
          <td align="center"><div align="center">${vo.id?if_exists}</div></td>
          <td><div align="center">${vo.proName?if_exists}</div></td>
		  <td><div align="center">
		   <#if vo.status?exists && vo.status==0>
             开通
           </#if>
          <#if vo.status?exists && vo.status==1>
             申请
           </#if>
           <#if vo.status?exists && vo.status==2>
             暂停恢复
           </#if>
           <#if vo.status?exists && vo.status==3>
             暂停
           </#if>
           <#if vo.status?exists && vo.status==4>
             预注销
           </#if>
           <#if vo.status?exists && vo.status==5>
             暂停预注销
           </#if>
           <#if vo.status?exists && vo.status==6>
             注销
           </#if>
		  </div></td>
		  <td><div align="center">
          	 ${vo.createTime?if_exists}
          	  </div>
          </td>
          <td><div align="center">
          		<a href="${request.contextPath}/customer/terUserOrderDetail.action?id=${vo.id?if_exists}" class="redfontvha">修改</a>
          	  </div>
          </td>
        </tr>
        </#list>
        </#if>
      </table>
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td bgcolor="#FFFFFF" style="width:100%" colspan="6" align="center">  
			    <!--分页-->
				<#if dataList?exists>
					<#if dataList.recordCount?exists>
						<#assign recordCount=dataList.recordCount />
						<#assign lastPage=dataList.lastPage />
					    <#assign maxindexpages=recordCount/pageSize+1 />
					    <#if (maxindexpages>10)>
					        <#assign maxindexpages=10 />
					    </#if>
						<@pg.pager isOffset=true encoder="UTF-8" maxPageItems=pageSize maxIndexPages=maxindexpages items=recordCount url="${request.contextPath}/customer/${actionName?if_exists}" export="offset,currentPageNumber=pageNumber" scope = "request">
						<@pg.index>		
							<@pg.param name="terUserOrderVO.phoneNum"  value="${terUserOrderVO.phoneNum?if_exists}"/> 
							<@pg.param name="terUserOrderVO.proId"  value="${terUserOrderVO.proId?if_exists}"/>  
							<@pg.param name="terUserOrderVO.status"  value="${terUserOrderVO.status?if_exists}"/> 
							<#include "/WEB-INF/pages/common/jsp_tags.ftl"/> 
						</@pg.index>
					    </@pg.pager>
					</#if>
				</#if>
		    </td>
        </tr>
      </table></td>
  </tr>
    
  <tr>
    <td height="26" align="left"background="../images/main/all_d_bg.gif"><img src="../images/main/all_d_l.gif" ></td>
    <td height="26" background="../images/main/all_d_bg.gif">&nbsp;</td>
    <td height="26" align="right" background="../images/main/all_d_bg.gif"><img src="../images/main/all_d_r.gif" ></td>
  </tr>
</table>
</body>
</html>
