<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网百业务支撑系统管理平台</title>
<link href="${request.contextPath}/css/left.css" rel="stylesheet" type="text/css">
<link rel="StyleSheet" href="${request.contextPath}/css/dtree.css" type="text/css" />
<script type="text/javascript" src="${request.contextPath}/javascript/dtree.js"></script>
</head>
<body style="margin-top:5px; margin-bottom:3px; margin-left:7px;">
<table width="100%"  height="100%" border="0" cellspacing="0" cellpadding="0" >
 <tr>
    <td width="14" height="25"  background="${request.contextPath}/images/left/box_t_l.gif"><img src="${request.contextPath}/images/left/box_t_l.gif"></td>
    <td  background="${request.contextPath}/images/left/box_t_m.gif"><img src="${request.contextPath}/images/left/box_t_m.gif"></td>
    <td width="14" background="${request.contextPath}/images/left/box_t_r.gif"><img src="${request.contextPath}/images/left/box_t_r.gif"></td>
  </tr>
  <tr>
    <td width="14" height=""background="${request.contextPath}/images/left/box_l.gif"><img src="${request.contextPath}/images/left/space.gif" width="1" height="1"></td>
    <td height="100%" valign="top" bgcolor="#EDF4FC">
<!-------- 树 star   ---------------------------------->  
<div class="dtree">
<script>
   var ids = new Array();
   var preids = new Array();
   var names = new Array();
   var url = new Array();
   var varModuleArray = new Array(ids,preids,names,url);
   
</script>
<script>
<#assign i=0/>
<#list haveModuleList as aml>
   <#if (aml.module_type)==1>	
		ids["${i}"] = "${aml.module_id?if_exists}";
		preids["${i}"] = "${aml.parent_module_id?if_exists}";
		names["${i}"] = "${aml.module_name?if_exists}";
		<#assign len = aml.module_url?length/>
		<#if (len>7)>
			var httpurl="${aml.module_url[0..6]?if_exists}";
			if(httpurl=="http://")
			{
				url["${i}"] = "${aml.module_url?if_exists}";
			}else
			{
				url["${i}"] = "${request.contextPath}${aml.module_url?if_exists}";
			}
		<#else>
			url["${i}"] = "${request.contextPath}${aml.module_url?if_exists}";
		</#if>
	<#assign i=i+1> 
	</#if>
</#list>
</script>
		
	<script type="text/javascript">
        //my system
        ds = new dTree('ds');
        ds.add(0001,-1,'管理控制台','','','mainFrame');
        for(var i=1;i<ids.length;i++)
        {
           if("${request.contextPath}"==url[i])
           {
           	ds.add(ids[i],preids[i],names[i],"",names[i],'mainFrame');
           }
           else
           {
           	ds.add(ids[i],preids[i],names[i],url[i],names[i],'mainFrame');
           }
        }
        
		
   document.write(ds);
   ds.closeAll();
  </script>

</div>  
<!-------- 树 end   ----------------------------------->
    </td>
    <td width="14" background="${request.contextPath}/images/left/box_r.gif"><img src="${request.contextPath}/images/left/space.gif" width="1" height="1"></td>
  </tr>
  <tr>
    <td width="14" height="25"  background="${request.contextPath}/images/left/box_d_l.gif">&nbsp;</td>
    <td  background="${request.contextPath}/images/left/box_d_m.gif"></td>
    <td width="14" background="${request.contextPath}/images/left/box_d_r.gif">&nbsp;</td>
  </tr>
</table>
</body>
</html>
