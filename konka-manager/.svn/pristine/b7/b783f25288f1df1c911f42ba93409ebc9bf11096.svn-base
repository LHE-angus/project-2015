<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div id="divExcel" class="rtabcont2" align="center" title="${dailyDist.dist_title}" style="margin-top:4px;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td colspan="6" align="center" style="padding-bottom:10px;"><font style="font-weight:bold;font-size:20px;">${dailyDist.dist_title}</font></td>
      </tr>
    </table>
    <table id="table1" width="100%" border="1" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="20%" nowrap="nowrap" align="center">订单流水号</td>
        <td width="15%" nowrap="nowrap" align="center">销售品类</td>
        <td width="15%" nowrap="nowrap" align="center">产品型号</td>
        <td nowrap="nowrap" align="center">工厂/仓位</td>
        <td width="15%" nowrap="nowrap" align="center">销售数量（台）</td>
      </tr>
      <c:if test="${empty dailyDistDetailList}">
      	<tr>
      		<td align="center" colspan="5" style="color:#ff0000">查询无结果</td>
      	</tr>
      </c:if>
      <c:if test="${not empty dailyDistDetailList}">
	      <c:forEach var="cur" items="${dailyDistDetailList}" varStatus="vs">
	        <tr>
	          <td align="center" class="sell_bill_id"><font class="blue12px"><c:out value="${fnx:leftPad_sis(cur.sell_bill_id, 12, '0')}" /></font></td>
	          <td align="left"><font class="blue12px">${cur.pd_cls_name}</font></td>
	          <td align="left"><font class="blue12px">${cur.pd_name}</font></td>
	          <td align="left"><font class="blue12px">${cur.factory_id},${cur.store_id},${cur.store_name}</font></td>
	          <td align="center" style="mso-number-format:'\@';"><font class="blue12px">${cur.counts}</font></td>
	        </tr>
	      </c:forEach>
      </c:if>
<!--      <tr>-->
<!--      	<td colspan="4" align="center">总计</td>-->
<!--      	<td align="right"><fmt:formatNumber value="${today_total_money}" pattern="#,#00.00#" /></td>-->
<!--      </tr>-->
      </table>
      <table width="100%" style="margin-top:10px;font-weight:bold;text-align:center;font-size:14px;">
      	<tr>
      		<td>待发货订单数：${empty dailyDist.today_total_bill_count?0:dailyDist.today_total_bill_count}</td>
      		<td>待发货产品数：${empty dailyDist.today_total_pd_count?0:dailyDist.today_total_pd_count}</td>
      	</tr>
      </table>
  </div>
  <div align="left" style="margin-left:10px;margin-bottom:20px;">
    <input class="but5" type="button"  value="返回" onclick="history.back();return false;" />
    <input class="but4" type="button"  value="下载" onclick="toExcel('divExcel', '?method=load');" />
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	trMerge("sell_bill_id",$("#table1"));
});
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
