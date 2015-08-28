<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳产品展示平台</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/pshow/css/global.css">
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/pshow/css/sale.css">
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/pshow/css/buyer.css">
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<!-- head start -->
<div id="topbox">
  <jsp:include page="_inc/_top2.jsp" flush="true" />
  <jsp:include page="_inc/_head2.jsp" flush="true" />
</div>
<!-- head end --> 
<!-- topnav start -->
<jsp:include page="_inc/_head_nav22.jsp" flush="true" />
<!-- topnav end --> 

<!-- third start -->
<div class="maincont">
<form action="${ctx}/Payment.do" method="post" id="id_form">
	<input type="hidden" name="method" value="list" />
	<input type="hidden" name="trade_index" value="${trade_index}" />
	<div class="buytwo"></div>
	<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" style="border-bottom:1px #e77611 solid; margin-top:30px;">
	  <tr>
	    <td width="633" height="62" align="left" style="font-size:14px; font-weight:bold; padding-left:20px;"><img src="${ctx}/styles/customer/pshow/images/right.jpg" width="55" height="44"  align="absmiddle"/> 订单已成功提交！</td>
	    <td width="127">&nbsp;</td>
	  </tr>
	</table>
	<table width="689" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:30px;">
	  <tr>
	    <td width="689" height="35" align="left" style="font-size:14px; font-weight:bold;">订单信息</td>
	  </tr>
	  <tr>
	    <td style="border:1px #feb27f solid; border-bottom:none; background:#fef3d7;">
	    <ul class="message">
	     <li><b>交易流水号：</b><font class="orange">${trade_index}</font></li>
	         <li><b>订单金额：</b><font class="orange"><fmt:formatNumber value="${total_price}" type="currency" /></font></li>
	    </ul>
	    </td>
	  </tr>
	</table>
	<table width="350" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:50px;">
	  <tr>
	    <td width="350" height="71" align="center">
	    	<input type="button" id="pay_btn" class="pay_btn" style="border:0px;" name="pay_btn" value="立即支付" />	
	    </td>
	  </tr>
	</table>
</form>
  <div class="clear"></div>
</div>
<!-- third end --> 

<!-- six start -->
<div class="maincont"> 
  <!-- footer start-->
  <jsp:include page="_inc/_footer2.jsp" flush="true" />
  <!-- footer end--> 
</div>
<!-- six end --> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	// 立即支付
	$("#pay_btn").click(function(){
		$("#id_form").submit();
	});
});
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
