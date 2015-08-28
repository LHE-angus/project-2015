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
<table width="100%" border="1" cellspacing="5" cellpadding="0">
        <tbody>
          <tr class="tabtt1">
                 <td nowrap="nowrap" align="center" width="3%">序号</td>
                  <td nowrap="nowrap" align="center" width="3%">分公司</td>
                  <td nowrap="nowrap" align="center" width="5%">销售时间</td>
                  <td nowrap="nowrap" align="center" width="5%">R3编码</td>
                  <td nowrap="nowrap" align="center" width="5%">卖方客户名称</td>
	              <td nowrap="nowrap" align="center" width="5%">卖方销售单号</td>
	              <td nowrap="nowrap" align="center" width="5%">买方网点名称</td>
	              <td nowrap="nowrap" align="center" width="5%">买方网点编码</td>
	              <td nowrap="nowrap" align="center" width="5%">买方联系人</td>
	              <td nowrap="nowrap" align="center" width="5%">网点业务员</td>
                  <td nowrap="nowrap" align="center" width="5%">商品类型</td>
                  <td nowrap="nowrap" align="center" width="5%">机型</td>
                  <td nowrap="nowrap" align="center" width="5%">尺寸</td>
                  <td nowrap="nowrap" align="center" width="5%">数量</td>
                  <td nowrap="nowrap" align="center" width="5%">销售单价</td>
                  <td nowrap="nowrap" align="center" width="5%">建议销售单价</td>
                  <td nowrap="nowrap" align="center" width="5%">销售金额</td>
                  <td nowrap="nowrap" align="center" width="5%">销售成本</td>
                  <td nowrap="nowrap" align="center" width="5%">销售毛利</td>
                  <td nowrap="nowrap" align="center" width="5%">折扣金额</td>
                  <td nowrap="nowrap" align="center" width="5%">出货仓</td>
                  <td nowrap="nowrap" align="center" width="5%">网点确认状态</td>
                  <td nowrap="nowrap" align="center" width="5%">财务确认状态</td>
        </tr>
        <c:forEach items="${entityList1}" var="cur" varStatus="vs">
        <tr>
           <td nowrap="nowrap" align="center">${vs.count}</td>
                    <td nowrap="nowrap" align="center">${cur.map.fgs_name}</td>
                    <td nowrap="nowrap"><fmt:formatDate value="${cur.map.opr_date}" pattern="yyyy-MM-dd" /></td>
                    <td nowrap="nowrap" align="left">${cur.map.sell_r3_code}</td>
                    <td nowrap="nowrap" align="left">${cur.map.sell_cust_name}</td>
                    <td nowrap="nowrap" align="left">${cur.map.bill_sn}</td>
                     <td nowrap="nowrap" align="left">${cur.map.partner_name }</td>
                    <td nowrap="nowrap" align="left">${cur.map.partner_sn }</td>
                    <td nowrap="nowrap" align="left">${cur.map.link_name}</td>
                    <td nowrap="nowrap" align="left">${cur.map.ywy_user_name}</td>
                    <td nowrap="nowrap" align="left">${cur.map.type_name }</td>
                    <td nowrap="nowrap" align="left">${cur.map.goods_name }</td>
                    <td nowrap="nowrap" align="right">${cur.map.md_size }</td>
                    <td nowrap="nowrap" align="right">${cur.num }</td>
                    <td nowrap="nowrap" align="right">${cur.price }</td>
                    <td nowrap="nowrap" align="right">${cur.map.sell_price }</td>
                    <td nowrap="nowrap" align="right">${cur.money }</td>
                    <td nowrap="nowrap" align="right">${cur.map.buy_price }</td>
                    <td nowrap="nowrap" align="right">${cur.money-cur.map.buy_price*cur.num }</td>
                    <td nowrap="nowrap" align="right">${cur.dis_money }</td>
                   <td nowrap="nowrap" align="right">${cur.map.chc_store_name}</td>
                    <td nowrap="nowrap" align="center">
                    <c:choose>
	              		<c:when test="${fn:escapeXml(cur.map.wd_status) eq 0}"><span style="color:red;">未确认</span></c:when>
	              		<c:when test="${fn:escapeXml(cur.map.wd_status) eq 1}"><span style="color:green;">已确认</span></c:when>
	              		<c:when test="${fn:escapeXml(cur.map.wd_status) eq 2}"><span style="color:green;">已退回</span></c:when>
              		</c:choose>
                    </td>
                    <td nowrap="nowrap" align="center">
                    <c:choose>
	              		<c:when test="${fn:escapeXml(cur.map.status) eq 0}"><span style="color:red;">未确认</span></c:when>
	              		<c:when test="${fn:escapeXml(cur.map.status) eq 1}"><span style="color:green;">已确认</span></c:when>
              		</c:choose>
                    </td>
        </tr>
        </c:forEach>
  </tbody>
</table>
</body>
</html>