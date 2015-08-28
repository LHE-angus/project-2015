<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<META http-equiv=Expires content=0>
<META http-equiv=Cache-Control content=no-cache>
<META http-equiv=Pragma content=no-cache>
<title>康佳电器</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/mobile/css/common.css" />
</head>
<body>
<div class="oarcont">
  <div class="rtabcont2">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtab2">
        <tr>
          <th colspan="2" width="90%" align="left" class="bno" id="filetitle">订单基本信息</th>
        </tr>
        <tr>
          <td width="25%" nowrap="nowrap" class="title_item">交易流水号：</td>
          <td>${fn:escapeXml(af.map.trade_index)}</td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap">客户名称：</td>
          <td>${fn:escapeXml(af.map.user_shop_name)}</td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" >下单时间：</td>
          <td><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap">配送方式：</td>
          <td><c:if test="${af.map.send_type eq 1}">自提</c:if>
            <c:if test="${af.map.send_type eq 2}">配送</c:if></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap">货款支付方式：</td>
          <td><c:choose>
            	<c:when test="${af.map.pay_type eq 4}">现汇</c:when>
				<c:when test="${af.map.pay_type eq 5}">帐期</c:when>
				<c:when test="${af.map.pay_type eq 6}">承兑</c:when>
				<c:when test="${af.map.pay_type eq 45}">现汇+帐期</c:when>
				<c:when test="${af.map.pay_type eq 46}">现汇+承兑</c:when>
				<c:when test="${af.map.pay_type eq 56}">帐期+承兑</c:when>
				<c:when test="${af.map.pay_type eq 456}">现汇+帐期+承兑</c:when>
            </c:choose></td>
        </tr>
       <c:if test="${not empty af.map.freight}">
        <tr>
          <td class="title_item" nowrap="nowrap">运费：</td>
          <td> ${fn:escapeXml(af.map.freight)} </td>
        </tr>
         </c:if>
        <tr>
          <td class="title_item" nowrap="nowrap">备注：</td>
          <td>${fn:escapeXml(af.map.remark)}</td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap">数量总计：</td>
          <td><span id="order_num">${af.map.order_num}</span></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap">金额总计：</td>
          <td><span id="money">${af.map.money}</span>元</td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap">折扣金额总计：</td>
          <td><span id="good_discount_price">${af.map.good_discount_price}</span>元</td>
        </tr>
        <c:if test="${not empty af.map.konkaOrderInfoDetailsList}">
          <tr>
            <th colspan="2" width="90%" align="left" class="bno" id="filetitle">订单明细：</th>
          </tr>
          <c:forEach items="${af.map.konkaOrderInfoDetailsList}" var="cur">
            <tr>
              <td  width="25%"   nowrap="nowrap"class="title_item">产品型号：</td>
              <td >${fn:escapeXml(cur.pd_name)}
                <html-el:hidden property="details_id" value="${cur.id}" /></td>
            </tr>
            <tr>
              <td class="title_item" nowrap="nowrap">数量：</td>
              <td >${cur.good_count}</td>
            </tr>
            <tr >
              <td class="title_item" nowrap="nowrap">单位：</td>
              <td >${fn:escapeXml(cur.good_unit)}</td>
            </tr>
            <tr >
              <td class="title_item" nowrap="nowrap">单价：</td>
              <td >${cur.good_price}元</td>
            </tr>
            <tr >
              <td class="title_item" nowrap="nowrap">金额：</td>
              <td >${cur.good_sum_price}元</td>
            </tr>
            <tr >
              <td class="title_item" nowrap="nowrap">折扣比例：</td>
              <td >${cur.good_discount}%</td>
            </tr>
            <tr >
              <td class="title_item" nowrap="nowrap">单台折扣：</td>
              <td >
             <c:if test="${not empty cur.good_count &&(cur.good_count gt 0  || cur.good_count lt 0)}">
              ${cur.good_discount_price / cur.good_count}
                                  </c:if>
                                  元</td>
            </tr>
            <tr >
              <td class="title_item" nowrap="nowrap">折扣金额：</td>
              <td ><span id="good_discount_price_all-${cur.id}">${cur.good_discount_price}</span><html-el:hidden property="good_discount_price" value="${cur.good_discount_price}" styleId="good_discount_price_all_hid-${cur.id}" />元</td>
            </tr>
            <tr  style="border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #d5c7c9;">
              <td class="title_item" nowrap="nowrap">产品备注：</td>
              <td >${fn:escapeXml(cur.pd_remark)}</td>
            </tr>
          </c:forEach>
        </c:if>
        <c:if test="${not empty af.map.konkaOrderInfoAuditList}">
          <tr>
            <th colspan="2" width="90%" align="left" class="bno" id="filetitle">审核流程：</th>
          </tr>
          <c:forEach items="${af.map.konkaOrderInfoAuditList}" var="cur1">
            <tr>
              <td  width="25%"  nowrap="nowrap" class="title_item">序号：</td>
              <td >${cur1.audit_level}</td>
            </tr>
            <tr>
              <td class="title_item" nowrap="nowrap">审核时间：</td>
              <td ><fmt:formatDate value="${cur1.audit_datetime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            </tr>
            <tr >
              <td class="title_item" nowrap="nowrap">审核人：</td>
              <td >${fn:escapeXml(cur1.audit_user)}</td>
            </tr>
            <tr >
              <td class="title_item" nowrap="nowrap">角色：</td>
              <td >${fn:escapeXml(cur1.map.role_name)}</td>
            </tr>
            <tr >
              <td nowrap="nowrap"  class="title_item">审核结果：</td>
              <td ><c:choose>
                  <c:when test="${cur1.audit_result eq 1}">审核通过</c:when>
                  <c:when test="${cur1.audit_result eq -1}">驳回</c:when>
                </c:choose></td>
            </tr>
            <tr class="tabtt1"  style="border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #d5c7c9;">
              <td  width="25%"  nowrap="nowrap" class="title_item">审核意见：</td>
              <td><c:if test="${empty cur1.audit_comment }">无</c:if>
                <c:if test="${not empty cur1.audit_comment }">${fn:escapeXml(cur1.audit_comment)}</c:if></td>
            </tr>
          </c:forEach>
        </c:if>
        <c:if test="${empty af.map.process_id}">
        	<tr>
        		<td class="title_item"><font color="red">*</font>订单流程：</td>
        		<td>
        			<ul>
		          		<c:forEach var="cur" items="${processList}">
			          		<li>
				          		<label for="process_id_${cur.id}" style="cursor:pointer;" id="process_id_label_${cur.id}">
				          			<html-el:radio styleId="process_id_${cur.id}" property="process_id" styleClass="process_id" value="${cur.id}">&nbsp;
						          		<c:if test="${cur.add_dept_id eq 0}">★[统一流程] </c:if>
					          			<c:if test="${cur.add_dept_id ne 0}">[${cur.add_dept_name}]</c:if>
					          			${cur.process_desc}
				          			</html-el:radio>
			          			</label>
		          			</li>
		          		</c:forEach>
	          		</ul>
				</td>
        	</tr>
        	<tr style="display:none;" id="process_id_tr">
				<td class="title_item">订单分类：</td>
				<td>
				    <label for="process_state_1" style="cursor:pointer;"><html-el:radio property="process_state" styleId="process_state_1" styleClass="process_state" value="1" /> 一般订单</label>
					<span style="color:#999;margin-left:3em;">一般订单是指订单的审核流程不需要经过分公司总经理审批的订单</span><br />
					<label for="process_state_2" style="cursor:pointer;"><html-el:radio property="process_state" styleId="process_state_2" styleClass="process_state" value="2" /> 特殊订单</label>
					<span style="color:#999;margin-left:3em;">特殊订单是指订单的审核流程需要经过分公司总经理审批的订单</span></td>
			</tr>
        </c:if>
        <tr>
          <td class="title_item">审核结果：</td>
          <td>
          <c:choose>
          	<c:when test="${af.map.audit_result eq 1}">审核通过</c:when>
          	<c:when test="${af.map.audit_result eq -1}">驳回</c:when>
          	<c:when test="${af.map.audit_result eq 0}">重新制单</c:when>
          </c:choose></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap">审核意见：</td>
          <td>${af.map.audit_comment}</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><label>
		      <input class="but4" type="button" name="Submit4" id="send" value=" 返回 " onclick="history.back();" /> 
            </label></td>
        </tr>
      </table>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
});
//]]></script>
</body>
</html>
