<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcOrderAuditProcessDefine">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="8">流程查看</th>
        </tr>
         <tr>
          <td width="30%" nowrap="nowrap" class="title_item">流程类型:</td>
          <td colspan="3">
          	<c:choose>
                <c:when test="${af.map.process_type eq 1}"> <span >普通流程</span></c:when>
                <c:when test="${af.map.process_type eq 2}"> <span >特殊流程</span></c:when>
            </c:choose>
          </td>
        </tr>
         <tr>
          <td width="30%" nowrap="nowrap" class="title_item">客户类型:</td>
          <td colspan="3">
          ${af.map.map.c_name}
          </td>
        </tr>
          <tr>
          <td width="30%" nowrap="nowrap" class="title_item">适用范围:</td>
          <td colspan="3">
              <c:choose>
                <c:when test="${af.map.used_field eq 0}">正常订单流程</c:when>
                <c:when test="${af.map.used_field eq 1}">变更订单流程</c:when>
                <c:when test="${af.map.used_field eq 2}">退货订单流程</c:when>
              </c:choose>
          </td>
        </tr>
         <tr>
          <td width="30%" nowrap="nowrap" class="title_item">有效性:</td>
          <td colspan="3">
          	<c:choose>
                <c:when test="${af.map.is_del eq 0}"> <span style="color:#060;">有效</span></c:when>
                <c:when test="${af.map.is_del eq 1}"> <span style="color:#F00;">已失效</span></c:when>
              </c:choose>
          </td>
        </tr>
        <tr class="title_top">
          <td width="30%" align="center" nowrap="nowrap" bgcolor="#fff2dc"><font class="bigall">审核角色</font></td>
          <td  align="center" nowrap="nowrap" bgcolor="#fff2dc"><font class="bigall">下个审核角色</font></td>
          <td  align="center" nowrap="nowrap" bgcolor="#fff2dc"><font class="bigall">修改权限</font></td>
          <td  align="center" nowrap="nowrap" bgcolor="#fff2dc"><font class="bigall">审核步骤</font></td>
        </tr>
        <c:if test="${not empty af.map.konkaOrderAuditProcessNodeList}">
        	 <c:forEach items="${af.map.konkaOrderAuditProcessNodeList}" var="cur" varStatus="status">
		        <tr>
		          <td align="center">${fn:escapeXml(cur.role_name)}</td>
		          <td align="center">${fn:escapeXml(cur.next_node)}</td>
		          <td align="center">
		          <c:if test="${cur.is_update_authority eq 0}">无修改权限</c:if>
		          <c:if test="${cur.is_update_authority eq 1}">有修改权限</c:if>
		          </td>
		           <td align="center">第${cur.audit_level}步</td>
		        </tr>
        	 </c:forEach>
        
        </c:if>
      <tr>
          <td colspan="4" align="center">
            &nbsp;
            <html-el:button property="" value="返 回" styleClass="bgButtonBack" styleId="btn_back" onclick="history.back();" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>

<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
