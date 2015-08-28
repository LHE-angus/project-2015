<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>康佳渠道信息管理系统</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/frame3/css/css.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/frame3/css/global.css" />
<script language="javascript" type="text/javascript" src="${ctx}/styles/frame3/js/jquery.js"></script>
</head>
<body>
<div class="right_contcontleft"  style=" border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #C9CFCD;">
  <table  border="0" class="k_tab" style="_width:100%;">
    <tr>
      <td><img src="${ctx}/styles/frame3/images/k_tup.jpg" width="33" height="32" /></td>
      <td width="97%">&nbsp; 当前位置：首页 &gt; 协同办公</td>
    </tr>
  </table>
</div>
<div style=" width:99%">
  <div class="m_l1">
    <div style="width:99%">
      <div class="k_tongzhi111" style="margin-bottom:10px"><font class="">待办事项</font></div>
      <div class="right_contcont2">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="rtab5">
          <tr>
            <th align="center">待办事项</th>
            <th width="24%">信息来源</th>
            <th align="center" width="20%">时间</th>
            <th align="center" width="15%">操作</th>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr id="tr_${cur.id}">
              <td align="left">${cur.map.title}</td>
              <td>${cur.fromPerson}</td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.enterDate}" pattern="yyyy/MM/dd" /></td>
              <td align="center" nowrap="nowrap">${cur.eventDo}</td>
            </tr>
            <c:if test="${vs.last eq true}">
              <c:set var="i" value="${vs.count}" />
            </c:if>
          </c:forEach>
          <c:forEach begin="${i}" end="4">
            <tr align="center">
              <td width="40px;">&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </c:forEach>
        </table>
        <div> </div>
      </div>
      <div class="k_tongzhi111" style="margin-bottom:10px"><font class="">下发文件</font></div>
      <div class="right_contcont2">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="rtab5">
          <tr>
            <th align="right">标题</th>
            <th width="24%">信息来源</th>
            <th width="20%">时间</th>
            <th width="15%">操作</th>
          </tr>
          <c:forEach var='_cur' items='${_entityList}' varStatus="vs">
            <tr id="tr_${_cur.id}">
              <td align="left"><c:choose>
                  <c:when test="${_cur.mod_type eq 'notice' }"> <span style="cursor:pointer;color: blue;" onclick="javascript:window.location.href='${ctx}/manager/oa/DocIssue.do?method=view&id=${_cur.id}&mod_id=992000'">${_cur.title}</span> </c:when>
                  <c:when test="${_cur.mod_type eq 'file' }"> <span style="cursor:pointer;color: blue;" onclick="view_and_print(${_cur.id});">${_cur.title}</span> </c:when>
                  <c:otherwise><span style="cursor:pointer;color: blue;" onclick="view_and_print(${_cur.id});">${_cur.title}</span> </c:otherwise>
                </c:choose></td>
              <td align="left">${_cur.pass_man_name}/${_cur.part_name}</td>
              <td align="center"><fmt:formatDate value="${_cur.add_date}" pattern="yyyy/MM/dd" /></td>
              <td align="center"><c:choose>
                  <c:when test="${_cur.mod_type eq 'notice' }"> <a style="cursor:pointer;color: blue;" onclick="javascript:window.location.href='${ctx}/manager/oa/DocIssue.do?method=view&id=${_cur.id}&mod_id=992000'">查看</a> </c:when>
                  <c:when test="${_cur.mod_type eq 'file' }"> <a style="cursor:pointer;color: blue;" onclick="view_and_print(${_cur.id});">查看</a> </c:when>
                  <c:otherwise><span style="cursor:pointer;color: blue;" onclick="view_and_print(${_cur.id});">查看</span> </c:otherwise>
                </c:choose></td>
            </tr>
            <c:if test="${vs.last eq true}">
              <c:set var="ii" value="${vs.count}" />
            </c:if>
          </c:forEach>
          <c:forEach begin="${ii}" end="4">
            <tr align="center">
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </c:forEach>
        </table>
        <div> </div>
      </div>
      <div class="k_tongzhi111"><font class="">我申请的事项</font></div>
      <div class="right_contcont2">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="rtab5">
          <tr>
            <th align="right">标题</th>
            <th width="24%">信息来源</th>
            <th width="20%">时间</th>
            <th width="15%">操作</th>
          </tr>
          <c:forEach var='cur_1' items='${kfList}' varStatus="vs">
            <tr id="tr_${cur_1.id}">
              <td align="left"><c:if test="${cur_1.is_node eq 1}"> <span style="cursor:pointer;color: blue;" onclick="view_and_print(${cur_1.id})">${cur_1.file_title}</span> </c:if>
                <c:if test="${cur_1.is_node eq 0}"> <span style="cursor:pointer;color: blue;" onclick="view_and_print(${cur_1.id})">${cur_1.file_title}</span> </c:if></td>
              <td align="left">${cur_1.submit_user}/${cur_1.submit_dept}</td>
              <td align="center"><fmt:formatDate value="${cur_1.submit_datetime}" pattern="yyyy/MM/dd HH:mm" /></td>
              <td align="center"><c:if test="${cur_1.is_node eq 1}"> <a style="cursor:pointer;color: blue;" onclick="view_and_print(${cur_1.id})">查看</a> </c:if>
                <c:if test="${cur_1.is_node eq 0 }"> <a style="cursor:pointer;color: blue;" onclick="view_and_print(${cur_1.id})">查看</a> </c:if></td>
            </tr>
            <c:if test="${vs.last eq true}">
              <c:set var="iii" value="${vs.count}" />
            </c:if>
          </c:forEach>
          <c:forEach begin="${iii}" end="4">
            <tr align="center">
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </c:forEach>
        </table>
        <div> </div>
      </div>
      <div class="right_contcont2">
        <div> </div>
      </div>
      <div class="clear"></div>
    </div>
  </div>
</div>
<script type="text/javascript">//<![CDATA[
function view_and_print(id) {
    window.showModalDialog("${ctx}/manager/oa/AuditIngFiles.do?azaz=" + Math.random() + "&method=view&id=" +id, window, "dialogWidth:800px;status:no;dialogHeight:600px");
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
