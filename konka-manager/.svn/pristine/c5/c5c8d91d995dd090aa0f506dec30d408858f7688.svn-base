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
        <c:if test="${ 993000 eq af.map.mod_id }"><td>当前位置：行政办公系统&nbsp;&gt;&nbsp;公告通知</td></c:if>
        <c:if test="${ 992000 eq af.map.mod_id }"><td>当前位置：行政办公系统&nbsp;&gt;&nbsp;下发文件</td></c:if>
        <c:if test="${ 996000 eq af.map.mod_id }"><td>当前位置：行政办公系统&nbsp;&gt;&nbsp;文件归档</td></c:if>
      </tr>
    </table>
  </div>
  <div>
    <h3 align="center" ><strong class="fb">公告通知查看</strong></h3>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/oa/KonkaOaNotice" enctype="multipart/form-data">
      <html-el:hidden property="queryString" styleId="queryString" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="id" styleId="id" />
      <div style="height:10px;"></div>
      <table width="80%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr>
          <td class="title_item" nowrap="nowrap">文件编号：</td>
          <td width="88%" >${af.map.file_no} </td>
        </tr>
        <tr>
          <td class="title_item">文件标题：</td>
          <td>${af.map.title}</td>
        </tr>
        <tr>
          <td class="title_item">下发范围：</td>
          <td><c:choose>
              <c:when test="${af.map.receive_type eq 0}">全部用户</c:when>
              <c:when test="${af.map.receive_type eq 1}"><c:if test="${not empty af.map.user_names}">下发用户：${af.map.user_names}<br /></c:if>
               <c:if test="${not empty af.map.dept_names}">下发部门：${af.map.dept_names}</c:if></c:when>
            </c:choose>
          </td>
        </tr>
        <c:if test="${not empty attachmentList }">
        <tr id="trFile">
          <td class="title_item">文件上传：</td>
          <td><c:forEach var="cur" items="${attachmentList}" varStatus="vs"><span> <a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}">${cur.file_name}</a>
              <!-- <a href='<c:url value="DocIssue.do?method=open&id=${af.map.id}" />'>${cur.file_name}</a> -->
              <br />
              </span></c:forEach></td>
        </tr>
        </c:if>
        <tr>
          <td height="200" valign="top" class="title_item">公文内容：</td>
          <td valign="top">${af.map.content}</td>
        </tr>
        <tr>
          <td class="title_item">拟稿人：</td>
          <td>${af.map.draft_man}</td>
        </tr>
      </table>
    </html-el:form>
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
