<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %> 
<!DOCTYPE html>
<html>
<head> 
<title>Flash 播放</title>   
<script type="text/javascript"> 
var url="${ctx}/touch/FlvPlayer.do?method=view&id=${af.map.id}&width=720&height=426";
var img_url="${ctx}/touch/FlvPlayer.do?method=showImg&id=${af.map.id}&width=720&height=426"; 
function showdiv() {  
	window.top.document.getElementById("bg").style.display ="block";
    window.top.document.getElementById("show").style.display ="block";  
    window.top.document.getElementById("player").src=url;
}
function hidediv() {
    window.top.document.getElementById("bg").style.display ='none';
    window.top.document.getElementById("show").style.display ='none';
    window.top.document.getElementById("player").src=img_url;
} 
</script>
</head>
<body> 
<div id="eppFlvPlayer_1" style="width:<c:out value="${empty width ? 315:width  }"/>px; margin:0 auto; border:solid 1px gray;color:gray;" >
<c:if test="${not empty imgUrl }">
<img src="http://epp.konka.com/<c:out value="${imgUrl}"/>" width="<c:out value='${empty width ? 315:width }'/>" height="<c:out value='${empty height ? 305:height }'/>" onclick="showdiv();"/>
</c:if>
</div>	 
</body>
</html>
