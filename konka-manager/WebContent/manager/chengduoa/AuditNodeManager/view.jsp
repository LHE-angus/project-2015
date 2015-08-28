<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div>
    <h3 align="center" ><strong class="fb">流程管理查看</strong></h3>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/chengduoa/AuditNodeManager" >
      <html-el:hidden property="queryString" styleId="queryString" />
      <html-el:hidden property="method" styleId="method" value="view" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="id" styleId="id" />
      <div style="height:10px;"></div>
      <table width="80%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr>
          <td class="title_item" nowrap="nowrap">流程名称：</td>
          <td width="88%" >${audit_node_name} </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap">审批流程：</td>
          <td width="88%" >${audit_node_name} </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap">流程类别：</td>
          <td width="88%"><c:choose>
              	<c:when test="${node_type eq 0}">文件审批</c:when>
              	<c:when test="${node_type eq 1}">费用申请</c:when>
              	<c:when test="${node_type eq 2}">请假申请</c:when>
            </c:choose></td>
        </tr>
        <tr>
          <td class="title_item">审批节点：</td>
          <c:if test="${not empty filesAuditNodeList }">
          <td>
          <c:forEach var="cur" items="${filesAuditNodeList}" varStatus="vs">
          <div>
                         第${vs.count}审批人：${cur.audit_user }
             </div>
          </c:forEach>
          </td>
          </c:if>
        </tr>
      </table>
    </html-el:form>
    <div> <br />
      <label >
      <input class="but5" type="button"  value="返回" onclick="history.back();return false;" />
      </label>
    </div>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
