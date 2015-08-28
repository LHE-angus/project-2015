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
<style type="text/css">
/* #hiddener { */
/* 	position: absolute; */
/* 	z-index: 9999; */
/* 	border: solid 1px; */
/* 	bgcolor:grey; */
/* } */
</style>
</head>
<body>
<div class="oarcont">
  <div class="rtabcont2">
    <html-el:form action="KonkaOrderAudit.do" method="post">
      <input type="hidden" id="id" name="id" value="${af.map.id}" />
      <input type="hidden" id="username" name="username" value="${af.map.username}" />
      <input type="hidden" id="userpass" name="userpass" value="${af.map.userpass}" />
      <input type="hidden" id="user_id" name="user_id" value="${af.map.user_id}" />
      <html-el:hidden property="order_id" styleId="order_id" value="${af.map.id}" />
      <html-el:hidden property="flag" styleId="flag" value="${flag}" />
      <html-el:hidden property="audit_proc_cond_flag" styleId="audit_proc_cond_flag" value="${has_proc_cond}" />
      <html-el:hidden property="customer_type" styleId="customer_type" value="${customer_type}" />
      <html-el:hidden property="flag_Zb_role" styleId="flag_Zb_role" value="${flag_Zb_role}" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
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
        <td class="title_item" nowrap="nowrap">售达方编码：</td>
		<td width="15%">${r3_code}</td>
		</tr>
         <tr>
         <td class="title_item" nowrap="nowrap">制单人：</td>
		<td width="35%" >${af.map.add_user_name}</td>
		 </tr>
		 <tr>
		<td class="title_item" nowrap="nowrap">业务员：</td>
		<td width="35%">${ywy_user_name}</td>
		</tr>
         <tr>
          <td class="title_item" nowrap="nowrap" >提交日期：</td>
          <td><fmt:formatDate value="${af.map.order_date}" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" >创建日期：</td>
          <td><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd"/></td>
        </tr>
<!--        <tr>
          <td class="title_item" nowrap="nowrap">配送方式：</td>
          <td><c:if test="${af.map.send_type eq 1}">自提</c:if>
            <c:if test="${af.map.send_type eq 2}">配送</c:if></td>
        </tr>-->
        <tr>
       
          <td class="title_item" nowrap="nowrap">订单凭证信息：</td>
          <td>
            <html-el:select property="doc_type" styleId="doc_type" value="${af.map.doc_type}">
			<html-el:option value="ZFOR">ZFOR</html-el:option>
			<html-el:option value="ZFGC">ZFGC</html-el:option>
		    <html-el:option value="ZFCR">ZFCR</html-el:option>
		    <html-el:option value="ZFRE">ZFRE</html-el:option>
			</html-el:select>
            </td>
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
            </c:choose>
<!--             <br /><span style="color:#999;">1、现汇：采用现金汇款或转帐的方式支付货款； -->
<!--             &nbsp;2、帐期：使用您的信用额度余额支付货款；&nbsp;3、承兑：使用银行提供的承兑支票支付货款。 -->
<!--             </span> -->
            </td>
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
          <td><span id="good_discount_price"><fmt:formatNumber value="${af.map.good_discount_price}" pattern="#0.00" /></span>元</td>
        </tr>
        
     
        
        
        
        
        
        
        <tr>
          <th colspan="2" width="90%" align="left" class="bno" id="filetitle">订单收货信息</th>
        </tr>
        
        <tr>
							<td class="title_item1" width="15%">配送方式：</td>
							<td>
								<c:if test="${af.map.send_type eq 1}">自提</c:if>
								<c:if test="${af.map.send_type eq 2}">配送</c:if>
							</td>
							</tr><tr>
							<td class="title_item1" width="15%">收货人姓名：</td>
		          			<td width="35%">${af.map.user_name}</td>
						</tr>
						<tr>
							<td class="title_item1" width="15%">收货人固定电话：</td>
							<td width="35%">${af.map.user_tel}</td>
							</tr><tr>
							<td class="title_item1" width="15%">收货人手机：</td>
							<td>${af.map.user_phone}</td>
						</tr>
						<tr>
							<td class="title_item1" width="15%">收货人所属地区：</td>
							<td colspan="1" width="85%">${fullName}</td>
						</tr>
						<tr>
							<td class="title_item1">收货人地址：</td>
							<td colspan="1">${af.map.user_addr}</td>
						</tr>
						<tr>
							<td class="title_item1">收货人备注：</td>
							<td colspan="1">${af.map.user_remark}</td>
						</tr>
        
           
      <c:if test="${not empty attachmentList}">  
        <tr>
       <th colspan="2" width="90%" align="left" class="bno" >附件：</th>
	   </tr>	
        	<c:forEach items="${attachmentList}" var="cur" varStatus="vs">
        	<tr>
        	<td align="center" width="10%">${vs.count}</td>
			<td align="left" nowrap="nowrap">
			<a href="${ctx}/${cur.save_path}">${cur.file_name}</a>
			</td>
        	</tr>
        	</c:forEach>
        	</c:if>
         <c:if test="${not empty af.map.content1}">
          <tr>
       		<th colspan="2" width="90%" align="left" class="bno" >意见反馈：</th>
	   		</tr>	
        <tr>
				<td class="title_item1">反馈内容：</td>
				<td>${af.map.content1}</td>
		</tr>	
       </c:if>
        
        
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
              <td ><html-el:text property="good_count" styleId="good_count-${cur.id}" styleClass="good_count" size="10" maxlength="15" value="${cur.good_count}" />${fn:escapeXml(cur.good_unit)}</td>
            </tr>
