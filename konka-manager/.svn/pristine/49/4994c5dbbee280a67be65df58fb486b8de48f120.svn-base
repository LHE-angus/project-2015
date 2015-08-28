<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<c:set var="naviString" value="文件下载" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" /><title>${naviString}</title>
<!--[if IE]>
<link href="${ctx}/styles/manager/ieHack.css" rel="stylesheet" type="text/css" />
<![endif]-->
</head>
<body>
<div id="navTab" class="tabsPage" style="text-align:left;">
  <div class="tabsPageHeader">
    <div class="tabsPageHeaderContent">
      <ul class="navTab-tab">
        <li class="main"><a href="javascript:void(0)"><span><span class="home_icon">${naviString}</span></span></a></li>
      </ul>
    </div>
  </div>
  <div class="navTab-panel tabsPageContent">
    <div class="page">
      <div class="pageContent">
        <div style="height:10px;"></div>
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="list">
          <c:if test="${af.map.title_is_strong eq 1}" var="is_strong">
            <tr>
              <td width="12%" nowrap="nowrap" class="title_item">标题：</td>
              <td width="88%"><span style="color:${af.map.title_color}; font-weight:bold;">
                <c:out value="${af.map.title}" />
                </span></td>
            </tr>
          </c:if>
          <c:if test="${not is_strong}">
            <tr>
              <td width="12%" nowrap="nowrap" class="title_item">标题：</td>
              <td width="88%"><span style="color:${af.map.title_color}">
                <c:out value="${af.map.title}" />
                </span></td>
            </tr>
          </c:if>
          <tr>
            <td nowrap="nowrap" class="title_item">内容：</td>
            <td  height="100">${af.map.content}</td>
          </tr>
          
          <c:if test="${not empty attachmentList}">
            <tr>
              <td nowrap="nowrap" class="title_item">已上传的附件：</td>
              <td><c:forEach var="cur" items="${attachmentList}" varStatus="vs">${vs.count}、<a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}">${cur.file_name}</a><br />
                </c:forEach></td>
            </tr>
          </c:if>
          <tr>
            <td nowrap="nowrap" class="title_item">添加时间：</td>
            <td><fmt:formatDate value="${af.map.add_datetime}" pattern="yyyy-MM-dd"/></td>
          </tr>
          <c:if test="${af.map.mod_id eq 5000}">
          <tr>
            <td nowrap="nowrap" class="title_item">添加人：</td>
            <td>${af.map.map.add_user_name }</td>
          </tr>
          </c:if>
          <c:if test="${(af.map.is_use_invalid_date eq 1) and (not empty (af.map.invalid_date))}">
            <tr>
              <td nowrap="nowrap" class="title_item">失效时间：</td>
              <td><fmt:formatDate value="${af.map.invalid_date}" /></td>
            </tr>
          </c:if>
          <tr>
            <td>&nbsp;</td>
            <td><input type="button" value="返 回" class="websub" onclick="history.back();" /></td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
