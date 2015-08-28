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
</head>
<body>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div id="divExcel" class="rtabcont2" align="center" title="${stm_title}" style="margin-top:4px;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td colspan="6" align="center" style="padding-bottom:10px;"><font style="font-weight:bold;font-size:20px;">${stm_title}</font></td>
      </tr>
    </table>
    <table id="table1" width="100%" border="1" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="15%" nowrap="nowrap" align="center">订单流水号</td>
        <td width="15%" nowrap="nowrap" align="center">付款方式</td>
        <td width="15%" nowrap="nowrap" align="center">销售品类</td>
        <td width="15%" nowrap="nowrap" align="center">产品型号</td>
        <td width="15%" nowrap="nowrap" align="center">销售单价（元）</td>
        <td width="10%" nowrap="nowrap" align="center">销售数量（台）</td>
        <td nowrap="nowrap" align="center">应收款（元）</td>
        <td width="15%" nowrap="nowrap" align="center">实收款（元）</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="center" class="sell_bill_id"><font class="blue12px"><c:out value="${fnx:leftPad_sis(cur.sell_bill_id, 12, '0')}" /></font></td>
          <td align="left" class="pay_way" id="${cur.sell_bill_id}_${cur.pay_way}">${fn:split(' ,现金付款,POS机刷卡,货到付款', ',')[cur.pay_way]}</td>
          <td align="left"><font class="blue12px">${cur.pd_cls_name}</font></td>
          <td align="left"><font class="blue12px">${cur.pd_name}</font></td>
          <td align="right"><font class="kz-price-12">
            <fmt:formatNumber value="${cur.price}" type="currency" />
            </font></td>
          <td align="right"><font class="blue12px">${cur.counts}</font></td>
          <td class="kz-price-12" align="right"><fmt:formatNumber value="${cur.price * cur.counts}" type="currency" /></td>
          <c:if test="${cur.pay_way eq 1}">
            <td align="right" class="money_of_deposit" id="${cur.sell_bill_id}_${cur.price}"><font class="kz-price-12"><fmt:formatNumber value="${cur.price}" type="currency" /></font></td>
           </c:if>
           <c:if test="${cur.pay_way eq 3}">
             <td align="right" class="money_of_deposit" id="${cur.sell_bill_id}_${cur.money_of_deposit}"><font class="kz-price-12"><fmt:formatNumber value="${cur.money_of_deposit}" type="currency" /></font></td>
           </c:if>
        </tr>
      </c:forEach>
      <tr>
      	<td colspan="7" align="center">应收款</td>
      	<td align="right" id="total_money_td_1"><span class="kz-price-12"><fmt:formatNumber value="${today_total_money}" type="currency" /></span></td>
      </tr>
      </table>
      <table width="100%" style="margin-top:10px;font-weight:bold;text-align:center;font-size:14px;">
      	<tr>
      		<td align="left">截至昨日总收款：<span class="kz-price"><fmt:formatNumber value="${his_total_money}" type="currency" /></span></td>
      		<td>今日收款：<span class="kz-price"><fmt:formatNumber value="${today_total_money}" type="currency" /> </span></td>
      		<td align="right">总收款：<span class="kz-price"><fmt:formatNumber value="${today_total_money + his_total_money}" type="currency" /></span></td>
      	</tr>
      </table>
  </div>
  <div align="left" style="margin-left:10px;margin-bottom:20px;">
    <input class="but5" type="button"  value="返回" onclick="history.back();return false;" />
    <input class="but4" type="button"  value="下载" onclick="toExcel('divExcel', '?method=load');" />
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	trMerge("sell_bill_id",$("#table1"));
	trMerge1("pay_way",$("#table1"));
	trMerge1("money_of_deposit",$("#table1"));
	

	//var money = 0.00;
	//$(".total_money").each(function(){
		//money = money + parseFloat($(this).html().replace(/\,/g, ""));
	//});
	//var total_total_moeny = outputMoney(money);
	//$("#total_money_td_1").html(total_total_moeny);
	//$("#total_money_td_2").html(total_total_moeny);
});

function trMerge1(para, parentObj) {
	var that;
	$("." + para, parentObj).each(function(){
		if ((that != undefined) && ($(this).attr("id") == $(that).attr("id"))) {
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

function outputMoney(number) {
    if (isNaN(number) || number == "") return "";
    number = Math.round(number * 100) / 100;
    if (number < 0)
        return '-' + outputDollars(Math.floor(Math.abs(number) - 0) + '') + outputCents(Math.abs(number) - 0);
    else
        return outputDollars(Math.floor(number - 0) + '') + outputCents(number - 0);
}

function outputDollars(number) {
    if (number.length <= 3)
        return (number == '' ? '0' : number);
    else {
        var mod = number.length % 3;
        var output = (mod == 0 ? '' : (number.substring(0, mod)));
        for (i = 0; i < Math.floor(number.length / 3); i++) {
            if ((mod == 0) && (i == 0))
                output += number.substring(mod + 3 * i, mod + 3 * i + 3);
            else
                output += ',' + number.substring(mod + 3 * i, mod + 3 * i + 3);
        }
        return (output);
    }
}

function outputCents(amount) {
    amount = Math.round(((amount) - Math.floor(amount)) * 100);
    return (amount < 10 ? '.0' + amount : '.' + amount);
}
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
