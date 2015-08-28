<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单登记修改</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：订单管理 &gt; 订单登记 &gt;修改</div>
  <div class="rtabcont1">
    <html-el:form action="/JxcKonkaOrderRegister">
      <html-el:hidden property="method" value="save" styleId="method"/>
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <input type="hidden" name="keySeq" id="keySeq" value="${af.map.keySeq}" />
      <input type="hidden" name="order_id" id="order_id" value="${af.map.order_id}" />
      <html-el:hidden property="trade_index" styleId="trade_index" value="${af.map.tradeIndex}" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="4">订单信息</th>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>订单流水号：</td>
          <td colspan="3"><font color="red">${af.map.tradeIndex}</font></span></td>
        </tr>
        <tr>
          <td class="title_item" width="15%"><font color="red">*</font>订单日期：</td>
          <td width="35%"><html-el:text property="order_date" styleId="order_date" size="9" maxlength="9" readonly="true" styleClass="webinput" value="${af.map.today}"/></td>
          <td class="title_item" width="15%"><font color="red">*</font>配送方式：</td>
          <td>
          	<html-el:select property="send_type" styleId="send_type">
          		<html-el:option value="">请选择...</html-el:option>
          		<html-el:option value="1">自提</html-el:option>
          		<html-el:option value="2">配送</html-el:option>
          	</html-el:select>
          </td>
        </tr>
         <tr>
          <td class="title_item"><font color="red">*</font>货款支付方式：</td>
          <td colspan="3">
          	<html-el:select property="pay_type" styleId="pay_type">
          		<html-el:option value="">请选择...</html-el:option>
          		<html-el:option value="4">现款</html-el:option>
          		<html-el:option value="5">担保</html-el:option>
          	</html-el:select>
          </td>
        </tr>
        <tr>
          <td class="title_item"> 运费：</td>
          <td colspan="3">${fn:escapeXml(af.map.freight)}</td>
        </tr>
        <tr>
          <td class="title_item"> 备注：</td>
          <td colspan="3"><html-el:text styleId="remark" property="remark" maxlength="120" styleClass="webinput" style="width:80%"/></td>
        </tr>
        
        <tr>
          <th colspan="4">订单产品信息</th>
        </tr>
        <tr>
          <td colspan="4"><table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
              <tr class="title_top">
                <td nowrap="nowrap">产品型号</td>
                <!--<td width="12%" nowrap="nowrap">产品类型</td>
                -->
                <td width="8%" nowrap="nowrap">数量</td>
                <td width="5%" nowrap="nowrap">单位</td>
                <td width="8%" nowrap="nowrap">单价</td>
                <td width="10%" nowrap="nowrap">金额</td>
                <td width="5%" nowrap="nowrap">折扣(%)</td>
                <td width="10%" nowrap="nowrap">折扣金额</td>
                <td width="12%" nowrap="nowrap">产品备注</td>
                <td width="5%" align="center" id="tdAdd" title="添加"><img src="${ctx}/styles/jxc/images/+.gif" style="vertical-align:text-bottom;"/></td>
              </tr>
              
              <tr id="trHidden" style="display:none;">
                <td align="left">
                  <html-el:hidden property="selectValue" styleId="selectValue" />
                  <html-el:hidden property="pd_ids" styleId="pd_ids" />
                  <html-el:select property="jxc_pd_ids" styleId="jxc_pd_ids">
                    <html-el:option value="">请选择...</html-el:option>
                    <c:forEach var="cur" items="${pdList}" varStatus="vs">
          				<html-el:option value="${cur.pd_id}@#${cur.cls_id}@#${cur.map.cls_name}@#${cur.brand_id}@#${cur.map.brand_name}@#${cur.md_name}">${cur.md_name}</html-el:option>
        			</c:forEach>
                  </html-el:select>
                </td>
                <!--<td align="center"><span id="good_pd_type_name"></span><html-el:hidden property="pd_type_name" styleId="pd_type_name" /></td>
                -->
                <td align="center"><html-el:hidden property="pd_id" styleId="pd_id" />
                <html-el:hidden property="cls_id" styleId="cls_id" />
                <html-el:hidden property="brand_id" styleId="brand_id" />
                <html-el:hidden property="md_name" styleId="md_name" />
                <html-el:text property="good_count" styleId="good_count" value="1" size="4" maxlength="4" styleClass="webinput" /></td>
                <td align="center"><html-el:text property="good_unit" styleId="good_unit" value="台" maxlength="20" size="5" styleClass="webinput" /></td>
                <td align="center"><html-el:text property="good_price" value="0" styleId="good_price"  maxlength="8" size="8" styleClass="webinput" /></td>
                <td align="center"><span id="good_sum_price">0.00</span><html-el:hidden property="sum_price" styleId="sum_price" /></td>
                <td align="center"><html-el:text property="good_discount" value="0" styleId="good_discount"  maxlength="5" size="5" styleClass="webinput" /></td>
                <td align="center"><span id="good_discount_price">0.00</span><html-el:hidden property="discount_price" styleId="discount_price" /></td>
                <td align="center"><html-el:text property="pd_remark" size="20" styleId="pd_remark" maxlength="100" styleClass="webinput" /></td>
                <td align="center" title="删除"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align:text-bottom;"/></td>
              </tr>
              <tbody id="tbodyContent">
              </tbody>
              <tr class="title_top">
                <td>合计</td>
                <td></td>
                <td><span id="dd_count_sum"></span><html-el:hidden property="order_num" styleId="order_num" /></td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td><span id="dd_money_sum"></span><html-el:hidden property="money_sum" styleId="money_sum" /></td>
                <td>&nbsp;</td>
                <td><span id="dd_discount_sum"></span><html-el:hidden property="discount_price_sum" styleId="discount_price_sum" /></td>
                <td>&nbsp;</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td class="title_item" width="15%">收货人商铺名称：</td>
          <td width="35%"><html-el:text property="user_shop_name" styleId="user_shop_name" value="${af.map.userShopName}" style="width:90%" maxlength="20" styleClass="webinput"/></td>
          <td class="title_item" width="15%">收货人姓名：</td>
          <td width="35%"><html-el:text property="user_name" styleId="user_name" value="${af.map.userName}" style="width:50%" maxlength="20" styleClass="webinput"/></td>
        </tr>
        <tr>
          <td class="title_item" width="15%">收货人固定电话：</td>
          <td width="35%">
          	<html-el:text property="user_tel" styleId="user_tel" value="${af.map.userTel}" style="width:40%" maxlength="20" styleClass="webinput"/>
          	<span id="span_msg_tel__error" style="display: none;"><font style="color: red">正确格式,如：0551-3698754</font></span>
          </td>
          <td class="title_item" width="15%">收货人手机：</td>
          <td>
          	<html-el:text property="user_phone" styleId="user_phone" style="width:40%" maxlength="11" styleClass="webinput"/>
          	<span id="span_msg_phone__error" style="display: none;"><font style="color: red">正确格式,如：13966557733</font></span>
          </td>
        </tr>
        <tr>
          <td class="title_item" width="15%">收货人所属地区：</td>
          <td colspan="3" width="85%">
          	<html-el:hidden property="user_p_index" styleId="user_p_index" />
          	<select name="province" id="province" style="width:175px;">
	            <option value="${af.map.province}">请选择...</option>
	          </select>
	          &nbsp;
	          <select name="city" id="city" style="width:157px;">
	            <option value="${af.map.city}">请选择...</option>
	          </select>
	          &nbsp;
	          <select name="country" id="country" style="width:157px;">
	            <option value="${af.map.country}">请选择...</option>
	          </select>
          </td>
        </tr>
        <tr>
          <td class="title_item" width="15%">收货人地址：</td>
          <td colspan="3" width="85%"><html-el:text property="user_addr" styleId="user_addr" value="${af.map.userAddr}" style="width:92%" maxlength="100" styleClass="webinput"/></td>
        </tr>
        <tr>
          <td class="title_item" width="15%">收货人邮编：</td>
          <td width="35%"><html-el:text property="user_zip" styleId="user_zip" value="${af.map.userZip}" style="width:50%" maxlength="6" styleClass="webinput"/></td>
          <td class="title_item" width="15%">收货人备注：</td>
          <td width="35%"><html-el:text property="user_remark" styleId="user_remark" style="width:80%" maxlength="100" styleClass="webinput"/></td>
        </tr>
        
        <tr>
          <td class="title_item">应付金额：￥</td>
          <td colspan="3"><html-el:text styleId="pay_money" property="pay_money" readonly="true" styleClass="webinput"/></td>
          <!--<td class="title_item"><font color="red">*</font>本次付款：￥</td>
          <td><html-el:text styleId="money" property="money" maxlength="12" styleClass="webinput"/></td>
        	-->
        </tr>
        <tr>
          <td colspan="4" align="center"><input type="button" name="save" class="bgButtonSave" value=" 提 交 " id="btn_submit"/>
          <input type="button" name="temp_save" class="bgButtonSave" value=" 暂 存 " id="btn_temp_save"/>
            <input class="bgButtonBack" type="reset" name="reset" value="重填" id="btn_reset"/>
            <html-el:button styleClass="bgButtonBack" property="" styleId="btn_back" value="返回 " onclick="history.back();" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script> 
