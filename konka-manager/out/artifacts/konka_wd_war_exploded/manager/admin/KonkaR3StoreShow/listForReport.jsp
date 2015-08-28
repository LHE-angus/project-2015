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
    <table width="100%" border="1">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
		<td width="10%" nowrap="nowrap" align="center">分公司</td>
		<td width="10%" nowrap="nowrap" align="center">客户R3</td>
		<td width="10%" nowrap="nowrap" align="center">客户名称</td>
		<td width="10%" nowrap="nowrap" align="center">门店名称</td>
		<td width="10%" nowrap="nowrap" align="center">门店ID</td>
		<td nowrap="nowrap" align="center">类型</td>
		<td nowrap="nowrap" align="center">品类名称</td>
	    <td width="10%" nowrap="nowrap" align="center">尺寸/规格</td>
		<td width="10%" nowrap="nowrap" align="center">数量</td>
		<td width="10%" nowrap="nowrap" align="center">金额</td>
		<td width="10%" nowrap="nowrap" align="center">年</td>
		<td width="10%" nowrap="nowrap" align="center">月</td>
		<td width="10%" nowrap="nowrap" align="center">添加人</td>
		<td width="10%" nowrap="nowrap" align="center">添加时间</td>
		<td width="10%" nowrap="nowrap" align="center">备注</td>
      </tr> 
      <c:forEach items="${entityList1}" var="cur" varStatus="vs">
        <tr>
         <td align="center" nowrap="nowrap">${vs.count}</td>
		 <td align="left" nowrap="nowrap">${cur.dept_name}</td>
		 <td align="left" nowrap="nowrap">${cur.map.r3_code}</td>
		 <td align="left" nowrap="nowrap">${cur.map.customer_name}</td>
		 <td align="left" nowrap="nowrap">${cur.store_name}</td>
		 <td align="left" nowrap="nowrap">${cur.store_id}</td>
		 <td align="left" nowrap="nowrap">${cur.task_name}</td>
		 <td align="left" nowrap="nowrap">${cur.category_name}</td>
		 <td align="right" nowrap="nowrap">${cur.size}</td>
		 <td align="right" nowrap="nowrap">${cur.num}</td>
		 <td align="right" nowrap="nowrap">${cur.task_money}</td>
		 <td align="left" nowrap="nowrap">${cur.year}</td>
		 <td align="left" nowrap="nowrap">${cur.month}</td>
		 <td align="right" nowrap="nowrap">${cur.map.user_name}</td>
		 <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
		 <td align="center" nowrap="nowrap">${cur.remark}</td>
        </tr>
      </c:forEach>
    </table>
    </div>
</body>
</html>
