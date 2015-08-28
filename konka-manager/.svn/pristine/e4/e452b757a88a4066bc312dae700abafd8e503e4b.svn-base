<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<title>打印</title>
</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" class="printTabTop">
  <tr>
    <td colspan="7" align="center" style="font-weight:bold;font-size: 18px;">分型号毛利分析报表</td>
  </tr>
  <tr>
    <td colspan="7" align="right" >${querydate}</td>
  </tr>
</table>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" class="printTab">
  <tr>
    <td width="15%" height="30" align="center"><font class="bigall">型号</font></td>
    <td width="15%" height="30" align="center"><font class="bigall">品类</font></td>
    <td width="15%" height="30" align="center"><font class="bigall">品牌</font></td>
    <td width="10%" height="30" align="center"><font class="bigall">商品数量</font></td>
    <td width="10%" height="30" align="center"><font class="bigall">销售成本</font></td>
    <td width="10%" height="30" align="center"><font class="bigall">销售收入</font></td>
    <td width="10%" height="30" align="center"><font class="bigall">毛利</font></td>
    <td width="10%" height="30" align="center"><font class="bigall">毛利率</font></td>
  </tr>
  		<c:forEach items="${entityList}" var="cur" varStatus="status">
  			 <tr>
			    <td height="30" align="center">${cur.map.pd_name}</td>
			    <td height="30" class="mergeCell">${cur.map.pd_type_name}</td>
			    <td height="30">${cur.map.brand_name}</td>
				<td height="30">${cur.map.count}</td>
			    <td height="30"><fmt:formatNumber value="${cur.map.cost_sales}" type="currency" /></td>
			    <td height="30"><fmt:formatNumber value="${cur.map.sales_revenue}" type="currency" /></td>
			    <td height="30"><fmt:formatNumber value="${cur.map.maori}" type="currency" /></td>
			    <td height="30"><fmt:formatNumber value="${cur.map.maori_rate div 100}" type="percent" maxFractionDigits="2" /></td>
			  </tr>
  		</c:forEach>
</table>
<c:if test="${not empty entityList}">
<div align="center">
  <input name="button" type="button" class="bgButtonPrint" value="打印" onclick="this.style.display='none';window.print();"/>
</div>
</c:if>
<c:if test="${empty entityList}">
  <div align="center">
  	<input name="button" type="button" class="dayin" value="关闭" onclick="window.close();" />
  </div>
</c:if>
<jsp:include page="/__analytics.jsp" />
</body>
</html>