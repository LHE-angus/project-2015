<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title></title> 
</head>
<body><table width="100%" border="1" cellspacing="0" cellpadding="0" >
        <tbody>
          <tr>
	           <td width="5%" nowrap="nowrap" align="center" rowspan="2">序号</td> 	           
	           <td width="5%" nowrap="nowrap" align="center" rowspan="2">发货分公司</td> 
	           <td width="5%" nowrap="nowrap" align="center" rowspan="2">交易流水号</td> 
	           <td width="5%" nowrap="nowrap" align="center" rowspan="2">订单来源</td> 
	           <td width="5%" nowrap="nowrap" align="center" rowspan="2">退换货时间</td>  
	           <td width="5%" nowrap="nowrap" align="center" rowspan="2">型号</td> 
	           <td width="5%" nowrap="nowrap" align="center" rowspan="2">数量</td>
	           <td width="5%" nowrap="nowrap" align="center" rowspan="2">金额</td>
	           <td width="5%" nowrap="nowrap" align="center" rowspan="2">收货人</td>
	           <td width="5%" nowrap="nowrap" align="center" rowspan="2">收货人电话</td>
	           <td width="160" nowrap="nowrap" align="center" rowspan="2">收货地址</td> 
	           <td width="25%" nowrap="nowrap" align="center" colspan="4">客服跟踪</td>
	           <td width="20%" nowrap="nowrap" align="center" colspan="4">财务跟踪</td>  
          	   <td width="160" nowrap="nowrap" align="center" rowspan="2">备注</td>
          </tr>
          <tr>
	          <td width="5%" nowrap="nowrap" align="center">机器状态</td>
	          <td width="5%" nowrap="nowrap" align="center">退机类型</td>
	          <td width="5%" nowrap="nowrap" align="center">是否换货</td>
	          <td width="5%" nowrap="nowrap" align="center">报险方式</td>
	          <td width="5%" nowrap="nowrap" align="center">报险金额</td>
	          <td width="5%" nowrap="nowrap" align="center">机器入库</td>
	          <td width="5%" nowrap="nowrap" align="center">财务核销</td>
	          <td width="5%" nowrap="nowrap" align="center">理赔是否到账</td>
	          <td width="5%" nowrap="nowrap" align="center">退款金额</td>
          </tr>          
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
	         <td height="28"  align="center">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
	         <td align="left"><c:out value="${cur.oldPshowOrder.map.dept_name}" /></td>
	         <td align="left" style="mso-number-format:'\@';"><c:out value="${cur.oldPshowOrder.trade_index}" /></td>
	         <td align="left"><c:if test="${cur.oldPshowOrder.order_from eq 1 }">工卡</c:if><c:if test="${cur.oldPshowOrder.order_from eq 2}">触网(${cur.oldPshowOrder.ecUser.map.dept_name })</c:if></td>
	         <td align="left"><fmt:formatDate value="${cur.oper_date}" pattern="yy-MM-dd HH:mm:ss" /></td><td align="left"><c:out value="${cur.pshowOrdeDetails.map.pd_sn}" /></td>
	         <td align="center"><c:out value="${cur.pshowOrdeDetails.num}" /></td><td align="left"><c:out value="${cur.pshowOrdeDetails.total_price}" /></td><td align="left"><c:out value="${cur.oldPshowOrder.buyer_name}" /></td><td align="left"><c:out value="${cur.oldPshowOrder.buyer_mp}" /></td>
	         <td align="left"><c:out value="${cur.oldPshowOrder.map.full_name}" />&nbsp;<c:out value="${cur.oldPshowOrder.buyer_addr}" /></td>
	         <td align="left">
	         <c:if test="${cur.exchange_info eq 1 }">质量机退货</c:if>  <c:if test="${cur.exchange_info eq 2 }">碎屏</c:if><c:if test="${cur.exchange_info eq 3 }">好机退货</c:if><c:if test="${cur.exchange_info eq 4 }">其它</c:if>
	         </td>
	         <td align="left"><c:if test="${cur.is_exchange eq 1 }">退货</c:if>  <c:if test="${cur.is_exchange eq 2 }">换货</c:if></td>
	       	 <td align="left"><c:if test="${cur.insurance_way eq 1 }">顺丰理赔</c:if>  <c:if test="${cur.insurance_way eq 2 }">保险+顺丰理赔</c:if><c:if test="${cur.insurance_way eq 3 }">保险理赔</c:if></td>
	       	 <td align="left"><c:out value="${cur.insurance_price}" /></td>
	       	 <td align="left"><c:if test="${cur.pd_store eq 1 }">未入库</c:if>  <c:if test="${cur.pd_store eq 2 }">入库</c:if></td>
	       	 <td align="left"><c:if test="${cur.exchange_state eq 1 }">未退换货</c:if>  <c:if test="${cur.exchange_state eq 2 }">已退换货</c:if></td>
	       	 <td align="left"><c:if test="${cur.insurance_state eq 1 }">未到账</c:if>  <c:if test="${cur.insurance_state eq 2 }">已到账</c:if></td>
	       	 <td align="left"><c:out value="${cur.exchange_price}" /></td>
	       	 <td align="left"><c:out value="${cur.remark}" /></td> 
	       	 </tr> 
          </c:forEach> 
        </tbody>
      </table> 
</body>
</html>
