<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>公告榜</title>
<link href="../../styles/themes/blue/styles/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div align="center">
  <div align="center" style="width: 65%"> <br />
    <br/>
    <div align="left"> <span style="color:#FF0000;font-weight:bold;">董事长信箱：</span><a href="mailto:zhaoj@easy-biz.com.cn"><span style="color:#FF0000;font-weight:bold;font-size:14px">zhaoj@easy-biz.com.cn</span></a></div>
    <form id="listForm" name="listForm" method="post" action="OecNotReportPerSon.do?method=delete">
      <br/>
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="datagrid">
        <c:if test="${not empty entityForNotice.content}">
          <tr>
            <th nowrap="nowrap">公告榜</th>
          </tr>
          <tr>
            <td align="left">${entityForNotice.content}
              <c:if test="${not empty (attachmentListForNotice)}"><br />
                <c:forEach var="cur" items="${attachmentListForNotice}" varStatus="vs"> 附件${vs.count}、<a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}" target="_blank">${cur.file_name}</a> </c:forEach>
              </c:if></td>
          </tr>
        </c:if>
        <c:if test="${not empty newsInfoList}">
          <tr>
            <th nowrap="nowrap" style="font-size:14px">公司重大新闻、通知及下文</th>
          </tr>
          <c:forEach var="cur" items="${newsInfoList}">
            <tr>
              <td align="left"><c:if var="hasColor" test="${not empty (cur.title_color)}"><a href="OecNewsInfo.do?method=view&amp;id=${cur.id}" target="_blank" style="color:#${cur.title_color}">${cur.title}</a></c:if>
                <c:if test="${not hasColor}"><a href="OecNewsInfo.do?method=view&amp;id=${cur.id}" target="_blank">${cur.title}</a></c:if>
                <fmt:formatDate var="_date" value="${cur.add_datetime}" pattern="[MM/dd]" />
                <fmt:formatDate var="_now" value="${now}" pattern="[MM/dd]" />
                <c:if test="${_date eq _now}">&nbsp;<img src="../../images/new.gif" style="vertical-align:middle;" /></c:if>
                <span>${_date}</span></td>
            </tr>
          </c:forEach>
        </c:if>
      </table>
    </form>
  </div>
</div>
<input type="button" name="back" id="back" value="返 回" onclick="history.back();" />
<input type="button" name="send" id="send" value="进入办公" onclick="location.href='../manager/Frames.do';" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.timers.js"></script> 
<script type="text/javascript">//<![CDATA[
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
