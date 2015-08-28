<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	.tabtt1 td{background-color:#eff;}
</style>
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td width="280">
          	<strong class="fb">客户名称：</strong>${konkaR3Shop.customer_name}
          </td>
          <td width="280">
          	<strong class="fb">客户R3编码：</strong>${konkaR3Shop.r3_code}
          </td>
          <td align="left"><input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
    		<input type="button" id="export_excel" value="导出" class="but_excel" onclick="toExcel('divExcel_all', '?method=toExcel');" />
    		</td>
        </tr>
      </table>
  </div>
  <div class="rtabcont1">
      <div style="text-align:right;">单位：台、元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center" nowrap="nowrap" rowspan="2">序号</td>
          <td align="center" nowrap="nowrap" rowspan="2">型号名称</td>
          <td align="center" nowrap="nowrap" colspan="3">结算（<span>总库</span>）</td>
          <td align="center" nowrap="nowrap" colspan="2">进</td>
          <td align="center" nowrap="nowrap" colspan="2">销</td>
          <td width="10%" align="center" nowrap="nowrap" rowspan="2">实时库存量</td>
          <td width="15%" align="center" nowrap="nowrap" rowspan="2">实时库存金额</td>
         </tr>
         <tr class="tabtt1">
          <td width="10%" align="center" nowrap="nowrap">期初库存量</td>
          <td width="10%" align="center" nowrap="nowrap">期初库存金额</td>
          <td width="10%" align="center" nowrap="nowrap">结算时间</td>
          <td width="10%" align="center" nowrap="nowrap">进货数量</td>
          <td width="10%" align="center" nowrap="nowrap">进货金额</td>
          <td width="10%" align="center" nowrap="nowrap">销售数量</td>
          <td width="10%" align="center" nowrap="nowrap">销售金额</td>
         </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr class="tr_${cur.map.initcount}${cur.map.incount}${cur.map.outcount} sortable">
            <td align="center" nowrap="nowrap">${vs.count }</td>
            <td align="left" nowrap="nowrap">${cur.md_name}</td>
            <td align="right" nowrap="nowrap">${cur.map.initcount}</td>
            <td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${cur.map.initmoney}" type="currency" /></span></td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.js_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            <td align="right" nowrap="nowrap">${cur.map.incount}</td>
            <td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${cur.map.inmoney}" type="currency" /></span></td>
            <td align="right" nowrap="nowrap">${cur.map.outcount}</td>
            <td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${cur.map.outmoney}" type="currency" /></span></td>
          	<td align="right" nowrap="nowrap"><span style="font-size:150%;font-weight:800;font-family:verdana;color:#C00;">${cur.map.initcount + cur.map.incount - cur.map.outcount}</span></td>
          	<td align="right" nowrap="nowrap"><span style="font-size:150%;font-weight:800;font-family:verdana;color:#C00;" class="kz-price-12"><fmt:formatNumber value="${cur.map.initmoney + cur.map.inmoney - cur.map.outmoney}" type="currency" /></span></td>
          </tr>
        </c:forEach>
      </table>
      <div style="color:#555;margin-top:20px;">
      注1：产品需要进行<a href="${ctx}/customer/manager/JsTimes.do?mod_id=199021000" style="color:#F00;text-decoration:underline;">库存初始化/结算</a>后方可查询库存；<br />
      注2：“期初库存”即最近一次的结算库存，默认采用客户“总库”（为客户仓库名称）作为产品的结算仓库；<br />    
      注3：“进货数量”统计自R3订单、进销存入库、进销存盘盈、进销存进货、进销存零售退货、手机零售通开单退货、专卖店开单退货；<br />    
      注4：“销售数量”统计自手机零售通零售数据、进销存零售开单、进销存分销、进销存盘亏、进销存进货退货、专卖店开单、R3订单退货；<br />   
      </div>
  </div>
</div>
<div class="rtabcont1" style="overflow-x: auto; display: none;" id="divExcel_all" title="${konkaR3Shop.customer_name}结算库存分析">
<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="25%">
          	<strong class="fb">客户名称：</strong>${konkaR3Shop.customer_name}
          </td>
          <td width="25%">
          	<strong class="fb">客户R3编码：</strong>${konkaR3Shop.r3_code}
          </td>
        </tr>
      </table>
      <div style="text-align:right;">单位：台、元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center" nowrap="nowrap" rowspan="2">序号</td>
          <td align="center" nowrap="nowrap" rowspan="2">型号名称</td>
          <td align="center" nowrap="nowrap" colspan="2">结算（<span>总库</span>）</td>
          <td align="center" nowrap="nowrap" colspan="2">进</td>
          <td align="center" nowrap="nowrap" colspan="2">销</td>
          <td width="15%" align="center" nowrap="nowrap" rowspan="2">实时库存</td>
         </tr>
         <tr class="tabtt1">
          <td width="10%" align="center" nowrap="nowrap">期初库存</td>
          <td width="10%" align="center" nowrap="nowrap">结算时间</td>
          <td width="10%" align="center" nowrap="nowrap">进货数量</td>
          <td width="10%" align="center" nowrap="nowrap">进货金额</td>
          <td width="10%" align="center" nowrap="nowrap">销售数量</td>
          <td width="10%" align="center" nowrap="nowrap">销售金额</td>
         </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count }</td>
            <td align="left" nowrap="nowrap">${cur.md_name}</td>
            <td align="right" nowrap="nowrap">${cur.map.initcount}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.js_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            <td align="right" nowrap="nowrap">${cur.map.incount}</td>
            <td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${cur.map.inmoney}" type="currency" /></span></td>
            <td align="right" nowrap="nowrap">${cur.map.outcount}</td>
            <td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${cur.map.outmoney}" type="currency" /></span></td>
          	<td align="right" nowrap="nowrap"><span style="font-size:150%;font-weight:800;font-family:verdana;color:#C00;">${cur.map.initcount + cur.map.incount - cur.map.outcount}</span></td>
          </tr>
        </c:forEach>
      </table>
      <div style="color:#555;margin-top:20px;">
      注1：产品需要进行库存初始化/结算后方可查询库存；<br />
      注2：“期初库存”即最近一次的结算库存，默认采用客户“总库”（为客户仓库名称）作为产品的结算仓库；<br />    
      注3：“进货数量”统计自R3订单、进销存入库、进销存盘盈、进销存进货、进销存零售退货、手机零售通开单退货、专卖店开单退货；<br />    
      注4：“销售数量”统计自手机零售通零售数据、进销存零售开单、进销存分销、进销存盘亏、进销存进货退货、专卖店开单、R3订单退货；<br />    
      </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	function filter() {
		if ($("#filter_nulls").is(":checked")) {
			$(".tr_000").hide();
			window.parent.resizeFrameHeight('mainFrame', 3);
		} else {
			$(".tr_000").show();
			window.parent.resizeFrameHeight('mainFrame', 3);
		}
	}
	filter();
	$("#filter_nulls").click(filter);

	/*
	var data = [];
	$(".sortable").each(function(row){
		$tr = $(this);
		$tr.each(function(col){
			data[row][col] = $(this).text().trim();
		});
	});	
	console.info(data);
	*/
});
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>