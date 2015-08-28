<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>打印</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div style="width:100%">
  <div class="rtabcont1">
    <table width="100%" border="1" cellpadding="0" cellspacing="1"  id="tb">
      <tr>
        <td width="15%" ></td>
        <td colspan="2" align="center">康佳集团${fn:escapeXml(fgsDept.dept_name)}分公司提货申请单</td>
        <td width="11%" ></td>
        <td width="22%" ></td>
      </tr>
      <tr>
        <td></td>
        <td align="center" colspan="2">${fn:escapeXml(nowDate)}</td>
        <td align="right"> 交易流水号：</td>
        <td align="left">${fn:escapeXml(af.map.trade_index)}</td>
      </tr>
      <tr>
        <td align="right">客户名称：</td>
        <td colspan="2">${fn:escapeXml(konkaR3Shop.r3_code)}&nbsp;&nbsp;${fn:escapeXml(konkaR3Shop.customer_name)}</td>
        <td width="11%" align="right">提货方式：</td>
        <td><c:if test="${af.map.send_type eq 1}">自提</c:if>
          <c:if test="${af.map.send_type eq 2}">配送</c:if></td>
      </tr>
      <tr>
        <td align="right">运费：</td>
        <td colspan="2"></td>
        <td align="right">货款支付方式：</td>
        <td ><c:if test="${af.map.pay_type eq 4}">现款</c:if>
          <c:if test="${af.map.pay_type eq 5}">担保</c:if></td>
      </tr>
      <tr>
        <td colspan="5" align="center"><table width="100%" border="1" cellpadding="0" cellspacing="1">
            <tr >
              <td width="15%" align="center">产品</td>
              <td width="8%" align="center">数量（台）</td>
              <td width="12%" align="center">产品单价（元）</td>
              <td width="12%" align="center">产品金额（元）</td>
              <td width="10%" align="center">项目</td>
              <td width="15%" align="center"  colspan="2">金额</td>
              <td width="18%" align="center" colspan="3">备注</td>
            </tr>
            <c:forEach items="${konkaOrderInfoDetailsList}" var="cur" varStatus="vs">
              <tr>
                <td align="center">${fn:escapeXml(cur.pd_name)}</td>
                <td align="center">${fn:escapeXml(cur.good_count)}</td>
                <td align="center"><fmt:formatNumber value="${cur.good_price}" pattern="0.00" /></td>
                <td align="center"><fmt:formatNumber value="${cur.good_sum_price}" pattern="0.00" /></td>
                <td align="center"></td>
                <td align="center" colspan="2"></td>
                <td align="center" colspan="3">${fn:escapeXml(cur.pd_remark)}</td>
              </tr>
            </c:forEach>
            <tr>
              <td align="center" ></td>
              <td align="center" ></td>
              <td align="center" ></td>
              <td align="center" ></td>
              <td align="center" >账期限额</td>
              <td align="center" colspan="2"></td>
              <td align="center" colspan="3"></td>
            </tr>
            <tr>
              <td align="center" ></td>
              <td align="center" ></td>
              <td align="center" ></td>
              <td align="center" ></td>
              <td align="center">账期余额</td>
              <td align="center" colspan="2"></td>
              <td align="center" colspan="3"></td>
            </tr>
            <tr>
              <td align="center" ></td>
              <td align="center" ></td>
              <td align="center" ></td>
              <td align="center" ></td>
              <td align="center" >本次收款</td>
              <td align="center" colspan="2"></td>
              <td align="center" colspan="3"></td>
            </tr>
            <tr>
              <td align="center" ></td>
              <td align="center" ></td>
              <td align="center" ></td>
              <td align="center" ></td>
              <td align="center" >收款方式</td>
              <td align="center" colspan="2"></td>
              <td align="center" colspan="3"></td>
            </tr>
            <tr>
              <td align="center" ></td>
              <td align="center" ></td>
              <td align="center" ></td>
              <td align="center" ></td>
              <td align="center" >本次可发货限额</td>
              <td align="center" colspan="2"></td>
              <td align="center" colspan="3"></td>
            </tr>
            <tr>
              <td align="center" ></td>
              <td align="center" ></td>
              <td align="center" ></td>
              <td align="center" ></td>
              <td align="center" >发货后余额</td>
              <td align="center" colspan="2"></td>
              <td align="center" colspan="3"></td>
            </tr>
            <tr>
              <td align="center" >合计</td>
              <td align="center" >${fn:escapeXml(af.map.order_num)}</td>
              <td align="center" ></td>
              <td align="center" ><fmt:formatNumber value="${af.map.money}" pattern="0.00" /></td>
              <td align="center" ></td>
              <td align="center" colspan="2"></td>
              <td align="left" colspan="3">收款人签名：</td>
            </tr>
            <tr>
              <td align="center" >销售订单号：</td>
              <td align="center" colspan="3"></td>
              <td align="center" >物流交货号：</td>
              <td align="center" colspan="2" ></td>
              <td align="left" colspan="3">账款或账期审核人签名：</td>
            </tr>
            <tr>
              <td align="center" >客户订单号：</td>
              <td align="center" colspan="3"></td>
              <td align="center" >备注：</td>
              <td align="center" colspan="2" ></td>
              <td align="center" colspan="3"></td>
            </tr>
            <c:if test="${not empty af.map.konkaOrderInfoAuditList}">
              <c:forEach items="${af.map.konkaOrderInfoAuditList}" var="cur1">
                <tr>
                  <td  align="left" colspan="10"> ${fn:escapeXml(cur1.map.role_name)}：${fn:escapeXml(cur1.audit_user)}&nbsp;&nbsp;&nbsp;时间：
                    <fmt:formatDate value="${cur1.audit_datetime }" pattern="yyyy年MM月dd日  HH:mm:ss"></fmt:formatDate>
                    &nbsp;&nbsp;&nbsp;意见：
                    <c:if test="${empty cur1.audit_comment}">无</c:if>
                    <c:if test="${not empty cur1.audit_comment}">${fn:escapeXml(cur1.audit_comment)}</c:if></td>
                </tr>
              </c:forEach>
            </c:if>
          </table></td>
      </tr>
      <tr>
          <td align="center">客户意见:</td>
          <td align="left" colspan="4">
          ${fn:escapeXml(konkaR3Shop.customer_name)}&nbsp;客户&nbsp;&nbsp;&nbsp; 提交时间：<fmt:formatDate value="${af.map.add_date}" pattern="yyyy年MM月dd日 HH:mm:ss"/>
		</td>
        </tr>
      <tr>
        <td align="center" >备注：</td>
        <td align="left" colspan="4">${fn:escapeXml(af.map.remark)}</td>
      </tr>
    </table>
  </div>
</div>
<div align="center" id="div_button">
  <input name="button" type="button" class="bgButtonPrint" value="打印"/>
  <input name="button" type="button" class="bgButtonBack" value="关闭" />
</div>
<jsp:include page="../public_page.jsp" flush="true"/>
<script type="text/javascript" src="${ctx}/scripts/print.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$(".bgButtonPrint").click(function(){
		$("#div_button").hide();
		window.print();
	})
	 $(".bgButtonBack").click(function(){
		 window.close();
	});
});
		
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>