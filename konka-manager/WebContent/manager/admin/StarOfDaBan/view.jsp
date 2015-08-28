<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
</head>
<body >
   <div>
    <table width="100%" border="1" cellspacing="0" cellpadding="0" class="rtable2">
  		<tr>
  			<td width="5%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">排名</td>
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">分公司</td>
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">门店ID</td>  
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">门店名称</td>  
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">门店类型</td>
        	<td width="3%" nowrap="nowrap" align="center" style="background-color: #97FFFF;">促销员</td>
        	<td width="6%" nowrap="nowrap" align="center" style="background-color: #97FFFF;">销售量</td>  
        	<td width="4%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">销售额</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">省</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">市</td>
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">县</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">镇</td>
  		</tr>
  		<c:forEach var="cur" items="${allList}" varStatus="vs">
  			<tr class="list-tr">
  				<td align="center" nowrap="nowrap">${cur.STORE_RANK}</td>
  				<td align="left" nowrap="nowrap">${cur.SUBCOMP_NAME}</td>
  				<td align="left" nowrap="nowrap">${cur.DEPT_ID}</td>
  				<td align="left" nowrap="nowrap">${cur.DEPT_NAME}</td>  	
  				<td align="left" nowrap="nowrap">
  					<c:if test="${not empty cur.STORE_TYPE}">${cur.STORE_TYPE}</c:if>
  				</td>
  				<td align="left" nowrap="nowrap">${cur.CXY_NAME }</td>
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.NUM}" pattern="#,###"/></td>
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.ALL_PRICE}" pattern="#,##0.00"/></td>
  				<td align="left" nowrap="nowrap">${cur.PROVINCE}</td>
  				<td align="left" nowrap="nowrap">${cur.CITY}</td>  	
  				<td align="left" nowrap="nowrap">${cur.COUNTRY}</td>
  				<td align="left" nowrap="nowrap">${cur.TOWN}</td>
  			</tr>
  		</c:forEach>
  	</table>
</div>
<script type="text/javascript"><!--//<![CDATA[
//]]>--></script>
</body>
</html>
