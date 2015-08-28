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
      <th colspan="2" width="90%" align="left" class="bno" id="filetitle">${af.map.file_title}</th>
    </tr>
    <tr>
      <td colspan="2" width="90%" align="left">申请人：${af.map.submit_user}</td>
    </tr>
    <tr>
      <td colspan="2" width="90%" align="left" class="bno">负责人：${af.map.apply_user_name}</td>
    </tr>
    <tr>
      <td colspan="2" width="90%" align="left">电话：${af.map.apply_user_tel}</td>
    </tr>
    <tr>
      <td colspan="2" width="90%" align="left" style="border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #d5c7c9;">时间：
        <fmt:formatDate value="${af.map.submit_datetime}" pattern="yyyy-MM-dd hh:mm" /></td>
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
    <c:if test="${af.map.file_type eq 1}">
      <tr>
        <td colspan="2" width="90%" align="left">文件类型：费用申请</td>
      </tr>
      <tr>
        <td colspan="2" width="90%" align="left"><table width="100%" border="0" >
            <tr>
              <td width="60%" align="left">费用类别</td>
              <td width="20%" align="left">数量</td>
              <td width="20%" align="left">金额</td>
            </tr>
            <c:forEach items="${filesPropertyList}" var="cur" varStatus="vs">
              <tr>
                <td align="left">${cur.map.c_name}</td>
                <td align="left">${cur.amount}</td>
                <td align="left">${cur.cost}</td>
              </tr>
            </c:forEach>
          </table></td>
      </tr>
      <tr>
        <td colspan="2" width="90%" align="left"  style="border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #d5c7c9;">费用总额：${af.map.column_6}</td>
      </tr>
    </c:if>
    <c:if test="${af.map.file_type ne 1}">
      <tr>
        <td colspan="2" width="90%" align="left">文件类型：文件提交</td>
      </tr>
    </c:if>
    <c:if test="${not empty filesAuditNodeList}">
    	<tr>
        <td colspan="2" width="90%" align="left"><table width="100%" border="0" >
            <tr>
              <td width="15%" align="left">状态</td>
              <td width="20%" align="left">时间</td>
              <td align="left">意见</td>
              <td width="20%" align="left">审批人</td>
            </tr>
            <c:forEach var="cur" items="${filesAuditNodeList}" varStatus="vs">
              <c:set var="i" value="${vs.count}" />
          	  <c:if test="${cur.audit_result eq 2}">
            		<c:set var="audit_result" value="同意" />
          	  </c:if>
	          <c:if test="${cur.audit_result eq 1}">
	            <c:set var="audit_result" value="驳回" />
	          </c:if>
	          <c:if test="${cur.audit_result eq -1}">
	            <c:set var="audit_result" value="转发" />
	          </c:if>
             <tr>
              <td align="left" valign="top">${audit_result}</td>
              <td align="left" valign="top" nowrap="nowrap"><fmt:formatDate value="${cur.audit_datetime}" pattern="yyyy-MM-dd"/>
                <c:set var="begin_time" value="${cur.audit_datetime}" /></td>
              <td align="left">${fn:escapeXml(cur.audit_comment)}<c:if test="${empty cur.audit_comment}">无</c:if></td>
              <td align="left" valign="top">${cur.audit_user}</td>
            </tr>
            </c:forEach>
          </table></td>
      </tr>
    </c:if>
  </table>
<script type="text/javascript">
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>