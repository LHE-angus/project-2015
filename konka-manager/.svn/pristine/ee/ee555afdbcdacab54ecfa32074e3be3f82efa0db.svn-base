<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单审核</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div style="width: 100%">
		<div class="oartop">
			<img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align: text-top;" /> 当前位置：${naviString}
		</div>
		<div class="rtabcont1">
			<html-el:form action="/KonkaJxcOrderAudit.do">
				<html-el:hidden property="queryString" />
				<html-el:hidden property="method" value="save" />
				<html-el:hidden property="audit_level" value="${af.map.audit_level}" />
				<html-el:hidden property="process_state" value="${af.map.process_state}" />
				<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
				<html-el:hidden property="id" styleId="id" value="${af.map.id}" />
				<html-el:hidden property="trade_index" styleId="trade_index" value="${af.map.trade_index}" />
				<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass" id="tb">
					<tr>
						<th colspan="2">订单基本信息</th>
					</tr>
					<tr>
						<td width="15%" class="title_item">交易流水号：</td>
						<td>${fn:escapeXml(af.map.trade_index)}</td>
					</tr>
					<tr>
						<td class="title_item" nowrap="nowrap">买卖提网点名称：</td>
						<td>${fn:escapeXml(af.map.map.shop_name)}</td>
					</tr>
					<tr>
						<td class="title_item">下单时间：</td>
						<td><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd" /></td>
					</tr>
					<tr>
						<td class="title_item">订单产品数量：</td>
						<td>${fn:escapeXml(af.map.order_num)}</td>
					</tr>
					<tr>
						<td class="title_item">订单金额（元）：</td>
						<td><fmt:formatNumber value="${af.map.money}" pattern="0.00" /></td>
					</tr>
					<tr>
						<td class="title_item">订单折扣金额（元）：</td>
						<td><fmt:formatNumber value="${af.map.good_discount_price}" pattern="0.00" /></td>
					</tr>
					<tr>
						<td class="title_item">说明：</td>
						<td>${fn:escapeXml(af.map.remark)}</td>
					</tr>
					<c:if test="${af.map.audit_level gt 1}">
						<c:if test="${not empty af.map.konkaOrderInfoAuditList}">
							<c:set var="is_audit" value="" />
							<c:forEach items="${af.map.konkaOrderInfoAuditList}" var="cur1">

								<td class="title_item">${fn:escapeXml(cur1.map.role_name)}审核信息：</td>
								<td>审核结果：【 <c:if test="${cur1.audit_result eq 1}">通过</c:if> <c:if test="${cur1.audit_result eq 0}">未审核</c:if> <c:if test="${cur1.audit_result eq -1}">未通过</c:if>
									】&nbsp;&nbsp; 审核意见：【 <c:if test="${empty cur1.audit_comment }">无</c:if> <c:if test="${not empty cur1.audit_comment }">${fn:escapeXml(cur1.audit_comment)}</c:if> 】
									&nbsp;&nbsp; 审核人：【${fn:escapeXml(cur1.audit_user)}】&nbsp;&nbsp; 审核时间：【 <fmt:formatDate value="${cur1.audit_datetime}" pattern="yyyy-MM-dd HH:mm:ss" /> 】
								</td>
								<tr>
								</tr>
							</c:forEach>
						</c:if>
					</c:if>
					<tr>
						<td class="title_item">审核结果：</td>
						<td><html-el:select property="audit_result" styleId="audit_result" style="width:120px;">
								<html-el:option value="1">审核通过</html-el:option>
								<html-el:option value="-1">审核未通过</html-el:option>
							</html-el:select></td>
					</tr>
					<tr>
						<td class="title_item">审核意见：</td>
						<td><html-el:text property="audit_comment" styleClass="webinput" maxlength="256" style="width:70%"></html-el:text></td>
					</tr>
					<tr>
						<th colspan="2">订单明细</th>
					</tr>
					<tr>
						<td colspan="2" align="center"><table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
								<tr class="title_top">
									<td width="5%" align="center">序号</td>
									<td width="8%" align="center">产品大类</td>
									<td width="8%" align="center">产品品牌</td>
									<td width="15%" align="center">产品型号</td>
									<td width="10%" align="center">产品数量</td>
									<td width="8%" align="center">产品单位</td>
									<td width="10%" align="center">产品单价（元）</td>
									<td width="15%" align="center">产品总金额（元）</td>
									<td width="10%" align="center">折扣(%)</td>
									<td width="10%" align="center">折扣金额(元)</td>
								</tr>
								<tbody id="tbody_order">
									<c:forEach items="${konkaOrderInfoDetailsList}" var="cur" varStatus="vs">
										<tr>
											<td align="center">${vs.count}<html-el:hidden property="order_details_ids" value="${cur.id}" /> <html-el:hidden property="good_discounts"
													value="${cur.good_discount}" /></td>
											<td align="center">${fn:escapeXml(cur.pd_type_name)} <html-el:hidden property="pd_type_id" styleId="pd_type_id" value="${cur.pd_type_id}" /></td>
											<td align="center">${fn:escapeXml(cur.brand_name)} <html-el:hidden property="brand_id" styleId="brand_id" value="${cur.brand_id}" /></td>
											<td align="center">${fn:escapeXml(cur.pd_name)} <html-el:hidden property="pd_id" styleId="pd_id" value="${cur.pd_id}" /> <html-el:hidden property="pd_name"
													styleId="pd_name" value="${cur.pd_name}" /></td>
											<td align="center"><c:if test="${af.map.is_update_authority eq 0}"> ${fn:escapeXml(cur.good_count)}
                        <html-el:hidden property="good_counts" styleId="good_counts" value="${cur.good_count}" />
												</c:if> <c:if test="${af.map.is_update_authority eq 1}">
													<html-el:text property="good_counts" size="4" styleId="good_counts" styleClass="webinput" maxlength="4" value="${cur.good_count}"></html-el:text>
												</c:if></td>
											<td align="center">${fn:escapeXml(cur.good_unit)}</td>
											<td align="center"><c:if test="${af.map.is_update_authority eq 0}"> ${fn:escapeXml(cur.good_price)}
                        <html-el:hidden property="good_prices" styleId="good_prices" value="${cur.good_price}" />
												</c:if> <c:if test="${af.map.is_update_authority eq 1}">
													<html-el:text property="good_prices" size="8" styleId="good_prices" styleClass="webinput" maxlength="8" value="${cur.good_price}"></html-el:text>
												</c:if></td>
											<td align="center"><span id="good_sum_prices">${fn:escapeXml(cur.good_sum_price)}</span></td>
											<td align="center">${fn:escapeXml(cur.good_discount)} <html-el:hidden property="good_discount" styleId="good_discount" value="${cur.good_discount}" /></td>
											<td align="center"><span id="good_discount_price">${fn:escapeXml(cur.good_discount_price)}</span></td>
										</tr>
									</c:forEach>
								</tbody>
							</table></td>
					</tr>
					<tr id="lowest_price_tr" style="display: none;">
						<td align="left" colspan="2"><span id="lowest_price_tip"></span></td>
					</tr>
					<tr>
						<td align="center" colspan="2" style="text-align: center"><html-el:button property="save" styleClass="bgButtonSave" styleId="btn_submit" value="提交" /> &nbsp; <html-el:button
								styleClass="bgButtonBack" property="" styleId="btn_back" value="返回 " onclick="history.back();" /></td>
					</tr>
				</table>
			</html-el:form>
		</div>
	</div>
	<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
	<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
		$("#audit_result").attr("dataType", "Require").attr("msg", "请选择审核结果！");

	 $("tr", "#tbody_order").each(function(){
			var _thisTr = $(this);
			var JQ_good_count = $("#good_counts", _thisTr);
			var JQ_good_price = $("#good_prices", _thisTr);
			var JQ_good_sum_price = $("#good_sum_prices", _thisTr);
			var JQ_good_discount = $("#good_discount", _thisTr);
			var JQ_good_discount_price = $("#good_discount_price", _thisTr);
			
			var JQ_pd_type_id = $("#pd_type_id", _thisTr);
			var JQ_brand_id = $("#brand_id", _thisTr);
			var JQ_pd_id = $("#pd_id", _thisTr);
			var JQ_pd_name = $("#pd_name", _thisTr);

			
			
			JQ_good_count.keyup(function(){
				/**var _reg_good_count = /^\d+$/;
				var good_count =(JQ_good_count.val());
		   		if (!_reg_good_count.test(good_count)) {
		   			JQ_good_count.val(0);
		   			good_count = 0;
		   		}*/
		   		var good_discount =  parseFloat(JQ_good_discount.val());
				var good_count =  parseFloat(JQ_good_count.val());
				if(isNaN(good_count)){
					 good_count = 0;
				}
				JQ_good_count.val(good_count);
				
				var good_price = (JQ_good_price.val());
				var _reg_good_price = /^[\+]?\d*?\.?\d*?$/;
				if (!_reg_good_price.test(good_price)) {
					JQ_good_price.val(0);
					good_price = 0;
				}
				good_price =  parseFloat(JQ_good_price.val());
				if(isNaN(good_price)) {
					good_price = 0;
				}
				JQ_good_discount_price.empty().text(((good_count * good_price * good_discount)/100).toFixed(2));//产品折扣金额
				JQ_good_sum_price.empty().text((good_count * good_price).toFixed(2));//产品总金额
			});
			
			JQ_good_price.keyup(function(){
				var good_discount =  parseFloat(JQ_good_discount.val());
				var good_count =  parseFloat(JQ_good_count.val());
				if(isNaN(good_count)){
					 good_count = 0;
				}
				JQ_good_count.val(good_count);
				
				var good_price = (JQ_good_price.val());
				var _reg_good_price = /^[\+]?\d*?\.?\d*?$/;
				if (!_reg_good_price.test(good_price)) {
					JQ_good_price.val(0);
					good_price = 0;
				}
				good_price =  parseFloat(JQ_good_price.val());
				if(isNaN(good_price)) {
					good_price = 0;
				}
				if(JQ_good_price.val() > 99999.99){
					alert("对不起，您输入的单价太大了，上限为10W 元！");
					JQ_good_price.val(99999.99);
				}
				JQ_good_discount_price.empty().text(((good_count * good_price * good_discount)/100).toFixed(2));//产品折扣金额
				JQ_good_sum_price.empty().text((good_count * good_price).toFixed(2));//产品总金额
				//取最低限价-大类、品牌、型号、部门
				$.ajax({
						type: "POST",
						url: "CsAjax.do",
						data: "method=getLowestPriceByTypeBrandPdAndDept"+"&pd_type_id="+JQ_pd_type_id.val()+"&brand_id="+JQ_brand_id.val()+"&pd_id="+JQ_pd_id.val()+"",
						dataType: "json",
						error: function(request, settings) {alert("数据加载请求失败！"); },
						success: function(Dates) {
							if (null!= Dates && Dates.length >0) {
								for(var i = 0; i < Dates.length ; i++) {
									var lowest_price = Dates[i].lowest_price;
									if(good_price < lowest_price){
										$("#lowest_price_tr").show();
										var tip = JQ_pd_name.val()+" 您填写的产品单价低于最低限价:[ "+lowest_price+" ]元，此订单将通过特殊流程审核！";

										$("#lowest_price_tip").empty().append('<span id="lowest_price_tip" style="color:#f00;">'+tip+ '</span>');
									}else if(JQ_good_price.val() >= lowest_price){
										$("#lowest_price_tr").hide();
										$("#lowest_price_tip").empty();
									}
								}
							} 
						}
				});
			});

			JQ_good_count.attr("dataType", "Number").attr("msg", "请填写产品数量,且必须是正整数！");
			JQ_good_price.attr("dataType", "Currency").attr("msg", "请填写产品单价,且必须是正实数！");
	});

	$("#btn_submit").click(function(){
		/*验证发货数量和发货单价为0  start*/
		var isZero = false;

		 $("tr", "#tbody_order").each(function(){
			var _thisTr = $(this);
			var JQ_good_count = $("#good_counts", _thisTr);
			
			var good_count = parseFloat(JQ_good_count.val());
			if(good_count == 0){
				alert("产品数量不能为 0 ！");
				isZero = true;
		        return false;
			}
			var JQ_good_price = $("#good_prices", _thisTr);
			var good_price = parseFloat(JQ_good_price.val());
			if(good_price == 0){
				alert("产品单价不能为 0 ！");
				isZero = true;
		        return false;
			}
		});
		if(isZero){//发货数量为0,返回    不提交
			return false;
		}
		/*验证发货数量和发货单价为0  end*/
		
		
		if (Validator.Validate(this.form, 3)) {
			if(confirm("确认提交？"+"\n"+"提交后此订单将不能再次审核！")){
				$("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
				$("#btn_back").attr("disabled", "true");
				this.form.submit();
			}else{
				return false;
			}
			
		}
	});
});
//]]></script>
	<jsp:include page="../public_page.jsp" flush="true" />
	<jsp:include page="/__analytics.jsp" />
</body>
</html>