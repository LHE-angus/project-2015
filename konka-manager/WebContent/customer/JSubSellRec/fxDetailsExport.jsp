<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
</head>
<body>
	<table width="100%" border="1" cellpadding="0" cellspacing="1">
    	<tr class="tabtt1">
        	<td nowrap="nowrap" align="center" width="3%">序号</td>
            <td nowrap="nowrap" align="center" width="5%">创建时间</td>
            <td nowrap="nowrap" align="center" width="5%">分销单号</td>
         	<td nowrap="nowrap" align="center" width="5%">单据类型</td>
         	<td nowrap="nowrap" align="center" width="5%">网点名称</td>
         	<td nowrap="nowrap" align="center" width="5%">网点编码</td>
            <td nowrap="nowrap" align="center" width="5%">联系人</td>
            <td nowrap="nowrap" align="center" width="5%">商品类型</td>
            <td nowrap="nowrap" align="center" width="5%">机型</td>
            <td nowrap="nowrap" align="center" width="5%">数量</td>
            <td nowrap="nowrap" align="center" width="5%">销售单价</td>
            <td nowrap="nowrap" align="center" width="5%">建议销售单价</td>
            <td nowrap="nowrap" align="center" width="5%">总金额（元）</td>
            <td nowrap="nowrap" align="center" width="5%">折让金额（元）</td>
            <td nowrap="nowrap" align="center" width="5%">折后金额（元）</td>
            <td nowrap="nowrap" align="center" width="5%">销售成本</td>
            <td nowrap="nowrap" align="center" width="5%">销售毛利</td>
            <td nowrap="nowrap" align="center" width="5%">出货仓库</td>
            <td nowrap="nowrap" align="center" width="5%">财务确认</td>
            <td nowrap="nowrap" align="center" width="5%">关联销售单号</td>
            <td nowrap="nowrap" align="center" width="5%">网点确认</td>
    	</tr>
        <c:forEach items="${entityList1}" var="cur" varStatus="vs">
        <tr>
        	<td nowrap="nowrap" align="center">${vs.count}</td>
            <td nowrap="nowrap" align="center"><fmt:formatDate value="${cur.map.opr_date}" pattern="yyyy-MM-dd" /></td>
            <td nowrap="nowrap" align="center">${cur.map.bill_sn}</td>
            <td nowrap="nowrap" align="left">
            	<c:if test="${cur.map.bill_type eq 40 }">分销</c:if>
            	<c:if test="${cur.map.bill_type eq 42 }">分销退货</c:if>
            </td>
            <td nowrap="nowrap" align="left">${cur.map.partner_name }</td>
            <td nowrap="nowrap" align="left">${cur.map.partner_id }</td>
            <td nowrap="nowrap" align="center">${cur.map.link_name}</td>
            <td nowrap="nowrap" align="center">${cur.map.type_name }</td>
            <td nowrap="nowrap" align="center">${cur.map.goods_name }</td>
            <td nowrap="nowrap" align="right">${cur.num }</td>
            <td nowrap="nowrap" align="right">${cur.price }</td>
            <td nowrap="nowrap" align="right">${cur.map.sell_price }</td>
            <td nowrap="nowrap" align="right">${cur.money }</td>
            <td nowrap="nowrap" align="right">${cur.dis_money }</td>
            <td nowrap="nowrap" align="right">${cur.money-cur.dis_money }</td>
            <td nowrap="nowrap" align="right">${cur.map.buy_price }</td>
            <td nowrap="nowrap" align="right">${cur.money-cur.map.buy_price*cur.num }</td>
            <td nowrap="nowrap" align="center">${cur.map.store_name }</td>
            <td nowrap="nowrap" align="center">
            	<c:if test="${empty cur.map.money}">待确认</c:if>
            	<c:if test="${not empty cur.map.money}">已确认</c:if>
            </td>
            <td nowrap="nowrap" align="center">${cur.map.r_bill_sn}</td>
            <td nowrap="nowrap" align="center">
            	<c:choose>
       				<c:when test="${fn:escapeXml(cur.map.status) eq 1}">已确认</c:when>
       				<c:when test="${fn:escapeXml(cur.map.status) eq 2}">已退回</c:when>
       				<c:otherwise>待确认</c:otherwise>
      			</c:choose>
            </td>
       	</tr>
        </c:forEach>
  	</table>
</body>
</html>