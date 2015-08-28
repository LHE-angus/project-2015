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
        <td nowrap="nowrap" align="center" width="15%">预约点编码</td>
        <td nowrap="nowrap"  align="center" width="20%">预约点标题</td>
         <td nowrap="nowrap"  align="center" width="10%">客户编码</td>
        <td nowrap="nowrap"  align="center" width="12%">客户名称</td>
        <td nowrap="nowrap"  align="center" width="12%">门店名称</td>
        <td nowrap="nowrap"  align="center" width="10%">创建人</td>
        <td nowrap="nowrap"  align="center" width="10%">创建时间</td>
        <td nowrap="nowrap"  align="center" width="10%">预约点负责人</td>
        <td nowrap="nowrap"  align="center" width="8%">预约点状态</td>
        <td nowrap="nowrap"  align="center" width="8%">预约点照片</td>
      </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">
            ${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>	
            <td align="center" nowrap="nowrap">
            <a href="${ctx}/manager/admin/KonkaSpActivityAddr.do?method=view&id=${cur.id}"  style="text-decoration:underline;">
          ${cur.addr_index}
            </a>
            </td>
            <td align="left"nowrap="nowrap">${fn:escapeXml(cur.addr)}</td>
            <td align="center" nowrap="nowrap">${cur.r3_code}</td>
            <td align="center" nowrap="nowrap">${cur.customer_name}</td>
            <td align="center" nowrap="nowrap">${cur.store_name}</td>
            <td align="center" nowrap="nowrap">${cur.add_user_name}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd"/></td>
            <td align="center" nowrap="nowrap">${cur.addr_header}</td>
            <td align="center" nowrap="nowrap">
            <c:choose>
            <c:when test="${cur.state eq 0}"><font color="green">开启</font></c:when>
             <c:when test="${cur.state eq 1}"><font color="grey">关闭</font></c:when>
             <c:otherwise>未指定</c:otherwise>
            </c:choose>
            </td>
            <td align="left" nowrap="nowrap">
            <c:if test="${not empty cur.map.fj_paths}">
           <c:set var="fapiao" value="${fn:split(cur.map.fj_paths,',')}" />
          <c:forEach items="${fapiao}" var="tt" varStatus="vs1">
         
          	<a href="${ctx}/${tt}" target="_blank">&nbsp;附件${vs1.count}&nbsp;</a>
          </c:forEach>
          </c:if>
            </td>
          </tr>
        </c:forEach>
</table>
</body>
</html>
