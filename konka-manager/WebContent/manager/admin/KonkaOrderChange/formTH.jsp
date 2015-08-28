<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/css/tab.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_div">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaOrderChange" method="post">
      <html-el:hidden property="order_id" styleId="order_id" value="${af.map.id}" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="flag" styleId="flag" value="${flag}" />
      <html-el:hidden property="audit_proc_cond_flag" styleId="audit_proc_cond_flag" value="${has_proc_cond}" />
      <html-el:hidden property="customer_type" styleId="customer_type" value="${customer_type}" />
      <html-el:hidden property="flag_Zb_role" styleId="flag_Zb_role" value="${flag_Zb_role}" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
        <tr>
          <td colspan="4" align="left" style="font-weight:bold;color:#74685F;">订单基本信息</td>
        </tr>
        <tr>
          <td width="15%" class="title_item">交易流水号：</td>
          <td colspan="3">${fn:escapeXml(af.map.trade_index)}</td>
        </tr>
        <tr>

          <td class="title_item" width="15%">客户名称：</td>
							<td width="35%">${fn:escapeXml(af.map.user_shop_name)}</td>
							<td class="title_item" width="15%">售达方编码：</td>
							<td width="15%">${r3_code}</td>
        </tr>
        <tr>
          <td class="title_item">提交日期：</td>
          <td ><fmt:formatDate value="${af.map.order_date}" pattern="yyyy-MM-dd"/></td>
           <td class="title_item">创建日期：</td>
          <td><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
         <td class="title_item" width="15%" >制单人：</td>
		<td width="35%" >${af.map.add_user_name}</td>
		<td class="title_item" width="15%">业务员：</td>
		<td width="35%">${ywy_user_name}</td>
        </tr>
        <c:if test="${not empty af.map.freight}">
        <tr>
          <td class="title_item">运费：</td>
          <td colspan="3"> ${fn:escapeXml(af.map.freight)} </td>
        </tr>
        </c:if>
        <tr>
          <td class="title_item">备注：</td>
          <td colspan="3">${fn:escapeXml(af.map.remark)}</td>
        </tr>        
        <c:if test="${not empty af.map.konkaOrderInfoDetailsList}">
        <tr class="one">
          <td colspan="4"><strong>订单明细：</strong><br /><table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
					<tr class="tabtt1">
						<td width="12%" nowrap="nowrap" align="center">产品型号</td>
						<td width="8%" nowrap="nowrap" align="center">数量</td>
		                <td width="5%" nowrap="nowrap" align="center">单位</td>
		                <td width="8%" nowrap="nowrap" align="center">单价（元）</td>
		                <td width="8%" nowrap="nowrap" align="center">金额（元）</td>
		                <td width="10%" nowrap="nowrap" align="center">折扣金额-RB00</td>
		                <td width="10%" nowrap="nowrap" align="center">折扣(%)-K007</td>
		                <td width="8%" nowrap="nowrap" align="center">折后金额</td>
		                <td width="12%" nowrap="nowrap" align="center">工厂/仓位</td>
		                <td nowrap="nowrap" align="center">产品备注</td>
		                <td width="8%"  nowrap="nowrap" align="center">前${weeks}周销售量</td>
		                <td width="5%" align="center">库存</td>
		                <td width="5%" align="center">状态</td>
      				</tr>
      				<tbody id="details_body">
      				<c:forEach items="${af.map.konkaOrderInfoDetailsList}" var="cur">
        			<tr>
            			<td align="center"><span id="pd_name_span-${cur.id}">${fn:escapeXml(cur.pd_name)}</span><html-el:hidden property="details_id" value="${cur.id}" /></td>
						<td align="center">
						<html-el:hidden property="count" styleId="count-${cur.id}" styleClass="count"  value="${cur.good_count}" />
						<html-el:text property="good_count" styleId="good_count-${cur.id}" styleClass="good_count" style="width:90%" maxlength="5" value="${cur.good_count}" 
						onfocus="javascript:setOnlyInt(this)"/></td>
						<td align="center">${fn:escapeXml(cur.good_unit)}</td>
						<td align="center"><html-el:text property="good_price" styleId="good_price-${cur.id}" styleClass="good_price" style="width:90%" maxlength="8" value="${cur.good_price}" /></td>
						<td align="center"><html-el:text property="good_sum_price" styleId="good_sum_price-${cur.id}" styleClass="good_sum_price" style="width:90%" maxlength="15" value="${cur.good_sum_price}" readonly="true"/></td>
						<td align="center" style="font-weight:800;"><html-el:text property="good_discount_price" styleClass="good_discount_price_all_hid" style="width:90%" maxlength="10" value="${cur.good_discount_price}" styleId="good_discount_price_all_hid-${cur.id}" /></td>
						<!-- 折扣比例（%） -->
						<td align="center"><html-el:text property="good_discount" styleId="good_discount-${cur.id}" styleClass="good_discount" style="width:90%" maxlength="10" value="${cur.good_discount}" /></td>
						<!-- 单台折扣（元）
						<td align="center"><html-el:text property="good_discount_price_one" styleId="good_discount_price-${cur.id}" styleClass="good_discount_price" size="10" maxlength="15" value="${cur.good_count eq 0 ? 0 : (cur.good_discount_price / cur.good_count)}" /></td>
						 -->
						 <td align="center"><html-el:text property="af_discount_price" styleId="af_discount_price-${cur.id}" styleClass="af_discount_price" size="10" maxlength="15" value="${cur.good_sum_price+cur.good_discount_price+cur.good_sum_price*cur.good_discount/100}" readonly="true"/></td>
						 <td>
						 <c:if test="${!flag_Zb_role}">
						 <select name="store_key" class="store_key">
							<option value="">请选择...</option>
							<c:forEach items="${storeList}" var="cur_s">
								<c:set var="k" value="[${cur_s.fac_sn}-${cur_s.store_sn}]${cur_s.store_desc}" />
								<c:if test="${cur.store_key eq k}">
									<option value="${k}" selected="selected">${k}</option>
								</c:if>
								<c:if test="${cur.store_key ne k}">
									<option value="${k}">${k}</option>
								</c:if>
							</c:forEach>
						</select>
						</c:if>
						
						<c:if test="${flag_Zb_role}">
						<c:forEach items="${storeList}" var="cur_s">
						<c:set var="k" value="[${cur_s.fac_sn}-${cur_s.store_sn}]${cur_s.store_desc}" />
						<c:if test="${k eq cur.store_key}">${k}</c:if>
						</c:forEach>
						</c:if>
						<c:if test="${empty storeList}">
						 <input  value="${cur.store_key}" readonly></input>
						</c:if>
						</td>
						<td>
						<c:if test="${not empty fn:escapeXml(cur.pd_remark)}">
						<html-el:text property="pd_remark" styleId="pd_remark-${cur.id}" value="${fn:escapeXml(cur.pd_remark)}" maxlength="50" />
						</c:if>
						<c:if test="${empty fn:escapeXml(cur.pd_remark)}">
						<html-el:text property="pd_remark" styleId="pd_remark-${cur.id}" value="" maxlength="50" />
						</c:if>
						</td>
						<td align="center">${fn:escapeXml(cur.sale_count)}</td>
						<td align="center">${fn:escapeXml(cur.store_num)}</td>
						
						<td align="center">
						<c:if test="${cur.sale_count - cur.store_num < cur.good_count}"><img src="${ctx}/images/yuan_red.png" width="16" height="16"/></c:if>
						<c:if test="${cur.sale_count - cur.store_num >= cur.good_count}"><img src="${ctx}/images/yuan_green.png" width="16" height="16"/></c:if>
						</td>
					</tr>
				    </c:forEach>
				    </tbody>
       			</table>
       			<div style="font-size:13px;font-weight:700;">
				          数量总计：<span id="order_num">${af.map.order_num}</span>&nbsp;&nbsp;&nbsp;&nbsp;
				          金额总计：<span id="money">${af.map.money}</span>元&nbsp;&nbsp;&nbsp;&nbsp;
				          折扣金额总计：<span id="good_discount_price">${af.map.good_discount_price}</span>元 &nbsp;&nbsp;&nbsp;&nbsp;
				          折后金额总计：<span id="af_discount_price_sum">${af.map.money + af.map.good_discount_price}</span>元 
       			</div>
            </td>
        </tr>
        </c:if>
        <c:if test="${not empty af.map.konkaOrderInfoAuditList}">
        <tr class="one">
        	<td colspan="4"><strong>审核流程：</strong><br /><table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        			<tr class="tabtt1">
        				<td width="5%" align="center">序号</td>
        				<td width="15%" align="center">审核时间</td>
        				<td width="15%" align="center">审核人</td>	
        				<td width="15%" align="center">角色</td>
        				<td width="10%" align="center">审核结果</td>
        				<td align="center">审核意见</td>
        			</tr>
        			<c:forEach items="${af.map.konkaOrderInfoAuditList}" var="cur1" varStatus="vs">
        			<tr>
        				<td align="center">${vs.count}</td>
        				<td align="center"><fmt:formatDate value="${cur1.audit_datetime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        				<td align="center">${fn:escapeXml(cur1.audit_user)}</td>
        				<td align="center">
        				<c:if test="${not empty cur1.map.role_name}">${fn:escapeXml(cur1.map.role_name)}</c:if>
						<c:if test="${empty cur1.map.role_name and cur1.audit_result eq -9}"><span style="color:orange;">客户(撤销订单)</span></c:if>
						<c:if test="${empty cur1.map.role_name and cur1.audit_result eq 10}"><span style="color:orange;">变更订单</span></c:if>
        			</td>
        				<td align="center"><c:choose>
       						<c:when test="${cur1.audit_result eq 1}"><span style="color:green;">审核通过</span></c:when>
       						<c:when test="${cur1.audit_result eq -1}"><span style="color:#F00;">驳回（至审核人）</span></c:when>
       						<c:when test="${cur1.audit_result eq 0}"><span style="color:#00F;">驳回（至制单）</span></c:when>
       						<c:when test="${cur1.audit_result eq -9}"><span style="color:#999;">（客户）撤销</span></c:when>
       						<c:when test="${cur1.audit_result eq 10}"><span style="color:#999;">订单变更</span></c:when>
        				</c:choose></td>
        				<td><c:if test="${empty cur1.audit_comment }">无</c:if>
                  			<c:if test="${not empty cur1.audit_comment }">${fn:escapeXml(cur1.audit_comment)}</c:if></td>
                  	</tr>
        			</c:forEach>
        	    </table>
            </td>
        </tr>
        </c:if>

        	<tr class="one">
        		<td class="title_item2"><font color="red">*</font>订单流程：</td>
        		<td colspan="3">
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
        	<tr class="one" style="display:none;" id="process_id_tr">
				<td class="title_item2">订单分类：</td>
				<td colspan="3">
				    <label for="process_state_1" style="cursor:pointer;"><html-el:radio property="process_state" styleId="process_state_1" styleClass="process_state" value="1" /> 一般订单</label>
					<span style="color:#999;margin-left:3em;">一般订单是指下单价格等于或高于分公司标准价格的订单</span><br />
					<label for="process_state_2" style="cursor:pointer;"><html-el:radio property="process_state" styleId="process_state_2" styleClass="process_state" value="2" /> 特殊订单</label>
					<span style="color:#999;margin-left:3em;">特殊订单是指下单价格低于分公司标准价格的订单</span></td>
			</tr>

        <tr class="one">
          <td class="title_item2">审核结果：</td>
          <td colspan="3"><html-el:select property="audit_result" value="10" styleId="audit_result" style="width:120px;">
              <html-el:option value="1">审核通过</html-el:option>
              <html-el:option value="-1">驳回</html-el:option>
              <html-el:option value="0">重新制单</html-el:option>
              <html-el:option value="10">变更订单</html-el:option>
            </html-el:select>&nbsp;&nbsp;<span style="color:blue;">审核流程中第一个审核人如果发现问题，可以线下电话通知客户，修复后再审核。</span></td>
        </tr>
        <tr class="one">
          <td class="title_item2">审核意见：</td>
          <td colspan="3"><html-el:textarea styleId="audit_comment" property="audit_comment" styleClass="webinput" style="width:100%" rows="3"></html-el:textarea></td>
        </tr>
        <tr class="one">
          <td>&nbsp;</td>
          <td colspan="3"><label>
              <input class="but4" type="button" name="Submit4" id="send" value="提交" />
              <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();" />
            </label></td>
           <td>&nbsp;</td>
        </tr>
        <tr>
        <td colspan="4">
        <table width="100%" >
        <!-- 汇总信息 -->
						<tr>
							<td colspan="4" align="left" style="font-weight:bold;color:#74685F;">汇总信息</td>
						</tr>
						<tr>
							<td class="title_item1">申请数量：</td>
							<td>${af.map.order_num}</td>
							<td class="title_item1">申请金额：</td>
							<td >￥${af.map.money}</td>
						</tr>
						<tr>
							<td class="title_item1">审核数量：</td>
							<td>${af.map.order_num}</td>
							<td class="title_item1">审核金额：</td>
							<td style="color:#CD0000;font-family:tahoma;">￥${af.map.money}</td>
						</tr>											
						<!-- 付款信息 -->
						<tr>
							<td colspan="4" align="left" style="font-weight:bold;color:#74685F;">付款信息</td>
						</tr>
						<tr>
							<td class="title_item1"><font color="red">*</font>货款支付方式：</td>
							<td>
								<c:choose>
									<c:when test="${af.map.pay_type eq 4}">现汇</c:when>
									<c:when test="${af.map.pay_type eq 5}">帐期</c:when>
									<c:when test="${af.map.pay_type eq 6}">承兑</c:when>
									<c:when test="${af.map.pay_type eq 45}">现汇、帐期</c:when>
									<c:when test="${af.map.pay_type eq 46}">现汇、承兑</c:when>
									<c:when test="${af.map.pay_type eq 56}">帐期、承兑</c:when>
									<c:when test="${af.map.pay_type eq 456}">现汇、帐期、承兑</c:when>
								</c:choose>
							</td>
							<td class="title_item1">可用额度：</td>
							<td><span id="can_use_money">-</span>
							<html-el:button property="btn_can_use_money" styleId="btn_can_use_money" value="点击查询 " style="margin-left:10px;" />
							</td>
							
		          			<td></td>
		        		</tr>
						<!-- 收货信息 -->
						<tr>
							<td colspan="4" align="left" style="font-weight:bold;color:#74685F;">收货信息</td>
						</tr>
						<tr>
							<td class="title_item1" width="15%"><font color="red">*</font>配送方式：</td>
							<td>
								<c:if test="${af.map.send_type eq 1}">自提</c:if>
								<c:if test="${af.map.send_type eq 2}">配送</c:if>
							</td>
							<td class="title_item1" width="15%">收货人姓名：</td>
		          			<td width="35%">${af.map.user_name}</td>
						</tr>
						<tr>
							<td class="title_item1" width="15%">收货人固定电话：</td>
							<td width="35%">${af.map.user_tel}</td>
							<td class="title_item1" width="15%">收货人手机：</td>
							<td>${af.map.user_phone}</td>
						</tr>
						<tr>
							<td class="title_item1" width="15%">收货人所属地区：</td>
							<td colspan="3" width="85%">${fullName}</td>
						</tr>
						<tr>
							<td class="title_item1">收货人地址：</td>
							<td colspan="3">${af.map.user_addr}</td>
						</tr>
						<tr>
							<td class="title_item1">收货人备注：</td>
							<td colspan="3">${af.map.user_remark}</td>
						</tr>
						<!-- 同步信息 -->
						<tr>
							<td colspan="4" align="left" style="font-weight:bold;color:#74685F;">同步信息</td>
						</tr>
						<tr>
							<td width="8%" align="left" class="title_item1">销售凭证类型</td>
							<td width="12%">${af.map.doc_type}</td>
							<td width="8%" align="left" class="title_item1">销售组织</td>
							<td width="12%">${af.map.sales_org}</td>
						</tr>
						<tr>
							<td width="8%" align="left" class="title_item1">分销渠道</td>
							<td width="12%">10</td>
							<td width="8%" align="left" class="title_item1">产品组</td>
							<td width="12%">10</td>
						</tr>
						<tr>
							<td width="8%" align="left" class="title_item1">售达方</td>
							<td width="12%">${af.map.ag}</td>
							<td width="8%"  align="left" class="title_item1">出具发票方</td>
							<td width="12%">${af.map.re}</td>
						</tr>
						<tr>
							<td width="8%" align="left" class="title_item1">付款方</td>
							<td width="12%">${af.map.rg}</td>
							<td width="8%"  align="left" class="title_item1">送达方</td>
							<td width="12%">${af.map.we}</td>
						</tr>
        </table>
        </td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	// 将分类默认不选择
	$(".process_state").removeAttr("checked");
	$("#audit_comment").attr("dataType", "Limit").attr("max", 200).attr("msg", "不要超过200个字符！");
	
	$(".good_count").attr("dataType", "Require").attr("msg", "请填写数量，且必须为正数！");
	//$(".good_price").attr("dataType", "Require").attr("msg", "请填写单价，且必须为正数！");
	$(".good_discount").attr("dataType", "Require").attr("msg", "请填写单台折扣，且必须为正数！");
	$(".good_discount_price").attr("dataType", "Require").attr("msg", "请填写单台折扣额，且必须为正数！");
	<c:if test="${empty af.map.process_id}">
		$("#process_id").attr("dataType", "Require").attr("msg", "请选择订单类型！");
	</c:if>

	$("#audit_result").change(function(){
		var audit_result = $("#audit_result").val();
		if(audit_result == 1){
			$('input:radio[name="node_id"]').attr("checked", false);
		}
	});
	
	
	//自动选择工厂仓位,某一订单行确认后,其它未填写的自动赋值
	$(".store_key").change(function(){
			//获取列表行的指定列
			var Inputdata = $(this).val();
			if(null!=Inputdata && Inputdata!=""){
				$(".store_key").each(function(){
					var optionVal =$(this).val();
					if(optionVal ==null || optionVal==""){
						$(this).val(Inputdata).attr("selected",true);
					}
					
				 });
			}
	});
	
	
	var f = document.forms[0];
	$("#send").click(function(){
		var process_id = $('input:radio[name="process_id"]:checked').val();
		var audit_result = $("#audit_result").val();
		var html = $("#show_tip").html();
		
		
		
		if(audit_result == -1 && node_id == null){
			alert("请选择驳回位置，或者如果您是第一个审核人，可以线下电话通知客户，无需驳回操作！");
			return false;
		}
		
		if(audit_result == 1){
			$('input:radio[name="node_id"]').removeAttr("checked");
		}
		
		if(Validator.Validate(f, 1)){
			if(process_id==null){
				alert("请先选择订单审核流程！"); 
	          }else{
			f.submit();	
			}
		}	
		
	});

	$(".good_price").bind("input propertychange", function(){
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		
		var id = $(this).attr("id").split("-")[1];
		var $good_count = $("#good_count-" + id);
		var $good_price = $("#good_price-" + id);
		var $good_sum_price = $("#good_sum_price-" + id);
		var $good_discount = $("#good_discount-" + id);
		var $good_discount_price_all_hid = $("#good_discount_price_all_hid-" + id);
		var $af_discount_price = $("#af_discount_price-" + id);
		var pd_name = $("#pd_name_span-"+id).text();

		if(pd_name=='KF-22PB'|| pd_name=='KF-22XD' || pd_name=='KF-22KK'){
			//$(this).val(1);
   	   	 }else{
   	   	if (!_reg.test($(this).val())) {
			$(this).val(0);
		   }
   	   	   	 }

		
	   // $good_discount_price.val(($good_price.val()*$good_discount.val()/100).toFixed(2));
		//alert($good_discount_price.val());
		//动态计算合计后的金额
	    $af_discount_price.val(parseFloat(parseFloat($good_price.val()*$good_count.val()*$good_discount.val()/100)+ parseFloat($good_discount_price_all_hid.val())+parseFloat($good_count.val())*parseFloat($good_price.val())).toFixed(2));
	    
		calcOneCountAndJhPrice($good_count,$good_price,$good_sum_price);

   		// 动态计算折扣总金额
   		//$("#good_discount_price_all-" + id).text($("#good_discount_price-" + id).val() * $("#good_count-" + id).val());
   		//$("#good_discount_price_all_hid-" + id).val($("#good_discount_price_all-" + id).text());
   		
		calcPdNumAndPrice("details_body");
	});

	$(".good_count").bind("input propertychange", function(){
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test($(this).val())) {
			$(this).val(0);
		}

		var id = $(this).attr("id").split("-")[1];
		var $good_count = $("#good_count-" + id);
		var $good_price = $("#good_price-" + id);
		var $good_sum_price = $("#good_sum_price-" + id);
		var pd_name = $("#pd_name_span-"+id).text();
		var $af_discount_price = $("#af_discount_price-" + id);
		var $good_discount_price_all_hid = $("#good_discount_price_all_hid-" + id);
		var $good_discount = $("#good_discount-" + id);
		var count= $("#count-" + id).val();
		if(pd_name=='KF-22PB'|| pd_name=='KF-22XD' || pd_name=='KF-22KK'){
			$(this).val(1);
   	   	 }
       if(parseInt(count) < parseInt($good_count.val())){
       alert("修改后的数量不能大于原有数量");
       $("#good_count-" + id).val(count);
           }
	    $af_discount_price.val(parseFloat(parseFloat($good_price.val()*$good_count.val()*$good_discount.val()/100)+parseFloat($good_discount_price_all_hid.val())+parseFloat($good_count.val())*parseFloat($good_price.val())).toFixed(2));
		
		calcOneCountAndJhPrice($good_count,$good_price,$good_sum_price);

   		// 动态计算折扣总金额
   		//$("#good_discount_price_all-" + id).text($("#good_discount_price-" + id).val() * $("#good_count-" + id).val());
   		//$("#good_discount_price_all_hid-" + id).val($("#good_discount_price_all-" + id).text());
   		
		calcPdNumAndPrice();
	});

	$(".good_discount").blur("input propertychange", function(){//折扣
		//var id = $(this).attr("id").split("-")[1];
		//var $good_price = $("#good_price-" + id);
		//var $good_discount = $("#good_discount-" + id); // 折扣率
		//var $good_discount_price = $("#good_discount_price-" + id);
			
		var discount = (this.value);
		var _reg = /^[\-]\d*?\.?\d*?$/;
		if (!_reg.test(discount)) {
			$(this).val(0);
			discount = 0;
		}
		if (!_reg.test($(this).val())) {
			$(this).val(0);
		}
		discount = parseFloat(this.value);
		if(isNaN(discount)) discount = 0;
		if(discount > 100) {
			discount = 0;
			this.value = 0;
		}

		var id = $(this).attr("id").split("-")[1];
		var $good_sum_price = $("#good_sum_price-" + id);
		var pd_name = $("#pd_name_span-"+id).text();
		var $af_discount_price = $("#af_discount_price-" + id);
		var $good_discount_price_all_hid = $("#good_discount_price_all_hid-" + id);
		var $good_discount = $("#good_discount-" + id);

		if(pd_name=='KF-22PB'|| pd_name=='KF-22XD' || pd_name=='KF-22KK'){
				$(this).val(1);
	   	   	 }
		$af_discount_price.val(parseFloat(parseFloat($good_sum_price.val() * $good_discount.val()/100)+parseFloat($good_discount_price_all_hid.val())+parseFloat($good_sum_price.val())).toFixed(2));
		
   		//var good_price = parseFloat($good_price.val());//金额
   		//if(isNaN(good_price)) good_price = 0;
	   	
   		//$good_discount_price.val((discount * good_price/100).toFixed(2));//折扣金额
   		
   		// 动态计算折扣总金额
   		//$("#good_discount_price_all-" + id).text($("#good_discount_price-" + id).val() * $("#good_count-" + id).val());
   		//$("#good_discount_price_all_hid-" + id).val($("#good_discount_price_all-" + id).text());
   		
   		calcPdNumAndPrice();
	});

	$(".good_discount_price_all_hid").blur("input propertychange", function(){ // 折扣金额
		//var id = $(this).attr("id").split("-")[1];
		//var $good_price = $("#good_price-" + id);
		//var $good_discount = $("#good_discount-" + id);
		//var $good_discount_price = $("#good_discount_price-" + id);
		var discount = (this.value);
		var _reg = /^[\-]\d*?\.?\d*?$/;
		if (!_reg.test(discount)) {
			//$good_discount_price.val(0);
			$(this).val(0);
			discount = 0;
		}

		var id = $(this).attr("id").split("-")[1];
		var $good_sum_price = $("#good_sum_price-" + id);
		var pd_name = $("#pd_name_span-"+id).text();
		var $af_discount_price = $("#af_discount_price-" + id);
		var $good_discount_price_all_hid = $("#good_discount_price_all_hid-" + id);
		var $good_discount = $("#good_discount-" + id);

		if(pd_name=='KF-22PB'|| pd_name=='KF-22XD' || pd_name=='KF-22KK'){
			$(this).val(0.00);
   	   	 }
		$af_discount_price.val(parseFloat(parseFloat($good_sum_price.val()* $good_discount.val()/100)+parseFloat($good_discount_price_all_hid.val())+parseFloat($good_sum_price.val())).toFixed(2));
		//discount = parseFloat(this.value);
		//if(isNaN(discount)) discount = 0;
   		//var good_price = parseFloat($good_price.val());//金额
   		//if(isNaN(good_price)) good_price = 0;
	   	
   		//$good_discount.val(good_price == 0 ? '-' : (discount * 100 / good_price).toFixed(2));//折扣率

   		// 动态计算折扣总金额
   		//$("#good_discount_price_all-" + id).text($("#good_discount_price-" + id).val() * $("#good_count-" + id).val());
   		//$("#good_discount_price_all_hid-" + id).val($("#good_discount_price_all-" + id).text());
   		
   		calcPdNumAndPrice();
	});
	
	$("#audit_result").change(function(){
		if($(this).val() == -1){
			$("#audit_poss_tr").show();
		}else{
			$("#audit_poss_tr").hide();
		}
		//iframe高度自适应
		window.parent.resizeFrameHeight('mainFrame', 3);
	});
	
	// 订单类型改变
	<c:if test="${empty af.map.process_id}">
		$(document).delegate(".process_id", "click", function(){
			$("#process_id_tr").hide();
			
			var label = $("#process_id_label_" + $(this).val()).html();
			if(label.indexOf("统一") > -1) $("#process_id_tr").show();
			
			//iframe高度自适应
			window.parent.resizeFrameHeight('mainFrame', 3);
		});
	</c:if>
});