<!--             <tr > -->
<!--               <td class="title_item" nowrap="nowrap">单位：</td> -->
<%--               <td >${fn:escapeXml(cur.good_unit)}</td> --%>
<!--             </tr> -->
            <tr >
              <td class="title_item" nowrap="nowrap">单价：</td>
              <td ><html-el:text property="good_price" styleId="good_price-${cur.id}" styleClass="good_price" size="10" maxlength="15" value="${cur.good_price}" />元</td>
            </tr>
            <tr >
              <td class="title_item" nowrap="nowrap">金额：</td>
              <td ><html-el:text property="good_sum_price" styleId="good_sum_price-${cur.id}" styleClass="good_sum_price" size="10" maxlength="15" value="${cur.good_sum_price}" readonly="true"/>元</td>
            </tr>
            <tr >
              <td class="title_item" nowrap="nowrap">折扣(%)-K007：</td>
              <td ><html-el:text property="good_discount" styleId="good_discount-${cur.id}" styleClass="good_discount" size="10" maxlength="15" value="${cur.good_discount}" />%</td>
            </tr>
            <tr >
              <td class="title_item" nowrap="nowrap">折扣金额-RB00：</td>
              <td ><html-el:text property="good_discount_price" styleId="good_discount_price-${cur.id}" styleClass="good_discount_price" size="10" maxlength="15" value="${cur.good_discount_price}" />元</td>
            </tr>
            <tr >
              <td class="title_item" nowrap="nowrap">总折扣：</td>
              <td ><span id="good_discount_price_all-${cur.id}"><fmt:formatNumber value="${cur.good_discount_price + (cur.good_sum_price*cur.good_discount/100)}" pattern="#0.00"/> </span><html-el:hidden property="good_discount_price_one" value="${cur.good_discount_price}" styleClass="good_discount_price_all_hid" styleId="good_discount_price_all_hid-${cur.id}" />元</td>
            </tr>
            
            <tr >
              <td class="title_item" nowrap="nowrap">工厂仓位：</td>
              <td>
						 <c:if test="${!flag_Zb_role}">
						 <select name="store_key">
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
						</td></tr>
            
            <tr >
              <td class="title_item" nowrap="nowrap">产品备注：</td>
              <td >
              <html-el:text   property="pd_remark" styleId="pd_remark-${cur.id}"  value="${cur.pd_remark}">
              </html-el:text>
