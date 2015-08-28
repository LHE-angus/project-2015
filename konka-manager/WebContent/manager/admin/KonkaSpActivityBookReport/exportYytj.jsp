<%@ page language="java"
	contentType="application/octet-stream;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
</head>
<body>
<table width="100%" border="1" cellspacing="0" cellpadding="0">
	   <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="5%">序号</td>
        <td nowrap="nowrap" align="center" width="10%">分公司</td>
         <td nowrap="nowrap"  align="center" width="10%">客戶编号</td>
        <td nowrap="nowrap"  align="center" width="12%">客户名称</td>
        <td nowrap="nowrap"  align="center" width="12%">门店名称</td>
        <td nowrap="nowrap"  align="center" width="10%">活动标题</td>
        <td nowrap="nowrap"  align="center" width="10%">预约点</td>
        <td nowrap="nowrap"  align="center" width="10%">尺寸段</td>
        <td nowrap="nowrap"  align="center" width="10%">预约数量</td>
        <td nowrap="nowrap"  align="center" width="8%">定金金额</td>
      </tr>
     
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">
            ${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>	
           <td align="center" nowrap="nowrap">
           ${cur.map.dept_name}
           </td>
 			<td align="center" nowrap="nowrap"> ${cur.r3_code} </td>
            <td align="center" nowrap="nowrap">${cur.map.customer_name} </td>
            <td align="center" nowrap="nowrap">${cur.store_name} </td>
            <td align="left"nowrap="nowrap">
              <a href="${ctx} /manager/admin/KonkaSpActivityManager.do?method=view&mod_id=986010&id=${cur.sp_id}"  style="text-decoration:underline;">
            ${fn:escapeXml(cur.sp_name)}</a></td>
            <td align="left"nowrap="nowrap">
              <a href="${ctx}/manager/admin/KonkaSpActivityAddr.do?method=view&id=${cur.addr_id}"  style="text-decoration:underline;">
            ${fn:escapeXml(cur.addr_name)}</a></td> 
              <td align="center"nowrap="nowrap">
              <c:forEach items="${sizeSecList}" var="sizeSec" varStatus="vs">
					<c:if test="${sizeSec.field1 eq cur.size_section}">${sizeSec.type_name}</c:if>
			  </c:forEach>
              </td> 
             <td align="center"nowrap="nowrap">${cur.num}</td> 
            <td>${cur.prepay_money}</td>
          </tr>
        </c:forEach>
</table>
</body>
</html>
