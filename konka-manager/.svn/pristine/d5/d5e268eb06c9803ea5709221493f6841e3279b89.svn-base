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
  			<td width="5%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">序号</td>
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">分公司</td>
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">合并编码</td>  
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">合并客户名称</td>  
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">户头</td>
        	<td width="3%" nowrap="nowrap" align="center" style="background-color: #97FFFF;">R3编码</td>
        	<td width="6%" nowrap="nowrap" align="center" style="background-color: #97FFFF;">合并客户类型</td>  
        	<td width="4%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">合并客户状态</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">区域信息</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">业务员</td>
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">当月结算</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">去年同期</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">当月回款</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">去年同期</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">剩余额度</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">加盟时间</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">更新时间</td>
  		</tr>
  		<c:forEach var="cur" items="${allList}" varStatus="vs">
  			<tr class="list-tr">
  				<td align="center">${vs.count}</td>
  				<td align="center" nowrap="nowrap">${cur.DEPT_NAME}</td>
  				<td align="left" nowrap="nowrap">${cur.R3_CODE}</td>
  				<td align="left" nowrap="nowrap">${cur.CUSTOMER_NAME }</td>
  				<td align="center" nowrap="nowrap">${cur.CUST_NUM }</td>
  				<td align="left" nowrap="nowrap">${cur.R3_CODE_LIST }</td>
  				<td align="left" nowrap="nowrap">${cur.C_NAME }</td>
  				<td align="left" nowrap="nowrap">
  					<c:if test="${cur.SHOP_STATUS eq 1 }">新客户</c:if>
  					<c:if test="${cur.SHOP_STATUS eq 2 }">正式客户</c:if>
  					<c:if test="${cur.SHOP_STATUS eq 3 }">静止客户</c:if>
  					<c:if test="${cur.SHOP_STATUS eq 4 }">无效客户</c:if>
  					<c:if test="${cur.SHOP_STATUS eq 5 }">门店网点</c:if>
  				</td>
  				<td align="left" nowrap="nowrap">
  					<c:if test="${empty cur.AREA_NAME }">${cur.DEPT_NAME}</c:if>
  					<c:if test="${not empty cur.AREA_NAME }">${cur.AREA_NAME}－${cur.DEPT_NAME}</c:if>
  				</td>  	
  				<td align="center" nowrap="nowrap">${cur.USER_NAME}</td>
  				<td align="right" nowrap="nowrap">${cur.CUR_CLS_MONEY}</td>
  				<td align="right" nowrap="nowrap">${cur.LASTYEAR_CLS_MONEY}</td>
  				<td align="right" nowrap="nowrap">${cur.CUR_BACK_MONEY}</td>
  				<td align="right" nowrap="nowrap">${cur.LASTYEAR_BACK_MONEY}</td>
  				<td align="right" nowrap="nowrap">${cur.CREDIT_BALANCE}</td>
  				<td align="center" nowrap="nowrap">${cur.ADD_DATE}</td>
  				<td align="center" nowrap="nowrap">${cur.CREATE_DATE}</td>
  			</tr>
  		</c:forEach>
  	</table>
</div>
<script type="text/javascript"><!--//<![CDATA[
//]]>--></script>
</body>
</html>
