<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <html-el:form action="/zmd/KonkaXxSellJs">
    <html-el:hidden property="method" value="save_js" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <html-el:hidden property="form_values" value="${form_values}" />
    <html-el:hidden property="queryString" />
    <table width="99%" border="0" cellpadding="0" cellspacing="1" class="rtable2" style="margin-top:3px;" align="center">
      <!-- 销售单信息Begin -->
      <tr>
        <td colspan="4" style="font-weight:900;">销售单信息（订单流水号：<span style="color:#FF0000;font-size:15px;">${fnx:leftPad_sis(af.map.sell_bill_id, 12, '0')}</span>）</td>
      </tr>
      <tr>
        <td width="15%" class="title_item" nowrap="nowrap" align="right" ><strong>分公司：</strong></td>
        <td width="35%" align="left" style="padding-bottom:5px;"><c:out value="${af.map.dept_name}" /></td>
        <td width="15%" class="title_item" nowrap="nowrap" align="right">专卖店：</td>
        <td align="left" style="padding-bottom:5px;"><c:out value="${af.map.zmd_sn}" /></td>
      </tr>
      <tr>
        <td width="15%" class="title_item" nowrap="nowrap" align="right">销售人员：</td>
        <td width="35%" align="left" style="padding-bottom:5px;"><c:out value="${af.map.sell_man}" /></td>
        <td width="15%" class="title_item" nowrap="nowrap" align="right">销售时间：</td>
        <td align="left" style="padding-bottom:5px;"><fmt:formatDate value="${af.map.sell_date}" pattern="yyyy-MM-dd" var="_sell_date" />
          ${_sell_date}</td>
      </tr>
      <tr>
        <td width="15%" class="title_item" nowrap="nowrap" align="right">付款方式：</td>
        <td align="left" colspan="3" style="padding-bottom:5px;">${fn:split(' ,现金付款,POS机刷卡,货到付款', ',')[af.map.pay_way]}</td>
      </tr>
      <c:if test="${not empty af.map.money_of_deposit}">
        <tr id="money_of_deposit_tr">
          <td width="15%" class="title_item" nowrap="nowrap" align="right">定金：</td>
          <td align="left" colspan="3" style="padding-bottom:5px;"><span class="kz-price-12">
            <fmt:formatNumber type="currency" value="${af.map.money_of_deposit}" />
            </span></td>
        </tr>
      </c:if>
      <!-- 销售单信息End -->
    </table>
    <div class="rtabcont1" >
      <div style="100%;overflow-x:auto;border-left:1px solid #ccc;border-right:1px solid #ccc;padding-bottom:5px;">
        <!-- 产品信息Begin -->
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2" align="center" style="border-left:0px;border-right:0px;">
          <tr>
            <td colspan="12" style="font-weight:900;">订单商品详细信息</td>
          </tr>
          <tr class="tabtt1">
            <td width="50" align="center" nowrap="nowrap">序号</td>
            <td width="80" align="center" nowrap="nowrap">商品品类</td>
            <td width="90" align="center" nowrap="nowrap">商品型号</td>
            <td width="90" align="center" nowrap="nowrap">返佣类型</td>
            <td width="60" align="center" nowrap="nowrap">数量</td>
            <td width="70" align="center" nowrap="nowrap">单价</td>
            <td width="110" align="center" nowrap="nowrap">金额</td>
            <td width="100" align="center" nowrap="nowrap">佣金</td>
            <td width="100" align="center" nowrap="nowrap">调整</td>
            <td width="450" align="center" nowrap="nowrap">结算公式</td>
            <td width="230" align="center" nowrap="nowrap">赠品</td>
            <td width="230" align="center" nowrap="nowrap">销售备注</td>
          </tr>
          <c:forEach items="${konkaXxSellBillDetailsList}" var="cur" varStatus="vs">
            <tr>
              <td align="center" >${vs.count}</td>
              <td align="left">${cur.pd_cls_name}</td>
              <td align="left" class="pdIds"><c:out value="${cur.md_name}" /></td>
              <td align="left"><c:forEach items="${baseTypesList70000}" var="_cur">
                  <c:if test="${_cur.type_id eq cur.pd_type}">${_cur.type_name}</c:if>
                </c:forEach></td>
              <td align="right"><span id="counts_${cur.sell_bill_details_id}">
                <c:out value="${cur.counts}" />
                </span></td>
              <td align="right" class="kz-price-12">￥<span id="price_${cur.sell_bill_details_id}">
                <c:out value="${cur.price}" />
                </span></td>
              <td align="right" class="kz-price-12">￥<span id="pdAmount_${vs.count}"></span></td>
              <td align="right" class="kz-price-12">￥<span id="zmd_fee_${cur.sell_bill_details_id}" title="${cur.map.reward}">
                <c:out value="${cur.map.reward}" />
                </span></td>
              <td align="right" class="kz-price-12">￥
                <input type="text" name="tzjs_${cur.sell_bill_details_id}" value="0" class="tzjs" maxlength="8" size="2" /></td>
              <td align="left"><c:out value="${cur.map.formula}" escapeXml="false" /></td>
              <td align="left"><c:out value="${cur.gift}" /></td>
              <td align="left"><c:out value="${cur.xs_remarks}" /></td>
            </tr>
          </c:forEach>
          <tr id="total">
            <td align="center" colspan="2" style="color:red;font-weight:bold;height:30px;">合计</td>
            <td align="right">&nbsp;</td>
            <td align="right">&nbsp;</td>
            <td align="right">&nbsp;</td>
            <td align="right">&nbsp;</td>
            <td align="right" nowrap="nowrap"><span class="kz-price">
              <fmt:formatNumber type="currency" value="${af.map.total_money}" />
              </span></td>
            <td align="right"><span id="temp_total_reward" title="${total_reward}" class="kz-price">
              <fmt:formatNumber type="currency" value="${total_reward}" />
              </span></td>
            <td align="right">&nbsp;</td>
            <td align="right">&nbsp;</td>
            <td align="right">&nbsp;</td>
            <td align="right">&nbsp;</td>
          </tr>
        </table>
        <!-- 产品信息End -->
      </div>
    </div>
    <table width="99%" border="0" cellpadding="0" cellspacing="1" class="rtable2" align="center">
      <!-- 客户信息Begin -->
      <tr>
        <td colspan="4" style="font-weight:900;">客户信息</td>
      </tr>
      <tr>
        <td width="15%" class="title_item" nowrap="nowrap" align="right" ><strong>客户姓名：</strong></td>
        <td width="35%" align="left" style="padding-bottom:5px;"><c:out value="${af.map.buyer_name}"></c:out></td>
        <td width="15%" class="title_item" nowrap="nowrap" align="right">身份证：</td>
        <td align="left" style="padding-bottom:5px;"><c:out value="${af.map.buyer_id}" />
          <c:if test="${empty af.map.buyer_id}"><span style="color:#999">未填写</span></c:if></td>
      </tr>
      <tr>
        <td width="15%" class="title_item" nowrap="nowrap" align="right" ><strong>性别：</strong></td>
        <td width="35%" align="left" style="padding-bottom:5px;"><c:choose>
            <c:when test="${af.map.buyer_sex eq 1}">男</c:when>
            <c:when test="${af.map.buyer_sex eq 2}">女</c:when>
            <c:when test="${af.map.buyer_sex eq 3}">保密</c:when>
            <c:otherwise>不详</c:otherwise>
          </c:choose></td>
        <td width="15%" class="title_item" nowrap="nowrap" align="right">生日：</td>
        <td align="left" style="padding-bottom:5px;"><fmt:formatDate value="${af.map.buyer_birthday}" pattern="yyyy-MM-dd" var="_buyer_birthday" />
          <c:out value="${_buyer_birthday}" />
          <c:if test="${empty _buyer_birthday}"><span style="color:#999">-</span></c:if></td>
      </tr>
      <tr>
        <td width="15%" class="title_item" nowrap="nowrap" align="right" ><strong>电话：</strong></td>
        <td width="35%" align="left" style="padding-bottom:5px;"><c:out value="${af.map.buyer_tel}" />
          <c:if test="${empty af.map.buyer_tel}"><span style="color:#999">未填写</span></c:if></td>
        <td width="15%" class="title_item" nowrap="nowrap" align="right">手机：</td>
        <td align="left" style="padding-bottom:5px;"><c:out value="${af.map.buyer_phone}" />
          <c:if test="${empty af.map.buyer_phone}"><span style="color:#999">未填写</span></c:if></td>
      </tr>
      <tr>
        <td width="15%" class="title_item" nowrap="nowrap" align="right">客户联系地址：</td>
        <td align="left" colspan="3" style="padding-bottom:5px;"><c:out value="${af.map.buyer_link_addr}" />
          <c:if test="${empty af.map.buyer_link_addr}"><span style="color:#999">未填写</span></c:if></td>
      </tr>
      <tr>
        <td width="15%" class="title_item" nowrap="nowrap" align="right">消费者备注：</td>
        <td align="left" colspan="3" style="padding-bottom:5px;"><c:out value="${af.map.buyer_memo}" />
          <c:if test="${empty af.map.buyer_memo}"><span style="color:#999">无</span></c:if></td>
      </tr>
      <!-- 客户信息End -->
      <tr>
        <td colspan="6" height="40"  align="center"><input class="but4" type="button" name="Submit4" id="send" value="提交" />
          <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" id="btn_back" /></td>
      </tr>
    </table>
  </html-el:form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery1.5.1.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script>
