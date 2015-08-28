<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<!--<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />-->
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">//<![CDATA[

//]]></script>
<style type="text/css">
html {overflow:hidden;}
</style>
</head>
<frameset rows="20, *" cols="*" border="0" frameborder="no" framespacing="0">
	<frame name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame"></frame>
	<frameset cols="400, 6,*" framespacing="0" frameborder="no" border="0" id="setyou">
	  <frame src="KonkaXxMessage.do?method=list&user_id=${sessionScope.userInfo.id}" name="msgLeftFrame" marginwidth="0" marginheight="0" id="msgLeftFrame" title="msgLeftFrame" noresize="noresize" scrolling="auto"/>
	  <frame src="KonkaXxMessageFrame.do?method=middle" name="msgMiddleFrame" marginwidth="0" marginheight="0" id="msgMiddleFrame" title="msgMiddleFrame" noresize="noresize" scrolling="no"/>
	  <frame src="KonkaXxMessage.do?method=view&out_id=${af.map.msg_id}" name="msgMainFrame" marginwidth="0" marginheight="0" scrolling="yes" id="msgMainFrame" title="msgMainFrame"  noresize="noresize" />
	</frameset>
</frameset>
<body>
<jsp:include page="/__analytics.jsp" />
</body>
</html>