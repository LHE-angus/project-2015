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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="divExcel_all" title="佣金确认数据表${af.map.now_date}">
	<table width="99%" border="1" cellspacing="0" cellpadding="0" class="rtable2" align="center">
        <tr>
          <td width="5%"  align="center" nowrap="nowrap" style="font-weight:bold;">序号</td>
          <td width="11%" align="center" nowrap="nowrap" style="font-weight:bold;">订单流水号</td>
          <td width="11%" align="center" nowrap="nowrap" style="font-weight:bold;">专卖店编号</td>
          <td width="11%" align="center" nowrap="nowrap" style="font-weight:bold;">合计金额</td>
          <td width="11%" align="center" nowrap="nowrap" style="font-weight:bold;">销售时间</td>
          <td width="11%" align="center" nowrap="nowrap" style="font-weight:bold;">销售单状态</td>
          <td width="10%" align="center" nowrap="nowrap" style="font-weight:bold;">付款方式</td>
          <td width="10%" align="center" nowrap="nowrap" style="font-weight:bold;">物流费用</td>
          <td width="10%" align="center" nowrap="nowrap" style="font-weight:bold;">定金</td>
          <td width="10%" align="center" nowrap="nowrap" style="font-weight:bold;">佣金</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap">${vs.count}</td>
              <td align="center" nowrap="nowrap" style="mso-number-format:'\@';"><c:out value="${fnx:leftPad_sis(cur.sell_bill_id, 12, '0')}" /></td>
              <td align="left" nowrap="nowrap" style="mso-number-format:'\@';">${cur.zmd_sn}</td>
              <td align="right" nowrap="nowrap" class="kz-price-12" style="mso-number-format:'\@';"><fmt:formatNumber value="${cur.total_money}" type="currency" /></td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.sell_date}" pattern="yyyy-MM-dd" /></td>
              <td align="center" nowrap="nowrap"><span style="color:green;">确认收货</span></td>
              <td align="center" nowrap="nowrap">${fn:split(' ,现金付款,POS机刷卡,货到付款', ',')[cur.pay_way]}</td>
              <td align="right" nowrap="nowrap" class="kz-price-12" style="mso-number-format:'\@';"><fmt:formatNumber value="${cur.fee_of_post}" type="currency" /></td>
              <td align="right" nowrap="nowrap" class="kz-price-12" style="mso-number-format:'\@';"><c:if test="${not empty cur.money_of_deposit}">
                  <fmt:formatNumber value="${cur.money_of_deposit}" type="currency" />
                </c:if>
                <c:if test="${empty cur.money_of_deposit}"> <span style="color:#999;">无定金</span> </c:if></td>
              <td align="center" nowrap="nowrap">&nbsp;</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
</div>
<div id="message_tip" style="display:none;">
  <div class="ui-overlay">
    <div class="ui-widget-overlay"></div>
    <div class="ui-widget-shadow ui-corner-all" style="width:302px;height:152px;position:absolute;left:35%;top:25%"></div>
  </div>
  <div style="position:absolute;width:280px;height:130px;left:35%;top:25%;padding:10px;" class="ui-widget ui-widget-content ui-corner-all"> <span><img id="loading" src="${ctx}/images/loading.gif" />正在导出数据，请稍等...</span> </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//$('#message_tip').show();
	toExcel('divExcel_all', '${ctx}/manager/zmd/KonkaXxSellJs.do?method=toExcel');
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>