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
<style type="text/css">
<!--
-->
</style>
</head>
<body style="background:#e8e8e8;">
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="leftFramesTable" id="leftFramesTable">
  <tr>
    <th>产品销售</th>
  </tr>
  <tr>
    <td id="xsdj"><a href="JxcSellBill.do" name="xsdj" target="mainFrame">销售登记</a></td>
  </tr>
  <tr>
    <td id="xsjl"><a href="JxcSellBill.do?method=list" name="xsjl" target="mainFrame">销售记录</a></td>
  </tr>
  <tr>
    <td id="fxqr"><a href="JxcFxConfirm.do?method=list" name="fxqr" target="mainFrame">分销确认</a></td>
  </tr>
  <tr>
    <td id="jxsjl"><a href="JxcJnhmSellBill.do?method=list" name="jxsjl" target="mainFrame">节能惠民销售记录</a></td>
  </tr>
  <tr>
    <td id="bttj"><a href="JnhmAllowance.do" name="bttj" target="mainFrame">节能惠民补贴统计</a></td>
  </tr>
  <tr>
    <td id="ckhz"><a href="JnhmEntpSellDetailsAgentReport.do" name="ckhz" target="mainFrame">月出库汇总</a></td>
  </tr>
  <tr>
    <td id="xsazhz"><a href="JnhmEntpSellDetailsReport.do" name="xsazhz" target="mainFrame">月销售安装汇总</a></td>
  </tr>
</table>
<jsp:include page="_public_left_js.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>