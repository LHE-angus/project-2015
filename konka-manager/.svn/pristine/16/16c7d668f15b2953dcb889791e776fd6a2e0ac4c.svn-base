<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>mainFrame</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" align="left">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：系统首页</td>
      </tr>
    </table>
  </div>
  <!-- 下发文件 -->
  <c:if test="${not empty entityList}"> <br/>
    <div> &nbsp;&nbsp;<span style="font-size:18px;color:red;font-weight:bold;">待办事项</span> <span style="font-size:15px;color:green;cursor:pointer;font-weight:bold;align:right;" onclick="javascript:window.location.href='${ctx}/manager/oa/SelfEventCenter.do?method=list&mod_id=991000'">更多</span></div>
    <div class="rtabcont1">
      <html-el:form action="/oa/SelfEventCenter">
        <html-el:hidden property="method" value="list" />
        <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      </html-el:form>
    </div>
    <div class="rtabcont1">
      <%@ include file="/commons/pages/messages.jsp" %>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="30" nowrap="nowrap" align="center">序号</td>
          <td width="55" nowrap="nowrap" align="center">事务类型</td>
          <td nowrap="nowrap">标题</td>
          <td width="150" nowrap="nowrap">信息来源</td>
          <td width="100" align="center" nowrap="nowrap">发起时间</td>
          <td width="40" align="center" nowrap="nowrap">操作</td>
        </tr>
        <tbody>
          <c:set var="ids" value="0" />
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr id="tr_${cur.id}">
              <td align="center" nowrap="nowrap" valign="top" style="table-layout:fixed;word-break:break-all;word-wrap:break-word;">${cur.sequence}</td>
              <td align="center" valign="top" style="table-layout:fixed;word-break:break-all;word-wrap:break-word;">${cur.eventType}</td>
              <td align="left" valign="top" style="table-layout:fixed;word-break:break-all;word-wrap:break-word;">${cur.eventiltle}</td>
              <td valign="top" style="table-layout:fixed;word-break:break-all;word-wrap:break-word;">${cur.fromPerson}</td>
              <td align="center" nowrap="nowrap" valign="top" style="table-layout:fixed;word-break:break-all;word-wrap:break-word;"><fmt:formatDate value="${cur.enterDate}" pattern="yyyy-MM-dd HH:mm" /></td>
              <td align="center" nowrap="nowrap" valign="top" style="table-layout:fixed;word-break:break-all;word-wrap:break-word;">${cur.eventDo}</td>
            </tr>
            <c:if test="${vs.last eq true}">
              <c:set var="i" value="${vs.count}" />
            </c:if>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </c:if>
  <!-- 下发文件 -->
  <c:if test="${not empty _entityList}"> <br/>
    <div> &nbsp;&nbsp;<span style="font-size:18px;color:red;font-weight:bold;">下发文件</span> <span style="font-size:15px;color:green;cursor:pointer;font-weight:bold;align:right;" onclick="javascript:window.location.href='${ctx}/manager/oa/SsuedDocument.do?method=list&mod_id=992000'">更多</span></div>
    <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
        <td width="30" nowrap="nowrap" align="center">序号</td>
        <td width="40" nowrap="nowrap" align="center">月份</td>
        <td width="55" nowrap="nowrap" align="center">事务类型</td>
        <td nowrap="nowrap">文件编号</td>
        <td nowrap="nowrap">文件主题</td>
        <td nowrap="nowrap">发放人/部门</td>
        <td width="100" nowrap="nowrap" align="center">发文时间</td>
        <td width="40" nowrap="nowrap" align="center">操作</td>
        </tr>
        <tbody>
          <c:forEach var='_cur' items='${_entityList}' varStatus="vs">
            <c:set var="ids" value="${ids},${cur.id }" />
            <tr id="tr_${_cur.id}">
            <td valign="top" align="center" nowrap="nowrap">${vs.count }</td>
            <td valign="top" align="center" nowrap="nowrap"><fmt:formatDate value="${_cur.add_date}" pattern="yyyy-MM" /></td>
            <td valign="top" align="center" style="table-layout:fixed;word-break:break-all;word-wrap:break-word;">${_cur.mod_name}</td>
            <td valign="top" align="left" style="table-layout:fixed;word-break:break-all;word-wrap:break-word;">${_cur.file_code}</td>   
            <td valign="top" align="left" style="table-layout:fixed;word-break:break-all;word-wrap:break-word;"><c:choose>
                  <c:when test="${_cur.mod_type eq 'notice' }"><span style="cursor:pointer;" onclick="javascript:window.location.href='${ctx}/manager/oa/DocIssue.do?method=view&id=${_cur.id}&mod_id=992000'">${_cur.title}</span></c:when>
                  <c:when test="${_cur.mod_type eq 'file' }"><span style="cursor:pointer;" onclick="javascript:window.location.href='${ctx}/manager/oa/AuditIngFiles.do?method=view&id=${_cur.id}&mod_id=995000'">${_cur.title}</span></c:when>
                  <c:otherwise></c:otherwise>
                </c:choose></td>
            <td valign="top" align="left" style="table-layout:fixed;word-break:break-all;word-wrap:break-word;">${_cur.pass_man_name}/${_cur.part_name}</td>
            <td valign="top" align="center" nowrap="nowrap"><fmt:formatDate value="${_cur.add_date}" pattern="yyyy-MM-dd HH:mm" /></td>
              <td align="center" valign="top" style="table-layout:fixed;word-break:break-all;word-wrap:break-word;"><c:choose>
                  <c:when test="${_cur.mod_type eq 'notice' }"><span style="cursor:pointer;" onclick="javascript:window.location.href='${ctx}/manager/oa/DocIssue.do?method=view&id=${_cur.id}&mod_id=992000'">查看</span></c:when>
                  <c:when test="${_cur.mod_type eq 'file' }"><span style="cursor:pointer;" onclick="javascript:window.location.href='${ctx}/manager/oa/AuditIngFiles.do?method=view&id=${_cur.id}&mod_id=995000'">查看</span></c:when>
                  <c:otherwise></c:otherwise>
                </c:choose></td>
            </tr>
            <c:if test="${vs.last eq true}">
              <c:set var="i" value="${vs.count}" />
            </c:if>
            <c:set var="ids" value="${ids},${cur.id }" />
          </c:forEach>
        </tbody>
      </table>
    </div>
  </c:if>
  <div class="rtabcont3"></div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[
   $(document).ready(function() {
   });                                      
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
