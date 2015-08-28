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
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">日期</td>
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">分公司</td>
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">类别</td>  
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">名称</td>  
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">编码</td>
        	<td width="3%" nowrap="nowrap" align="center" style="background-color: #97FFFF;">申请类型</td>
        	<td width="6%" nowrap="nowrap" align="center" style="background-color: #97FFFF;">申请原因</td>  
        	<td width="4%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">确认状态</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">申请人</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">确认人</td>
  		</tr>
  		<c:forEach var="cur" items="${allList}" varStatus="vs">
  			<tr class="list-tr">
  				<td>${vs.count}</td>
  				<td align="left" nowrap="nowrap">${cur.ADD_DATE}</td>
  				<td align="left" nowrap="nowrap">${cur.DEPT_NAME}</td>
  				<td align="left" nowrap="nowrap">${cur.AUDIT_TYPE}</td>
  				<td align="left" nowrap="nowrap">${cur.TYPE_NAME }</td>
  				<td align="right" nowrap="nowrap">${cur.TYPE_CODE }</td>
  				<td align="right" nowrap="nowrap">${cur.OPER_NAME }</td>
  				<td align="left" nowrap="nowrap">${cur.OPER_REASON}</td>
  				<td align="left" nowrap="nowrap">
  					<c:if test="${cur.STATUS eq 0 }">待确认</c:if>
  					<c:if test="${cur.STATUS eq 1 }">已驳回</c:if>
  					<c:if test="${cur.STATUS eq 2 }">已确认</c:if>
  				</td>  	
  				<td align="left" nowrap="nowrap">${cur.CREAT_NAME}</td>
  				<td align="left" nowrap="nowrap">${cur.MODIFY_NAME}</td>
  			</tr>
  		</c:forEach>
  	</table>
</div>
<script type="text/javascript"><!--//<![CDATA[
//]]>--></script>
</body>
</html>
