<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<META http-equiv=Expires content=0>
<META http-equiv=Cache-Control content=no-cache>
<META http-equiv=Pragma content=no-cache>
<title>康佳电器</title>
<link rel="stylesheet" type="text/css" href="${ctx}/mobile/css/common.css" />
</head>

<body>
<div id="fileContent">
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

var recordNum = 0;
$(document).ready(function(){
	$.ajax({//ajax 取数据：
		type: "POST",
		url: "OaFile.do",
		data: "method=list&username="+getQueryString("username")+"&userpass="+getQueryString("userpass"),
		dataType: "text",
		error: function(request, settings) {alert();},
		success: function(Datas) {
			$("#fileContent").html(Datas);
		}
	});
	$.ajax({//ajax 取记录数：
		type: "POST",
		url: "OaFile.do",
		data: "method=getCount&username="+getQueryString("username")+"&userpass="+getQueryString("userpass"),
		dataType: "text",
		error: function(request, settings) {alert();},
		success: function(Datas) {
			recordNum = Datas;
		}
	});
});

function goview(url){
	location.href ="${ctx}/"+url;
	//alert(url);
}

function goPage(method,page,forward,pagelimit){
	pagelimit = Math.ceil(recordNum / pagelimit);
	if(page == 1 && forward == -1){
		alert("已到首页！");
		return false;
	}
	else if(page == pagelimit && forward == 1){
		alert("已到尾页！");
		return false;
	}
	else if(pagelimit == 0){
		alert("无翻页信息！");
		return false;
	}
	else
		return true;
}

function goback(page,username,userpass) {
	if(goPage(null,page,-1,10)){
		$.ajax({//ajax 取数据：
			type: "POST",
			url: "OaFile.do",
			data: "method=list&username="+getQueryString("username")+"&userpass="+getQueryString("userpass")+"&page="+page+"&forward=-1",
			dataType: "text",
			error: function(request, settings) {alert();},
			success: function(Datas) {
				$("#fileContent").html(Datas);
			}
		});
	}
}

function goforward(page,username,userpass) {
	if(goPage(null,page,1,10)){
		$.ajax({//ajax 取数据：
			type: "POST",
			url: "OaFile.do",
			data: "method=list&username="+getQueryString("username")+"&userpass="+getQueryString("userpass")+"&page="+page+"&forward=1",
			dataType: "text",
			error: function(request, settings) {alert();},
			success: function(Datas) {
				$("#fileContent").html(Datas);
			}
		});
	}
}
</script> 
<jsp:include page="/__analytics.jsp" />
</body>
</html>