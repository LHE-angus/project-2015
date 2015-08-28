<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
 <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
<div class="oarcont" align="center">
 	<div class="rtabcont2">
    <table width="100%" border="0" cellspacing="0" cellpadding="1" class="rtable3">
      <tr>
        <td colspan="2"><strong>${fn:escapeXml(af.map.title)}</strong></td>
      </tr>
      <tr>
        <td width="15%" nowrap="nowrap" class="title_item" align="right">标题：</td>
        <td width="85%"><c:if var="colorIsEmpty" test="${empty (af.map.title_color)}">${fn:escapeXml(af.map.title)}</c:if>
          <c:if test="${not colorIsEmpty}"><span style="color:#${af.map.title_color}">${fn:escapeXml(af.map.title)}</span></c:if></td>
      </tr>
      <c:if test="${not empty af.map.image_path}">
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">主图：</td>
          <td><img src="${ctx}/${fn:substringBefore(af.map.image_path, '.')}_400.jpg" title="${af.map.image_desc}" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">主图说明：</td>
          <td>${fn:escapeXml(af.map.image_desc)}</td>
        </tr>
      </c:if>
      <tr>
        <td nowrap="nowrap" class="title_item" align="right">发布时间：</td>
        <td><fmt:formatDate value="${af.map.pub_date}" pattern="yyyy年MM月dd日 HH:mm:ss" /></td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item" align="right">失效时间：</td>
        <td><fmt:formatDate value="${af.map.invalid_date}" pattern="yyyy年MM月dd日 HH:mm:ss" /></td>
      </tr>
      <c:if test="${not empty af.map.modify_date}">
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">最后修改时间：</td>
          <td><fmt:formatDate value="${af.map.modify_date}" pattern="yyyy-MM-dd" /></td>
        </tr>
      </c:if>
      <tr>
        <td nowrap="nowrap" class="title_item" align="right">浏览次数：</td>
        <td>${af.map.view_count}</td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item" align="right">发布人：</td>
        <td>${fn:escapeXml(af.map.pub_user_name)}</td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item" align="right">排序号：</td>
        <td>${af.map.order_value}</td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item" align="right">信息状态：</td>
        <td><c:choose>
            <c:when test="${af.map.info_state eq 0}">关闭</c:when>
            <c:when test="${af.map.info_state eq 1}">发布</c:when>
          </c:choose></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><input type="button" class="but5" value="返回 " onclick="history.back();" /></td>
      </tr>
    </table>
  </div>
</div>

<jsp:include page="/__analytics.jsp" />
</body>
</html>
