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
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/zmd/KonkaXxSellBillJSStatis" styleClass="form_search">
      <html-el:hidden property="method" value="zmd" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="pay_way" value="${af.map.pay_way}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15">&nbsp;</td>
          <td width="300" nowrap="nowrap"><strong class="fb">日期：</strong>
            <html-el:text property="date_start" styleId="date_start" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
            &nbsp;至&nbsp;
            <html-el:text property="date_end" styleId="date_end" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" /></td>
          <td align="left"><input class="but1" type="button" value="查看" id="btn_search" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
    <c:if test="${empty af.map.date_start or empty af.map.date_end or empty konkaXxSellBillList}">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr>
          <td align="left" height="28"><strong class="fb" style="color:green;">请选择查询条件点击查看！</strong></td>
        </tr>
      </table>
    </c:if>
    <c:if test="${(not empty af.map.date_start) and (not empty af.map.date_end) and (not empty konkaXxSellBillList)}">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2" style="margin-bottom:3px;">
        <tr>
          <td align="left" height="24" nowrap="nowrap"><strong class="fb">表：</strong><strong class="fb" style="color:green;">${af.map.date_start}至${af.map.date_end}&nbsp;${konkaDept.dept_name}分公司旗下&nbsp;<c:if test="${af.map.pay_way eq '3'}">货到付款</c:if><c:if test="${af.map.pay_way eq '2'}">全额POS机刷卡</c:if><c:if test="${af.map.pay_way eq '1'}">全额现金支付</c:if>&nbsp;销售单各结算状态订单量和订单额统计表</strong></td>
        </tr>
      </table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
		  <tr>
		    <td rowspan="2" width="15%" align="center" nowrap="nowrap"><strong class="fb">专卖店</strong></td>
		    <td colspan="2" width="25%" align="center" nowrap="nowrap"><strong class="fb">已结算</strong></td>
		    <td colspan="2" width="25%" align="center" nowrap="nowrap"><strong class="fb">未结算</strong></td>
		    <td colspan="2" width="25%" align="center" nowrap="nowrap"><strong class="fb">总计</strong></td>
		    <td rowspan="2" width="10%" align="center" nowrap="nowrap"><strong class="fb">明细</strong></td>
		  </tr>
		  <tr>
		    <td align="center" width="10%" nowrap="nowrap">数量</td>
		    <td align="center" width="15%" nowrap="nowrap">金额</td>
		    <td align="center" width="10%" nowrap="nowrap">数量</td>
		    <td align="center" width="15%" nowrap="nowrap">金额</td>
		    <td align="center" width="10%" nowrap="nowrap">数量</td>
		    <td align="center" width="15%" nowrap="nowrap">金额</td>
		  </tr>
		  <c:forEach items="${konkaXxSellBillList}" var="cur">
		  	<c:set var="all_0_num" value="${all_0_num + cur.map.count_num_0}" />
		  	<c:set var="all_0_money" value="${all_0_money + cur.map.sum_money_0}" />
		  	<c:set var="all_1_num" value="${all_1_num + cur.map.count_num_1}" />
		  	<c:set var="all_1_money" value="${all_1_money + cur.map.sum_money_1}" />
			<tr>
			    <td>${cur.zmd_sn}</td>
			    <td align="right" nowrap="nowrap">${cur.map.count_num_0}</td>
			    <td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${cur.map.sum_money_0}" type="currency" /></span></td>
			    <td align="right" nowrap="nowrap">${cur.map.count_num_1}</td>
			    <td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${cur.map.sum_money_1}" type="currency" /></span></td>
			    <td align="right" nowrap="nowrap">${cur.map.count_num_0 + cur.map.count_num_1}</td>
			    <td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${cur.map.sum_money_0 + cur.map.sum_money_1}" type="currency" /></span></td>
			    <td align="center" nowrap="nowrap"><span style="cursor:pointer;" class="fblue" onclick="$('#message_tip').show();doNeedMethod(null, 'KonkaXxSellBillJSStatis.do', 'zmdView', 'zmd_id=${cur.zmd_id}&mod_id=${af.map.mod_id}&date_start=${af.map.date_start}&date_end=${af.map.date_end}');">详细</span></td>
			</tr>
		  </c:forEach>
		  <tr>
		    <td align="center" nowrap="nowrap"><strong class="fb">总计</strong></td>
		    <td align="right">${all_0_num}</td>
		    <td align="right"><span class="kz-price-12"><fmt:formatNumber value="${all_0_money}" type="currency" /></span></td>
		    <td align="right">${all_1_num}</td>
		    <td align="right"><span class="kz-price-12"><fmt:formatNumber value="${all_1_money}" type="currency" /></span></td>
		    <td align="right">${all_0_num + all_1_num}</td>
		    <td align="right"><span class="kz-price-12"><fmt:formatNumber value="${all_0_money + all_1_money}" type="currency" /></span></td>
		    <td align="center">-</td>
		  </tr>
		</table>
    </c:if>
  </div>
  <div class="clear"></div>
</div>
<!-- Ajax 提交 覆盖层  -->
<div id="message_tip" style="display:none;z-index:888888888;">
  <div class="ui-overlay">
    <div class="ui-widget-overlay"></div>
    <div class="ui-widget-shadow ui-corner-all" style="width:302px;height:152px;position:absolute;left:35%;top:25%"></div>
  </div>
  <div style="position:absolute;width:280px;height:130px;left:35%;top:25%;padding:10px;" class="ui-widget ui-widget-content ui-corner-all"> <span><img id="loading" src="${ctx}/images/loading.gif" />正在查询，请稍等...</span> </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	// 日期控件
	$("#date_start").datepicker({ maxDate: '+0d' });
	$("#date_end"  ).datepicker({ maxDate: '+0d' });
	
	$("#date_start").attr("dataType" , "Require").attr("msg" , "请选择开始日期！");
	$("#date_end"  ).attr("dataType" , "Require").attr("msg" , "请选择结束日期！");
	
	$("#btn_search").click(function (){
		if(Validator.Validate(this.form, 1)){
			var s = $("#date_start").val();
			var e = $("#date_end").val();
			var d_d = (new Date(e.replace("-", "/")).getTime() - new Date(s.replace("-", "/")).getTime()) / 1000 / 60 / 60 / 24;
			if (s > e) {
				alert("开始时间不得大于结束时间！");
				return false;
			}
			if (d_d > 91) {
				alert("查询时间范围不能超过3个月！");
				return false;
			}
			
			$('#message_tip').show(); // 查询会比较慢，提供覆盖层
			$(".form_search").submit();
		}
	});
});
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>