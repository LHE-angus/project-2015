<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>&nbsp;${fn:escapeXml(af.map.file_title)}&nbsp;(${af.map.submit_user})</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body style="background-color:#fff;">
<div style="width:100%;background:white;">
  <html-el:form action="/admin/AuditFiles">
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
    <div style="overflow-y:auto;"></div>
    <table width="780" border="0" cellspacing="0" cellpadding="0" class="rtable2" align="center">
      <tr id="tr1">
        <td width="85" align="center" class="td_bord">负&nbsp;责&nbsp;人</td>
        <td width="460">${fn:escapeXml(af.map.apply_user_name)}&nbsp;</td>
        <td width="85" nowrap="nowrap" align="center" class="td_bord">电　　话</td>
        <td width="160" nowrap="nowrap">${fn:escapeXml(af.map.apply_user_tel)}&nbsp;</td>
      </tr>
      <tr id="tr2">
        <td align="center" class="td_bord">文件标题</td>
        <td>${fn:escapeXml(af.map.file_title)}</td>
        <td nowrap="nowrap" align="center" class="td_bord">文件编号</td>
        <td nowrap="nowrap">${fn:escapeXml(af.map.file_no)}&nbsp;</td>
      </tr>
      <tr id="tr3">
        <td align="center" class="td_bord">申&nbsp;请&nbsp;人</td>
        <td>${af.map.submit_user}</td>
        <td align="center" class="td_bord">申请时间</td>
        <td nowrap="nowrap"><fmt:formatDate value="${af.map.submit_datetime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
      </tr>
      <tr id="tr4">
        <td align="center" class="td_bord">审&nbsp;批&nbsp;人</td>
        <td><div id="audits">
            <c:set var="audit_node_num" value="2" />
            <c:if test="${not empty _filesAuditNodeList}">
              <c:forEach var="cur" items="${_filesAuditNodeList}" varStatus="vs"> ${cur.audit_user}
                <c:if test="${vs.last ne true}">，</c:if>
              </c:forEach>
            </c:if>
          </div></td>
        <td align="center" class="td_bord">时　　限</td>
        <td>${af.map.time_limit}&nbsp;天</td>
      </tr>
  <!-- 提交文件 -->
   <c:if test="${af.map.file_type eq 0 }">
      <tr>
        <td align="center" class="td_bord" style="width:80px;">请示内容</td>
        <td colspan="3" valign="top" style="width:700px;table-layout:fixed;word-break:break-all;word-wrap:break-word;">${af.map.content}
          <c:if test="${not empty attachmentList}">上传附件：<br /></c:if>
          <c:forEach var="cur" items="${attachmentList}" varStatus="vs"><span>&nbsp;&nbsp;${vs.count}、<a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}" target="_blank">${cur.file_name}</a> <br />
            </span></c:forEach></td>
      </tr>
      </c:if>
        <!-- 费用申请 -->
            <c:if test="${af.map.file_type ne 0 }">
            <tr  style="display: none;">
              <td align="center">申请客户</td>
              <td>${fn:escapeXml(shop_name)}</td>
              <td align="center">费用总额</td>
              <td>${fn:escapeXml(af.map.column_6)}</td>
            </tr>
              <c:if test="${not empty filesPropertyList}">
              <tr  style="display: none;">
              <td width="10%" align="center" nowrap="nowrap">费用类别</td>
              <td colspan="3"><table width="96%" border="0" align="left" cellpadding="0" cellspacing="0" id="categorys_td" style="border:#ccc 1px solid;">
                <c:if test="${not empty propertyList }">
                <tr>
                  <td colspan="4"></td>
                  <c:forEach items="${propertyList}" var="cur" step="${fn:length(filesPropertyList)}">
                  <td colspan="2" align="center">${cur.map.real_name }</td>
                  </c:forEach>
                </tr>
                </c:if>
                <tr>
                  <td align="left">费用类别</td>
                  <td align="center">说明</td>
                  <td align="center">数量</td>
                  <td align="center">单价</td>
                  <c:forEach items="${propertyList}" var="cur" step="${fn:length(filesPropertyList)}">
	                  <td align="center">数量</td>
	                  <td align="center">单价</td>
                  </c:forEach>
                  
                </tr>
                <c:forEach items="${filesPropertyList}" var="_cur" >
                <tr>
                  <td align="left">${fn:escapeXml(_cur.map.c_name)}<html-el:hidden property="c_index" styleId="c_index_1" value="${_cur.c_index}" /></td>
                  <td align="center">${fn:escapeXml(_cur.c_desc)}</td>
                  <td align="right">${fn:escapeXml(_cur.amount)}</td>
                  <td align="right"><fmt:formatNumber pattern="0.00" value="${_cur.cost}" /></td>
                  <c:forEach items="${_cur.map.appList}" var="ct">
                 	 <td align="right"><fmt:formatNumber pattern="0" value="${ct.amount}" /></td>
                 	 <td align="right"><fmt:formatNumber pattern="0.00" value="${ct.cost}" /></td>
                  </c:forEach>
                </tr>
                </c:forEach> 
                </table></td>
              </tr>
              </c:if>
            <tr>
              <td align="center">详细内容</td>
              <td colspan="5" valign="top" style="width:700px;table-layout:fixed;word-break:break-all;word-wrap:break-word;">${af.map.content}
              <c:forEach var="cur" items="${attachmentList}" varStatus="vs"><span>${vs.count}、<a href="${ctx}/${cur.save_path}" target="_blank">${cur.file_name}</a> <br />
              </span></c:forEach></td>
            </tr>
            </c:if>
      <c:set var="display" value="none" />
      <c:if var="is_forword" test="${not empty af.map.fa_names or not empty af.map.dept_names}">
        <c:set var="display" value="" />
      </c:if>
      <c:if test="${!is_forword}">
        <tr id="tr6" style="display: none;">
          <td align="center" class="td_bord">是否下发</td>
          <td colspan="5" ><c:if test="${af.map.is_forward eq 0}">否</c:if>
            <c:if test="${af.map.is_forward eq 1}">是</c:if></td>
        </tr>
      </c:if>
      <c:if test="${not empty af.map.fa_names }">
        <tr id="tr7"  style="display: none;">
          <td align="center" class="td_bord">下&nbsp;发&nbsp;用&nbsp;户</td>
          <td colspan="5">${af.map.fa_names} </td>
        </tr>
      </c:if>
      <c:if test="${not empty af.map.dept_names }">
        <tr id="tr8"  style="display: none;"">
          <td align="center" class="td_bord">下&nbsp;发&nbsp;部&nbsp;门</td>
          <td colspan="5">${af.map.dept_names} </td>
        </tr>
      </c:if>
    </table>
    <br/>
    <c:if test="${not empty filesAuditNodeList}">
      <table id="shenpi" width="780" border="0" cellspacing="0" cellpadding="0" class="rtable2" align="center">
        <tr>
          <td width="80" align="center" nowrap="nowrap" class="td_bord">审批状况</td>
          <td width="110" align="center" nowrap="nowrap" class="td_bord">审批开始时间</td>
          <td width="110" align="center" nowrap="nowrap" class="td_bord">审批结束时间</td>
          <td align="center" nowrap="nowrap" class="td_bord">审批意见</td>
          <td width="150" align="center" nowrap="nowrap" class="td_bord">审批人/部门</td>
        </tr>
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
          <tr>
            <td align="center" valign="top">${audit_result}</td>
            <td align="center" valign="top"><fmt:formatDate value="${begin_time}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td align="center" valign="top"><fmt:formatDate value="${cur.audit_datetime}" pattern="yyyy-MM-dd HH:mm"/>
              <c:set var="begin_time" value="${cur.audit_datetime}" /></td>
            <td>${fn:escapeXml(cur.audit_comment)}</td>
            <td align="center" valign="top">${cur.audit_user}</td>
          </tr>
        </c:forEach>
      </table>
    </c:if>
    <br/>
    <table id="table_footer" width="95%" align="center" class="list" style="margin-top:3px;" align="center">
      <tr>
        <td align="center"><html-el:button property="" value="关 闭" styleClass="but5" styleId="btn_back" onclick="window.close();" />
      </tr>
    </table>
  </html-el:form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#audit_comment" ).attr({"dataType":"Limit","min":"1","max":"250","msg":"请填写审批评语且字数少于250个"});
	$("input[name=audit_result]:first").attr("dataType", "Group").attr("msg", "请选择审批结果");

 	// 提交
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 1)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
