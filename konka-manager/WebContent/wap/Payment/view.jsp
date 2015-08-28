<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" /> 
<title>开心猫</title> 
<link rel="stylesheet" type="text/css" href="${ctx}/styles/wap/css/global.css" />  
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>  
</head>
<body>  
<form action="<c:url value='/wap/ShoppingCar.do' />" method="post" id="step_two_form">
<input type="hidden" name="method" value="createOrder" />
<input type="hidden" name="p_index" value="${p_index}" />
<input type="hidden" name="buy_json_object" value="${buy_json_object}" /> 
<div style="width:100%;background-color:#ffffff;">
   <div style="padding: 10px;line-height:26px;font-size:18px;font-family: Microsoft YaHei ;">订单明细信息 </div> 
    <c:forEach items="${pshowOrderList}" var="cur1"> 
    <div style="margin-left:3px;padding: 3px;border: solid 1px #E6E6E6;">
     <b>订单号 </b>${cur1.trade_index }<br/>
     <b>订单状态 </b><c:if test="${cur1.state eq -30 }">退货成功</c:if><c:if test="${cur1.state eq -20 }">处理失败</c:if><c:if test="${cur1.state eq -10 }">已取消</c:if><c:if test="${cur1.state eq 0 }"><c:if test="${cur1.pay_way eq 0 or cur1.pay_way eq 1 }"> 待审核  </c:if> <c:if test="${cur1.pay_way ne 9 and cur1.pay_way ne 0 and cur1.pay_way ne 1}">待付款  </c:if></c:if><c:if test="${cur1.state eq 5 }">待确认</c:if><c:if test="${cur1.state eq 10 }">已确认待处理</c:if><c:if test="${cur1.state eq 20 }">订单处理中</c:if> <c:if test="${cur1.state eq 30 }">订单处理中</c:if><c:if test="${cur1.state eq 40 }">已发货</c:if><c:if test="${cur1.state eq 50 }">已换货</c:if><c:if test="${cur1.state eq 60 }">交易完成</c:if> 
  	</div>   	
    <div style="padding: 10px;line-height:26px;font-size:16px;font-family: Microsoft YaHei ;">商品清单</div>
  	<div style="margin-left:3px;padding: 3px;border: solid 1px #E6E6E6;"> 
    <table style="width:100%"> 
        <tr><td width="15%" height="30">商品编号</td><td>商品名称</td><td width="10%">数量</td><td width="10%">单价</td> <td width="30%">增值服务</td></tr>
        <c:forEach items="${cur1.pshowOrdeDetailsList}" var="cur2">
	    <tr>
	          <td style="border-bottom:solid 1px #E6E6E6;">${cur2.pd_id}</td><td style="border-bottom:solid 1px #E6E6E6;"><a href="<c:url value='/member/PdShow.do?goods_id=${cur2.pd_id}' />" target="_blank">${cur2.pd_name}</a></td>
	          <td style="border-bottom:solid 1px #E6E6E6;">${cur2.num}</td><td style="border-bottom:solid 1px #E6E6E6;">￥<fmt:formatNumber value="${cur2.price}" pattern="#,#00" /></td>
	          <td valign="middle" style="text-align:left;border-bottom:solid 1px #E6E6E6;">
	          	 <c:if test="${empty cur2.ecBindingPdOrdeDetailsList}">没有选择增值服务</c:if>
	          	 <c:forEach items="${cur2.ecBindingPdOrdeDetailsList}" var="cur3">${cur3.goods_name} ￥<fmt:formatNumber value="${cur3.price}" pattern="#,#00" /><br /></c:forEach>
	          </td>
	    </tr>
        </c:forEach> 
    </table>
    </div>
    <div style="padding: 10px;line-height:26px;font-size:16px;font-family: Microsoft YaHei ;">收货人信息 <a style="cursor:pointer;" id="show_addr">[展开]</a><a style="cursor:pointer;display:none;" id="close_addr">[关闭]</a></div>
    <div id="addr_div" style="margin-left:3px;padding: 6px;border: solid 1px #E6E6E6;display:none;">
    	<div style="font-size:14px; padding:10px 0px;">${cur1.map.p_full_name}${cur1.buyer_addr}(${cur1.buyer_name}&nbsp;)&nbsp;<em>${cur1.buyer_mp}<c:if test="${not empty cur1.buyer_tel}">/${cur1.buyer_tel}</c:if></em></div>
    </div>
    </c:forEach>
  <div class="clear"></div> 
</div> 
</form>  
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