<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<html lang="en">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<!-- Title and other stuffs -->
<title>渠道管理BI分析统计系统</title>


<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content=""><script type="text/javascript"
	src="${ctx}/commons/scripts/jquery.min.js"></script>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/commons/scripts/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/commons/scripts/themes/icon.css" />
</head>

<body>

<div class="navbar navbar-fixed-top bs-docs-nav" role="banner">
<jsp:include page="/manager/leader/head.jsp"></jsp:include>
</div>
<div class="content" >
<div class="sidebar"><jsp:include page="/manager/leader/left.jsp"></jsp:include>
</div>

<!-- Sidebar ends --> <!-- Main bar -->
<div class="mainbar"><!-- Page heading -->
<div class="page-head">
<h2 class="pull-left"><i class="icon-home"></i>销售趋势分析</h2>
<div class="clearfix"></div>
</div>
<!-- Page heading ends --> <!-- Matter -->

<div class="matter">
<div class="container" align="center">

<div class="middle" style="width: 90%;">
<div style="padding: 5px;" align="center"><input type="hidden"
	id='mod_id' value='${mod_id}' /> <strong>年&nbsp;&nbsp;度</strong> <select id="year"
	class="easyui-combobox" name="year" style="width: 80px;"
	value="${year}">
	<option value="2011">2011</option>
	<option value="2012">2012</option>
	<option value="2013">2013</option>
	<option value="2014">2014</option>
	<option value="2015">2015</option>
	<option value="2016">2016</option>
	<option value="2017">2017</option>
	<option value="2018">2018</option>
	<option value="2019">2019</option>
	<option value="2020">2020</option>
	<option value="2021">2021</option>
	<option value="2022">2022</option>
</select>
&nbsp;&nbsp;&nbsp;&nbsp;
 <strong>月&nbsp;&nbsp;份</strong> <select id="month" class="easyui-combobox"
	name="month" style="width: 80px;" value="${month}">
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
	<option value="4">4</option>
	<option value="5">5</option>
	<option value="6">6</option>
	<option value="7">7</option>
	<option value="8">8</option>
	<option value="9">9</option>
	<option value="10">10</option>
	<option value="11">11</option>
	<option value="12">12</option>
</select>
&nbsp;&nbsp;&nbsp;&nbsp;
 <strong>分&nbsp;公&nbsp;司</strong> <input id="dept_id" name="dept_id"
	style="width: 100px" value="${dept_id}" />
	
	&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" id="submit" value="搜索" style="width:80px" />
	</div>


<h1 style="padding: 5px 0 10px; align: center" id="title_month"></h1>
<div id="main_month" style="height: 300px;"></div>

<h1 style="padding: 5px 0 10px;" id="title_week"></h1>
<div id="main_week" style="height: 300px;"></div>

<h1 style="padding: 5px 0 10px;" id="title_day"></h1>
<div id="main_day" style="height: 300px;"></div>
</div>




</div>
</div>

<!-- Matter ends --></div>

<!-- Mainbar ends -->
<div class="clearfix"></div>

</div>
<!-- Content ends -->

<!-- Footer starts -->
<footer>
<jsp:include page="/manager/leader/foot.jsp"></jsp:include>
</footer>

<!-- Footer ends -->

<!-- Scroll to top -->
<span class="totop"><a href="#"><i class="icon-chevron-up"></i></a></span>


<script src="${ctx }/scripts/echarts-plain-map.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/jquery.min.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/public.js"></script>


