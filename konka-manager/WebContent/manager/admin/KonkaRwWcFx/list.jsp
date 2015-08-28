<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style>
.datatable tr:hover, .datatable tr.hilite {
	background-color: #DFE7F2;
	color: #000000;
}
</style>
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <html-el:form action="/admin/KonkaRwWcFx">
    <html-el:hidden property="method" value="list" />
    <html-el:hidden property="mod_id" styleId="mod_id" />
    <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
        <tr>
          <td height="36" align="left" style="padding-left:5px;">&nbsp;<strong class="fb">年度：</strong>
            <html-el:select property="year" styleId="year">
              <c:forEach items="${yearList}" var="cur">
                <html-el:option value="${cur}">${cur}年</html-el:option>
              </c:forEach>
              &nbsp;&nbsp; </html-el:select>
            <c:if test="${empty fgs_dept}">
	            <strong class="fb">分公司：</strong>
	            <html-el:select property="dept_id" styleId="dept_id">
	              <html-el:option value="">请选择...</html-el:option>
	              <c:forEach var="cur" items="${deptList}">
	                <html-el:option value="${cur.dept_id}">${fn:escapeXml(cur.dept_name)}</html-el:option>
	              </c:forEach>
	            </html-el:select>
            </c:if>
            &nbsp;&nbsp;&nbsp;
            <input name="button" type="submit" class="bgSearch" id="sear_btn" value="搜 索" /></td>
        </tr>
      </table>
    </div>
  </html-el:form>
  <h1 align="center" style="padding-top: 20px;font-size: 22px;"><c:if test="${not empty fgs_dept}">${fgs_dept.dept_name}分公司</c:if>任务达成分析表</h1>
  <div><span style="color: gray;padding-left: 10px;">提示：“--”表示为无数据！</span><span style="float:right; padding-right: 10px;" >单位:万元</span></div>
  <div class="rtabcont1" style="overflow-x: auto;">
    <%@ include file="/commons/pages/messages.jsp" %>
    <table class="rtable6 datatable" width="100%" cellspacing="1" cellpadding="0" border="0">
      <tr class="tabtt6">
        <td nowrap="nowrap" align="center">月份</td>
        <td width="10%" nowrap="nowrap" align="center">上年结算额</td>
        <td width="10%" nowrap="nowrap" align="center">当年任务额</td>
        <td width="10%" nowrap="nowrap" align="center">当年结算额</td>
        <td width="10%" nowrap="nowrap" align="center">月度达成率</td>
        <td width="10%" nowrap="nowrap" align="center">累计达成率</td>
        <td width="10%" nowrap="nowrap" align="center">同期增长率</td>
        <td width="10%" nowrap="nowrap" align="center">上年月占全年比</td>
        <td width="10%" nowrap="nowrap" align="center">当年月占全年比</td>
      </tr>
      <c:forEach var="cur" items="${entityList1}" varStatus="vs">
        <tr style="font-family:verdana;">
          <td nowrap="nowrap" align="center">${cur.map.m}月</td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.l_total_money}" pattern="###,##0.00" />
            <c:if test="${empty cur.map.l_total_money}">--</c:if></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.cur_money_of_month_task}" pattern="###,##0.00" />
            <c:if test="${empty cur.map.cur_money_of_month_task}">--</c:if></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.total_money}" pattern="###,##0.00" />
            <c:if test="${empty cur.map.total_money}">--</c:if></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.yd_dcl}" pattern="###,##0.00" />
            <c:if test="${empty cur.map.yd_dcl}">--</c:if></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.lj_dcl}" pattern="###,##0.00" />
            <c:if test="${empty cur.map.lj_dcl}">--</c:if></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.tq_zzl}" pattern="###,##0.00" />
            <c:if test="${empty cur.map.tq_zzl}">--</c:if></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.last_month_zb}" pattern="###,##0.00" />
            <c:if test="${empty cur.map.last_month_zb}">--</c:if></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.this_month_zb}" pattern="###,##0.00" />
            <c:if test="${empty cur.map.this_month_zb}">--</c:if></td>
        </tr>
      </c:forEach>
      <c:forEach var="cur" items="${entityList2}" varStatus="vs">
        <tr style="color:#F00;font-weight:700;font-family:verdana;">
          <td nowrap="nowrap" align="center">${cur.map.m}季度</td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.l_total_money}" pattern="###,##0.00" />
            <c:if test="${empty cur.map.l_total_money}">--</c:if></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.cur_money_of_month_task}" pattern="###,##0.00" />
            <c:if test="${empty cur.map.cur_money_of_month_task}">--</c:if></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.total_money}" pattern="###,##0.00" />
            <c:if test="${empty cur.map.total_money}">--</c:if></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.yd_dcl}" pattern="###,##0.00" />
            <c:if test="${empty cur.map.yd_dcl}">--</c:if></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.lj_dcl}" pattern="###,##0.00" />
            <c:if test="${empty cur.map.lj_dcl}">--</c:if></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${map.tq_zzl}" pattern="###,##0.00" />
            <c:if test="${empty map.tq_zzl}">--</c:if></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.last_month_zb}" pattern="###,##0.00" />
            <c:if test="${empty cur.map.last_month_zb}">--</c:if></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.this_month_zb}" pattern="###,##0.00" />
            <c:if test="${empty cur.map.this_month_zb}">--</c:if></td>
        </tr>
      </c:forEach>
      <c:forEach var="cur" items="${entityList3}" varStatus="vs">
        <tr style="color:#00F;font-weight:700;font-family:verdana;font-size:14px;">
          <td nowrap="nowrap" align="center">全年</td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.l_total_money}" pattern="###,##0.00"/>
            <c:if test="${empty cur.map.l_total_money}">--</c:if></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.cur_money_of_month_task}" pattern="###,##0.00" />
            <c:if test="${empty cur.map.cur_money_of_month_task}">--</c:if></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.total_money}" pattern="###,##0.00" />
            <c:if test="${empty cur.map.total_money}">--</c:if></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.yd_dcl}" pattern="###,##0.00" />
            <c:if test="${empty cur.map.yd_dcl}">--</c:if></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.yd_dcl}" pattern="###,##0.00" />
            <c:if test="${empty cur.map.yd_dcl}">--</c:if></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${map.tq_zzl}" pattern="###,##0.00" />
            <c:if test="${empty map.tq_zzl}">--</c:if></td>
          <td nowrap="nowrap" align="right"><c:if test="${empty cur.map.l_total_money}">--</c:if>
            <c:if test="${!empty cur.map.l_total_money}">100.00</c:if></td>
          <td nowrap="nowrap" align="right"><c:if test="${empty cur.map.l_total_money}">--</c:if>
            <c:if test="${!empty cur.map.l_total_money}">100.00</c:if></td>
        </tr>
      </c:forEach>
    </table>
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[ 	                                    
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>