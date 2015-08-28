<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳进销存系统</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
body {font:12px/20px "宋体","\5b8b\4f53",sans-serif;background-color:#d9d6d6;color:#1e3257;}
-->
</style>

</head>
<body onclick="switchToolBar()" title="隐藏菜单">
<table style="height:100%;width:100%;" border="0" cellpadding="0" cellspacing="0" id="imageTab">
  <tr>
    <td valign="middle" style="cursor:pointer; "><img src="${ctx}/commons/scripts/mztree/images/switch/s_0.gif" id="switchImage" /></td>
  </tr>
</table>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
var lrFrame = parent.document.getElementById("lrFrame");
document.getElementById("imageTab").style.height = lrFrame.offsetHeight + 'px';

var isHiddenFlag = false;
function switchToolBar(){
	var mainFrameset = parent.document.getElementById("mainFrameset");
	var switchImage = document.getElementById("switchImage");
	if(!isHiddenFlag) {
		isHiddenFlag = true;
		switchImage.src = "${ctx}/commons/scripts/mztree/images/switch/s_1.gif";
		document.body.title = "显示菜单";
		mainFrameset.cols = "0,7,*";
	} else {
		isHiddenFlag = false;
		switchImage.src = "${ctx}/commons/scripts/mztree/images/switch/s_0.gif";
		document.body.title = "隐藏菜单";
		mainFrameset.cols = "119,7,*";
	}
}
//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
