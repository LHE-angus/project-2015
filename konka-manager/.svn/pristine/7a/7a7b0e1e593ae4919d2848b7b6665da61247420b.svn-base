<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
textarea {
	width: 527px;
	overflow-y: hidden;
	background: transparent;
}
.title {
	background-color:#eee;
	text-align:right;
}
span.desc {color:#848484;font-size:12px;margin-left:1em;}
div.desc {color:#848484;font-size:12px;}
-->
</style>
</head>
<body>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<div class="oarcont" style="margin-bottom: 100px;">
	<div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
 	<div class="rtabcont2"> 
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td nowrap="nowrap" class="title_item">活动类型：</td>
          <td>${fn:escapeXml(entity.hd_type)}&nbsp; </td>
          <td nowrap="nowrap" class="title_item">活动类型编码：</td>
          <td>${fn:escapeXml(entity.hd_type_sn)}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">开始时间：</td>
          <td><fmt:formatDate value="${af.map.s_date}" pattern="yyyy-MM-dd" /></td>
          <td nowrap="nowrap" class="title_item">结束时间：</td>
          <td><fmt:formatDate value="${af.map.e_date}" pattern="yyyy-MM-dd" /></td>
        </tr>
        <c:if test="${0 eq af.map.p_type}">
       <tr>
          <td nowrap="nowrap" class="title_item">活动达标金额（万元）：</td>
          <td colspan="3" align="left">${fn:escapeXml(entity.money)}</td>
        </tr>
        </c:if>
        <c:if test="${not empty mdTypePlList}">
        <tr>
          <td nowrap="nowrap" class="title_item">指定品类：</td>
          <td colspan="3">
          	<c:forEach items="${mdTypePlList}" var="cur">
          		${cur.md_name},
          	</c:forEach>
          </td>
        </tr>
        </c:if>
       <c:if test="${not empty mdTypeJxList}">
        <tr>
          <td nowrap="nowrap" class="title_item">指定机型：</td>
          <td colspan="3">
          	<c:forEach items="${mdTypeJxList}" var="cur">
          		${cur.md_name},
          	</c:forEach>
          </td>
        </tr>
        </c:if>
        <tr>
          <td colspan="4" align="center"> 
            <input type="button" class="but5" name="return" value="返回 " onclick="history.back();" /></td>
        </tr>
      </table>
  </div>
  <div class="clear"></div>
</div>

<jsp:include page="/__analytics.jsp" />
</body>
</html>