//计算 单个的金额
function calcOneCountAndJhPrice(good_count, good_price, good_sum_price) {
	var good_count = parseFloat(good_count.val());//数量
   	if(isNaN(good_count)) good_count = 1;
   	var good_price =  parseFloat(good_price.val());//单价
   	if(isNaN(good_price)) good_price = 0;
   	
   	good_sum_price.val((good_count * good_price).toFixed(2));//金额
}

//计算 合计的 数量、 金额和折扣金额
function calcPdNumAndPrice() {
	var dd_count_sum = 0;
	var dd_money_sum = 0;
	var dd_discount_sum = 0;
	var dd_af_discount_sum = 0;

	$(".good_count").each(function(){
		var this_value = parseFloat($(this).val());
		if(isNaN(this_value)) this_value = 0;
		dd_count_sum += this_value;
	});

	$(".good_sum_price").each(function(){
		var this_value = parseFloat($(this).val());
		if(isNaN(this_value)) this_value = 0;
		dd_money_sum += this_value;
	});
	
	//$(".good_discount_price").each(function(){
	//	var id = $(this).attr("id").split("-")[1];
	//	var good_count = $("#good_count-" + id).val();
	//	if(isNaN(good_count)) good_count = 0;

	//	var good_discount_price_all_hid = $("#good_discount_price_all_hid-" + id).val();	
		
	//	var this_value = parseFloat($(this).val());
	//	if(isNaN(this_value)) this_value = 0;
	//	dd_discount_sum += (this_value * good_count) + good_discount_price_all_hid;;
	//});
	
	//折扣后总金额
	$(".af_discount_price").each(function(){
		var this_value = parseFloat($(this).val());
		if(isNaN(this_value)) this_value = 0;
		dd_af_discount_sum += this_value;
	});
	
	$("#order_num").text(dd_count_sum);//订单总数
	$("#money").text(dd_money_sum.toFixed(2));//订单金额
	$("#good_discount_price").text((parseFloat(dd_af_discount_sum) - (parseFloat(dd_money_sum))).toFixed(2));//订单折扣金额
	$("#af_discount_price_sum").text(parseFloat(dd_af_discount_sum).toFixed(2));//折后金额


	
}



