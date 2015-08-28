<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<style type="text/css">
body { font-family:宋体,仿宋;font-size:14px;}
.bill-tab { border-collapse:collapse; margin:10px auto; }
.bill-tab th { text-align:center; font-weight:800; }
</style>
</head>
<body>
<c:set var="bill_title" value="" />
<c:set var="bill_m" value="" />	
<c:choose>
	<c:when test="${af.map.bill_type eq 10}"><c:set var="bill_title" value="采购" /><c:set var="bill_m" value="付" /></c:when>
	<c:when test="${af.map.bill_type eq 11}"><c:set var="bill_title" value="采购退货" /><c:set var="bill_m" value="收" /></c:when>
	<c:when test="${af.map.bill_type eq 20}"><c:set var="bill_title" value="销售" /><c:set var="bill_m" value="收" /></c:when>
	<c:when test="${af.map.bill_type eq 21}"><c:set var="bill_title" value="销售退货" /><c:set var="bill_m" value="付" /></c:when>
	<c:when test="${af.map.bill_type eq 40}"><c:set var="bill_title" value="分销" /><c:set var="bill_m" value="收" /></c:when>
	<c:when test="${af.map.bill_type eq 41}"><c:set var="bill_title" value="分销确认" /><c:set var="bill_m" value="付" /></c:when>
</c:choose>
<div>
  <table width="1000" border="0" cellspacing="0" cellpadding="5" class="bill-tab">
    <tr>
      <th colspan="3" height="50"><span style="font-size:20px;">${konkaR3Shop.customer_name}${bill_title}单</span></th>
    </tr>
    <tr>
      <td width="35%" nowrap="nowrap">${bill_title}单号：${af.map.bill_sn}</td>
      <td width="30%" align="center">创建日期：<fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd HH:mm" /></td>
      <td width="35%" align="right">交易日期：<fmt:formatDate value="${af.map.opr_date}" pattern="yyyy-MM-dd" /></td>
    </tr>
    <tr>
      <td nowrap="nowrap">客户名称：${af.map.jBasePartner.partner_name}</td>
      <td align="center">客户编码：${af.map.jBasePartner.partner_sn}</td>
      <td align="right">联系电话：${af.map.jBasePartner.link_mobile} ${af.map.jBasePartner.link_tel} ${af.map.jBasePartner.consignee_tel}</td>
    </tr>
    <tr>
      <td nowrap="nowrap" colspan="3" align="right">单位：元</td>
    </tr>
    <tr>
      <td colspan="3">
      	<table width="100%" border="1" cellspacing="0" cellpadding="5" style="border-collapse:collapse;">
      		<tr>
      			<th width="4%" nowrap="nowrap">序号</th>
      			<th width="10%" nowrap="nowrap">商品编码</th>
      			<th nowrap="nowrap">商品名称/规格</th>
      			<th width="8%">单位</th>
      			<th width="8%">数量</th>
      			<th width="8%">单价</th>
      			<th width="8%">金额</th>
      			<th width="20%">赠品/说明/备注</th>
      		</tr>
      		<c:set var="sum_num" value="0" />
      		<c:set var="sum_money" value="0" />
      		<c:forEach var="cur" items="${af.map.jBillDetailsList}" varStatus="vs">
	      		<tr>
	      			<td align="center">${vs.count}</td>
	      			<td align="center">${empty cur.jBaseGoods.goods_sn ? '-' : cur.jBaseGoods.goods_sn}</td>
	      			<td>${cur.jBaseGoods.goods_name}</td>
	      			<td align="center">${cur.jBaseGoods.goods_unit}</td>
	      			<td align="right">${cur.num}</td>
	      			<td align="right">${cur.price}</td>
	      			<td align="right">${cur.money}</td>
	      			<td align="center">${empty cur.gift_desc ? '-' : cur.gift_desc}/${empty cur.notes ? '-' : cur.notes}</td>
	      		</tr>
	      		<c:set var="sum_num" value="${cur.num + sum_num}" />
	      		<c:set var="sum_money" value="${cur.money + sum_money}" />
      		</c:forEach>
      		<tr>
      			<td align="center" colspan="4">合&nbsp;&nbsp;&nbsp;&nbsp;计</td>
      			<td align="right">${sum_num}</td>
      			<td></td>
      			<td align="right">${sum_money}</td>
      			<td colspan="2"></td>
      		</tr>
      	</table>
      </td>
    </tr>
    <tr>
      <td>应${bill_m}：${af.map.rec_money}</td>
      <td align="center">折扣：<c:if var="isnull" test="${empty af.map.discount or af.map.discount gt 99}">-</c:if><c:if test="${not isnull}">${af.map.discount}%</c:if></td>
      <td align="right">实${bill_m}：${af.map.money}</td>
    </tr>
	<tr>
		<td colspan="2" nowrap="nowrap">合计人民币（大写）：<span id="dx"></span></td>
		<td align="right" nowrap="nowrap">小写：${af.map.money}</td>
	</tr>
  </table>
