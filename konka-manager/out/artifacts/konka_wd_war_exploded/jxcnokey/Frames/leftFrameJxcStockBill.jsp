<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳进销存系统</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body style="background:#e8e8e8;">
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="leftFramesTable" id="leftFramesTable">
  <tr>
    <th>进货管理</th>
  </tr>
  <tr>
    <td id="jhdj"><a href="JxcStockBill.do?method=add" name="jhdj" target="mainFrame">进货登记</a></td>
  </tr>
  <tr>
    <td id="jhjl"><a href="JxcStockBill.do" name="jhjl" target="mainFrame">进货记录</a></td>
  </tr>
  <tr>
    <td id="shqr"><a href="JxcReceiveStockConfirm.do" name="shqr" target="mainFrame">收货确认</a></td>
  </tr>
</table>
<jsp:include page="_public_left_js.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>