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
        <td>当前位置：意见反馈管理</td>
      </tr>
    </table>
  </div>
  <div> </div>
  <div class="rtabcont2">
    <table width="80%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr>
        <td width="12%" nowrap="nowrap" class="title_item" align="right">反馈类别：</td>
        <td width="88%" align="left"><c:out value="${entity.map.c_name}" /></td>
      </tr>
      <tr>
        <td width="12%" nowrap="nowrap" class="title_item" align="right">内容：</td>
        <td width="88%" align="left"><c:out value="${af.map.content}" /></td>
      </tr>
      <tr>
        <td width="12%" nowrap="nowrap" class="title_item" align="right">提交人：</td>
        <c:if test="${entity.map.subcomp_name ne null && entity.map.office_name ne null}">
          <td width="88%" align="left"><c:out value="${entity.map.subcomp_name }--${entity.map.office_name }--${af.map.question_person}" /></td>
        </c:if>
        <c:if test="${entity.map.subcomp_name eq null || entity.map.office_name eq null}">
          <td width="88%" align="left"><c:out value="${af.map.question_person}" /></td>
        </c:if>
      </tr>
      <tr>
        <td width="12%" nowrap="nowrap" class="title_item" align="right">提交时间：</td>
        <td width="88%" align="left"><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
      </tr>
      <tr>
        <td width="12%" nowrap="nowrap" class="title_item" align="right">回复：</td>
        <td width="88%" align="left"><c:forEach  var="cur" items="${konkaMobileTerminalFbBackList}" varStatus="vs"> 回复${vs.count}: ${cur.content}
            <div style="margin-left:250px;"> ----
              <c:if test="${not empty cur.map.subcomp_name}">${cur.map.subcomp_name}--</c:if>
              ${cur.map.subcomp_name}
              <c:if test="${not empty cur.map.office_name}">${cur.map.office_name}--</c:if>
              ${cur.question_person }
              &nbsp;&nbsp;
              <fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd"/>
            </div>
          </c:forEach></td>
      </tr>
    </table>
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
