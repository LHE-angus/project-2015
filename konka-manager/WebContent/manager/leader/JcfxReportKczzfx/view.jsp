<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>客户大类库存周转</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
.webinput {
	background: #f5f4f4;
	padding-left: 5px;
	height: 19px;
	line-height: 19px;
	/*font-family: Arial, Helvetica, sans-serif;*/
	border: #ccc solid 1px;
}

ul.ckUl {
	list-style-type: none;
	display: inline;
}

ul.ckUl li {
	float: left;
	margin: auto 5px auto 0px; /*padding:2px 5px;*/
}

input,textarea,select {
	font-family: Microsoft Yahei;
	font-size: 12px;
}

.ck-li {
	position: relative;
}

.ck-li .hidden-accessible {
	position: absolute !important;
	clip: rect(1px, 1px, 1px, 1px);
}

.ck-li .ck-default {
	font-family: Microsoft yahei, verdana, Geneva, Tahoma, Georgia;
	font-size: 12px;
	display: inline-block;
	padding: 1px 8px;
	height: 16px;
	line-height: 16px;
	border: 1px solid #CCC;
	background: #F6F6F6;
	font-weight: bold;
	color: #C4C4C4;
	cursor: pointer;
}

.ck-li .ck-hover {
	font-family: Microsoft yahei, verdana, Geneva, Tahoma, Georgia;
	font-size: 12px;
	display: inline-block;
	padding: 1px 8px;
	height: 16px;
	line-height: 16px;
	border: 1px solid #FBCB09;
	background: #FDF5CE;
	font-weight: bold;
	color: #C77405;
	cursor: pointer;
}

.ck-li .ck-visited {
	font-family: Microsoft yahei, verdana, Geneva, Tahoma, Georgia;
	font-size: 12px;
	display: inline-block;
	padding: 1px 8px;
	height: 16px;
	line-height: 16px;
	border: 2px solid #EF0F28 /*#FF4800/*FBD850*/;
	background: white url("${ctx}/styles/customer/images/ck-visited.gif")
		right bottom no-repeat;
	font-weight: bold;
	color: #EF0F28 /*#FF4800/*#EB8F00*/;
	cursor: pointer;
}

.rtable1 td {
	padding: 0px 2px;
}

.desc {
	float: left;
	color: red;
}
</style>
</head>
<c:if test="${not empty is_bi}">
	<jsp:include page="/manager/leader/extend.jsp"></jsp:include>
</c:if>
<c:if test="${empty is_bi}">
	<jsp:include page="/manager/leader/extend_not_bi.jsp"></jsp:include>