<!-- Script for this page -->
<script type="text/javascript">
$(document).ready(function(){
	$(".sidebar #nav > li > a").removeAttr("class");
	$(".sidebar #nav > li > a:eq(4)").attr("class","open subdrop");
	$(".sidebar #nav > li > a:eq(4)").click();
	
	
	
	//分公司初始化
	$('#dept_id').combobox({                 
		url:'${ctx}/manager/admin/CsAjax.do?method=getAjaxKonkaDeptList',  
	 	method:'post',              
	 	editable:false, //不可编辑状态                
	 	cache: false,               
	 	valueField:'DEPT_ID',                   
		textField:'DEPT_NAME',
		onChange:function(){
			reLoad(1);
		}
	}); 
	$("#year").combobox({  
        onChange:function(){  
        	reLoad(1);
        } 
	});
	$("#month").combobox({  
        onChange:function(){  
        	reLoad(2);
        } 
	});
	$('#dept_id').combobox("setValue", '${dept_id}');
	$('#year').combobox("setValue", '${year}');
	$('#month').combobox("setValue", '${month}');
	reLoad(1);
	
	$("#submit").click(function(){
		$('#dept_id').combobox("setValue", '${dept_id}');
		$('#year').combobox("setValue", '${year}');
		$('#month').combobox("setValue", '${month}');
		reLoad(1);
	});
	
// 	$('#dept_id').combobox("setValue", '${dept_id}');
// 	$('#year').combobox("setValue", '${year}');
// 	$('#month').combobox("setValue", '${month}');
	
	
});

function reLoad(type){
	var year =
	    $("#year").combobox("getValue"); 

	var month =  $("#month").combobox("getValue");
	var dept_id =  $("#dept_id").combobox("getValue");
	if(type=="1"){
		myChart_month(year,month,dept_id);
		myChart_week(year,month,dept_id);
		myChart_day(year,month,dept_id);
	}else{
		myChart_day(year,month,dept_id);
	}
	
	
}
function myChart_month(year,month,dept_id){
	var img_src="${ctx}/images/loading45.gif";
	$("#main_month").html("<img src='"+img_src+"' />"); 

	  $.ajax({
		  type: "POST",
		url: "${ctx}/manager/leader/JcfxReportXsqs.do?method=getOPtionMonth",
		data: {
			"year":year, "month":month,"dept_id":dept_id
		},
		dataType: "jsonp",
		jsonp: "jsonpcallback",
		//sync: false, // jsonp不支持同步
		error: function (xhr, ajaxOptions, thrownError) {$("#main_month").html("<font color='red'>网络不给力</font>");},
		success: function(result) {
			var myChart_month = echarts.init(document.getElementById('main_month')); 
			var main_month =jQuery.parseJSON(result.main_month);
			$("#title_month").html(result.title_month);
			myChart_month.setOption(main_month);
			}
	});
	
} 
function myChart_week(year,month,dept_id){
	var img_src1="${ctx}/images/loading45.gif";
	$("#main_week").html("<img src='"+img_src1+"' />"); 

	  $.ajax({
		  type: "POST",
		url: "${ctx}/manager/leader/JcfxReportXsqs.do?method=getOPtionWeek",
		data: {
			"year":year, "month":month,"dept_id":dept_id
		},
		dataType: "jsonp",
		jsonp: "jsonpcallback",
		//sync: false, // jsonp不支持同步
		error: function (xhr, ajaxOptions, thrownError) { $("#main_week").html("<font color='red'>网络不给力</font>"); },
		success: function(result) {
			var main_week =jQuery.parseJSON(result.main_week);
			var myChart_week = echarts.init(document.getElementById('main_week')); 
			$("#title_week").html(result.title_week);
			myChart_week.setOption(main_week);
			}
	});
	
}
function myChart_day(year,month,dept_id){
	var img_src2="${ctx}/images/loading45.gif";
	$("#main_day").html("<img src='"+img_src2+"' />"); 
	
	  $.ajax({
		  type: "POST",
		url: "${ctx}/manager/leader/JcfxReportXsqs.do?method=getOPtionDay",
		data: {
			"year":year, "month":month,"dept_id":dept_id
		},
		dataType: "jsonp",
		jsonp: "jsonpcallback",
		//sync: false, // jsonp不支持同步
		error: function (xhr, ajaxOptions, thrownError) { $("#main_day").html("<font color='red'>网络不给力</font>"); },
		success: function(result) {
			var main_day =jQuery.parseJSON(result.main_day);
			var myChart_day = echarts.init(document.getElementById('main_day')); 
			$("#title_day").html(result.title_day);
			myChart_day.setOption(main_day);
			}
	});
}

</script>

</body>
</html>