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
    <h3 align="center" ><strong class="fb">文件下发查看</strong></h3>
  </div>
  <div class="rtabcont2">
      <div style="height:10px;"></div>
      <table width="80%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr>
          <td class="title_item" nowrap="nowrap">文件编号：</td>
          <td width="88%" >${af.map.file_no} </td>
        </tr>
        <tr>
          <td class="title_item">文件标题：</td>
          <td>${af.map.file_title}</td>
        </tr>
        <tr>
          <td class="title_item">文件类别：</td>
          <td><c:choose>
                  <c:when test="${af.map.file_type eq 10}">3C证书</c:when>
                  <c:when test="${af.map.file_type eq 20}">获奖证书</c:when>
                  <c:when test="${af.map.file_type eq 30}">检测报告</c:when>
                  <c:when test="${af.map.file_type eq 40}">产品参数</c:when>
                  <c:when test="${af.map.file_type eq 50}">库存</c:when>
                  <c:when test="${af.map.file_type eq 60}">生产计划</c:when>
                  <c:when test="${af.map.file_type eq 80}">节能证书</c:when>
                  <c:when test="${af.map.file_type eq 90}">表格模版</c:when>
                  <c:when test="${af.map.file_type eq 100}">软件升级资料</c:when>
                  <c:when test="${af.map.file_type eq 70}">其他</c:when>
               </c:choose>
          </td>
        </tr>
        <tr>
          <td class="title_item">下发对象：</td>
          <td>${fn:escapeXml(af.map.user_names)}
          </td>
        </tr>
        <c:if test="${not empty attachmentList }">
        <tr id="trFile">
          <td class="title_item">文件下载：</td>
          <td><c:forEach var="cur" items="${attachmentList}" varStatus="vs"><span> <a href="${ctx}/${cur.save_path}">${cur.file_name}</a>
              <!-- <a href='<c:url value="DocIssue.do?method=open&id=${af.map.id}" />'>${cur.file_name}</a> -->
              <br />
              </span></c:forEach></td>
        </tr>
        </c:if>
        <tr>
          <td height="200" valign="top" class="title_item">公件内容：</td>
          <td valign="top">${file_content}</td>
        </tr>
        <tr>
          <td class="title_item">下发人：</td>
          <td>${af.map.add_user_name}</td>
        </tr>
        <tr>
          <td class="title_item">下发时间：</td>
          <td><fmt:formatDate value="${af.map.add_time}" pattern="yyyy-MM-dd HH:mm" /></td>
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
