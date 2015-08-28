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
  <h3 align="center" ><strong class="fb">加盟信息查看</strong></h3>
  </div>
    <div class="rtabcont2">
    <html-el:form action="/admin/JoinInfoManager" enctype="multipart/form-data">
     <table width="80%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr >
        <td height="28" width="15%" nowrap="nowrap" class="title" align="right">标题：</td>
        <td><c:out value="${entity.title}" /></td>
      </tr>
      <tr>
      
        <td height="28" nowrap="nowrap" class="title_item" align="right">内容：</td>
        <td><div id="content">${entity.content}</div></td>
      </tr>
      <tr>
		<td nowrap="nowrap" class="title_item" align="right">发布方式：</td>
		<td>
			<c:if test="${af.map.article_type_id eq 0}">所有网点</c:if>
			<c:if test="${af.map.article_type_id ne 0}">选择对象发布</c:if>
		</td>
	</tr>
		<c:if test="${af.map.article_type_id eq 3}">
		<tr >
			<td nowrap="nowrap" class="title_item" align="right">发布省：</td>
			<td align="left">${af.map.province}</td>
		</tr> 
		</c:if>
		<c:if test="${af.map.article_type_id eq 1}">
		<tr >
			<td nowrap="nowrap" class="title_item" align="right">发布地区：</td>
			<td align="left">${af.map.citys }</td>
		</tr> 
		</c:if>
		<c:if test="${af.map.article_type_id eq 2}">
		<tr >
			<td nowrap="nowrap" class="title_item" align="right">发布网点：</td>
			<td align="left">${af.map.shops }</td>
		</tr>
		</c:if>
    <tr >
        <td height="28" class="title_item" align="right">添加时间：</td>
        <td><fmt:formatDate value="${entity.add_date}" pattern="yyyy-MM-dd" /></td>
      </tr>
      <tr >
        <td height="28" class="title_item" align="right">添加人用户名：</td>
        <td><c:out value="${entity.add_user_name}" /></td>
      </tr>
    </table>
    </html-el:form>
    <div>
     <br />
        <label >
            <input class="but5" type="button"  value="返回" onclick="history.back();return false;" />
          </label>
  </div>
  </div>
<div class="rtabcont3"></div>
<div class="clear"></div>
</div>
<script type="text/javascript">//<![CDATA[
	$(document).ready(function(){
		//
	});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
