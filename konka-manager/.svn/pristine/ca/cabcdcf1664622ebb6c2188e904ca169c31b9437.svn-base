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
<link href="${ctx}/styles/jGrowl.css" rel="stylesheet" type="text/css" />
</head>
<body>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<div class="oarcont">
  <div class="oartop">
    <table id="tab_1" width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/zmd/KonkaXxZmdCustReceiveConfirm">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table id="tab_2" width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td nowrap="nowrap" style="padding:2px;"><table width="80%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="15%" align="left" nowrap="nowrap" style="padding:2px;">&nbsp;&nbsp;<strong class="fb">专卖店编号：</strong></td>
                <td width="35%" align="left" nowrap="nowrap" style="padding:2px;"><html-el:text property="zmd_sn_like" styleId="zmd_sn_like" size="20" maxlength="20" /></td>
                <td width="15%" align="left" nowrap="nowrap" style="padding:2px;"><strong class="fb">订单流水号：</strong></td>
                <td width="35%" align="left" nowrap="nowrap" style="padding:2px;"><html-el:text property="sell_bill_id" styleId="sell_bill_id" size="20" maxlength="12" ></html-el:text></td>
              </tr>
              <tr>
                <td width="15%" align="left" nowrap="nowrap" style="padding:2px;">&nbsp;&nbsp;<strong class="fb">开单时间：</strong></td>
                <td width="35%" align="left" nowrap="nowrap" style="padding:2px;"><html-el:text property="add_date_ge" styleId="add_date_ge" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
                  至
                  <html-el:text property="add_date_le" styleId="add_date_le" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" /></td>
                <td width="15%" align="left" nowrap="nowrap" style="padding:2px;"><strong class="fb">销售单状态：</strong></td>
                <td width="35%" align="left" nowrap="nowrap" style="padding:2px;"><html-el:select property="sell_state" styleId="sell_state" onchange="this.form.submit();">
                    <html-el:option value="">==请选择==</html-el:option>
                    <html-el:option value="30">待确认收货</html-el:option>
                    <html-el:option value="70">已确认收货</html-el:option>
                  </html-el:select></td>
              </tr>
              <tr>
                <td width="15%" align="left" nowrap="nowrap" style="padding:2px;">&nbsp;&nbsp;<strong class="fb">开单人姓名：</strong></td>
                <td width="35%" align="left" nowrap="nowrap" style="padding:2px;"><html-el:text property="add_user_realname_like" styleId="add_user_realname_like" size="20" maxlength="20" ></html-el:text>
                  &nbsp;&nbsp;</td>
                <td width="15%" align="left" nowrap="nowrap" style="padding:2px;"><strong class="fb">消费者姓名：</strong></td>
                <td width="35%" align="left" nowrap="nowrap" style="padding:2px;"><html-el:text property="buyer_name_like" styleId="buyer_name_like" size="20" maxlength="20" ></html-el:text>
                  &nbsp;&nbsp;</td>
              </tr>
              <tr>
                <td width="15%" align="left" nowrap="nowrap" style="padding:2px;">&nbsp;&nbsp;<strong class="fb">付款方式：</strong></td>
                <td width="35%" align="left" nowrap="nowrap" style="padding:2px;"><html-el:select property="pay_way" styleId="pay_way" onchange="this.form.submit();">
                    <html-el:option value="">==请选择==</html-el:option>
                    <html-el:option value="1">现金支付</html-el:option>
                    <html-el:option value="2">POS机刷卡</html-el:option>
                    <html-el:option value="3">货到付款</html-el:option>
                  </html-el:select>
                  &nbsp;&nbsp;
                  <input class="but1" type="submit" name="Submit" value="搜索" /></td>
                <td width="15%" align="left" nowrap="nowrap" style="padding:2px;"></td>
                <td width="35%" align="left" nowrap="nowrap" style="padding:2px;"></td>
              </tr>
            </table></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <table id="tab_3" width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="10%" align="center">订单流水号</td>
        <td align="center">专卖店编号</td>
        <td width="8%" align="center">开单人姓名</td>
        <td width="8%" align="center">消费者姓名</td>
        <td width="10%" align="center">开单时间</td>
        <td width="8%" align="center">付款方式</td>
        <td width="8%" align="center">订单总金额</td>
        <td width="8%" align="center">物流应收款</td>
        <td width="8%" align="center">销售单状态</td>
        <td width="10%" align="center">操作</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap"><font class="blue12px"> <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdAddSalesOrder.do', 'view', 'sell_bill_id=${cur.sell_bill_id}&mod_id=802001');$('#message_tip').show();">${fnx:leftPad_sis(cur.sell_bill_id, 12, '0')}</span> </font></td>
          <td align="left" nowrap="nowrap"><font class="blue12px">${cur.zmd_sn}</font></td>
          <td align="left" nowrap="nowrap"><font class="blue12px">${cur.add_user_realname}</font></td>
          <td align="left" nowrap="nowrap"><font class="blue12px">${cur.buyer_name}</font></td>
          <td align="left" nowrap="nowrap"><font class="blue12px">
            <fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" />
            </font></td>
          <td align="center" nowrap="nowrap"><font class="blue12px">${fn:split(' ,现金付款,POS机刷卡,货到付款', ',')[cur.pay_way]}</font></td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber type="currency" value="${cur.total_money}" />
            </span></td>
          <td align="right" nowrap="nowrap"><c:choose>
              <c:when test="${cur.pay_way eq 3}"> <span class="kz-price-12">
                <fmt:formatNumber type="currency" value="${cur.total_money - cur.money_of_deposit}" />
                </span></c:when>
              <c:otherwise>-</c:otherwise>
            </c:choose></td>
          <td align="center" nowrap="nowrap"><c:choose>
              <c:when test="${cur.sell_state eq 0}">未付款</c:when>
              <c:when test="${cur.sell_state eq 10}">已付款未审核</c:when>
              <c:when test="${cur.sell_state eq 20}">已审核通过</c:when>
              <c:when test="${cur.sell_state eq 21}">已审核不通过</c:when>
              <c:when test="${cur.sell_state eq 30}"><span style="color:#ff0000;" title="该订单已发货，正等待确认收货！">待确认收货</span></c:when>
              <c:when test="${cur.sell_state eq 70}"><span style="color:green;">已确认收货</span></c:when>
              <c:otherwise>未知状态</c:otherwise>
            </c:choose></td>
          <td align="center" nowrap="nowrap"><c:if test="${cur.sell_state le 30}"> <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdCustReceiveConfirm.do', 'edit','sell_bill_id=${cur.sell_bill_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">确认消费者已收货</span> </c:if>
            <c:if test="${cur.sell_state eq 70}"> <span style="color: #ccc;">确认消费者已收货</span> </c:if></td>
        </tr>
        <c:if test="${vs.last eq true}">
          <c:set var="i" value="${vs.count}" />
        </c:if>
      </c:forEach>
      <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
        <tr align="center">
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </c:forEach>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxZmdCustReceiveConfirm.do">
      <table id="tab_4" width="98%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td align="left">注：订单总金额单位：元，物流应收款单位：元</td>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
			            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			            pager.addHiddenInputs("method", "list");
			            pager.addHiddenInputs("mod_id","${af.map.mod_id}");
						pager.addHiddenInputs("zmd_sn_like", "${af.map.zmd_sn_like}");	
			            pager.addHiddenInputs("sell_bill_id", "${af.map.sell_bill_id}");
			            pager.addHiddenInputs("add_date_ge", "${af.map.add_date_ge}");
			            pager.addHiddenInputs("add_date_le", "${af.map.add_date_le}");
			            pager.addHiddenInputs("sell_state", "${af.map.sell_state}");
						pager.addHiddenInputs("add_user_realname_like", "${fn:escapeXml(af.map.add_user_realname_like)}");
						pager.addHiddenInputs("buyer_name_like", "${fn:escapeXml(af.map.buyer_name_like)}");
						pager.addHiddenInputs("pay_way", "${af.map.pay_way}");
	            		document.write(pager.toString());
	            	</script></td>
        </tr>
      </table>
    </form>
    <div>说明： 1、此功能列出财务审核通过并且物流已经发货的订单； 2、“消费者已确认收货”表示您已经收到消费者订单余款； </div>
  </div>
  <div class="clear"></div>
  <div id="message_tip" style="display:none;">
    <div class="ui-overlay">
      <div class="ui-widget-overlay"></div>
      <div class="ui-widget-shadow ui-corner-all" style="width:302px;height:152px;position:absolute;left:35%;top:25%"></div>
    </div>
    <div style="position:absolute;width:280px;height:130px;left:35%;top:25%;padding:10px;" class="ui-widget ui-widget-content ui-corner-all"> <span><img id="loading" src="${ctx}/images/loading.gif" />正在查询，请稍等...</span> </div>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery1.5.1.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script>
<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){
	$("#add_date_ge").datepicker();
	$("#add_date_le").datepicker();

	//DIV自适应宽度Begin
	var ww = $(".oarcont").width();
	var w3 = $("#tab_3").width();
	var w4 = $("#tab_4").width();
	var w = 0;
	for ( var i = 1; i <= 3; i++) {
		var w_t = $("#tab_" + i).width();
		var className = $("#tab_" + i).parent().attr("class");
		if (className == "" || className == undefined) {
			className = $("#tab_" + i).parent().parent().attr("class");
		}
		//alert(className);
		if ("oartop" == className) {
			w_t = w_t + 17;
		} else if ("rtabcont1" == className) {
			w_t = w_t + 7 + 7;
		}
		
		//alert("tab_" + i +" = " + w_t);
		if (w_t > w) {
			w = w_t;
		}
		//alert("w = " + w);
	}
	$("#tab_3").css("width", w3);
	$("#tab_4").css("width", w4);
	$(".oarcont").css("width", w);
	if (ww < w) {
		$("body").css("overflow-x", "scroll");
	}
	//DIV自适应宽度End
	
	if ("" != "${af.map.msg}") {
		$.jGrowl("${af.map.msg}",
				 {theme:'success', 
			 	  life: 4500});
	}
});
//]]>--></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
