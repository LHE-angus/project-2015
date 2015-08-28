<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<title>打印</title>
</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" class="printTabTop">
  <tr>
    <td colspan="7" align="center" style="font-weight:bold;font-size: 18px;">销售数据统计分析报表</td>
  </tr>
  <tr>
    <td colspan="7" align="right" >${querydate}</td>
  </tr>
</table>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" class="printTab">
  <tr>
    <td colspan="3" width="18%" align="center" nowrap="nowrap"><font class="bigall">基本信息</font></td>
    <td colspan="4" width="26%" align="center" nowrap="nowrap"><font class="bigall">当月销售情况</font></td>
    <td colspan="8" width="56%" height="30" nowrap="nowrap" align="center"><font class="bigall">环比、同比销售分析统计</font></td>
  </tr>
  <tr>
  	<td rowspan="2" width="3%" align="center" height="60" nowrap="nowrap"><font class="bigall">序号</font></td>
  	<td rowspan="2" width="7%" align="center" height="60" nowrap="nowrap"><font class="bigall">产品类型</font></td>
  	<td rowspan="2" width="8%" align="center" height="60" nowrap="nowrap"><font class="bigall">品牌</font></td>
  	<td rowspan="2" width="4%" align="center" height="60" nowrap="nowrap"><font class="bigall">当月销量</font></td>
  	<td rowspan="2" width="4%" align="center" height="60" nowrap="nowrap"><font class="bigall">当月销额</font></td>
  	<td rowspan="2" width="4%" align="center" height="60" nowrap="nowrap"><font class="bigall">销量占比</font></td>
  	<td rowspan="2" width="4%" align="center" height="60" nowrap="nowrap"><font class="bigall">销额占比</font></td>
  	<td colspan="4" width="28%" align="center" nowrap="nowrap"><font class="bigall">销售量</font></td>
  	<td colspan="4" width="28%" align="center" nowrap="nowrap"><font class="bigall">销售额</font></td>
  </tr>
  <tr>
  	<td width="7%" align="center" nowrap="nowrap"><font class="bigall">上月</font></td>
  	<td width="7%" align="center" nowrap="nowrap"><font class="bigall">环比</font></td>
  	<td width="7%" align="center" nowrap="nowrap"><font class="bigall">上年同期</font></td>
  	<td width="7%" align="center" nowrap="nowrap"><font class="bigall">同比</font></td>
  	<td width="7%" align="center" nowrap="nowrap"><font class="bigall">上月</font></td>
  	<td width="7%" align="center" nowrap="nowrap"><font class="bigall">环比</font></td>
  	<td width="7%" align="center" nowrap="nowrap"><font class="bigall">上年同期</font></td>
  	<td width="7%" align="center" nowrap="nowrap"><font class="bigall">同比</font></td>
  </tr>
  <c:if test="${search eq 'search'}">
  	<c:if test="${not empty entityList}">
	  <c:forEach items="${entityList}" var="cur" varStatus="vs">
	  	<tr>
	  	  <td height="30" align="center" nowrap="nowrap">${vs.count}</td>
	  	  <td height="30" align="center" nowrap="nowrap" class="pd_type_name">${cur.pd_type_name}</td>
	  	  <td height="30" align="center" nowrap="nowrap">${cur.brand_name}</td>
	  	  <td height="30" align="right" nowrap="nowrap">${cur.map.b_count}</td>
	  	  <td height="30" align="right" nowrap="nowrap"><fmt:formatNumber type="Currency" value="${cur.map.b_m}" /></td>
	  	  <td height="30" align="right" nowrap="nowrap">
	  	  	<c:if test="${empty cur.map.count_rate}">--</c:if>
	  	  	<c:if test="${not empty cur.map.count_rate}">
		  	  	<fmt:formatNumber type="percent" maxFractionDigits="2" value="${cur.map.count_rate}" />
	  	  	</c:if>
	  	  </td>
	  	  <td height="30" align="right" nowrap="nowrap">
	  	  	<c:if test="${empty cur.map.money_rate}">--</c:if>
	  	  	<c:if test="${not empty cur.map.money_rate}">
		  	  <fmt:formatNumber type="percent" maxFractionDigits="2" value="${cur.map.money_rate}" />
	  	  	</c:if>
	  	  </td>
	  	  <td height="30" align="right" nowrap="nowrap">${cur.map.last_month_count}</td>
	  	  <td height="30" align="right" nowrap="nowrap">
	  	  	<c:if test="${empty cur.map.hb_count_rate}">--</c:if>
	  	  	<c:if test="${not empty cur.map.hb_count_rate}">
	  	  		<fmt:formatNumber type="percent" maxFractionDigits="2" value="${cur.map.hb_count_rate}" />
	  	  	</c:if>
	 	  </td>
	  	  <td height="30" align="right" nowrap="nowrap">${cur.map.last_year_count}</td>
	  	  <td height="30" align="right" nowrap="nowrap">
	  	  	<c:if test="${empty cur.map.tb_count_rate}">--</c:if>
	  	  	<c:if test="${not empty cur.map.tb_count_rate}">
	  	  		<fmt:formatNumber type="percent" maxFractionDigits="2" value="${cur.map.tb_count_rate}" />
	  	  	</c:if>
	  	  </td>
	  	  <td height="30" align="right" nowrap="nowrap"><fmt:formatNumber type="Currency" value="${cur.map.last_month_money}" /></td>
	  	  <td height="30" align="right" nowrap="nowrap">
	  	  	<c:if test="${empty cur.map.hb_money_rate}">--</c:if>
	  	  	<c:if test="${not empty cur.map.hb_money_rate}">
	  	  		<fmt:formatNumber type="percent" maxFractionDigits="2" value="${cur.map.hb_money_rate}" />
	  	  	</c:if>
	  	  </td>
	  	  <td height="30" align="right" nowrap="nowrap"><fmt:formatNumber type="Currency" value="${cur.map.last_year_money}" /></td>
	  	  <td height="30" align="right" nowrap="nowrap">
	  	  	<c:if test="${empty cur.map.tb_money_rate}">--</c:if>
	  	  	<c:if test="${not empty cur.map.tb_money_rate}">
	  	  		<fmt:formatNumber type="percent" maxFractionDigits="2" value="${cur.map.tb_money_rate}" />
	  	  	</c:if>
	  	  </td>
	  	</tr>
	  </c:forEach>
	</c:if>
	<c:if test="${empty entityList}">
		<tr>
			<td height="30" align="center" nowrap="nowrap" colspan="15"><font color="red">无销售数据</font></td>
		</tr>
	</c:if>
  </c:if>
</table>
<c:if test="${not empty entityList}">
<div align="center">
  <input name="button" type="button" class="bgButtonPrint" value="打印" onclick="this.style.display='none';window.print();"/>
</div>
</c:if>
<c:if test="${empty entityList}">
  <div align="center">
  	<input name="button" type="button" class="dayin" value="关闭" onclick="window.close();" />
  </div>
</c:if>

<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	trMerge("pd_type_name");
});

function  trMerge(className){
   var that;
	$("." + className).each(function(){
		// alert("$(this).html(): " + $(this).html() + " $(that).html():" + $(that).html());
		if ((that != undefined) && ($(this).html() == $(that).html())) {
			rowspan = $(that).attr("rowSpan");
			if (rowspan == undefined) {
				$(that).attr("rowSpan", 1);   
				rowspan = $(that).attr("rowSpan");   
			}
			rowspan = Number(rowspan) + 1;
			$(that).attr("rowSpan", rowspan); // do your action for the colspan cell here
			$(this).remove(); // .remove(); // do your action for the old cell here
	    } else {
			that = this;
	    }
	});
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>