<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>以旧换新销售 - 康佳进销存系统</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<frameset rows="*" cols="*" >
  <frame src="http://${ctxMmt}/client/manager/YjhxProSellReg.do?keySeq=${af.map.keySeq}" name="jdxxFrame" scrolling="yes" noresize="noresize" id="jdxxFrame" title="jdxxFrame" />
    <noframes>
    <body>
    对不起，您的浏览器不支持框架
    </body>
    </noframes>
</frameset>
</html>