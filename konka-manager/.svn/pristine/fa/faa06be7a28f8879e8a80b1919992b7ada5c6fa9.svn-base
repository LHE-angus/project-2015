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
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">经办名称</td>  
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">网点级别</td>  
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">网点编号</td>
        	<td width="3%" nowrap="nowrap" align="center" style="background-color: #97FFFF;">网点名称</td>
        	<td width="6%" nowrap="nowrap" align="center" style="background-color: #97FFFF;">上级客户</td>  
        	<td width="4%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">上级客户编码</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">业务员岗位ID</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">业务员姓名</td>
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">联系人</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">省</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">市</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">县</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">乡/镇</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">街道地址</td>
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">状态</td>
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">是否确认</td>
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">创建时间</td>
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">维护时间</td>
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">创建人</td>
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">维护人</td>
  		</tr>
  		<c:forEach var="cur" items="${allList}" varStatus="vs">
  			<tr class="list-tr">
  				<td>${vs.count}</td>
  				<td align="left" nowrap="nowrap">${cur.DEPT_NAME}</td>
  				<td align="left" nowrap="nowrap">${cur.JB_NAME}</td>
  				<td align="left" nowrap="nowrap">
  					<c:if test="${empty cur.P_LEVEL }">1级网点</c:if>
  					<c:if test="${not empty cur.P_LEVEL }">${cur.P_LEVEL}级网点</c:if>
  				</td>
  				<td align="left" nowrap="nowrap">${cur.PARTNER_SN }</td>
  				<td align="right" nowrap="nowrap">${cur.PARTNER_NAME }</td>
  				<td align="right" nowrap="nowrap">${cur.CUSTOMER_NAME }</td>
  				<td align="left" nowrap="nowrap">${cur.R3_CODE}</td>
  				<td align="left" nowrap="nowrap">${cur.YWY_JOB_ID}</td>  	
  				<td align="left" nowrap="nowrap">${cur.REAL_NAME}</td>
  				<td align="left" nowrap="nowrap">${cur.LINK_NAME}</td>
  				<td align="left" nowrap="nowrap">${cur.PROVINCE}</td>
  				<td align="left" nowrap="nowrap">${cur.CITY}</td>
  				<td align="left" nowrap="nowrap">${cur.COUNTRY}</td>
  				<td align="left" nowrap="nowrap">${cur.TOWN}</td>
  				<td align="left" nowrap="nowrap">${cur.PARTNER_ADDR}</td>
  				<td align="left" nowrap="nowrap">
  					<c:choose>
  						<c:when test="${cur.IS_DEL eq 1}">停用</c:when>
  						<c:otherwise>启用</c:otherwise>
  					</c:choose>
  				</td>
  				<td align="left" nowrap="nowrap">
  					<c:choose>
  						<c:when test="${cur.STATUS eq 0}">待确认</c:when>
  						<c:when test="${cur.STATUS eq 2}">已确认</c:when>
  						<c:otherwise>已确认</c:otherwise>
  					</c:choose>
  				</td>
  				<td align="left" nowrap="nowrap">${cur.CREATE_DATE}</td>
  				<td align="left" nowrap="nowrap">${cur.MODIFY_DATE}</td>
  				<td align="left" nowrap="nowrap">${cur.CREATE_MAN}</td>
  				<td align="left" nowrap="nowrap">${cur.MODIFY_MAN}</td>
  			</tr>
  		</c:forEach>
  	</table>
</div>
<script type="text/javascript"><!--//<![CDATA[
//]]>--></script>
</body>
</html>