// 点击查询额度
$(document).delegate("#btn_can_use_money", "click", function(){
	  $("#can_use_money").html("<img src='${ctx}/styles/images/loading.gif' />");
	  $.ajax({
		  type: "POST",
		  url: "${ctx}/customer/manager/JxcKonkaOrderRegister.do?method=getCustomerCreditByR3CodeForAjax",
		  data: {"r3_code" : '${af.map.r3_code}', "timestamp" : new Date().getTime()},
		  dataType: "json",
		  sync: true, 
		  error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
		  success: function(result) {
			if(result.status == "-1"){
				alert(result.msg);
				return;
			}
			$("#can_use_money").html(result.data);
		  }
	  }); 
});



//正则表达式：只能输入数字
function setOnlyInt(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:\d+(?:\d+)?|\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\d+$/))obj.value=obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		if(obj.value.length == 0 || isNaN(obj.value) || obj.value == 0) obj.value = "0";
	});
}
//]]></script>
<style type="text/css">
.title_item,.title_item1 {
	background-color: #F5F4F4;
	font-weight: bold;
	text-align: right;
}

.title_item2 {
	background-color: #F5F4F4;
	font-weight: bold;
	text-align: right;
}

.but4 {
	text-align: right;
	width: 67px;
}

.but5 {
	text-align: right;
	width: 67px;
}

.one {
	background-color: #FFF;
}

.tabtt1 {
	background-color: #FFF;
}
</style>


<jsp:include page="/__analytics.jsp" />
</body>
</html>
