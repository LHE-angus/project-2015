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
<script type="text/javascript" src="${ctx}/scripts/charts.js"></script>
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
  <div class="rtabcont1">
		<div id="divChartSale"></div>
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	openColumn3D('${af.map.customer_type}','${af.map.chart_type}');
});

//销售额柱状图
function openColumn3D(customer_type,chart_type){
		var chart = new FusionCharts("${ctx}/manager/scripts/Charts/Column3D.swf", "ChartId","800", "560", "0", "0");
		var dataStr = "ChannelCustomerPaymentAnalysis.do?method=column3D&customer_type="+customer_type+"&chart_type="+chart_type;
		$.ajax({
		    type: "POST",
		    url: dataStr,
		    error: function(request, settings) {alert("数据加载请求失败"); },
		    success: function(data) {
		        if (data != ''){
		            chart.setDataXML(data);
		            chart.render("divChartSale");
		        }
		    }
		});
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>