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
  <div class="rtabcont1" style="overflow-x:auto;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="8%" nowrap="nowrap">文件编号</td>
          <td nowrap="nowrap">文件标题</td>
          <td width="12%" nowrap="nowrap" align="center">客户名称</td>
          <td width="8%" nowrap="nowrap" align="center" >R3编码</td>
          <td width="8%" nowrap="nowrap" align="center">费用总额</td>
          <td width="10%"nowrap="nowrap" align="center">申请部门</td>
          <td width="8%"nowrap="nowrap" align="center">申请人</td>
          <td width="10%" nowrap="nowrap" align="center">申请时间</td>
          <td width="8%" nowrap="nowrap" align="center">审批时间</td>
          <td width="8%" nowrap="nowrap" align="center">当前审批人</td>
          <td width="6%" nowrap="nowrap" align="center">文件状态</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${allList}" varStatus="vs">
            <tr>
              <td align="left"  nowrap="nowrap">${fn:escapeXml(cur.file_no)}</td>
              <td align="left"  nowrap="nowrap"><span style="cursor:pointer;" class="fblue" onclick="view_and_print(${cur.id});">${fn:escapeXml(cur.file_title)}</span></td>
              <td nowrap="nowrap" align="left" title="${cur.map.r3_shop_name}">${fn:substring(cur.map.r3_shop_name,0,12)}<c:if test="${empty cur.map.r3_shop_name}">无</c:if></td>
              <td nowrap="nowrap" align="left">${cur.map.r3_code}<c:if test="${empty cur.map.r3_code}">无</c:if></td>
              <td nowrap="nowrap" align="right"><fmt:formatNumber pattern="0.00" value="${cur.map.column_6}" /></td>
              <td nowrap="nowrap" align="center">${cur.submit_dept}</td>
              <td nowrap="nowrap" align="center">${cur.submit_user}</td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.submit_datetime}" pattern="yyyy-MM-dd HH:mm" /></td>
              <td nowrap="nowrap" align="center">${cur.map.archive_datetime}</td>
              <td align="center">${fn:escapeXml(cur.map.cur_audit_user_name)}</td>
              <td align="center" nowrap="nowrap"><c:choose>
                  <c:when test="${cur.file_status eq 0}">未提交</c:when>
                  <c:when test="${cur.file_status eq 1}">审批中</c:when>
                  <c:when test="${cur.file_status eq 2}">已审批</c:when>
                </c:choose></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
  </div>
</body>
</html>
