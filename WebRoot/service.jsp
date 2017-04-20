<%@ page contentType="text/html;charset=utf-8"%>
<%
String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
			
String baseUrl="http://localhost:8080/wqstravel/";

String serverUrl="http://113.108.186.146:9002/wqstravel/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>万顷沙接口demo</title>
<link href="<%=basePath%>css/login.css" rel="stylesheet" type="text/css">
 <script type="text/javascript" src="<%=baseUrl%>/javascript/jquery-core/jquery-1.3.js"></script>



</head>

<body>




		<br> <br/>
  	  <a id="test"  href="<%=baseUrl%>travel.htm?method=getYujiaInfo&imsi=1234455&esn=&category=android&phone=XT800" target="_blank">
  	  [1]:渔家乐介绍</a>
  	  
		<br> <br/>
  	  <a id="test"  href="<%=baseUrl%>travel.htm?method=getYujiaList&typeId=1&imsi=1234455&esn=&category=android&phone=XT800" target="_blank">
  	  [1]:渔家乐列表</a>
  	  
		<br> <br/>
  	  <a id="test"  href="<%=baseUrl%>travel.htm?method=getNongJiaLe&imsi=1234455&esn=&category=android&phone=XT800" target="_blank">
  	  [1]:农家乐列表</a>
  	  
		<br> <br/>
  	  <a id="test"  href="<%=baseUrl%>travel.htm?method=getRestaurantList&imsi=1234455&esn=&category=android&phone=XT800" target="_blank">
  	  [1]:餐馆列表</a>
  	  
		<br> <br/>
  	  <a  href="<%=baseUrl%>travel.htm?method=getHospitalService&imsi=1234455&esn=&category=android&phone=XT800" target="_blank">
  	  [1]:医疗服务列表</a>
  	  
		<br> <br/>
  	  <a  href="<%=baseUrl%>travel.htm?method=getTownJobs&imsi=1234455&esn=&category=android&phone=XT800" target="_blank">
  	  [1]:招聘信息列表</a>
  	  
		<br> <br/>
  	  <a  href="<%=baseUrl%>travel.htm?method=getAlgGoodsType&imsi=1234455&esn=&category=android&phone=XT800" target="_blank">
  	  [1]:农产品列表</a>
  	  
		<br> <br/>
  	  <a  href="<%=baseUrl%>travel.htm?method=getAlgGoods&typeId=3&imsi=1234455&esn=&category=android&phone=XT800" target="_blank">
  	  [1]:农产品详细</a>
  	  
  	  
		<br> <br/>
  	  <a  href="<%=baseUrl%>travel.htm?method=getEnterpriseList&imsi=1234455&esn=&category=android&phone=XT800" target="_blank">
  	  [1]:企业名录</a>
  	  
  	  
		<br> <br/>
  	  <a  href="<%=baseUrl%>travel.htm?method=getBeautifulCountry&imsi=1234455&esn=&category=android&phone=XT800" target="_blank">
  	  [1]:美丽乡村</a>
  	  
  	  
  	  
		<br> <br/>
  	  <a href="<%=baseUrl%>travel.htm?method=getPictorialType&phone=ios&category=ios&pagesize=10&page=" target="_blank">
  	  [1]:沙田画卷分类</a>

		<br> <br/>
  	  <a href="<%=baseUrl%>travel.htm?method=getShaTianPictorial&typeId=1&imsi=1234455&esn=&category=android&phone=XT800&page=1&pagesize=20" target="_blank">
  	  [1]:沙田画卷分类下的画卷</a>


		<br> <br/>
  	  <a href="<%=baseUrl%>travel.htm?method=getEcoTourismType&imsi=1234455&esn=&category=android&phone=XT800&phoneNum=xxxxx" target="_blank">
  	  [1]:生态旅游分类列表</a>

		<br> <br/>
  	  <a href="<%=baseUrl%>travel.htm?method=getEcoTourismType&imsi=1234455&esn=&category=android&phone=XT800&page=1&pagesize=20" target="_blank">
  	  [1]:生态旅游某类图集</a>















</body>
</html>
