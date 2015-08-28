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
  <div id="print" class="rtabcont2" align="center" title="出库单" style="margin-top:4px;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td colspan="4" height="36" align="center"><span style="font-weight:bold;font-size:20px;">出库单</span></td>
      </tr>
      <tr>
        <td colspan="3" style="font-size:14px;"><span style="font-weight:700;">订单流水号：</span> <span style="color:#F00;font-weight:700;font-family:'Arial Narrow','宋体';font-size:200%;">
          <c:out value="${fnx:leftPad_sis(sell_bill_id, 12, '0')}" />
          </span></td>
        <td align="right" rowspan="2" width="40%"><img src="${ctx}/barcode.jpg?msg=${fnx:leftPad_sis(sell_bill_id, 12, '0')}" style="float:right;" height="80px" width="200px" /></td>
      </tr>
      <tr>
        <td align="left" width="20%" nowrap="nowrap"><b>客户名称：</b>
          <c:out value="${buyer_name}" /></td>
        <td align="left" width="20%" nowrap="nowrap"><b>联系电话：</b>
          <c:out value="${buyer_tel}" /></td>
        <td align="left" width="20%" nowrap="nowrap"><b>销售日期：</b>
          <fmt:formatDate value="${sell_date}" pattern="yyyy年M月d日"/></td>
      </tr>
    </table>
    <table id="trMergeTable" width="100%" border="1" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td align="center">仓位名称</td>
        <td width="15%" align="center">产品名称</td>
        <td width="10%" align="center">规格</td>
        <td width="10%" align="center">单位</td>
        <td width="10%" align="center">数量</td>
        <td width="10%" align="center">单价</td>
        <!--	       <td width="15%" align="center">总计（元）</td>-->
        <!--<td width="15%" align="center">出库人</td>-->
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="left" class="fs" height="26"><font class="blue12px">${cur.factory_id}_${cur.store_id}_${cur.store_name}</font></td>
          <td align="left" height="26"><font class="blue12px">${cur.md_name}</font></td>
          <td align="center" height="26"><font class="blue12px">
            <c:out value="${empty cur.map.spec ? '暂无' : cur.map.spec}" />
            </font></td>
          <td align="center" height="26"><font class="blue12px"></font>台</td>
          <td align="center" height="26"><font class="blue12px">${cur.counts}</font></td>
          <td align="right" height="26"><span class="kz-price-12">
            <fmt:formatNumber type="currency" value="${cur.map.price}" />
            </span></td>
          <!--<td></td>-->
        </tr>
      </c:forEach>
    </table>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" style="margin-top:20px;margin-bottom:10px;">
      <tr>
        <td rowspan="3" align="left" valign="top" style="font-weight:bold;" nowrap="nowrap"> 已收款：
          <c:if test="${pay_way_id eq 2}"><span class="kz-price">
            <fmt:formatNumber value="${total_pay}" type="currency" />
            </span></c:if>
          <!-- 终端POS刷卡 -->
          <c:if test="${pay_way_id eq 1}"><span class="kz-price">
            <fmt:formatNumber value="${total_pay}" type="currency" />
            </span></c:if>
          <!-- 收取现金 -->
          <c:if test="${pay_way_id eq 3}"><span class="kz-price">
            <fmt:formatNumber value="${empty money_of_deposit ? 0 : money_of_deposit}" type="currency" />
            </span></c:if>
          <!-- 货到付款 -->
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          欠款：
          <c:if test="${pay_way_id eq 2}"><span class="kz-price">
            <fmt:formatNumber value="0" type="currency" />
            </span></c:if>
          <!-- 终端POS刷卡 -->
          <c:if test="${pay_way_id eq 1}"><span class="kz-price">
            <fmt:formatNumber value="0" type="currency" />
            </span></c:if>
          <!-- 收取现金 -->
          <c:if test="${pay_way_id eq 3}"><span class="kz-price">
            <fmt:formatNumber value="${empty money_of_deposit ? total_pay : total_pay - money_of_deposit}" type="currency" />
            </span></c:if>
          <!-- 货到付款 -->
        </td>
        <td align="right" height="36" nowrap="nowrap">
        	<b>出库人签字：</b>
        	<input type="text" style="border-bottom:1px solid #000;border-top:1px solid #fff;border-left:1px solid #fff;border-right:1px solid #fff;" size="20" />
        </td>
      </tr>
      <tr>
      	<td align="right" height="36" nowrap="nowrap">
      		<b>收货人签字：</b>
        	<input type="text" style="border-bottom:1px solid #000;border-top:1px solid #fff;border-left:1px solid #fff;border-right:1px solid #fff;" size="20" />
        </td>
      </tr>
      <tr>
        <td align="right" height="30" nowrap="nowrap">
        	<b>日期：</b>
        	<input type="text" style="border-bottom:1px solid #000;border-top:1px solid #fff;border-left:1px solid #fff;border-right:1px solid #fff;text-align:center;" size="20" value="<fmt:formatDate value="${today}" pattern="yyyy年M月d日" />" />
        </td>
      </tr>
    </table>
  </div>
  <div align="left" style="margin-left:10px;margin-bottom:20px;">
    <input class="but5" type="button"  value="返回" onclick="history.back();return false;" />
    <input class="but_excel" type="button"  value="打印" onclick="win_print();" />
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#cnn_money").html(toUpperRMB("${total_pay}"));

	trMerge("fs", $("#trMergeTable"));	
});

function win_print(){
	prnhtml=$("#print").html();
	var oWin=window.open("","_blank");  
    var strPrint="<h4 style=’font-size:18px; text-align:center;’>打印预览区</h4>\n";  
      
    strPrint=strPrint + "<script type=\"text/javascript\">\n";  
    strPrint=strPrint + "function printWin()\n";  
    strPrint=strPrint + "{";  
    strPrint=strPrint +    "var oWin=window.open(\"\",\"_blank\");\n";  
    strPrint=strPrint + "oWin.document.write(document.getElementById(\"content\").innerHTML);\n";  
    strPrint=strPrint + "oWin.focus();\n";  
    strPrint=strPrint + "oWin.document.close();\n";  
    strPrint=strPrint + "oWin.print()\n";  
    strPrint=strPrint + "oWin.close()\n";  
    strPrint=strPrint + "}\n";  
    strPrint=strPrint + "<\/script>\n";  
      
    strPrint=strPrint + "<hr size='1'/>\n";  
    strPrint=strPrint + "<div id=\"content\">\n";  
    strPrint=strPrint + prnhtml + "\n";  
    strPrint=strPrint + "</div>\n";  
    strPrint=strPrint + "<hr size='1' />\n";  
    strPrint=strPrint + "<div style='text-align:center'><button onclick='printWin()' style='padding-left:4px;padding-right:4px;'>打  印</button><button onclick='window.opener=null;window.close();' style='padding-left:4px;padding-right:4px;'>关  闭</button></div>\n";  
    oWin.document.write(strPrint);  
    oWin.focus();  
    oWin.document.close();  
}

//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
