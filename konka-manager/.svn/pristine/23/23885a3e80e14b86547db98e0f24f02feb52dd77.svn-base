<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>进货管理 &gt; 进货登记</title>
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
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：进货管理 &gt; 进货登记</div>
  <div class="rtabcont1">
    <html-el:form action="/JxcStockBill">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <input type="hidden" name="keySeq" id="keySeq" value="${af.map.keySeq}" />
      <html-el:hidden property="p_index" styleId="p_index" />
      <html-el:hidden property="sn" styleId="sn" value="${sn}" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="4">进货登记</th>
        </tr>
        <tr>
          <td class="title_item" width="15%"><font color="red">*</font>进货日期：</td>
          <td width="35%"><html-el:text property="in_date" styleId="in_date" size="9" maxlength="9" readonly="true" styleClass="webinput" value="${today}" onclick="new Calendar(2011, 2031, 0).show(this);" /></td>
          <td class="title_item" width="15%"><font color="red">*</font>进货编号：</td>
          <td><span><font color="red">NO:</font><font color="red">${sn}</font></span></td>
        </tr>
        <tr id="supplier">
          <td class="title_item"><span><font style="color: red">*</font></span>供应商：</td>
          <td colspan="3"><html-el:select property="jxc_supplier_id" styleId="jxc_supplier_id" style="width:170px">
              <option value="">请选择供应商</option>
              <c:forEach items="${listJxcSupplier}" var="cur">
                <option value="${cur.id}">${cur.name}</option>
              </c:forEach>
            </html-el:select>
            &nbsp;<span style="cursor:pointer;" class="jxcTip" onclick="gotoSupplierApply()">如果没有可选的供应商，请点击
            <input type="button" name="supplierAdd" class="bgButtonAdd" value="新增"/>
            </span></td>
        </tr>
        <tr class="title_top">
          <th colspan="4" align="center">产品信息</th>
        </tr>
        <tr>
          <td width="15%" class="title_item"><font color="red">*</font>所属系统：</td>
          <td colspan="3"><label for="own_sys_0">
              <html-el:radio property="own_sys" styleId="own_sys_0" value="0">非家电下乡</html-el:radio>
            </label>
            &nbsp;
            <label for="own_sys_1">
              <html-el:radio property="own_sys" styleId="own_sys_1" value="1" >家电下乡</html-el:radio>
            </label>
            <span id="own_sys_info" style="padding:5px;color:#f00;">
            <c:if test="${empty af.map.id}">说明：该产品为[有标识卡号]的家电下乡产品</c:if>
            </span></td>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>产品类型：</td>
          <td colspan="3"><html-el:select property="pd_type" styleId="pd_type" style="width:170px">
              <c:forEach items="${basePdTypeList}" var="cur">
                <html-el:option value="${cur.pd_type}">${cur.pd_name}</html-el:option>
              </c:forEach>
              <html-el:option value="0">其他</html-el:option>
            </html-el:select>
            <span id="otherPdType" style="display:none">
            <html-el:select property="jxc_pd_type_id" styleId="jxc_pd_type_id" styleClass="bdfont" style="width:170px">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach items="${JxcPdTypeList}" var="cur">
                <html-el:option value="${cur.id}">${cur.name}</html-el:option>
              </c:forEach>
            </html-el:select>
            &nbsp; <span style="color:#0066FF;cursor:pointer;" id="addOtherPdTypeSpan">
            <input type="button" name="jxcPdTypeAdd" class="bgButtonAdd" value="新增"/>
            </span> </span></td>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>产品品牌：</td>
          <td colspan="3"><html-el:text title="点击选择产品品牌" styleClass="webinput" property="brand_name" styleId="brand_name" style="cursor:pointer" readonly="true" onclick="openChild()" />
            <html-el:hidden property="brand_id" styleId="brand_id" />
            &nbsp;<span style="cursor:pointer;" class="jxcTip" onclick="gotoJxcBrandApply()">如果没有可用品牌，请点击 &nbsp;
            <input type="button" name="brandAdd" class="bgButtonAdd" value="新增"/>
            </span></td>
        </tr>
        <tr>
          <th colspan="4" align="center">进货信息</th>
        </tr>
        <tr>
          <td colspan="4"><table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
              <tr class="title_top">
                <td width="25%" nowrap="nowrap">常用产品</td>
                <td width="30%" nowrap="nowrap">全部产品</td>
                <td width="8%" nowrap="nowrap">期初库存</td>
                <td width="8%" nowrap="nowrap">进货数量</td>
                <td width="8%" nowrap="nowrap">进货单价</td>
                <td width="8%" nowrap="nowrap">进货金额</td>
                <td width="8%" nowrap="nowrap">备注</td>
                <td width="5%" align="center" id="tdAdd" title="添加"><img src="${ctx}/styles/jxc/images/+.gif" style="vertical-align:text-bottom;"/></td>
              </tr>
              <tr id="trHidden" style="display:none;">
                <td align="left"><html-el:hidden property="pd_ids" styleId="pd_ids" />
                  <html-el:hidden property="max_pd_nums" styleId="max_pd_nums"/>
                  <html-el:hidden property="units" value="台"/>
                  <html-el:select property="jxc_pd_ids" styleId="jxc_pd_ids">
                    <html-el:option value="">请选择...</html-el:option>
                  </html-el:select>
                  <span title="如果找不到型号，请点击" class="notFindPd" id="notFindPd" style="cursor: pointer;"> <img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:text-bottom;"/></span></td>
                <td align="left"><html-el:select property="mmt_pd_ids" styleId="mmt_pd_ids">
                    <html-el:option value="">请选择...</html-el:option>
                  </html-el:select></td>
                <td align="center"><html-el:text property="init_counts" styleId="init_counts" value="0" size="4" maxlength="8" styleClass="webinput" /></td>
                <td align="center"><html-el:text property="jh_counts" styleId="jh_counts"  maxlength="8" size="4" value="1" styleClass="webinput" /></td>
                <td align="center"><html-el:text property="jh_prices" value="0" styleId="jh_prices"  maxlength="8" size="4" styleClass="webinput" /></td>
                <td align="center"><span id="jh_moneys"></span></td>
                <td align="center"><html-el:text property="remarks" size="4" styleId="remarks" maxlength="100" styleClass="webinput" /></td>
                <td align="center" title="删除"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align:text-bottom;"/></td>
              </tr>
              <tbody id="tbodyContent">
              </tbody>
              <tr class="title_top">
                <td>合计</td>
                <td colspan="2"><font id="kcinfo" style="color: red;"></font></td>
                <td><span id="jh_count_sum"></span></td>
                <td>&nbsp;</td>
                <td><span id="jh_money_sum"></span></td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td class="title_item"> 说明：</td>
          <td><html-el:text styleId="stock_remarks" property="stock_remarks" maxlength="120" styleClass="webinput" style="width:80%"/></td>
          <td class="title_item">经办人：</td>
          <td><html-el:text styleId="opr_man" property="opr_man" value="${userName}" maxlength="20" styleClass="webinput"/></td>
        </tr>
        <tr>
          <td class="title_item">应付金额：￥</td>
          <td><html-el:text styleId="pay_money" property="pay_money" readonly="true" styleClass="webinput"/></td>
          <td class="title_item"><font color="red">*</font>本次付款：￥</td>
          <td><html-el:text styleId="paid_money" property="paid_money" maxlength="12" styleClass="webinput"/></td>
        </tr>
        <tr>
          <td colspan="4" align="center"><input type="button" name="save" class="bgButtonSave" value=" 保 存 " id="btn_submit"/>
            <input class="bgButtonBack" type="reset" name="reset" value="重填" id="btn_reset"/></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <!-- 弹出层部分 start -->
  <div id="addOtherPdTypeDiv" style="display:none;" title="添加其他产品类型">
    <div align="left"> 产品类型名称：&nbsp;
      <input type="text" name="jxc_pd_type_name" id="jxc_pd_type_name" style="width:100px;" maxlength="10"/>
    </div>
    <div align="right" style="padding: 5px;">
      <input type="button" name="addOtherPdTypeButton" class="bgButton" value="添加" onclick="ajaxAddOtherPd(this);" />
    </div>
  </div>
  <div id="addOtherJxcPdDiv" style="display:none;" title="添加常用产品型号">
    <div align="left"> 产品类型：&nbsp; <span id="pd_type_name_readonly"></span> </div>
    <div align="left"> 产品品牌：&nbsp; <span id="brand_name_readonly"></span> </div>
    <div align="left"> 型号名称：&nbsp;
      <input type="text" name="new_pd_name" class="webinput" id="new_pd_name" style="width:120px;" maxlength="60"/>
    </div>
  </div>
  <div id="addJxcSupplierDiv" style="display:none;" title="添加供应商">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
      <tr>
        <td class="title_item"><font color="red">*</font> 供应商名称：</td>
        <td align="left"><input type="text" name="supplier_name" id="supplier_name" class="webinput" maxlength="200"/></td>
      </tr>
      <tr>
        <td class="title_item"><span><font style="color: red">*</font></span>联系人姓名：</td>
        <td><input type="text" name="link_man" id="link_man" class="webinput" maxlength="120"/></td>
      </tr>
      <tr>
        <td class="title_item"><span><font style="color: red">*</font></span>联系人电话：</td>
        <td><input type="text" name="tel" id="tel" class="webinput" maxlength="20"/>
          <span id="span_msg_tel__error" style="display: none;"><font style="color: red">请填写正确的联系电话,如：0551-3698754或者13966557733</font></span></td>
      </tr>
      <tr>
        <td class="title_item">传真：</td>
        <td><input type="text" name="fax" id="fax" class="webinput" maxlength="20"/></td>
      </tr>
      <tr>
        <td class="title_item">QQ号码：</td>
        <td><input type="text" name="qq" id="qq" class="webinput" maxlength="20"/></td>
      </tr>
      <tr>
        <td class="title_item">电子邮箱：</td>
        <td><input type="text" name="e_mail" id="e_mail" class="webinput" maxlength="40"/></td>
      </tr>
      <tr>
        <td class="title_item">邮政编码：</td>
        <td><input type="text" name="post" id="post" class="webinput" maxlength="10"/></td>
      </tr>
      <tr>
        <td class="title_item">街道地址：</td>
        <td><input type="text" name="addr" id="addr" class="webinput" maxlength="60"/></td>
      </tr>
      <tr>
        <td class="title_item">备注：</td>
        <td><textarea class="webtextarea" name="supplier_remarks" id="supplier_remarks"></textarea></td>
      </tr>
      <tr>
        <td class="title_item">所在地区：</td>
        <td><select name="province" id="province" style="width:175px;">
            <option value="${af.map.province}">请选择...</option>
          </select>
          &nbsp;
          <select name="city" id="city" style="width:157px;">
            <option value="${af.map.city}">请选择...</option>
          </select>
          &nbsp;
          <select name="country" id="country" style="width:157px;">
            <option value="${af.map.country}">请选择...</option>
          </select></td>
      </tr>
      <tr>
        <td colspan="2" align="center"><input type="button" name="addOtherJxcSupplierButton" class="bgButton" value="添加" onclick="ajaxAddOtherJxcSupplier(this);" /></td>
      </tr>
    </table>
  </div>
  <!-- 弹出层部分 end --> 
  
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
var multiSelectMmtPdId;
var multiSelectJxcPdId;
var f = document.forms[0];
$(document).ready(function(){
	$("#paid_money").attr("dataType","Double").attr("msg","请填本次付款，且必须为实数！");
	$("#brand_name").attr("dataType","Require").attr("msg","请选择产品品牌！");
	$("#in_date").attr("dataType","Require").attr("msg","请选择进货日期！");
	//$("#jxc_pd_id").attr("dataType","Require").attr("msg","常用产品型号与买卖提型号必须选择一个！");
	//$("#jxc_supplier_id").attr("dataType","Require").attr("msg","请选择供应商！");
	//$("#link_man").attr("dataType","Require").attr("msg","请填写联系人姓名！");
	//$("#tel").attr("dataType","Require").attr("msg","请填写联系人电话！");
	

	//所在地市联动
	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${af.map.province}"});
	$("#city").attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map.city}"});
	$("#country" ).attr({"defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${af.map.country}"});
	$("#province").cs("${ctx}/jxc/CsAjax.do?method=getBaseProvinceList", "p_index", "0", false);
	//$("#province").change();

	var own_sys = $("input[type='radio'][name='own_sys']:checked").val();
	if (1 == own_sys) {
		$("#applyPd").hide();// 家电下乡才不可以申请型号
	}
	
	/*所属系统 Begin*/
	$("input[type='radio'][name='own_sys']").click(function(){
		$("#brand_name").val("");
		$("#brand_id").val("");
		var own_sys = $(this).val();
		clearValue();
		if(0 == own_sys) { //非家电下乡
			$("#own_sys_info").empty().text("说明：该产品为非家电下乡产品或者是[没有标识卡号]的家电下乡产品");
			$("#pd_type option[value='0']").remove();
			$("#pd_type").get(0).options.add(new Option("其他", "0"));
			$("#applyPd").show();// 只有非家电下乡才可以申请型号
		} else { //家电下乡
			$("#own_sys_info").empty().text("说明：该产品为[有标识卡号]的家电下乡产品");
			$("#otherPdType").hide();
			$("#jxc_pd_type_id").removeAttr("dataType").val("");
			$("#pd_type option[value='0']").remove();
			$("#applyPd").hide();// 家电下乡才不可以申请型号
		}
	});
	/*所属系统 End*/
	
	/*产品类型 （与自定义大类控制是否下级可选）Begin*/
	$("#pd_type").change(function(){
		$("#brand_name").val("");//清空选择的品牌名称
		$("#brand_id").val("");//清空选择的品牌ID
		clearValue();
		$("#pd_id").val("");//清空选择的型号隐藏域值
		$("#out_sys_id").val("");
		$("#tip").remove();
		var pd_type = $(this).val();
		if(0 == pd_type) {// 选择"其他"时，提供自己的产品大类列表选择，并且可以添加自定义
			$("#otherPdType").show();
			$("#jxc_pd_type_id").attr("dataType","Require").attr("msg","请选择其他产品类型！");
		} else {
			$("#otherPdType").hide();
			$("#jxc_pd_type_id").removeAttr("dataType").val("");
		}
	});
	/*产品类型 End*/
	
	//非家电下乡自定义大类改变时也恢复型号
	$("#jxc_pd_type_id").change(function(){
		$("#brand_name").val("");//清空选择的品牌名称
		$("#brand_id").val("");//清空选择的品牌ID
		clearValue();
		$("#pd_id").val("");//清空选择的型号隐藏域值
		$("#out_sys_id").val("");
	});
	

	/*  添加其他产品类型  Begin */
	$("#addOtherPdTypeSpan").click(function(){
		$("#jxc_pd_type_name").val("");
		$("#addOtherPdTypeDiv").dialog({// 弹出添加层
			width: 280,
			height: 160,
			resizable: false,
			//position:'right',
			modal : true
		}).dialog("open");
	});
	/*  添加其他产品类型  End */
	
	
	// 供应商联系电话验证
	$("#tel").bind("blur",function(){
		var mobile=/^((\(\d{2,3}\))|(\d{3}\-))?(1[3458]\d{9})$/; 
		var phone=/^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/;
		if (mobile.exec(this.value)||phone.exec(this.value)){
			$("#span_msg_tel__error").hide();
		}else{
			$("#span_msg_tel__error").show();
		}
	});

	
	// 提交表单(验证价格是否为负和字段是否超过长度)
	$("#btn_submit").click(function(){
		if ($("#pd_ids", "#tbodyContent").length == 0) {
	        alert("请至少添加一个产品进货信息！");
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
			if($("#brand_id").val() != "${KONKA_BRAND_ID}"){
				if($("#jxc_supplier_id").val() == ""){
					alert("请选择供应商！");
					$("#jxc_supplier_id").focus();
					return false;
				}
			}
			if ($("input[name='jh_counts']").length <= 1) {
				alert("不能提交空的产品进货单！");
				return false;
			}
			
			$("#btn_submit").attr("value", "提交中").attr("disabled", "true");
	        $("#btn_reset").attr("disabled", "true");
			f.submit();
		}
	});

	$("#tdAdd").click(function(){
		var brand_id = $("#brand_id").val();
		if(brand_id == ""){
			alert("请先选择品牌！");
			return false;
		}
    	$("#trHidden").clone().appendTo($("#tbodyContent")).show();
    	var lastTR = $("tr:last", "#tbodyContent");
    	var init_counts = $("#init_counts", lastTR);//期初库存
    	var jh_counts = $("#jh_counts", lastTR);//进货数量
    	var jh_prices = $("#jh_prices", lastTR);//进货单价
    	var jxc_pd_ids = $("#jxc_pd_ids", lastTR);//常用产品
    	var pd_ids = $("#pd_ids", lastTR);//真正的产品，隐藏表单
    	var mmt_pd_ids = $("#mmt_pd_ids", lastTR);//全部产品
    	var max_pd_nums = $("#max_pd_nums", lastTR);//当前产品实时库存
    	var jh_moneys = $("#jh_moneys", lastTR);//进货金额
    	
    	init_counts.attr("dataType","Number").attr("msg","请填写起初库存,且只能为正整数据！");
    	jh_counts.attr("dataType","Integer").attr("msg","请填写进货数量，且必须为整数！");
    	jh_prices.attr("dataType","Currency").attr("msg","请填写进货单价，且必须为正数！");
    	//进货信息：输入进货数量和单价后自动计算金额
    	
 		bindJhCountAndJhPrice(jh_counts, jh_prices, max_pd_nums, jh_moneys, lastTR);	
 		var ms_jxc_pd_id, ms_mmt_pd_id;
    	var jxc_pd_values = [];
    	ms_jxc_pd_id = jxc_pd_ids.multiselect({
			selectedList: 1, 
			multiple: false, 
			minWidth:150,
			click: function(event, ui){
    			pd_ids.val("");
    			$("#kcinfo").empty();
    			ms_mmt_pd_id.find("option[value='']").attr("selected", "selected");
				if (ms_mmt_pd_id) ms_mmt_pd_id.multiselect("disable");// 禁选买卖提型号
				if(ui.value == ""){// 如果是请选择，恢复
					if(ms_mmt_pd_id)ms_mmt_pd_id.multiselect("enable");
				}
		       	if(ui.value != ""){
			       var this_value = ui.value;
		    	   jxc_pd_values = this_value.split("$$");
		    	   var pd_id  = jxc_pd_values[0], init_count = jxc_pd_values[1], now_count = jxc_pd_values[2], jh_price = jxc_pd_values[3], pd_name = jxc_pd_values[4];
		    	   jh_prices.val(jh_price);
		    	   pd_ids.val("pd_id:" + pd_id);
		    	   max_pd_nums.val(now_count);
				   init_counts.val(init_count).attr("readonly", true).css("color","#ccc");
		    	   $("#kcinfo").text(pd_name + "当前库存数为：" + now_count);
		    	   calcJhCountAndJhPrice(jh_counts, jh_prices, jh_moneys);
		    	   calcPdNumAndPrice("tbodyContent");
		       }
		   	}
		}).multiselectfilter();
    	notFindPd($("#notFindPd", lastTR), ms_jxc_pd_id);
    	ms_mmt_pd_id = mmt_pd_ids.multiselect({
			selectedList: 1, 
			multiple: false, 
			minWidth:180,
			click: function(event, ui){
    			pd_ids.val("");
    			$("#kcinfo").empty();
				ms_jxc_pd_id.find("option[value='']").attr("selected", "selected");
				if (ms_jxc_pd_id) ms_jxc_pd_id.multiselect("disable");// 禁选进销存型号
				if(ui.value == ""){// 如果是请选择，恢复
					if(ms_jxc_pd_id)ms_jxc_pd_id.multiselect("enable");
				}
		       if(ui.value != ""){
		    	   pd_ids.val(ui.value);
		    	   pd_ids.val("out_sys_id:" + ui.value);
		    	   jh_prices.val(0);
		    	   max_pd_nums.val(0);
				   init_counts.val("").attr("readonly", false).css("color","#000");
				   calcJhCountAndJhPrice(jh_counts, jh_prices, jh_moneys);
				   calcPdNumAndPrice("tbodyContent");
		       }
		   	}
		}).multiselectfilter();
		
	   	$("td:last", lastTR).click(function (){
        	$(this).parent().remove();
        	 calcPdNumAndPrice("tbodyContent");
        	 $("#kcinfo").empty();
        }).css("cursor", "pointer");

	}).css("cursor", "pointer");


	
});//ready end

