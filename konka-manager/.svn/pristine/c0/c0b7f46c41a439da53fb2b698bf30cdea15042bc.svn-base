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
    <h3 align="center" ><strong class="fb">查看培训信息</strong></h3>
  </div>
  <div class="rtabcont2">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr>
        <td  colspan="2" class="item_class" align="right"><strong class="fb">基本信息</strong></td>
      </tr>
      <tr >
        <td height="28" width="15%" nowrap="nowrap" class="title">标题：</td>
        <td><c:out value="${af.map.title}" /></td>
      </tr>
      <tr >
        <td height="28" nowrap="nowrap" class="title_item">来源：</td>
        <td><c:out value="${af.map.source}" /></td>
      </tr>
       <tr >
        <td height="28" nowrap="nowrap" class="title_item">发文人：</td>
        <td><c:out value="${af.map.author}" /></td>
      </tr>
      <c:if test="${not empty (af.map.img_path)}">
        <tr>
          <td  class="title_item">上传主图：</td>
          <td><img src="${ctx}/${fn:substringBefore(af.map.img_path, '.')}_240.jpg" title="${entity.img_desc}" /></td>
        </tr>
        <tr>
          <td  nowrap="nowrap" class="title_item">主图说明：</td>
          <td><c:out value="${af.map.img_desc}" /></td>
        </tr>
      </c:if>
      <tr >
        <td height="28" nowrap="nowrap" class="title_item">内容：</td>
        <td><div id="content">${af.map.content}</div></td>
      </tr>
      <tr >
        <c:if test="${not empty  attachmentList}">
      <tr>
        <td height="28" width="15%" nowrap="nowrap" class="title">已上传的附件：</td>
        <td><c:forEach var="cur" items="${attachmentList}" varStatus="vs">${vs.count}、<a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}">${cur.file_name}</a><br />
          </c:forEach></td>
      </tr>
      </c:if>
      </tr>
      <tr >
        <td height="28" class="title_item">是否启用外链：</td>
        <td><c:choose>
            <c:when test="${af.map.is_link_out eq 1}"> 是</c:when>
            <c:when test="${af.map.is_link_out eq 0}"> 否 </c:when>
          </c:choose></td>
      </tr>
      <c:if test="${af.map.is_link_out eq 1}">
        <tr>
          <td  class="title_item">外链URL：</td>
          <td><a href="${fn:escapeXml(af.map.link_out_addr)}" target="blank">${fn:escapeXml(entity.link_out_addr)}</a></td>
        </tr>
      </c:if>
      <tr >
        <td height="28" class="title_item">浏览次数：</td>
        <td><c:out value="${af.map.view_counts}" /></td>
      </tr>
      <tr>
        <td height="28" class="title_item">发布时间：</td>
        <td><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd" /></td>
      </tr>
      <tr>
        <td  nowrap="nowrap" class="title_item">信息状态：</td>
        <td><c:choose>
            <c:when test="${af.map.states eq 0}">未发布</c:when>
            <c:when test="${af.map.states eq 1}">已发布</c:when>
          </c:choose></td>
      </tr>
      <tr >
        <td height="28" nowrap="nowrap" class="title_item">排序值：</td>
        <td><c:out value="${af.map.order_value}" /></td>
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
