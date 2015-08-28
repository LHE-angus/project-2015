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
  		<tr class="tabtt1">
  			<td width="5%" align="center" nowrap="nowrap">序号</td>
        	<td align="center" nowrap="nowrap">客户名称</td>
        	<td align="center" nowrap="nowrap">R3编码</td>  
        	<td align="center" nowrap="nowrap">门店ID</td>  
        	<td align="center" nowrap="nowrap">门店名称</td>
        	<c:if test="${not empty model_name_select}">
        		<td align="center" nowrap="nowrap">产品型号</td>
        	</c:if>
        	<td width="3%" nowrap="nowrap" align="center">零售额</td>
        	<td width="6%" nowrap="nowrap" align="center">零售量</td>  
        	<td width="4%" align="center" nowrap="nowrap">分公司</td>
        	<td width="6%" align="center" nowrap="nowrap">一级部门</td>
        	<td align="center" nowrap="nowrap">业务员</td>
        	<td width="6%" align="center" nowrap="nowrap">客户类型</td>
        	<td width="6%" align="center" nowrap="nowrap">客户细分类型</td>        	

  		</tr>
  		<c:forEach var="cur" items="${allList}" varStatus="vs">
  			<tr class="list-tr">
  				<td align="center" nowrap="nowrap">${vs.count}</td>
  				<td align="left" nowrap="nowrap">${cur.CUSTOMER_NAME}</td>
  				<td align="left" nowrap="nowrap">${cur.CUSTOMER_R3_CODE}</td>  	
  				<td align="left" nowrap="nowrap">${cur.STORE_ID}</td>
  				<td align="left" nowrap="nowrap">${cur.STORE_NAME}</td>
  				<c:if test="${not empty model_name_select}">
        			<td align="center" nowrap="nowrap">${cur.MODEL_NAME}</td>
        		</c:if>
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.TOTAL_MONEY}" pattern="#,##0.00"/></td>
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.TOTAL_NUM}" pattern="#,###"/></td>
  				<td align="left" nowrap="nowrap">${cur.DEPT_NAME}</td>
  				<td align="left" nowrap="nowrap">${cur.L4_DEPT_NAME}</td>
  				<td align="left" nowrap="nowrap">${cur.YWY_USER_NAME}</td>
  				<td align="left" nowrap="nowrap">${cur.PAR_CUSTOMER_TYPE_NAME}</td>
  				<td align="left" nowrap="nowrap">${cur.CUSTOMER_TYPE_NAME}</td>
  			</tr>
  		</c:forEach>
  	</table>
</div>
<script type="text/javascript"><!--//<![CDATA[
//]]>--></script>
</body>
</html>
