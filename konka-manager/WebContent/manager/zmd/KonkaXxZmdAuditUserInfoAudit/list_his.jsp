<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" style="position:relative;overflow:hidden;">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div style="padding: 5px;float: right;">
 	<input class="but5" type="button"  value="返回 " onclick="history.back();" />
  </div>
  <div class="rtabcont1" style="100%;overflow-x:scroll;">
    <table id="table1" width="1670" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="50" align="center">序号</td>
        <td width="100" align="center">客户姓名</td>
        <td width="100" align="center">专卖店编号</td>
        <td width="100" align="center">R3编码</td>
        <td width="80" align="center">类型</td>
        <td width="120" align="center">审批状态</td>
        <td width="120" align="center">审批人</td>
        <td width="140" align="center">审批时间</td>
        <td width="180" align="center">下个审核角色</td>
        <td width="340" align="center">数据更改描述</td>
        <td align="center">审批意见</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
          <td align="left" nowrap="nowrap">${zmd_user_name}</td>
          <td align="left" nowrap="nowrap">${cur.map.zmd_sn}<c:if test="${empty cur.map.zmd_sn}">--</c:if></td>
          <td align="left" nowrap="nowrap">${cur.map.r3_id}<c:if test="${empty cur.map.r3_id}">--</c:if></td>
          <td align="center" nowrap="nowrap">${cur.audit_desc}</td>
          <td align="left" nowrap="nowrap">${cur.audit_status_desc}<c:if test="${empty cur.audit_status_desc}">--</c:if></td>
          <td align="left" nowrap="nowrap">${cur.audit_user_name}</td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.audit_date}" pattern="yyyy-MM-dd HH:mm"/></td>
          <td align="left" >${cur.audit_next_node_name}<c:if test="${empty cur.audit_status_desc}">--</c:if></td>
          <td align="left" >${cur.audit_data_desc}<c:if test="${empty cur.audit_data_desc}">--</c:if></td>
          <td align="left" >${cur.audit_option}</td>
        </tr>
      </c:forEach>
    </table>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
