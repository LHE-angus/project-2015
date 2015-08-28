<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
      <tr>
        <td colspan="2" style="font-weight:bold;font-size:18px;height:40px;text-align:center;border-bottom:none;">汇款详细信息</td>
      </tr>
      <tr>
        <td colspan="2"><table width="100%" border="0" cellspacing="0" cellpadding="1" class="rtable2">
            <tr class="tabtt1" style="background-image:url('${ctx}/images/manager/tabtitbg1.gif') repeat-x;background:#F0F0F0;">
              <td align="center" nowrap="nowrap"><font class="blue">订单流水号</font></td>
              <td width="10%" align="center" nowrap="nowrap"><font class="blue">付款方式</font></td>
              <td width="10%" align="center" nowrap="nowrap"><font class="blue">账单总金额</font></td>
              <td width="10%" align="center" nowrap="nowrap"><font class="blue">销售单状态</font></td>
              <td width="16%" align="center" nowrap="nowrap"><font class="blue">开单时间</font></td>
              <td width="16%" align="center" nowrap="nowrap"><font class="blue">消费者确认收货时间</font></td>
            </tr>
            <c:forEach var="cur" items="${konkaXxSellBillList}" varStatus="vs">
              <tr>
                <td align="left" nowrap="nowrap"><a href="${ctx}/manager/zmd/KonkaXxZmdAddSalesOrder.do?method=view&sell_bill_id=${cur.sell_bill_id}&mod_id=802001"><font class="fblue">
                  <c:out value="${fnx:leftPad_sis(cur.sell_bill_id, 12, '0')}" />
                  </font></a></td>
                <td align="center" nowrap="nowrap"><c:choose>
                    <c:when test="${cur.pay_way eq 1}">现金全额支付</c:when>
                    <c:when test="${cur.pay_way eq 2}">POS机支付</c:when>
                    <c:when test="${cur.pay_way eq 3}">货到付款</c:when>
                  </c:choose></td>
                <td align="right" nowrap="nowrap"><span class="kz-price-12">
                  <fmt:formatNumber value="${cur.total_money}" type="currency" />
                  </span></td>
                <td align="center" nowrap="nowrap"><font class="blue12px">
                  <c:choose>
                    <c:when test="${cur.sell_state eq 0}">未付款</c:when>
                    <c:when test="${cur.sell_state eq 10}">已付款未审核</c:when>
                    <c:when test="${cur.sell_state eq 20}">已审核通过</c:when>
                    <c:when test="${cur.sell_state eq 21}"><span style="color:#FF0000">已审核不通过</span></c:when>
                    <c:when test="${cur.sell_state eq 30}">已发货</c:when>
                    <c:when test="${cur.sell_state eq 40}">确认消费者收货</c:when>
                    <c:when test="${cur.sell_state eq 70}">交易成功</c:when>
                  </c:choose>
                  </font></td>
                <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.take_googs_date}" pattern="yyyy-MM-dd HH:mm:ss" />
                  <c:if test="${empty cur.take_googs_date}"><span style="color:#999;">未填写</span></</c:if></td>
              </tr>
            </c:forEach>
          </table></td>
      </tr>
      <tr>
        <td width="15%" nowrap="nowrap" class="title_item" align="right">Pos机应收款：</td>
        <td width="85%"><span class="kz-price">
          <fmt:formatNumber value="${af.map.money}" type="currency" />
          </span></td>
      </tr>
      <tr>
        <td width="15%" nowrap="nowrap" class="title_item" align="right">账单结算人：</td>
        <td width="85%">${af.map.man}
          <c:if test="${empty af.map.man}"><span style="color:gray;">未填写</span></c:if></td>
      </tr>
      <tr>
        <td nowrap="nowrap" height="28" class="title_item" align="right">账单日期：</td>
        <td><fmt:formatDate value="${af.map.out_date}" pattern="yyyy-MM-dd"/></td>
        <c:if test="${empty af.map.out_date}"><span style="color:gray;">未填写</span></c:if>
      </tr>
      <tr>
        <td width="15%" nowrap="nowrap" class="title_item" align="right">结算状态：</td>
        <td width="85%"><c:if test="${af.map.state eq 1}"><span style="color:red">未结算</span></c:if>
          <c:if test="${af.map.state eq 2}"><span style="color:green">已结算</span></c:if></td>
      </tr>
      <tr>
        <td width="15%" nowrap="nowrap" class="title_item" align="right">备注：</td>
        <td width="85%">${af.map.memo}
          <c:if test="${empty af.map.memo}"><span style="color:gray;">未填写</span></c:if></td>
      </tr>
      <tr>
        <td>&nbsp;&nbsp;</td>
        <td><input class="but5" type="button" name="Submit5" value="返回" onclick="history.back()" /></td>
      </tr>
    </table>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
