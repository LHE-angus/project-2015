<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
select{font-family:Microsoft YAHEI;font-size:12px;}
input{font-family:Microsoft YAHEI;font-size:12px;}
label {cursor:pointer;}
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
  		<c:if test="${empty u}">
  		<div>
  		<link href="${ctx}/commons/styles/message/message.css" rel="stylesheet" type="text/css" />
  		<table cellpadding="5" width="100%" cellspacing="8px" class="noteMacro" border="0" align="center" style="background-color:#FFFFBB;">
		  <tr>
		    <td width="40" align="center" valign="middle" height="25"><img src="${ctx}/commons/styles/message/images/warning.gif" width="16" height="16" style="vertical-align:middle;" alt="" border="0" /></td>
		    <td><p>业务员 ${customerUserInfo.real_name}，您好！该客户暂没有分配登录用户，无法登录康佳客户端，系统提醒您请尽快分配登录用户并告知。</p></td>
		  </tr>
		</table>
  		</div>
  		</c:if>
	  	<html-el:form action="/admin/JxcKonkaOrderRegister" enctype="multipart/form-data">
			<html-el:hidden property="method" value="save" styleId="method"/>
	      	<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	      	<html-el:hidden property="trade_index" styleId="trade_index" value="${af.map.tradeIndex}" />
	      	<html-el:hidden property="cust_id" styleId="cust_id" value="${cust_id}" />
	      	<html-el:hidden property="pro_ids" styleId="pro_ids"/>
			<html-el:hidden property="pro_names" styleId="pro_names"/>
		    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
		        <tr>
		          <th colspan="4" style="font-size:15px;">订单信息</th>
		        </tr>
		        <tr>
		          <td class="title_item" width="15%"><font color="red">*</font>订单流水号：</td>
		          <td colspan="3"><font color="red">${af.map.tradeIndex}</font></span></td>
		        </tr>
		        <tr>
		          <td class="title_item" width="15%"><font color="red">*</font>订单日期：</td>
		          <td width="35%">
		          	<html-el:text property="order_date" styleId="order_date" size="10" maxlength="9" readonly="true" styleClass="webinput" value="${af.map.today}"/>
		          </td>
		          <td class="title_item" width="15%"><font color="red">*</font>配送方式：</td>
		          <td>
		          <label for="send_type_1"><html-el:radio property="send_type" styleId="send_type_1" value="1" /> 自提</label>
		          <label for="send_type_2"><html-el:radio property="send_type" styleId="send_type_2" value="2" /> 配送</label>
		          </td>
		        </tr>
		        <tr>
		          <td class="title_item"><font color="red">*</font>货款支付方式：</td>
		          <td colspan="3">
		          <label for="pay_type_4"><html-el:radio property="pay_type" styleId="pay_type_4" value="4" /> 现汇</label>
		          <span style="color:#ccc;margin-left:5em;">采用现金汇款或转帐的方式支付货款</span><br />
		          <label for="pay_type_5"><html-el:radio property="pay_type" styleId="pay_type_5" value="5" /> 帐期</label>
		          <span style="color:#ccc;margin-left:5em;">使用您的信用额度余额支付货款</span><br />
		          <label for="pay_type_6"><html-el:radio property="pay_type" styleId="pay_type_6" value="6" /> 承兑</label>
		          <span style="color:#ccc;margin-left:5em;">使用银行提供的承兑支票支付货款</span>
		          </td>
		        </tr>
		        <tr style="display:none;">
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
		          <td colspan="4">
		            <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
		              <tr class="title_top">
		                <td nowrap="nowrap">产品型号</td>
		                <!--<td width="12%" nowrap="nowrap">产品类型</td>
		                -->
		                <td width="8%" nowrap="nowrap">数量</td>
		                <td width="5%" nowrap="nowrap">单位</td>
		                <td width="8%" nowrap="nowrap">单价</td>
		                <td width="10%" nowrap="nowrap">金额</td>
		                <td width="10%" nowrap="nowrap">折扣金额</td>
		                <td width="5%" nowrap="nowrap">折扣(%)</td>
		                <td width="12%" nowrap="nowrap">产品备注</td>
		                <td width="5%" align="center" style="cursor:pointer;" title="添加" onclick="getPePdModel();"><img src="${ctx}/styles/jxc/images/+.gif" style="vertical-align:text-bottom;"/></td>
		              </tr>
		              <tr id="trHidden" style="display:none;">
		                <td align="center">
		                  <html-el:hidden property="pd_ids" styleId="pd_ids" />
		                  <html-el:hidden property="pd_id" styleId="pd_id" />
		                  <html-el:hidden property="md_name" styleId="md_name1" styleClass="md_name1" /> 
		                  <span id="md_name" class="md_name"></span><span id="span_1"></span>
		                </td>
		                <td align="center"><html-el:text property="good_count" styleId="good_count" value="1" size="4" maxlength="4" styleClass="webinput good_count" /></td>
		                <td align="center"><html-el:text property="good_unit" styleId="good_unit" value="台" maxlength="20" size="5" styleClass="webinput" /></td>
		                <td align="center"><html-el:text property="good_price" value="0" styleId="good_price"  maxlength="8" size="8" styleClass="webinput" /></td>
		                <td align="center">
		                  <span id="good_sum_price">0</span>
		                  <html-el:hidden property="sum_price" styleId="sum_price" />
		                </td>
		                <td align="center">
		                  <html-el:text property="good_discount_price" value="0" styleId="good_discount_price"  maxlength="10" size="10" styleClass="webinput" />
		                  <html-el:hidden property="discount_price" styleId="discount_price" />
		                </td>
		                <td align="center"><html-el:text property="good_discount" value="0" styleId="good_discount"  maxlength="5" size="5" styleClass="webinput" /></td>
		                <td align="center"><html-el:text property="pd_remark" size="20" styleId="pd_remark" maxlength="100" styleClass="webinput" /></td>
		                <td align="center" title="删除"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align:text-bottom;"/></td>
		              </tr>
		              <tbody id="tbodyContent">
		              </tbody>
		              <tr class="title_top">
		                <td>合计</td>
		                <td>
		                	<span id="dd_count_sum"></span>
		                  	<html-el:hidden property="order_num" styleId="order_num" />
		                </td>
		                <td>&nbsp;</td>
		                <td>&nbsp;</td>
		                <td>
		                	<span id="dd_money_sum"></span>
		                  	<html-el:hidden property="money_sum" styleId="money_sum" />
		                </td>
		                <td>
		                	<span id="dd_discount_sum"></span>
		                  	<html-el:hidden property="discount_price_sum" styleId="discount_price_sum" />
		                </td>
		                <td>&nbsp;</td>
		                <td>&nbsp;</td>
		                <td>&nbsp;</td>
		              </tr>
		            </table>
		          </td>
		        </tr>
		        <tr>
		          <td class="title_item" width="15%">客户名称：</td>
		          <td width="35%">
		          <c:if test="${empty konkaR3ShopList}">
		          	<html-el:text property="user_shop_name" styleId="user_shop_name" value="${af.map.userShopName}" style="width:90%" maxlength="20" styleClass="webinput"/>
		          </c:if>
		          <c:if test="${not empty konkaR3ShopList}">
			          <html-el:select property="ag">
						<c:forEach var="cur" items="${konkaR3ShopList}" varStatus="vs">
							<html-el:option value="${cur.id}">${cur.customer_name}</html-el:option>
						</c:forEach>
			          </html-el:select>
		          </c:if>
		          </td>
		          <td class="title_item" width="15%">收货人姓名：</td>
		          <td width="35%"><html-el:text property="user_name" styleId="user_name" value="${u.real_name}" style="width:50%" maxlength="20" styleClass="webinput"/></td>
		        </tr>
		        <tr>
		          <td class="title_item" width="15%">收货人固定电话：</td>
		          <td width="35%">
		          	<html-el:text property="user_tel" styleId="user_tel" value="${af.map.userTel}" style="width:40%" maxlength="20" styleClass="webinput"/>
		            <span id="span_msg_tel__error" style="display: none;"><font style="color: red">正确格式,如：0551-3698754</font></span>
		          </td>
		          <td class="title_item" width="15%">收货人手机：</td>
		          <td>
		          	<html-el:text property="user_phone" styleId="user_phone" value="${u.link_phone}" style="width:40%" maxlength="11" styleClass="webinput"/>
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
		          <td colspan="3" width="85%"><html-el:text property="user_addr" styleId="user_addr" value="${u.link_addr}" style="width:92%" maxlength="100" styleClass="webinput"/></td>
		        </tr>
		        <tr>
		          <td class="title_item" width="15%">收货人邮编：</td>
		          <td width="35%"><html-el:text property="user_zip" styleId="user_zip" value="${u.link_post}" style="width:50%" maxlength="6" styleClass="webinput"/></td>
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
		          <td colspan="4">&nbsp;</td>
		        </tr>
		        <tr>
					<td width="8%" align="left" class="title_item">销售凭证类型</td>
					<td width="12%"><select name="doc_type">
						<option value="ZFOR">ZFOR</option>
						<option value="ZFGC">ZFGC</option>
						<option value="ZFRE">ZFRE</option>
						<option value="ZFRC">ZFRC</option>
					</select>
					</td>
					<td width="8%" align="left" class="title_item">销售组织</td>
					<td width="12%"><input type="text" name="sales_org" id="sales_org" value="${sales_org}" readonly="readonly" /></td>
				</tr>
				<tr>
					<td width="8%" colspan="1" align="left" class="title_item">分销渠道</td>
					<td width="12%"><input type="text" name="distr_chan" value="10" readonly="readonly" /></td>
					<td width="8%" align="left" class="title_item">产品组</td>
					<td width="12%"><input type="text" name="division" value="10" readonly="readonly" /></td>
				</tr>
				<tr>
					<td width="8%" align="left" class="title_item">售达方</td>
					<td width="12%">
					<c:if test="${empty agList}"><input type="text" name="ag" value="${ag}" /></c:if>
					<c:if test="${not empty agList}"><html-el:select property="ag">
						<c:forEach var="cur" items="${agList}" varStatus="vs">
							<html-el:option value="${cur.KUNN2}">${cur.KUNN2}</html-el:option>
						</c:forEach>
		            </html-el:select></c:if>
					</td>
					<td width="8%" colspan="1" align="left" class="title_item">出具发票方</td>
					<td width="12%"><c:if test="${empty reList}"><input type="text" name="re" value="${re}" /></c:if>
					<c:if test="${not empty reList}"><html-el:select property="re">
						<c:forEach var="cur" items="${reList}" varStatus="vs">
							<html-el:option value="${cur.KUNN2}">${cur.KUNN2}</html-el:option>
						</c:forEach>
		            </html-el:select></c:if></td>
				</tr>
				<tr>
					<td width="8%" colspan="1" align="left" class="title_item">付款方</td>
					<td width="12%">
					<c:if test="${empty rgList}"><input type="text" name="rg" value="${rg}" /></c:if>
					<c:if test="${not empty rgList}"><html-el:select property="rg">
						<c:forEach var="cur" items="${rgList}" varStatus="vs">
							<html-el:option value="${cur.KUNN2}">${cur.KUNN2}</html-el:option>
						</c:forEach>
		            </html-el:select></c:if>
		            </td>
					<td width="8%" colspan="1" align="left" class="title_item">送达方</td>
					<td width="12%">
					<c:if test="${empty weList}"><input type="text" name="we" value="${we}" /></c:if>
					<c:if test="${not empty weList}"><html-el:select property="we">
						<c:forEach var="cur" items="${weList}" varStatus="vs">
							<html-el:option value="${cur.KUNN2}">${cur.KUNN2}</html-el:option>
						</c:forEach>
		            </html-el:select></c:if>
					</td>
				</tr>
		        <tr>
		          <td colspan="4" align="center"><input type="button" name="save" class="bgButtonSave" value="提交" id="btn_submit"/>
		            <input type="button" name="temp_save" class="bgButtonSave" value="暂存" id="btn_temp_save"/>
		            <input class="bgButtonBack" type="reset" name="reset" value="重填" id="btn_reset"/>
		            <c:if test="${call_r3_interface}"><input type="button" value="库存检查" id="btn_check" /></c:if>
		          </td>
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
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=jtop"></script> 
<script type="text/javascript">//<![CDATA[

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
	$("#pay_type_6").attr("dataType","Group").attr("msg","至少选择一种货款支付方式！");
	$("#send_type_2").attr("dataType","Group").attr("msg","至少选择一种配送方式！");
	//所在地市联动
	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${af.map.province}"});
	$("#city").attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map.city}"});
	$("#country" ).attr({"defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${af.map.country}"});
	$("#province").cs("${ctx}/jxc/CsAjax.do?method=getBaseProvinceList", "p_index", "0", false);

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
		if ($("#pd_id", "#tbodyContent").length == 0) {
	        alert("请至少添加一个订单产品信息！");
	        return false;
		}
		var notSelectedPd = false;
		$("#pd_id", "#tbodyContent").each(function(){
			if ("" == $.trim(this.value)){
				notSelectedPd = true;
			}
		});
		if (notSelectedPd) {
	        alert("您还没有选择产品，请选择！");
	        return false;
		}
		
	    if (judgeSelectedValueIsRepeat("pd_id", $("#tbodyContent"))) {
	        alert("您选择的产品型号有重复，请重新选择！");
	        return false;
	    }

		if (Validator.Validate(this.form, 1)){
			if($("#country").val() != ""){
				$("#user_p_index").val($("#country").val());
			}else{
				if($("#city").val() != ""){
					$("#user_p_index").val($("#city").val());
				}else{
					if($("#province").val() != ""){
						$("#user_p_index").val($("#province").val());
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
		if ($("#pd_id", "#tbodyContent").length == 0) {
	        alert("请至少添加一个订单产品信息！");
	        return false;
		}
		var notSelectedPd = false;
		$("#pd_id", "#tbodyContent").each(function(){
			if ("" == $.trim(this.value)){
				notSelectedPd = true;
			}
		});
		if (notSelectedPd) {
	        alert("您还没有选择产品，请选择！");
	        return false;
		}
		
	    if (judgeSelectedValueIsRepeat("pd_id", $("#tbodyContent"))) {
	        alert("您选择的产品型号有重复，请重新选择！");
	        return false;
	    }

		if (Validator.Validate(this.form, 3)){
			if($("#country").val() != ""){
				$("#user_p_index").val($("#country").val());
			}else{
				if($("#city").val() != ""){
					$("#user_p_index").val($("#city").val());
				}else{
					if($("#province").val() != ""){
						$("#user_p_index").val($("#province").val());
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
	
	//检查库存
	$("#btn_check").click(function(){
		
		var sales_org = $("#sales_org").val();
		var md_names = [];
		var good_counts = [];
		
		$(".md_name").each(function(){
			md_names[md_names.length] = $.trim($(this).text());
		});
		
		$(".good_count").each(function(){
			good_counts[good_counts.length] = $.trim($(this).val());
		});
		
		md_names.shift();
		good_counts.shift();
		
		//console.info("sales_org : ", sales_org);
		//console.info("md_names : ", md_names);
		//console.info("good_counts : ", good_counts);
		
		/// ajax begin 
		$.ajax({
			type: "POST",
			url: "${ctx}/manager/admin/JxcKonkaOrderRegister.do?method=checkStockForAjax",
			data: {"sales_org" : sales_org, "md_name" : md_names.join(','), "good_count" : good_counts.join(',')},
			dataType: "json",
			sync: true, 
			error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
			success: function(result) {
				if(result == null){
					alert("库存检查发生异常！");
					return;
				}
				////显示
				//[{"amount":1.000,"bukrs":"KF28","isOk":0,"lamount":0,"lgort":"9028","matnr":"LCM17BT35H","werks":"L00A"}]
				//[{"amount":1.000,"bukrs":"KF28","isOk":0,"lamount":0,"lgort":"9028","matnr":"LCM17BT35H","werks":"L00A"}]
				//alert(result.length);
				var ispass = 1;
				for(var i = 0 ;i< result.length ;i++){
					if(result[i].isOk == 0){
							$("#"+result[i].matnr).text("  库存不足!").css("color","red");
							ispass = ispass * 0;	
					}else{
						$("#"+result[i].matnr).text("  有库存!").css("color","green");
					}
				}
				
				if(ispass != 1) {
					$("#btn_submit").attr("disabled", true);
				} else {
					$("#btn_submit").removeAttr("disabled");
				}
				
			}
		}); /// ajax end 
	});//检查库存 end 
	
	
});//ready end
	/*处理返回值数据 ===start===*/

function resizeFrameHeight(offset, min_height) {
	// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}

function getPePdModel() { 
	//var returnValue = window.showModalDialog("${ctx}/jxcnokey/SelectPePdModel.do?selectype=mutiple&selects=" + $("#pd_ids").val() + "&azaz=" + Math.random(),window,"dialogWidth:600px;status:no;dialogHeight:370px");
    
	//if (returnValue != null && returnValue.ids != "") {
		$.dialog({
		title:  "产品列表",
		width:  400,
		height: 390,
        lock:true ,
		content:"url:${ctx}/customer/manager/SelectPePdModel.do?method=newList&selectype=mutiple&selects=" + $("#pd_ids").val() + "&azaz=" + Math.random()
	});
}

//取得窗口选择的产品并回显
function getProInfo(){
	var ids = $("#pro_ids").val();
	var names = $("#pro_names").val();
	if (ids != "") {
		//前一次操作返回的值  + 当前操作返回的值 = 当前的值
		var pd_ids_temp = "";
		if("" != $("#pd_ids").val()){
			pd_ids_temp = $("#pd_ids").val()+ "," + ids ;
		}else{
			pd_ids_temp = ids ;
		}
	    $("#pd_ids").val(pd_ids_temp);
	    
	    var pd_id_array = new Array();
	    var md_name_array = new Array();
	
	    pd_id_array = ids.split(",");
	    md_name_array = names.split(",");
	    
		for(var i = 0; i < pd_id_array.length; i++){

			$("#trHidden").clone().appendTo($("#tbodyContent")).show();
	    	var lastTR = $("tr:last", "#tbodyContent");
	    	
	    	var pd_id = $("#pd_id", lastTR);//产品型号id
	    	var md_name = $("#md_name", lastTR);//隐藏域产品型号名称
	    	var span_1 = $("#span_1", lastTR);//隐藏域产品型号名称
	    	var md_name1 = $("#md_name1", lastTR);//产品编号
	    	
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
			
			pd_id.val(pd_id_array[i]);//隐藏域选择的产品
	      	md_name.text(md_name_array[i]);
	      	md_name1.val(md_name_array[i]);
	      	
	      	
			good_count.val("1");
			good_unit.val("台");
			
			good_price.val("0");
			
			span_1.attr("id",md_name_array[i]);//改变id
			
			good_sum_price.text("0");
			sum_price.val("0");
			
			good_discount.val("0");
			good_discount_price.val("0");

			pd_remark.val("");

			calcOneCountAndJhPrice(good_count, good_price, good_sum_price, sum_price);
			good_discount_price.val(parseFloat(good_sum_price.text())*parseFloat(good_discount.val())/100);//折扣金额
			discount_price.val(parseFloat(good_sum_price.text())*parseFloat(good_discount.val())/100);//隐藏域折扣金额
			calcPdNumAndPrice("tbodyContent");

			bindJhCountAndJhPrice(good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price, lastTR);

			//删除操作，影响弹出页面的返回值
			$("td:last", lastTR).click(function (){
				
				var pd_id_td1 = $("#pd_id", $(this).parent()).val()+",";
				var pd_id_td2 = ","+$("#pd_id", $(this).parent()).val();
				var pd_ids_new1 = $("#pd_ids").val().replace(pd_id_td1,"");
				pd_ids_new2 = pd_ids_new1.replace(pd_id_td2,"");
				
				$("#pd_ids").val(pd_ids_new2);//删除掉的id，得到新的已经选择的id数组
				
		    	$(this).parent().remove();
		    	 calcPdNumAndPrice("tbodyContent");
		    }).css("cursor", "pointer");
		}
		resizeFrameHeight(2);
	}
}
	


/*处理返回值数据 ===end===*/

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
   		//good_discount_price.val((count * price * discount/100).toFixed(2));//折扣金额
   		//discount_price.val((count * price * discount/100).toFixed(2));//隐藏域折扣金额
   		
   		dis_money = good_discount_price.val();
   		if(isNaN(dis_money)) dis_money = 0;
   		good_discount.val((count * price) == 0 ? '-' : (dis_money * 100 / (count * price)).toFixed(2));//折扣率
   		
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
   		//good_discount_price.val((count * price * discount/100).toFixed(2));//折扣金额
   		//discount_price.val((count * price * discount/100).toFixed(2));//隐藏域折扣金额
   		
   		dis_money = good_discount_price.val();
   		if(isNaN(dis_money)) dis_money = 0;
   		good_discount.val((count * price) == 0 ? '-' : (dis_money * 100 / (count * price)).toFixed(2));//折扣率
   		
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
			this.value = 0;
		}
   		var sum_price = parseFloat(good_sum_price.text());//金额
   		if(isNaN(sum_price)) sum_price = 0;
	   	
   		good_discount_price.val((discount * sum_price/100).toFixed(2));//折扣金额
   		discount_price.val((discount * sum_price/100).toFixed(2));//隐藏域折扣金额
		calcPdNumAndPrice("tbodyContent");
	});
	
	// 下面的代码是xxd新增，为放开可修改折扣金额输入框 add by xxd @20130702
	good_discount_price.keyup(function(){ // 折扣金额
		var discount = (this.value);
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(discount)) {
			good_discount_price.val(0);
			discount = 0;
		}
		discount = parseFloat(this.value);
		if(isNaN(discount)) discount = 0;
   		var sum_price = parseFloat(good_sum_price.text());//金额
   		if(isNaN(sum_price)) sum_price = 0;
	   	
   		good_discount.val(sum_price == 0 ? '-' : (discount * 100 / sum_price).toFixed(2));//折扣率
		calcPdNumAndPrice("tbodyContent");
	});
	// end. add by xxd @20130702
	
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

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>