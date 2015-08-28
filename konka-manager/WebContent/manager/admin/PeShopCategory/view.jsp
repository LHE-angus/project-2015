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
  <h3 align="center" ><strong class="fb">网点类别查看</strong></h3>
  </div>
    <div class="rtabcont2">
     <table width="80%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
     <c:if test="${0 ne entity.category_pid}">
      <tr >
        <td height="28" width="15%" nowrap="nowrap" class="title_item">网点父类别名称：</td>
        <td><c:out value="${category_pName}" /></td>
      </tr>
     </c:if>
      <tr >
        <td height="28" width="15%" nowrap="nowrap" class="title_item">网点类别名称：</td>
        <td><c:out value="${entity.category_name}" /></td>
      </tr>
        <c:if test="${not empty entity.peShopCategoryList}">
      <tr >
        <td height="28" width="15%" nowrap="nowrap" class="title_item">网点子类别名称：</td>
        <td><c:forEach var="cur" items="${entity.peShopCategoryList}" >
                <div>${fn:escapeXml(cur.category_name)}</div>
                </c:forEach>
        </td>
      </tr>
     </c:if>
      <tr >
        <td height="28" width="15%" nowrap="nowrap" class="title_item">网点类别描述：</td>
        <td><c:out value="${entity.category_desc}" /></td>
      </tr>
       <tr >
        <td height="28" width="15%" nowrap="nowrap" class="title_item">事业部名称：</td>
        <td><c:out value="${entity.map.dept_name}" /></td>
      </tr>
      <tr >
        <td height="28" nowrap="nowrap" class="title_item">添加人：</td>
        <td><c:out value="${entity.map.user_name}" /></td>
      </tr>
      <tr >
        <td height="28" class="title_item">添加时间：</td>
        <td><fmt:formatDate value="${entity.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
      </tr>
       <tr >
        <td height="28" width="15%" nowrap="nowrap" class="title_item">删除状态：</td>
        <td><c:choose>
            <c:when test="${entity.is_del eq 0}">未删除</c:when>
            <c:when test="${entity.is_del eq 1}">已删除</c:when>
          </c:choose>
        </td>
      </tr>
    </table>
    <div>
     <br />
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
