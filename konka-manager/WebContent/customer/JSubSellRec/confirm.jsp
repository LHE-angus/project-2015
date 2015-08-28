<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单打印</title>
<style>
	table{
		border-collapse:collapse;
	}
	table tr{
	    line-height:25px;
	}
</style>
<base target="_self" /> 
</head>
<body>
<html-el:form action="/manager/JSubSellRec.do">
    <html-el:hidden property="method" value="saveConfirm" />
    <html-el:hidden property="mod_id" styleId="mod_id" />
    <html-el:hidden property="bill_id" styleId="bill_id" />
<table width="100%" border="0" >
	<tr>
		<td colspan="7" nowrap="nowrap"><span style="padding-right: 10px">销售单号：&nbsp;${af.map.bill_sn }&nbsp;</span></td>
	</tr>
	<tr>
		<th colspan="7" align="center" nowrap="nowrap"><span style="padding-right: 10px">${sell_cust_name}分销单</span></th>
	</tr>
	<tr>
		<td colspan="7" align="center" nowrap="nowrap"><span style="padding-right: 10px"><fmt:formatDate value="${nowDate}" pattern="yyyy年MM月dd日" /></span></td>
	</tr>
	<tr>
		<td colspan="7" nowrap="nowrap">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="3" nowrap="nowrap">收货单位：&nbsp;${partner.partner_name}&nbsp;</td>
		<td colspan="2" nowrap="nowrap">联系人：&nbsp;${partner.link_name}&nbsp;</td>
		<td colspan="2" nowrap="nowrap">联系电话：&nbsp;${partner.link_tel}&nbsp;</td>
	</tr>
</table>
<table width="100%" border="1" >
	<tr>
		<th nowrap="nowrap" align="center">产品</th>
		<th nowrap="nowrap" width="5%" align="center">数量</th>
		<th nowrap="nowrap" width="15%" align="center">单价</th>
		<th nowrap="nowrap" align="center">金额</th>
		<th nowrap="nowrap" align="center">折扣金额</th>
		<th nowrap="nowrap" width="20%" align="center" colspan="2">备注</th>
	</tr>
	<c:set var="num_sum" value="0"></c:set>
	<c:set var="price_sum" value="0"></c:set>
	<c:set var="money_sum" value="0"></c:set>
	<c:set var="dis_sum" value="0"></c:set>
	<c:forEach items="${entityList}" var="cur" varStatus="vs">
	<tr>
		<td>&nbsp;${cur.map.type_name}&nbsp;&nbsp;${cur.map.goods_name }</td>
		<td nowrap="nowrap" align="right">&nbsp;${cur.num}&nbsp;</td>
		<td nowrap="nowrap" align="right">&nbsp;<fmt:formatNumber type="number" maxFractionDigits="2" value="${cur.price}"></fmt:formatNumber>&nbsp;</td>
		<td nowrap="nowrap" align="right">&nbsp;<fmt:formatNumber type="number" maxFractionDigits="2" value="${cur.money}"></fmt:formatNumber>&nbsp;</td>
		<td nowrap="nowrap" align="right">&nbsp;<fmt:formatNumber type="number" maxFractionDigits="2" value="${cur.dis_money}"></fmt:formatNumber>&nbsp;</td>
		<td nowrap="nowrap" colspan="2">${cur.notes}</td>
		<c:set var="num_sum" value="${num_sum+ cur.num}"></c:set>
		<c:set var="price_sum" value="${price_sum+ cur.price}"></c:set>
		<c:set var="money_sum" value="${money_sum+ cur.money}"></c:set>
		<c:set var="dis_sum" value="${dis_sum+ cur.dis_money}"></c:set>
	</tr>
	</c:forEach>
	<tr>
		<td align="right">合计：</td>
		<td align="right">&nbsp;${num_sum }&nbsp;</td>
		<td align="right">&nbsp;<fmt:formatNumber type="number" maxFractionDigits="2" value="${price_sum }"></fmt:formatNumber>&nbsp;</td>
		<td align="right">&nbsp;<fmt:formatNumber type="number" maxFractionDigits="2" value="${money_sum }"></fmt:formatNumber>&nbsp;</td>
		<td align="right">&nbsp;<fmt:formatNumber type="number" maxFractionDigits="2" value="${dis_sum }"></fmt:formatNumber>&nbsp;</td>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td align="right">应收金额：</td>
		<td>&nbsp;<fmt:formatNumber type="number" maxFractionDigits="2" value="${af.map.rec_money }"></fmt:formatNumber>&nbsp;</td>
		<td align="right">实收金额: </td>
		<td>&nbsp;<html-el:text property="money" styleClass="webinput" styleId="money" maxlength="10" size="20" value="${af.map.rec_money }"/>&nbsp;</td>
		<td align="right" nowrap="nowrap">&nbsp;</td>
		<td colspan="2">&nbsp;</td>
	</tr>
	</table>
	<div align="center" id="div_button">
	  <input name="print" type="button" class="bgButtonEdit" value="确认"/>
	  <input name="close" type="button" class="bgButtonBack" value="关闭"/>
	</div>
</html-el:form>



<script type="text/javascript" src="${ctx}/scripts/print.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript">//<![CDATA[
var form = document.forms[0];
$(document).ready(function(){
	$(".bgButtonEdit").click(function(){
		if($("#money").val()>${af.map.rec_money }){
			alert("实收金额不能超过应收金额！");
			return false;
		}
		 $("#money").attr("require","true").attr("dataType", "Custom").attr("regexp", "^\\d{1,8}(\\.\\d{0,2})?$").attr("msg" ,"实收金额为整数且不能小于0！");
		if(Validator.Validate(this.form, 3)){
			form.submit();
		}
	});
	
	$(".bgButtonBack").click(function(){
		window.close();
	});
	
});

//正则表达式：只能输入数字
function setOnlyInt(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:\d+(?:\d+)?|\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\d+$/))obj.value=obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		if(obj.value.length == 0 || isNaN(obj.value) || obj.value == 0) obj.value = "1";
	});
}
		
//]]></script>
</body>
</html>