// 根据选择的大类查询品牌（常用型号和买卖提型号均要查找）
function openChild(){
	  var pd_type = $("#pd_type").val();
	  var returnValue = window.showModalDialog("JxcStockBill.do?method=chooseBrand&pd_type="+pd_type+"&time="+new Date(), window, "dialogWidth:800px;status:no;dialogHeight:680px"); 
	  if(returnValue != null) {
		var value = new Array();
		value = returnValue.split(",");
		clearValue();//当品牌改变时，清空
		document.forms[0].brand_name.value = value[0];
		document.forms[0].brand_id.value = value[1];
		// 判断如果选的是康佳品牌，隐藏供应商信息
		if (value[1] == "${KONKA_BRAND_ID}") {
			$("#supplier").hide();
			$(".notFindPd").hide();//康佳品牌，不支持添加常用型号
		} else {//非康佳显示
			$("#supplier").show();
			$(".notFindPd").show();
		}	
	  }
	  var own_sys = $("input[type='radio'][name='own_sys']:checked").val();
	  var brand_id = $("#brand_id").val();
	  getCommonPd(own_sys, pd_type, brand_id);// 根据所选的产品大类、品牌查找常用产品型号
	  getProductType(pd_type, brand_id, own_sys);// 根据所选的产品大类、品牌查找买卖提产品型号
	  $("#tdAdd").click();//自动添加一行
}

