<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign pg=JspTaglibs["/WEB-INF/tlds/pager-taglib.tld"] >
<#assign rootPath=request.scheme+"://"+request.serverName+":"+request.serverPort+request.contextPath />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>反馈管理</title>
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../javascript/jquery-core/jquery-1.3.js"></script>
<script type="text/javascript">
  function search(){
  	return;
    //$("#searchForm").attr("action","doParkCommentListByCondition.action");
    //$("#searchForm").submit();
  }
  
  function aduit(obj){
	  $("#searchForm").attr("action","addFeedbackReply.action?id=" + obj);
	  $("#searchForm").submit();
  }
  
  function unAduit(obj){
	if(confirm("该反馈已经回复，你确定要追加回复？")){
	  $("#searchForm").attr("action","addFeedbackReply.action?id=" + obj);
	  $("#searchForm").submit();
	}
  }                         
	
  function del(obj){ 
	if(confirm("你确定要删除这条记录吗？")){
	  $("#searchForm").attr("action","doFeedbackDelete.action?feedbackId=" + obj)
	  $("#searchForm").submit();
	}
  }
  function selectall() {
	var form =$("#detailForm input");
    $.each(form,function(key,value) {
      if(value.type == "checkbox"){
        if(!value.disabled)
          value.checked = !value.checked;
      }
    });
  } 
  function aduitAll() {
	var str = "";
	var form =$("#detailForm input")
    $.each(form,function(key,elem) {
      if(elem.type == "checkbox"){
        if(elem.checked)
         str = str +elem.value+"-";
      }
    }); 
	if(str ==""){
	  alert("请选择要审核的评论！");
	  return;	
	}
	if(confirm("你确定批量审核所选记录吗？")){
	  $("#searchForm").attr("action","doParkCommentMultiAduit.action?multiId="+str);
	  $("#searchForm").submit();
	} 
  }
  
</script>


</head>

<body>
<!-- TOP -->
<div class="top">
	<div class="topright"></div>
	<div class="topleft"></div>
	<img src="../images/icon/shuju.gif" /><span class="title">反馈管理 - 列表</span>
</div>
<!-- TOP END -->

<!-- MIDDLE -->
<div class="tmiddle">&nbsp;
	<div class="boxsearch">
		<div class="boxsearch2">
			<form id="searchForm" name="searchForm" method="post" action="searchRssItem.action">
			<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" class="">
				<tr>
					<td colspan="8">&nbsp;</td>
				</tr>
				<#--
				<tr> 
					<td width="10%" align="right">反馈人：</td>
					<td width="10%" align="left">
					<input id="nsFeedbackModel.phone"  name="nsFeedbackModel.operatorName"  maxlength="50" size="15" type="text" value='${nsFeedbackModel.phone?if_exists}'/>
					</td>
					<td width="10%" align="right">内容(关键字)：</td>
					<td width="10%" align="left">
						<input id="nsFeedbackModel.content"  name="nsFeedbackModel.content"  maxlength="50" size="15" type="text" value='${nsFeedbackModel.content?if_exists}'/>
					</td>
					<td width="10%" align="right">是否回复</td>
					<td width="10%" align="left">
						<select name="nsCommentModel.isAduit" id="nsFeedbackModel.isReply" style="width:100px">
						   <option value="3" selected="selected">请选择</option>
						   <option value="1" <#if "${nsFeedbackModel.isReply?if_exists}" = "1"> selected="selected"</#if> >是</option>
						   <option value="0" <#if "${nsFeedbackModel.isReply?if_exists}" = "0"> selected="selected"</#if> >否</option>
						</select>
					</td>
					
					<td width="20%" align="left">&nbsp;</td>
					<td width="10%" align="left">&nbsp;</td>
				</tr>
				-->
				<tr>
					<td colspan="8">&nbsp;</td>
				</tr>
				<#--
				<tr>
					<td colspan="8">
					<a href="javascript:search()"><img src="../images/buttom/buttom_search.gif" /></a>
					</td>
				</tr>
				-->
			</table>
			</form>
		</div>
	</div>
	<div class="tablebox">
		<form id="detailForm" name="detailForm" method="post" action="">
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab">
			<tr>
				<th width="4%">状态</th>
				<th width="8%">ID</th>
				<th width="10%">反馈人</th>
				<th width="8%">手机</th>
				<th width="30%">反馈内容</th>
				<th width="20%">反馈时间</th>
				<th width="10%">操作</th>
			</tr>
			<#if pageList?exists>
			<#list pageList as page>
			<tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
				<td align="center">
					<#if "${page.isReply}" == "0">
						<input type="checkbox"  value="${page.id?if_exists}" >
				    </#if>
					<#if "${page.isReply}" != "0">
						<input type="checkbox"  value="${page.id?if_exists}" disabled="false">
					</#if>
				</td>
				<td align="center">${page.id?if_exists}</td>
				<td align="center">${page.operatorName?if_exists}</td>
				<td align="center"><font color="blue">${page.phone?if_exists}</td>
				<td align="center" width="30%" >
					<#if page.content?length gt 50>
					${page.content?if_exists}
					<#else>
						${page.content?if_exists}
					</#if>
				</td>			
				<td align="center">${page.submitTime?if_exists}</td>
				<td align="center">
					<#if "${page.isReply}" == "0">
						<a href="javascript:aduit('${page.id?if_exists}')"><font color="blue"><u>回复</u></font></a>
				    </#if>
					<#if "${page.isReply}" != "0">
						<a href="javascript:unAduit('${page.id?if_exists}')"><font color="grey"><u>追加回复</u></font></a>
					</#if>
					<a href="javascript:del('${page.id?if_exists}')"><font color="red"><u>删除</u></font></a>
				</td>
			</tr>
			</#list>
			</#if>
		</table>
		</form>
		<form id="tailForm" name="tailForm" method="post" action="">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="box_fanye">
			<tr rules="rows">
				<td>
				<!--分页-->
				<#if pageList?exists>
				<#if pageList.recordCount?exists>
					<#assign recordCount=pageList.recordCount />
					<#assign lastPage=pageList.lastPage />
					<#assign maxindexpages=recordCount/pageSize+1 />
					<#if (maxindexpages>10)>
						<#assign maxindexpages=10 />
					</#if>
					<@pg.pager isOffset=true encoder="utf-8" maxPageItems=pageSize maxIndexPages=maxindexpages items=recordCount url="${actionName?if_exists}" export="offset,currentPageNumber=pageNumber" scope = "request">
						<@pg.index>
							<@pg.param name="nsFeedbackModel.phone"  value="${nsFeedbackModel?if_exists.phone?if_exists}"/>
							<#include "/WEB-INF/pages/common/jsp_tags.ftl"/>
						</@pg.index>
					</@pg.pager>
				</td>
				</#if>
				</#if>
			</tr>
		</table>
		</form>
	</div><!-- tablebox DIV END -->
</div><!-- MIDDLE DIV END -->

<div class="bottom" align="center">
	<div class="bottomright"></div>
	<div class="bottomleft"></div>
</div>

</body>
</html>