<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8"> </meta>
<title>决策分析 - ${title}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style>*{ margin:0} html,.gridtable td{ border-width:1px;  border-style:solid; border-color:#666;}.middle{ text-align:center; } .info{ font-size:12px; text-align:center; line-height:20px; padding:40px} .info a{ margin:0 10px; text-decoration:none; color:green}.mychar_title{display: block;font-size: 1.5em;-webkit-margin-before: 0.83em;-webkit-margin-after: 0.83em;-webkit-margin-start: 0px;-webkit-margin-end: 0px;font-weight: bold;}
</style>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
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
<div style="padding:7px;width:98%;" class="middle">  
<div style="padding:7px;width:98%;" >
<html-el:form>
<html-el:hidden property="mod_id"/>
<strong>年度</strong>
<html-el:select property="year" styleId="year" onchange="sbt();">
	<c:forEach begin="2012" end="2015" var="cur" varStatus="vs">
		<html-el:option value="${cur}">${cur}</html-el:option>
	</c:forEach>
</html-el:select>

<strong>月份</strong>
<html-el:select property="month" onchange="sbt();" styleId="month">
	<c:forEach begin="1" end="12" var="cur" varStatus="vs">
		<html-el:option value="${cur}">${cur}</html-el:option>
	</c:forEach>
</html-el:select> 
<span style="float:right;">
<strong>分公司</strong>
<html-el:select property="fgs_id" onchange="sbt();" styleId="fgs_id">
<c:forEach items="${fgsLatlngList}" var="cur" varStatus="vs">
<html-el:option value="${cur.fgs_id }">${cur.fgs_name }</html-el:option>
</c:forEach>
</html-el:select></span>
</html-el:form>
</div> 
<table id="table1" style="width:100%;">
    <tr>
    	<td width="40%">
	        <span class="mychar_title" style="padding: 10px 0 10px;" id="title1">...</span>
	        <p style="margin-top:5px;color:blue;font: normal 14px '微软雅黑';">目标销售额:<span id="option1_b">... </span>万元 </p> 
	        <p style="margin-top:5px;color:red;font: normal 14px '微软雅黑';">实际销售额: <span id="option1_a">... </span>万元</p> 
	        <div id="main1" style="height:300px;"></div>
        </td>
        <td width="60%" valign="top" align="center" id="option_table1"> 
	      
        </td>
        </tr>
</table> 

<span class="mychar_title"  style="padding: 5px 0 10px;" id="title2"> </span>
<div id="main2" style="height:300px;"></div> 

<table id="table5" style="width:100%;height:600px;">
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
	var fgs_id = $("#fgs_id").val();
	myChart1(year,month,fgs_id);
	myChart2(year,month,fgs_id); 
	table1(year,month,fgs_id); 
	table2(year,month,fgs_id); 
}  
 
function myChart1(year,month,fgs_id){
	$("#main1").html("<img src='"+img_src+"' />"); 
	$.ajax({
		type: "POST",
		url: "<c:url value='/manager/admin/JcfxReportXswcFgs.do' />",
		data: { "method":"option1","year":year ,"month":month,"fgs_id":fgs_id},
		dataType: "json",
		sync: false,
		error: function (xhr, ajaxOptions, thrownError) {},
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
function myChart2(year,month,fgs_id){
	$("#main2").html("<img src='"+img_src+"' />"); 
	$.ajax({
		type: "POST",
		url: "<c:url value='/manager/admin/JcfxReportXswcFgs.do' />",
		data: { "method":"option2","year":year ,"month":month,"fgs_id":fgs_id},
		dataType: "json",
		sync: false,
		error: function (xhr, ajaxOptions, thrownError) {},
		success: function(result) {
			var option= $.parseJSON(result.option);  
			var title= result.title; 
			$("#title2").html(title); 
			var myChart2 = echarts.init(document.getElementById('main2')); 
			myChart2.setOption(option);
		}
	});
} 

function table1(year,month,fgs_id){
	$("#option_table1").html("<img src='"+img_src+"' />"); 
	$.ajax({
		type: "POST",
		url: "<c:url value='/manager/admin/JcfxReportXswcFgs.do' />",
		data: { "method":"table1","year":year ,"month":month,"fgs_id":fgs_id},
		dataType: "html",
		sync: false,
		error: function (xhr, ajaxOptions, thrownError) {$("#option_table1").html("网络不给力-_-!");},
		success: function(result) { 
			$("#option_table1").html(result);  
		}
	});
	// 为echarts对象加载数据	
} 

function table2(year,month,fgs_id){
	$("#option_table2").html("<img src='"+img_src+"' />"); 
	$.ajax({
		type: "POST",
		url: "<c:url value='/manager/admin/JcfxReportXswcFgs.do' />",
		data: { "method":"table2","year":year ,"month":month,"fgs_id":fgs_id},
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