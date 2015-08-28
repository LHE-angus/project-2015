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
    <table width="100%" border="1" cellpadding="0" cellspacing="1">
      <tr class="tabtt1">
        <td align="center" nowrap="nowrap">序号</td>        
        <td align="center" nowrap="nowrap">分公司</td>
        <td align="center" nowrap="nowrap">经办名称</td>
        <td align="center" nowrap="nowrap">客户类型</td>
        <td align="center" nowrap="nowrap">客户细分类型</td>
        <td align="center" nowrap="nowrap">区域</td>
        <td align="center" nowrap="nowrap">客户名称</td>
        <td align="center" nowrap="nowrap">R3编码</td>
        <td align="center" nowrap="nowrap">年份</td>        
        <td align="center" nowrap="nowrap">1月</td>
        <td align="center" nowrap="nowrap">2月</td>
        <td align="center" nowrap="nowrap">3月</td>
        <td align="center" nowrap="nowrap">4月</td>
        <td align="center" nowrap="nowrap">5月</td>
        <td align="center" nowrap="nowrap">6月</td>
        <td align="center" nowrap="nowrap">7月</td>
        <td align="center" nowrap="nowrap">8月</td>
        <td align="center" nowrap="nowrap">9月</td>
        <td align="center" nowrap="nowrap">10月</td>
        <td align="center" nowrap="nowrap">11月</td>
        <td align="center" nowrap="nowrap">12月</td>
      </tr>
      <c:forEach var="cur" items="${allList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
          <td align="left" nowrap="nowrap">${cur.branch_area_name}</td>
          <td align="left" nowrap="nowrap">${cur.handle_name}</td>
          <td align="left" nowrap="nowrap">${cur.map.c_comm}</td>
          <td align="left" nowrap="nowrap">${cur.map.c_name}</td>
          <td align="left" nowrap="nowrap">${cur.area_name}</td>
          <td align="left" nowrap="nowrap">${cur.customer_name}</td>
          <td align="left" nowrap="nowrap">${cur.r3_code}</td>    
          <td align="center" nowrap="nowrap">${cur.year}</td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_1}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_2}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_3}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_4}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_5}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_6}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_7}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_8}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_9}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_10}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_11}" pattern="#0.00" /></td><td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_12}" pattern="#0.00" /></td>
        </tr>
      </c:forEach>
    </table>
</div>
<script type="text/javascript"><!--//<![CDATA[
//]]>--></script>
</body>
</html>
