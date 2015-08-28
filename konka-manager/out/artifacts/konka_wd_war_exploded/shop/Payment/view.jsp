<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳粉丝会</c:if></title>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/shop/css/global1.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/shop/css/epp.css" /> 
<link rel=stylesheet type=text/css  href="${ctx}/styles/shop/css/shoppingcar.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/shop/__inc/top.jsp" flush="true" />
<jsp:include page="/shop/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/shop/__inc/nav.jsp" flush="true" />

<!-- buy start -->
<form action="<c:url value='/shop/ShoppingCar.do' />" method="post" id="step_two_form">
<input type="hidden" name="method" value="createOrder" />
<input type="hidden" name="p_index" value="${p_index}" />
<input type="hidden" name="buy_json_object" value="${buy_json_object}" />
<div class="card_center">
  <div class="card_a">订单明细信息</div>
  <div class="shoppingcart_c">
    <c:forEach items="${pshowOrderList}" var="cur1">
    <h3 class="TitleName"><b>订单状态</b></h3>
  	<div class="HideBox">
      <div style="font-size:14px; padding:10px 0px;">
      	 <c:if test="${cur1.state eq -30 }">退货成功</c:if>
         <c:if test="${cur1.state eq -20 }">处理失败</c:if>
         <c:if test="${cur1.state eq -10 }">已取消</c:if>
         <c:if test="${cur1.state eq 0 }"><c:if test="${cur1.pay_way eq 0 or cur1.pay_way eq 1}">待审核  </c:if> <c:if test="${cur1.pay_way ne 0 and cur1.pay_way ne 1}">待付款  </c:if></c:if>
         <c:if test="${cur1.state eq 5 }">待确认</c:if>
         <c:if test="${cur1.state eq 10 }">已确认待处理</c:if>
         <c:if test="${cur1.state eq 20 }">订单处理中</c:if>
         <c:if test="${cur1.state eq 30 }">订单处理中</c:if>
         <c:if test="${cur1.state eq 40 }">已发货</c:if>
         <c:if test="${cur1.state eq 50 }">已换货</c:if>
         <c:if test="${cur1.state eq 60 }">交易完成</c:if>
      </div>
  	</div>
    <h3 class="TitleName"><b>商品清单</b></h3>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="mytable3">
      <tbody>
        <tr class="item">
          <td width="15%" height="30">商品编号</td>
          <td>商品名称</td>
          <td width="10%">商品数量</td>
          <td width="10%">会员价</td>
          <td width="30%">增值服务</td>
        </tr>
        <c:forEach items="${cur1.pshowOrdeDetailsList}" var="cur2">
	        <tr>
	          <td style="border-bottom:solid 1px #E6E6E6;">${cur2.pd_id}</td>
	          <td style="border-bottom:solid 1px #E6E6E6;"><a href="<c:url value='/shop/PdShow.do?goods_id=${cur2.pd_id}' />" target="_blank">${cur2.pd_name}</a></td>
	          <td style="border-bottom:solid 1px #E6E6E6;">${cur2.num}</td>
	          <td style="border-bottom:solid 1px #E6E6E6;">￥<fmt:formatNumber value="${cur2.price}" pattern="#,#00" /></td>
	          <td valign="middle" style="text-align:left;border-bottom:solid 1px #E6E6E6;">
	          	 <c:if test="${empty cur2.ecBindingPdOrdeDetailsList}">没有选择增值服务</c:if>
	          	 <c:forEach items="${cur2.ecBindingPdOrdeDetailsList}" var="cur3">
	          	 	${cur3.goods_name} ￥<fmt:formatNumber value="${cur3.price}" pattern="#,#00" /><br />
	          	 </c:forEach>
	          </td>
	        </tr>
        </c:forEach>
      </tbody>
    </table>
    <h3 class="TitleName"><b>收货人信息</b><span><a style="cursor:pointer;" id="show_addr">[展开]</a><a style="cursor:pointer;display:none;" id="close_addr">[关闭]</a></span></h3>
    <div class="HideBox" id="addr_div" style="display:none;">
    	<div style="font-size:14px; padding:10px 0px;">${cur1.map.p_full_name}${cur1.buyer_addr}(${cur1.buyer_name}&nbsp;收)&nbsp;<em>${cur1.buyer_mp}<c:if test="${not empty cur1.buyer_tel}">/${cur1.buyer_tel}</c:if></em></div>
    </div>
    </c:forEach>
  <div class="clear"></div>
  <div class="but_ca">
    <ul>
      <li><a href="<c:url value='/shop/Index.do' />" id="step_one_go_shopping">继续购物</a></li>
    </ul>
  </div>
</div>
</div>
</form>
<!-- buy end --> 

<!-- six footer -->
<jsp:include page="../__inc/footer.jsp" flush="true" />
<!-- six footer --> 

<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	$(document).delegate("#show_addr", "click", function(){
		$("#addr_div").show();
		$("#close_addr").show();
		$("#show_addr").hide();
	});
	$(document).delegate("#close_addr", "click", function(){
		$("#addr_div").hide();
		$("#close_addr").hide();
		$("#show_addr").show();
	});
});
//]]></script>
</body>
</html>