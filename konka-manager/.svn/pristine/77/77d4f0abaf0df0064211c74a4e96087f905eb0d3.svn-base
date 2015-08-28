<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
</head>
<body >
  	<table width="100%" border="1" cellpadding="0" cellspacing="1">
      <tr>
        <td>序号</td>
        <td>销售组织</td>
        <td>售达方</td>
        <td>分类描述</td>
        <td>名称(售)</td>
        <td>送达方</td>
        <td>名称（送）</td>
        <td>创建日期</td>
        <td>凭证日期</td>
        <td>订单号</td>
        <td>订单类型</td>
        <td>项目</td>
        <td>机型</td>
        <td>订单数量</td>
        <td>单价（含税）</td>
        <td>总金额（含税）</td>
        <td>交货单数量</td>
        <td>已发货数量</td>
        <td>开发票数量</td>
        <td>K007（含税）</td>
        <td>总净值金额(含税)</td>
        <td>客户物料号</td>
        <td>办事处</td>
      </tr>
      <c:forEach var="cur" items="${entityList1}" varStatus="vs">
        <tr>
          <td>${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td><td>${cur.column_25}</td><td>${cur.column_1}</td><td>${cur.column_3}</td><td>${cur.map.column_04}</td><td>${cur.column_5}</td><td>${cur.map.column_06}</td><td><fmt:formatDate value="${cur.column_7}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td><td><fmt:formatDate value="${cur.column_26}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td><td>${cur.column_8}</td><td>${cur.column_9}</td><td>${cur.column_10}</td><td>${cur.column_11}</td><td><fmt:formatNumber value="${cur.column_12}" pattern="#,###"/></td><td><fmt:formatNumber value="${cur.column_13}" pattern="#,##0.00"/></td><td><fmt:formatNumber value="${cur.column_14}" pattern="#,##0.00"/></td><td><fmt:formatNumber value="${cur.column_27}" pattern="#,###"/></td><td><fmt:formatNumber value="${cur.column_28}" pattern="#,###"/></td><td><fmt:formatNumber value="${cur.column_29}" pattern="#,###"/></td><td>${cur.column_15}</td><td>${cur.column_17}</td><td>${cur.column_23}</td><td>${cur.column_24}</td>
        </tr>
      </c:forEach>
    </table>
</body>
</html>
