<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>分销确认</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：产品销售 &gt; 分销确认</div>
  <div class="rtabcont1">
    <html-el:form action="/JxcFxConfirm">
      <html-el:hidden property="method" value="saveConfirm" />
      <html-el:hidden property="id" styleId="id" value="${af.map.id}" />
      <html-el:hidden property="supplier_name" styleId="supplier_name" value="${af.map.supplier_name}" />
      <html-el:hidden property="supplier_id" styleId="supplier_id" value="${af.map.supplier_id}" />
      <html-el:hidden property="keySeq" styleId="keySeq" value="${af.map.keySeq}"/>
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="2">产品分销基本信息</th>
        </tr>
        <tr>
          <td width="15%" class="title_item">销售单号</td>
          <td width="85%">${fn:escapeXml(af.map.sn)}</td>
        </tr>
        <tr>
          <td width="15%" class="title_item">销售日期</td>
          <td width="85%"><fmt:formatDate value="${af.map.sell_date}" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
          <td width="15%" class="title_item">应收金额</td>
          <td width="85%"><fmt:formatNumber value="${af.map.money}" pattern="0.00" /></td>
        </tr>
        <tr>
          <td width="15%" class="title_item">实收金额</td>
          <td width="85%"><fmt:formatNumber value="${af.map.pay_money}" pattern="0.00" /></td>
        </tr>
        <tr>
          <td width="15%" class="title_item">供应商</td>
          <td width="85%">${fn:escapeXml(af.map.supplier_name)}</td>
        </tr>
        <tr>
          <td width="15%" class="title_item">是否确认收货</td>
          <td width="85%"><c:choose>
              <c:when test="${af.map.is_confirm eq 0}"> <span style="color:#F00;">未确认</span></c:when>
              <c:when test="${af.map.is_confirm eq 1}"> <span style="color:#060;">已确认</span></c:when>
            </c:choose></td>
        </tr>
        <tr>
          <td colspan="2" align="center"><table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
              <tr>
                <th colspan="6">产品分销明细</th>
              </tr>
              <tr class="title_top">
                <td width="5%" align="center">序号</td>
                <td width="20%" align="center">产品大类</td>
                <td width="20%" align="center">产品品牌</td>
                <td align="center">产品型号</td>
                <td width="10%" align="center">销售数量</td>
                <td width="10%" align="center">单价</td>
              </tr>
              <c:forEach items="${jxcSellBillDetailsList}" var="cur" varStatus="vs">
                <tr>
                  <td align="center">${vs.count}</td>
                  <td align="center">${fn:escapeXml(cur.pd_type_name)}</td>
                  <td align="center">${fn:escapeXml(cur.brand_name)}</td>
                  <td align="center">${fn:escapeXml(cur.pd_name)}</td>
                  <td align="center"><html-el:hidden property="count" styleId="count" value="${cur.count}" />
                    ${fn:escapeXml(cur.count)}</td>
                  <td align="center"><html-el:hidden property="sell_bill_detail_id" styleId="sell_bill_detail_id" value="${cur.id}" />
                    <html-el:hidden property="price" styleId="price" value="${cur.price}" />
                    <fmt:formatNumber value="${cur.price}" pattern="0.00" /></td>
                </tr>
              </c:forEach>
              <tr>
                <td align="center" colspan="8" style="text-align:center"><html-el:button property="save" styleClass="bgButtonSave" styleId="btn_submit" value="保 存"/>
                  &nbsp;
                  <input type="button" class="bgButtonBack" value="返回 " onclick="history.back();" /></td>
              </tr>
            </table></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	var f = document.forms[0];
	$("#btn_submit").click(function(){
		var isSubmit = confirm("确认保存！");
		if (isSubmit){
			$(":submit").attr("value", "正在提交...").attr("disabled", "true");
			$(":button").attr("disabled", "true");
			f.submit();
		}
	});
	
});
//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>