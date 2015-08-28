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
  <div class="rtabcont1">
   <div class="oartop" style="border-top:1px solid #E3E3E3;margin:0 0;padding: 0px 0 0 0px;">
     <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="15" style="border-left:1px solid #E3E3E3;"></td>
        <td valign="middle" id="flash_type_name"></td>
        <td align="right" style="border-right:1px solid #E3E3E3;padding-right:20px;" valign="middle">
            <select name="change_chart" id="change_chart" onchange="change_chart(this.value)">
              <option value="1">柱状图</option>
              <option value="2">折线图</option>
            </select>
        </td>
      </tr>
     </table>
    </div>
    <table align="center" width="100%" cellpadding="0" cellspacing="0">
       <tr align="center"><td><div id="chartdiv"></div></td></tr>
    </table>
  </div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
var chartTitles = ["","当前月与上个月该网点品类销售额环比图","当前与去年同期该网点品类销售额同比图","当前月与上个月该网点品类品牌销售量环比图","当前与去年同期该网点品类品牌销售量同比图"];
var last_argument = '';
//销售额柱状图 、折线图
function openChart(){
	  var flash = "MSColumn3D.swf";
	  if(type != '1') flash = "MSLine.swf";	  
	  var chart = new FusionCharts("${ctx}/manager/scripts/Charts/"+flash, "ChartId","100%", "400", "0", "0");
	  chart.setDataUrlParams("shop_id", "${af.map.shop_id}");
	  last_argument = arguments[0];
	  var paras = [];
	  last_argument = arguments[0];
	  var args = last_argument.split(",");
	  for(var i = 0;i < args.length; i++){
	      var arg_value = document.getElementById(args[i]).value;
	      paras[paras.length] = args[i]+"="+arg_value;
	  }

      var reqUrl = "EntpShopSellAnalysis.do?method=chart_sell&shop_id=${af.map.shop_id}&"+paras.join("&");
	  $.ajax({
		    type: "POST",
		    url: reqUrl,
		    error: function(request, settings) {alert("数据加载请求失败"); },
		    success: function(data) {
		        if (data != ''){
		            chart.setDataXML(data);
		            chart.render("chartdiv");
		            $("#flash_type_name").html(chartTitles[parseInt($("#chart_type").val())]);
		        }
		    }
	  });
}

function change_chart(flg){
	type = flg;
	openChart(last_argument);	
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>