//根据所选的产品大类、品牌查找常用产品型号
function getCommonPd(own_sys, pd_type, brand_id){
	if (pd_type == "" || brand_id == "") {
		return false;
	}
	var isOther = 1;//标识是否选择了其他大类
	if (pd_type == 0) {// 选择了其他
		pd_type = $("#jxc_pd_type_id").val();
		if(pd_type == ""){
			alert("请选择产品类型");
			$("#brand_name").val('');
			$("#brand_id").val('');
			return false;
		}
		isOther = 0;// 选择了其他
	}
	clearValue();
	$.ajax({
		type: "POST",
		url: 'JxcStockBill.do',
		data: "method=getJxcPd&pd_type=" + pd_type +"&brand_id=" + brand_id  + "&own_sys=" + own_sys + "&isOther=" + isOther + "&keySeq=${af.map.keySeq}",
		dataType: "json",
		async: false,
		error: function(request, settings) {alert("数据加载请求失败！"); },
		success: function(Dates) {
			$("#jxc_pd_ids").empty();
			if (Dates.length > 1) {
				$("#jxc_pd_ids").get(0).options.add(new Option("请选择...", ''));
				for ( var i = 0; i < Dates.length - 1; i++) {
					var md_name = Dates[i].md_name;
					$("#jxc_pd_ids").get(0).options.add(new Option(md_name, Dates[i].values));
				}
			} else {
				$("#jxc_pd_ids").get(0).options.add(new Option("请选择...", ''));
			}
		}
	});
}

