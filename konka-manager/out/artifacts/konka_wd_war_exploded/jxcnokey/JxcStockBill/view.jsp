<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>进货单详细</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：进货管理 &gt; 进货记录 &gt; 进货单详细</div>
  <div class="rtabcont1">
    <html-el:form action="/JxcStockBill.do">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" />
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
          <td><fmt:formatDate value="${af.map.in_date }" pattern="yyyy-MM-dd"></fmt:formatDate></td>
        </tr>
        <tr>
          <td class="title_item">供应商名称：</td>
          <td> ${fn:escapeXml(af.map.map.supplier_name)} </td>
        </tr>
        <tr>
          <td class="title_item">应付金额(元)：</td>
          <td>${af.map.pay_money}</td>
        </tr>
        <tr id="pc_result_tr">
          <td class="title_item">实付金额(元)： </td>
          <td>${af.map.paid_money}</td>
        </tr>
        <tr>
          <td class="title_item">经办人：</td>
          <td>${af.map.opr_man}</td>
        </tr>
        <tr>
          <td class="title_item">说明：</td>
          <td>${af.map.remarks}</td>
        </tr>
        <tr>
          <th colspan="2">进货单明细</th>
        </tr>
        <tr>
          <td colspan="2"><table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
              <tr class="title_top">
                <td width="15%">产品类型</td>
                <td width="15%">产品品牌</td>
                <td width="15%">产品型号</td>
                <td width="15%">进货数量</td>
                <td width="15%">进货单价</td>
                <td>备注</td>
              </tr>
              <c:forEach items="${jxcStockBillDetailsList}" var="cur">
                <tr>
                  <td>${fn:escapeXml(cur.pd_type_name)}</td>
                  <td>${fn:escapeXml(cur.brand_name)}</td>
                  <td>${fn:escapeXml(cur.pd_name)}</td>
                  <td>${fn:escapeXml(cur.count)}</td>
                  <td>${fn:escapeXml(cur.price)}</td>
                  <td>${fn:escapeXml(cur.remarks)}</td>
                </tr>
              </c:forEach>
            </table></td>
        </tr>
        <tr>
          <td colspan="2" align="center"><input class="bgButtonBack" type="submit" name="stock_bill_back" value="返回" onclick="history.back();return false;" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[

//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
