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
    <h3 align="center" ><strong class="fb">查看</strong></h3>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/oa/AuditFiles" enctype="multipart/form-data">
     <html-el:hidden property="queryString" styleId="queryString" />
      <html-el:hidden property="id" styleId="id" />
      <div align="center" style="font-size: 20px; font-weight:bolder; margin-bottom: 4px;">
        <c:forEach var="cur" items="${category11List}" varStatus="vs">
          <c:forEach var="_cur" items="${filesProperty11List}" varStatus="vs">
            <c:if test="${cur.c_index eq _cur.c_index}">${cur.c_name}类文件
              <c:set var="lb_name" value="${cur.c_name}" />
            </c:if>
          </c:forEach>
        </c:forEach>
      </div>
      
      <div style="height:10px;"></div>
      <table width="80%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr>
          <td  align="center" class="td_bord">申请负责人</td>
          <td>${fn:escapeXml(af.map.apply_user_name)}&nbsp;</td>
          <td  align="center" class="td_bord">电　　话</td>
          <td >${fn:escapeXml(af.map.apply_user_tel)}&nbsp;</td>
          <td align="center" class="td_bord">编　　号</td>
          <td >${fn:escapeXml(af.map.file_no)}&nbsp;</td>
        </tr>
        <tr>
          <td align="center" class="td_bord">标　　题</td>
          <td colspan="3">${fn:escapeXml(af.map.file_title)}</td>
          <td nowrap="nowrap" align="center" class="td_bord">文件密级</td>
          <td nowrap="nowrap"><c:forEach var="cur" items="${category13List}" varStatus="vs">
              <c:if test="${af.map.category13 eq cur.c_index}">${cur.c_name}　</c:if>
            </c:forEach></td>
        </tr>
        <tr>
          <td align="center" class="td_bord">提 案 人</td>
          <td>${af.map.submit_user}</td>
          <td align="center" class="td_bord">提案时间</td>
          <td nowrap="nowrap"><fmt:formatDate value="${af.map.submit_datetime}" pattern="yyyy-MM-dd HH:mm" /></td>
          <td align="center" class="td_bord">类　　别</td>
          <td>${lb_name}</td>
        </tr>
        <tr>
          <td align="center" class="td_bord">审 批 人</td>
          <td>
	          <div id="audits">
	              <c:set var="audit_node_num" value="2" />
	              <c:if test="${not empty filesAuditNodeList}">
	                <c:forEach var="cur" items="${filesAuditNodeList}" varStatus="vs"> ${cur.audit_user}
	                  <c:if test="${vs.last ne true}">，</c:if>
	                </c:forEach>
	              </c:if>
	          </div>
          </td>
          <td align="center" class="td_bord">级　　别</td>
          <td nowrap="nowrap">
          	<c:forEach var="cur" items="${category12List}" varStatus="vs">
              <c:if test="${af.map.category12 eq cur.c_index}">${cur.c_name}　</c:if>
            </c:forEach></td>
          <td align="center" class="td_bord"></td>
          <td></td>
        </tr>
        
        <tr>
          <td height="200" align="center" valign="top" class="td_bord" style="padding-top:5px;">请示内容</td>
          <td colspan="5" valign="top" style="padding-top:10px;">${af.map.content}
            <c:forEach var="cur" items="${attachmentList}" varStatus="vs"><span>${vs.count}、<a href="${ctx}/${cur.save_path}" target="_blank">${cur.file_name}</a> <br />
              </span>
            </c:forEach>
          </td>
        </tr>
        
        <tr>
          <td align="center" class="td_bord">是否下发</td>
          <td colspan="5"><c:if test="${af.map.is_forward eq 0}">否</c:if>
            <c:if test="${af.map.is_forward eq 1}">是</c:if>
            <c:set var="display" value="none" />
            <c:if test="${af.map.is_forward eq 1}">
              <c:set var="display" value="" />
            </c:if></td>
        </tr>
        <tr style="display:${display};">
          <td align="center" class="td_bord">下发用户</td>
          <td colspan="5">${af.map.fa_names} </td>
        </tr>
        <tr style="display:${display};">
          <td align="center" class="td_bord">下发部门</td>
          <td colspan="5">${af.map.dept_names} </td>
        </tr>
      </table>
      <c:if test="${not empty filesAuditNodeList}">
         <table width="80%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
          <tr>
            <td align="center" nowrap="nowrap" class="td_bord">审批状况</td>
            <td align="center" nowrap="nowrap" class="td_bord">审批开始时间</td>
            <td align="center" nowrap="nowrap" class="td_bord">审批结束时间</td>
            <td align="center" nowrap="nowrap" class="td_bord">审批意见</td>
            <td align="center" nowrap="nowrap" class="td_bord">审批人/部门</td>
          </tr>
          <c:set var="begin_time" value="${af.map.submit_datetime}" />
	          <c:forEach var="cur" items="${filesAuditNodeList}" varStatus="vs">
	            <c:set var="audit_result" value="" />
	            <c:set var="i" value="${vs.count}" />
	            <c:if test="${cur.audit_result eq 2}">
	              <c:set var="audit_result" value="<span style='color:#090;'>同意</span>" />
	            </c:if>
	            <c:if test="${cur.audit_result eq 1}">
	              <c:set var="audit_result" value="<span style='color:#f00;'>驳回</span>" />
	            </c:if>
	            <c:set var="begin_time" value="${filesAuditNodeList[i].audit_datetime}" />
	            <c:if test="${vs.last}">
	              <c:set var="begin_time" value="${af.map.submit_datetime}" />
	            </c:if>
	            <tr>
	              <td align="center">${audit_result}</td>
	              <td align="center">
	              	<c:if test="${not empty cur.audit_datetime}">
	                  <fmt:formatDate value="${begin_time}" pattern="yyyy-MM-dd HH:mm:ss"/>
	                </c:if>
<%-- 	                <c:if test="${empty cur.audit_datetime}"><span style="color:#f00">未审批</span> </c:if> --%>
	              </td>
	              
	              <td align="center">
	              	<c:if test="${not empty cur.audit_datetime}">
	                  <fmt:formatDate value="${cur.audit_datetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
	                </c:if>
<%-- 	                <c:if test="${empty cur.audit_datetime}"><span style="color:#f00">未审批</span> </c:if> --%>
	              </td>
	              
	              <td align="left">
	              	<c:if test="${not empty cur.audit_comment}"> ${fn:escapeXml(cur.audit_comment)} </c:if>
<%-- 	                <c:if test="${empty cur.audit_comment}"><span style="color:#f00">未审批</span> </c:if> --%>
	              </td>
	              <td>${cur.audit_user}</td>
	            </tr>
	          </c:forEach>
        </table>
      </c:if>
    </html-el:form>
    <div> 
    	<br />
    	<br />
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
