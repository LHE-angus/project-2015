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
  <html-el:form action="/admin/KonkaFgsSailsTop">
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
            &nbsp;&nbsp;&nbsp;
            <input name="button" type="submit" class="bgSearch" id="sear_btn" value="搜 索" /></td>
        </tr>
      </table>
    </div>
  </html-el:form>
  <h1 align="center" style="padding-top: 20px;font-size: 22px;">${yyyy}年${mm}月份分公司、直管经营部彩电销售业绩排名</h1>
  <div><span style="color: gray;padding-left: 10px;">提示：“--”表示为无数据！</span><span style="color: red;float:right; padding-right: 10px;" >单位:台、万元，平均单价为元</span></div>
  <div class="rtabcont1" style="overflow-x: auto;">
    <%@ include file="/commons/pages/messages.jsp" %>
    <table class="rtable6" width="100%" cellspacing="1" cellpadding="0" border="0">
      <tr class="tabtt6">
        <td width="5%" align="center" rowspan="3">排名</td>
        <td width="6%" align="center" rowspan="3">分公司/直管经营部</td>
        <td width="6%" align="center" rowspan="3">系数%</td>
        <td colspan="5" align="center" style="border-bottom: 1px solid #E3E3E3;">销售业绩</td>
        <td colspan="7" align="center" style="border-bottom: 1px solid #E3E3E3;">销售结构</td>
        <td colspan="2" rowspan="2" align="center" style="border-bottom: 1px solid #E3E3E3;">零售业绩</td>
      </tr>
      <tr class="tabtt6">
        <td colspan="3" align="center" style="border-bottom: 1px solid #E3E3E3;">业绩完成情况</td>
        <td colspan="2" align="center" style="border-bottom: 1px solid #E3E3E3;">业绩增长情况</td>
        <td colspan="4" align="center" style="border-bottom: 1px solid #E3E3E3;">销量</td>
        <td colspan="2" align="center" style="border-bottom: 1px solid #E3E3E3;">内部占比</td>
        <td rowspan="2" width="6%" nowrap="nowrap" align="center">平均单价</td>
      </tr>
      <tr class="tabtt6">
        <td width="6%" nowrap="nowrap" align="center">销售任务</td>
        <td width="6%" nowrap="nowrap" align="center">实际含税销额</td>
        <td width="6%" nowrap="nowrap" align="center">任务完成率%</td>
        <td width="6%" nowrap="nowrap" align="center">去年同期</td>
        <td width="6%" nowrap="nowrap" align="center">同比增长率%</td>
        <td width="6%" nowrap="nowrap" align="center">3D</td>
        <td width="6%" nowrap="nowrap" align="center">大板</td>
        <td width="6%" nowrap="nowrap" align="center">安卓</td>
        <td width="6%" nowrap="nowrap" align="center">平板</td>
        <td width="6%" nowrap="nowrap" align="center">3D%</td>
        <td width="6%" nowrap="nowrap" align="center">大板%</td>
        <td width="6%" nowrap="nowrap" align="center">零售额</td>
        <td width="6%" nowrap="nowrap" align="center">零售量</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
   		<c:choose>
   			<c:when test="${vs.count le 10}">
	        <tr style="background-color: #FFEBCD;">
	          <td nowrap="nowrap" align="center">${vs.count}</td>
	          <td nowrap="nowrap" align="left">${cur.map.dept_name}</td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.ratio}" pattern="###,##0.00" /></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.cur_money_of_month_task}" pattern="###,##0" /></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.hs_money}" pattern="###,##0.00" /></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.rw_wcl}" pattern="###,##0.00" />
	            <c:if test="${empty cur.map.rw_wcl}">--</c:if></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.l_hs_money}" pattern="###,##0.00" /></td>
	          <td nowrap="nowrap" align="right">
		          <c:choose>
		          	<c:when test="${cur.map.tb_zzl lt 0}"><span style="color: red;"><fmt:formatNumber value="${cur.map.tb_zzl}" pattern="###,##0.00" /></span></c:when>
		          	<c:when test="${empty cur.map.tb_zzl}">--</c:when>
		          	<c:otherwise><fmt:formatNumber value="${cur.map.tb_zzl}" pattern="###,##0.00" /></c:otherwise>
		          </c:choose>
	          </td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.d3_num}" pattern="###,##0" /></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.db_num}" pattern="###,##0" /></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.int_num}" pattern="###,##0" /></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.pb_num}" pattern="###,##0" /></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.d3_zb}" pattern="###,##0.00" />
	            <c:if test="${empty cur.map.d3_zb}">--</c:if></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.db_zb}" pattern="###,##0.00" />
	            <c:if test="${empty cur.map.d3_zb}">--</c:if></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.price}" pattern="###,##0.00" />
	            <c:if test="${empty cur.map.price}">--</c:if></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.all_price}" pattern="###,##0.00" />
	            <c:if test="${empty cur.map.all_price}">--</c:if></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.num}" pattern="###,##0" />
	            <c:if test="${empty cur.map.num}">--</c:if></td>
	        </tr>
	        </c:when>
	        <c:when test="${vs.count gt (size - 10)}">
	         <tr style="background-color:#97FFFF;">
	          <td nowrap="nowrap" align="center">${vs.count}</td>
	          <td nowrap="nowrap" align="left">${cur.map.dept_name}</td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.ratio}" pattern="###,##0.00" /></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.cur_money_of_month_task}" pattern="###,##0" /></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.hs_money}" pattern="###,##0.00" /></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.rw_wcl}" pattern="###,##0.00" />
	            <c:if test="${empty cur.map.rw_wcl}">--</c:if></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.l_hs_money}" pattern="###,##0.00" /></td>
	          <td nowrap="nowrap" align="right">
		          <c:choose>
		          	<c:when test="${cur.map.tb_zzl lt 0}"><span style="color: red;"><fmt:formatNumber value="${cur.map.tb_zzl}" pattern="###,##0.00" /></span></c:when>
		          	<c:when test="${empty cur.map.tb_zzl}">--</c:when>
		          	<c:otherwise><fmt:formatNumber value="${cur.map.tb_zzl}" pattern="###,##0.00" /></c:otherwise>
		          </c:choose>
	          </td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.d3_num}" pattern="###,##0" /></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.db_num}" pattern="###,##0" /></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.int_num}" pattern="###,##0" /></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.pb_num}" pattern="###,##0" /></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.d3_zb}" pattern="###,##0.00" />
	            <c:if test="${empty cur.map.d3_zb}">--</c:if></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.db_zb}" pattern="###,##0.00" />
	            <c:if test="${empty cur.map.d3_zb}">--</c:if></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.price}" pattern="###,##0.00" />
	            <c:if test="${empty cur.map.price}">--</c:if></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.all_price}" pattern="###,##0.00" />
	            <c:if test="${empty cur.map.all_price}">--</c:if></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.num}" pattern="###,##0" />
	            <c:if test="${empty cur.map.num}">--</c:if></td>
	        </tr>
	        </c:when>
	        <c:otherwise>
	        <tr>
	          <td nowrap="nowrap" align="center">${vs.count}</td>
	          <td nowrap="nowrap" align="left">${cur.map.dept_name}</td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.ratio}" pattern="###,##0.00" /></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.cur_money_of_month_task}" pattern="###,##0" /></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.hs_money}" pattern="###,##0.00" /></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.rw_wcl}" pattern="###,##0.00" />
	            <c:if test="${empty cur.map.rw_wcl}">--</c:if></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.l_hs_money}" pattern="###,##0.00" /></td>
	          <td nowrap="nowrap" align="right">
		          <c:choose>
		          	<c:when test="${cur.map.tb_zzl lt 0}"><span style="color: red;"><fmt:formatNumber value="${cur.map.tb_zzl}" pattern="###,##0.00" /></span></c:when>
		          	<c:when test="${empty cur.map.tb_zzl}">--</c:when>
		          	<c:otherwise><fmt:formatNumber value="${cur.map.tb_zzl}" pattern="###,##0.00" /></c:otherwise>
		          </c:choose>
	          </td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.d3_num}" pattern="###,##0" /></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.db_num}" pattern="###,##0" /></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.int_num}" pattern="###,##0" /></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.pb_num}" pattern="###,##0" /></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.d3_zb}" pattern="###,##0.00" />
	            <c:if test="${empty cur.map.d3_zb}">--</c:if></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.db_zb}" pattern="###,##0.00" />
	            <c:if test="${empty cur.map.d3_zb}">--</c:if></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.price}" pattern="###,##0.00" />
	            <c:if test="${empty cur.map.price}">--</c:if></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.all_price}" pattern="###,##0.00" />
	            <c:if test="${empty cur.map.all_price}">--</c:if></td>
	          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.map.num}" pattern="###,##0" />
	            <c:if test="${empty cur.map.num}">--</c:if></td>
	        </tr>
	        </c:otherwise>
        </c:choose>
      </c:forEach>
      <tr>
       <td nowrap="nowrap" align="center"></td>
       <td nowrap="nowrap" align="center" style="font-weight:600;">合计</td>
       <td nowrap="nowrap" align="right" style="font-weight:600;"><fmt:formatNumber value="${t_ratio}" pattern="###,##0.00" /></td>
       <td nowrap="nowrap" align="right" style="font-weight:600;"><fmt:formatNumber value="${t_cur_money_of_month_task}" pattern="###,##0" /></td>
       <td nowrap="nowrap" align="right" style="font-weight:600;"><fmt:formatNumber value="${t_hs_money}" pattern="###,##0.00" /></td>
       <td nowrap="nowrap" align="right" style="font-weight:600;"><fmt:formatNumber value="${t_rw_wcl}" pattern="###,##0.00" />
         <c:if test="${empty t_rw_wcl}">--</c:if></td>
       <td nowrap="nowrap" align="right" style="font-weight:600;"><fmt:formatNumber value="${t_l_hs_money}" pattern="###,##0.00" /></td>
       <td nowrap="nowrap" align="right" style="font-weight:600;">
        <c:choose>
        	<c:when test="${t_tb_zzl lt 0}"><span style="color: red;"><fmt:formatNumber value="${t_tb_zzl}" pattern="###,##0.00" /></span></c:when>
        	<c:when test="${empty t_tb_zzl}">--</c:when>
        	<c:otherwise><fmt:formatNumber value="${t_tb_zzl}" pattern="###,##0.00" /></c:otherwise>
        </c:choose>
       </td>
       <td nowrap="nowrap" align="right" style="font-weight:600;"><fmt:formatNumber value="${t_d3_num}" pattern="###,##0" /></td>
       <td nowrap="nowrap" align="right" style="font-weight:600;"><fmt:formatNumber value="${t_db_num}" pattern="###,##0" /></td>
       <td nowrap="nowrap" align="right" style="font-weight:600;"><fmt:formatNumber value="${t_int_num}" pattern="###,##0" /></td>
       <td nowrap="nowrap" align="right" style="font-weight:600;"><fmt:formatNumber value="${t_pb_num}" pattern="###,##0" /></td>
       <td nowrap="nowrap" align="right" style="font-weight:600;"><fmt:formatNumber value="${t_d3_zb}" pattern="###,##0.00" />
         <c:if test="${empty t_d3_zb}">--</c:if></td>
       <td nowrap="nowrap" align="right" style="font-weight:600;"><fmt:formatNumber value="${t_db_zb}" pattern="###,##0.00" />
         <c:if test="${empty t_db_zb}">--</c:if></td>
       <td nowrap="nowrap" align="right" style="font-weight:600;"><fmt:formatNumber value="${sale_price}" pattern="###,##0.00" />
         <c:if test="${empty sale_price}">--</c:if></td>
       <td nowrap="nowrap" align="right" style="font-weight:600;"><fmt:formatNumber value="${sale_all_price}" pattern="###,##0.00" />
         <c:if test="${empty sale_all_price}">--</c:if></td>
       <td nowrap="nowrap" align="right" style="font-weight:600;"><fmt:formatNumber value="${sale_num}" pattern="###,##0" />
         <c:if test="${empty sale_num}">--</c:if></td>
	 </tr>
    </table>
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[                                        
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>