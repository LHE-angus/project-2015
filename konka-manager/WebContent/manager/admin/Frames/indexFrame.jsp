<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frames1et//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>康佳渠道管理系统</title>
<script type="text/javascript">//<![CDATA[
if(self != top){
	top.location = self.location;
}
//]]></script>
</head>
<frameset rows="67,*,20" cols="*" border="0" frameborder="no" framespacing="0">
  <frame src="Frames.do?method=top" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />
	<frameset cols="182,6,*" framespacing="0" frameborder="no" border="0" id="setyou">
	  <frame src="Frames.do?method=left" name="leftFrame" marginwidth="0" marginheight="0" id="leftFrame" title="leftFrame" noresize="noresize" scrolling="auto"/>
	  <frame src="Frames.do?method=middle" name="middleFrame" marginwidth="0" marginheight="0" id="middleFrame" title="middleFrame" noresize="noresize" scrolling="no"/>
	  <frame src="Frames.do?method=main&url=${url}" name="mainFrame" marginwidth="0" marginheight="0" scrolling="yes" id="mainFrame" title="mainFrame"  noresize="noresize" />
	</frameset>
	<frame src="Frames.do?method=footer" name="footerFrame" scrolling="no" noresize="noresize" id="footerFrame" title="footerFrame" />
</frameset>
<noframes>
<body>
对不起，您的浏览器不支持框架
<jsp:include page="/__analytics.jsp" />
</body>
</noframes>
</html>
