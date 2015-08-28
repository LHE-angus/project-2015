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
<body style="margin:0px;padding:0px;">
<iframe id="indexFrame" src="${ctx}/manager/admin/Frames.do?method=index" style="width:100%;">
        本系统使用了框架技术，但是您的浏览器不支持框架，请升级您的浏览器以便正常访问。
</iframe>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>

<script type="text/javascript">
	//<![CDATA[
	$(document)
			.ready(
					function() {     
						$("#indexFrame").height($(window).height()-5);
					});

	//]]>
</script>
</body>
</html>