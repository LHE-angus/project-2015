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
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">开始时间</td>
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">结束时间</td>  
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">分公司</td>  
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">上报人</td>
        	<td width="3%" nowrap="nowrap" align="center" style="background-color: #97FFFF;">客户编码</td>
        	<td width="6%" nowrap="nowrap" align="center" style="background-color: #97FFFF;">客户名称</td>  
        	<td width="4%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">门店名称</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">客户类型</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">拜访类型</td>
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">反馈问题</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">拜访说明</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">被访人</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">被访人电话</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">拜访状态</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">添加时间</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">状态</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">数据来源</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">当前位置</td>
  		</tr>
  		<c:forEach var="cur" items="${allList}" varStatus="vs">
  			<tr class="list-tr">
  			    <td align="center" nowrap="nowrap">${vs.count}</td>
  				<td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.begin_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
  				<td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.end_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
  				<td align="left" nowrap="nowrap">${cur.map.subcomp_name}</td>
  				<td align="left" nowrap="nowrap">${cur.report_nae}</td>  	
  				<td align="left" nowrap="nowrap">
  					${cur.map.r3_code}
  				</td>
  				<td align="left" nowrap="nowrap"><c:out value="${cur.map.customer_name}" />  </td>
  				<td align="right" nowrap="nowrap"><c:out value="${cur.map.shop_name}" />  </td>
  				<td align="right" nowrap="nowrap"><c:out value="${cur.map.cust_type_name}" /> </td>
  				<td align="left" nowrap="nowrap"><c:out value="${cur.map.visit_type_names}" /> </td>
  				<td align="left" nowrap="nowrap"><c:out value="${cur.feed_list}" /> </td>  	
  				<td align="left" nowrap="nowrap"><c:out value="${cur.visit_desc}" /> </td>
  				<td align="left" nowrap="nowrap">${cur.consumer_name}</td>
  				<td align="left" nowrap="nowrap">${cur.consumer_phone}</td>
  				<td align="left" nowrap="nowrap"><c:if test="${cur.state eq 0}">需跟踪</c:if>
	          	<c:if test="${cur.state eq 1}">已关闭</c:if></td>
  				<td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
  				<td align="left" nowrap="nowrap"><c:if test="${cur.is_del eq 0}">正常</c:if>
          	<c:if test="${cur.is_del eq 1}">已删除</c:if></td>
  				<td align="left" nowrap="nowrap"><c:if test="${cur.data_source eq 0}">手机端</c:if>
	      	<c:if test="${cur.data_source eq 1}">手机端</c:if>
           	<c:if test="${cur.data_source eq 2}">web端</c:if>
           	<c:if test="${cur.data_source eq 3}">手机端</c:if></td>
  				<td align="left" nowrap="nowrap"><c:out value="${cur.map.address}" /> </td>
  			</tr>
  		</c:forEach>
  	</table>
</div>
<script type="text/javascript"><!--//<![CDATA[
//]]>--></script>
</body>
</html>
