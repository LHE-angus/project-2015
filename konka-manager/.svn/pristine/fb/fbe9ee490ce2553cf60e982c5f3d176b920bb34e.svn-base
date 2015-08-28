<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
</head>
<body>
      <table width="100%" border="1" cellpadding="0" cellspacing="1">
        <tr>
          <td>序号</td>
          <td>销售组织</td>
          <td>客户编码</td>
          <td>客户名称</td>
          <td>客户类型</td>
          <td>客户细分类型</td>
          <td>风险类型</td>
		  <td>原始分配金额</td>
          <td>当月分配金额</td>	
          <td>信用账期额度</td>
          <td>总经理担保额度</td>
          <td>信贷限额</td>
          <td>信贷风险总额</td>
          <td>剩余额度</td>
          <td>使用的信贷限额</td>
          <td>余额(应收) </td>
        </tr>
        <c:forEach var="cur" items="${allList}" varStatus="vs">
        <tr>
        	<td>${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
        	<td>${cur.vkorg}</td>
        	<td>${cur.kunnr}</td>
        	<td>${cur.map.customer_name}</td>
        	<td>${cur.map.c_comm}</td>
            <td>${cur.map.c_name}</td>
        	<td>${fn:escapeXml(cur.ctlpc)}</td>
        	<td>${fn:escapeXml(cur.klime)}</td>
        	<td>${fn:escapeXml(cur.klimg)}</td>
        	<td>${fn:escapeXml(cur.dbekr)}</td>
        	<td>${fn:escapeXml(cur.zlimt)}</td>
        	<td>${fn:escapeXml(cur.klimk)}</td>
        	<td>${fn:escapeXml(cur.oblig)}</td>
        	<td>${fn:escapeXml(cur.syed)}</td>
        	<td>${fn:escapeXml(cur.klprz)}</td>
        	<td>${fn:escapeXml(cur.skfor)}</td>
        </tr>
        </c:forEach>
        </table>
</body>
</html>