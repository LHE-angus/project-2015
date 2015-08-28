<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳进销存 &gt; 系统信息</title>
<style type="text/css">
<!--
body{ margin:0px; padding:0px;background:url(${ctx}/styles/jxc/images/jxcnewsbg.jpg) no-repeat}
ul,li{ margin:0px; padding:0px; font-size:12px; list-style:none;}
.news li{line-height:26px; padding-left:10px; text-align:left; color:#27393e;}

.yellow a,.yellow a:visited{ color:#F60; text-decoration:none;}
.yellow a:hover{ color:#000; text-decoration:underline;}
.gran a,.gran a:visited{ color:#27393e; text-decoration:none;}
.gran a:hover{ color:#000; text-decoration:underline;}
h1{ font-size:12px; font-weight:bold; margin:0px; padding:0px; padding-left:10px;}
-->
</style>
</head>
<body>
<table width="234" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="230" valign="top" style="padding-top:30px;">
     <h1>系统信息</h1>
      <ul class="news">
        <c:forEach var="cur" items="${konkaPeArticleInfoList}" varStatus="vs" begin="0" end="4">
          <c:url var="url" value="/jxc/KonkaJxcNewsInfo.do?method=view&id=${cur.id}" />
          <li class="gran">
            <a href="${url}" target="_blank" title="${fn:escapeXml(cur.title)}">${fn:escapeXml(fnx:abbreviate(cur.title, 2 * 16, "..."))}</a>
          </li>
          <c:if test="${vs.last eq true}">
            <c:set var="i" value="${vs.count}" />
          </c:if>
        </c:forEach>
        <c:forEach begin="${i}" end="${rows}"> </c:forEach>
      </ul>
    </td>
  </tr>
</table>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
