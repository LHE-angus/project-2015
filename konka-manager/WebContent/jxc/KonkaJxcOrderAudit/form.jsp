<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单审核</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
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
						<td class="title_item" nowrap="nowrap">客户名称：</td>
						<td>${fn:escapeXml(af.map.user_shop_name)}</td>
					</tr>
					<tr>
						<td class="title_item">下单时间：</td>
						<td><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd" /></td>
					</tr>
					<tr>
						<td class="title_item">配送方式：</td>
						<td><c:if test="${af.map.send_type eq 1}">自提</c:if> <c:if test="${af.map.send_type eq 2}">配送</c:if></td>
					</tr>
					<tr>
						<td class="title_item">货款支付方式：</td>
						<td><c:if test="${af.map.pay_type eq 4}">现款</c:if> <c:if test="${af.map.pay_type eq 5}">担保</c:if></td>
					</tr>
					<tr>
						<td class="title_item">运费：</td>
						<td>${fn:escapeXml(af.map.freight)}</td>
					</tr>
					<tr>
						<td class="title_item">备注：</td>
						<td>${fn:escapeXml(af.map.remark)}</td>
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
						<td colspan="2" align="center">
							<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
								<tr class="title_top">
									<td width="15%" align="center">产品型号</td>
									<td width="10%" align="center">产品数量</td>
									<c:if test="${not empty is_last_audit}">
										<td width="10%" align="center">本次审核数量</td>
										<td width="8%" align="center">已审核数量</td>
									</c:if>
									<td width="8%" align="center">产品单位</td>
									<td width="10%" align="center">产品单价（元）</td>
									<c:if test="${not empty is_last_audit}">
										<td width="10%" align="center">本次审核单价（元）</td>
									</c:if>
									<td width="15%" align="center">产品总金额（元）</td>
									<td width="10%" align="center">折扣(%)</td>
									<td width="10%" align="center">折扣金额(元)</td>
									<td width="10%" align="center">产品备注</td>
									<td width="5%" align="center" id="tdAdd" title="添加"><img src="${ctx}/styles/jxc/images/+.gif" style="vertical-align: text-bottom;" /></td>
								</tr>
								<tr id="trHidden" style="display: none;">
									<td align="left"><html-el:hidden property="selectValue" styleId="selectValue" /> <html-el:hidden property="pd_ids" styleId="pd_ids" /> <html-el:select
											property="jxc_pd_ids" styleId="jxc_pd_ids">
											<html-el:option value="">请选择...</html-el:option>
											<c:forEach var="cur" items="${pdList}" varStatus="vs">
												<html-el:option value="${cur.pd_id}@#${cur.cls_id}@#${cur.map.cls_name}@#${cur.brand_id}@#${cur.map.brand_name}@#${cur.md_name}">${cur.md_name}</html-el:option>
											</c:forEach>
										</html-el:select></td>
									<td align="center"><html-el:hidden property="pd_id" styleId="pd_id" /> <html-el:hidden property="cls_id" styleId="cls_id" /> <html-el:hidden
											property="brand_id" styleId="brand_id" /> <html-el:hidden property="md_name" styleId="md_name" /> <html-el:text property="good_count" styleId="good_count"
											value="1" size="4" maxlength="4" styleClass="webinput" /></td>
									<c:if test="${not empty is_last_audit}">
										<td align="center"><html-el:text property="this_audit_good_count" styleId="this_audit_good_count" value="0" size="4" maxlength="4" styleClass="webinput" /></td>
										<td align="center"><html-el:text property="already_audit_good_count" styleId="already_audit_good_count" value="0" size="4" maxlength="4"
												styleClass="webinput" readonly="true" /></td>
									</c:if>


									<td align="center"><html-el:text property="good_unit" styleId="good_unit" value="台" maxlength="20" size="5" styleClass="webinput" /></td>
									<td align="center"><html-el:text property="good_price" value="0" styleId="good_price" maxlength="8" size="8" styleClass="webinput" /></td>
									<c:if test="${not empty is_last_audit}">
										<td align="center"><html-el:text property="this_audit_good_price" value="0" styleId="this_audit_good_price" maxlength="8" size="8" styleClass="webinput" /></td>
									</c:if>
									<td align="center"><span id="good_sum_price">0.00</span> <html-el:hidden property="sum_price" styleId="sum_price" /></td>
									<td align="center"><html-el:text property="good_discount" value="0" styleId="good_discount" maxlength="5" size="5" styleClass="webinput" /></td>
									<td align="center"><span id="good_discount_price">0.00</span> <html-el:hidden property="discount_price" styleId="discount_price" /></td>
									<td align="center"><html-el:text property="pd_remark" size="4" styleId="pd_remark" maxlength="100" styleClass="webinput" /></td>
									<td align="center" title="删除"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align: text-bottom;" /></td>
								</tr>
								<tbody id="tbodyContent">
								</tbody>
								<tr class="title_top">
									<td>合计</td>
									<td><span id="dd_count_sum"></span> <html-el:hidden property="order_num" styleId="order_num" /></td>
									<c:if test="${not empty is_last_audit}">
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</c:if>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td><span id="dd_money_sum"></span> <html-el:hidden property="money_sum" styleId="money_sum" /></td>
									<td>&nbsp;</td>
									<td><span id="dd_discount_sum"></span> <html-el:hidden property="discount_price_sum" styleId="discount_price_sum" /></td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
							</table>
						</td>
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
	<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
	<script type="text/javascript">//<![CDATA[
	var f = document.forms[0];
	$(document).ready(function(){
		$("#audit_result").attr("dataType", "Require").attr("msg", "请选择审核结果！");

		// 提交表单(验证价格是否为负和字段是否超过长度)
		$("#btn_submit").click(function(){
			if ($("#pd_ids", "#tbodyContent").length == 0) {
		        alert("请至少添加一个订单产品信息！");
		        return false;
			} 
			var notSelectedPd = false;
			$("#pd_ids", "#tbodyContent").each(function(){
				if ("" == $.trim(this.value)){
					notSelectedPd = true;
				}
			});
			if (notSelectedPd) {
		        alert("您还没有选择产品，请选择！");
		        return false;
			}
			
		    if (judgeSelectedValueIsRepeat("pd_ids", $("#tbodyContent"))) {
		        alert("您选择的产品型号有重复，请重新选择！");
		        return false;
		    }

			if (Validator.Validate(this.form, 3)){
				if(confirm("确认提交？"+"\n"+"提交后此订单将不能再次审核！")){
					$("*","#trHidden").attr("disabled", "true");
					$("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
					$("#btn_back").attr("disabled", "true");
					f.submit();
				}else{
					return false;
				}
			}
		});
		<c:if test="${not empty konkaOrderInfoDetailsList}">
			<c:forEach items="${konkaOrderInfoDetailsList}" var="cur" varStatus="vs">
				$("#trHidden").clone().appendTo($("#tbodyContent")).show();
		    	var lastTR = $("tr:last", "#tbodyContent");
		    	
		    	var pd_ids = $("#pd_ids", lastTR);
		    	var jxc_pd_ids = $("#jxc_pd_ids", lastTR);
		    	var selectValue = $("#selectValue", lastTR);
		    	
		    	var pd_id = $("#pd_id", lastTR);//产品型号id
		      	var cls_id = $("#cls_id", lastTR);//产品大类id
		      	var brand_id = $("#brand_id", lastTR);//产品品牌id
		    	var md_name = $("#md_name", lastTR);//隐藏域产品型号名称
		      	
		    	//var good_pd_type_name = $("#good_pd_type_name", lastTR);//产品类型名称
		    	//var pd_type_name = $("#pd_type_name", lastTR);//隐藏域产品类型
		    	
		     	//var good_brand_name = $("#good_brand_name", lastTR);//产品品牌
		    	//var brand_name = $("#brand_name", lastTR);//隐藏域产品品牌
				var good_count = $("#good_count", lastTR);//数量
				var good_unit = $("#good_unit", lastTR);//单位
				var good_price = $("#good_price", lastTR);//单价
				<c:if test="${not empty is_last_audit}">
					var this_audit_good_count = $("#this_audit_good_count", lastTR);//本次审核数量====
					var this_audit_good_price = $("#this_audit_good_price", lastTR);//本次审核单价===
					var already_audit_good_count = $("#already_audit_good_count", lastTR);//已审核数量===

					already_audit_good_count.val('${cur.map.already_audit_good_count}');
				</c:if>
				
				var good_sum_price = $("#good_sum_price", lastTR);//金额
				var sum_price = $("#sum_price", lastTR);//隐藏域金额
				var good_discount = $("#good_discount", lastTR);//折扣
				var good_discount_price = $("#good_discount_price", lastTR);//折扣金额
				var discount_price = $("#discount_price", lastTR);//隐藏域折扣金额
				
				var pd_remark = $("#pd_remark", lastTR);//产品备注
				
				good_count.attr("dataType","Number").attr("msg","请填写数量,且必须为正整数！");
				good_price.attr("dataType","Currency").attr("msg","请填写单价，且必须为正数！");
				good_discount.attr("dataType","Currency").attr("msg","请填写折扣，且必须为正数！");
				
				pd_ids.val('${cur.pd_id}');//隐藏域选择的产品
				jxc_pd_ids.val('${cur.pd_id}@#${cur.pd_type_id}@#${cur.pd_type_name}@#${cur.brand_id}@#${cur.brand_name}@#${cur.pd_name}');
				jxc_pd_ids.attr("disabled","true"); //禁止修改以前的订单详细，可以删除
		      	
				pd_id.val('${cur.pd_id}');
		      	cls_id.val('${cur.pd_type_id}');
		      	brand_id.val('${cur.brand_id}');
		      	
		      	md_name.val('${cur.pd_name}');
				//good_pd_type_name.text('${cur.pd_type_name}');
				//pd_type_name.val('${cur.pd_type_name}');
				//good_brand_name.text('${cur.brand_name}');
				//brand_name.val('${cur.brand_name}');
		      	
				good_count.val('${cur.good_count}');
				good_unit.val('${cur.good_unit}');
				
				good_price.val('${cur.good_price}');
				
				good_sum_price.text('${cur.good_sum_price}');
				sum_price.val('${cur.good_sum_price}');
				
				good_discount.val('${cur.good_discount}');
				good_discount_price.text('${cur.good_discount_price}');

				pd_remark.val('${cur.pd_remark}');
	
				calcOneCountAndJhPrice(good_count, good_price, good_sum_price, sum_price);
				good_discount_price.text(parseFloat(good_sum_price.text())*parseFloat(good_discount.val())/100);//折扣金额
				discount_price.val(parseFloat(good_sum_price.text())*parseFloat(good_discount.val())/100);//隐藏域折扣金额
				calcPdNumAndPrice("tbodyContent");
				
				<c:if test="${not empty is_last_audit}">
					bindJhCountAndJhPrice(already_audit_good_count, this_audit_good_count,  this_audit_good_price, pd_id, cls_id, brand_id, md_name, good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price, lastTR);
				</c:if>
				<c:if test="${empty is_last_audit}">
					bindJhCountAndJhPrice(pd_id, cls_id, brand_id, md_name, good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price, lastTR);
				</c:if>
				
				$("td:last", lastTR).click(function (){
			    	$(this).parent().remove();
			    	 calcPdNumAndPrice("tbodyContent");
			    }).css("cursor", "pointer");
		    </c:forEach>
		  </c:if>
		  
		/*处理回显订单详细数据 ===start===*/
		
	  /*处理回显订单详细数据 ===end===*/
	  
		//添加
		$("#tdAdd").click(function(){
			if ($("#pd_ids", "#tbodyContent").length > 100) {
				alert("机型个数太多,请拆分订单！");
				return;
			}
			$("#trHidden").clone().appendTo($("#tbodyContent")).show();
	    	var lastTR = $("tr:last", "#tbodyContent");
	    	
	    	var pd_id = $("#pd_id", lastTR);//产品型号id
	      	var cls_id = $("#cls_id", lastTR);//产品大类id
	      	var brand_id = $("#brand_id", lastTR);//产品品牌id
	    	var md_name = $("#md_name", lastTR);//隐藏域产品型号名称
	    	
	    	<c:if test="${not empty is_last_audit}">
		    	var this_audit_good_count = $("#this_audit_good_count", lastTR);//本次审核数量====
				var this_audit_good_price = $("#this_audit_good_price", lastTR);//本次审核单价===
				var already_audit_good_count = $("#already_audit_good_count", lastTR);//已审核数量===
			</c:if>
			
	    	//var good_pd_type_name = $("#good_pd_type_name", lastTR);//产品类型名称
	    	//var pd_type_name = $("#pd_type_name", lastTR);//隐藏域产品类型
	    	
	     	//var good_brand_name = $("#good_brand_name", lastTR);//产品品牌
	    	//var brand_name = $("#brand_name", lastTR);//隐藏域产品品牌
			var good_count = $("#good_count", lastTR);//数量
			var good_unit = $("#good_unit", lastTR);//单位
			var good_price = $("#good_price", lastTR);//单价
			var good_sum_price = $("#good_sum_price", lastTR);//金额
			var sum_price = $("#sum_price", lastTR);//隐藏域金额
			var good_discount = $("#good_discount", lastTR);//折扣
			var good_discount_price = $("#good_discount_price", lastTR);//折扣金额
			var discount_price = $("#discount_price", lastTR);//隐藏域折扣金额
			
			good_count.attr("dataType","Number").attr("msg","请填写数量,且必须为正整数！");
			good_price.attr("dataType","Currency").attr("msg","请填写单价，且必须为正数！");
			good_discount.attr("dataType","Currency").attr("msg","请填写折扣，且必须为正数！");

			
			$("#jxc_pd_ids", lastTR).multiselect({
				selectedList: 1,
				multiple: false,
				minWidth:160,
				click: function(event, ui){
				   if (ui.value == "") {
					    $("#selectValue", lastTR).val("");
						$("#pd_ids", lastTR).val("");//隐藏域选择的产品
						
						pd_id.val("");
				      	cls_id.val("");
				      	brand_id.val("");
				      	md_name.val("");
						//good_pd_type_name.text("");
						//pd_type_name.val("");
						
						//good_brand_name.text("");
						//brand_name.val("");
						
						good_unit.val("");
						good_price.val("");
						good_discount.val("");
						calcOneCountAndJhPrice(good_count, good_price, good_sum_price, sum_price);
						good_discount_price.text(parseFloat(good_sum_price.text())*parseFloat(good_discount.val())/100);//折扣金额
						discount_price.val(parseFloat(good_sum_price.text())*parseFloat(good_discount.val())/100);//隐藏域折扣金额
						calcPdNumAndPrice("tbodyContent");
				   }
			       if(ui.value != ""){
				    s = ui.value.split("@#");
				   // alert("添加select==="+s[0]+"==="+s[1]+"==="+s[2]+"==="+s[3]+"==="+s[4]);
					//$("#thInfo", lastTR).remove();
					$("#selectValue", lastTR).val(ui.value);
					$("#pd_ids", lastTR).val(s[0]);//隐藏域选择的产品
				    if (judgeSelectedValueIsRepeat("pd_ids", $("#tbodyContent"))) {
				       alert("产品型号有重复，请合并或者重新选择！");
				       return false;
				    }
				    pd_id.val(s[0]);
				    cls_id.val(s[1]);
				    brand_id.val(s[3]);
				    
					//good_pd_type_name.text(s[2]);
					//pd_type_name.val(s[2]);
					
					//good_brand_name.text(s[4]);
					//brand_name.val(s[4]);
					md_name.val(s[5]);
					//good_unit.val(s[3]);
					//good_price.val(s[5]);
					calcOneCountAndJhPrice(good_count, good_price, good_sum_price, sum_price);
					good_discount_price.text(parseFloat(good_sum_price.text())*parseFloat(good_discount.val())/100);//折扣金额
					discount_price.val(parseFloat(good_sum_price.text())*parseFloat(good_discount.val())/100);//隐藏域折扣金额
					calcPdNumAndPrice("tbodyContent");
			       }
			       <c:if test="${not empty is_last_audit}">
						bindJhCountAndJhPrice(already_audit_good_count, this_audit_good_count,  this_audit_good_price, pd_id, cls_id, brand_id, md_name, good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price, lastTR);
					</c:if>
					<c:if test="${empty is_last_audit}">
						bindJhCountAndJhPrice(pd_id, cls_id, brand_id, md_name, good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price, lastTR);
					</c:if>
			   	}
			}).multiselectfilter({width:90});
			
	    	$("td:last", lastTR).click(function (){
	        	$(this).parent().remove();
	        	 calcPdNumAndPrice("tbodyContent");
	        }).css("cursor", "pointer");
		}).css("cursor", "pointer");
	});//ready end

	//判断产品型号是否选择重复
	function judgeSelectedValueIsRepeat(objId, parObj){
		var isRepeat = false;
		var ary = new Array();
		$("#" + objId, parObj).each(function() {
			if ("" != $(this).val()) {
				ary.push($(this).val());
			}
		});
		var nary = ary.sort();  
		for(var i = 0; i < nary.length - 1; i++) {
			if (nary[i] == nary[i + 1]) {
				isRepeat = true;
				break;
			}  
	    }  
		return isRepeat;
	}

	<c:if test="${not empty is_last_audit}">
		//输入数量和台数的绑定计算函数
		function bindJhCountAndJhPrice(already_audit_good_count, this_audit_good_count,  this_audit_good_price, pd_id, cls_id, brand_id, md_name, good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price, lastTR) {
	
			this_audit_good_count.keyup(function(){//本次审核数量
				var _reg = /^\d+$/;
				var count =(this.value);
		   		if (!_reg.test(count)) {
		   			this.value = 0; 
		   			count = 0;
		   		}
				var val_good_count =  parseFloat(good_count.val());
				var val_this_audit_good_count =  parseFloat(count);
				var val_already_audit_good_count =  parseFloat(already_audit_good_count.val());
				var val_this_audit_count_max =  parseFloat(val_good_count - val_already_audit_good_count);
				
				//alert("val_good_count "+val_good_count);
				//alert("val_this_audit_good_count "+val_this_audit_good_count);
				//alert("val_this_audit_count_max "+val_this_audit_count_max);
				if(val_this_audit_good_count > val_this_audit_count_max ){
					alert("本次允许的最大审核数为： ["+ val_this_audit_count_max +"]，请重新填写");
					this.value = 0; 
		   			count = 0;
				}
			});
			
			good_count.keyup(function(){//数量
				var _reg = /^\d+$/;
				var count =(this.value);
		   		if (!_reg.test(count)) {
		   			good_count.val(1);
		   			count = 1;
		   		}
		   		var val_good_count =  parseFloat(count);
				var val_this_audit_good_count =  parseFloat(this_audit_good_count.val());
				//alert("val_good_count"+val_good_count);
				//alert("val_this_audit_good_count"+val_this_audit_good_count);
				if(val_good_count < val_this_audit_good_count){
					alert("对不起，您填写的【产品数量】小于【本次审核数量】，请重新填写");
					good_count.val('${cur.good_count}');
				}
				
		   		var price = parseFloat(good_price.val());//单价
		   		if(isNaN(price)) price = 0;
		   		
		   		var discount = parseFloat(good_discount.val());//折扣
		   		if(isNaN(discount)) discount = 0;
		   		
		   		count = parseFloat(count);//数量
		   		if(isNaN(count)) count = 1;
				
		   		good_sum_price.text((count * price).toFixed(2));//金额
		   		sum_price.val((count * price).toFixed(2));//隐藏域金额
		   		good_discount_price.text((count * price * discount/100).toFixed(2));//折扣金额
		   		discount_price.val((count * price * discount/100).toFixed(2));//隐藏域折扣金额
				calcPdNumAndPrice("tbodyContent");
			});
			
			this_audit_good_price.keyup(function(){//本次审核单价
				var price = (this.value);
				var _reg = /^[\+]?\d*?\.?\d*?$/;
				if (!_reg.test(price)) {
					this_audit_good_price.val(0);
					price = 0;
				}
			});
			
	
			good_price.keyup(function(){//单价
				var price = (this.value);
				var _reg = /^[\+]?\d*?\.?\d*?$/;
				if (!_reg.test(price)) {
					good_price.val(0);
					price = 0;
				}
				price = parseFloat(this.value);
				if(isNaN(price)) price = 0;
				if(price > parseFloat(99999.99)){
					alert("价格太大了，上限为10万！");
					good_price.val(99999.99);
					price = 99999.99;
				}
		   		var count = parseFloat(good_count.val());//数量
		   		if(isNaN(count)) count = 1;
	
		   		var discount = parseFloat(good_discount.val());//折扣
		   		if(isNaN(discount)) discount = 0;
	
		   		good_sum_price.text((count * price).toFixed(2));//金额
		   		sum_price.val((count * price).toFixed(2));//隐藏域金额
		   		good_discount_price.text((count * price * discount/100).toFixed(2));//折扣金额
		   		discount_price.val((count * price * discount/100).toFixed(2));//隐藏域折扣金额
				calcPdNumAndPrice("tbodyContent");
			});
			
			good_discount.keyup(function(){//折扣
				var discount = (this.value);
				var _reg = /^[\+]?\d*?\.?\d*?$/;
				if (!_reg.test(discount)) {
					good_discount.val(0);
					discount = 0;
				}
				discount = parseFloat(this.value);
				if(isNaN(discount)) discount = 0;
				if(discount > 100) {
					discount = 0;
				}
		   		var sum_price = parseFloat(good_sum_price.text());//金额
		   		if(isNaN(sum_price)) sum_price = 0;
	
		   		good_discount_price.text((discount * sum_price/100).toFixed(2));//折扣金额
		   		discount_price.val((discount * sum_price/100).toFixed(2));//隐藏域折扣金额
				calcPdNumAndPrice("tbodyContent");
			});
			
			
			calcPdNumAndPrice("tbodyContent");
		}
	</c:if>
	<c:if test="${empty is_last_audit}">
		//输入数量和台数的绑定计算函数
		function bindJhCountAndJhPrice( pd_id, cls_id, brand_id, md_name, good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price, lastTR) {
			good_count.keyup(function(){//数量
				var _reg = /^\d+$/;
				var count =(this.value);
		   		if (!_reg.test(count)) {
		   			good_count.val(1);
		   			count = 1;
		   		}
		   		var price = parseFloat(good_price.val());//单价
		   		if(isNaN(price)) price = 0;
		   		
		   		var discount = parseFloat(good_discount.val());//折扣
		   		if(isNaN(discount)) discount = 0;
		   		
		   		count = parseFloat(count);//数量
		   		if(isNaN(count)) count = 1;
				
		   		good_sum_price.text((count * price).toFixed(2));//金额
		   		sum_price.val((count * price).toFixed(2));//隐藏域金额
		   		good_discount_price.text((count * price * discount/100).toFixed(2));//折扣金额
		   		discount_price.val((count * price * discount/100).toFixed(2));//隐藏域折扣金额
				calcPdNumAndPrice("tbodyContent");
			});
			
	
			good_price.keyup(function(){//单价
				var price = (this.value);
				var _reg = /^[\+]?\d*?\.?\d*?$/;
				if (!_reg.test(price)) {
					good_price.val(0);
					price = 0;
				}
				price = parseFloat(this.value);
				if(isNaN(price)) price = 0;
				if(price > parseFloat(99999.99)){
					alert("价格太大了，上限为10万！");
					good_price.val(99999.99);
					price = 99999.99;
				}
		   		var count = parseFloat(good_count.val());//数量
		   		if(isNaN(count)) count = 1;
	
		   		var discount = parseFloat(good_discount.val());//折扣
		   		if(isNaN(discount)) discount = 0;
	
		   		good_sum_price.text((count * price).toFixed(2));//金额
		   		sum_price.val((count * price).toFixed(2));//隐藏域金额
		   		good_discount_price.text((count * price * discount/100).toFixed(2));//折扣金额
		   		discount_price.val((count * price * discount/100).toFixed(2));//隐藏域折扣金额
		   		<c:if test="${not empty show_tips}">
		   	//取最低限价-大类、品牌、型号、部门
				$.ajax({
						type: "POST",
						url: "CsAjax.do",
						data: "method=getLowestPriceByTypeBrandPdAndDept"+"&pd_type_id="+cls_id.val()+"&brand_id="+brand_id.val()+"&pd_id="+pd_id.val()+"",
						dataType: "json",
						error: function(request, settings) {alert("数据加载请求失败！"); },
						success: function(Dates) {
							if (null!= Dates && Dates.length >0) {
								for(var i = 0; i < Dates.length ; i++) {
									var lowest_price = Dates[i].lowest_price;
									if( price < lowest_price){
										$("#lowest_price_tr").show();
										var tip = md_name.val()+" 您填写的产品单价低于最低限价:[ "+lowest_price+" ]元，此订单将通过特殊流程审核！";
	
										$("#lowest_price_tip").empty().append('<span id="lowest_price_tip" style="color:#f00;">'+tip+ '</span>');
									}else if(price >= lowest_price){
										$("#lowest_price_tr").hide();
										$("#lowest_price_tip").empty();
									}
								}
							} 
						}
				});
			</c:if>
				calcPdNumAndPrice("tbodyContent");
			});
			good_discount.keyup(function(){//折扣
				var discount = (this.value);
				var _reg = /^[\+]?\d*?\.?\d*?$/;
				if (!_reg.test(discount)) {
					good_discount.val(0);
					discount = 0;
				}
				discount = parseFloat(this.value);
				if(isNaN(discount)) discount = 0;
				if(discount > 100) {
					discount = 0;
				}
		   		var sum_price = parseFloat(good_sum_price.text());//金额
		   		if(isNaN(sum_price)) sum_price = 0;
	
		   		good_discount_price.text((discount * sum_price/100).toFixed(2));//折扣金额
		   		discount_price.val((discount * sum_price/100).toFixed(2));//隐藏域折扣金额
				calcPdNumAndPrice("tbodyContent");
			});
			
			
			calcPdNumAndPrice("tbodyContent");
		}
	</c:if>

	//计算 单个的金额
	function calcOneCountAndJhPrice(good_count, good_price, good_sum_price, sum_price) {
		var good_count = parseFloat(good_count.val());//数量
	   	if(isNaN(good_count)) good_count = 1;
	   	var good_price =  parseFloat(good_price.val());//单价
	   	if(isNaN(good_price)) good_price = 0;
	   	good_sum_price.text((good_count * good_price).toFixed(2));//金额
	   	sum_price.val((good_count * good_price).toFixed(2));//隐藏域金额
	}

	//计算 合计的 数量、 金额和折扣金额
	function calcPdNumAndPrice(tbody_id) {
		var dd_count_sum = 0;
		var dd_money_sum = 0;
		var dd_discount_sum = 0;
		$("#good_count", $("#" + tbody_id)).each(function(){
			var this_value = parseFloat($(this).val());
			if(isNaN(this_value)) this_value = 0;
			dd_count_sum += this_value;
		});

		$("#good_sum_price", $("#" + tbody_id)).each(function(){
			var this_value = parseFloat($(this).text());
			if(isNaN(this_value)) this_value = 0;
			dd_money_sum += this_value;
		});
		
		$("#good_discount_price", $("#" + tbody_id)).each(function(){
			var this_value = parseFloat($(this).text());
			if(isNaN(this_value)) this_value = 0;
			dd_discount_sum += this_value;
		});
		
		$("#dd_count_sum").text(dd_count_sum);
		$("#dd_money_sum").text("￥" + (dd_money_sum.toFixed(2)));
		$("#dd_discount_sum").text("￥" + (dd_discount_sum.toFixed(2)));
		$("#pay_money").val(parseFloat(dd_money_sum.toFixed(2)-dd_discount_sum.toFixed(2)).toFixed(2));
		//$("#money").val(dd_money_sum.toFixed(2)-dd_discount_sum.toFixed(2));
		$("#order_num").val(dd_count_sum);//隐藏域订单总数
		$("#money_sum").val(dd_money_sum.toFixed(2));//隐藏域订单金额
		$("#discount_price_sum").val(dd_discount_sum.toFixed(2));//隐藏域订单折扣金额
	}
//]]></script>
	<jsp:include page="../public_page.jsp" flush="true" />
	<jsp:include page="/__analytics.jsp" />
</body>
</html>