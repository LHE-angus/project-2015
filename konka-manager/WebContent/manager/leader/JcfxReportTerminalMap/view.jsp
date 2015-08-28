<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
		<!-- Title and other stuffs -->
		<title>渠道管理BI分析统计系统</title>


		<meta name="viewport" content="width=device-width, initial-scale=1.0">
			<meta name="author" content="">
				<script type="text/javascript"
					src="${ctx}/commons/scripts/jquery.min.js"></script>
				<link href="${ctx}/styles/global.css" rel="stylesheet"
					type="text/css" />
				<link href="${ctx}/styles/konka.css" rel="stylesheet"
					type="text/css" />
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

		<!-- Sidebar ends -->
		<!-- Main bar -->
		<div class="mainbar">
			<!-- Page heading -->
			<div class="page-head">
				<h2 class="pull-left">
					<i class="icon-home"></i>终端作战地图
				</h2>
				<div class="clearfix"></div>
			</div>
			<!-- Page heading ends -->
			<!-- Matter -->

			<div class="matter">
				<div class="container" align="center">

					<html-el:form action="/leader/JcfxReportTerminalMap">
						<div class="rtabcont1">
						<c:if test="${not empty is_bi}">
								<html-el:hidden property="is_bi" value="yes" styleId="is_bi" />
							</c:if>
							<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
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
									<table table width="100%" border="0" cellspacing="5"
										cellpadding="0"
										class="table table-striped table-bordered table-hover">
										<tr>
											<td align="right"><strong class="fb">年：</strong> <html-el:select
													property="_year" styleId="_year" style="width:100px"
													value="${_year}">
													<html-el:option value="2010">2010</html-el:option>
													<html-el:option value="2011">2011</html-el:option>
													<html-el:option value="2012">2012</html-el:option>
													<html-el:option value="2013">2013</html-el:option>
													<html-el:option value="2014">2014</html-el:option>
													<html-el:option value="2015">2015</html-el:option>
													<html-el:option value="2016">2016</html-el:option>
													<html-el:option value="2017">2017</html-el:option>
													<html-el:option value="2018">2018</html-el:option>
													<html-el:option value="2019">2019</html-el:option>
													<html-el:option value="2020">2020</html-el:option>
												</html-el:select> <select name="group_by">
													<option value="gdp">按照GDP查询</option>
													<option value="area">按照人口查询</option>

											</select></td>


											</td>
											<td><button name="button" type="submit"
													class="btn btn-success">搜 索</button>
										</tr>
									</table>
								</div>
							</div>
						</div>

					</html-el:form>




					<div id="main" style="height: 600px; width: 100%;"></div>





					<div class="widget" id="details"
						style="width: 25%; position: absolute; z-index: 9999; top: 120px; left: 20px;">
						<!-- Widget title -->
						<div class="widget-head">
							<div class="pull-left">终端信息</div>
							<div class="widget-icons pull-right">
								<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
								<a href="#" class="wclose"><i class="icon-remove"></i></a>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="widget-content referrer">
							<!-- Widget content -->

							<table class="table table-striped table-bordered table-hover">
								<tbody>
									<tr>
										<th>项目</th>
										<th>值</th>
									</tr>
									<tr>
										<td>门店</td>
										<td align="right"><span id="store_name">－</span></td>
									</tr>
									<tr>
										<td>客户</td>
										<td align="right"><span id="customer_name">－</span></td>
									</tr>
									<tr>
										<td>所在乡镇</td>
										<td align="right"><span id="town">－</span></td>
									</tr>
									<tr>
										<td>年度</td>
										<td align="right"><span id="year">－</span></td>
									</tr>
									<tr>
										<td>零售量（台）</td>
										<td align="right"><span id="sale_num">－</span></td>
									</tr>
									<tr>
										<td>零售额（元）</td>
										<td align="right"><span id="sale_price">－</span></td>
									</tr>
									<tr>
										<td>GDP（万元）</td>
										<td align="right"><span id="gdp">－</span></td>
									</tr>
									<tr>
										<td>人口（万人）</td>
										<td align="right"><span id="person_count">－</span></td>
									</tr>
									<tr>
										<td>面积（平米）</td>
										<td align="right"><span id="area">－</span></td>
									</tr>

									<tr>
										<td>客户数</td>
										<td align="right"><span id="customer_count">－</span></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<!-- Matter ends -->
		</div>

		<!-- Mainbar ends -->
		<div class="clearfix"></div>

	</div>
	<!-- Content ends -->

	<!-- Footer starts -->
	<footer> <jsp:include page="/manager/leader/foot.jsp"></jsp:include>
	</footer>

	<!-- Footer ends -->

	<!-- Scroll to top -->
	<span class="totop"><a href="#"><i class="icon-chevron-up"></i></a></span>


	<script src="${ctx }/scripts/echarts-plain-map.js"></script>
	<!--<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>-->
	<script type="text/javascript"
		src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
	<script type="text/javascript"
		src="${ctx}/commons/scripts/rowEffect.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
	<script type="text/javascript"
		src="${ctx}/commons/scripts/validator.js"></script>
	<!--<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>-->
	<script type="text/javascript"
		src="${ctx}/commons/scripts/jquery.min.js"></script>
	<script type="text/javascript"
		src="${ctx}/commons/scripts/jquery.easyui.min.js"></script>
	<script type="text/javascript"
		src="${ctx}/commons/scripts/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/public.js"></script>
	<script type="text/javascript"
		src="${ctx}/manager/leader/JcfxReportTerminalMap/echarts.js"></script>
	<script type="text/javascript"
		src="${ctx}/manager/leader/JcfxReportTerminalMap/js/esl/esl.js"></script>

	<!-- Script for this page -->
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$(".sidebar #nav > li > a").removeAttr("class");
							$(".sidebar #nav > li > a:eq(4)").attr("class",
									"open subdrop");
							$(".sidebar #nav > li > a:eq(4)").click();
							// 基于准备好的dom，初始化echarts图表

							require(
									[ 'echarts' ],
									function(ec) {
										var ecConfig = require('echarts/config'); //我在这里

										var myChart = echarts.init(document
												.getElementById('main'));

										var option = ${option};

										// 为echarts对象加载数据
										myChart.setOption(option);
										var ecConfig = require('echarts/config');
										myChart
												.on(
														ecConfig.EVENT.CLICK,
														function(param) {
															var selected = param;
															var terminal_id = selected.data.value2;
															var _year = selected.data.year;
															if (null == terminal_id) {
																return;
															}

															$
																	.ajax({
																		type : "POST",
																		url : "<c:url value='/manager/leader/JcfxReportTerminalMap.do' />",
																		data : {
																			"method" : "ajaxGetDate",
																			"terminal_id" : terminal_id,
																			"_year" : _year
																		},
																		dataType : "json",
																		sync : false,
																		error : function(
																				xhr,
																				ajaxOptions,
																				thrownError) {
																		},
																		success : function(
																				result) {
																			var data = result[0];
																			var sale_num = data.ALLNUM;
																			var sale_money = data.ALLPRICE;
																			var gdp = data.GDP;
																			var area = data.P_AREA;
																			var person_count = data.COLUMN_1;
																			var customer_count = data.COLUMN_2;
																			var town = data.TOWN;
																			var customer_name = data.KH_NAME;
																			var store_name = data.STORE_NAME;
																			$(
																					"#sale_num")
																					.empty()
																					.text(
																							sale_num = (sale_num == null) ? "-"
																									: sale_num);
																			$(
																					"#sale_price")
																					.empty()
																					.text(
																							sale_money = (sale_money == null) ? "-"
																									: sale_money);
																			$(
																					"#gdp")
																					.empty()
																					.text(
																							gdp = (gdp == null) ? "-"
																									: gdp);
																			$(
																					"#area")
																					.empty()
																					.text(
																							area = (area == null) ? "-"
																									: area);
																			$(
																					"#person_count")
																					.empty()
																					.text(
																							person_count = (person_count == null) ? "-"
																									: person_count);
																			$(
																					"#customer_count")
																					.empty()
																					.text(
																							customer_count = (customer_count == null) ? "-"
																									: customer_count);
																			$(
																			"#year")
																			.empty()
																			.text(${_year});
																			$(
																					"#town")
																					.empty()
																					.text(
																							town = (town == null) ? "-"
																									: town);
																			$(
																					"#store_name")
																					.empty()
																					.text(
																							store_name = (store_name == null) ? "-"
																									: store_name);
																			$(
																					"#customer_name")
																					.empty()
																					.text(
																							customer_name = (customer_name == null) ? "-"
																									: customer_name);
																			$(
																					"#details")
																					.show();
																			// $('.wminimize').click();
																		}
																	});
														});
										/**myChart.on(ecConfig.EVENT.DBLCLICK, function (param){
										 $("#details").hide();
										 });*/
									})
							$(".wclose").click(function() {
								$(this).parent().parent().parent().hide(100);
							});
							$('.wminimize').click(
									function(e) {
										e.preventDefault();
										var $wcontent = $(this).parent()
												.parent().next(
														'.widget-content');
										if ($wcontent.is(':visible')) {
											$(this).children('i').removeClass(
													'icon-chevron-up');
											$(this).children('i').addClass(
													'icon-chevron-down');
										} else {
											$(this).children('i').removeClass(
													'icon-chevron-down');
											$(this).children('i').addClass(
													'icon-chevron-up');
										}
										$wcontent.toggle(500);
									});

						});
	</script>

</body>
</html>