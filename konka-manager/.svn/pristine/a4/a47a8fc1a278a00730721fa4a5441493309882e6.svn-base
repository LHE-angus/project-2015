<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>客户库存周转</title>
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
<div class="content" >
<c:if test="${not empty is_bi}">
<div class="sidebar"><jsp:include page="/manager/leader/left.jsp"></jsp:include>
</c:if>
</div>


<!-- Sidebar ends --> <!-- Main bar -->
<div class="mainbar"><!-- Page heading -->
<div class="page-head">
<h2 class="pull-left"><i class="icon-home"></i>客户库存周转</h2>
<div class="clearfix"></div>
</div>
<!-- Page heading ends --> <!-- Matter -->

<div class="matter">
<div class="container" align="center">

<html-el:form action="/leader/JcfxKczzKh">
	<div class="rtabcont1">
	<html-el:hidden property="method" value="jcfxzdkhList" />
	 <html-el:hidden property="mod_id"	value="${af.map.mod_id}" />
	  <html-el:hidden property="isfirst" value="first" />
<!--	  <html-el:hidden property="type" styleId="type" value="${af.map.type}" />-->
	   <html-el:hidden property="r3_code_like" styleId="r3_code_like" value="${af.map.r3_code_like}" />
		<c:if test="${not empty is_bi}">
		<html-el:hidden property="is_bi" value="yes" styleId="is_bi" />
	    </c:if>
	<table width="100%" border="0" cellspacing="5" cellpadding="0"
		class="rtable1">
		<tr>
			<td align="right"><strong class="fb">年月：</strong></td>
			<td><html-el:select property="year" styleId="year" value="${af.map.year}">
				<c:forEach items="${yearList}" var="cur">
					<html-el:option value="${cur}">${cur}年</html-el:option>
				</c:forEach>
			</html-el:select> <html-el:select property="month" styleId="month" value="${af.map.month}">
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
			</html-el:select></td>
			<td>
			<button name="button" type="submit" class="btn btn-success">搜索</button>
			<!--<input name="button" type="button" class="bgSearch"
				id="button" value="搜 索" style="font-family: Microsoft YAHEI;" /> --><!--	         <input class="but_excel" type="button" name="export_excel" disabled="true" id="export_excel" value="导出" />-->
			<input class="but5" type="button" name="Submit5" value="返回"
				onclick="history.back();return false;" /></td>
		</tr>
	</table>
	</div>
	<!--
  <div style="text-align: left;padding-left: 10px">
  	<input name="button" type="button" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" />
	<input class="but_excel" type="button" name="export_excel" disabled="disabled" id="export_excel" value="导出" />
  </div>
  -->
</html-el:form>

<div class="rtabcont_img" style="width: 100%">
<div class="widget">
<div class="widget-head">
<div class="pull-left">客户库存周转天数</div>
<div class="widget-icons pull-right"><a href="#" class="wminimize"><i
	class="icon-chevron-up"></i></a> <a href="#" class="wclose"><i
	class="icon-remove"></i></a></div>
<div class="clearfix"></div>
</div>
<div class="rtabcont1" style="overflow-x: auto;">
   <table width="100%" border="0">
   	<tr>
   		<td width="70%">${custmsg}</td>
   		<td align="right">单位：万元</td>
   	</tr>
   </table>