<%--               ${fn:escapeXml(cur.pd_remark)} --%>
              </td>
            </tr>
             <tr style="border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #d5c7c9;">
            <td class="title_item" nowrap="nowrap" colspan="2">
            <span width="20%">前${weeks}周销量:&nbsp;</span>&nbsp;&nbsp;&nbsp;&nbsp;<span width="20%">${fn:escapeXml(cur.sale_count)}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             <span width="20%">库存：&nbsp;</span>&nbsp;&nbsp;&nbsp;&nbsp;<span width="20%">${fn:escapeXml(cur.store_num)}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 &nbsp;&nbsp;&nbsp;&nbsp;
                 <br/>
                  <span width="10%">周转天数：&nbsp;</span>
                  &nbsp; &nbsp; <span width="20%">
                  <c:if test="${cur.sale_count ne 0 and cur.store_num ne 0}">
						<fmt:formatNumber pattern="#00" value="${ weeks * 7 /(cur.store_num/cur.sale_count)}"></fmt:formatNumber>
						</c:if>
						<c:if test="${cur.sale_count eq 0 or cur.store_num eq 0}">
						-
						</c:if>
						</span> &nbsp;&nbsp; &nbsp;&nbsp;
                 <span width="15%">
                 <c:if test="${cur.sale_count - cur.store_num < cur.good_count}"><img src="${ctx}/images/yuan_red.png" width="16" height="16"/></c:if>
						<c:if test="${cur.sale_count - cur.store_num >= cur.good_count}"><img src="${ctx}/images/yuan_green.png" width="16" height="16"/></c:if>
						</span>
                 </td>
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
              <td>
              <c:if test="${empty cur1.audit_comment }">无</c:if>
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
          <td><html-el:select property="audit_result" styleId="audit_result" style="width:120px;">
              <html-el:option value="1">审核通过</html-el:option>
              <html-el:option value="-1">驳回</html-el:option>
              <html-el:option value="0">重新制单</html-el:option>
            </html-el:select>&nbsp;&nbsp;<span style="color:blue;">审核流程中第一个审核人如果发现问题，可以线下电话通知客户，修复后再审核。</span></td>
        </tr>
        <tr id="audit_poss_tr" style="display:none;">
        	<td class="title_item">驳回位置：</td>
        	<td><c:forEach var="cur" items="${nodeList}" varStatus="vs">
        			<label for="${cur.id}"><html-el:radio styleId="${cur.id}" property="node_id" disabled="${cur.id ge af.map.next_node_id}" value="${cur.id}">${cur.role_name}</html-el:radio></label> 
	        		<c:if test="${vs.last ne true}">
						--&gt;
					</c:if>
        	</c:forEach>
        	<c:if test="${empty nodeList}"><span style="color:#F00;">该订单暂未确定审核流程，只有确定了审核流程的订单方可进行驳回操作。</span></c:if></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap">财务与业务的意见：</td>
          <td>
		<html-el:select property="is_need_to_manager" styleId="is_need_to_manager" value="${af.map.is_need_to_manager}">
		<html-el:option value="0">一致</html-el:option>
		<html-el:option value="1">不一致</html-el:option>
		</html-el:select>
		</td>
</tr>
	<tr>
          <td class="title_item" nowrap="nowrap">审核意见：</td>
          <td>
          <html-el:textarea property="audit_comment" cols="23" style="width:300px" rows="3" styleClass="webinput" ></html-el:textarea> 
          </td>
        </tr>
        <tr>
          <td></td>
          <td><label>
              <a id="send1"><img src="${ctx}/mobile/images/tijiao.jpg" width="93" height="38" /></a>
		      <input class="but4" type="button" name="Submit4" id="send" value="" style="background-color:#666666; display:none;"/> 
            </label></td>
        </tr>
      </table>
    </html-el:form>
   
  </div>
   <div id="hiddener" style="display: none;text-align:center; position: relative;  top: -285px; z-index: 99999">
     <div style="background-color:grey;margin:0 auto;width:200px;border:solid 2px blue;"> 
       <font color="white" size="5">正在提交。。。</font></div>
        </div>
