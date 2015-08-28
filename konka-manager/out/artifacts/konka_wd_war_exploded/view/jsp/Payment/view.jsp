<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳产品展示平台</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/pshow/css/global.css">
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/pshow/css/sale.css">
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/pshow/css/order.css">
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<!-- head start -->
<div id="topbox">
  <jsp:include page="/customer/PShow/_inc/_top2.jsp" flush="true" />
  <jsp:include page="/customer/PShow/_inc/_head2.jsp" flush="true" />
</div>
<!-- head end --> 
<!-- topnav start -->
<jsp:include page="/customer/PShow/_inc/_head_nav22.jsp" flush="true" />
<!-- topnav end --> 

<!-- third start -->
<div class="maincont">
    <div class="buybox">
      <div class="address">
        <ul id="address-list" class="address-list">
          <li class="selected"><span class="marker-tip">订单状态：</span>&nbsp;&nbsp;
          		<c:choose>
          			<c:when test="${state eq '-30'}">已退货</c:when>
          			<c:when test="${state eq '-20'}">审核未通过</c:when>
          			<c:when test="${state eq '-10'}">已关闭</c:when>
          			<c:when test="${state eq '0'}">已预订</c:when>
          			<c:when test="${state eq '10'}">已付款</c:when>
          			<c:when test="${state eq '20'}">审核通过</c:when>
          			<c:when test="${state eq '30'}">下发处理</c:when>
          			<c:when test="${state eq '40'}">商家发货</c:when>
          			<c:when test="${state eq '50'}">客户已换货</c:when>
          			<c:when test="${state eq '60'}">确认收货</c:when>
          			<c:otherwise>未知状态</c:otherwise>
          		</c:choose>
          </li>
        </ul>
    </div>
    <div class="buy_list"><font class="orange14px">收货人信息</font></div>
    <div class="buybox">
      <div class="address">
        <ul id="address-list" class="address-list">
          <li class="selected"><span class="marker-tip">寄送至：</span>&nbsp;${link_p_index_name}(${real_name}&nbsp;收)&nbsp;<em>${link_phone}<c:if test="${not empty link_tel}">/${link_tel}</c:if></em></li>
        </ul>
    </div>
	<div class="buy_list" style="margin-top:20px;"><font class="orange14px">购买的商品</font></div>
	<c:if test="${not empty ordersList}">
		<table width="990" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px;">
			<tr>
              <td height="34" align="center" nowrap="nowrap" style="background:url(${ctx}/styles/customer/pshow/images/az.jpg) repeat-x;border-bottom:1px dotted #B2D1FF;"><strong>图片</strong></td>
              <td width="469" align="left" nowrap="nowrap" style="background:url(${ctx}/styles/customer/pshow/images/az.jpg) repeat-x;border-bottom:1px dotted #B2D1FF;"><strong>商品名称</strong></td>
              <td width="90" align="center" nowrap="nowrap" style="background:url(${ctx}/styles/customer/pshow/images/az.jpg) repeat-x;border-bottom:1px dotted #B2D1FF;"><strong>单价</strong></td>
              <td width="80" align="center" nowrap="nowrap" style="background:url(${ctx}/styles/customer/pshow/images/az.jpg) repeat-x;border-bottom:1px dotted #B2D1FF;"><strong>数量</strong></td>
              <td width="100" align="center" nowrap="nowrap" style="background:url(${ctx}/styles/customer/pshow/images/az.jpg) repeat-x;border-bottom:1px dotted #B2D1FF;"><strong>小计</strong></td>
            </tr>
		  	<c:forEach items="${ordersList}" var="cur">
				<c:forEach items="${cur.pshowOrdeDetailsList}" var="cur_x">
					<c:set value="${fn:split(cur_x.map.main_pic, ',')[0]}" var="main_pic_path" />
					<tr style="background:#FAFCFF;">
					    <td width="106" align="center" nowrap="nowrap" style="border-bottom:1px dotted #B2D1FF;"><img src="${ctx}/${fn:substringBefore(main_pic_path, '.')}_086.jpg" /></td>
          				<td height="81" align="left" nowrap="nowrap" style="border-bottom:1px dotted #B2D1FF;"><a href="${ctx}/customer/manager/PShow.do?method=OrderNow&id=${cur_x.pd_id}" target="_blank">${cur_x.pd_name}</a></td>
          				<td height="81" align="center" nowrap="nowrap" style="border-bottom:1px dotted #B2D1FF;"><font style="font-size:13px;font-family:verdana;"><fmt:formatNumber value="${cur_x.price}" type="currency" /></font></td>
          				<td height="81" align="center" nowrap="nowrap" style="border-bottom:1px dotted #B2D1FF;"><font style="font-size:13px;font-family:verdana;">${cur_x.num}</font></td>
          				<td height="81" align="center" nowrap="nowrap" style="border-bottom:1px dotted #B2D1FF;"><font class="orange" style="font-size:13px;font-family:verdana;"><fmt:formatNumber value="${cur_x.price * cur_x.num}" type="currency" /></font></td>
				    </tr>
				</c:forEach>
		  	</c:forEach>
		</table>
	</c:if>
	<table width="990" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:20px;">
	  <tr>
	    <td colspan="2" align="left" style="padding-left:10px; font-size:14px; font-weight:bold;border-bottom:1px #F60 solid;padding-bottom:5px;">结算信息</td>
	  </tr>
	  <tr>
	    <td width="250" height="46" align="left" bgcolor="#fef7e8" style=" padding-left:10px;"></td>
	    <td width="740" bgcolor="#fef7e8" nowrap="nowrap" style="font-weight:bold;font-size:14px;padding-right:10px;text-align:right;">应付总额：<font class="orange" style="font-size:22px;"><fmt:formatNumber value="${totalMoney}" type="currency" /></font>&nbsp;</td>
	  </tr>
	</table>
  <div class="clear"></div>
</div>
<!-- third end --> 

<!-- six start -->
<div class="maincont"> 
  <!-- footer start-->
  <jsp:include page="/customer/PShow/_inc/_footer2.jsp" flush="true" />
  <!-- footer end--> 
</div>
<!-- six end --> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
