<#assign rootPath=request.scheme+"://"+request.serverName+":"+request.serverPort+request.contextPath />
<html>
<head>
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>广东移百管理系统</title>
<link href="${rootPath}/css/main.css" rel="stylesheet" type="text/css">

</head>

<body style="padding-top:5px;padding-bottom:3px;">
<table width="100%" height="99%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="3%"  height="36"  align="left" background="${rootPath}/images/main/all_t_bg.gif"><img src="${rootPath}/images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="${rootPath}/images/main/all_t_bg.gif"><img src="${rootPath}/images/icon/baobiao.gif"><span class="title"><strong>动态消息管理</strong> - 查看列表</span></td>
    <td width="3%"  height="36"  align="right" background="${rootPath}/images/main/all_t_bg.gif"><img src="${rootPath}/images/main/all_t_r.gif" ></td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab">
        <tr>
           <th colspan="6">动态消息列表</th>
        </tr>
      <tr>
          <td width="5%" align="center"><b>ID</td>
          <td width="10%" align="center"><b>软件名称</td>
          <td width="15%" align="center"><b>手机号码</td>
          <td width="40%" align="center"><b>消息内容</td>
          <td width="20%" align="center"><b>参数</td>
          <td width="10%" align="center"><b>操作</td>
       </tr>
        <#if msgList?exists && msgList.size() != 0>
        <#list msgList as vo>
        <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'" align="center">
          <td align="center">${vo.id?if_exists}</td>
          <td>${vo.product?if_exists.name?if_exists}</td>
          <td>${vo.phoneNum?if_exists}</td>
          <td style="word-break:break-all;">${vo.content?if_exists}</td>
          <td style="word-break:break-all;">${vo.param?if_exists}</td>
          <td align="center" nowarp>
          <a href="${rootPath}/content/dynamicmsg/preupdate.action?id=${vo.id?if_exists}" class="redfontvha">修改</a>
          &nbsp;|&nbsp;
          <a href="${rootPath}/content/dynamicmsg/delete.action?id=${vo.id?if_exists}" class="redfontvha">删除</a>
          </td>
        </tr>
        </#list>
        </#if>
        <tr>
	      <td colspan="6" align="center" valign="middle" bgcolor="#FFFFFF" class="down">
	         <INPUT class="denglu1" type="button" value="添加" onClick="javascript:window.location.href='${rootPath}/content/dynamicmsg/preadd.action';"/>
	      </td>
  	  	</tr>
      </table>
     </td>
  </tr>
    
  <tr>
    <td height="26" align="left"background="${rootPath}/images/main/all_d_bg.gif"><img src="${rootPath}/images/main/all_d_l.gif" ></td>
    <td height="26" background="${rootPath}/images/main/all_d_bg.gif">&nbsp;</td>
    <td height="26" align="right" background="${rootPath}/images/main/all_d_bg.gif"><img src="${rootPath}/images/main/all_d_r.gif" ></td>
  </tr>
</table>


</body>
</html>
