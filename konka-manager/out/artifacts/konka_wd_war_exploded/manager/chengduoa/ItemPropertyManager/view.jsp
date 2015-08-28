<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<c:set var="naviString" value="事项属性维护" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
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
        <table width="100%" class="list">
          <tr>
            <td width="15%" nowrap="nowrap" class="title_item" align="right">属性名称：</td>
            <td width="85%">
              <c:out value="${af.map.p_name}" />
            </td>
          </tr>
          <tr>
            <td width="15%" nowrap="nowrap" class="title_item" align="right">属性类型：</td>
            <td>
              <c:if test="${af.map.p_type eq '0'}">属性1</c:if>
              <c:if test="${af.map.p_type eq '1'}">属性2</c:if>
            </td>
          </tr>
          <tr>
            <td width="15%" nowrap="nowrap" class="title_item" align="right">排序值：</td>
            <td>${af.map.order_value}</td>
          </tr>
          <tr>
            <td width="15%" nowrap="nowrap" class="title_item" align="right">添加时间：</td>
            <td>
              <fmt:formatDate value="${af.map.add_time}" pattern="yyyy-MM-dd"/>
            </td>
          </tr>
          <tr>
            <td width="15%" nowrap="nowrap" class="title_item" align="right">添加人：</td>
            <td>${af.map.add_user_name }</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>
              <input type="button" value="返 回" class="websub" onclick="history.back();" />
            </td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
