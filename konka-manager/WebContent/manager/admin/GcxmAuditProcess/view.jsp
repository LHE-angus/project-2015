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
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="3">流程查看</th>
        </tr>
         <tr>
          <td width="30%" nowrap="nowrap" class="title_item">流程名称:</td>
          <td colspan="2">
          	${af.map.process_name}
          </td>
        </tr>
         <tr>
          <td width="30%" nowrap="nowrap" class="title_item">审核类型:</td>
          <td colspan="2">
          <c:if test="${af.map.audit_type eq 1001}">工程上报审核</c:if>
            <c:if test="${af.map.audit_type eq 1002}">工程上报价格审核</c:if>
          </td>
        </tr>
         <tr>
          <td width="30%" nowrap="nowrap" class="title_item">有效性:</td>
          <td colspan="2">
          	<c:choose>
                <c:when test="${af.map.is_del eq 0}"> <span style="color:#060;">有效</span></c:when>
                <c:when test="${af.map.is_del eq 1}"> <span style="color:#F00;">已失效</span></c:when>
              </c:choose>
          </td>
        </tr>
        <tr>
         <td width="30%" nowrap="nowrap" class="title_item">备注:</td>  
         <td colspan="2">${af.map.memo}</td>  
        </tr>
        <tr class="title_top">
          <td width="30%" align="center" nowrap="nowrap" bgcolor="#fff2dc"><font class="bigall">审核角色</font></td>
          <td  align="center" nowrap="nowrap" bgcolor="#fff2dc"><font class="bigall">下个审核角色</font></td>
          <td  align="center" nowrap="nowrap" bgcolor="#fff2dc"><font class="bigall">审核步骤</font></td>
        </tr>
        <c:if test="${not empty af.map.gcxmAuditProcessNodeList}">
        	 <c:forEach items="${af.map.gcxmAuditProcessNodeList}" var="cur" varStatus="vs">
		        <tr>
		          <td align="center">${fn:escapeXml(cur.audit_role_name)}</td>
		          <td align="center">${fn:escapeXml(cur.next_audit_role_name)}</td>
		           <td align="center">第${vs.count}步</td>
		        </tr>
        	 </c:forEach>
        
        </c:if>
      <tr>
          <td colspan="3" align="center">
            &nbsp;
            <html-el:button property="" value="返 回" styleClass="bgButtonBack" styleId="btn_back" onclick="history.back();" /></td>
        </tr>
      </table>
  </div>
</div>

<jsp:include page="/__analytics.jsp" />
</body>
</html>
