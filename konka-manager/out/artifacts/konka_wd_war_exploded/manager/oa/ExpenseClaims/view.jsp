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
    <h3 align="center" ><strong class="fb">费用申请信息查看</strong></h3>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/oa/ExpenseClaims" enctype="multipart/form-data">
      <html-el:hidden property="queryString" styleId="queryString" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="id" styleId="id" />
      <div style="height:10px;"></div>
      <table width="80%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
       <tr>
              <td width="10%" align="center" nowrap="nowrap">标    &nbsp;&nbsp;  题</td>
              <td>${fn:escapeXml(af.map.file_title)}</td>
              <td width="10%" align="center" nowrap="nowrap">文件编号</td>
              <td width="20%" align="left" nowrap="nowrap">${fn:escapeXml(af.map.file_no)}</td>
              <td width="10%" align="center" nowrap="nowrap">物料制作广告公司</td>
              <td width="15%">${fn:escapeXml(konkaExpenseClaims.column_7)}</td>
            </tr>
             <tr>
              <td width="10%" align="center" nowrap="nowrap">申请人</td>
              <td >${fn:escapeXml(af.map.submit_user)}</td>
              <td width="10%" align="center" nowrap="nowrap">申请部门</td>
              <td width="20%" align="left" nowrap="nowrap">${fn:escapeXml(af.map.submit_dept)}</td>
              <td width="10%" align="center" nowrap="nowrap">申请时间</td>
              <td width="15%"><fmt:formatDate value="${af.map.submit_datetime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            </tr>
             <tr>
              <td width="10%" align="center" nowrap="nowrap">广告受益客户</td>
              <td >${fn:escapeXml(konkaExpenseClaims.column_1)}</td>
              <td>受益客户本年度销售额</td>
              <td>${fn:escapeXml(konkaExpenseClaims.column_2)}</td>
              <td>受益客户本年度广告累计投入</td>
              <td>${fn:escapeXml(konkaExpenseClaims.c_type)}</td>  
            </tr>
            <tr>
              <td width="10%" align="center" nowrap="nowrap">费用类别</td>
              <td height="50">
              <c:if test="${not empty filesPropertyList}">
               <c:forEach items="${categoryList}" var="cur">
              <c:forEach items="${filesPropertyList}" var="_cur" >
              <c:if test="${_cur.c_index eq cur.c_index}">
              <span>${fn:escapeXml(cur.c_name)}</span>
              </c:if>
              </c:forEach>
              </c:forEach>
              </c:if>
              </td>
              <td width="10%" align="center" nowrap="nowrap">自批复日起制作、验收时间</td>
              <td width="20%" align="left" nowrap="nowrap"><fmt:formatDate value="${konkaExpenseClaims.column_8}" pattern="yyyy-MM-dd" /></td>
              <td width="10%" align="center" nowrap="nowrap">发货、运输、到达时间</td>
              <td width="15%"><fmt:formatDate value="${konkaExpenseClaims.column_9}" pattern="yyyy-MM-dd" /></td>
            </tr>
             <tr>
              <td width="10%" align="center" nowrap="nowrap">费用总额</td>
              <td width="20%" align="left" nowrap="nowrap">${fn:escapeXml(konkaExpenseClaims.column_6)}</td>
              <td align="center">时　　限</td>
              <td>${fn:escapeXml(af.map.time_limit)}天</td>
              <td align="center">报告回复日期</td>
              <td><fmt:formatDate value="${af.map.archive_datetime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            </tr>
            <tr>
              <td align="center">活动主题内容及预期效果</td>
              <td height="200" colspan="5" valign="top" style="table-layout:fixed;word-break:break-all;word-wrap:break-word;">${konkaExpenseClaims.column_4}
                <c:forEach var="cur" items="${attachmentList}" varStatus="vs"><span>${vs.count}、<a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}" target="_blank">${cur.file_name}</a> <br />
                  </span></c:forEach></td>
            </tr>
            <tr>
              <td align="center">广告费预算金额、数量、规格、制作内容</td>
              <td height="100" colspan="5" valign="top" style="table-layout:fixed;word-break:break-all;word-wrap:break-word;">${konkaExpenseClaims.column_5}
              </td>
            </tr>
            <c:if var="is_huiqian" test="${af.map.file_status ne -1 and af.map.file_status ne 1 and af.map.file_status ne 0}">
            <tr style="display:${display};">
              <td align="center">下发范围</td>
              <td colspan="5">${af.map.fa_names} </td>
            </tr>
            </c:if>
            <c:if test="${!is_huiqian}">
            <tr>
              <td align="center">是否下发</td>
              <td colspan="5"><html-el:radio property="is_xiafa" value="0" styleId="is_xiafa_0" />
                <label for="is_xiafa_0"> 否 </label>
                <html-el:radio property="is_xiafa" value="1" styleId="is_xiafa_1" />
                <label for="is_xiafa_1"> 是 </label>
                <c:set var="display" value="none" />
                <c:if test="${af.map.is_forward eq 1}">
                  <c:set var="display" value="" />
                </c:if></td>
            </tr>
            <tbody id="is_xiafa_tr" style="display:${display};">
              <tr>
                <td align="center">下发用户</td>
                <td colspan="5"><input type="hidden" name="receive_type_1_ids" id="receive_type_1_ids" value="${af.map.fa_ids}" />
                  <input type="text" name="receive_type_1_names" id="receive_type_1_names" value="${af.map.fa_names}" readonly="readonly"  style="width:95%;vertical-align:middle;" />
                  <img id="add_fa" src="${ctx}/images/search.gif" style='vertical-align:middle;cursor: pointer;' alt='选择人员' /> <br />
              </tr>
              <tr>
                <td align="center">下发部门</td>
                <td colspan="5"><input type="hidden" name="receive_dept_1_ids" id="receive_dept_1_ids" value="${af.map.dept_ids}" />
                  <input type="text" name="receive_dept_1_names" id="receive_dept_1_names" value="${af.map.dept_names}" readonly="readonly"  style="width:95%;vertical-align:middle;" />
                  <img id="add_dept" src="${ctx}/images/search.gif" style='vertical-align:middle; cursor: pointer;' alt='选择部门' /> <br />
              </tr>
              </tbody>
              </c:if>
              <c:if test="${not empty filesAuditNodeList}">
        <table width="80%" align="center" class="list1" style="margin-top:3px;">
          <tr>
            <td width="120" align="center" nowrap="nowrap" class="td_bord">审批状况</td>
            <td width="15%" align="center" nowrap="nowrap" class="td_bord">审批开始时间</td>
            <td width="15%" align="center" nowrap="nowrap" class="td_bord">审批结束时间</td>
            <td align="center" nowrap="nowrap" class="td_bord">审批意见</td>
            <td width="15%" align="center" nowrap="nowrap" class="td_bord">审批人/部门</td>
          </tr>
          <c:set var="begin_time" value="${af.map.submit_datetime}" />
          <c:forEach var="cur" items="${filesAuditNodeList}" varStatus="vs">
            <c:set var="audit_result" value="未审批" />
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
              <td align="center"><c:if test="${not empty cur.audit_datetime}">
                  <fmt:formatDate value="${begin_time}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </c:if>
                <c:if test="${empty cur.audit_datetime}"></c:if></td>
              <td align="center"><c:if test="${not empty cur.audit_datetime}">
                  <fmt:formatDate value="${cur.audit_datetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </c:if>
                <c:if test="${empty cur.audit_datetime}"></c:if></td>
              <td><c:if test="${not empty cur.audit_comment}"> ${fn:escapeXml(cur.audit_comment)} </c:if>
                <c:if test="${empty cur.audit_comment}"> </c:if></td>
              <td>${cur.audit_user}</td>
            </tr>
          </c:forEach>
        </table>
      </c:if>
      </table>
    </html-el:form>
    <div> <br />
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