//根据所选的产品大类、品牌查找买卖提产品型号
function getProductType(pd_type, brand_id, own_sys){
	if (pd_type == "" || brand_id == "") {
		return false;
	}
	if(pd_type == 0){// 选择了其他
		pd_type = $("#jxc_pd_type_id").val();
		if(pd_type == ""){
			alert("请选择自定义产品类型");
			return false;
		}
	}
	clearValue();
	$.ajax({
		type: "POST",
		url: 'JxcStockBill.do',
		data: "method=getPdModel&pd_type=" + pd_type + "&brand_id=" + brand_id + "&own_sys=" + own_sys + "&keySeq=${af.map.keySeq}",
		dataType: "json",
		async: false,
		error: function(request, settings) {alert("数据加载请求失败！"); },
		success: function(Dates) {
			$("#mmt_pd_ids").empty();
			if (Dates.length > 1) {
				$("#mmt_pd_ids").get(0).options.add(new Option("请选择...", ''));
				for ( var i = 0; i < Dates.length - 1; i++) {
					var md_name ="[" + Dates[i].pd_id + "]  " + Dates[i].md_name;
					$("#mmt_pd_ids").get(0).options.add(new Option(md_name, Dates[i].pd_id));
				}
			} else {
				$("#mmt_pd_ids").get(0).options.add(new Option("请选择...", ''));
			}
		}
	});
}

