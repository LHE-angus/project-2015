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
<c:if test="${not empty is_bi}">
<jsp:include page="/manager/leader/extend.jsp"></jsp:include>
</c:if>
<c:if test="${empty is_bi}">
<jsp:include page="/manager/leader/extend_not_bi.jsp"></jsp:include>
</c:if>
<body>
  
 
<c:if test="${not empty is_bi}">
<div class="navbar navbar-fixed-top bs-docs-nav" role="banner">
<jsp:include page="/manager/leader/head.jsp"></jsp:include>
</div>
</c:if>
<div class="content" >
<c:if test="${not empty is_bi}">
<div class="sidebar"><jsp:include page="/manager/leader/left.jsp"></jsp:include>
</c:if>
</div>

<!-- Sidebar ends --> <!-- Main bar -->
<div class="mainbar"><!-- Page heading -->
<div class="page-head">
<h2 class="pull-left"><i class="icon-home"></i>分公司零售完成率</h2>
<div class="clearfix"></div>
</div>
<!-- Page heading ends --> <!-- Matter -->

<div class="matter">
<div class="container" align="center">

<div style="padding:7px;width:100%;" class="middle">  
<div style="padding:7px;width:100%;" >
<div class="widget">
<div class="widget-head">
<div class="pull-left">查询条件</div>
<div class="widget-icons pull-right"><a href="#" class="wminimize"><i
	class="icon-chevron-up"></i></a> <a href="#" class="wclose"><i
	class="icon-remove"></i></a></div>
<div class="clearfix"></div>
</div>
<div class="widget-content referrer"><html-el:form>
	<html-el:hidden property="mod_id" />
	<c:if test="${not empty is_bi}">
		<html-el:hidden property="is_bi" value="yes" styleId="is_bi" />
	</c:if>
	<table width="100%" border="0" cellspacing="5" cellpadding="0"
		class="table table-striped table-bordered table-hover">
		<tr>
			<td align="right"><strong>年度</strong> <html-el:select
				property="year" styleId="year" onchange="sbt();">
				<c:forEach begin="2012" end="2015" var="cur" varStatus="vs">
					<html-el:option value="${cur}">${cur}</html-el:option>
				</c:forEach>
			</html-el:select> <strong>月份</strong> <html-el:select property="month"
				onchange="sbt();" styleId="month">
				<c:forEach begin="1" end="12" var="cur" varStatus="vs">
					<html-el:option value="${cur}">${cur}</html-el:option>
				</c:forEach>
			</html-el:select> <strong>分公司</strong> <html-el:select property="fgs_id"
				onchange="sbt();" styleId="fgs_id">
				<c:forEach items="${fgsLatlngList}" var="cur" varStatus="vs">
					<html-el:option value="${cur.fgs_id }">${cur.fgs_name}</html-el:option>
				</c:forEach>
			</html-el:select> <html-el:hidden property="filter_by" styleId="filter_by" /></td>
			<td align="left">
			<button name="button" type="button" class="btn btn-success"
				onClick="sbt();">搜 索</button>
			</td>
		</tr>
	</table>
</html-el:form></div>
</div>
</div>
<div class="row">
<div class="col-md-4">
<div class="widget">
<div class="widget-head">
<div class="pull-left">目标完成情况(单位：万元)</div>
<div class="widget-icons pull-right"><a href="#" class="wminimize"><i
	class="icon-chevron-up"></i></a> <a href="#" class="wclose"><i
	class="icon-remove"></i></a></div>
<div class="clearfix"></div>
</div>
<div class="widget-content referrer">
<table id="table1" style="width: 100%;">
	<tr>
		<td width="40%"><span class="mychar_title"
			style="padding: 10px 0 10px;" id="title1">...</span>
		<p style="margin-top: 5px; color: blue; font: normal 14px '微软雅黑';">目标零售额:<span
			id="option1_b">... </span>万元</p>
		<p style="margin-top: 5px; color: red; font: normal 14px '微软雅黑';">实际零售额:
		<span id="option1_a">... </span>万元</p>
		<div id="main1" style="height: 300px;"></div>
		</td>

	</tr>
