<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8"> </meta>
<title>决策分析 - ${title}</title>
<style>*{ margin:0} html,body{ height:100%} .wrapper{ min-height:100%; height:auto !important; height:100%; margin:0 auto -155px} .footer,.push{ height:155px} table.gridtable{ font-family:verdana,arial,sans-serif; font-size:11px; color:#333; border-width:1px; border-color:#666; border-collapse:collapse; margin:5px auto} table.gridtable th{ border-width:1px; padding:8px; border-style:solid; border-color:#666; background-color:#dedede} table.gridtable td{ border-width:1px; padding:8px; border-style:solid; border-color:#666; background-color:#fff} .middle{ text-align:center; margin:0 auto; width:90%; height:auto} .info{ font-size:12px; text-align:center; line-height:20px; padding:40px} .info a{ margin:0 10px; text-decoration:none; color:green}</style>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
</head>
<body>
<div class="wrapper">
<div class="middle" style="width:90%;">
<div style="padding:7px;">
<html-el:form>
<strong>年度</strong>
<html-el:select property="year" styleId="year" onchange="sbt();">
	<c:forEach begin="2012" end="2015" var="cur" varStatus="vs">
		<html-el:option value="${cur}">${cur}</html-el:option>
	</c:forEach>
</html-el:select>

<strong>月份</strong>
<html-el:select property="month" onchange="reLoad();" styleId="month">
	<c:forEach begin="1" end="12" var="cur" varStatus="vs">
		<html-el:option value="${cur}">${cur}</html-el:option>
	</c:forEach>
</html-el:select>
</html-el:form>
</div> 
<table id="table1" style="width:100%;">
    <tr>
    	<td width="40%">
	        <h2 style="padding: 10px 0 10px;" id="title1">...</h2> 
	        <p style="margin-top:5px;color:blue;font: normal 14px '微软雅黑';">目标零售额:<span id="option1_b">... </span>万元 </p> 
	        <p style="margin-top:5px;color:red;font: normal 14px '微软雅黑';">实际零售额: <span id="option1_a">... </span>万元</p>  
	        <div id="main1" style="height:300px;"></div>
        </td>
        <td width="60%" valign="top" align="center" id="option_table1">   
        </td>
        </tr>
</table> 

<h1 style="padding: 5px 0 10px;" id="title2"> </h1>  
<div id="main2" style="height:300px;"></div>

<table id="table2" style="width:100%;">
    <tr>
    	<td width="40%"> 
	        <div id="main3" style="height:400px;"></div>
        </td>
        <td width="60%"  align="center">  
        	<h2 style="padding: 5px 0 10px;" id="title4"></h2>  
	        <div id="main4" style="height:400px;"></div>
        </td>
    </tr>
</table> 

<table id="table5" style="width:100%;">
    <tr>
        <td width="100%" valign="top" align="center" id="option_table2">  
        </td>
        </tr>
</table> 
</div> 
</div> 
</body> 
<!-- script src="http://echarts.baidu.com/build/echarts-plain-map.js"></script-->
<script src="${ctx }/scripts/echarts-plain-map.js"></script>
<script type="text/javascript">
// 基于准备好的dom，初始化echarts图表
var img_src="${ctx}/images/loading45.gif";
function sbt(){
	document.forms[0].submit();
}
function reLoad(){
	var year = $("#year").val();
	var month = $("#month").val();
	
	table1(year,month); 
	myChart1(year,month);
	myChart2(year,month);
	myChart3(year,month);
	myChart4(year,month);
	table2(year,month);
}  
 
function myChart1(year,month){
	$("#main1").html("<img src='"+img_src+"' />"); 
	$.ajax({
		type: "POST",
		url: "<c:url value='/webservice/JcfxReportLswc.do' />",
		data: { "method":"option1","year":year ,"month":month},
		dataType: "json",
		sync: false,
		error: function (xhr, ajaxOptions, thrownError) {$("#main1").html("网络不给力-_-!");},
		success: function(result) {
			var option1 = $.parseJSON(result.option); 
			var title1= result.title; 
			var option1_a = result.option_a; 
			var option1_b = result.option_b;
			$("#title1").html(title1);
			$("#option1_a").html(option1_a);
			$("#option1_b").html(option1_b); 
			var myChart1 = echarts.init(document.getElementById('main1')); 	
			myChart1.setOption(option1);
		}
	});
	// 为echarts对象加载数据	
} 

function myChart2(year,month){
	$("#main2").html("<img src='"+img_src+"' />"); 
	$.ajax({
		type: "POST",
		url: "<c:url value='/webservice/JcfxReportLswc.do' />",
		data: { "method":"option2","year":year ,"month":month},
		dataType: "json",
		sync: false,
		error: function (xhr, ajaxOptions, thrownError) {$("#main2").html("网络不给力-_-!");},
		success: function(result) {
			var option= $.parseJSON(result.option);  
			var title= result.title; 
			$("#title2").html(title); 
			var myChart2 = echarts.init(document.getElementById('main2')); 
			myChart2.setOption(option);
		}
	});
} 

function myChart3(year,month){  
	var option= ${option3};
	var myChart3 = echarts.init(document.getElementById('main3')); 
	 myChart3.setOption(option);
	
	$.ajax({
		type: "POST",
		url: "<c:url value='/webservice/JcfxReportLswc.do' />",
		data: { "method":"option3","year":year ,"month":month},
		dataType: "json",
		sync: false,
		error: function (xhr, ajaxOptions, thrownError) {},
		success: function(result) { 
			var option= $.parseJSON(result.option);  
			var myChart3 = echarts.init(document.getElementById('main3')); 
			myChart3.setOption(option);
		}
	});
}

function myChart4(year,month){
	$("#main4").html("<img src='"+img_src+"' />"); 
	$.ajax({
		type: "POST",
		url: "<c:url value='/webservice/JcfxReportLswc.do' />",
		data: { "method":"option4","year":year ,"month":month},
		dataType: "json",
		sync: false,
		error: function (xhr, ajaxOptions, thrownError) {$("#main4").html("网络不给力-_-!");},
		success: function(result) {
			var option= $.parseJSON(result.option);   
			var selected={}; 
			var title= result.title;  
			$("#title4").html(title);  
			for(var i=0;i<option.legend.data.length;i++){
				 selected[option.legend.data[i]]=false;
			}
			 selected['北京']=true;
			 selected['上海']=true;
			 selected['广州']=true;
			 selected['天津']=true;
			 option.legend.selected = selected; 
			var myChart4 = echarts.init(document.getElementById('main4')); 
			myChart4.setOption(option);
		}
	}); 
}

function table1(year,month){
	$("#option_table1").html("<img src='"+img_src+"' />"); 
	$.ajax({
		type: "POST",
		url: "<c:url value='/webservice/JcfxReportLswc.do' />",
		data: { "method":"table1","year":year ,"month":month},
		dataType: "html",
		sync: false,
		error: function (xhr, ajaxOptions, thrownError) {$("#option_table1").html("网络不给力-_-!");},
		success: function(result) { 
			$("#option_table1").html(result); 
		}
	});
	// 为echarts对象加载数据	
} 

function table2(year,month){
	$("#option_table2").html("<img src='"+img_src+"' />"); 
	$.ajax({
		type: "POST",
		url: "<c:url value='/webservice/JcfxReportLswc.do' />",
		data: { "method":"table2","year":year ,"month":month},
		dataType: "html",
		sync: false,
		error: function (xhr, ajaxOptions, thrownError) {$("#option_table2").html("网络不给力-_-!");},
		success: function(result) { 
			$("#option_table2").html(result); 
		}
	});
	// 为echarts对象加载数据	
} 

reLoad();
</script> 
</html>