</c:if>
<body style="font-family: Microsoft Yahei;">

	<c:if test="${not empty is_bi}">
		<div class="navbar navbar-fixed-top bs-docs-nav" role="banner">
			<jsp:include page="/manager/leader/head.jsp"></jsp:include>
		</div>
	</c:if>
	<div class="content">
		<c:if test="${not empty is_bi}">
			<div class="sidebar"><jsp:include
					page="/manager/leader/left.jsp"></jsp:include>
		</c:if>
	</div>

	<!-- Sidebar ends -->
	<!-- Main bar -->
	<div class="mainbar">
		<!-- Page heading -->
		<div class="page-head">
			<h2 class="pull-left">
				<i class="icon-home"></i>客户库存周转
			</h2>
			<div class="clearfix"></div>
		</div>
		<!-- Page heading ends -->
		<!-- Matter -->

		<div class="matter">
			<div class="container" align="center">
				<html-el:form action="/leader/JcfxReportKczzfx">
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
							<html-el:hidden property="method" value="view" />
							<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
							<html-el:hidden property="isfirst" value="first" />
							<c:if test="${not empty is_bi}">
								<html-el:hidden property="is_bi" value="yes" styleId="is_bi" />
							</c:if>
							<table width="100%" border="0" cellspacing="5" cellpadding="0"
								class="table table-striped table-bordered table-hover">
								<tr>
									<td align="right"><strong class="fb">年月：</strong> <html-el:select
											property="year" styleId="year" value="${af.map.year}">
											<c:forEach items="${yearList}" var="cur">
												<html-el:option value="${cur}">${cur}年</html-el:option>
											</c:forEach>
										</html-el:select> <html-el:select property="month" styleId="month"
											value="${af.map.month}">
											<html-el:option value="01">1月</html-el:option>
											<html-el:option value="02">2月</html-el:option>
											<html-el:option value="03">3月</html-el:option>
											<html-el:option value="04">4月</html-el:option>
											<html-el:option value="05">5月</html-el:option>
											<html-el:option value="06">6月</html-el:option>
											<html-el:option value="07">7月</html-el:option>
											<html-el:option value="08">8月</html-el:option>
											<html-el:option value="09">9月</html-el:option>
											<html-el:option value="10">10月</html-el:option>
											<html-el:option value="11">11月</html-el:option>
											<html-el:option value="12">12月</html-el:option>
										</html-el:select><strong class="fb">分公司：</strong> <html-el:select
											property="branch_area_name_2" styleId="branch_area_name_2"
											style="width:100px" value="${af.map.branch_area_name_2}">
											<html-el:option value=" ">--请选择--</html-el:option>
											<c:forEach var="cur" items="${sybDeptInfoList}">
												<html-el:option value="${cur.dept_sn}">${cur.dept_name}</html-el:option>
											</c:forEach>
										</html-el:select></td>
									<td>

										<button name="button" type="submit" class="btn btn-success">搜
											索</button> <!--	         <input class="but_excel" type="button" name="export_excel" disabled="true" id="export_excel" value="导出" />-->
										<input class="but5" type="button" name="Submit5" value="返回" />
									</td>
								</tr>
							</table>
						</div>
					</div>
				</html-el:form>
				<div class="rtabcont1"><%@ include
						file="/commons/pages/messages.jsp"%></div>
				<div class="widget">
					<div class="widget-head">
						<div class="pull-left">数据(单位：万元)</div>
						<div class="widget-icons pull-right">
							<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
							<a href="#" class="wclose"><i class="icon-remove"></i></a>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="widget-content referrer">

						<table width="100%" border="0" cellspacing="5" cellpadding="0"
							class="table table-striped table-bordered table-hover">
							<tr>
								<td width="5%" nowrap="nowrap" align="center">序号</td>
								<td nowrap="nowrap" align="left">客户类型</td>
								<td nowrap="nowrap" align="right">期初库存</td>
								<td nowrap="nowrap" align="right">本期进货</td>
								<td nowrap="nowrap" width="150px;" align="right">本期销售</td>
								<td nowrap="nowrap" width="150px;" align="right">期末库存</td>
								<td nowrap="nowrap" width="150px;" align="right">本月周转天数</td>
								<td nowrap="nowrap" width="150px;" align="right">目标周转天数</td>
							</tr>
							<c:forEach items="${entityList}" var="cur" varStatus="vs">
								<tr>
									<td align="center" nowrap="nowrap">${vs.count}</td>
									<td align="left" nowrap="nowrap" title="查看细分类型库存周转率"><c:if
											test="${not empty is_bi}">
											<span style="cursor: pointer;" class="fblue"
												onclick="doNeedMethod(null, 'JcfxReportKczzfx.do','view1' ,'par_index=${cur.PAR_INDEX}&year=${af.map.year}&month=${af.map.month}&mod_id=${af.map.mod_id}&is_bi=yes&branch_area_name_2=${af.map.branch_area_name_2 }&' + $('#bottomPageForm').serialize())"')">${cur.C_COMM}</span>
										</c:if> <c:if test="${empty is_bi}">
											<span style="cursor: pointer;" class="fblue"
												onclick="doNeedMethod(null, 'JcfxReportKczzfx.do','view1' ,'par_index=${cur.PAR_INDEX}&year=${af.map.year}&month=${af.map.month}&mod_id=${af.map.mod_id}&branch_area_name_2=${af.map.branch_area_name_2 }&' + $('#bottomPageForm').serialize())"')">${cur.C_COMM}</span>
										</c:if></td>

									<td align="right" nowrap="nowrap"><fmt:formatNumber
											value="${cur.INIT_MONEY}" pattern="0.00" /></td>
									<td align="right" nowrap="nowrap"><fmt:formatNumber
											value="${cur.COME_MONEY}" pattern="0.00" /></td>
									<td align="right" nowrap="nowrap"><fmt:formatNumber
											value="${cur.OUT_MONEY}" pattern="0.00" /></td>
									<td align="right" nowrap="nowrap"><fmt:formatNumber
											value="${cur.END_MONEY}" pattern="0.00" /></td>
									<td align="right" nowrap="nowrap"><fmt:formatNumber
											value="${cur.CUR_DAY}" pattern="0" /></td>
									<td align="right" nowrap="nowrap"><fmt:formatNumber
											value="${cur.TARGET_DAY}" pattern="0" /></td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<c:if test="${not vs.last}">
						<div style="height: 10px;"></div>
					</c:if>
				</div>
				<div class="widget">
					<div class="widget-head">
						<div class="pull-left">备注</div>
						<div class="widget-icons pull-right">
							<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
							<a href="#" class="wclose"><i class="icon-remove"></i></a>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="widget-content referrer">
						<html-el:hidden property="method" value="view" />
						<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
						<html-el:hidden property="isfirst" value="first" />
						<c:if test="${not empty is_bi}">
							<html-el:hidden property="is_bi" value="yes" styleId="is_bi" />
						</c:if>
						<div align="left" style="color: red;">

							注:统计报表中<br /> 本期进货是进货金额的累积和，<br /> 本期销售是每次销售金额的累积和，<br />
							期末库存为期初库存与本期进货量的总和扣除本期销售金额得出的。<br /> 数据统计时间 ${add_date}
						</div>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	</div>

	<footer>
		<jsp:include page="/manager/leader/foot.jsp"></jsp:include>
	</footer>
	<span class="totop"><a href="#"><i class="icon-chevron-up"></i></a></span>
	<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
	<script type="text/javascript"
		src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
	<script type="text/javascript"
		src="${ctx}/commons/scripts/rowEffect.js"></script>
	<script type="text/javascript"
		src="${ctx}/commons/scripts/validator.js"></script>
	<script type="text/javascript"
		src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
	<script type="text/javascript"
		src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
	<script type="text/javascript">
	//<![CDATA[
	$(document).ready(function() {
	//导出excel
	$("#export_excel").click(function() {
		if (confirm("提示，您确认导出数据？")) {
			$("#export_id").val(1);
			$("#bottomPageForm").submit();
		}
		$("#export_id").val(0);
	});
	//]]>
</script>
	<jsp:include page="/__analytics.jsp" />
</body>
</html>
