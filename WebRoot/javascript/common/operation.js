 
function confirmDelete(formobject,chkname,msg){
  selected=false;
  for(i=0;i<formobject.elements.length;i++){
   	if (formobject.elements[i].name==chkname) {
       	if(formobject.elements[i].checked) selected=true;
   	}
  }
 if(!selected){
  	alert("没有选中任何记录!");
   	return false;
   }
 if(confirm(msg)) {
         return true;
      }else{
         return false;
      }
}

function setChecked(formobject,ischecked,chkname) {
	for(i=0;i<formobject.elements.length;i++){
  		if(formobject.elements[i].name==chkname) formobject.elements[i].checked=ischecked;
  	}
	return;	
}


function setDefaultOptions(selectObject,defaultvalue){
	opts=selectObject.options;
	for(var i=0;i<opts.length;i++){
		if(opts[i].value==defaultvalue) opts[i].selected=true;
		
	}
	
}

String.prototype.Len = function()
{
    return this.replace(/[^\x00-\xff]/g,"**").length;
}

String.prototype.trim = function()  
{
    return this.replace(/(^\s*)|(\s*$)/g,"");   
} 

function isEnglishOrNumber(s,msg){   
    var reg = /^[a-zA-Z0-9_-]*$/gi;   
    if(reg.test(s.value)){
    	return   true   
    } else {   
      alert((msg?msg:'\u8be5\u680f\u76ee\u53ea\u80fd\u8f93\u5165\u82f1\u6587\u5b57\u7b26\u6216\u6570\u5b57\uff0c\u8bf7\u6539\u6b63'));   
      s.focus();   
      s.select();   
      return false;   
    }   
} 

function doValidate(value)
{
	vkeyWords=/^[^`~!$%^&*()+=|\\\][\]\{\}:;'\,<>/?]{0}[^`~!@$%^&()+=|\\\][\]\{\}:;'\,<>?]{0,19}$/; 
	if(!vkeyWords.test(value))
	{
		alert("\u60a8\u7684\u8f93\u5165\u5b58\u5728\u975e\u6cd5\u5b57\u7b26\u6216\u8005\u8d85\u51fa\u957f\u5ea6\u9650\u5236,\u8bf7\u91cd\u65b0\u8f93\u5165!");
		return false;
	}   
	return true;
}

