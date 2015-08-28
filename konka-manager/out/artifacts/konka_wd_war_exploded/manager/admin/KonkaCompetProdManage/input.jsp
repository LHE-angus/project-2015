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
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      	<tr class="tabtt1">
          <td width="10%" nowrap="nowrap" align="left">品牌</td>
          <td width="6%" nowrap="nowrap" align="right">尺寸</td>
          <td width="6%" nowrap="nowrap" align="center">规格段</td>
          <td width="10%" nowrap="nowrap" align="left">智能电视</td>
          <td width="10%" nowrap="nowrap" align="left">三维电视</td>
          <td width="10%" nowrap="nowrap" align="left">背光源</td>
          <td width="21%" nowrap="nowrap" align="left">机型</td>
          <td width="10%" nowrap="nowrap" align="right">参考价格（元）</td>
          <td width="8%" nowrap="nowrap" align="right">排序值</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${allList}" varStatus="vs">
          	<tr>
          	  <td align="left">${cur.brand_name}</td>
          	  <td align="right">${cur.screen_size}</td>
          	  <td align="center">${cur.map.par_type_name}</td>
          	  <td align="left">
          	  	<c:if test="${cur.is_smart_tv eq 0}">否</c:if>
          	  	<c:if test="${cur.is_smart_tv eq 1}">是</c:if>
          	  </td>
          	  <td align="left">
          	  	<c:if test="${cur.d_tv eq 0}">2D</c:if>
          	  	<c:if test="${cur.d_tv eq 1}">3D</c:if>
          	  </td>
          	  <td align="left">
          	  	<c:if test="${cur.back_light eq 0}">LED</c:if>
          	  	<c:if test="${cur.back_light eq 1}">CCFL</c:if>
          	  	<c:if test="${cur.back_light eq 9}">其他</c:if>
          	  </td>
          	  <td align="left">${cur.md_name}</td>
          	  <td align="right"><fmt:formatNumber value="${cur.ref_price}" pattern="#,###.00" /></td>
          	  <td align="right">${cur.order_value}</td>
          	</tr>
          </c:forEach>
      	</tbody>
      </table>
  </div>
</body>
</html>