// 点击申请型号
function notFindPd(obj, ms) {
	obj.click(function(){
		var _this = this;
		var pd_type = $("#pd_type").val();
		$("#pd_type_name_readonly").text($("#pd_type").find("option:selected").text());// 弹出框显示类型名称
		if(pd_type==0){// 选择了其他
			pd_type = $("#jxc_pd_type_id").val();
			if(pd_type == ""){
				alert("请选择自定义产品类型");
				return false;
			}
			$("#pd_type_name_readonly").text($("#jxc_pd_type_id").find("option:selected").text());// 弹出框显示自定义类型名称
		}
		var brand_id = $("#brand_id").val();
		if(brand_id == ""){
			alert("请选择品牌");
			return false;
		}
		
		$("#brand_name_readonly").text($("#brand_name").val());// 弹出框显示品牌名称
		$("#addOtherJxcPdDiv").dialog({// 弹出添加层
			width: 260,
			height: 200,
			resizable: false,
			//position:'right',
			buttons : { "添加" : function() {ajaxAddOtherJxcPd(_this, ms);}},
			modal : true
		}).dialog("open");
	});
	
}
	
//验证与保存添加常用产品型号（是否已经存在）
function ajaxAddOtherJxcPd(obj, ms){
	var thisTR = $(obj).parent().parent();
	var name = $("#new_pd_name").val();// 型号名称
	var brand_id = $("#brand_id").val();
	if (brand_id == "${KONKA_BRAND_ID}") {
		alert("康佳品牌不能添加常用型号！");
		return false;
	}

	if("" == $.trim(name)) {
		alert("请输入常用产品型号名称！");
		return false;
	}
	var pd_type_name = $("#pd_type").find("option:selected").text();// 基础类型名称
	var pd_type = $("#pd_type").val();//基础大类
	var jxc_pd_type_id = "";//自定义大类
	if(pd_type==0){// 选择了其他
		jxc_pd_type_id = $("#jxc_pd_type_id").val();
		pd_type_name = $("#jxc_pd_type_id").find("option:selected").text();// 自定义类型名称
	}
	obj.disabled = true;
	$.ajax({
		type: "POST",
		url: "JxcStockBill.do",
		async : false,
		data: "method=saveJxcPd&pd_type=" + pd_type + "&out_sys_id=" + jxc_pd_type_id + "&brand_id=" + brand_id + "&pd_type_name=" + pd_type_name + "&brand_name=" + $("#brand_name").val() + "&name=" + name + "&keySeq=${af.map.keySeq}",
		dataType: "json",
		error: function(request, settings) {alert("数据加载请求失败！");obj.disabled = false;$("#addOtherJxcPdDiv").dialog("close"); },
		success: function(id) {
			if(0 < id) {
				$.jGrowl("恭喜，型号成功添加到常用产品型号列表中！",{theme:  'success'});
				var values = id + "$$0$$0$$0$$" + name;
				$("#jxc_pd_ids", thisTR).get(0).options.add(new Option(name, values));
				$("#jxc_pd_ids", thisTR).val(values);
				if(ms)ms.multiselect('refresh');
				$("#pd_ids", thisTR).val("pd_id:" + id);
				//$("#out_sys_id").val("");//清空买卖提外包产品型号ID
				//$("#mmt_pd_id").attr("disabled","true");// 禁选买卖提型号
			} else {
				if(-1 == id){
					$.jGrowl("对不起，密钥key丢失或者没有插入！",{theme:  'error'});
				}
				if(-2 == id){
					$.jGrowl("对不起，您添加的产品型号名称自定义已存在！",{theme:  'error'});
				}
				if(-3 == id){
					$.jGrowl("康佳品牌不能添加常用型号！",{theme:  'error'});
				}
			}
			obj.disabled = false;
			$("#addOtherJxcPdDiv").dialog("close");
		}
	});
}

