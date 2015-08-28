<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>middleFrame</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body onclick="hidFrame();" style=" cursor:pointer;" title="隐藏菜单">
<!--向左侧隐藏菜单-->
<div id="showmenu" class="oararrow">
<img src="../../images/manager/arrow_left.gif" width="6" height="24" name="img1" id="img1" />
</div>
<script type="text/javascript">
//<![CDATA[
var currentChoice = true;
arrow1=new Image;
arrow2=new Image;
arrow1.src="../../images/manager/arrow_left.gif";
arrow2.src="../../images/manager/arrow_right.gif";

$(document).ready(function(){
	$("#img1").attr("src", arrow1.src);
});

function hidFrame(){
	if($("#img1").attr("src") == arrow1.src){
		$("#img1").attr("src", arrow2.src);
		$("#img1").attr("alt", "显示菜单");
		window.parent.document.getElementById("setyou").cols = "0,6,*";
	} else {
		$("#img1").attr("src", arrow1.src);
		$("#img1").attr("alt", "隐藏菜单");
		window.parent.document.getElementById("setyou").cols="167,6,*";
	}
	currentChoice = !currentChoice;
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>