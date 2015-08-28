<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>${naviString}</title>
	<style type="text/css">
		.rtable1 td{
			padding: 2px 0 2px 0;
		}
	</style>
</head>
<body>
<div class="oarcont" id="body_oarcont">
	<table width="100%" border="1" cellpadding="0" cellspacing="1" class="rtable6">
	  <tr class="tabtt6">
		  <td width="5%" nowrap="nowrap" align="center">序号</td>
		  <td nowrap="nowrap" align="center">产品型号</td>
		  <td nowrap="nowrap" align="center" width="5%">尺寸段</td>
		  <td width="20%" align="center" nowrap="nowrap">销售额</td>
		  <td width="20%" align="center" nowrap="nowrap">销售量</td>
		  <td width="20%" align="center" nowrap="nowrap">平均单价</td>
	  </tr>
	  <c:set var="total_money" value="0" />
	  <c:set var="total_num" value="0" />
	  <c:forEach items="${entityList}" var="cur" varStatus="vs">
		<tr>
		  <td align="center">${vs.count}</td>
		  <td align="left" nowrap="nowrap">${cur.MODEL_NAME}</td>
		  <td align="center" nowrap="nowrap">${cur.TYPE_NAME}</td>
		  <td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${cur.ALL_PRICE}" type="number" pattern="#,##0.00" /></span></td>
		  <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.ALL_NUM}" type="number" pattern="#,###" /></td>
		  <td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${cur.PRICE}" type="number" pattern="#,##0.00"/></span></td>
		</tr>
	  </c:forEach>
	</table>
  </div>
</body>
</html>


