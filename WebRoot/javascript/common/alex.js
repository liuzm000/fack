window.onerror = function(){}
var sdivTop,sdivLeft,sdivWidth,sdivHeight,sdocHeight,sdocWidth,sobjTimer,i = 0;
function getMsg()
{
 try{
	 sdivTop = parseInt(document.getElementById("location").style.top,10)
	 sdivLeft = parseInt(document.getElementById("location").style.left,10)
	 sdivHeight = parseInt(document.getElementById("location").offsetHeight,10)
	 sdivWidth = parseInt(document.getElementById("location").offsetWidth,10)
	 sdocWidth = document.body.clientWidth - 0;
	 sdocHeight = document.body.clientHeight;
	 document.getElementById("location").style.top = parseInt(document.body.scrollTop,10) + sdocHeight + 10;//  divHeight
	 document.getElementById("location").style.left = parseInt(document.body.scrollLeft,10) + sdocWidth - sdivWidth
	 document.getElementById("location").style.visibility="visible"
	 sobjTimer = window.setInterval("moveDiv()",10)
 }
 catch(e){}
}

function resizeDiv()
{
 //i+=1
 //if(i>500) 
 	//closeDiv();
 try{
	 sdivHeight = parseInt(document.getElementById("location").offsetHeight,10)
	 sdivWidth = parseInt(document.getElementById("location").offsetWidth,10)
	 sdocWidth = document.body.clientWidth;
	 sdocHeight = document.body.clientHeight;
	 document.getElementById("location").style.top = sdocHeight - sdivHeight + parseInt(document.body.scrollTop,10)
	 document.getElementById("location").style.left = sdocWidth - sdivWidth + parseInt(document.body.scrollLeft,10) - 0
 }
 catch(e){}
}

function moveDiv()
{
 try
 {
	 if(parseInt(document.getElementById("location").style.top,10) <= (sdocHeight - sdivHeight + parseInt(document.body.scrollTop,10)))
	 {
		 window.clearInterval(sobjTimer)
		 sobjTimer = window.setInterval("resizeDiv()",1)
	 }
	 sdivTop = parseInt(document.getElementById("location").style.top,10)
	 document.getElementById("location").style.top = sdivTop - 1
 }
 catch(e){}
}
function closeDiv()
{
 document.getElementById('location').style.visibility='hidden';
 if(sobjTimer) 
 	window.clearInterval(sobjTimer);
}