</div>
  
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#is_need_to_manager").attr("disabled","true");
	<c:if test="${flag_cw_yw_role}">
	$("#is_need_to_manager").removeAttr("disabled");
	</c:if>
	
	// 将分类默认不选择
	$(".process_state").removeAttr("checked");
	$("#hiddener").hide();
	$(".good_count").attr("dataType", "Require").attr("msg", "请填写数量，且必须为正数！");
	$(".good_price").attr("dataType", "Require").attr("msg", "请填写单价，且必须为正数！");
	$(".good_discount").attr("dataType", "Require").attr("msg", "请填写单台折扣，且必须为正数！");
	$(".good_discount_price").attr("dataType", "Require").attr("msg", "请填写单台折扣额，且必须为正数！");
	<c:if test="${empty af.map.process_id}">
		$("process_id").attr("dataType", "Group").attr("msg", "请选择订单类型！");
	</c:if>
	

	$("#audit_result").change(function(){
		var audit_result = $("#audit_result").val();
		if(audit_result == 1){
			$('input:radio[name="node_id"]').attr("checked", false);
		}
	});


	$("#send1").live('click', function() { 
	  $("#send").bind('click', function() { 
		var node_id = $('input:radio[name="node_id"]:checked').val();
		var audit_result = $("#audit_result").val();
		if(audit_result == -1 && node_id == null){
			alert("请选择驳回位置，或者如果您是第一个审核人，可以线下电话通知客户，无需驳回操作！");
			return false;
		}
		if(audit_result == 1){
			$('input:radio[name="node_id"]').removeAttr("checked");
		}

		<c:if test="${empty af.map.process_id}">
		 // 判断是否选择了统一流程，如果是则需要选择订单分类
	    $(".process_state").removeAttr("dataType");
		var id = $('input:radio:checked').val();
		var label = $("#process_id_label_" + id).html();
	    if(label.indexOf("统一") != -1)
	    	$(".process_state").attr("dataType", "Group").attr("msg", "请选择一种订单分类！");
   </c:if>
   
	    
		if(Validator.Validate(this.form, 1)){
		//	alert($("#send").offsetTop);
			$("#hiddener").show();
			$("#send").attr("disabled","disable"); 
			$("#send1").attr("disabled","disable"); 
			this.form.submit();
		}
		$("#send").unbind('click');
	}); 
	}); 
	
	$(".good_price").bind("input propertychange", function(){
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test($(this).val())) {
			$(this).val(0);
		}
		var id = $(this).attr("id").split("-")[1];
		var $good_count = $("#good_count-" + id);
		var $good_price = $("#good_price-" + id);
		var $good_sum_price = $("#good_sum_price-" + id);
		var $good_discount = $("#good_discount-" + id);
		var $good_discount_price = $("#good_discount_price-" + id);
		
	   // $good_discount_price.val(($good_price.val()*$good_discount.val()/100).toFixed(2));

	    //折扣率
	    var discount = parseFloat($good_discount.val());
	    if(isNaN(discount)) discount = 0;

		//数量
		var count = parseFloat($good_count.val());
		if(isNaN(count)) count = 0;

		//金额
		var price = parseFloat(this.value);
		if(isNaN(price)) price = 0;

		//折扣金额
		var discount_price = parseFloat($good_discount_price.val());
		if(isNaN(discount_price)) discount_price = 0;

		//折扣总金额
		var s_discount_price = parseFloat(discount_price + discount*count*price/100).toFixed(2);
					   
		calcOneCountAndJhPrice($good_count,$good_price,$good_sum_price);

   		// 动态计算折扣总金额
   		$("#good_discount_price_all-" + id).text(s_discount_price);
   		//$("#good_discount_price_all_hid-" + id).val(s_discount_price);
   		
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
		var $good_discount = $("#good_discount-" + id);
		var $good_discount_price = $("#good_discount_price-" + id);

		//折扣率
	    var discount = parseFloat($good_discount.val());
	    if(isNaN(discount)) discount = 0;

		//数量
		var count = parseFloat(this.value);
		if(isNaN(count)) count = 0;

		//金额
		var price = parseFloat($good_price.val());
		if(isNaN(price)) price = 0;

		//折扣金额
		var discount_price = parseFloat($good_discount_price.val());
		if(isNaN(discount_price)) discount_price = 0;

		//折扣总金额
		var s_discount_price = parseFloat(discount_price + discount*count*price/100).toFixed(2);
		
		calcOneCountAndJhPrice($good_count,$good_price,$good_sum_price);

   		// 动态计算折扣总金额
   		$("#good_discount_price_all-" + id).text(s_discount_price);
   		//$("#good_discount_price_all_hid-" + id).val(s_discount_price);
   		
		calcPdNumAndPrice();
	});

	$(".good_discount").blur("input propertychange", function(){//折扣
		var id = $(this).attr("id").split("-")[1];
		var $good_sum_price = $("#good_sum_price-" + id);
		var $good_discount = $("#good_discount-" + id); // 折扣率
		var $good_discount_price = $("#good_discount_price-" + id);
		
		var discount = (this.value);
		var _reg = /^[\-]?\d*?\.?\d*?$/;	
		if (discount>0) {
			$good_discount.val(0);
			alert("折扣率只能填负数");
			discount = 0; 
		}
		discount = parseFloat(this.value);
		if(isNaN(discount)) discount = 0;
		if(discount > 100) {
			discount = 0;
			this.value = 0;
		}
		
   		var g_sum_price = parseFloat($good_sum_price.val());//金额
   		if(isNaN(g_sum_price)) g_sum_price = 0;

		var g_discount_price = parseFloat($good_discount_price.val());
		if(isNaN(g_discount_price)) g_discount_price = 0;
	
   		var s_discount_price = parseFloat(g_sum_price * discount/100 + g_discount_price);
   		//$good_discount_price.val((discount * good_price/100).toFixed(2));//折扣金额
		   		
   		// 动态计算折扣总金额
   		$("#good_discount_price_all-" + id).text(s_discount_price);
   		//$("#good_discount_price_all_hid-" + id).val(s_discount_price);
   		
   		calcPdNumAndPrice();
	});

	$(".good_discount_price").bind("input propertychange", function(){ // 折扣金额
		var id = $(this).attr("id").split("-")[1];
		//var $good_price = $("#good_price-" + id);
		var $good_discount = $("#good_discount-" + id);
		var $good_discount_price = $("#good_discount_price-" + id);
		var $good_sum_price = $("#good_sum_price-" + id);
		
		var g_discount_price = (this.value);
		var _reg = /^[\-]?\d*?\.?\d*?$/;
		if (!_reg.test(g_discount_price)) {
			$good_discount_price.val(0);
		}
		g_discount_price = parseFloat(this.value);
		if(isNaN(g_discount_price)) g_discount_price = 0;

		var discount = parseFloat($good_discount.val());//折扣率
   		if(isNaN(discount)) discount = 0;

		var g_sum_price = parseFloat($good_sum_price.val());
		if(isNaN(g_sum_price)) g_sum_price = 0;
	
   		var s_discount_price = parseFloat(g_sum_price * discount/100 + g_discount_price);
		
   		//var good_price = parseFloat($good_price.val());//金额
   		//if(isNaN(good_price)) good_price = 0;
	   	
   		//$good_discount.val(good_price == 0 ? '-' : (discount * 100 / good_price).toFixed(2));//折扣率

   		// 动态计算折扣总金额
   		$("#good_discount_price_all-" + id).text(s_discount_price);
   		//$("#good_discount_price_all_hid-" + id).val(s_discount_price);
   		
   		calcPdNumAndPrice();
	});


	$(".good_discount_price").blur("input propertychange", function(){//折扣
		var id = $(this).attr("id").split("-")[1];
		var $good_sum_price = $("#good_sum_price-" + id);
		var $good_discount = $("#good_discount-" + id); // 折扣率
		var $good_discount_price = $("#good_discount_price-" + id);
		
		var good_discount_price = (this.value);
		var _reg = /^[\-]?\d*?\.?\d*?$/;	
		if (good_discount_price>0) {
			$good_discount_price.val(0);
			alert("折扣金额只能填负数");
			good_discount_price = 0; 
		}
		var discount = parseFloat($good_discount.value);
		if(isNaN(discount)) discount = 0;
		if(discount > 100) {
			discount = 0;
			this.value = 0;
		}
   		var g_sum_price = parseFloat($good_sum_price.val());//金额
   		if(isNaN(g_sum_price)) g_sum_price = 0;

		if(isNaN(g_discount_price)) g_discount_price = 0;
	
   		var s_discount_price = parseFloat(g_sum_price * discount/100 + g_discount_price);
   		//$good_discount_price.val((discount * good_price/100).toFixed(2));//折扣金额
		   		
   		// 动态计算折扣总金额
   		$("#good_discount_price_all-" + id).text(s_discount_price);
   		//$("#good_discount_price_all_hid-" + id).val(s_discount_price);
   		
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
	
	$(".good_discount_price_all_hid").each(function(){
		//var id = $(this).attr("id").split("-")[1];
		//var good_count = $("#good_count-" + id).val();
		//if(isNaN(good_count)) good_count = 0;
		
		//var this_value = parseFloat($(this).val());
		//if(isNaN(this_value)) this_value = 0;
		
		var this_value = parseFloat($(this).val());
		if(isNaN(this_value)) this_value = 0;
		
		dd_discount_sum += this_value;
	});
	
	$("#order_num").text(dd_count_sum);//订单总数
	$("#money").text(dd_money_sum.toFixed(2));//订单金额
	$("#good_discount_price").text(dd_discount_sum.toFixed(2));//订单折扣金额
}
//]]></script>
</body>
</html>
