<%@ page language="java"
	contentType="application/octet-stream;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
</head>
<body>
<table width="100%" border="1" cellspacing="0" cellpadding="0">
	<tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="5%">序号</td>
        <td nowrap="nowrap" align="center" width="5%">门店</td>
        <td nowrap="nowrap" align="center" width="10%">促销活动</td>
         <td nowrap="nowrap"  align="center" width="10%">预约点</td>
        <td nowrap="nowrap"  align="center" width="12%">消费者姓名</td>
        <td nowrap="nowrap"  align="center" width="10%">消费者电话</td>
        <td nowrap="nowrap"  align="center" width="10%">预约尺寸</td>
        <td nowrap="nowrap"  align="center" width="10%">预约型号</td>
        <td nowrap="nowrap"  align="center" width="10%">预约数量</td>
        <td nowrap="nowrap"  align="center" width="8%">支付定金</td>
        <td nowrap="nowrap"  align="center" width="8%">定金金额</td>
      </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">
            ${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>	
          <td align="center"nowrap="nowrap">${fn:escapeXml(cur.store_name)}</td>  
            <td align="left"nowrap="nowrap">
            ${fn:escapeXml(cur.sp_name)}</td>
            <td align="left"nowrap="nowrap">
            ${fn:escapeXml(cur.addr_name)}</td> 
             <td align="center"nowrap="nowrap">${fn:escapeXml(cur.comsumer_name)}</td> 
             <td align="center"nowrap="nowrap">${cur.comsumer_phone}</td> 
              <td align="center"nowrap="nowrap">${cur.size_section}</td> 
              <td align="center"nowrap="nowrap">${cur.model_name}</td> 
              <td align="center"nowrap="nowrap">${cur.num}</td> 
             <td align="center"nowrap="nowrap">  <c:choose>
            <c:when test="${cur.prepay_state eq 0}"><font color="green">是</font></c:when>
             <c:when test="${cur.prepay_state eq 1}"><font color="grey">否</font></c:when>
             <c:otherwise>未指定</c:otherwise>
            </c:choose>
            </td> 
            <td>${cur.prepay_money}</td>
          </tr>
        </c:forEach>
</table>
</body>
</html>
