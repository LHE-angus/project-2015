<%@ page language="java" contentType="text/html; charset=UTF-8"
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
  <div>
     <table width="100%"  border="1" cellspacing="0" cellpadding="0">
      <tr class="tabtt1">
      <td width="11%" nowrap="nowrap" align="center">序号</td>
        <td width="11%" nowrap="nowrap" align="center">品牌</td>
        <td width="11%" nowrap="nowrap" align="center">小于32英寸</td>
        <td width="11%" nowrap="nowrap" align="center">39-40英寸</td>
        <td width="11%" nowrap="nowrap" align="center">42-43英寸</td>
        <td width="11%" nowrap="nowrap" align="center">46-50英寸</td>
        <td width="11%" nowrap="nowrap" align="center">55英寸</td>
        <td width="11%" nowrap="nowrap" align="center">58-60英寸</td>
        <td width="11%" nowrap="nowrap" align="center">65英寸</td>
        <td width="11%" nowrap="nowrap" align="center">大于65英寸</td>
        <td width="11%" nowrap="nowrap" align="center">其他</td>
      </tr>
      <tbody> 
        <c:forEach var="cur" items="${allList}" varStatus="vs">
         <c:if test="${pbList==1}">
          <tr>
          <td align="center" nowrap="nowrap">${vs.count}</td>
          	<td width="11%" nowrap="nowrap" align="center">${cur.map.brand_name }</td>
           	<td width="11%" nowrap="nowrap" align="center">${cur.map.yc32 }</td>
        	<td width="11%" nowrap="nowrap" align="center">${cur.map.yc40 }</td>
        	<td width="11%" nowrap="nowrap" align="center">${cur.map.yc43 }</td>
        	<td width="11%" nowrap="nowrap" align="center">${cur.map.yc50 }</td>
       		<td width="11%" nowrap="nowrap" align="center">${cur.map.yc55 }</td>
        	<td width="11%" nowrap="nowrap" align="center">${cur.map.yc60 }</td>
        	<td width="11%" nowrap="nowrap" align="center">${cur.map.yc65 }</td>
        	<td width="11%" nowrap="nowrap" align="center">${cur.map.ycd65 }</td>
        	<td width="11%" nowrap="nowrap" align="center">${cur.map.ycqt }</td>
          </tr>
          </c:if>
           <c:if test="${pbList==0}">
         <tr>
          <td width="8%" nowrap="nowrap" align="right">${vs.count}</td>
          <td width="8%" nowrap="nowrap" align="right" >${cur.map.brand_name }</td>
          <td width="8%" nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.yc32 }"  pattern="0.00"></fmt:formatNumber></td>
          <td width="8%" nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.yc40 }"  pattern="0.00"></fmt:formatNumber></td>
          <td width="8%" nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.yc43 }"  pattern="0.00"></fmt:formatNumber></td>
          <td width="8%" nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.yc50 }"  pattern="0.00"></fmt:formatNumber></td>
          <td width="8%" nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.yc55 }"  pattern="0.00"></fmt:formatNumber></td>
          <td width="8%" nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.yc60 }"  pattern="0.00"></fmt:formatNumber></td>
          <td width="8%" nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.yc65 }"  pattern="0.00"></fmt:formatNumber></td>
          <td width="8%" nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.ycd65}"  pattern="0.00"></fmt:formatNumber></td>
          <td width="8%" nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.ycqt }"  pattern="0.00"></fmt:formatNumber></td>
          </tr>
          </c:if>
        </c:forEach>
      </tbody>
    </table>
  </div>
</body>
</html>
