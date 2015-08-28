<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>产品销售 &gt; 销售记录</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：产品销售 &gt; 销售记录 &gt; 详细信息</div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tableTop">
      <tr>
        <td><strong class="fb">客户:</strong>${cust.name}
          &nbsp;&nbsp;&nbsp;&nbsp; <strong class="fb">销售日期:</strong>
          <fmt:formatDate value="${sellBill.sell_date}" pattern="yyyy-MM-dd" />
          &nbsp;&nbsp;&nbsp;&nbsp; <strong class="fb"><font color="red">NO:</font></strong><font color="red">${sellBill.sn}</font></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
      <tr>
        <th>行号</th>
        <th>产品型号</th>
        <th>产品类型</th>
        <th>品牌</th>
        <th>数量</th>
        <th>单价</th>
        <th>金额</th>
        <th>备注</th>
      </tr>
      <c:forEach items="${detailList}" var="cur" varStatus="status">
        <tr>
          <td>${status.count}</td>
          <td>${fn:escapeXml(cur.pd_name)}</td>
          <td>${fn:escapeXml(cur.pd_type_name)}</td>
          <td>${fn:escapeXml(cur.brand_name)}</td>
          <td>${cur.count}</td>
          <td><fmt:formatNumber type="currency" value="${cur.price}" /></td>
          <td><fmt:formatNumber type="currency" value="${cur.count*cur.price}" /></td>
          <td>${fn:escapeXml(cur.remarks)}</td>
        </tr>
      </c:forEach>
      <tr>
        <td colspan="8"><strong class="fb">说明:</strong>${fn:escapeXml(sellBill.remarks)}
          &nbsp;&nbsp;&nbsp;&nbsp; <strong class="fb">经办人:</strong>${fn:escapeXml(sellBill.opr_man)}
          &nbsp;&nbsp;&nbsp;&nbsp; <strong class="fb">应收金额:</strong>
          <fmt:formatNumber type="currency" value="${sellBill.money}" />
          &nbsp;&nbsp;&nbsp;&nbsp; <strong class="fb"><font color="red">本次付款:</font></strong> &nbsp;&nbsp;&nbsp;&nbsp;
          <fmt:formatNumber type="currency" value="${sellBill.pay_money}" /></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <c:url value="" var="expPara">
      <c:param name="method" value="toExcel"/>
      <c:param name="type" value="xsddetail"/>
      <c:param name="keySeq" value="${af.map.keySeq}"/>
      <c:param name="sell_bill_id" value="${af.map.sell_bill_id}"/>
    </c:url>
    <html-el:form action="/JxcSellBill${expPara}" styleClass="saveForm">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding-left:10px;">
        <tr>
          <td height="26"><div class="left">
              <!--
        <input name="button" type="button" class="bgButton" id="print" value="打印" />
        <input name="button" type="button" class="bgButton" id="toExcel" value="导出" />
         -->
              <input class="bgButtonBack" type="button" name="btn_back"  id="btn_back" value="返 回"  onclick="history.back();"/>
            </div></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#gotoList").click(function(){
		window.location.href = "${ctx}/jxc/JxcSellBill.do?method=list&keySeq=${af.map.keySeq}";
	});

	if("printView" == "${af.map.status}"){
		var type = "xsddetail";
		var keySeq = "${af.map.keySeq}";
		var sell_bill_id = "${af.map.sell_bill_id}";
		var param ="keySeq=" + keySeq + "&type=" + type  + "&sell_bill_id=" + sell_bill_id;
		window.showModalDialog("${ctx}/client/manager/JxcSellBill.do?method=print&"+param, window, "dialogWidth:900px;status:no;dialogHeight:580px");
	}

	if("outView" == "${af.map.status}"){
		$(".saveForm").submit();
	}
});

var type = "xsddetail";
var keySeq = "${af.map.keySeq}";
var sell_bill_id = "${af.map.sell_bill_id}";
var param ="keySeq=" + keySeq + "&type=" + type  + "&sell_bill_id=" + sell_bill_id;

$("#toExcel").click(function(){
	this.form.submit();
	window.open("${ctx}/jxc/JxcSellBill.do?method=toExcel&" + param, "下载页面", "height=100,width=100");
});

$("#print").click(function(){
	window.showModalDialog("${ctx}/client/manager/JxcSellBill.do?method=print&"+param, window, "dialogWidth:900px;status:no;dialogHeight:580px"); 
});
//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>