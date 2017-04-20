var platformCode="";
function platformChange(platform){
    platformCode=platform.value;
    var result="";
    if(platformCode == ""){     
		result+="<select id=model name=model>";
		result+="<option value=''>请选择型号</option>";
		result+="</select>";
		//alert(result);	  	
    } else if(platformCode == "J2ME"){  
    	result+="<select id=model name=model>";
		result+="<option value=''>请选择型号</option>";
		result+="<option value='LG kw 838'>LG kw 838</option>";
		result+="</select>";
    } else if(platformCode == "Mobile"){  
    	result+="<select id=model name=model>";
		result+="<option value=''>请选择型号</option>";
		result+="<option value='S500'>多普达 S500</option>";
		result+="<option value='S500Z'>多普达 S500增强版</option>";
		result+="<option value='S505'>多普达 S505</option>";
		result+="</select>";   
    }
    document.getElementById("modelSpan").innerHTML=result;
    return;
   //regionInfoManager.getCityListbyProvinceCode(provinceCode,cityList);
}

function statRegLog(timeRange,type){   //type为view是查看统计结果，为export是导出报表，为print是打印
	 var dateStart=document.getElementById("dateStart").value;//必须是yyyy-MM-dd形式
	 if(dateStart==""){
	       alert("请选择统计开始日期");
	       return;
	 }

	if(timeRange=="freedom"){
	    var dateEnd=document.getElementById("dateEnd").value;
	    if(dateStart==""){
	       alert("请选择统计开始日期");
	       return;
	    }
	    if(dateEnd==""){
	       alert("请选择统计结束日期");
	       return;
	    }
	    var status=checkDateTime(dateStart,dateEnd);
	    if(status!=1){
	      alert("统计开始日期不能大于或等于结束日期");
	      return;
	    }
	}
	var biz_id=document.getElementById("biz_id").value;//业务
	var pt_id = document.getElementById("pt_id").value;  //机型
	var form=document.getElementById("reportForm");
    if(type=="chart"){
       form.action=context+"/stat/statReportChart.action?timeRange="+timeRange;
    }else if(type=="export"){
       form.action=context+"/stat/statReportExport.action?timeRange="+timeRange;
    }else if(type=="print"){    
       //var item = get_radio_value(form.item);
       var url=context+"/stat/statReport.action?timeRange=freedom&print=yes&dateStart="+dateStart+"&dateEnd="+dateEnd+"&biz_id="+biz_id+"&pt_id="+pt_id;
       window.open(url,'','height=600, width=800, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');
       return;
    }else{//view或其他
       form.action=context+"/stat/statReport.action?timeRange="+timeRange;
    }
    form.submit();
} 

function checkDateTime(from,to) { 
	 if(from==""||to=="")
	    return 2; 
	 var rValue = 0 ; 
	 for(var i=0 ;i<10 ;i++) { 
	   if(from.charAt(i) < to.charAt(i)) { 
	   		rValue = 1 ; break ; 
	   }else if(from.charAt(i) > to.charAt(i)) {
	   		rValue = -1 ; break ; 
	   } 
 	 }
 	 return rValue ; 
}

function get_radio_value(field){ 
	var radioValue = -1;
    if (field && field.length){ 
        for (var i = 0; i < field.length; i++){ 
            if (field[i].checked){  
                radioValue = field[i].value; 
            } 
        } 
    }
    return radioValue;
}