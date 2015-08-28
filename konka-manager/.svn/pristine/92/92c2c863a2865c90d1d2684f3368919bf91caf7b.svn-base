<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${entity.map.c_name}</title>
<style>
body { font-family: 宋体; font-size:12px;}
</style>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="5">
      <tr>
        <td nowrap="nowrap" class="title_item" align="right">反馈类别：</td>
        <td align="left"><c:out value="${entity.map.c_name}" /></td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item" align="right">内容：</td>
        <td align="left"><c:out value="${af.map.content}" /></td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item" align="right">提交人：</td>
        <c:if test="${entity.map.subcomp_name ne null && entity.map.office_name ne null}">
          <td align="left"><c:out value="${entity.map.subcomp_name }--${entity.map.office_name }--${af.map.question_person}" /></td>
        </c:if>
        <c:if test="${entity.map.subcomp_name eq null || entity.map.office_name eq null}">
          <td align="left"><c:out value="${af.map.question_person}" /></td>
        </c:if>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item" align="right">提交时间：</td>
        <td align="left"><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
      </tr>
      <c:forEach  var="cur" items="${konkaMobileTerminalFbBackList}" varStatus="vs">
	      <tr>
	        <td nowrap="nowrap" class="title_item" align="right" valign="top">回复${vs.count}：</td>
	        <td align="left" valign="top">${cur.content}<br />
	            <span style="margin-left:10%;"> ----
	              <c:if test="${not empty cur.map.subcomp_name}">${cur.map.subcomp_name}--</c:if>
	              ${cur.map.subcomp_name}
	              <c:if test="${not empty cur.map.office_name}">${cur.map.office_name}--</c:if>
	              ${cur.question_person }
	              &nbsp;&nbsp;
	              <fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd"/>
	            </span></td>
	      </tr>
      </c:forEach>
    </table>
<div>
 <input class="but5" type="button"  value="返回" onclick="history.back();return false;" />
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
