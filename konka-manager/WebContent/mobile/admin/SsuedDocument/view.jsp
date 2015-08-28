<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<META http-equiv=Expires content=0>
<META http-equiv=Cache-Control content=no-cache>
<META http-equiv=Pragma content=no-cache>
<title>零售通-康佳电器</title>
<link rel="stylesheet" href="${ctx}/mobile/themes/konka.min.css" />
<link rel="stylesheet" href="${ctx}/commons/styles/jquery.mobile.structure-1.0.min.css" />
<link href="${ctx}/styles/mobiscroll-1.5.2.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/commons/styles/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
<script src="${ctx}/commons/scripts/jquery.mobile-1.0.min.js"></script>
<script src="${ctx}/commons/scripts/jquery.form.js" ></script>
<script src="${ctx}/commons/scripts/mobiscroll-1.5.2.js" ></script>
<script src="${ctx}/commons/scripts/jquery.autocomplete.js" ></script>
</head>
<body>
<div data-role="page">
  <div data-role="header" data-position="inline">
    <h1>下发文件</h1>
    <a href="${ctx}/mobile/admin/Frames.do" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-left jqm-home">返回</a>
	<a onclick="logout();" data-icon="forward" data-iconpos="notext" data-direction="reverse" class="ui-btn-right jqm-home">退出</a>
  </div>
  
    <div data-role="fieldcontain">
    <ul data-role="listview" data-inset="true" data-theme="b" data-divider-theme="a">
    <li data-role="list-divider"><c:if test="${af.map.file_type eq 0 }">下发文件</c:if><c:if test="${af.map.file_type ne 0 }">费用申请</c:if></li>
    <li><p>负责人：${fn:escapeXml(af.map.apply_user_name)}</p></li>
    <li><p>电话：<c:out value="${af.map.apply_user_tel}"/></p></li>
    <li><p>文件标题：${fn:escapeXml(af.map.file_title)}</p></li>
    <li><p>文件编号：<c:out value="${af.map.file_no}"/></p></li>
    <li><p>申请人：<c:out value="${af.map.submit_user}"/></p></li>
    <li><p>申请时间：<fmt:formatDate value="${af.map.submit_datetime}" pattern="yyyy-MM-dd HH:mm:ss" /></p></li>
    <li><p>审批人：
            <c:set var="audit_node_num" value="2" />
            <c:if test="${not empty _filesAuditNodeList}">
              <c:forEach var="cur" items="${_filesAuditNodeList}" varStatus="vs"> ${cur.audit_user}
                <c:if test="${vs.last ne true}">，</c:if>
              </c:forEach>
            </c:if>
     </li>
    <li><p>时限：<c:out value="${af.map.time_limit}"/>天</p></li>
      <!-- 提交文件 -->
   <c:if test="${af.map.file_type eq 0 }">
    <li><p>请示内容：${af.map.content}</p></li>
   </c:if>
        <!-- 费用申请 -->
   <c:if test="${af.map.file_type ne 0 }">
   <li><p>申请网点：${fn:escapeXml(shop_name)}</p></li>
   <li><p>费用总额：${fn:escapeXml(af.map.column_6)}</p></li>
    <c:if test="${not empty filesPropertyList}">
	   <li data-role="list-divider">费用类别</li>
	   <c:if test="${not empty propertyList }">
	     <c:forEach items="${propertyList}" var="cur" step="${fn:length(filesPropertyList)}">
	    	 <li><p>${cur.map.real_name}</p></li>
	     </c:forEach>
	   </c:if>
	    <c:forEach items="${filesPropertyList}" var="_cur" >
	    	<li><p>费用类别：${fn:escapeXml(_cur.map.c_name)}</p></li>
	    	<li><p>说明：${fn:escapeXml(_cur.c_desc)}</p></li>
	    	<li><p>数量：${fn:escapeXml(_cur.amount)}</p></li>
	    	<li><p>单价：<fmt:formatNumber pattern="0.00" value="${_cur.cost}" /></p></li>
	    	 <c:forEach items="${_cur.map.appList}" var="ct">
	     	<li><p>数量：<fmt:formatNumber pattern="0" value="${ct.amount}" /></p></li>
	     	<li><p>价格：<fmt:formatNumber pattern="0.00" value="${ct.cost}" /></p></li>
	    	</c:forEach>
	   </c:forEach>
    </c:if>
   </c:if>
   <c:set var="display" value="none" />
      <c:if var="is_forword" test="${not empty af.map.fa_names or not empty af.map.dept_names}">
        <c:set var="display" value="" />
      </c:if>
      <c:if test="${!is_forword}">
       	<li><p>是否下发：<c:if test="${af.map.is_forward eq 0}">否</c:if>
            <c:if test="${af.map.is_forward eq 1}">是</c:if></p></li>
      </c:if>
    <c:if test="${not empty af.map.fa_names }">
       <li><p>下发用户：${af.map.fa_names}</p></li>
    </c:if>
    <c:if test="${not empty af.map.dept_names }">
       <li><p>下发部门：${af.map.dept_names}</p></li>
    </c:if>
     <c:if test="${not empty filesAuditNodeList}">
		 <c:forEach var="cur" items="${filesAuditNodeList}" varStatus="vs">
			  <c:set var="i" value="${vs.count}" />
	          <c:if test="${cur.audit_result eq 2}">
	            <c:set var="audit_result" value="同意" />
	          </c:if>
	          <c:if test="${cur.audit_result eq 1}">
	            <c:set var="audit_result" value="<span style='color:#f00;'>驳回</span>" />
	          </c:if>
	          <c:if test="${cur.audit_result eq -1}">
	            <c:set var="audit_result" value="<span style='color:#f00;'>转发</span>" />
	          </c:if>
	          <c:if var="is_vs_last" test="${vs.last}">
	            <c:set var="begin_time" value="${af.map.submit_datetime}" />
	          </c:if>
	          <c:if test="${!is_vs_last}">
	            <c:set var="begin_time" value="${filesAuditNodeList[i].audit_datetime}" />
	          </c:if>
			<li data-role="list-divider">审批人/部门：${cur.audit_user}</li>
		 	<li><p>审批开始时间：<fmt:formatDate value="${begin_time}" pattern="yyyy-MM-dd HH:mm"/></p></li>
		 	<li><p>审批结束时间：<fmt:formatDate value="${cur.audit_datetime}" pattern="yyyy-MM-dd HH:mm"/><c:set var="begin_time" value="${cur.audit_datetime}" /></p></li>
		 	<li><p>审批状况：${audit_result}</p></li>
		 	<li><p>审批意见：${fn:escapeXml(cur.audit_comment)}</p></li>
		 </c:forEach>
    </c:if>
    </ul>
    <input type="hidden" id="mod_id" name="mod_id" value="${af.map.mod_id}"/>
    </div>
<div data-role="footer" class="ui-bar" data-position="fixed">
   	<div data-role="controlgroup"  data-type="horizontal">
		<a href="SsuedDocument.do?method=list&mod_id=${af.map.mod_id}" data-role="button" data-icon="back" data-theme="b" data-inline="true">返回</a>
	</div>
</div>

</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>