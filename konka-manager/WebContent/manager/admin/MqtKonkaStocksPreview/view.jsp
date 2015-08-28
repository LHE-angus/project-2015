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
<body>
  <div>
      <table width="100%" border="1" cellpadding="0" cellspacing="1" >
        <tr>
          <td>序号</td>
          <td>分公司</td>
          <td>R3编码</td>
          <td>客户名称</td>
          <td>产品型号</td>
          <td>初始库存</td>
          <td>进货数量</td>
          <td>进货金额</td>
          <td>销售数量</td>
          <td>销售金额</td>
          <td>剩余库存</td>
          <td>盘存时间</td>
        </tr>
        <c:forEach var="cur" items="${allList}" varStatus="vs">
          <tr>
            <td>${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td><td>${fn:escapeXml(cur.map.dept_name)}</td><td>${fn:escapeXml(cur.map.r3_code)}</td><td>${fn:escapeXml(cur.map.customer_name)}</td><td>${fn:escapeXml(cur.map.pd_name)}</td><td><fmt:formatNumber value="${cur.map.initcount}" pattern="0" /></td><td><fmt:formatNumber value="${cur.map.incount}" pattern="0.00" /></td><td><fmt:formatNumber value="${cur.map.inmoney}" pattern="0" /></td><td><fmt:formatNumber value="${cur.map.outcount}" pattern="0.00" /></td><td><fmt:formatNumber value="${cur.map.outmoney}" pattern="0" /></td><td><fmt:formatNumber value="${cur.map.stocks}" pattern="0" /></td><td><fmt:formatDate value="${cur.map.ts}" pattern="yyyy-MM-dd HH:mm" /></td>
          </tr>
        </c:forEach>
      </table>
  </div>
</body>
</html>