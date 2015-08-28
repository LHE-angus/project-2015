<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
select{font-family:Microsoft YAHEI;font-size:12px;}
input{font-family:Microsoft YAHEI;font-size:12px;}
.tableClass1 {
	background-color:#ccc;
}
.tableClass1 th {
	background-image:url(../images/tabtitbg1.gif);
	height: 23px;
	line-height: 23px;
	text-align: center;
	font-size:12px; 
	color:#333; 
	font-weight:bold;
}
.tableClass1 tr {
}
.tableClass1 tr.title_top td {
	background-color: #f5f4f4;
	height: 23px;
	line-height: 23px;
	text-align: center;
	font-size:12px; 
	font-weight:bold;
}
.tableClass1 tr.title_top_no_bc td {
	height: 23px;
	line-height: 23px;
	text-align: center;
	font-size:12px; 
	font-weight:bold;
}
.tableClass1 td {
	line-height: 28px;
	background-color: #ffffff;
}
.tableClass1 td.title_item {
	background-color:#f5f4f4;
	font-weight:bold;
	text-align:right;
}
.tableClass1 td.title_item_no_bc {
	font-weight:bold;
	text-align:right;
}

tr.over td {
	background: #bcd4ec;
}
</style>
<title>订单记录</title>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body style="font-family:Microsoft Yahei;">
	<div class="oartop">
	    <table width="400" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
	        <td>当前位置：${naviString}</td>
	      </tr>
	    </table>
  	</div>
	<div class="rtabcont1">
	  <%@ include file="/commons/pages/messages.jsp" %>
	</div>
	<div class="rtabcont1" style="width:100%;overflow-x:scroll;">
	  <table  border="0" cellpadding="0" cellspacing="1" class="tableClass1">
	  		<tr>
	  			<td width="50" height="40" align="center" nowrap="nowrap" rowspan="2">序号</td>
	  			<td width="131" height="40" align="center" nowrap="nowrap" rowspan="2">商品型号</td>
	  			<c:forEach items="${jBaseStoreList}" var="j_cur" varStatus="vs">
	  				<td width="100" align="center" nowrap="nowrap" colspan="2">${j_cur.store_name}</td>
	  			</c:forEach>
	  		</tr>
	  		<tr>
	  			<c:forEach items="${jBaseStoreList}" var="j_cur" varStatus="vs">
	  				<td width="100" align="center" nowrap="nowrap">数量</td>
	  				<td width="100" align="center" nowrap="nowrap">金额</td>
	  			</c:forEach>
	  		</tr>
	  		<c:forEach var="p_cur" items="${jBaseGoodsList}" varStatus="vs1">
		  		<tr>
		  			<td width="100" align="center" nowrap="nowrap" >${vs1.count}</td>
		  			<td width="100" align="left" nowrap="nowrap" >${p_cur.goods_name}</td>
		  			<c:forEach items="${jBaseStoreList}" var="j_cur" varStatus="vs">
		  				<td width="100" align="right" nowrap="nowrap" class="num_${vs.count}">
		  					<c:set var="k" value="${j_cur.store_id}_${p_cur.goods_id}" />${empty af.map[k].stocks ? 0 : af.map[k].stocks}
		  				</td>
		  				<td width="100" align="right" nowrap="nowrap" class="price_${vs.count}">
		  					<c:set var="k" value="${j_cur.store_id}_${p_cur.goods_id}" />${empty af.map[k].total_cost ? 0 : af.map[k].total_cost}
		  				</td>
		  			</c:forEach>
		  		</tr>
	  		</c:forEach>
	  		<tr>
	  			<td width="100" align="center" nowrap="nowrap" colspan="2">合计</td>
	  			<c:forEach items="${jBaseStoreList}" var="j_cur" varStatus="vs">
	  				<td width="100" align="center" nowrap="nowrap" id="num_${vs.count}">正在查询</td>
	  				<td width="100" align="center" nowrap="nowrap" id="price_${vs.count}">正在查询</td>
	  			</c:forEach>
	  		</tr>
	  		
  	  </table>
  	</div>
  	
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/customer/script/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	var len = "${fn:length(jBaseStoreList)}";

	var last = 0;
	for (var i = 1; i < len+1; i++) {
		$(".num_" + i).each(function(){
			last += parseFloat($.trim($(this).text()));
		});
		$("#num_" + i).text(last);
	}

	var price = 0.00;
	for (var i = 1; i < len+1; i++) {
		$(".price_" + i).each(function(){
			price += parseFloat($.trim($(this).text()));
		});
		$("#price_" + i).text(price.toFixed(2));
	}
	
});

//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>