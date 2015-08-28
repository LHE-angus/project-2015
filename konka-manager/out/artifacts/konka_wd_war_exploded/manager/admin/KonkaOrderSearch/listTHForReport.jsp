<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td width="8%" nowrap="nowrap" align="center">下单日期</td>
        <td width="10%" nowrap="nowrap" align="center">流水号</td>
        <td width="10%" nowrap="nowrap" align="center">退货类型</td>
        <td width="8%" nowrap="nowrap" align="center">R3客户编码</td>
        <td nowrap="nowrap" align="center">客户名称</td>
        <td nowrap="nowrap" align="center">分公司</td>
        <td nowrap="nowrap" align="center">经办</td>
        <td width="6%" nowrap="nowrap" align="center">数量</td>
        <td width="6%" nowrap="nowrap" align="center">金额￥</td>
        <td width="6%" nowrap="nowrap" align="center">折扣￥</td>
        <c:if test="${af.map.dept_type eq 1}">
          <!-- 系统管理员 -->
          <td width="6%" nowrap="nowrap" align="center">审核状态</td>
          <td width="6%" nowrap="nowrap" align="center">待审核角色</td>
        </c:if>
        <c:if test="${af.map.dept_type eq 2}"> 
          <!-- 非系统管理员 -->
          <td width="6%" nowrap="nowrap" align="center">审核状态</td>
        </c:if>
        <td width="6%" nowrap="nowrap" align="center">客户确认</td>
        <td width="6%" nowrap="nowrap" align="center">R3单号</td>
      </tr>
      
      <c:forEach var="cur" items="${allList}" varStatus="vs">
	        <tr>
	          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
	          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.order_date}" pattern="yyyy-MM-dd"/></td>
	          <td align="center" nowrap="nowrap">${cur.trade_index}</td>
	          <td align="center" nowrap="nowrap">
		          <c:choose>
	                <c:when test="${cur.return_type eq '1'}">滞销退货</c:when>
	                <c:when test="${cur.return_type eq '2'}">残次品退货</c:when>
	                <c:when test="${cur.return_type eq '3'}">当月拒收</c:when>
	                <c:when test="${cur.return_type eq '6'}">跨月拒收</c:when>
	                <c:when test="${cur.return_type eq '4'}">异型换机</c:when>
	                <c:when test="${cur.return_type eq '5'}">其他原因</c:when>
	                <c:otherwise>未能确定类型</c:otherwise>
              </c:choose> 
	          </td>
	          <td align="center" nowrap="nowrap">${cur.ag}</td>
	          <td nowrap="nowrap">${cur.user_shop_name}</td>
	          
	          <td align="right" nowrap="nowrap">${cur.map.fgsName}</td>
	          <td align="right" nowrap="nowrap">${cur.map.jbName}</td>
	          
	          <td align="right" nowrap="nowrap">${cur.order_num}</td>
	          <td align="right" nowrap="nowrap">
	          	<span class="kz-price-12">
	            <fmt:formatNumber value="${cur.money}" type="currency" />
	            </span>
	          </td>
	          <td align="right" nowrap="nowrap"><span class="kz-price-12">
	            <fmt:formatNumber value="${cur.good_discount_price}" type="currency" />
	            </span></td>
	          <c:if test="${af.map.dept_type eq 1}"> 
	            <!-- 系统管理员 -->
	            <td align="center" nowrap="nowrap">
	            	<c:choose>
	                	<c:when test="${(cur.audit_state eq 3) and (cur.kh_confirm_state ne -1) }">已完结</c:when>
	                	<c:otherwise>审核中</c:otherwise>
	                </c:choose>
	            </td>
	            <td align="center" nowrap="nowrap"><c:choose>
	                <c:when test="${empty cur.map.next_audit_role_name}">无</c:when>
	                <c:when test="${not empty cur.map.next_audit_role_name}">${cur.map.next_audit_role_name}</c:when>
	              </c:choose></td>
	          </c:if>
	          <c:if test="${af.map.dept_type eq 2}"> 
	            <!-- 非系统管理员 -->
	            <td align="center" nowrap="nowrap"><c:choose>
	                <c:when test="${cur.audit_state eq 3}">已完结</c:when>
	                <c:otherwise>
	                  <c:choose>
	                    <c:when test="${cur.map.states eq 0}">待审核</c:when>
	                    <c:when test="${cur.map.states eq 1}">审核中</c:when>
	                  </c:choose>
	                </c:otherwise>
	              </c:choose></td>
	          </c:if>
	         <td align="center" nowrap="nowrap">${fn:split('待确认,不用确认,已确认', ',')[cur.kh_confirm_state + 1]}</td>
	         <td align="center" nowrap="nowrap">${empty cur.r3_id ? '未同步' : cur.r3_id}</td>
	        </tr>
      </c:forEach>
      
    </table>
</body>
</html>