<script type="text/javascript"><!--//<![CDATA[

var f = document.forms[0];
$(document).ready(function(){
	//$("#money").attr("dataType","Double").attr("msg","请填本次付款，且必须为实数！");
	$("#user_zip").keyup(function(){//邮编必须为数字
		var _reg = /^\d+$/;
		var user_zip =(this.value);
   		if (!_reg.test(user_zip)) {
   			$("#user_zip").val("");
   		}
	});
	$("#pay_type").attr("dataType","Require").attr("msg","请选择货款支付方式！");
	$("#send_type").attr("dataType","Require").attr("msg","请选择配送方式！");
	//所在地市联动
	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${af.map.province}"});
	$("#city").attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map.city}"});
	$("#country" ).attr({"defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${af.map.country}"});
	$("#province").cs("${ctx}/jxc/CsAjax.do?method=getBaseProvinceList", "p_index", "0", false);

	$("#btn_reset").click(function(){
		//重填
		$("tr", $("#tbodyContent")).each(function(){
			$(this).remove();
	   		calcPdNumAndPrice("tbodyContent");
	});

	});
	// 暂存表单(验证价格是否为负和字段是否超过长度)
	$("#btn_temp_save").click(function(){
		if ($("#user_tel").val() != "") {
			var phone=/^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/;
			if (phone.exec($("#user_tel").val())){
				$("#span_msg_tel__error").hide();
			}else{
				$("#span_msg_tel__error").show();
				return false;
			}
		}
		if ($("#user_phone").val() != "") {
			var mobile=/^((\(\d{2,3}\))|(\d{3}\-))?(1[3458]\d{9})$/;
			if (mobile.exec($("#user_phone").val())){
				$("#span_msg_phone__error").hide();
			}else{
				$("#span_msg_phone__error").show();
				return false;
			}
		}
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

		if (Validator.Validate(this.form, 1)){
			if($("#country").val() != ""){
				$("#user_p_index").val($("#country").val());
			}else{
				if($("#city").val() != ""){
					$("#user_p_index").val($("#city").val());alert(2);
				}else{
					if($("#province").val() != ""){
						$("#user_p_index").val($("#province").val());alert(1);
					}
				}
			}
			if (${confirm == 1}) {
				if (!confirm("没有找到上级分公司，订单将会丢失无法审核，确定要提交吗？（提示：您可以联系管理员进行客户分配后再登记）")) {
					return false;
				}
			}
			//将方法改为 tempSave
			$("#method").val("tempSave");
			$("#btn_submit").attr("value", "提交中").attr("disabled", "true");
	        $("#btn_reset").attr("disabled", "true");
			f.submit();
		}
	});
	// 提交表单(验证价格是否为负和字段是否超过长度)
	$("#btn_submit").click(function(){
		if ($("#user_tel").val() != "") {
			var phone=/^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/;
			if (phone.exec($("#user_tel").val())){
				$("#span_msg_tel__error").hide();
			}else{
				$("#span_msg_tel__error").show();
				return false;
			}
		}
		if ($("#user_phone").val() != "") {
			var mobile=/^((\(\d{2,3}\))|(\d{3}\-))?(1[3458]\d{9})$/;
			if (mobile.exec($("#user_phone").val())){
				$("#span_msg_phone__error").hide();
			}else{
				$("#span_msg_phone__error").show();
				return false;
			}
		}
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

		if (Validator.Validate(this.form, 1)){
			if($("#country").val() != ""){
				$("#user_p_index").val($("#country").val());
			}else{
				if($("#city").val() != ""){
					$("#user_p_index").val($("#city").val());alert(2);
				}else{
					if($("#province").val() != ""){
						$("#user_p_index").val($("#province").val());alert(1);
					}
				}
			}
			if (${confirm == 1}) {
				if (!confirm("没有找到上级分公司，订单将会丢失无法审核，确定要提交吗？（提示：您可以联系管理员进行客户分配后再登记）")) {
					return false;
				}
			}
			$("#btn_submit").attr("value", "提交中").attr("disabled", "true");
	        $("#btn_reset").attr("disabled", "true");
			f.submit();
		}
	});
	  
	/*处理回显订单详细数据 ===start===*/
	
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
	      	
			var good_count = $("#good_count", lastTR);//数量
			var good_unit = $("#good_unit", lastTR);//单位
			var good_price = $("#good_price", lastTR);//单价
			
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
			
			bindJhCountAndJhPrice(good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price, lastTR);
			$("td:last", lastTR).click(function (){
		    	$(this).parent().remove();
		    	 calcPdNumAndPrice("tbodyContent");
		    }).css("cursor", "pointer");
	    </c:forEach>
	  </c:if>
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
    	
    	//var good_pd_type_name = $("#good_pd_type_name", lastTR);//产品类型
    	//var pd_type_name = $("#pd_type_name", lastTR);//隐藏域产品类型
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

		bindJhCountAndJhPrice(good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price, lastTR);
		
		$("#jxc_pd_ids", lastTR).multiselect({
			selectedList: 1,
			multiple: false,
			minWidth:250,
			click: function(event, ui){
			   if (ui.value == "") {
				    $("#selectValue", lastTR).val("");
					$("#pd_ids", lastTR).val("");//隐藏域选择的产品
					//good_pd_type_name.text("");
					pd_id.val("");
			      	cls_id.val("");
			      	brand_id.val("");
			      	md_name.val("");
			      	
					//pd_type_name.val("");
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
				//$("#thInfo", lastTR).remove();
				$("#selectValue", lastTR).val(ui.value);
				//${cur.pd_id}@#${cur.cls_id}@#${cur.map.cls_name}@#${cur.brand_id}@#${cur.map.brand_name}@#${cur.md_name}
				$("#pd_ids", lastTR).val(s[0]);//隐藏域选择的产品
			    if (judgeSelectedValueIsRepeat("pd_ids", $("#tbodyContent"))) {
			       alert("产品型号有重复，请合并或者重新选择！");
			       return false;
			    }
			    pd_id.val(s[0]);
			    cls_id.val(s[1]);
			    brand_id.val(s[3]);
			    
				//good_pd_type_name.text(s[1]);
				//pd_type_name.val(s[1]);
				//good_unit.val(s[3]);
				//good_price.val(s[5]);
				calcOneCountAndJhPrice(good_count, good_price, good_sum_price, sum_price);
				good_discount_price.text(parseFloat(good_sum_price.text())*parseFloat(good_discount.val())/100);//折扣金额
				discount_price.val(parseFloat(good_sum_price.text())*parseFloat(good_discount.val())/100);//隐藏域折扣金额
				calcPdNumAndPrice("tbodyContent");
		       }
		   	}
		}).multiselectfilter({width:140});
		
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

//输入数量和台数的绑定计算函数
function bindJhCountAndJhPrice(good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price, lastTR) {
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
//]]>--></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
