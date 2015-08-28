<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳网上直销商城</c:if></title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/touch/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/touch/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/touch/css/touch.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/touch/__inc/top.jsp" flush="true" />
<jsp:include page="/touch/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/touch/__inc/nav.jsp" flush="true" />
<!-- second start -->
<div class="maincont">
  <jsp:include page="/touch/__inc/_mleft.jsp" flush="true" />
  <!--right-->
  <div class="touch_right padbot45">
    <div class="position"><a href="${ctx }/touch/Index.do">首页</a> &gt; <a href="${ctx }/touch/center/Index.do">会员中心</a> &gt; <a href="${ctx }/touch/center/EcGiftOrde.do">积分换礼品 </a> </div>
    <div class="touchtab3">
      <p style="margin-top:15px;font-size:16px;"> 订单信息</p>
       <table width="100%" border="0" cellpadding="0" cellspacing="0" class="touch_form_table1">
         <tr>
          <td width="15%">订单流水号：</td>
          <td><span><font color="red">${fn:escapeXml(af.map.trade_index)}</font></span></td>
        </tr>
        <tr>
          <td width="15%">配送方式：</td>
          <td><c:if test="${af.map.deliver_way eq '0'}">上门自提</c:if>
            <c:if test="${af.map.deliver_way eq 1}">商家配送</c:if></td>
        </tr>    
        <tr>  
          <td width="15%">发货前电话确认：</td>
          <td><c:if test="${af.map.deliver_is_call eq '0'}">否</c:if>
            <c:if test="${af.map.deliver_is_call eq 1}">是</c:if></td>
        </tr>
        <tr>
          <td width="15%">送货日期：</td>
          <td><c:if test="${af.map.deliver_time eq '0'}">只工作日送货（双休日、假日不用送）</c:if>
            <c:if test="${af.map.deliver_way eq 1}">工作日、双休日与假日均可送货</c:if>
            <c:if test="${af.map.deliver_way eq 2}">只双休日、假日送货（工作日不用送）</c:if></td>
        </tr>
      </table>     
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="touch_form_table0" style="border:1px solid #e3e3e3;" >
       		  <tr >
          		<td align="left" colspan="4" style="font-weight:bold;border-bottom:1px solid #91beff;">订单商品信息</th>
              </tr>
              <tr style="background:#fff9e9;border:1px solid #e3e3e3;border-bottom:1px solid #ffbe91;">
                <td align="center" width="15%" nowrap="nowrap">商品编码</td>
                <td align="center" width="15%" nowrap="nowrap">商品名称</td>
                <td align="center" width="5%" nowrap="nowrap">数量</td>
                <td align="center" width="8%" nowrap="nowrap">所需积分</td>
              </tr>
              <tr style="border:1px solid #e3e3e3;">
                <td align="center">${fn:escapeXml(af.map.ecGift.pd_sn)}</td>
                <td align="center">${fn:escapeXml(af.map.ecGift.pd_name)}</td>
                <td align="center">1</td>
                <td align="center"><fmt:formatNumber value="${fn:escapeXml(af.map.integral)}" pattern="0" /></td>
              </tr>
      </table>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="touch_form_table1">        
         <tr>
          <td width="15%">收货人固定电话：</td>
          <td>${fn:escapeXml(af.map.buyer_tel)}</td>
          <td   width="15%">收货人手机：</td>
          <td colspan="4">${fn:escapeXml(af.map.buyer_mp)}</td>
        </tr>
        <tr>
          <td width="15%">购买人姓名：</td>
          <td>${fn:escapeXml(af.map.buyer_name)}</td>
          <td width="15%">收货人所属地区：</td>
          <td colspan="4">${p_index_name}</td>
        </tr>
        <tr>
        	<td width="15%">订单状态：</td>
          	<td ><c:if test="${af.map.order_state eq -1}">已取消</c:if>
             <c:if test="${af.map.state eq -30 }">已退货</c:if>
	         <c:if test="${af.map.state eq -20 }">审核未通过</c:if>
	         <c:if test="${af.map.state eq -10 }">已关闭</c:if>
	         <c:if test="${af.map.state eq 0 }">已预订</c:if>
	         <c:if test="${af.map.state eq 10 }">已兑换</c:if>
	         <c:if test="${af.map.state eq 20 }">审核通过</c:if>
	         <c:if test="${af.map.state eq 30 }">下发处理</c:if>
	         <c:if test="${af.map.state eq 40 }">商家发货</c:if>
	         <c:if test="${af.map.state eq 50 }">客户已换货</c:if>
	         <c:if test="${af.map.state eq 60 }">确认收货</c:if></td>
	         <td class="title_item" width="15%">收货地址：</td>
             <td colspan="4">${fn:escapeXml(af.map.buyer_addr)}</td>
        </tr>
      </table>
      <div style="margin:10px 300px 20px 0;float:right;">
        <input class="inputbtn" type="button" name="order_back" value="返回" onclick="history.back();" />
      </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="/touch/__inc/footer.jsp" flush="true" />
<!-- six footer -->
</body>
</html>
