<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="${ctx}/scripts/flot/excanvas.min.js"></script><![endif]-->
<script type="text/javascript" src="${ctx}/scripts/fusioncharts/FusionCharts.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/flot/jquery.flot.js"></script> 
<style type="text/css">
#placeholder {
	width: 800px;
}
.demo-container {
	box-sizing: border-box;
	width: 800px;
	height: 400px;
	padding: 20px 15px 15px 15px;
	margin: 15px auto 30px auto;
	
	
}
.demo-placeholder {
	width: 100%;
	height: 100%;
	font-size: 14px;
	line-height: 1.2em;
}
.legend table {
	border-spacing: 5px;
}
</style>
</head>
<body>
<div id="chartdiv"></div>
<div class="demo-container">
	<div id="placeholder" class="demo-placeholder"></div>
</div>
<script type="text/javascript">//<![CDATA[    
$(document).ready(function(){

	//var flash = "MSColumn3D.swf"; 
	var flash = "MSStackedColumn2DLineDY.swf";
	var chart = new FusionCharts("${ctx}/scripts/fusioncharts/swf/"+flash, "ChartId", "100%", "400", "0", "0");
	//chart.setDataXML("<chart palette='2' caption='Unit Sales' xAxisName='Month' yAxisName='Units' showValues='0' decimals='0' formatNumberScale='0' useRoundEdges='1'  rotateNames='0'> <set label='Jan' value='462'  /></chart>");
    //chart.render("chartdiv");

	var dataStr = "KonkaR3RankInterface.do?method=getKonkaR3SellToJson";
	dataStr += "&user_id=0";
	dataStr += "&year=2012";
	dataStr += "&month=6";
	$.ajax({
	    type: "POST",
	    url: dataStr,
	    error: function(request, settings) {alert("数据加载请求失败"); },
	    success: function(data) {
	        if (data){
	        	if(data.status == '0'){
	        		
	        		//FusionCharts
	        		/*
	        		var xmldata = "<chart palette='2' shownames='1' showvalues='1' decimals='2'";
	        		xmldata += "numberSuffix='%' formatNumberScale='0' useRoundEdges='1' legendBorderAlpha='0' baseFontColor='666666' BaseFontSize ='12' showBorder='1' bgSWFAlpha='0' canvasBgAlpha='0'>";
	        		xmldata += "<categories>";
	        		var xml_data_set = "<dataset seriesName='任务完成%' showValues='1' color='1D8BD1'>";
	        		for(var i=0; i<data.list.length; i++){		
	        			xmldata += "<category label='"+data.list[i].dept_name+"' />";
	        			xml_data_set +="<set value='"+data.list[i].sale+"' />"
		        	}
	        		
	        		xmldata += "</categories>";
	        		xml_data_set +="</dataset>";
	        		xmldata += xml_data_set;
					xmldata += "</chart>";

		            chart.setDataXML(xmldata);
		            chart.render("chartdiv");*/
		            
		            
		            var xmldata = "<chart palette='2' shownames='1' showvalues='1' decimals='2' "; 
		            xmldata += "sNumberSuffix='%' setAdaptiveSYMin='1' showPlotBorder='1' showBorder='0' bgColor='FFFFFF' >";
		            xmldata += "<categories>";
	        		var xml_data_set1 = "<dataset><dataset seriesName='任务销售额' showValues='1' color='F1683C'>";
	        		var xml_data_set2 = "<dataset><dataset seriesName='实际销售额' showValues='1' color='3366FF'>";
	        		var xml_data_set3 = "<lineSet seriesName='任务完成%' showValues='1' color='FFFF31' lineThickness='4'>";
	        		for(var i=0; i<data.list.length; i++){		
	        			xmldata += "<category label='"+data.list[i].dept_name+"' />";
	        			xml_data_set1 +="<set value='"+data.list[i].rw_money+"' />"
	        			xml_data_set2 +="<set value='"+data.list[i].all_price+"' />"
	        			xml_data_set3 +="<set value='"+data.list[i].sale+"' />"
		        	}
	        		xmldata += "</categories>";
	        		xml_data_set1 +="</dataset></dataset>";
	        		xml_data_set2 +="</dataset></dataset>";
	        		xml_data_set3 +="</lineSet>";
	        		xmldata += xml_data_set1;
	        		xmldata += xml_data_set2;
	        		xmldata += xml_data_set3;
	        		xmldata += "</chart>";
	        		
	        		chart.setDataXML(xmldata);
		            chart.render("chartdiv");
		            
		            //jquery flot
		            
		            /*
	        		$(function() {
	        			var d1 = [];
	        			for (var i = 0; i < 14; i += 0.5) {
	        				d1.push([i, Math.sin(i)]);
	        			}

	        			var d2 = [[0, 3], [4, 8], [8, 5], [9, 13]];

	        			// A null signifies separate line segments

	        			var d3 = [[0, 12], [7, 12], null, [7, 2.5], [12, 2.5]];

	        			var d4 = [ [1,8], [2,16] ,[3,20] ];//任务销售额

	        			var d5 = [ [1,4], [2,8], [3,40] ];//实际销售额

	        			var d6 = [ [1,50], [2,60], [3,200] ];//任务完成%
	        			
	        			var d_ticks = [ [1,'北京'],[2,'西安'],[3,'长沙'] ];
	        			
	        			var d_rw = [];
	        			var d_r3 = [];
	        			var d_sale = [];
	        			var ticks = [];
	        			for(var i=0; i<data.list.length; i++){	
	        				ticks.push([i+1, data.list[i].dept_name]);
	        				d_rw.push([i+1, data.list[i].rw_money]);
	        				d_r3.push([i+1, data.list[i].all_price]);
	        				d_sale.push([i+1, data.list[i].sale]);
	        			}

	        			function euroFormatter(v, axis) {
	        				return v.toFixed(axis.tickDecimals) + "%";
	        			}

	        			$.plot("#placeholder", [
	        					{	data: d4, 
	        						label:'任务销售额', 
	        						color:'#F1683C',
	        						bars:{
	        							show: true,
	        							barWidth: 0.3,
	        							align: "right"
	        						}
	        					},
	        					{	data: d5,
	        						label: '实际销售额',
	        						color:'#3366FF',
	        						bars:{
	        							show: true,
	        							barWidth: 0.3,
	        							align: 'left'
	        						}
	        					},
	        					{	data: d6, 
	        						label: '任务完成%', 
	        						color:'#FFFF31',
	        						lines: {
	        							show: true
	        						}, 
	        						points: {
	        							show: true
	        						},
	        						yaxis: 2
	        					}
	        				],
	        				{
	        					xaxis: {
	        						//mode: 'categories',
	        						ticks: d_ticks
	        						
	        					},

	        					yaxes: [{ min: 0 }, {
	        							// align if we are to the right
	        							alignTicksWithAxis: 1,
	        							position: 'right',
	        							tickFormatter: euroFormatter
	        					}],
	        					legend: { 
	        						position: "nw",
	        						backgroundOpacity: 0 
	        						//noColumns: 0 //legend.noColumns为0,这样会让图例显示成一列
	        					},
	        					grid: {
	        						hoverable: true,
	        						borderWidth: 3,
	        						mouseActiveRadius: 50,
	        						
	        						axisMargin: 20
	        					}
	        				}
	        			);
	        			
	        			var previousPoint = null, previousLabel = null;
	        			 
	        			$.fn.UseTooltip = function () {
	        				$(this).bind("plothover", function (event, pos, item) {
	        					if (item) {
	        						if ((previousLabel != item.series.label) || (previousPoint != item.dataIndex)) {
	        							previousPoint = item.dataIndex;
	        							previousLabel = item.series.label;
	        							$("#tooltip").remove();
	        			 
	        							var x = item.datapoint[0];
	        							var y = item.datapoint[1];

	        							var color = item.series.color;
	        							var date = d_ticks[x-1][1];
	        							
	        							var unit = "";
	        			 
	        							if (item.series.label == "任务销售额") {
	        								unit = "元";
	        							} else if (item.series.label == "实际销售额") {
	        								unit = "元";
	        							} else if (item.series.label == "任务完成%") {
	        								unit = "%";
	        							}
	        			 
	        							showTooltip(item.pageX, item.pageY, color,
	        										"<strong>" + item.series.label + "</strong><br>" + date +
	        										" : <strong>" + y + "</strong> " + unit + "");
	        						}
	        					} else {
	        						$("#tooltip").remove();
	        						previousPoint = null;
	        					}
	        				});
	        			};
	        			 
	        			 
	        			function showTooltip(x, y, color, contents) {
	        				$('<div id="tooltip">' + contents + '</div>').css({
	        					position: 'absolute',
	        					display: 'none',
	        					top: y - 40,
	        					left: x - 120,
	        					border: '2px solid ' + color,
	        					padding: '3px',
	        					'font-size': '9px',
	        					'border-radius': '5px',
	        					'background-color': '#fff',
	        					'font-family': 'Verdana, Arial, Helvetica, Tahoma, sans-serif',
	        					opacity: 0.9
	        				}).appendTo("body").fadeIn(200);
	        			}

	        			$("#placeholder").UseTooltip();

	        			// Add the Flot version string to the footer

	        		});*/
		          
		            
	        	}else {
	        		alert("数据加载请求失败");
	        	}
	        	
	        }
	    }
	});
});
//]]></script>                                  
</body>
</html>