</table>
</div>
</div>
</div>

<div class="col-md-8">
<div class="widget">

<div class="widget-head">
<div class="pull-left">分区域目标完成情况(单位：万元)</div>
<div class="widget-icons pull-right"><a href="#" class="wminimize"><i
	class="icon-chevron-up"></i></a> <a href="#" class="wclose"><i
	class="icon-remove"></i></a></div>
<div class="clearfix"></div>
</div>
<div class="widget-content referrer">
<table class="table table-striped table-bordered table-hover" style="width:100%;height:420px;">
	<tr>
		<td width="60%" valign="top" align="center" id="option_table1"></td>
	</tr>
</table>
</div>
</div>
</div>
</div>


	<div class="widget">

							<div class="widget-head">
								<div class="pull-left">多媒体零售趋势</div>
								<div class="widget-icons pull-right">
									<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
									<a href="#" class="wclose"><i class="icon-remove"></i></a>
								</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content referrer"> 
<div id="main2" style="height:300px;"></div> 
</div></div></div>
<div class="widget">

							<div class="widget-head">
								<div class="pull-left">部门零售完成情况(单位：万元)</div>
								<div class="widget-icons pull-right">
									<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
									<a href="#" class="wclose"><i class="icon-remove"></i></a>
								</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content referrer">
<table id="table5" style="width:100%;height:800px;">
    <tr>
        <td valign="top" align="center" id="option_table2"  style="width:100%;height:100%;"> 
	       
        </td>
        </tr>
</table> 
</div></div>
</div> 
</div> 
<!-- Matter ends --></div> 
<!-- Mainbar ends -->
<div class="clearfix"></div> 
<!-- Content ends -->

<!-- Footer starts --> 
<footer>
<jsp:include page="/manager/leader/foot.jsp"></jsp:include>
</footer>

<!-- Footer ends -->

<!-- Scroll to top -->
<span class="totop"><a href="#"><i class="icon-chevron-up"></i></a></span>
 
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
	var filter_by=$("#filter_by").val();
	myChart1(year,month,fgs_id);
	myChart2(year,month,fgs_id); 
	table1(year,month,fgs_id,filter_by); 
	table2(year,month,fgs_id); 
}  
 
function myChart1(year,month,fgs_id){
	$("#main1").html("<img src='"+img_src+"' />"); 
	$.ajax({
		type: "POST",
		url: "<c:url value='/manager/leader/JcfxReportLswcFgs.do' />",
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
		url: "<c:url value='/manager/admin/JcfxReportLswcFgs.do' />",
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

function table1(year,month,fgs_id,filter_by){
	$("#option_table1").html("<img src='"+img_src+"' />"); 
	$.ajax({
		type: "POST",
		url: "<c:url value='/manager/leader/JcfxReportLswcFgs.do' />",
		data: { "method":"table1","year":year ,"month":month,"fgs_id":fgs_id,"filter_by":filter_by},
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
		url: "<c:url value='/manager/leader/JcfxReportLswcFgs.do' />",
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

	
	$(".sidebar #nav > li > a:eq(1)").attr("class","open");
	$(".sidebar #nav > li > a:eq(1)").click();

	$(".wclose").click(function(){	
		$(this).parent().parent().parent().hide(100);	
		});
		  $('.wminimize').click(function(e){
			    e.preventDefault();
			    var $wcontent = $(this).parent().parent().next('.widget-content');
			    if($wcontent.is(':visible')) 
			    {
			      $(this).children('i').removeClass('icon-chevron-up');
			      $(this).children('i').addClass('icon-chevron-down');
			    }
			    else 
			    {
			      $(this).children('i').removeClass('icon-chevron-down');
			      $(this).children('i').addClass('icon-chevron-up');
			    }            
			    $wcontent.toggle(500);
			  }); 

</script> 
</html>