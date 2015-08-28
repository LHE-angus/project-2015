<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<META http-equiv=Expires content=0>
<META http-equiv=Cache-Control content=no-cache>
<META http-equiv=Pragma content=no-cache>
<title>康佳电器</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/mobile/css/common.css" />
</head>
<body>
  <table width="100%" border="0"  class="rtab2">
    <tr>
      <th colspan="2" width="90%" align="left" class="bno" id="filetitle">${af.map.title}</th>
    </tr>
    <tr>
      <td colspan="2" width="90%" align="left">文件编号：${af.map.file_no}</td>
    </tr>
    <tr>
      <td colspan="2" width="90%" align="left" class="bno">拟稿人：${af.map.draft_man}</td>
    </tr>
    <tr>
      <td colspan="2" width="90%" align="left">文件类型：
	     <c:choose>
              <c:when test="${af.map.doc_type eq 0}">公告信息</c:when>
              <c:when test="${af.map.doc_type eq 1}">公文下发</c:when>
         </c:choose>
      </td>
    </tr>
    <tr>
      <td colspan="2" width="90%" align="left" style="border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #d5c7c9;">下发时间：
        <fmt:formatDate value="${af.map.add_time}" pattern="yyyy-MM-dd hh:mm" /></td>
    </tr>
    <tr>
      <td align="left">内容：<br />
        <label for="textarea"></label>
        ${af.map.content}
        <c:if test="${!empty attachmentList}"> 
        	<br />
        	<c:forEach var="cur" items="${attachmentList}" varStatus="vs">
        		<span style="font-size: 12px;color: blue;">${vs.count}、<a style="font-size: 12px;color: blue;" href="OaFiles.do?method=downloadFile&save_name=${cur.save_name}" target="_blank">${cur.file_name}</a> <br />
              </span></c:forEach>
        </c:if></td>
    </tr>
  </table>
<script type="text/javascript">
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>