<script type="text/javascript" src="${ctx}/scripts/json2.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var total_num = 0;
    var return_values = JSON.parse('${konkaXxSellBillDetailsListJSON}');
    for(var i = 0; i< return_values.length; i++){
    	var counts = parseInt(return_values[i].counts);
  		var price = parseFloat(return_values[i].price);
  		var money = counts * price;
		var j = i + 1;
  		$("#pdAmount_" + j).text(money.toFixed(2));
  		total_num = parseInt(total_num) + counts;
    }
    $("#total").children().eq(3).html("<font color=\"red\" style=\"font-weight:bold;\">" + total_num + "</font>");
    
   // 设置调整数据只能输入数字
	$(".tzjs").focus(setOnlyNumForTz); 
  
	$("#send").click(function(){
		if(confirm("确定结算吗 ？")) {
			this.form.submit();
		}
	});
});

function setOnlyNumForTz() {
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/)){
			this.value=this.t_value;
		} else { 
			this.t_value=this.value;
		}
		if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/)){
		   this.o_value=this.value;
		}
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/)){
			this.value=this.t_value;
		}else{ 
			this.t_value=this.value;
		}
		if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/)){
			this.o_value=this.value;
		}
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))
			this.value=this.o_value;
		else{
			if(this.value.match(/^\.\d+$/))
				this.value=0+this.value;
			if(this.value.match(/^\.$/))
				this.value=0;
			this.o_value=this.value;
		}
		if(this.value.length == 0) 
			this.value = "0";


	   // 判断结算最后的光标移去佣金计算
	   var id = this.name.split("_")[1];
	   var zmd_fee = parseFloat(this.value) +  parseFloat($("#zmd_fee_" + id).attr("title"));
	   if(zmd_fee < 0){
		 alert("调整佣金结果不能小于0，请重新输入！");
		 this.value = "0";
		 return ;			
	   }
	   var total_price = parseFloat($("#counts_" + id).html()) * parseFloat($("#price_" + id).html());
	   if(total_price < zmd_fee){
		   alert("调整佣金结果不能大于销售额，请重新输入！");
		   this.value = "0";
		   return ;
	   }
	   
	   $("#zmd_fee_" + id).html(changeTwoDecimal_f(zmd_fee));
	   

	   // 计算最后的总佣金
	   var total_reward_value = 0;
	   $(".tzjs").each(function (){
		   total_reward_value = total_reward_value + parseFloat($(this).attr("value"));
		});
	   $("#temp_total_reward").html(changeTwoDecimal_f(parseFloat(total_reward_value) +  parseFloat($("#temp_total_reward").attr("title"))));
	});
}

function changeTwoDecimal_f(x){
	var f_x = parseFloat(x);
	if (isNaN(f_x)){
		alert('function:changeTwoDecimal->parameter error');
		return false;
	}
	var f_x = Math.round(x*100)/100;
	var s_x = f_x.toString();
	var pos_decimal = s_x.indexOf('.');
	if (pos_decimal < 0){
		pos_decimal = s_x.length;
		s_x += '.';
	}
	while (s_x.length <= pos_decimal + 2){
		s_x += '0';
	}
	return s_x;
}
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>