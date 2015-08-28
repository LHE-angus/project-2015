<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
</head>
<body><table border="1" >         
<tr><td align="center">序号</td><td width="10%" nowrap="nowrap" align="center">机型</td><td align="center">销售价格</td><td width="10%" nowrap="nowrap" align="center">抵扣金额</td><td align="center">实付金额</td><td align="center">数量</td><td align="center">佣金</td><td align="center">奖励积分</td><td align="center">物流费用</td><td align="center">支付费用 </td></tr><c:forEach var="cur" items="${entityList}" varStatus="vs">
<tr><td height="28" align="center">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td><td align="left"><c:out value="${cur.pd_sn}" /></td><td align="left"><fmt:formatNumber value="${cur.total_price}" pattern="0.00" /></td><td align="left"><fmt:formatNumber value="${cur.dedu_price}" pattern="0.00" /></td><td align="left"><fmt:formatNumber value="${cur.pay_price}" pattern="0.00" /></td><td align="left"><c:out value="${cur.num}" /> </td><td align="left"><fmt:formatNumber value="${cur.rebates}" pattern="0.00" /> </td><td align="left"><fmt:formatNumber value="${cur.integral}" pattern="0" /></td><td align="left"><fmt:formatNumber value="${cur.price_wl}" pattern="0.00" /></td><td align="left"><fmt:formatNumber value="${cur.zffy}" pattern="0.00" /></td></tr></c:forEach>   
</table>  
</body>
</html>
