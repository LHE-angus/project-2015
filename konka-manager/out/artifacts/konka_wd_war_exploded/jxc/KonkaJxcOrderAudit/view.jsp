<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcOrderAudit.do">
      <html-el:hidden property="queryString" />
      <html-el:hidden property="method" value="saveAudit"/>
      <html-el:hidden property="audit_level" value="${af.map.audit_level}" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="id" styleId="id" value="${af.map.id}" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass" id="tb">
        <tr>
          <th colspan="2">订单基本信息</th>
        </tr>
        <tr>
          <td width="15%" class="title_item">交易流水号：</td>
          <td>${fn:escapeXml(af.map.trade_index)}</td>
        </tr>
        <tr>
          <td class="title_item">客户名称：</td>
          <td>${fn:escapeXml(af.map.user_shop_name)}</td>
        </tr>
        <tr>
          <td  class="title_item">下单时间：</td>
          <td><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
        <td class="title_item">配送方式：</td>
          <td>
          	<c:if test="${af.map.send_type eq 1}">自提</c:if>
            <c:if test="${af.map.send_type eq 2}">配送</c:if>
          </td>
        </tr>
		<tr>
          <td class="title_item">货款支付方式：</td>
          <td>
			<c:if test="${af.map.pay_type eq 4}">现款</c:if>
            <c:if test="${af.map.pay_type eq 5}">担保</c:if>
		</td>
        </tr>
		<tr>
          <td class="title_item">运费：</td>
          <td>
            ${fn:escapeXml(af.map.freight)}
		</td>
        </tr>
        <tr>
          <td  class="title_item">备注：</td>
          <td>${fn:escapeXml(af.map.remark)}</td>
        </tr>
        <tr>
          <td  class="title_item">订单产品数量：</td>
          <td>${fn:escapeXml(af.map.order_num)}</td>
        </tr>
        <tr>
          <td  class="title_item">订单金额（元）：</td>
          <td><fmt:formatNumber value="${af.map.money}" pattern="0.00" /></td>
        </tr>
        <tr>
          <td  class="title_item">订单折扣金额（元）：</td>
          <td><fmt:formatNumber value="${af.map.good_discount_price}" pattern="0.00" /></td>
        </tr>
        <tr>
          <td class="title_item">客户意见:</td>
          <td>
          ${fn:escapeXml(af.map.map.shop_name)}&nbsp;客户&nbsp;&nbsp;&nbsp; 提交时间：<fmt:formatDate value="${af.map.add_date}" pattern="yyyy年MM月dd日 HH:mm:ss"/>
		</td>
        </tr>
        <tr>
          <td class="title_item">订单审核状态:</td>
          <td><c:if test="${af.map.audit_state eq 0}">未审核 </c:if>
            <c:if test="${af.map.audit_state eq 1}"><span style="color:#00f;">审核中</span> </c:if>
            <c:if test="${af.map.audit_state eq 3}"><span style="color:#060;">审核通过</span> </c:if>
            <c:if test="${af.map.audit_state eq 2}"><span style="color:#f00;">审核未通过</span></c:if></td>
        </tr>
        <tr>
          <td colspan="2" align="center"><table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
              <tr>
                <th colspan="9">订单明细</th>
              </tr>
              <tr class="title_top">
                <td width="5%" align="center">序号</td>
                <td width="15%" align="center">产品型号</td>
                <td width="8%" align="center">产品数量</td>
                <td width="8%" align="center">产品单位</td>
                <td width="10%" align="center">产品单价（元）</td>
                <td width="14%" align="center">产品金额（元）</td>
                <td width="10%" align="center">折扣（%）</td>
                <td width="10%" align="center">折扣金额（元）</td>
                <td width="10%" align="center">产品备注</td>
              </tr>
              <c:forEach items="${konkaOrderInfoDetailsList}" var="cur" varStatus="vs">
                <tr>
                  <td align="center">${vs.count}</td>
                  <td align="center">${fn:escapeXml(cur.pd_name)}</td>
                  <td align="center">${fn:escapeXml(cur.good_count)}</td>
                  <td align="center">${fn:escapeXml(cur.good_unit)}</td>
                  <td align="center"><fmt:formatNumber value="${cur.good_price}" pattern="0.00" /></td>
                  <td align="center"><fmt:formatNumber value="${cur.good_sum_price}" pattern="0.00" /></td>
                  <td align="center"><fmt:formatNumber value="${cur.good_discount}" pattern="0" /></td>
                  <td align="center"><fmt:formatNumber value="${cur.good_discount_price}" pattern="0.00" /></td>
                  <td align="center">${fn:escapeXml(cur.pd_remark)}</td>
                </tr>
              </c:forEach>
              <tr>
                <td align="center" colspan="9" style="text-align:center"><html-el:button styleClass="bgButtonBack" property="" styleId="btn_back" value="返回 " onclick="history.back();" /></td>
              </tr>
            </table></td>
        </tr>
        <c:if test="${not empty konkaOrderAuditList}">
        <tr>
            <th colspan="7">订单审核记录</th>
        </tr>
        <tr>
        	<td colspan="2">
        	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
                <tr>
                  <td class="title_item" width="5%" style="text-align:center;">序号</td>
                  <td class="title_item" width="18%" style="text-align:center;">审核角色</td>
                  <td class="title_item" width="10%" style="text-align:center;">审核状态</td>
                  <td class="title_item" width="10%" style="text-align:center;">审核人</td>
                  <td class="title_item" width="12%" style="text-align:center;">审核时间</td>
                  <td class="title_item" width="10%" style="text-align:center;">结果</td>
                  <td class="title_item" style="text-align:center;">意见</td>
                </tr>
                <c:forEach items="${konkaOrderAuditList}" var="cur" varStatus="st">
                  <tr>
                    <td align="center" >${st.count}</td>
                    <td align="center"><c:if test="${(af.map.audit_state eq 0) or (af.map.audit_state eq 1)}"> 当前审核角色： </c:if>
                      <c:if test="${(af.map.audit_state eq 2) or (af.map.audit_state eq 3)}"> 最后审核角色： </c:if>
                      ${fn:escapeXml(af.map.map.audit_role_name)} </td>
                    <td align="center"><c:if test="${af.map.audit_state eq 0}">未审核 </c:if>
                      <c:if test="${af.map.audit_state eq 1}"><span style="color:#00f;">审核中</span> </c:if>
                      <c:if test="${af.map.audit_state eq 3}"><span style="color:#060;">审核通过</span> </c:if>
                      <c:if test="${af.map.audit_state eq 2}"><span style="color:#f00;">审核未通过</span></c:if></td>
                    <td align="center">${fn:escapeXml(cur.audit_user)}</td>
                    <td align="center"><fmt:formatDate value="${cur.audit_datetime }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                    <td align="center"><c:if test="${cur.audit_result eq -1}">不同意</c:if>
                      <c:if test="${cur.audit_result eq 1}">同意</c:if></td>
                    <td align="left"><c:if test="${empty cur.audit_comment}">无</c:if>
                      <c:if test="${not empty cur.audit_comment}">${fn:escapeXml(cur.audit_comment)}</c:if></td>
                  </tr>
                </c:forEach>
              </table>
        	</td>
        </tr>
        </c:if>
      </table>
    </html-el:form>
  </div>
</div>
<jsp:include page="../public_page.jsp" flush="true"/>
<script type="text/javascript" src="${ctx}/scripts/print.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
var id = "${af.map.id}";
var param = "id=" + id +"&random=" + Math.random();

$("#btn_print").click(function(){
	window.showModalDialog("?method=print&" + encodeURI(param), window, "dialogWidth:900px;status:no;dialogHeight:580px");
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>