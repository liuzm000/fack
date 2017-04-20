<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<#assign rootPath=request.scheme+"://"+request.serverName+":"+request.serverPort+request.contextPath />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>知识普及</title>
<link href="${request.contextPath}/css/global.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/javascript/ui/css/ui.core.css"/>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/javascript/ui/css/ui.theme.css"/>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/javascript/ui/css/ui.dialog.css"/>
<!-- import jquery -->
<script type="text/javascript" src="${request.contextPath}/javascript/jquery-core/jquery-1.3.js"></script>


<script>
  function checkSaveForm(){
    var replyValue = document.getElementById("nsFeedbackReplyValue");
    if(replyValue.value == "" || replyValue.value == null) {
    	alert("请输入回复的内容");
    	return;
    }
  
	document.forms["editForm"].action = "doFeedbackReplySave.action";
	document.forms["editForm"].submit();
  }
  function del(obj1,obj2) {
  	if(confirm("你确定要删除这条回复吗？")){
	  $("#editForm").attr("action","doFeedbackReplyDelete.action?replyid=" + obj1+"&feedbackId="+obj2)
	  $("#editForm").submit();
	}
  }
  
	
</script>

</head>
<body>
  <div class="top">
  <div class="topright"></div>
  <div class="topleft"></div>
    <img src="../images/icon/baobiao.gif"><span class="title"><strong>反馈回复</strong></span>
  </div>
  <!-- 主体部分 -->
  <div class="tmiddle">&nbsp;
  	<div class="boxsearch">
		<div class="boxsearch3"></div>
	</div>
	
  	<div class="boxsearch" >
  		<form id="editForm" name="editForm" method="post" action="" enctype="multipart/form-data">
  		<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0" class="tab" style="line-height:300%">
				<tr >
					<td width="20%" align="right" valign="top"  style="padding-right:10px"><b>反馈人:</b></font></td>
					<td width="30%" align="left" style="padding-left:10px">
						<input maxlength="50" size="40" type="text" value="${nsFeedbackModel.operatorName?if_exists}" readonly = "readonly"/>
					</td>
					<td width="40%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				<tr>
					<td width="20%" align="right" valign="top"  style="padding-right:10px"><b>反馈时间:</b></td>
					<td width="30%" align="left" style="padding-left:10px">
						<input   maxlength="50" size="40" type="text" value="${nsFeedbackModel.submitTime?if_exists}" readonly = "readonly"//>
					</td>
					<td width="40%" align="left" style="padding-left:20px"><font color="green"></font></td>
				</tr>
				<tr>
					<td align="right" valign="top" style="padding-right:10px"><b> 反馈问题:</b></td>
					<td colspan="2" align="left" style="padding-left:10px">
					<textarea  maxlength="2000" rows="5" cols="40"  disabled="disabled">${nsFeedbackModel.content?if_exists}</textarea>
					</td>
					
				</tr>
				<tr>
					<td width="20%" align="right" style="padding-right:10px"><b>回复列表:</b></td>
				
				</tr>
					<#if replyList?exists>
						<#list replyList as page>
							<tr>
								<td width="20%" align="right" valign="top"  style="padding-right:10px"></b></td>
								<td width="10%" align="left" style="padding-left:10px">
								<textarea  maxlength="2000" rows="5" cols="40">${page.content?if_exists} ${"\n"}回复时间:${page.replyTime?if_exists} </textarea> <a href="javascript:del('${page.id?if_exists}','${nsFeedbackModel.id?if_exists}')"><font color="red"><u>删除</u></font></a>
								</td>
							</tr>
						</#list>
					</#if>
				<tr>
					<td width="20%" align="right" valign="top"  style="padding-right:10px"></b></td>
					<td width="10%" align="left" style="padding-left:10px">
					<textarea  maxlength="2000" rows="4" cols="40" id="nsFeedbackReplyValue" name="replyModel.content"></textarea>
					</td>
				</tr>
				<input type="hidden" value = "${nsFeedbackModel.id?if_exists}" name= "id" />
			</table>
		<div class="grayline"></div>
		<div class="boxsearch2">
			<a href="javascript:checkSaveForm()"><img src="../images/buttom/buttom_baocun.gif" alt="保存" /></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:window.history.back();'><img src="../images/buttom/buttom_fanhui.gif" alt="返回"/></a>
		</div>
	  	</form>
  	</div>
  </div>
  <div class="bottom">
	<div class="bottomright"></div>
	<div class="bottomleft"></div>
  </div>
  
</body>
</html>