// 验证与保存添加产品类型（是否已经存在）
function ajaxAddOtherPd(obj){
	var JQ_jptn = $("#jxc_pd_type_name");
	var jptn = JQ_jptn.val();//添加的大类名称
	if("" == $.trim(jptn)) {
		alert("请输入产品类型名称");
		return false;
	}
	//$("#tip").remove();
	obj.disabled = true;
	$.ajax({
		type: "POST",
		url: "JxcStockBill.do",
		async : false,
		data: "method=saveJxcPdType&jxc_pd_type_name=" + jptn + "&keySeq=${af.map.keySeq}",
		dataType: "json",
		error: function(request, settings) {alert("数据加载请求失败！"); },
		success: function(id) {
			if(0 < id) {
				$.jGrowl("恭喜，信息添加成功！",{theme:  'success'});
				$("#jxc_pd_type_id").get(0).options.add(new Option(jptn, id));
				$("#jxc_pd_type_id").val(id);
			} else {
				if(-1 == id){
					$.jGrowl("对不起，您添加的产品类型名称基础品类中已存在！",{theme:  'error'});
				}
				if(-2 == id){
					$.jGrowl("对不起，您添加的产品类型名称自定义已存在！",{theme:  'error'});
				}
			}
			obj.disabled = false;
			$("#addOtherPdTypeDiv").dialog("close");
		}
	});
}

