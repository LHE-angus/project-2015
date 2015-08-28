<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frames1et//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>康佳渠道管理系统</title>
</head>
<frameset rows="0,100%" border="0" id="mainFrameset">
  <frame src="about:blank" />
  <frame src="BaseProp.do?method=listBasePropCategory&cls_id=${af.map.cls_id}&category_name_like=${af.map.category_name_like}" />
  <noframes>
  <body>
  对不起，您的浏览器不支持框架
  </body>
  </noframes>
</frameset>
</html>