</div>
<script type="text/javascript">//<![CDATA[
/***************************************** 
     Copyright (c) 2004, Laser Lu       
          http://www.idow.net            
 *****************************************/ 
function convertCurrency(currencyDigits) { 
// Constants: 
    var MAXIMUM_NUMBER = 99999999999.99; 
    // Predefine the radix characters and currency symbols for output: 
    var CN_ZERO = "零"; 
    var CN_ONE = "壹"; 
    var CN_TWO = "贰"; 
    var CN_THREE = "叁"; 
    var CN_FOUR = "肆"; 
    var CN_FIVE = "伍"; 
    var CN_SIX = "陆"; 
    var CN_SEVEN = "柒"; 
    var CN_EIGHT = "捌"; 
    var CN_NINE = "玖"; 
    var CN_TEN = "拾"; 
    var CN_HUNDRED = "佰"; 
    var CN_THOUSAND = "仟"; 
    var CN_TEN_THOUSAND = "万"; 
    var CN_HUNDRED_MILLION = "亿"; 
    var CN_SYMBOL = "人民币"; 
    var CN_DOLLAR = "元"; 
    var CN_TEN_CENT = "角"; 
    var CN_CENT = "分"; 
    var CN_INTEGER = "整"; 
     
// Variables: 
    var integral;    // Represent integral part of digit number. 
    var decimal;    // Represent decimal part of digit number. 
    var outputCharacters;    // The output result. 
    var parts; 
    var digits, radices, bigRadices, decimals; 
    var zeroCount; 
    var i, p, d; 
    var quotient, modulus; 
     
// Validate input string: 
    currencyDigits = currencyDigits.toString(); 
    if (currencyDigits == "") { 
        alert("Empty input!"); 
        return ""; 
    } 
    if (currencyDigits.match(/[^,.\d]/) != null) { 
        alert("Invalid characters in the input string!"); 
        return ""; 
    } 
    if ((currencyDigits).match(/^((\d{1,3}(,\d{3})*(.((\d{3},)*\d{1,3}))?)|(\d+(.\d+)?))$/) == null) { 
        alert("Illegal format of digit number!"); 
        return ""; 
    } 
     
// Normalize the format of input digits: 
    currencyDigits = currencyDigits.replace(/,/g, "");    // Remove comma delimiters. 
    currencyDigits = currencyDigits.replace(/^0+/, "");    // Trim zeros at the beginning. 
    // Assert the number is not greater than the maximum number. 
    if (Number(currencyDigits) > MAXIMUM_NUMBER) { 
        alert("Too large a number to convert!"); 
        return ""; 
    } 
     
// Process the coversion from currency digits to characters: 
    // Separate integral and decimal parts before processing coversion: 
    parts = currencyDigits.split("."); 
    if (parts.length > 1) { 
        integral = parts[0]; 
        decimal = parts[1]; 
        // Cut down redundant decimal digits that are after the second. 
        decimal = decimal.substr(0, 2); 
    } 
    else { 
        integral = parts[0]; 
        decimal = ""; 
    } 
    // Prepare the characters corresponding to the digits: 
    digits = new Array(CN_ZERO, CN_ONE, CN_TWO, CN_THREE, CN_FOUR, CN_FIVE, CN_SIX, CN_SEVEN, CN_EIGHT, CN_NINE); 
    radices = new Array("", CN_TEN, CN_HUNDRED, CN_THOUSAND); 
    bigRadices = new Array("", CN_TEN_THOUSAND, CN_HUNDRED_MILLION); 
    decimals = new Array(CN_TEN_CENT, CN_CENT); 
    // Start processing: 
    outputCharacters = ""; 
    // Process integral part if it is larger than 0: 
    if (Number(integral) > 0) { 
        zeroCount = 0; 
        for (i = 0; i < integral.length; i++) { 
            p = integral.length - i - 1; 
            d = integral.substr(i, 1); 
            quotient = p / 4; 
            modulus = p % 4; 
            if (d == "0") { 
                zeroCount++; 
            } 
            else { 
                if (zeroCount > 0) 
                { 
                    outputCharacters += digits[0]; 
                } 
                zeroCount = 0; 
                outputCharacters += digits[Number(d)] + radices[modulus]; 
            } 
            if (modulus == 0 && zeroCount < 4) { 
                outputCharacters += bigRadices[quotient]; 
                zeroCount = 0; 
            } 
        } 
        outputCharacters += CN_DOLLAR; 
    } 
    // Process decimal part if there is: 
    if (decimal != "") { 
        for (i = 0; i < decimal.length; i++) { 
            d = decimal.substr(i, 1); 
            if (d != "0") { 
                outputCharacters += digits[Number(d)] + decimals[i]; 
            } 
        } 
    } 
    // Confirm and return the final output string: 
    if (outputCharacters == "") { 
        outputCharacters = CN_ZERO + CN_DOLLAR; 
    } 
    if (decimal == "") { 
        outputCharacters += CN_INTEGER; 
    } 
    outputCharacters = CN_SYMBOL + outputCharacters; 
    return outputCharacters; 
}

document.getElementById("dx").innerHTML = convertCurrency("${af.map.money}");
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