// 申请品牌
function gotoJxcBrandApply(){
	window.location.href="JxcBrandApply.do?method=add&keySeq=${af.map.keySeq}";
}

//弹出添加供应商窗口
function gotoSupplierApply(){
	$("#addJxcSupplierDiv").dialog({// 弹出添加层
		width: 700,
		height: 500,
		resizable: false,
		//position:'right',
		modal : true
	}).dialog("open");
}

//添加验证供应商:验证供应商是否存在
function ajaxAddOtherJxcSupplier(obj){
	
	var supplierName = $("#supplier_name").val();//添加的供应商名称
	var linkMan = $("#link_man").val();
	var tel = $("#tel").val();
	if("" == $.trim(supplierName)) {
		alert("请输入供应商名称");
		return false;
	}
	if(linkMan == ""){
		alert("请填写联系人姓名！");
		$("#link_man").focus();
		return false;
	}
	if(tel == ""){
		alert("请填写联系人电话！");
		$("#tel").focus();
		return false;
	}
	var supplier_remarks = $("#supplier_remarks").val();
	if(supplier_remarks.length > 130){
		alert("供应商信息中的备注超过120字符长度限制！");
		$("#supplier_remarks").focus();
		return false;
	}
	var fax = $("#fax").val();
	var qq  = $("#qq").val();
	var e_mail  = $("#e_mail").val();
	var post   = $("#post").val();
	var addr  = $("#addr").val();
	if($("#country").val() != ""){
		$("#p_index").val($("#country").val());
	}else{
		if($("#city").val() != ""){
			$("#p_index").val($("#city").val());
		}else{
			if($("#province").val() != ""){
				$("#p_index").val($("#province").val());
			}
		}
	}
	var p_index  = $("#p_index").val();
	obj.disabled = true;
	$.ajax({
		type: "POST",
		url: "JxcStockBill.do",
		async : false,
		data: "method=saveJxcSupplier&supplier_name=" + supplierName + "&link_man=" + linkMan + "&tel=" + tel + "&fax=" + fax + "&e_mail=" + e_mail + "&qq=" + qq + "&post=" + post + "&addr=" + addr + "&supplier_remarks=" + supplier_remarks + "&p_index=" + p_index + "&keySeq=${af.map.keySeq}",
		dataType: "json",
		error: function(request, settings) {alert("数据加载请求失败！"); },
		success: function(id) {
			if(0 < id) {
				$.jGrowl("恭喜，信息添加成功！",{theme:  'success'});
				$("#jxc_supplier_id").get(0).options.add(new Option(supplierName, id));
				$("#jxc_supplier_id").val(id);
			} else {
				if(-1 == id){
					$.jGrowl("对不起，密钥key丢失或者没有插入！",{theme:  'error'});
				}
				if(-2 == id){
					$.jGrowl("对不起，您添加的供应商名称已存在！",{theme:  'error'});
				}
			}
			obj.disabled = false;
			$("#addJxcSupplierDiv").dialog("close");
		}
	});
}

