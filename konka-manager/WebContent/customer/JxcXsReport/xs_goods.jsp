<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/manager/scripts/charts.js"></script>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont">
<div class="oartop">
  <table width="400" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
      <td nowrap="nowrap">当前位置：${naviString}</td>
    </tr>
  </table>
</div>
<div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
</div>
<%@ include file="search.jsp" %>
<c:if test="${not empty entityList}">
<div class="rtabcont1">
<table width="100%">
	<tr>
		<td width="50%" valign="top" >
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
				<tr>
					<th align="center" width="20%"><c:if test="${af.map.tj_type eq 3}">商品</c:if><c:if test="${af.map.tj_type eq 4}">商品类型</c:if></th>
					<th align="center" width="20%">销售数量</th>
					<th align="center" width="20%">收入</th>
					<!-- 
					<th align="center" width="20%">成本</th>
					 -->
					<th align="center" width="20%">利润</th>
				</tr>
				<c:set var="total_num" value="0" />
				<c:set var="total_money" value="0" />
				<!--
				<c:set var="total_cost" value="0" />
				-->
				<c:set var="total_profit" value="0" />
				<c:forEach items="${entityList}" var="cur" varStatus="vs">
				<tr>
					<td align="left"><c:if test="${af.map.tj_type eq 3}"><a href="JxcXsReport.do?method=xs_goods_details&mod_id=${af.map.mod_id}&flag_type=500&bill_types=20,21&st_date=${af.map.st_date}&en_date=${af.map.en_date}&goods_id=${cur[0]}&type_id=${cur[6]}&partner_id=${af.map.partner_id}" style="color:#0072bc;">${cur[1]}</a></c:if>
						<c:if test="${af.map.tj_type eq 4}"><a href="JxcXsReport.do?method=xs_goods_details&mod_id=${af.map.mod_id}&flag_type=500&bill_types=20,21&st_date=${af.map.st_date}&en_date=${af.map.en_date}&type_id=${cur[0]}&partner_id=${af.map.partner_id}" style="color:#0072bc;">${cur[1]}</a></c:if>
					</td>
					<td align="right">${cur[2]}</td>
					<td align="right"><span class="kz-price-12"><fmt:formatNumber value="${cur[3]}" type="currency" /></span></td>
					<!--
					<td align="right"><span class="kz-price-12"><fmt:formatNumber value="${cur[4]}" type="currency" /></span></td>
					-->
					<td align="right"><span class="kz-price-12"><fmt:formatNumber value="${cur[5]}" type="currency" /></span></td>
				</tr>
				<c:set var="total_num" value="${total_num+cur[2]}" />
				<c:set var="total_money" value="${total_money+cur[3]}" />
				<!--
				<c:set var="total_cost" value="${total_cost+cur[4]}" />
				-->
				<c:set var="total_profit" value="${total_profit+cur[5]}" />
				</c:forEach>
				<tr>
					<td align="center"><span style="font-size:14px;font-family:verdana;font-weight:700;">总计</span></td>
					<td align="right"><span style="font-size:14px;font-family:verdana;font-weight:700;">${total_num}</span></td>
					<td align="right"><span class="kz-price"><fmt:formatNumber value="${total_money}" type="currency" /></span></td>
					<!--
					<td align="right"><span class="kz-price"><fmt:formatNumber value="${total_cost}" type="currency" /></span></td>
					-->
					<td align="right"><span class="kz-price"><fmt:formatNumber value="${total_profit}" type="currency" /></span></td>
				</tr>
			</table>
		</td>
		<td width="50%" valign="top">
			<table align="center" width="100%" cellpadding="0" cellspacing="0">
       <tr align="center"><td><div id="chartdiv"></div>
       
       </td></tr>
    </table>
		</td>
	</tr>
</table>
</div>
</c:if>

<div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript">
if(${not empty entityList}){
   var flash = "MSColumn3D.swf";
   if(${af.map.chart_type eq 2}){
   		flash = "MSLine.swf";
   }
   var chart = new FusionCharts("${ctx}/scripts/fusioncharts/swf/"+flash, "ChartId", "100%", "330", "0", "0");
   chart.setDataUrlParams("st_date"         , "${af.map.st_date}");
   chart.setDataUrlParams("en_date"         , "${af.map.en_date}");
   chart.setDataUrlParams("type_id"         , "${af.map.type_id}");
   //chart.setDataUrlParams("goods_id"         , "${af.map.goods_id}");
   chart.setDataUrlParams("partner_id"         , "${af.map.partner_id}");
   chart.setDataUrlParams("tj_type"         , "${af.map.tj_type}");
   chart.setDataUrlParams("chart_type"         , "${af.map.chart_type}");
   chart.setDataUrlParams("goods_ids"         , "${af.map.goods_ids}");
   chart.setDataURL("${ctx}/customer/manager/JxcXsReport.do?method=xs_goods_chart");		
   //chart.setDataURL("${ctx}/customer/script/data/MSLine.ftl");		     
   chart.render("chartdiv");
}
   
   selectGoodType('${af.map.type_id}');
   
function selectGoodType(type_id){
	$.ajax({
		type: "POST",
		url: "JBill.do",
		data: {method : "ajaxGetGoodsList", "type_id": type_id},
		dataType: "json",
		cache:false,
		error: function(){alert("数据加载请求失败！");},
		success: function(ret){
			if(ret){
				$("#goods_id").empty();
				var html = "<option value=''>请选择</option>";
				for(var i=0; i<ret.list.length; i++){
					if(${not empty af.map.goods_id}){
						var goods_id_ = '${af.map.goods_id}';
						if(goods_id_ == ret.list[i].goods_id){
							html += "<option value='" + ret.list[i].goods_id + "' selected='selected'>" + ret.list[i].goods_name +"</option>";
						}else {
							html += "<option value='" + ret.list[i].goods_id + "'>" + ret.list[i].goods_name +"</option>";
						}
					}else {
						html += "<option value='" + ret.list[i].goods_id + "'>" + ret.list[i].goods_name +"</option>";
					}			
				}
				$("#goods_id").html(html);

			}
		}
   });
}
</script>
<script type="text/javascript">//<![CDATA[

//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>