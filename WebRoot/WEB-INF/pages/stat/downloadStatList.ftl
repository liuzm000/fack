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
	searchForm.submit();
}
</script>
</head>

<body style="padding-top:5px;padding-bottom:3px;">
<script src="../javascript/DatePicker/WdatePicker.js" type="text/javascript"></script>
<table width="100%" height="99%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="3%"  height="36"  align="left" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="../images/main/all_t_bg.gif"><img src="../images/icon/baobiao.gif"><span class="title"><strong>软件管理</strong> -软件下载统计</span></td>
    <td width="3%"  height="36"  align="right" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_r.gif" ></td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
    <form id="searchForm" name="searchForm" method="post" action="${request.contextPath}/stat/downloadStatSearch.action">
    <table width="98%" border="0" cellspacing="0" cellpadding="0" class="boxsearch">
      <tr>
        <td colspan="3">&nbsp;</td>
        </tr>
      <tr>
        <td colspan="3" valign="top" ><table width="100%" border="0" cellpadding="0" cellspacing="5" class="boxsearch2">
            <tr>
              <td width="9%" align="right" nowrap="nowrap">开始时间：</td>
              <td width="30%" align="left">
              	<input type="text" size="14" id="starttime" name="starttime" class="Wdate" value="${starttime?if_exists}" onFocus="WdatePicker({startDate:'%y-%M-01',readOnly:true})"/>
              </td>
              <td width="9%" align="right" nowrap="nowrap">截止时间：</td>
              <td width="52%" align="left">
				<input type="text" size=14 id="endtime" name="endtime" class="Wdate" value="${endtime?if_exists}" onFocus="WdatePicker({readOnly:true})"/>（留空则不限制）
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
           <th colspan="9">软件下载统计列表</th>
        </tr>
        <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
          <td><div align="center"><strong>序号</strong></div></td>
          <td><div align="center"><strong>类型名称</strong></div></td>
          <td><div align="center"><strong>类型描述</strong></div></td>
          <td height="14" align="center"><strong>类型ID</strong></td>
          <td><div align="center"><strong>下载次数</strong></div></td>
          <!--
          <td><div align="center"><strong>下载手机号码数量</strong></div></td>
          -->
		 <!-- <td><div align="center"><strong>提交者ID</strong></div></td> -->
        </tr>
        <#if dataList?exists && dataList.size() != 0>
        <#list dataList as vo>
        <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
          <td><div align="center">${vo_index + 1 + (pageNum-1) * pageSize}</div></td>
          <td><div align="center">${vo.softTypeName?if_exists}</div></td>
          <td><div align="center">${vo.softTypeDesc?if_exists}</div></td>
          <td align="center"><div align="center">${vo.softTypeId?if_exists}</div></td>
          <td><div align="center">${vo.downloadCount?if_exists}</div></td>
          <!--
		  <td><div align="center">${vo.downloadPhoneCount?if_exists}</div></td>
		  -->
		  <!--<td><div align="center">${vo.submitId?if_exists}</div>-->
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
						<@pg.pager isOffset=true encoder="UTF-8" maxPageItems=pageSize maxIndexPages=maxindexpages items=recordCount url="${request.contextPath}/stat/${actionName?if_exists}" export="offset,currentPageNumber=pageNumber" scope = "request">
						<@pg.index>		
							<@pg.param name="starttime"  value="${starttime?if_exists}"/> 
							<@pg.param name="endtime"  value="${endtime?if_exists}"/>  
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
