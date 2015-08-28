<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单详细</title> 
</head>
<body>
<table width="100%" border="1" cellpadding="0" cellspacing="1">
<tr><th colspan="7">订单信息</th></tr>
<tr>
<td class="title_item" width="15%">订单流水号：</td><td colspan="4"  ><span><font color="red">${fn:escapeXml(af.map.trade_index)}</font>&nbsp;</span></td>
<td class="title_item" width="15%">下单人姓名：</td><td colspan="1">${af.map.order_user_name}</td>
</tr>
<tr>
<td class="title_item" width="15%">送货日期：</td>
<td colspan="4"><c:if test="${af.map.deliver_time eq 0}">只工作日送货（双休日、假日不用送）</c:if><c:if test="${af.map.deliver_time eq 1}">工作日、双休日与假日均可送货</c:if><c:if test="${af.map.deliver_time eq 2}">只双休日、假日送货（工作日不送货）</c:if></td>
<td class="title_item" width="15%">订单顺丰状态：<c:if test="${af.map.order_state eq 1}"></c:if></td>
<td colspan="1"><c:if test="${af.map.order_state eq 0}">该订单没有通过顺丰物流发货</c:if></td>
</tr>
<tr>
<td class="title_item" width="15%">发货前电话确认：</td><td colspan="4"><c:if test="${af.map.deliver_is_call eq 0}">否</c:if><c:if test="${af.map.deliver_is_call eq 1}">是</c:if></td>
<td class="title_item" width="15%">发票是否索要：</td><td colspan="1"><c:if test="${af.map.bill_is_add eq 0}">否</c:if><c:if test="${af.map.bill_is_add eq 1}">是</c:if></td>
</tr>
<tr>
<td class="title_item" width="15%">发票类型：</td><td colspan="4"><c:if test="${af.map.bill_type eq 0}">普通发票</c:if><c:if test="${af.map.bill_type eq 1}">增值税发票</c:if></td>
<td class="title_item" width="15%">发票抬头：</td><td colspan="1"><c:if test="${af.map.bill_head eq 0}">个人</c:if><c:if test="${af.map.bill_head eq 1}">单位</c:if></td>
</tr>
<tr>
<td class="title_item" width="15%">发票内容：</td>
<td colspan="4"><c:if test="${af.map.bill_content eq 0}">明细</c:if><c:if test="${af.map.bill_content eq 1}">办公用品</c:if><c:if test="${af.map.bill_content eq 2}">电脑配件</c:if><c:if test="${af.map.bill_content eq 3}">耗材</c:if></td>
<td class="title_item" width="15%">发票单位：</td><td colspan="1">${af.map.bill_company}</td>
</tr>
<tr>
<td class="title_item" width="15%">货款支付方式：</td>
<td colspan="4"><c:if test="${af.map.pay_way eq 0}">货到付款</c:if><c:if test="${af.map.pay_way eq 1}">银行汇款</c:if><c:if test="${af.map.pay_way eq 2}">支付宝</c:if><c:if test="${af.map.pay_way eq 3}">银联</c:if><c:if test="${af.map.pay_way eq 4}">财付通</c:if><c:if test="${af.map.pay_way eq 5}">民生银行</c:if><c:if test="${af.map.pay_way eq 8}">嘿客代收货款</c:if><c:if test="${af.map.pay_way eq 9}">线下处理</c:if><c:if test="${not empty af.map.trade_no}">(支付单号<c:out value="${af.map.trade_no}" />)</c:if></td>
<td class="title_item" width="15%">运单号：</td><td colspan="1"><span><font color="red">${af.map.logistic_sn}</font>&nbsp;</span></td>   
</tr>
<c:if test="${not empty af.map.ecVouchersList }">
<tr>
    <td class="title_item" width="15%" >使用购物券：</td>
    <td colspan="6"><c:forEach items="${af.map.ecVouchersList }" var="cur">
            ${fn:escapeXml(cur.title)} &nbsp;￥<font color="red"> ${fn:escapeXml(cur.price)}</font>元<br/>
     </c:forEach>
	</td>
</tr>
</c:if>
<tr><td class="title_item" width="15%" >备注：</td><td colspan="6"><c:out value="${af.map.remark}" />  </td></tr>
<tr>
<td colspan="7">
	<table width="100%" border="1" cellpadding="0" cellspacing="1">
        <tr><th colspan="7">订单产品信息</th></tr>
        <tr class="title_top"><td width="30%" nowrap="nowrap" colspan="2">(型号)商品名称</td><td width="5%" nowrap="nowrap">数量</td><td width="8%" nowrap="nowrap">单价</td><td width="8%" nowrap="nowrap">金额</td><td width="8%" nowrap="nowrap">增值服务</td><td width="20%" nowrap="nowrap">备注</td></tr>
        <c:forEach items="${pshowOrdeDetails}" var="cur">
                <tr>
                  <td align="left" colspan="2">(${cur.map.pd_sn })${cur.pd_name }</td>
                  <td align="center">${fn:escapeXml(cur.num)}</td>
                  <td align="center"><fmt:formatNumber value="${fn:escapeXml(cur.price)}" pattern="0.00" /></td>
                  <td align="center"><fmt:formatNumber value="${fn:escapeXml(cur.total_price)}" pattern="0.00" /></td>
                  <td align="center">
						 <c:forEach items="${bddetailsList}" var="cu"><c:if test="${cu.details_id eq cur.bill_item_id}">
						 		${cu.goods_name} ￥${cu.price}<br />
						 	</c:if></c:forEach>
				  </td>
                  <td align="left"><c:if test="${cur.state eq 1 }">退货&nbsp;</c:if><c:if test="${cur.state eq 2 }">换货&nbsp;</c:if>${fn:escapeXml(cur.remark)}</td>
                </tr>
              </c:forEach>
            </table></td>
