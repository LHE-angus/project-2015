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
<!--
-->
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：订单管理 &gt; 订单记录 &gt; 订单详细</div>
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
          <td colspan="6"><c:if test="${af.map.deliver_way eq 0}">上门自提</c:if>
            <c:if test="${af.map.deliver_way eq 1}">商家配送</c:if></td>
        </tr>
        <tr>
          <td class="title_item" width="15%">货款支付方式：</td>
          <td colspan="6"><c:if test="${af.map.pay_way eq 0}">货到付款</c:if>
              <c:if test="${af.map.pay_way eq 1}">银行汇款</c:if>
              <c:if test="${af.map.pay_way eq 2}">支付宝</c:if>
              <c:if test="${af.map.pay_way eq 3}">银联</c:if>
              <c:if test="${af.map.pay_way eq 4}">财付通</c:if></td>
        </tr>
        <tr>
          <td colspan="7"><table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
              <tr>
                <th colspan="7">订单产品信息</th>
              </tr>
              <tr class="title_top">
                <td width="15%" nowrap="nowrap">产品型号</td>
                <td width="5%" nowrap="nowrap">数量</td>
                <td width="8%" nowrap="nowrap">单价</td>
                <td width="8%" nowrap="nowrap">金额</td>
                <td width="8%" nowrap="nowrap">增值服务</td>
                <td width="8%" nowrap="nowrap">佣金</td>
                <td width="8%" nowrap="nowrap">卖家留言</td>
              </tr>
              <c:forEach items="${pshowOrdeDetails}" var="cur">
                <tr>
                  <td align="center">${fn:escapeXml(cur.map.pd_sn)}</td>
                  <td align="center">${fn:escapeXml(cur.num)}</td>
                  <td align="center"><fmt:formatNumber value="${fn:escapeXml(cur.price)}" pattern="0.00" /></td>
                  <td align="center"><fmt:formatNumber value="${fn:escapeXml(cur.total_price)}" pattern="0.00" /></td>
                  <td align="center">
						 <c:forEach items="${bddetailsList}" var="cu">
						 	<c:if test="${cu.details_id eq cur.bill_item_id}">
						 		${cu.goods_name} ￥${cu.price}<br />
						 	</c:if>
						 </c:forEach>
				  </td>
				  <td align="center"><fmt:formatNumber value="${fn:escapeXml(cur.rebates)}" pattern="0.00" /></td>
                  <td align="center">${fn:escapeXml(cur.remark)}</td>
                </tr>
              </c:forEach>
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
          <td class="title_item" width="15%">订单产品数量：</td>
          <td >${t_num }</td>
          <td class="title_item" width="15%">订单金额：</td>
          <td colspan="4"><fmt:formatNumber value="${t_price}" pattern="0.00" />
            (元)</td>
        </tr>
        <tr>
        	<td class="title_item" width="15%">订单状态：</td>
          	<td ><c:if test="${af.map.order_state eq -1}">已取消</c:if>
             <c:if test="${af.map.state eq -30 }">已退货</c:if>
	         <c:if test="${af.map.state eq -20 }">审核未通过</c:if>
	         <c:if test="${af.map.state eq -10 }">已关闭</c:if>
	         <c:if test="${af.map.state eq 0 }">已预订</c:if>
	         <c:if test="${af.map.state eq 10 }">已付款</c:if>
	         <c:if test="${af.map.state eq 20 }">审核通过</c:if>
	         <c:if test="${af.map.state eq 30 }">下发处理</c:if>
	         <c:if test="${af.map.state eq 40 }">商家发货</c:if>
	         <c:if test="${af.map.state eq 50 }">客户已换货</c:if>
	         <c:if test="${af.map.state eq 60 }">确认收货</c:if></td>
	         <td class="title_item" width="15%">收货地址：</td>
             <td colspan="4">${fn:escapeXml(af.map.buyer_addr)}</td>
        </tr>
          <tr>
            <th colspan="7">订单审核信息</th>
          </tr>
          <tr>
            <td colspan="7"><table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
                <tr>
                  <td class="title_item" width="5%" style="text-align:center;">序号</td>
                  <td class="title_item" width="18%" style="text-align:center;">操作人姓名</td>
                  <td class="title_item" width="18%" style="text-align:center;">操作时间</td>
                  <td class="title_item" width="18%" style="text-align:center;">订单总金额</td>
                  <td class="title_item" width="18%" style="text-align:center;">审核结果</td>
                  <td class="title_item" style="text-align:center;">意见</td>
                </tr>
                <c:forEach items="${PshowOrdeAudits}" var="cur" varStatus="st">
                  <tr>
                    <td align="center" >${st.count}</td>
                    <td align="center">${cur.opr_user_real_name } </td>
                    <td align="center"><fmt:formatDate value="${cur.oper_date }" pattern="yyyy-MM-dd"/></td>
                    <td align="center">${cur.total_price}</td>
                    <td align="center"><c:if test="${cur.state eq -1}">已取消</c:if>
			            <c:if test="${cur.state eq -30 }">已退货</c:if>
				         <c:if test="${cur.state eq -20 }">审核未通过</c:if>
				         <c:if test="${cur.state eq -10 }">已关闭</c:if>
				         <c:if test="${cur.state eq 0 }">已预订</c:if>
				         <c:if test="${cur.state eq 10 }">已付款</c:if>
				         <c:if test="${cur.state eq 20 }">审核通过</c:if>
				         <c:if test="${cur.state eq 30 }">下发处理</c:if>
				         <c:if test="${cur.state eq 40 }">商家发货</c:if>
				         <c:if test="${cur.state eq 50 }">客户已换货</c:if>
				         <c:if test="${cur.state eq 60 }">确认收货</c:if></td>
                    <td align="left">${cur.remark}</td>
                  </tr>
                </c:forEach>
              </table></td>
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
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
