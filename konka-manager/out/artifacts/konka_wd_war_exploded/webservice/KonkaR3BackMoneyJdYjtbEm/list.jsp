<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<style>
.oarcont {float:left;width:100%;height:100%;background:#fff;font-size: 12px;}
.rtabcont1 {margin:8px 7px;clear:both;}

.rtable6 {border-top:1px #ccc solid;border-left:1px #ccc solid;}
.rtable6 td {border-right:1px #A1A1A1 solid;border-bottom:1px #A1A1A1 solid;padding:5px 5px 0px 5px;}
.rtable6 .tabtt6 {height:23px;background:#ED7676;}
.rtable6 .tabtt6 td {border-right:1px #e3e3e3 solid;border-bottom:1px #C00 solid;padding:5px 5px 0px 5px;;font:bold 12px "宋体";color:#FFFFFF;}
</style>
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <h1 align="center" style="padding-top: 20px;font-size: 22px;">${af.map.year}年
    <c:choose>
      <c:when test="${jb_type eq 1}">A</c:when>
      <c:when test="${jb_type eq 2}">B</c:when>
      <c:when test="${jb_type eq 3}">C</c:when>
      <c:otherwise>A、B、C</c:otherwise>
    </c:choose>
    类经办${mm}月份经办彩电销售业绩排名</h1>
  <div><span style="color: gray;padding-left: 10px;">提示：“--”表示为无数据！</span><span style="color: red;float:right; padding-right: 10px;" >单位:万元</span></div>
  <div class="rtabcont1" 	>
    <%@ include file="/commons/pages/messages.jsp" %>
    <table class="rtable6" width="100%" cellspacing="1" cellpadding="0" border="0">
      <tr class="tabtt6">
        <td width="7%" nowrap="nowrap" align="center">分公司</td>
        <td nowrap="nowrap" align="center">经办名称</td>
        <td width="7%" nowrap="nowrap" align="center">经办类型</td>
        <td width="7%" nowrap="nowrap" align="center">经办经理</td>
        <td width="7%" nowrap="nowrap" align="center">任务系数</td>
        <td width="7%" nowrap="nowrap" align="center">当月任务</td>
        <td width="7%" nowrap="nowrap" align="center">当月回款</td>
        <td width="7%" nowrap="nowrap" align="center">去年同期</td>
        <td width="7%" nowrap="nowrap" align="center">回款完成率</td>
        <td width="7%" nowrap="nowrap" align="center">同比增长率</td>
        <td width="7%" align="center">回款完成<br />率排名</td>
        <td width="7%" align="center">同比增长<br />率排名</td>
        <td width="7%" nowrap="nowrap" align="center">大板结<br />算占比</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
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
          <td nowrap="nowrap" align="left">${cur.map.leader_user_name}<c:if test="${empty cur.map.leader_user_name}">--</c:if></td>
          <td nowrap="nowrap" align="right" class="kz-price-12"><fmt:formatNumber value="${cur.map.ratio}" pattern="###,##0.00" />
            <c:if test="${empty cur.map.ratio}"><span title="未设置任务系数">--</span></c:if></td>
          <td nowrap="nowrap" align="right" class="kz-price-12"><fmt:formatNumber value="${cur.map.hk_rw}" pattern="###,##0.00" />
            <c:if test="${empty cur.map.hk_rw}"><span title="未设置当月任务">--</span></c:if></td>
          <td nowrap="nowrap" align="right" class="kz-price-12"><fmt:formatNumber value="${cur.map.hk_money}" pattern="###,##0.00" />
            <c:if test="${empty cur.map.hk_money}">0.00</c:if></td>
          <td nowrap="nowrap" align="right" class="kz-price-12"><fmt:formatNumber value="${cur.map.l_hk_money}" pattern="###,##0.00" />
            <c:if test="${empty cur.map.l_hk_money}">0.00</c:if></td>
          <td nowrap="nowrap" align="right" class="kz-price-12"><fmt:formatNumber value="${cur.map.hk_wcl}" pattern="###,##0.00" /></td>
          <td nowrap="nowrap" align="right" class="kz-price-12"><fmt:formatNumber value="${cur.map.hk_zzl}" pattern="###,##0.00" /></td>
          <td nowrap="nowrap" align="center" class="kz-price-12">${cur.map.hk_wc_pm}</td>
          <td nowrap="nowrap" align="center" class="kz-price-12">${cur.map.hk_zz_pm}</td>
          <td nowrap="nowrap" align="right" class="kz-price-12"><fmt:formatNumber value="${cur.map.db_zb}" pattern="###,##0.00" /></td>
        </tr>
      </c:forEach>
    </table>
  </div>
</div>
<script type="text/javascript">//<![CDATA[ 	                                    
//]]></script>
</body>
</html>