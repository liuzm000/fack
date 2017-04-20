<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>MISMP</title>
<link href="${request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
<script language="JavaScript"  type="text/javascript" src="${request.contextPath}/javascript/common/calendar.js"></script>
<script language="JavaScript"  type="text/javascript" src="${request.contextPath}/javascript/common/base.jsp"></script>
<script language="JavaScript" type="text/JavaScript">
String.prototype.trim = function()  
{
    return this.replace(/(^\s*)|(\s*$)/g,"");   
}
</script>
</head>

<body style="padding-top:5px;padding-bottom:3px;">
<table width="100%" height="99%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="3%"  height="36"  align="left" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="../images/main/all_t_bg.gif"><img src="../images/icon/baobiao.gif"><span class="title"><strong>终端用户查询</strong> - 终端用户信息详情</span></td>
    <td width="3%"  height="36"  align="right" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_r.gif" ></td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
    <form id="searchForm" name="searchForm" method="post" action="">
    <table width="98%" border="0" cellspacing="0" cellpadding="0" class="boxsearch">
      <tr>
        <td colspan="3">&nbsp; </td>
        </tr>
      <tr>
        <td colspan="3" valign="top" >
          <table width="100%" border="0" cellpadding="0" cellspacing="5" class="boxsearch2">
            <tr>
              <td width="9%" align="right" nowrap="nowrap">ID：</td>
              <td width="30%" align="left">
              	${terUserDetailVO.id?if_exists}
              </td>
              <td width="9%" align="right" nowrap="nowrap">手机号码：</td>
              <td width="52%" align="left">${terUserDetailVO.phoneNum?if_exists}</td>
            </tr>
            <tr>
              <td width="9%" align="right" nowrap="nowrap">IMSI：</td>
              <td width="30%" align="left">${terUserDetailVO.imsi?if_exists}</td>
              <td width="9%" align="right" nowrap="nowrap">ESN：</td>
              <td width="52%" align="left">
	              ${terUserDetailVO.esn?if_exists}
              </td>
            </tr>        
            <tr>
              <td width="9%" align="right" nowrap="nowrap">手机类型ID：</td>
              <td width="30%" align="left">
                 ${terUserDetailVO.terId?if_exists}
              </td>
              <td width="9%" align="right" nowrap="nowrap">首次访问时间：</td>
              <td width="52%" align="left">
                 ${terUserDetailVO.createTime?if_exists}
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
           <th colspan="16">手机信息</th>
        </tr>
        <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
          <td height="14" align="center" nowrap><strong>类型ID</strong></td>
          <td><div align="center" nowrap><strong>类型编号</strong></div></td>
          <td><div align="center" nowrap><strong>机型名称</strong></div></td>
          <td><div align="center" nowrap><strong>厂商</strong></div></td>
          <td height="14" align="center" nowrap><strong>平台</strong></td>
          <td nowrap><div align="center"><strong>分辨率</strong></div></td>
          <td nowrap><div align="center"><strong>JAVA支持</strong></div></td>
          <td nowrap><div align="center"><strong>彩信支持</strong></div></td>
          <td nowrap><div align="center"><strong>WAP支持</strong></div></td>
          <td nowrap><div align="center"><strong>EVDO支持</strong></div></td>
          <td nowrap><div align="center"><strong>描述</strong></div></td>
        </tr>
        <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
          <td align="center">
		    ${terUserDetailVO.terId?if_exists}
          </td>
          <td>
		    ${terUserDetailVO.terCode?if_exists}
		  </td>
          <td><div align="center">${terUserDetailVO.terName?if_exists}</div></td>
          <td><div align="center">${terUserDetailVO.facName?if_exists}</div></td>
          <td><div align="center">${terUserDetailVO.terplat?if_exists}</div></td>
          <td nowrap><div align="center">${terUserDetailVO.virName?if_exists}</div></td>
          <td><div align="center">
          <#if terUserDetailVO.javaSupport?exists && terUserDetailVO.javaSupport.equals("1")>
          支持
          <#else>
          不支持
          </#if>
          </div></td>
          <td><div align="center">
           <#if terUserDetailVO.mmsSupport?exists && terUserDetailVO.mmsSupport.equals("1")>
          支持
          <#else>
          不支持
          </#if>
          </div></td>
          <td><div align="center">
           <#if terUserDetailVO.wapSupport?exists && terUserDetailVO.wapSupport.equals("1")>
          支持
          <#else>
          不支持
          </#if></div></td>
          <td><div align="center">
          	  <#if terUserDetailVO.evdoSupport?exists && terUserDetailVO.evdoSupport.equals("1")>
          支持
          <#else>
          不支持
          </#if>
          	  </div>          </td>
          <td><div align="center">
          	 ${terUserDetailVO.terdesc?if_exists}
          	  </div>          </td>
        </tr>
      </table>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="box_fanye">
        <tr>
          <td align="center"><INPUT class=denglu1 type=button value="返 回" onClick="javascript:window.history.go(-1);"/>&nbsp;</td>
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


</body>
</html>
