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
    <th>财务报表</th>
  </tr>
  <tr>
    <td id="fplmlfx"><a href="JxcPdTypeAnalysis.do" name="fplmlfx" target="mainFrame">分品类毛利分析</a></td>
  </tr>
  <tr>
    <td id="fxhmlfx"><a href="JxcPdModelAnalysis.do" name="fxhmlfx" target="mainFrame">分型号毛利分析</a></td>
  </tr>
  <tr>
    <td id="fxkhmlfx"><a href="JxcCustomerAnalysis.do" name="fxkhmlfx" target="mainFrame">分销客户毛利分析</a></td>
  </tr>
  <tr>
    <td id="xssjfx"><a href="JxcSellDataAnalysis.do" name="xssjfx" target="mainFrame">销售数据分析</a></td>
  </tr>
  <!--<tr>
    <td id="fx5"><a href="Frames.do?method=main" name="fx5" target="mainFrame">调价补差</a></td>
  </tr>
  <tr>
    <td id="fx6"><a href="Frames.do?method=main" name="fx6" target="mainFrame">年终返利</a></td>
  </tr>
  --><tr>
    <th>基础信息</th>
  </tr>
  <tr>
    <td id="wdxx"><a href="JxcMyProfile.do" name="wdxx" target="mainFrame">我的信息</a></td>
  </tr>
  <tr>
    <td id="bdsj"><a href="KonkaJxcBindingMobile.do?method=list" name="bdsj" target="mainFrame">绑定手机</a></td>
  </tr>
  <!--<tr>
    <td id="jxsyd"><a href="../jxcnokey/Jxsyd/jxsyd.jsp" name="jxsyd" target="mainFrame">经销商园地</a></td>
  </tr>
  -->
  <tr>
    <th>信息接收</th>
  </tr>
  <tr>
    <td id="zxxx"><a href="JxcReceiveInfo.do?infoType=zxxx" name="zxxx" target="mainFrame">价格政策</a></td>
  </tr>
  <tr>
    <td id="znx"><a href="JxcReceivePeShopMsg.do?infoType=znx" name="znx" target="mainFrame">站内信</a></td>
  </tr>
  <tr>
    <td id="xcpxx"><a href="JxcReceiveInfo.do?infoType=xcpxx" name="xcpxx" target="mainFrame">产品资料</a></td>
  </tr>
  <!--<tr>
    <th>经销商园地</th>
  </tr>
  <tr>
    <td id="jxsyd"><a href="../jxcnokey/Jxsyd/mainFrame.jsp" name="jxsyd" target="mainFrame">经销商园地</a></td>
  </tr>
	-->
</table>
<jsp:include page="_public_left_js.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>