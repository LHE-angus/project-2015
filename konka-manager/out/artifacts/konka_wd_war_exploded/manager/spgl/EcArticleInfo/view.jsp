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
        <td nowrap="nowrap" class="title_item" align="right">添加时间：</td>
        <td><fmt:formatDate value="${af.map.add_date}" pattern="yyyy年MM月dd日 HH:mm:ss" /></td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item" align="right">所属组织：</td>
        <td>${af.map.group_name}</td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item" align="right">所属系统：</td>
        <td>
        <c:if test="${af.map.own_sys eq 1}">工卡</c:if>
        <c:if test="${af.map.own_sys eq 2}">触网</c:if>
        </td>
      </tr>
       <tr>
        <td nowrap="nowrap" class="title_item" align="right">总部/分公司：</td>
        <td>
        <c:if test="${af.map.plat_sys eq 0}">总部</c:if>
        <c:if test="${af.map.plat_sys eq 1}">分公司</c:if>
        </td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item" align="right">内容：</td>
        <td>${af.map.content}</td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item" align="right">添加人：</td>
        <td>${fn:escapeXml(af.map.user_name)}</td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item" align="right">信息状态：</td>
        <td><c:choose>
            <c:when test="${af.map.state eq 0}">未发布</c:when>
            <c:when test="${af.map.state eq 1}">已发布</c:when>
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
