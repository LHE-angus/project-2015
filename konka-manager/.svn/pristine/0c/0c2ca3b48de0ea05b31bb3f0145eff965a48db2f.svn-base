<%@ page language="java" contentType="application/vnd.ms-excel;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
</head>
<body>
<div id="body_oarcont">
  <div>
    <table width="100%" border="1" cellspacing="0" cellpadding="0">
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="5%">序号</td>
        <td nowrap="nowrap" align="center" width="10%">客户/促销员</td>
        <td nowrap="nowrap"  align="center" width="8%">变更类型</td>
        <td nowrap="nowrap"  align="center" width="10%">操作人</td>
        <td nowrap="nowrap"  align="center" width="8%">岗位ID</td>
        <td nowrap="nowrap"  align="center" width="12%">操作时间</td>
        <td nowrap="nowrap" align="center">变更内容</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td>${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td><td>${fn:escapeXml(cur.ss_name)}</td>
            <td><c:choose>
            	<c:when test="${cur.c_type eq 10}">促销员门店变更</c:when>
            	<c:when test="${cur.c_type eq 20}">促销员门店变更</c:when>
            	<c:when test="${cur.c_type eq 30}">门店启用停用</c:when>
            	<c:when test="${cur.c_type eq 40}">客户启用停用</c:when>
            	<c:when test="${cur.c_type eq 50}">促销员启用停用</c:when>
            	<c:when test="${cur.c_type eq 60}">岗位人员启用停用</c:when>
            	<c:when test="${cur.c_type eq 70}">角色变更</c:when>
            	<c:otherwise>客户业务员变更</c:otherwise>
            </c:choose></td><td>${fn:escapeXml(cur.add_user_name)}</td><td>${fn:escapeXml(cur.add_user_job_id)}</td><td><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm" /></td><td>${fn:escapeXml(cur.change_info)}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</div>
</body>
</html>
