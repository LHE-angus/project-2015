<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单详细</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
select{font-family:Microsoft YAHEI;font-size:12px;}
input{font-family:Microsoft YAHEI;font-size:12px;}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div style="width:100%">
	<div class="oartop">
	    <table width="400" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
	        <td>当前位置：${naviString}</td>
	      </tr>
	    </table>
  	</div>
  <div class="rtabcont1">
    <html-el:form action="/manager/JxcKonkaOrderRegister.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="4">订单信息</th>
        </tr>
        <tr>
          <td class="title_item" width="15%">订单流水号：</td>
          <td colspan="3"><font color="red">${fn:escapeXml(af.map.trade_index)}</font></span></td>
        </tr>
        <tr>
          <td class="title_item" width="15%">订单日期：</td>
          <td><fmt:formatDate value="${af.map.order_date}" pattern="yyyy-MM-dd"/></td>
          <td class="title_item" width="15%">配送方式：</td>
          <td>
          	<c:if test="${af.map.send_type eq 1}">自提</c:if>
            <c:if test="${af.map.send_type eq 2}">配送</c:if>
          </td>
        </tr>
        <tr>
          <td class="title_item" width="15%">货款支付方式：</td>
          <td colspan="3">
			<c:if test="${af.map.pay_type eq 4}">现款</c:if>
            <c:if test="${af.map.pay_type eq 5}">担保</c:if>
		</td>
        </tr>
        <tr>
          <td class="title_item" width="15%">运费：</td>
          <td colspan="3">${fn:escapeXml(af.map.freight)}</td>
        </tr>
        <tr>
          <td class="title_item" width="15%">备注：</td>
          <td colspan="3">${fn:escapeXml(af.map.remark)}</td>
        </tr>
        <tr>
          <td colspan="4"><table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
              <tr class="title_top">
                <td nowrap="nowrap">产品型号</td>
                <td width="5%" nowrap="nowrap">数量</td>
                <td width="5%" nowrap="nowrap">单位</td>
                <td width="10%" nowrap="nowrap">单价</td>
                <td width="10%" nowrap="nowrap">金额</td>
                <td width="8%" nowrap="nowrap">折扣(%)</td>
                <td width="10%" nowrap="nowrap">折扣金额</td>
                <td width="12%" nowrap="nowrap">产品备注</td>
              </tr>
              <c:forEach items="${konkaOrderInfoDetailsList}" var="cur">
                <tr>
                  <td align="center">${fn:escapeXml(cur.pd_name)}</td>
                  <td align="center">${fn:escapeXml(cur.good_count)}</td>
                  <td align="center">${fn:escapeXml(cur.good_unit)}</td>
                  <td align="center"><fmt:formatNumber value="${fn:escapeXml(cur.good_price)}" pattern="0.00" /></td>
                  <td align="center"><fmt:formatNumber value="${fn:escapeXml(cur.good_sum_price)}" pattern="0.00" /></td>
                  <td align="center"><fmt:formatNumber value="${fn:escapeXml(cur.good_discount)}" pattern="0.00" /></td>
                  <td align="center"><fmt:formatNumber value="${fn:escapeXml(cur.good_discount_price)}" pattern="0.00" /></td>
                  <td align="center">${fn:escapeXml(cur.pd_remark)}</td>
                </tr>
              </c:forEach>
            </table></td>
        </tr>
        <tr>
          <td class="title_item" width="15%">收货人商铺名称：</td>
          <td>${fn:escapeXml(af.map.user_shop_name)}</td>
          <td class="title_item" width="15%">收货人姓名：</td>
          <td>${fn:escapeXml(af.map.user_name)}</td>
        </tr>
        <tr>
          <td class="title_item" width="15%">收货人固定电话：</td>
          <td>${fn:escapeXml(af.map.user_tel)}</td>
          <td class="title_item" width="15%">收货人手机：</td>
          <td>${fn:escapeXml(af.map.user_phone)}</td>
        </tr>
        <tr>
          <td class="title_item" width="15%">收货人所属地区：</td>
          <td colspan="3">${fn:escapeXml(af.map.fullName)}</td>
        </tr>
        <tr>
          <td class="title_item" width="15%">收货人地址：</td>
          <td colspan="3">${fn:escapeXml(af.map.user_addr)}</td>
        </tr>
        <tr>
          <td class="title_item" width="15%">收货人邮编：</td>
          <td>${fn:escapeXml(af.map.user_zip)}</td>
          <td class="title_item" width="15%">收货人备注：</td>
          <td>${fn:escapeXml(af.map.user_remark)}</td>
        </tr>
        <tr>
          <td class="title_item" width="15%">订单状态：</td>
          <td><c:if test="${af.map.order_state eq -1}">已取消</c:if>
            <c:if test="${af.map.order_state eq 0}">已预定</c:if>
            <c:if test="${af.map.order_state eq 1}">已付款</c:if>
            <c:if test="${af.map.order_state eq 2}">已发货</c:if>
            <c:if test="${af.map.order_state eq 3}">已到货</c:if>
            <c:if test="${af.map.order_state eq 4}">已确认</c:if>
            <c:if test="${af.map.order_state eq 9}">已关闭</c:if></td>
          <td class="title_item" width="15%">订单产品数量：</td>
          <td>${fn:escapeXml(af.map.order_num)}</td>
        </tr>
        <tr>
          <td class="title_item" width="15%">订单金额：</td>
          <td><fmt:formatNumber value="${fn:escapeXml(af.map.money)}" pattern="0.00" />
            (元)</td>
          <td class="title_item" width="15%">订单折扣金额：</td>
          <td><fmt:formatNumber value="${fn:escapeXml(af.map.good_discount_price)}" pattern="0.00" />
            (元)</td>
        </tr>
        
        <tr>
          <th colspan="4">订单审核信息</th>
        </tr>
        <tr>
          <td class="title_item" width="15%">订单审核状态：</td>
          <td colspan="3"><c:if test="${af.map.audit_state eq 0}">未审核 </c:if>
            <c:if test="${af.map.audit_state eq 1}"><span style="color:#00f;">审核中</span> </c:if>
            <c:if test="${af.map.audit_state eq 3}"><span style="color:#060;">审核通过</span> </c:if>
            <c:if test="${af.map.audit_state eq 2}"><span style="color:#f00;">审核未通过</span></c:if></td>
        </tr>
        <tr>
          <td class="title_item" width="15%"><c:if test="${(af.map.audit_state eq 0) or (af.map.audit_state eq 1)}"> 待审核角色： </c:if>
            <c:if test="${(af.map.audit_state eq 2) or (af.map.audit_state eq 3)}"> 最后审核角色： </c:if></td>
          <td colspan="3"> ${fn:escapeXml(af.map.map.audit_role_name)} </td>
        </tr>
        <c:if test="${not empty konkaOrderAuditList}">
        <tr>
        	<td colspan="4">
        	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
                <tr>
                  <td class="title_item" width="5%" style="text-align:center;">序号</td>
                  <td class="title_item" width="18%" style="text-align:center;">审核角色</td>
                  <td class="title_item" width="10%" style="text-align:center;">审核人</td>
                  <td class="title_item" width="12%" style="text-align:center;">审核时间</td>
                  <td class="title_item" width="10%" style="text-align:center;">结果</td>
                  <td class="title_item" style="text-align:center;">意见</td>
                </tr>
                <c:forEach items="${konkaOrderAuditList}" var="cur" varStatus="st">
                  <tr>
                    <td align="center" >${st.count}</td>
                    <td align="center">${fn:escapeXml(cur.map.role_name)}</td>
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
        <tr>
          <td colspan="4" align="center"><input class="bgButtonBack" type="submit" name="order_back" value="返回" onclick="history.back();return false;" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
