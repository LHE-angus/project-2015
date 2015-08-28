<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>进货管理 &gt; 进货登记</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：进货管理 &gt; 进货登记</div>
  <div class="rtabcont1">
    <html-el:form action="/JxcStockBill.do">
      <html-el:hidden property="method" value="saveUpdate" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="keySeq" value="${af.map.keySeq}" />
      <html-el:hidden property="id" value="${af.map.id}" />
      <html-el:hidden property="stock_bill_details_id" value="${af.map.listJxcStockBillDetails[0].id}" />
      <html-el:hidden property="supplier_id" value="${af.map.map.supplier_id}" />
      <html-el:hidden property="curr_count" styleId="curr_count" value="${af.map.map.curr_count}" />
      <html-el:hidden property="curr_count" styleId="pre_update_count" value="${af.map.listJxcStockBillDetails[0].count}" />
      <html-el:hidden property="querystring" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="2">进货单</th>
        </tr>
        <tr>
          <td width="18%" class="title_item">进货单号：</td>
          <td>${fn:escapeXml(af.map.sn)}</td>
        </tr>
        <tr>
          <td class="title_item">进货日期：</td>
          <td><fmt:formatDate value="${af.map.add_date }" pattern="yyyy-MM-dd"></fmt:formatDate></td>
        </tr>
        <tr>
          <td class="title_item">供应商名称：</td>
          <td> ${fn:escapeXml(af.map.map.supplier_name)} </td>
        </tr>
        <tr>
          <td class="title_item">应付金额(元)：</td>
          <td><span id="pay_money">${af.map.pay_money}</span></td>
        </tr>
        <tr id="pc_result_tr">
          <td class="title_item"><font color="red">*</font>实付金额(元)： </td>
          <td><html-el:text styleId="paid_money" property="paid_money" value="${af.map.paid_money}" styleClass="webinput" size="12" maxlength="12"/></td>
        </tr>
        <tr>
          <td class="title_item">经办人：</td>
          <td>${af.map.opr_man}</td>
        </tr>
        <tr>
          <td class="title_item">备注：</td>
          <td>${af.map.remarks}</td>
        </tr>
        <tr>
          <th colspan="2">进货单明细</th>
        </tr>
        <tr>
          <td width="18%" class="title_item">产品类型：</td>
          <td>${fn:escapeXml(af.map.listJxcStockBillDetails[0].pd_type_name)}</td>
        </tr>
        <tr>
          <td class="title_item">产品品牌：</td>
          <td>${fn:escapeXml(af.map.listJxcStockBillDetails[0].brand_name)}</td>
        </tr>
        <tr>
          <td class="title_item">产品型号：</td>
          <td>${fn:escapeXml(af.map.listJxcStockBillDetails[0].pd_name)}</td>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>进货数量：</td>
          <td><html-el:text property="count" styleId="count" value="${af.map.listJxcStockBillDetails[0].count}"  maxlength="8" styleClass="webinput"></html-el:text></td>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>进货单价：</td>
          <td><html-el:text property="price" styleId="price"  maxlength="8" value="${af.map.listJxcStockBillDetails[0].price}" styleClass="webinput"></html-el:text></td>
        </tr>
        <tr>
          <td class="title_item">是否盘存：</td>
          <td><c:if test="${af.map.listJxcStockBillDetails[0].is_pc eq 0}"><span style="color:#060;">未盘存</span></c:if>
            <c:if test="${af.map.listJxcStockBillDetails[0].is_pc eq 1}"><span style="color:#F00;">已盘存</span></c:if></td>
        </tr>
        <tr>
          <td class="title_item">备注：</td>
          <td>${fn:escapeXml(af.map.listJxcStockBillDetails[0].remarks)}</td>
        </tr>
        <tr>
          <td colspan="2" align="center"><input class="bgButtonSave" type="button" name="save" class="bgButtonSave" value=" 保 存 " id="btn_submit"/>
            <input class="bgButtonBack" type="submit" name="stock_bill_back" value="返回" onclick="history.back();return false;" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){
	//$("#count").focus(setOnlyNum).attr("dataType","Require").attr("msg","请填写进货数量！");
	//$("#price").focus(setOnlyNum1).attr("dataType","Require").attr("msg","请填写进货单价！");
	//$("#paid_money").focus(setOnlyNum).attr("dataType","Require").attr("msg","请填实付金额！");

	$("#count").attr("dataType","Integer").attr("msg","请填写进货数量，且必须为数字！");
	$("#price").attr("dataType","Currency").attr("msg","请填写零售价，且必须为正数！");
	$("#paid_money").attr("dataType","Double").attr("msg","请填本次付款，且必须为实数！");

	var f = document.forms[0];
	
	//进货信息：输入进货数量和单价后自动计算金额
	$("#count").keyup(function(){//进货数量事件
		var _reg = /^[-\+]?\d+$/;
		var pre_update_count = $("#pre_update_count").val();//修改前进货数量
		var count = $(this).val();//修改后进货数量
		var curr_count = $("#curr_count").val();//当前库存
		if("" != $.trim(count)) {
			$("#thInfo").remove();
			if (!_reg.test(count)) {
				alert($(this).attr("msg"));
				$("#pay_money").text("");
				$("#paid_money").val("");
				$(this).focus().value = 0;
				return false;
			}
			if(Number(curr_count) - Number(pre_update_count) + Number(count) < 0){//当前库存-修改前订单进货数量+本次进货数量
				alert("当前库存数【" + Number(curr_count) + "】,您输入的退货产品数量，导致该产品库存不足，请重新输入！");
				$("#pay_money").text("");
				$("#paid_money").val("");
				$(this).focus().val(0);
				return;
			}
			if(count < 0){
				$(this).after("<span id='thInfo' title='退货：当输入的数量为负数时。'><img src='${ctx}/styles/jxc/images/th.gif' style='vertical-align: text-bottom;padding-right:2px;'/></span>");
			}
			var price = $("#price").val();//进货单价
			$("#pay_money").text((count*price).toFixed(2));//应付金额
			$("#paid_money").val((count*price).toFixed(2));//本次付款
		}
	});
	$("#price").keyup(function(){//进货单价
		var count = $("#count").val();//进货数量
		var price = $(this).val();//进货单价
		if(price < 0){
			alert("进货单价不能为负，请重新输入！");
			$(this).focus().val(0);
			$("#pay_money").text(0);//应付金额
			$("#paid_money").val(0);//本次付款
			return;
		}
		if(isNaN(count*price)){
			$("#pay_money").text(0);
			$("#paid_money").val(0);
		}else{
			$("#pay_money").text((count*price).toFixed(2));
			$("#paid_money").val((count*price).toFixed(2));
		}
	});
	
	// 提交表单(验证价格是否为负和字段是否超过长度)
	$("#btn_submit").click(function(){
		$("#tip").remove();
		if (Validator.Validate(this.form, 3)){
			var price = $("#price").val();//单价
			if(price < 0){
				alert("单价不能为负，请重新输入！");
				$("#price").focus();
				return false;
			}
			$("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	        $("#btn_reset").attr("disabled", "true");
			f.submit();
		}
	});
});

//正则表达式：只能输入数字和带小数点数字
function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		//if(this.value.length == 0) this.value = "0";
	});
}
//正则表达式：只能输入数字
function setOnlyNum1() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	var regInteger = /^[-\+]?\d+$/;
	$(this).keypress(function (){
		if(!this.value.match(regInteger))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(regInteger))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(regInteger))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(regInteger))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(regInteger))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = "0";
	});
}
//]]>--></script> 
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>