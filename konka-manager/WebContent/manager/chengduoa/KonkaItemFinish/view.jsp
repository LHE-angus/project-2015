<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<c:set var="naviString" value="康佳事项完成情况" />
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
            <td width="15%" nowrap="nowrap" class="title_item" align="right">事项内容：</td>
            <td width="85%">${fn:escapeXml(af.map.item_content)}</td>
          </tr>
          <tr>
            <td width="15%" nowrap="nowrap" class="title_item" align="right">负责人：</td>
            <td>${fn:escapeXml(af.map.receive_user_name)}</td>
          </tr>
          <tr>
            <td width="15%" nowrap="nowrap" class="title_item" align="right">计划完成时间：</td>
            <td width="85%">
              <fmt:formatDate value="${af.map.plan_finish_date}" pattern="yyyy-MM-dd" />
            </td>
          </tr>
          <tr>
            <td width="15%" nowrap="nowrap" class="title_item" align="right">计划完成百分比：</td>
            <td width="85%">${af.map.plan_finish_rate}
              <c:if test="${af.map.plan_finish_rate ne null }">%</c:if>
            </td>
          </tr>
          <tr>
            <td width="15%" nowrap="nowrap" class="title_item" align="right">是否已完成：</td>
            <td width="85%">
              <c:if test="${af.map.is_finished eq 0}">未完成</c:if>
              <c:if test="${af.map.is_finished eq 1}">已完成</c:if>
            </td>
          </tr>
          <tr>
            <td width="15%" nowrap="nowrap" class="title_item" align="right">完成情况：</td>
            <td width="85%">${fn:escapeXml(af.map.finish_status)}</td>
          </tr>
          <tr>
            <td width="15%" nowrap="nowrap" class="title_item" align="right">属性1：</td>
            <td width="85%">${fn:escapeXml(af.map.map.p_type1_name)}</td>
          </tr>
          <tr>
            <td width="15%" nowrap="nowrap" class="title_item" align="right">属性2：</td>
            <td width="85%">${fn:escapeXml(af.map.map.p_type2_name)}</td>
          </tr>
          <tr>
            <td width="15%" nowrap="nowrap" class="title_item" align="right">添加时间：</td>
            <td width="85%">
              <fmt:formatDate value="${af.map.add_time}" pattern="yyyy-MM-dd" />
            </td>
          </tr>
          <tr>
            <td width="15%" nowrap="nowrap" class="title_item" align="right">更新时间：</td>
            <td width="85%">
              <fmt:formatDate value="${af.map.last_update_time}" pattern="yyyy-MM-dd" />
            </td>
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
