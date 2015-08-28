<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
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
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align: middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
  	<h1 style="text-align:center;font-weight:bold;font-size:20px;padding:5px;">${acc.title}</h1>
  	<div style="font-weight:bold;text-align:right;font-size:14px;padding-bottom:15px;">出账时间：<fmt:formatDate value="${acc.add_date}" pattern="yyyy年MM月dd日" /></div>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2" id="divExcel">
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="10%">订单流水号</td>
        <td nowrap="nowrap" align="center" width="10%">分公司</td>
        <td nowrap="nowrap" align="center" width="10%">专卖店编号</td>
        <td nowrap="nowrap" align="center">明细（型号，单价，数量）</td>
        <td nowrap="nowrap" align="center" width="12%">合计金额</td>
        <td nowrap="nowrap" align="center" width="12%">出账时间</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center"><c:out value="${fnx:leftPad_sis(cur.sell_bill_id, 12, '0')}" /></td>
            <td align="left">${fn:escapeXml(cur.map.dept_name)}</td>
            <td align="left">${fn:escapeXml(cur.zmd_sn)}</td>
            <td><c:forEach var="cur1" items="${cur.map.konkaXxSellBillDetailsList}" varStatus="vs"> [${fn:escapeXml(cur1.md_name)}，<fmt:formatNumber value="${cur1.price}" pattern="0.00" />元，${cur1.counts}台]
                <c:if test="${not vs.last}">；</c:if>
              </c:forEach></td>
            <td align="right" nowrap="nowrap"><img src="${ctx}/images/yen.png" alt="￥" title="RMB" />
            	<fmt:formatNumber value="${cur.total_money}" pattern="0.00" /></td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
          </tr>
        </c:forEach>
	      <c:if test="${empty entityList}">
	        <tr>
	          <td align="center" colspan="6">无订单记录</td>
	        </tr>
	      </c:if>
      </tbody>
    </table>
    <div style="font-weight:bold;text-align:center;margin-top:15px;font-size:14px;">
	 <span style="float:left;">截至昨日总收款：<fmt:formatNumber value="${acc.bill_money_s}" /> 元</span>
	 今日收款：<fmt:formatNumber value="${acc.bill_money}" /> 元
	 <span style="float:right;">总收款：<fmt:formatNumber value="${acc.bill_money_e}" /> 元</span>
    </div>
    <br />
    <input class="but5" type="button"  value="返回" onclick="history.back();return false;" />
    <input class="but4" type="button"  value="下载" onclick="toExcel('divExcel', '?method=load');" />
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
});
//]]>
</script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
