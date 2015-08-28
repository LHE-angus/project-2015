<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>ECharts - Generate By @isea533/abel533</title>
    <style>*{ margin:0} html,body{ height:100%} .wrapper{ min-height:100%; height:auto !important; height:100%; margin:0 auto -155px} .footer,.push{ height:155px} table.gridtable{ font-family:verdana,arial,sans-serif; font-size:11px; color:#333; border-width:1px; border-color:#666; border-collapse:collapse; margin:5px auto} table.gridtable th{ border-width:1px; padding:8px; border-style:solid; border-color:#666; background-color:#dedede} table.gridtable td{ border-width:1px; padding:8px; border-style:solid; border-color:#666; background-color:#fff} .middle{ text-align:center; margin:0 auto; width:90%; height:auto} .info{ font-size:12px; text-align:center; line-height:20px; padding:40px} .info a{ margin:0 10px; text-decoration:none; color:green}</style>
</head>
<body>
<div class="wrapper">
    <div class="middle">
<h1 style="padding: 70px 0 20px;">ECharts ${title }图 效果</h1>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="height:400px"></div>
    </div>
    <div class="push">
<a href="${url }" target="_blank">${title } 百度地址</a>
<a href="?method=Bar">Bar</a>
<a href="?method=Line">Line</a>
<a href="?method=Radar">Radar</a>
<a href="?method=Scatter">Scatter</a>
<a href="?method=mix3">mix3</a>
<a href="?method=map9">map9</a>
<a href="?method=Gauge">Gauge</a>
    </div>
</div>
<div class="footer">
    <div class="info">
ECharts - JAVA开发工具：
<a href="http://git.oschina.net/free/ECharts" target="_blank">项目地址</a>
<a href="http://echarts.baidu.com" target="_blank">ECharts地址</a>
<br/>
作者：<a href="http://blog.csdn.net/isea533" style="margin: 0;" target="_blank">@Isea533/abel533</a>
    </div>
</div>
</body>
<!-- ECharts单文件引入 -->
<!-- script src="http://echarts.baidu.com/build/echarts-plain-map.js"></script-->
<script src="${ctx }/scripts/echarts-plain-map.js"></script>
<script type="text/javascript">
// 基于准备好的dom，初始化echarts图表
var myChart = echarts.init(document.getElementById('main'));

var option =  { title : {text: '2014年分公司零售完成情况',	subtext: ' ',	sublink: ' ',	x:'center' }, tooltip : {	trigger: 'item' }, legend: {	orient: 'vertical',	x:'left',	data:['完成率'] }, dataRange: {	min : 0,	max : 100,	calculable : true,	color: ['maroon','purple','red','orange','yellow','lightgreen'] }, toolbox: {	show : true,	orient : 'vertical',	x: 'right',	y: 'center',	feature : {	    mark : {show: true},	    dataView : {show: true, readOnly: false},	    restore : {show: true},	    saveAsImage : {show: true}	} }, series : [	{	    name: '完成率',	    type: 'map',	    mapType: 'china',	    hoverable: false,	    roam:true,	    data : [],	    markPoint : {symbolSize: 5,     itemStyle: {    normal: {borderColor: '#87cefa',borderWidth: 1,  label: {    show: false}    },    emphasis: {borderColor: '#1e90ff',borderWidth: 5,label: {    show: false}    }},data : []	    },	    geoCoord: {	    }	},	{	    name: 'Top5',	    type: 'map',	    mapType: 'china',	    data:[],	    markPoint : {symbol:'emptyCircle',symbolSize : function (v){		  return 10 + v/100;},effect : {    show: true,    shadowBlur : 0},itemStyle:{    normal:{label:{show:false}    }},data : [	]	    }	} ]		}; 
// 为echarts对象加载数据
myChart.setOption(option); 
</script>
</html>