<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
	 <tr class="tabtt1">
          <td width="5%" align="center">序号</td>
          <td nowrap="nowrap" align="center" width="8%">产品型号</td>
          <td nowrap="nowrap" align="center" width="6%">初始库存</td>
          <td nowrap="nowrap" align="center" width="8%">期初金额</td>
          <td nowrap="nowrap" align="center" width="6%">进货数量</td>
          <td nowrap="nowrap" align="center" width="8%">进货金额</td>
          <td nowrap="nowrap" align="center" width="6%">销售数量</td>
          <td nowrap="nowrap" align="center" width="8%">销售成本</td>
          <td nowrap="nowrap" align="center" width="8%">销售金额</td>
          <td nowrap="nowrap" align="center" width="6%">剩余库存量</td>
          <td nowrap="nowrap" align="center" width="8%">剩余库存金额</td>
          <td nowrap="nowrap" align="center" width="12%">盘存时间</td>
     </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.goods_name)}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.init_num}" pattern="0" /></td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.init_money}" pattern="0.00" /></td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.come_num}" pattern="0" /></td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.come_money}" pattern="0.00" /></td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.out_num}" pattern="0" /></td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.sale_cost}" pattern="0.00" /></td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.out_money}" pattern="0.00" /></td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.init_num + cur.come_num - cur.out_num}" pattern="0" /></td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.init_money + cur.come_money - cur.out_money }" pattern="0" /></td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.opr_date}" pattern="yyyy-MM-dd HH:mm" /></td>
          </tr>
        </c:forEach>
</table>
</div>
</div>
<!--<div align="center" style="color: red;">X轴代表周转天数，Y代表期末库存</div>-->
</div>


<div class="widget" style="margin-left: 25px">
	<div class="widget-head">
	<div class="pull-left">备注</div>
	<div class="widget-icons pull-right"><a href="#"
		class="wminimize"><i class="icon-chevron-up"></i></a> <a href="#"
		class="wclose"><i class="icon-remove"></i></a></div>
	<div class="clearfix"></div>
	</div>
	<div class="widget-content referrer">
	<html-el:hidden property="method" value="view" /> <html-el:hidden
		property="mod_id" value="${af.map.mod_id}" /> <html-el:hidden
		property="isfirst" value="first" />
		<c:if test="${not empty is_bi}">
		<html-el:hidden property="is_bi" value="yes" styleId="is_bi" />
	</c:if>
	    <div align="left" style="color: red;">
	    
	         1：注：统计报表中的期初库存、本期进货、本期零售、上期零售、期末库存的金额是利用现款价进行计算的，<br/>
													即    期初库存金额=（现款价*期初库存数量）；<br/>
													         本期进货金额=（现款价*本期进货数量）；<br/>
													         本期零售金额 = （现款价*本期零售数量）；<br/>
													         上期零售=（现款价*上期零售量）；<br/>
													         期末库存金额 = （现款价*期末库存量）<br/>
								          2：金额计算取数逻辑  当月取 实时数据  非当月取月度数据<br/>
								          3：数据统计时间 ${add_date}<br/>
								          4：分组客户 是指由管理员后台对客户进行自定义分组的客户   
	    </div>
	</div>
	</div>
<div class="clear"></div>
</div>
</div>
</div>
<footer><jsp:include page="/manager/leader/foot.jsp"></jsp:include></footer>
<span class="totop"><a href="#"><i class="icon-chevron-up"></i></a></span>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript"
	src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
$("#button").click(function(){
	if (Validator.Validate(this.form, 1)){
		this.form.submit();
		}
});
});
//格式化数字
function numformat(num){
	if(null!=num){
	   return parseFloat(num).toFixed(0);
	}else{
      return 0;
	}
}
//导出excel
$("#export_excel").click(function(){
	if(confirm("提示，您确认导出数据？")){
		$("#export_id").val(1);
		$("#bottomPageForm").submit();
	}
	$("#export_id").val(0);
});
$(".sidebar #nav > li > a").removeAttr("class");
$(".sidebar #nav > li > a:eq(3)").attr("class", "open subdrop");
$(".wclose").click(function() {
	$(this).parent().parent().parent().hide(100);
});
$('.wminimize').click(function(e) {
	e.preventDefault();
	var $wcontent = $(this).parent().parent().next('.widget-content');
	if ($wcontent.is(':visible')) {
		$(this).children('i').removeClass('icon-chevron-up');
		$(this).children('i').addClass('icon-chevron-down');
	} else {
		$(this).children('i').removeClass('icon-chevron-down');
		$(this).children('i').addClass('icon-chevron-up');
	}
	$wcontent.toggle(500);
});
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
