<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/manager/scripts/charts.js"></script>
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}&nbsp;&gt;&nbsp;经办排名</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <%@ include file="list.jsp" %>
  <%@ include file="search.jsp" %>
  <div class="rtabcont1">
  	<table width="100%">
  	    <tr>
  			<td><div id="chartdiv"></div></td>
  		</tr>
  		<tr>
  			<td width="100%" valign="top" >
  				<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable6">
			      <tr class="tabtt6">
			        <td width="5%" nowrap="nowrap">排名</td>
			        <td nowrap="nowrap" width="10%">经办</td>
			        <td width="10%" nowrap="nowrap">任务系数</td>
			        <td width="20%" nowrap="nowrap">任务销售额（万元）</td>
			        <td width="20%" nowrap="nowrap">实际销售额（万元）</td>
			        <td width="10%" nowrap="nowrap">销售量</td>
			        <td width="10%" nowrap="nowrap">任务完成%</td>
			      </tr>
			      <c:set var="total_money" value="0" />
	  			  <c:set var="total_num" value="0" />
			      <c:forEach items="${entityList}" var="cur" varStatus="vs">
			        <tr>
			          <td align="center">${vs.count}</td>
			          <td align="left" nowrap="nowrap">${cur[1]}</td>
			          <td align="center" nowrap="nowrap">${cur[4]}</td>
			          <td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${cur[5]}" type="currency" /></span></td>
			          <td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${cur[3]}" type="currency" /></span></td>
			          <td align="right" nowrap="nowrap">${cur[2]}</td>
			          <td align="center" nowrap="nowrap"><fmt:formatNumber type="number"  maxIntegerDigits="3" value="${cur[6]*100}" />%</td>
			        </tr>
			        <c:set var="total_money" value="${total_money+cur[3] }" />
	  			    <c:set var="total_num" value="${total_num+cur[2] }" />
	  			    <c:set var="total_rw_money" value="${total_rw_money+cur[5] }" />
			      </c:forEach>
			      <tr>
			      	<td align="right" colspan="2"><span style="font-size:14px;font-family:verdana;font-weight:700;">合计</span></td>
			      	<td align="center"><span style="font-size:14px;font-family:verdana;font-weight:700;">/</span></td>
			      	<td align="right"><span class="kz-price"><fmt:formatNumber value="${total_rw_money}" type="currency" /></span></td>
			      	<td align="right"><span class="kz-price"><fmt:formatNumber value="${total_money}" type="currency" /></span></td>
			      	<td align="right"><span style="font-size:14px;font-family:verdana;font-weight:700;">${total_num}</span></td>
			      	<td align="center"><span style="font-size:14px;font-family:verdana;font-weight:700;">/</span></td>
			      </tr>
			    </table>
  			</td>
  		</tr>
  	</table>
    
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[    
$(document).ready(function(){
	$("#span_help").click(function(){
        $("#cvtooltipClose").cvtooltip({
            panel: "#body_oarcont",
            direction: 1,                
            width: 420,
            left: 320,
            top: 5,
            speed: 500,
            delay: 10000
        });
    });
	
	if('${not empty entityList}'){
		   var flash = "MSStackedColumn2DLineDY.swf";
		   var chart = new FusionCharts("${ctx}/scripts/fusioncharts/swf/"+flash, "ChartId", "100%", "400", "0", "0");
		   chart.setDataUrlParams("tj_type"         , "${af.map.tj_type}");
		   chart.setDataUrlParams("show_top"         , "${af.map.show_top}");
		   chart.setDataUrlParams("year"         , "${af.map.year}");
		   chart.setDataUrlParams("month_start"         , "${af.map.month_start}");
		   chart.setDataUrlParams("month_end"         , "${af.map.month_end}");
		   chart.setDataUrlParams("dept_id"         , "${af.map.dept_id}");
		   chart.setDataUrlParams("l4_dept_id"         , "${af.map.l4_dept_id}");
		   chart.setDataUrlParams("l5_dept_id"         , "${af.map.l5_dept_id}");
		   chart.setDataURL("${ctx}/manager/admin/KonkaR3OrderRank.do?method=chart_200");		
		   chart.render("chartdiv");
	}
});


//]]></script>
</body>
</html>


