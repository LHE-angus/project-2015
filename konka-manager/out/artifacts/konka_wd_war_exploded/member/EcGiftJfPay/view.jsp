<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳网上直销商城</c:if></title>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/member/css/global1.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/member/css/epp.css" /> 
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/shoppingcar.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/member/__inc/top.jsp" flush="true" />
<jsp:include page="/member/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/member/__inc/nav.jsp" flush="true" /> 
<!-- buy start -->
<form action="<c:url value='/member/ShoppingCar.do' />" method="post" id="step_two_form"> 
<div class="card_center">
  <div class="card_a">订单明细信息</div>
  <div class="shoppingcart_c"> 
    <h3 class="TitleName"><b>订单状态</b></h3>
  	<div class="HideBox">
      <div style="font-size:14px; padding:10px 0px;">
      	 <c:if test="${af.map.state eq 0}">等待支付</c:if>
         <c:if test="${af.map.state eq 1 }">处理失败</c:if>
         <c:if test="${af.map.state eq 2 }">已取消</c:if> 
      </div>
  	</div>
    <h3 class="TitleName"><b>商品清单</b></h3>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="mytable3">
      <tbody>
        <tr class="item"> 
          <td>商品名称</td>
          <td width="10%">商品数量</td>
          <td width="10%">金额</td> 
        </tr>
        <tr> 
	      <td style="border-bottom:solid 1px #E6E6E6;">积分充值</td>
	      <td style="border-bottom:solid 1px #E6E6E6;">${af.map.integral}</td>
	      <td style="border-bottom:solid 1px #E6E6E6;">￥<fmt:formatNumber value="${af.map.price}" pattern="#,#00" /></td>
	   </tr> 
      </tbody>
    </table>
    <h3 class="TitleName"><b>收货人信息</b><span><a style="cursor:pointer;" id="show_addr">[展开]</a><a style="cursor:pointer;display:none;" id="close_addr">[关闭]</a></span></h3>
    <div class="HideBox" id="addr_div" style="display:none;">
    	<div style="font-size:14px; padding:10px 0px;">${af.map.real_name}</div>
    </div> 
  <div class="clear"></div> 
</div>
</div>
</form>
<!-- buy end -->  
<!-- six footer -->
<jsp:include page="../__inc/footer.jsp" flush="true" />
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