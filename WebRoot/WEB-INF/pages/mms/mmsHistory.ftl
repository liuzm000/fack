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
<table width="98%" height="99%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="3%"  height="36"  align="left" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="../images/main/all_t_bg.gif"><img src="../images/icon/baobiao.gif"><span class="title"><strong>短信管理</strong> -彩信发送记录查询</span></td>
    <td width="3%"  height="36"  align="right" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_r.gif" ></td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
    <form id="searchForm" name="searchForm" method="post" action="mmsHistory.action">
    <input type='hidden' name='flag' value='1'/>
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
				<input type="text" size=14 id="endtime" name="endtime" class="Wdate" value="${endtime?if_exists}" onFocus="WdatePicker({readOnly:true})"/>
              </td>
            </tr>
            <tr>
			   <td width="9%" align="right" nowrap="nowrap">用户账号：</td>
               <td width="30%" align="left">
				<input type="text" size=14 id="userAccount" name="userAccount" value="${userAccount?if_exists}"/>
              </td>
              <td width="9%" align="right" nowrap="nowrap">接收号码：</td>
               <td width="52%" align="left">
				<input type="text" size=14 id="receiver" name="receiver" value="${receiver?if_exists}"/>
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
	  
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab" style="WORD-BREAK:break-all">
        <tr>
          <th width="8%">&nbsp;</th>
           <th colspan="9">短信发送列表</th>
        </tr>
        <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
          <td width="8%" height="14" align="center"><strong>短信ID</strong></td>
          <td width="12%"><div align="center"><strong>接收号码</strong></div></td>
          <td width="35%"><div align="center"><strong>短信主题</strong></div></td>
          <td width="20%"><div align="center"><strong>发送时间</strong></div></td>
		  <td width="5%" ><div align="center"><strong>状态</strong></div></td>
		  <td width="8%"><div align="center"><strong>用户账号</strong></div></td>
		  <td width="20%"><div align="center"><strong>文件下载</strong></div></td>
        </tr>
        <#if dataList?exists && dataList.size() != 0>
        <#list dataList as vo>
        <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
          <td align="center"><div align="center">${vo.id?if_exists}</div></td>
          <td><div align="center">${vo.receiver?if_exists}</div></td>
          <td><div align="center">${vo.subject?if_exists}</div></td>
		  <td><div align="center">${vo.sendTime?if_exists}</div></td>
		  <#-- 状态【0：已发送，000：成功送达，001：成功递交，002：未知状态，003：发送失败；
		  004：排队等待，005：不支持回执，006：鉴权失败，999 ：其他错误，其他：发送失败】';
		  -->
		  <td><div align="center">
		   <#if vo.status=="0">已发送
		   <#elseif vo.status=="000">成功送达
		   <#elseif vo.status=="001">成功递交
		   <#elseif vo.status=="002">未知状态
		   <#elseif vo.status=="003">发送失败
		   <#elseif vo.status=="004">排队等待
		   <#elseif vo.status=="005">不支持回执
		   <#elseif vo.status=="006">鉴权失败
		   <#elseif vo.status=="999">其他错误
		   <#else>发送失败
		   </#if>
		  </div></td>
		  <td><div align="center">${vo.userAccount?if_exists}</div></td>
		  <td align="center"><a class="redfontvha" href='zipOut.action?pid=${vo.fileDir}'>打包下载</a></td>
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
						<@pg.pager isOffset=true encoder="UTF-8" maxPageItems=pageSize maxIndexPages=maxindexpages items=recordCount url="${request.contextPath}/mms/mmsHistory.action" export="offset,currentPageNumber=pageNumber" scope = "request">
						<@pg.index>		
							<@pg.param name="starttime"  value="${starttime?if_exists}"/> 
							<@pg.param name="endtime"  value="${endtime?if_exists}"/> 
							<@pg.param name="userAccount"  value="${userAccount?if_exists}"/> 
							<@pg.param name="receiver"  value="${receiver?if_exists}"/> 
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
