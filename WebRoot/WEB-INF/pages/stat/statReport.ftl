<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>MISMP</title>
<link href="${request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
<script language="JavaScript"  type="text/javascript" src="${request.contextPath}/javascript/common/calendar.js"></script>
<script language="JavaScript"  type="text/javascript" src="${request.contextPath}/javascript/common/jquery1.2.6.js"></script>
<script language="JavaScript"  type="text/javascript" src="${request.contextPath}/javascript/common/base.jsp"></script>
<script language="JavaScript"  type="text/javascript" src="${request.contextPath}/javascript/report/report.js"></script>
</head>

<body style="padding-top:5px;padding-bottom:3px;">
<script src="../javascript/DatePicker/WdatePicker.js" type="text/javascript"></script>
<table width="100%" height="99%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="3%"  height="36"  align="left" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_l.gif" ></td>
    <td width="94%" height="36" background="../images/main/all_t_bg.gif"><img src="../images/icon/baobiao.gif"><span class="title"><strong>报表统计</strong> - 业务使用情况统计</span></td>
    <td width="3%"  height="36"  align="right" background="../images/main/all_t_bg.gif"><img src="../images/main/all_t_r.gif" ></td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="top" bgcolor="#FFFFFF" class="allbox">
    <form id="reportForm" name="reportForm" method="post" action="">
    <table width="98%" border="0" cellspacing="0" cellpadding="0" class="boxsearch">
      <tr>
        <td colspan="3">&nbsp;</td>
        </tr>
      <tr>
        <td colspan="3" valign="top" ><table width="100%" border="0" cellpadding="0" cellspacing="5" class="boxsearch2">
            <tr>             
              <td width="9%" align="right" nowrap="nowrap">时间：</td>
              <td width="30%" align="left">
				<input class="Wdate" id="dateStart" name="dateStart" type="text" size="15"  value="${dateStart?if_exists}" onFocus="WdatePicker({dateStart:'%y-%M-01',readOnly:true})"/>
	             至
				<input class="Wdate" id="dateEnd" name="dateEnd" type="text" size="15" value="${dateEnd?if_exists}" onFocus="WdatePicker({dateEnd:'%y-%M-01',readOnly:true})"/>
              </td>
              <td width="30%" align="left">&nbsp;业务：
      			<select id="biz_id" name="biz_id">
   					<#list business as biz><option value="${biz.id?if_exists}" <#if biz_id?exists && biz.id==biz_id>selected</#if>>${biz.name?if_exists}</option></#list>
        		</select>
        	  </td>
		      <td width="30%" align="left">&nbsp;机型：
		        <select id="pt_id" name="pt_id">
		            <#list phonetype as pt><option value="${pt.id?if_exists}" <#if pt_id?exists && pt.id==pt_id>selected</#if>>${pt.name?if_exists}</option></#list>
		        </select>
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
        <a href="#"><img src="${request.contextPath}/images/buttom/buttom_ribaobiao.gif" border="0" onClick="statRegLog('day','view');"></a> 
        <a href="#"><img src="${request.contextPath}/images/buttom/buttom_zhoubaobiao.gif" border="0" onClick="statRegLog('week','view');"></a> 
        <img src="${request.contextPath}/images/buttom/buttom_yuebaobiao.gif" border="0" onClick="statRegLog('month','view');"> 
        <a href="#"><img src="${request.contextPath}/images/buttom/buttom_ziyoubaobiao.gif" border="0" onClick="statRegLog('freedom','view');"></a> 
        <a href="#"><img src="${request.contextPath}/images/buttom/buttom_daochu.gif" border="0" onClick="statRegLog('freedom','export');"></a> 
        <a href="#"><img src="${request.contextPath}/images/buttom/buttom_dayin.gif" border="0" onClick="statRegLog('freedom','print');"></a>
        </td>
      </tr>
    </table>
	</form>
	　
	<table width="98%" border="0" cellspacing="0" cellpadding="0">
		<tr>
		   
           <td>        	
        	<div align="center">        		
        		<script language="JavaScript"  type="text/javascript" src="${request.contextPath}/javascript/ext3.2.0/adapter/ext/ext-base.js"></script> 				    
        		<script language="JavaScript"  type="text/javascript" src="${request.contextPath}/javascript/ext3.2.0/ext-all.js"></script>
            	<style type="text/css">
				    #container {
				        
				    }
				
				    #container .x-panel-header {
					    color:#15428b;
						font-weight:bold; 
					    font-size: 11px;
					    font-family: tahoma,arial,verdana,sans-serif;
					    border-color:#99bbe8;
				  		overflow:hidden;
				    zoom:1;
				    padding:5px 3px 4px 5px;
				    background: transparent no-repeat;
				    text-align: left;
					}
				    #container .x-toolbar {
				        border:1px solid #99BBE8;
				        border-width: 0 0 1px 0;
				    }
				   .chart {
				        background-image: url(${request.contextPath}/javascript/ext3.2.0/examples/chart/chart.gif) !important;
				    }
    		</style>
        	<script type="text/javascript">
        	 Ext.onReady(function(){
        	 	var store = new Ext.data.JsonStore({
			        fields:['date', 'orders', 'querys'],
			        
			        data: [					    
					     <#if dataList?exists && dataList.size() != 0>
					     <#assign d = 0>
					     <#list dataList as vo>
					          <#assign d = d + 1>
					     	 {date:'${vo.logtime?if_exists}', orders: ${vo.order_count?if_exists}, querys: ${vo.query_count?if_exists}}
					     	  <#if d != dataList.size() >
					     	 ,
					    	 </#if>
					     </#list>
					    
					     </#if>
			        ]
		   		});
		   		
// more complex with a custom look
    new Ext.Panel({
        iconCls:'chart',
        title: 'ExtJS.com 业务查询量与订单量',
        frame:true,
        renderTo: 'container',
        width:500,
        height:300,
        layout:'fit',

        items: {
            xtype: 'columnchart',
            store: store,
            url:'${request.contextPath}/javascript/ext3.2.0/resources/charts.swf',
            xField: 'date',
            yAxis: new Ext.chart.NumericAxis({
                displayName: 'Orders',
                labelRenderer : Ext.util.Format.numberRenderer('0,0')
            }),
            tipRenderer : function(chart, record, index, series){
                if(series.yField == 'orders'){
                    return Ext.util.Format.number(record.data.orders, '0,0') + ' orders in ' + record.data.date;
                }else{
                    return Ext.util.Format.number(record.data.querys, '0,0') + ' querys in ' + record.data.date;
                }
            },
            chartStyle: {
                padding: 10,
                animationEnabled: true,
                font: {
                    name: 'Tahoma',
                    color: 0x444444,
                    size: 11
                },
                dataTip: {
                    padding: 5,
                    border: {
                        color: 0x99bbe8,
                        size:1
                    },
                    background: {
                        color: 0xDAE7F6,
                        alpha: .9
                    },
                    font: {
                        name: 'Tahoma',
                        color: 0x15428B,
                        size: 10,
                        bold: true
                    }
                },
                xAxis: {
                    color: 0x69aBc8,
                    majorTicks: {color: 0x69aBc8, length: 4},
                    minorTicks: {color: 0x69aBc8, length: 2},
                    majorGridLines: {size: 1, color: 0xeeeeee}
                },
                yAxis: {
                    color: 0x69aBc8,
                    majorTicks: {color: 0x69aBc8, length: 4},
                    minorTicks: {color: 0x69aBc8, length: 2},
                    majorGridLines: {size: 3, color: 0xdfe8f6}
                }
            },
            series: [{
                type: 'line',
                displayName: 'Querys',
                yField: 'querys',
                style: {
                    image:'bar.gif',
                    mode: 'stretch',
                    color:0x99BBE8
                }
            },{
                type:'line',
                displayName: 'Orders',
                yField: 'orders',
                style: {
                    color: 0x15428B
                }
            }]
        }
    });
        	 })
        	 </script>
         <div id="container"></div>
         </td>
        </tr>
	</table>
	
	
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="box_gongneng"  >
        <tr>
          <td align="right"><a href="right8.html"></a> <a href="right6.html"></a> <a href="#"></a> </td>
        </tr>
      </table>
	  
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="listTab">
        <tr>
          <th width="8%">&nbsp;</th>
           		<th colspan="5">业务使用次数 </th>         
        </tr>
        <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
          <td><div align="center"><strong>访问时间</strong></div></td>
          <td><div align="center"><strong>业务类型</strong></div></td>
          <td><div align="center"><strong>机型</strong></div></td>
          <td><div align="center"><strong>查询次数</strong></div></td>
          <td><div align="center"><strong>下单次数</strong></div></td>         
        </tr>
        <#if dataList?exists && dataList.size() != 0>
        <#list dataList as vo>
        <tr onMouseOver="this.className='row_over'" onMouseOut="this.className='row_out'">
          <td><div align="center" nowrap>${vo.logtime?if_exists}</div></td>
          <td><div align="center">
          <#if vo.biz?if_exists = 'insertFlyLog'>
          	机票
          <#elseif vo.biz?if_exists = 'insertHotelLog'>
            　　　　酒店	
          <#else>
            　　　　
          </#if>
          </div>
          </td>
          <td><div align="center">${vo.phoneType?if_exists}</div></td>
          <td><div align="center">${vo.query_count?if_exists}</div></td>
          <td><div align="center">${vo.order_count?if_exists}</div></td>          
        </tr>
        </#list>
         </#if>
      </table>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="box_fanye">
        <tr>
          <td align="right">&nbsp;</td>
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
<#if print?exists && print.equals("yes")>
<script language="javascript" type="text/javascript">
	window.print();
	window.close();
</script>
</#if>
</html>
