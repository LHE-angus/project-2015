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
  <html-el:form action="/admin/KonkaDeptJbTaskForBackMoney">
    <html-el:hidden property="method" value="list" />
    <html-el:hidden property="mod_id" styleId="mod_id" />
    <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
        <tr>
          <td height="36" align="left" style="padding-left:5px;">&nbsp;<strong class="fb">年月：</strong>
            <html-el:select property="year" styleId="year">
              <c:forEach items="${yearList}" var="cur">
                <html-el:option value="${cur}">${cur}年</html-el:option>
              </c:forEach>
            </html-el:select>
            <html-el:select property="month" styleId="month">
              <html-el:option value="01">1月</html-el:option>
              <html-el:option value="02">2月</html-el:option>
              <html-el:option value="03">3月</html-el:option>
              <html-el:option value="04">4月</html-el:option>
              <html-el:option value="05">5月</html-el:option>
              <html-el:option value="06">6月</html-el:option>
              <html-el:option value="07">7月</html-el:option>
              <html-el:option value="08">8月</html-el:option>
              <html-el:option value="09">9月</html-el:option>
              <html-el:option value="10">10月</html-el:option>
              <html-el:option value="11">11月</html-el:option>
              <html-el:option value="12">12月</html-el:option>
            </html-el:select>
            &nbsp;&nbsp; <strong class="fb">经办类型：</strong>
            <html-el:select property="jb_type" styleId="jb_type">
              <html-el:option value="4">全部</html-el:option>
              <html-el:option value="1">A类经办</html-el:option>
              <html-el:option value="2">B类经办</html-el:option>
              <html-el:option value="3">C类经办</html-el:option>
            </html-el:select>
            &nbsp;&nbsp;&nbsp;
            <input name="button" type="submit" class="bgSearch" id="sear_btn" value="搜 索" /></td>
        </tr>
      </table>
    </div>
  </html-el:form>
  <h1 align="center" style="padding-top: 20px;font-size: 22px;">${af.map.year}年${mm}月
    <c:choose>
      <c:when test="${jb_type eq 1}">A</c:when>
      <c:when test="${jb_type eq 2}">B</c:when>
      <c:when test="${jb_type eq 3}">C</c:when>
      <c:otherwise>A、B、C</c:otherwise>
    </c:choose>
    类经办回款业绩通报（不含业绩划拨）</h1>
  <div><span style="color: gray;padding-left: 10px;">提示：“--”表示为无数据！&nbsp;数据最新同步时间：<fmt:formatDate value="${new_date}" pattern="yyyy-MM-dd HH:mm" /></span><span style="color: red;float:right; padding-right: 10px;" >单位:万元</span></div>
  <div class="rtabcont1" style="overflow-x: auto;">
    <%@ include file="/commons/pages/messages.jsp" %>
    <table class="rtable6 datatable" width="100%" cellspacing="1" cellpadding="0" border="0">
      <tr bgcolor="7EC0EE">
        <td width="7%" nowrap="nowrap" align="center">分公司</td>
        <td width="7%" nowrap="nowrap" align="center">经办名称</td>
        <td width="7%" nowrap="nowrap" align="center">经办类型</td>
        <td width="7%" nowrap="nowrap" align="center">经办经理</td>
        <td width="7%" nowrap="nowrap" align="center">任务系数</td>
        <td width="7%" nowrap="nowrap" align="center">当月任务</td>
        <td width="7%" nowrap="nowrap" align="center">当月回款</td>
        <td width="7%" nowrap="nowrap" align="center">去年同期</td>
        <td width="7%" nowrap="nowrap" align="center">回款完成率<br />%</td>
        <td width="7%" nowrap="nowrap" align="center">同比增长率<br />%</td>
        <td width="7%" nowrap="nowrap" align="center">回款完成<br />率排名</td>
        <td width="7%" nowrap="nowrap" align="center">同比增长<br />率排名</td>
        <td width="7%" nowrap="nowrap" align="center">回款排名<br />模拟奖金</td>
        <td width="7%" nowrap="nowrap" align="center">同比增长<br />模拟奖金</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <c:choose>
        <c:when test="${vs.count le 10}">
	      <tr style="background-color: #FFEBCD;">
          <td nowrap="nowrap" align="left">${cur.map.dept_name}</td>
          <td nowrap="nowrap" align="left">${cur.map.jb_name}</td>
          <td nowrap="nowrap" align="left">
          	<c:choose>
          		<c:when test="${cur.map.jb_type eq 1}">A类</c:when>
          		<c:when test="${cur.map.jb_type eq 2}">B类</c:when>
          		<c:when test="${cur.map.jb_type eq 3}">C类</c:when>
          	</c:choose>
          </td>
          <td nowrap="nowrap" align="left">${cur.map.jb_jl}</td>
          <td nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.task}" pattern="###,##0.00" />
            <c:if test="${empty cur.map.task}"><span title="未设置任务系数">--</span></c:if></td>
          <td nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.task_money}" pattern="###,##0" />
            <c:if test="${empty cur.map.task_money}"><span title="未设置当月任务">--</span></c:if></td>
          <td nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.hk_money}" pattern="###,##0" />
            <c:if test="${empty cur.map.hk_money}">0</c:if></td>
          <td nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.l_hk_money}" pattern="###,##0" />
            <c:if test="${empty cur.map.l_hk_money}">0</c:if></td>
          <td nowrap="nowrap" align="right" style="color: red;"><fmt:formatNumber value="${cur.map.hk_wcl}" pattern="###,##0.00" /></td>
          <c:choose>
          <c:when test="${cur.map.hk_zzl le 0}">
          <td nowrap="nowrap" align="right" >
          <span style="color: #008B00">
          <fmt:formatNumber value="${cur.map.hk_zzl}" pattern="###,##0.00" />
          </span>
          </td>
          </c:when>
          <c:otherwise>
          <td nowrap="nowrap" align="right" style="color: red;"><fmt:formatNumber value="${cur.map.hk_zzl}" pattern="###,##0.00" /></td>
          </c:otherwise>
          </c:choose>
          <td nowrap="nowrap" align="center" >${cur.map.hk_wc_pm}</td>
          <td nowrap="nowrap" align="center" >${cur.map.hk_zz_pm}</td>
          <c:choose>
          <c:when test="${cur.map.jj_hk_wcl gt 0}">
          <td nowrap="nowrap" align="center" style="color: red;">${cur.map.jj_hk_wcl}</td>
          </c:when>
          <c:otherwise>
          <td nowrap="nowrap" align="center" >&nbsp;</td>
          </c:otherwise>
          </c:choose>
          <c:choose>
          <c:when test="${cur.map.jj_hk_zzl gt 0}">
          <td nowrap="nowrap" align="center" style="color: red;">${cur.map.jj_hk_zzl}</td>
          </c:when>
          <c:otherwise>
          <td nowrap="nowrap" align="center" >&nbsp;</td>
          </c:otherwise>
          </c:choose>
        </tr>
        </c:when>
        
        <c:when test="${(vs.count gt (size -10)) && (vs.count le size)}">
	      <tr style="background-color:#EDEDED;">
          <td nowrap="nowrap" align="left">${cur.map.dept_name}</td>
          <td nowrap="nowrap" align="left">${cur.map.jb_name}</td>
          <td nowrap="nowrap" align="left">
          	<c:choose>
          		<c:when test="${cur.map.jb_type eq 1}">A类</c:when>
          		<c:when test="${cur.map.jb_type eq 2}">B类</c:when>
          		<c:when test="${cur.map.jb_type eq 3}">C类</c:when>
          	</c:choose>
          </td>
          <td nowrap="nowrap" align="left">${cur.map.jb_jl}</td>
          <td nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.task}" pattern="###,##0.00" />
            <c:if test="${empty cur.map.task}"><span title="未设置任务系数">--</span></c:if></td>
          <td nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.task_money}" pattern="###,##0" />
            <c:if test="${empty cur.map.task_money}"><span title="未设置当月任务">--</span></c:if></td>
          <td nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.hk_money}" pattern="###,##0" />
            <c:if test="${empty cur.map.hk_money}">0</c:if></td>
          <td nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.l_hk_money}" pattern="###,##0" />
            <c:if test="${empty cur.map.l_hk_money}">0</c:if></td>
          <td nowrap="nowrap" align="right" style="color: red;"><fmt:formatNumber value="${cur.map.hk_wcl}" pattern="###,##0.00" /></td>
          <c:choose>
          <c:when test="${cur.map.hk_zzl le 0}">
          <td nowrap="nowrap" align="right" >
          <span style="color: #008B00">
          <fmt:formatNumber value="${cur.map.hk_zzl}" pattern="###,##0.00" />
          </span>
          </td>
          </c:when>
          <c:otherwise>
          <td nowrap="nowrap" align="right" style="color: red;"><fmt:formatNumber value="${cur.map.hk_zzl}" pattern="###,##0.00" /></td>
          </c:otherwise>
          </c:choose>
          <td nowrap="nowrap" align="center" >${cur.map.hk_wc_pm}</td>
          <td nowrap="nowrap" align="center" >${cur.map.hk_zz_pm}</td>
          <c:choose>
          <c:when test="${cur.map.jj_hk_wcl gt 0}">
          <td nowrap="nowrap" align="center" style="color: red;">${cur.map.jj_hk_wcl}</td>
          </c:when>
          <c:otherwise>
          <td nowrap="nowrap" align="center" >&nbsp;</td>
          </c:otherwise>
          </c:choose>
          <c:choose>
          <c:when test="${cur.map.jj_hk_zzl gt 0}">
          <td nowrap="nowrap" align="center" style="color: red;">${cur.map.jj_hk_zzl}</td>
          </c:when>
          <c:otherwise>
          <td nowrap="nowrap" align="center" >&nbsp;</td>
          </c:otherwise>
          </c:choose>
        </tr>
        </c:when>
        <c:otherwise>
         <tr>
          <td nowrap="nowrap" align="left">${cur.map.dept_name}</td>
          <td nowrap="nowrap" align="left">${cur.map.jb_name}</td>
          <td nowrap="nowrap" align="left">
          	<c:choose>
          		<c:when test="${cur.map.jb_type eq 1}">A类</c:when>
          		<c:when test="${cur.map.jb_type eq 2}">B类</c:when>
          		<c:when test="${cur.map.jb_type eq 3}">C类</c:when>
          	</c:choose>
          </td>
          <td nowrap="nowrap" align="left">${cur.map.jb_jl}</td>
          <td nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.task}" pattern="###,##0.00" />
            <c:if test="${empty cur.map.task}"><span title="未设置任务系数">--</span></c:if></td>
          <td nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.task_money}" pattern="###,##0" />
            <c:if test="${empty cur.map.task_money}"><span title="未设置当月任务">--</span></c:if></td>
          <td nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.hk_money}" pattern="###,##0" />
            <c:if test="${empty cur.map.hk_money}">0</c:if></td>
          <td nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.l_hk_money}" pattern="###,##0" />
            <c:if test="${empty cur.map.l_hk_money}">0</c:if></td>
          <td nowrap="nowrap" align="right" style="color: red;"><fmt:formatNumber value="${cur.map.hk_wcl}" pattern="###,##0.00" /></td>
          <c:choose>
          <c:when test="${cur.map.hk_zzl le 0}">
          <td nowrap="nowrap" align="right" >
          <span style="color: #008B00">
          <fmt:formatNumber value="${cur.map.hk_zzl}" pattern="###,##0.00" />
          </span>
          </td>
          </c:when>
          <c:otherwise>
          <td nowrap="nowrap" align="right" style="color: red;"><fmt:formatNumber value="${cur.map.hk_zzl}" pattern="###,##0.00" /></td>
          </c:otherwise>
          </c:choose>
          <td nowrap="nowrap" align="center" >${cur.map.hk_wc_pm}</td>
          <td nowrap="nowrap" align="center" >${cur.map.hk_zz_pm}</td>
          <c:choose>
          <c:when test="${cur.map.jj_hk_wcl gt 0}">
          <td nowrap="nowrap" align="center" style="color: red;">${cur.map.jj_hk_wcl}</td>
          </c:when>
          <c:otherwise>
          <td nowrap="nowrap" align="center" >&nbsp;</td>
          </c:otherwise>
          </c:choose>
          <c:choose>
          <c:when test="${cur.map.jj_hk_zzl gt 0}">
          <td nowrap="nowrap" align="center" style="color: red;">${cur.map.jj_hk_zzl}</td>
          </c:when>
          <c:otherwise>
          <td nowrap="nowrap" align="center" >&nbsp;</td>
          </c:otherwise>
          </c:choose>
        </tr>
        </c:otherwise>
        </c:choose>
      </c:forEach>
      <tr>
      	<td nowrap="nowrap" align="left" style="font-weight:600;">合计</td>
      	<td nowrap="nowrap" align="left" style="font-weight:600;">---</td>
      	<td nowrap="nowrap" align="left" style="font-weight:600;">---</td>
      	<td nowrap="nowrap" align="left" style="font-weight:600;">---</td>
      	<td nowrap="nowrap" align="right" style="font-weight:600;"><fmt:formatNumber value="${task}" pattern="###,##0.00" />
         <c:if test="${empty task}">--</c:if></td>
         <td nowrap="nowrap" align="right" style="font-weight:600;"><fmt:formatNumber value="${task_money}" pattern="###,##0" />
         <c:if test="${empty task_money}">--</c:if></td>
         <td nowrap="nowrap" align="right" style="font-weight:600;"><fmt:formatNumber value="${hk_money}" pattern="###,##0" />
         <c:if test="${empty hk_money}">--</c:if></td>
         <td nowrap="nowrap" align="right" style="font-weight:600;"><fmt:formatNumber value="${l_hk_money}" pattern="###,##0" />
         <c:if test="${empty l_hk_money}">--</c:if></td>
         <td nowrap="nowrap" align="right" style="font-weight:600;"><fmt:formatNumber value="${hk_wcl}" pattern="###,##0.00" />
         <c:if test="${empty hk_wcl}">--</c:if></td>
         <td nowrap="nowrap" align="right" style="font-weight:600;"><fmt:formatNumber value="${hk_zzl}" pattern="###,##0.00" />
         <c:if test="${empty hk_zzl}">--</c:if></td>
      	<td nowrap="nowrap" align="center" style="font-weight:600;">---</td>
      	<td nowrap="nowrap" align="center" style="font-weight:600;">---</td>
      	<td nowrap="nowrap" align="center" style="font-weight:600;"><fmt:formatNumber value="${t_jj_hk_wcl}" pattern="###,##0.0" /></td>
      	<td nowrap="nowrap" align="center" style="font-weight:600;"><fmt:formatNumber value="${t_jj_hk_zzl}" pattern="###,##0.0" /></td>
      </tr>
    </table>
  </div>
  <div>&nbsp;&nbsp;备注：数据采集于R3系统，采集时间段为&nbsp;
  ${firstDay}--${yestDay}
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[ 	                                    
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>