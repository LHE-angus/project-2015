<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
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
    <th>库存管理</th>
  </tr>
  <tr>
    <td id="sskc"><a href="JxcStock.do" name="sskc" target="mainFrame">实时库存</a></td>
  </tr>
  <tr>
    <td id="cpgl"><a href="JxcPd.do" name="cpgl" target="mainFrame">产品管理</a></td>
  </tr>
  <!--<tr>
    <td id="ppsq"><a href="JxcBrandApply.do" name="ppsq" target="mainFrame">品牌申请</a></td>
  </tr>
  --><tr>
    <th style="padding-left:8px;">退换货管理</th>
  </tr>
  <tr>
    <td id="thsq"><a href="JxcThApply.do" name="thsq" target="mainFrame">退货申请</a></td>
  </tr>
  <tr>
    <td id="hhsq"><a href="JxcHhApply.do" name="hhsq" target="mainFrame">换货申请</a></td>
  </tr>
  <tr>
    <th>盘存管理</th>
  </tr>
  <tr>
    <td id="pc"><a href="JxcPcInfo.do" name="pc" target="mainFrame">盘存管理</a></td>
  </tr>
</table>
<jsp:include page="_public_left_js.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>