</tr>
<tr>
<td class="title_item" width="15%">收货人固定电话：</td><td>${fn:escapeXml(af.map.buyer_tel)}</td>
<td class="title_item" width="15%">收货人手机：</td><td colspan="4">${fn:escapeXml(af.map.buyer_mp)}</td>
</tr>
<tr>
<td class="title_item" width="15%">购买人姓名：</td><td>${fn:escapeXml(af.map.buyer_name)}</td>
<td class="title_item" width="15%">收货人所属地区：</td><td colspan="4">${p_index_name}</td>
</tr> 
<tr>
<td class="title_item" width="15%">订单产品数量：</td><td >${t_num }</td>
<td class="title_item" width="15%">订单总金额：</td><td colspan="4"><fmt:formatNumber value="${af.map.total_price}" pattern="0.00" /> (元)</td>
</tr>
<tr>
<td class="title_item" width="15%">抵扣金额：</td><td>${fn:escapeXml(af.map.dedu_price)} (元)</td>
<td class="title_item" width="15%">应付金额：</td><td colspan="4"><fmt:formatNumber value="${af.map.pay_price}" pattern="0.00" /> (元)</td>
</tr>
<tr>
<td class="title_item" width="15%">订单状态：</td><td ><c:if test="${af.map.state eq -1}">已取消</c:if><c:if test="${af.map.state eq -30 }">已退货</c:if><c:if test="${af.map.state eq -20 }">审核未通过</c:if><c:if test="${af.map.state eq -10 }">已关闭</c:if><c:if test="${af.map.state eq 0 }">已预订</c:if><c:if test="${af.map.state eq 5 }">待确认</c:if> <c:if test="${af.map.state eq 10 }">已确认</c:if><c:if test="${af.map.state eq 20 }">审核通过</c:if><c:if test="${af.map.state eq 30 }">下发处理</c:if><c:if test="${af.map.state eq 40 }">商家发货</c:if><c:if test="${af.map.state eq 50 }">客户已换货</c:if><c:if test="${af.map.state eq 60 }">确认收货</c:if><c:if test="${af.map.pay_way eq 9}">（线下处理）</c:if></td>
<td class="title_item" width="15%">收货地址：</td><td colspan="4">${fn:escapeXml(af.map.buyer_addr)}</td>
</tr>
<tr><th colspan="7">订单审核信息</th></tr><c:if test="${empty af.map.par_order_id}"> 
<tr><td align="left" colspan="7">&nbsp;客户下单时间 ：<fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td> </tr></c:if>
<tr>
<td colspan="7">
				<table width="100%" border="1" cellpadding="0" cellspacing="1" > 
                <tr>
                  <td class="title_item" width="5%" style="text-align:center;" nowrap="nowrap">序号</td>
                  <td class="title_item" width="18%" style="text-align:center;" nowrap="nowrap">登录名</td>
                  <td class="title_item" width="10%" style="text-align:center;" nowrap="nowrap">真实姓名</td>
                  <td class="title_item" width="18%" style="text-align:center;" nowrap="nowrap">操作时间</td>
                  <td class="title_item" width="18%" style="text-align:center;" nowrap="nowrap">订单应付金额</td>
                  <td class="title_item" width="18%" style="text-align:center;" nowrap="nowrap">审核结果</td>
                  <td class="title_item" style="text-align:center;" nowrap="nowrap">意见</td>
                </tr>
                <c:forEach items="${PshowOrdeAudits}" var="cur" varStatus="st">
                  <tr>
                    <td align="center" >${st.count}</td>
                    <td align="center">${cur.opr_user_real_name } </td>
                     <td align="center">${cur.map.real_name} </td>
                    <td align="center"><fmt:formatDate value="${cur.oper_date }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td align="center">${cur.total_price}</td>
                    <td align="center"><c:if test="${cur.state eq -1}">已取消</c:if>
			            <c:if test="${cur.state eq -30 }">已退货</c:if>
				         <c:if test="${cur.state eq -20 }">审核未通过</c:if>
				         <c:if test="${cur.state eq -10 }">已关闭</c:if>
				         <c:if test="${cur.state eq 0 }">已预订</c:if>
				         <c:if test="${cur.state eq 5 }">待确认</c:if>
				         <c:if test="${cur.state eq 10 }">已确认</c:if>
				         <c:if test="${cur.state eq 20 }">审核通过</c:if>
				         <c:if test="${cur.state eq 30 }">下发处理</c:if>
				         <c:if test="${cur.state eq 40 }">商家发货</c:if>
				         <c:if test="${cur.state eq 50 }">客户已换货</c:if>
				         <c:if test="${cur.state eq 60 }">确认收货</c:if>
				         <c:if test="${cur.state eq 70 }">确认回款</c:if>
				         <c:if test="${cur.state eq 80 }">退货</c:if>
					     <c:if test="${cur.state eq 90 }">换货</c:if>
				         <c:if test="${af.map.pay_way eq 9}">（线下处理）</c:if>
				         </td>
                    <td align="left">${cur.remark}</td>
                  </tr>
                </c:forEach>
              </table></td>
</tr> 
</table> 
</body>
</html>
