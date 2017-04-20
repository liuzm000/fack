$(document).ready(function() {
	$("#viewTree").attr({width:200,style:"padding-left:5px"});
	$("#viewTree").html("" + 
	"<TABLE id='treeTab' cellSpacing='3' border='0' cellpadding='0'>" +
		"<TR id='spTreeTR'>" +
			"<TD>"+
				"<UL class='simpleTree' id='spTree'>" +
					"<LI class='root'><SPAN style='cursor:pointer;'>" +
						"<SPAN>SP列表及产品目录</SPAN></SPAN>" +
						"<UL id='spTreeXML'>" +
						"</UL>" +
					"</LI>" +
				"</UL>" +
			"</TD>" +
		"</TR>"+
	"</TABLE>"
	);
	initSpTree();
});

/**
 * 初始化SP显示树
 */
function initSpTree() {
	var addUrl = this.request();
	$.ajax({url : addUrl + "/spManage.do?action=ajaxShowSpXML&addUrl="+addUrl,
			dataType : "html",
			cache : false,
			success : function(html) {
					//alert(html);
					//$("#spTreeXML").empty();
                    $("#spTreeXML").append(html);
                    //alert($("#entTree").html());
                    var enterpriseTree = $("#spTree").simpleTree({
                        autoclose: false,
                        drag:false,
                        afterClick:function(node) {
                        	if(node.attr("ray")){
								var id = node.attr("id");
								window.parent.right.rightPage.location = addUrl 
															+ "/spManage.do?action=showSoftTypeList&proId="+id
															+ "&pageNum=1&pageSize=10";
                        	}
                        },
                        animate:false
                    });
				}
			});
	}

/**
 * 获取当前应用的URL
 */
function request(paras){
	var url = location.href;
	var urlArr = url.split("\/");
	var urlFinal = urlArr[0] + "\/" + urlArr[1] + "\/" + urlArr[2] + "\/" + urlArr[3];
	return urlFinal;
}