function clearValue(){
	$("#tbodyContent").empty();
}

function clearValueForJxcAndMmtPdId(){
}

function calcPdNumAndPrice(tbody_id) {//计算 合计的 数量 和 单价
	var allNum = 0;
	var allAmount = 0;
	$("#pdNum", $("#" + tbody_id)).each(function(){
		var this_value = parseFloat($(this).val());
		if(isNaN(this_value)) this_value = 0;
		allNum += this_value;
	});
	$("#sumPrice", $("#" + tbody_id)).each(function(){
		var this_value = parseFloat($(this).text());
		if(isNaN(this_value)) this_value = 0;
		allAmount += this_value;
	});

	$("#allNum").text(allNum);
	$("#allAmount").text(allAmount);
	$("#money").val("￥" + allAmount);
	$("#pay_money").val(allAmount);

}

function calcJhCountAndJhPrice(jh_counts, jh_prices, jh_moneys) {
	var jh_count = parseFloat(jh_counts.val());//进货数量
   	if(isNaN(jh_count)) jh_count = 1;
   	var jh_price =  parseFloat(jh_prices.val());//进货单价
   	if(isNaN(jh_price)) jh_price = 0;
   	jh_moneys.text((jh_count * jh_price).toFixed(2));//进货金额
}

function bindJhCountAndJhPrice(jh_counts, jh_prices, max_pd_nums, jh_moneys, lastTR) {
	jh_counts.keyup(function(){//进货数量
		var _reg = /^[-\+]?\d+$/;
		var jh_count =(this.value);
   		if (!_reg.test(jh_count)) {
   			jh_counts.val(1);
   			jh_count = 1;
   		}
   		var jh_price = parseFloat(jh_prices.val());//进货单价
   		if(isNaN(jh_price)) jh_price = 0;
   		var curr_count = parseFloat(max_pd_nums.val());//当前库存
   		if(isNaN(curr_count)) curr_count = 0;
   		jh_count = parseFloat(jh_count);//进货数量
   		if(isNaN(jh_count)) jh_count = 1;
   		
		$("#thInfo", lastTR).remove();
		if(Number(jh_count) + Number(curr_count) < 0){
			alert("您输入的退货产品数量超出了当前库存数量，导致该产品库存不足，请重新输入！");
			this.value = 1;
			jh_count = 1;
		}
		if(jh_count < 0){
			$(this).after("<span id='thInfo' title='退货：当输入的数量为负数时。'><img src='${ctx}/styles/jxc/images/th.gif' style='vertical-align: text-bottom;padding-left:5px;'/></span>");
		}
		jh_moneys.text((jh_count * jh_price).toFixed(2));//进货金额
		calcPdNumAndPrice("tbodyContent");
	});

	jh_prices.keyup(function(){//进货单价
		var jh_price = (this.value);
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(jh_price)) {
			jh_prices.val(0);
			jh_price = 0;
		}
		jh_price = parseFloat(this.value);
		if(isNaN(jh_price)) jh_price = 0;
   		var jh_count = parseFloat(jh_counts.val());//进货数量
   		if(isNaN(jh_count)) jh_count = 1;

   		jh_moneys.text((jh_count * jh_price).toFixed(2));//进货金额
		calcPdNumAndPrice("tbodyContent");
	});
	calcPdNumAndPrice("tbodyContent");
}

function calcPdNumAndPrice(tbody_id) {//计算 合计的 数量 和 单价
	var jh_count_sum = 0;
	var jh_money_sum = 0;
	$("#jh_counts", $("#" + tbody_id)).each(function(){
		var this_value = parseFloat($(this).val());
		if(isNaN(this_value)) this_value = 0;
		jh_count_sum += this_value;
	});

	$("#jh_moneys", $("#" + tbody_id)).each(function(){
		var this_value = parseFloat($(this).text());
		if(isNaN(this_value)) this_value = 0;
		jh_money_sum += this_value;
	});
	$("#jh_count_sum").text(jh_count_sum);
	$("#jh_money_sum").text("￥" + jh_money_sum.toFixed(2));
	$("#pay_money").val(jh_money_sum.toFixed(2));
	$("#paid_money").val(jh_money_sum.toFixed(2));
}

                        
//]]>--></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
