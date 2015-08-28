<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
      <tr>
        <td width="15%" nowrap="nowrap" class="title_item" align="right">通知公告标题：</td>
        <td width="85%">${af.map.notice_title}</td>
      </tr>
      <tr>
        <td width="15%" nowrap="nowrap" class="title_item" align="right">通知公告内容：</td>
        <td width="85%">${af.map.content}</td>
      </tr>
      <tr>
        <td width="15%" nowrap="nowrap" class="title_item" align="right">通知添加人：</td>
        <td width="85%">${af.map.map.add_user_name}</td>
      </tr>
      <tr>
        <td width="15%" nowrap="nowrap" class="title_item" align="right">通知添加时间：</td>
        <td width="85%"><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
      </tr>
      <tr>
        <td width="15%" nowrap="nowrap" class="title_item" align="right">是否发布：</td>
        <td width="85%"><c:choose>
            	<c:when test="${af.map.is_public eq 0}">未发布</c:when>
            	<c:when test="${af.map.is_public eq 1}">已发布</c:when>
            </c:choose></td>
      </tr>
      <c:if test="${af.map.is_public eq 1}">
       <tr>
        <td width="15%" nowrap="nowrap" class="title_item" align="right">发布人：</td>
        <td width="85%">${af.map.map.public_user_name}</td>
      </tr>
      <tr>
        <td width="15%" nowrap="nowrap" class="title_item" align="right">发布时间：</td>
        <td width="85%"><fmt:formatDate value="${af.map.public_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
      </tr>
      </c:if>
      <tr>
        <td>&nbsp;&nbsp;</td>
        <td><input class="but5" type="button" name="Submit5" value="返回" onclick="history.back()" /></td>
      </tr>
    </table>
  </div>
</div>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
