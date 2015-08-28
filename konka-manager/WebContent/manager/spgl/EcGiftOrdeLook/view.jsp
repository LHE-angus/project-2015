<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单详细</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：${naviString}</div>
  <div class="rtabcont1">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="7">订单信息</th>
        </tr>
        <tr>
          <td class="title_item" width="15%">订单流水号：</td>
          <td colspan="4"><span><font color="red">${fn:escapeXml(af.map.trade_index)}</font></span></td>
          <td class="title_item" width="15%">下单人姓名：</td>
          <td colspan="1">${af.map.order_user_name}</td>
        </tr>
        <tr>
          <td class="title_item" width="15%">配送方式：</td>
          <td colspan="4"><c:if test="${af.map.deliver_way eq '0'}">上门自提</c:if>
            <c:if test="${af.map.deliver_way eq 1}">商家配送</c:if></td>
          <td class="title_item" width="15%">发货前电话确认：</td>
          <td colspan="1"><c:if test="${af.map.deliver_is_call eq '0'}">否</c:if>
            <c:if test="${af.map.deliver_is_call eq 1}">是</c:if></td>
        </tr>
        <tr>
          <td class="title_item" width="15%">送货日期：</td>
          <td colspan="4"><c:if test="${af.map.deliver_time eq '0'}">只工作日送货（双休日、假日不用送）</c:if>
            <c:if test="${af.map.deliver_way eq 1}">工作日、双休日与假日均可送货</c:if>
            <c:if test="${af.map.deliver_way eq 2}">只双休日、假日送货（工作日不用送）</c:if></td>
          <td class="title_item" width="15%">下单时间：</td>  
          <td><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>  
        </tr>
        <tr>
          <td class="title_item" width="15%">订单备注：</td><td colspan="6">${af.map.remark }</td>
        </tr> 
        <tr>
          <td colspan="7"><table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
              <tr>
                <th colspan="4">订单商品信息</th>
              </tr>
              <tr class="title_top">
                <td width="15%" nowrap="nowrap">商品编码</td>
                <td width="15%" nowrap="nowrap">商品名称</td>
                <td width="5%" nowrap="nowrap">数量</td>
                <td width="8%" nowrap="nowrap">所需积分</td>
              </tr>
              <tr>
                <td align="center">${fn:escapeXml(af.map.ecGift.pd_sn)}</td>
                <td align="center">${fn:escapeXml(af.map.ecGift.pd_name)}</td>
                <td align="center">1</td>
                <td align="center"><fmt:formatNumber value="${fn:escapeXml(af.map.integral)}" pattern="0" /></td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td class="title_item" width="15%">收货人固定电话：</td>
          <td>${fn:escapeXml(af.map.buyer_tel)}</td>
          <td class="title_item" width="15%">收货人手机：</td>
          <td colspan="4">${fn:escapeXml(af.map.buyer_mp)}</td>
        </tr>
        <tr>
          <td class="title_item" width="15%">购买人姓名：</td>
          <td>${fn:escapeXml(af.map.buyer_name)}</td>
          <td class="title_item" width="15%">收货人所属地区：</td>
          <td colspan="4">${p_index_name}</td>
        </tr>
        <tr>
        	<td class="title_item" width="15%">订单状态：</td>
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
        
        <tr>
          <td colspan="7" align="center">
            &nbsp;&nbsp;
            <input class="bgButtonBack" type="submit" name="order_back" value="返回" onclick="history.back();return false;" /></td>
        </tr>
      </table>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[

                                          
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
