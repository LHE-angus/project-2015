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
  <div class="rtabcont2" align="center" style="margin-top:4px;">
     <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-bottom:20px;">
      <tr>
        <td colspan="9" align="center"><font style="font-weight:bold;font-size:20px;">康佳专卖店销售单结算明细表</font></td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td colspan="9" align="right"><font style="font-weight:bolder;">订单流水号：<span style="color:#F00;font-size:16px;"><c:out value="${fnx:leftPad_sis(konkaXxSellBill.sell_bill_id, 12, '0')}" /></span></font></td>
      </tr>
    </table>
    <table width="100%" border="1" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="10%" align="center" nowrap="nowrap">分公司</td>
        <td width="10%" align="center" nowrap="nowrap">专卖店编号</td>
        <td width="10%" align="center" nowrap="nowrap">商品类型</td>
        <td width="10%" align="center" nowrap="nowrap">产品型号</td>
        <td width="11%" align="center" nowrap="nowrap">销售价格</td>
        <td width="7%" align="center" nowrap="nowrap">销售数量</td>
        <td width="11%" align="center" nowrap="nowrap">金额合计</td>
        <td width="30%" align="center" style="display:${is_zmd ? 'none' : ''}">佣金结算快照</td>
        <td width="11%" align="center" nowrap="nowrap">佣金</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="left" class="dept_name">${dept_name}</td>
          <td align="left" class="zmd_sn"><font class="blue12px">${konkaXxSellBill.zmd_sn}</font></td>
          <td align="left"><font class="blue12px">${cur.map.type_name}</font></td>
          <td align="left"><font class="blue12px">${cur.md_name}</font></td>
          <td align="right"><span class="kz-price-12"><fmt:formatNumber type="currency" value="${cur.price}" /></span></td>
          <td align="right"><font class="blue12px">${cur.counts}</font></td>
          <td align="right"><span class="kz-price-12"><fmt:formatNumber type="currency" value="${cur.price * cur.counts}" /></span>
            </td>
          <td align="left" style="display:${is_zmd ? 'none' : ''}"><font class="blue12px">${cur.zmd_fee_fp}</font></td>
          <td align="right"><span class="kz-price-12"><fmt:formatNumber type="currency" value="${cur.zmd_fee}" /></span>
          </td>
        </tr>
      </c:forEach>
      <tr>
        <td align="center" nowrap="nowrap"><font class="blue">合计</font></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td style="display:${is_zmd ? 'none' : ''}">&nbsp;</td>
        <td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber type="currency" value="${total_zmd_fee}" /></span></td>
      </tr>
    </table>
  </div>
  <div align="left" style="margin-left:10px;margin-bottom:20px;">
    <input class="but5" type="button"  value="返回" onclick="history.back();return false;" />
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/print.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var that;
	$(".dept_name").each(function(){
		if ($(this).text() == $(that).text()) {
			var rowspan = $(that).attr("rowSpan");
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

	var other;
	$(".zmd_sn").each(function(){
		if ($(this).text() == $(other).text()) {
			var rowspan = $(other).attr("rowSpan");
			if (rowspan == undefined) {
				$(other).attr("rowSpan", 1);
				rowspan = $(other).attr("rowSpan");   
			}
			rowspan = Number(rowspan) + 1;
			$(other).attr("rowSpan", rowspan); // do your action for the colspan cell here
			$(this).remove(); // .remove(); // do your action for the old cell here
	    } else {
	    	other = this;			
	    }
	});
});
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
