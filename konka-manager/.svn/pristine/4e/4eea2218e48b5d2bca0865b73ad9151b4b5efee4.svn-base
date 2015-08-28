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
<div id="body_oarcont">
  <div>
      <table width="100%" border="1" cellspacing="0" cellpadding="0">
          <tr>
            <td width="30" nowrap="nowrap" >序号</td>
            <td nowrap="nowrap" >岗位ID</td>
            <td nowrap="nowrap" >登录名</td>
            <td nowrap="nowrap" >在岗人员</td>
             <td nowrap="nowrap" align="center">R3人员编号</td>
            <td nowrap="nowrap">部门</td>
            <td nowrap="nowrap" >职务</td>
            <td nowrap="nowrap" >类型</td>
            <td nowrap="nowrap" align="center">手机/电话</td>
            <td nowrap="nowrap" align="center">添加日期</td>
             <td nowrap="nowrap" align="center">登录次数</td>
          </tr>
          <c:forEach var="cur" items="${allList}" varStatus="vs">
            <tr>
              <td>${vs.count}</td><td><c:out value="${cur.job_id}" /></td>
              <td><c:out value="${cur.user_name}" /></td>
              <td>${cur.real_name}</td>
              <td><c:out value="${cur.r3_job_id}" /></td>
              <td><c:out value="${cur.map.full_dept_name}" /></td>
              <td><c:out value="${cur.map.role_name}" /></td>
              <td><c:if test="${cur.map.sales_type eq 1 }">兼职</c:if>
              	<c:if test="${cur.map.sales_type eq 2 }">全职</c:if></td>
              <td><c:out value="${not empty cur.link_phone ? cur.link_phone : (not empty cur.link_tel ? cur.link_tel : '')}" /></td>
              <td><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
              <td><c:out value="${cur.login_count}" /></td>
            </tr>
          </c:forEach>
      </table>
  </div>
</div>
</body>
</html>
