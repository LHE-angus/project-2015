<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %> 
<!DOCTYPE html>
<html>
<head> 
<title>Flash 播放</title>  
<script type="text/javascript" src="${ctx }/scripts/flvPlayer/swfobject.js"></script>
</head>
<body> 
<div id="eppFlvPlayer_1" style="width:<c:out value="${empty width ? 315:width  }"/>px; margin:0 auto; border:solid 1px gray;color:gray;" ></div>	
<script type="text/javascript"> 
var eppFlvPlayer = new SWFObject("${ctx }/scripts/flvPlayer/FlvPlayer201002.swf","playlist","<c:out value='${empty width ? 315:width }'/>","<c:out value='${empty height ? 305:height }'/>","0");
eppFlvPlayer.addParam("allowfullscreen","true");
eppFlvPlayer.addVariable("autostart","true");   
eppFlvPlayer.addVariable("file","<c:out value='${flvUrl }'/>"); 
eppFlvPlayer.addVariable("backcolor","gray");
eppFlvPlayer.addVariable("BarColor","0xFFFFFF");
eppFlvPlayer.addVariable("fontcolor","0xFFFFFF");
//s5.addVariable("logo","${ctx }/images/v.png");
eppFlvPlayer.write("eppFlvPlayer_1");
</script>
</body>
</html>