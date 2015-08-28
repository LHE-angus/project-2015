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
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/zmd/KonkaXxZmdRemitRec">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="remit_rec_id" value="${af.map.remit_rec_id}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td colspan="2" style="font-weight:bold;font-size:18px;height:40px;text-align:center;border-bottom:none;">汇款信息确认</td>
        </tr>
        <tr>
          <td colspan="2"><table width="100%" border="0" cellspacing="0" cellpadding="1" class="rtable2">
              <tr class="tabtt1" style="background-image:url('${ctx}/images/manager/tabtitbg1.gif') repeat-x;background:#F0F0F0;">
                <td align="center" nowrap="nowrap"><font class="blue">订单流水号</font></td>
                <td width="10%" align="center" nowrap="nowrap"><font class="blue">付款方式</font></td>
                <td width="10%" align="center" nowrap="nowrap"><font class="blue">账单总金额</font></td>
                <td width="10%" align="center" nowrap="nowrap"><font class="blue">实收款</font></td>
                <td width="10%" align="center" nowrap="nowrap"><font class="blue">销售单状态</font></td>
                <td width="16%" align="center" nowrap="nowrap"><font class="blue">开单时间</font></td>
              </tr>
              <c:forEach var="cur" items="${konkaXxSellBillList}" varStatus="vs">
                <tr>
                  <td align="left" nowrap="nowrap"><a href="${ctx}/manager/zmd/KonkaXxZmdAddSalesOrder.do?method=view&sell_bill_id=${cur.sell_bill_id}&mod_id=802001"><font class="fblue">
                    <c:out value="${fnx:leftPad_sis(cur.sell_bill_id, 12, '0')}" />
                    </font></a></td>
                  <td align="center" nowrap="nowrap"><c:choose>
                      <c:when test="${cur.pay_way eq 1}">现金全额支付</c:when>
                      <c:when test="${cur.pay_way eq 2}">POS机支付</c:when>
                      <c:when test="${cur.pay_way eq 3}">货到付款</c:when>
                    </c:choose></td>
                  <td align="right" nowrap="nowrap"><span class="kz-price-12">
                    <fmt:formatNumber value="${cur.total_money}" type="currency" />
                    </span></td>
                  <td align="right" nowrap="nowrap"><c:if test="${cur.pay_way eq 1}"><span class="kz-price-12">
                      <fmt:formatNumber value="${cur.total_money}" type="currency" />
                      </span> </c:if>
                    <c:if test="${cur.pay_way eq 3}"><span class="kz-price-12">
                      <fmt:formatNumber value="${cur.money_of_deposit}" type="currency" />
                      </span> </c:if></td>
                  <td align="center" nowrap="nowrap"><font class="blue12px">
                    <c:choose>
                      <c:when test="${cur.sell_state eq 0}">未付款</c:when>
                      <c:when test="${cur.sell_state eq 10}">已付款未审核</c:when>
                      <c:when test="${cur.sell_state eq 20}">已审核通过</c:when>
                      <c:when test="${cur.sell_state eq 21}"><span style="color:#FF0000">已审核不通过</span></c:when>
                      <c:when test="${cur.sell_state eq 30}">已发货</c:when>
                      <c:when test="${cur.sell_state eq 40}">确认消费者收货</c:when>
                      <c:when test="${cur.sell_state eq 70}">交易成功</c:when>
                    </c:choose>
                    </font></td>
                  <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
                <c:if test="${cur.pay_way eq 1}">
                  <c:set var="all_money" value="${all_money + cur.total_money}" />
                </c:if>
                <c:if test="${cur.pay_way eq 3}">
                  <c:set var="all_money" value="${all_money + cur.money_of_deposit}" />
                </c:if>
              </c:forEach>
            </table></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right">应汇款金额：</td>
          <td width="85%"><span class="kz-price">
            <fmt:formatNumber value="${af.map.todo_money}" type="currency" />
            </span></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color:red">[必填]</span>实际汇款金额：</td>
          <td width="85%"><html-el:text property="total_money" styleId="total_money" maxlength="20" size="20" readonly="true" /></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color:red">[必填]</span>汇款/转账流水号：</td>
          <td width="85%"><html-el:text property="remit_sn" styleId="remit_sn" maxlength="20" size="20" /></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right">汇款人：</td>
          <td width="85%"><html-el:text property="man" styleId="man" maxlength="20" size="20" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right">汇款时间：</td>
          <td><html-el:text property="remit_date" styleId="remit_date"  size="20" maxlength="10" readonly="true" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
        </tr>
        <tr>
          <td>&nbsp;&nbsp;</td>
          <td><input class="but4" type="button" name="Submit4" value="保存" id="send" />
            <input class="but3" type="reset"  value="重填 " />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back()" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<!-- ****** Main Frame End ****** -->
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#remit_date").datepicker();
	$("#remit_sn").attr("dataType", "Require").attr("msg", "请填写汇款/转账流水号！");
	$("#total_money").attr("dataType", "Require").attr("msg", "请填写实际汇款金额！");
	$("#total_money").attr("focus",setOnlyNum);
	
	
	$("#send").click(function(){
			var isSubmit = Validator.Validate(this.form, 3);
			
			if (isSubmit) {
				$(":button").attr("disabled", "true");
				$(":reset").attr("disabled", "true");
				this.form.submit();
			}
	 });
});

function setOnlyNum() {
	$(this).css("ime-mode", "disabled").attr("t_value", "").attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
