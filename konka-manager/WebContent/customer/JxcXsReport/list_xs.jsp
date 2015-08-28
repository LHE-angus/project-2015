<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
select {
	font-family:Microsoft YAHEI;
	font-size:12px;
}
input {
	font-family:Microsoft YAHEI;
	font-size:12px;
}
.bill-list {
	border-top: 2px solid #aaa;
	border-bottom: 2px solid #aaa;
	margin-bottom:1em;
}
.bill-list td, .bill-list th {
	padding:3px;
}
.bill-list th {
	background-color: #eee;
	border-bottom: 1px solid #aaa;
	line-height:25px;
	font-weight:800;
}
.bill-bottom {
	line-height:25px;
}
.bill-bottom td {
	border-top: 1px solid #aaa;
}
.bill-list-inner {
	border:0px;
	border-collapse:collapse;
	background-color:#ccc;
}
.bill-list-inner th {
	font-weight:normal;
	background-color:#eee;
	padding:0px 5px;
}
.bill-list-inner td {
	background-color:#fff;
	padding:2px 5px;
}
</style>
<title>${naviString}</title>
<base target="_self" />
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <input name="input" type="button" id="gotoAdd" class="bgButtonBack" value="返 回" onclick="history.back();" />
  </div>
  <div class="rtabcont1">
    <c:forEach items="${entityList}" var="cur" varStatus="vs">
      <div class="bill-list">
        <table width="100%" border="0" cellpadding="5" cellspacing="0">
          <tr>
            <th width="50%"><c:if test="${cur.bill_type eq 10}">采购单号</c:if>
            	<c:if test="${cur.bill_type eq 11}">退货单号</c:if>
            	<c:if test="${cur.bill_type eq 20}">销售单号</c:if>
            	<c:if test="${cur.bill_type eq 21}">销售退货单号</c:if>：${cur.bill_sn}</th>
            <th width="50%" style="text-align:right;">往来单位：${cur.map.partner_name}</th>
          </tr>
          <tr>
            <td colspan="2" style="padding:0px 0px 0px 30px;"><table width="100%" border="0" cellpadding="5" cellspacing="1" class="bill-list-inner">
                <tr>
                  <th width="5%" style="text-align:center;">序号</th>
                  <th>商品明细</th>
                  <th width="10%" style="text-align:right;">数量</th>
                  <th width="10%" style="text-align:right;">单位</th>
                  <th width="10%" style="text-align:right;"><c:if test="${cur.bill_type eq 10}">采购价</c:if>
                  	<c:if test="${cur.bill_type eq 11}">退货价</c:if>
                  	<c:if test="${cur.bill_type eq 20}">销售价</c:if>
                  	<c:if test="${cur.bill_type eq 21}">退货价</c:if></th>
                  <th width="10%" style="text-align:right;"><c:if test="${cur.bill_type eq 10}">采购金额</c:if>
                  	<c:if test="${cur.bill_type eq 11}">退货金额</c:if>
                  	<c:if test="${cur.bill_type eq 20}">销售金额</c:if>
                  	<c:if test="${cur.bill_type eq 21}">退货金额</c:if>${goods_money_title}</th>
                </tr>
                <c:forEach items="${cur.jBillDetailsList}" var="cur1" varStatus="vs1">
                  <tr>
                    <td align="center">${vs1.count}</td>
                    <td>${cur1.map.goods_name}</td>
                    <td align="right"><fmt:formatNumber value="${cur1.num}" pattern="0" /></td>
                    <td align="right">${cur1.map.unit}</td>
                    <td align="right"><fmt:formatNumber value="${cur1.price}" pattern="0.00" />
                      元</td>
                    <td align="right"><fmt:formatNumber value="${cur1.money}" pattern="0.00" />
                      元</td>
                  </tr>
                </c:forEach>
              </table></td>
          </tr>
          <tr class="bill-bottom">
            <td><c:if test="${cur.bill_type eq 10}">应付金额</c:if>
            	<c:if test="${cur.bill_type eq 11}">应退金额</c:if>
            	<c:if test="${cur.bill_type eq 20}">应收金额</c:if>
            	<c:if test="${cur.bill_type eq 21}">应退金额</c:if>：
              <fmt:formatNumber value="${cur.rec_money}" pattern="0.00元" />
              ，
              <c:if test="${cur.bill_type eq 10}">实付金额</c:if>
              <c:if test="${cur.bill_type eq 11}">实退金额</c:if>
              <c:if test="${cur.bill_type eq 20}">实收金额</c:if>
              <c:if test="${cur.bill_type eq 21}">实退金额</c:if>：
              <fmt:formatNumber value="${cur.money}" pattern="0.00元" /></td>
            <td align="right">&nbsp;</td>
          </tr>
        </table>
      </div>
    </c:forEach>
    
  </div>
</div>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>