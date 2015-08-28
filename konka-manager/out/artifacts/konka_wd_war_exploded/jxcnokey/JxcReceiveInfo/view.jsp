<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>信息接收 &gt; ${navString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：信息接收 &gt; ${navString}</div>
  <div class="rtabcont1">
    <html-el:form action="/JxcReceiveInfo">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="2" align="right">${navString}基本信息</th>
        </tr>
        <tr >
          <td width="15%" nowrap="nowrap" class="title_item">资讯标题：</td>
          <td><c:out value="${entity.title}" /></td>
        </tr>
        <!--<tr >
          <td width="15%" nowrap="nowrap" class="title_item">资讯短标题：</td>
          <td><c:out value="${entity.short_title}" /></td>
        </tr>
        --><tr >
          <td width="15%" nowrap="nowrap" class="title_item">关键字：</td>
          <td><c:out value="${entity.key_words}" /></td>
        </tr>
        <tr >
          <td nowrap="nowrap" class="title_item">作者：</td>
          <td><c:out value="${entity.author}" /></td>
        </tr>
        <tr >
          <td nowrap="nowrap" class="title_item">来源：</td>
          <td><c:out value="${entity.source}" /></td>
        </tr>
        <tr >
          <td nowrap="nowrap" class="title_item">摘要：</td>
          <td><c:out value="${entity.summary}" /></td>
        </tr>
        <c:if test="${not empty (entity.img_path)}">
          <tr>
            <td class="title_item">上传主图：</td>
            <td><img src="${ctx}/${fn:substringBefore(entity.img_path, '.')}_240.jpg" title="${entity.img_desc}" /></td>
          </tr>
          <tr>
            <td nowrap="nowrap" class="title_item">主图说明：</td>
            <td><c:out value="${entity.img_desc}" /></td>
          </tr>
        </c:if>
        <tr >
          <td nowrap="nowrap" class="title_item">内容：</td>
          <td><div id="content">${entity.content}</div></td>
        </tr>
      <c:if test="${not empty   attachmentList}">
      <tr>
        <td nowrap="nowrap" class="title_item">已上传的附件：</td>
        <td><c:forEach var="cur" items="${attachmentList}" varStatus="vs">${vs.count}、<a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}">${cur.file_name}</a><br />
          </c:forEach></td>
      </tr>
      </c:if>
       <tr>
          <td class="title_item">发布人：</td>
          <td><c:out value="${entity.add_user_name}" /></td>
        </tr>
        <tr>
          <td class="title_item">发布时间：</td>
          <td><fmt:formatDate value="${entity.pub_date}" pattern="yyyy-MM-dd" /></td>
        </tr>
        <tr>
          <td colspan="2" align="center"><html-el:button property="back" styleClass="bgButtonBack" value="返回" onclick="history.back();" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
