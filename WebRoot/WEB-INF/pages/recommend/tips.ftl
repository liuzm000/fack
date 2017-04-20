<#assign rootPath=request.scheme+"://"+request.serverName+":"+request.serverPort+request.contextPath />
<#assign rootPathOther=request.scheme+"://"+request.serverName+":"+request.serverPort />
<script language="javascript" type="text/javascript">
<#if tips?exists && !tips.equals("")>
   alert("${tips}");
</#if>
window.location.href="${rootPath}/<#if forward?exists>${forward}</#if>";
</script>