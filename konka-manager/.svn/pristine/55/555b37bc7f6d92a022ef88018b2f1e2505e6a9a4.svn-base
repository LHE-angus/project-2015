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
<h2 class="pull-left"><i class="icon-home"></i>销售趋势分析</h2>
<div class="clearfix"></div>
</div>
<!-- Page heading ends --> <!-- Matter -->

<div class="matter">
<div class="container" align="center">


			<div class="widget">
								<div class="widget-head">
									<div class="pull-left">查询条件</div>
									<div class="widget-icons pull-right">
										<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
										<a href="#" class="wclose"><i class="icon-remove"></i></a>
									</div>
									<div class="clearfix"></div>
								</div>
								<div class="widget-content referrer">
<div style="padding: 5px;" align="right"  >
<html-el:form  action="/leader/JcfxReportXsqs.do?method=init">
<c:if test="${not empty is_bi}">
		<html-el:hidden property="is_bi" value="yes" styleId="is_bi" />
	</c:if>
<strong>年&nbsp;&nbsp;度</strong> <select id="year"
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
	<option value="01">01</option>
	<option value="02">02</option>
	<option value="03">03</option>
	<option value="04">04</option>
	<option value="05">05</option>
	<option value="06">06</option>
	<option value="07">07</option>
	<option value="08">08</option>
	<option value="09">09</option>
	<option value="10">10</option>
	<option value="11">11</option>
	<option value="12">12</option>
</select>
&nbsp;&nbsp;&nbsp;&nbsp;
 <strong>分&nbsp;公&nbsp;司</strong> <input id="dept_id" name="dept_id"
	style="width: 100px" value="${dept_id}" />
	
	&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" class="btn btn-success" id="submitbut" value="搜索" style="width:80px" />
	</html-el:form>
	</div>
</div></div>
<!--年度筛选条件-月销售趋势图 开始-->
<div class="widget">
                <!-- Widget title -->
                <div class="widget-head">
                  <div class="pull-left" id="title_month" >月销售趋势图 </div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content referrer"   >
                <div  id="main_month" style="height:300px;"></div>
                </div>
                </div>
<!--年度筛选条件-月销售趋势图 -->

<div class="widget">
                <!-- Widget title -->
                <div class="widget-head">
                  <div class="pull-left" id="title_week" >周销售趋势图 </div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content referrer"   >
                <div  id="main_week" style="height:300px;"></div>
                </div>
                </div>
                
<div class="widget">
                <!-- Widget title -->
                <div class="widget-head">
                  <div class="pull-left" id="title_day" >日销售趋势图</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content referrer"   >
                <div  id="main_day" style="height:300px;"></div>
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
<script type="text/javascript"><!--
$(document).ready(function(){
	$(".sidebar #nav > li > a").removeAttr("class");
	$(".sidebar #nav > li > a:eq(1)").attr("class","open subdrop");
	$(".sidebar #nav > li > a:eq(1)").click();
	
	
	
	//分公司初始化
	$('#dept_id').combobox({                 
		url:'${ctx}/manager/admin/CsAjax.do?method=getAjaxKonkaDeptList&isNotEpp=isNotEpp',  
	 	method:'post',              
	 	editable:false, //不可编辑状态                
	 	cache: false,               
	 	valueField:'DEPT_ID',                   
		textField:'DEPT_NAME'
	}); 
	$('#dept_id').combobox("setValue", '${dept_id}');
	$('#year').combobox("setValue", '${year}');
	$('#month').combobox("setValue", '${month}');
	reLoad(1);
	
	$("#submitbut").click(function(){
		this.form.submit();
	});
	
// 	$('#dept_id').combobox("setValue", '${dept_id}');
// 	$('#year').combobox("setValue", '${year}');
// 	$('#month').combobox("setValue", '${month}');
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
	
});

function reLoad(type){
	var year =
	    $("#year").combobox("getValue"); 

	var month =  $("#month").combobox("getValue");
	var dept_id =  $("#dept_id").combobox("getValue");
	
		myChart_month(year,month,dept_id);
		myChart_week(year,month,dept_id);
		myChart_day(year,month,dept_id);
	
	
	
}
function myChart_month(year,month,dept_id){
	var img_src="${ctx}/images/loading45.gif";
	$("#main_month").html("<img src='"+img_src+"' />"); 
	var myChart_month = echarts.init(document.getElementById('main_month')); 
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
			
			var main_month =jQuery.parseJSON(result.main_month);
			//$("#title_month").html(result.title_month);
			myChart_month.setOption(main_month);
			}
	});
	
} 
function myChart_week(year,month,dept_id){
	var img_src1="${ctx}/images/loading45.gif";
	$("#main_week").html("<img src='"+img_src1+"' />"); 
	var myChart_week = echarts.init(document.getElementById('main_week')); 
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
			
			//$("#title_week").html(result.title_week);
			myChart_week.setOption(main_week);
			}
	});
	
}
function myChart_day(year,month,dept_id){
	var img_src2="${ctx}/images/loading45.gif";
	$("#main_day").html("<img src='"+img_src2+"' />"); 
	var myChart_day = echarts.init(document.getElementById('main_day')); 
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
			
		//	$("#title_day").html(result.title_day);
			myChart_day.setOption(main_day);
			}
	});
}

</script>

</body>
</html>