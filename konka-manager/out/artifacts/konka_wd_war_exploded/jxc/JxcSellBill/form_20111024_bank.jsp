<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>产品销售 &gt; 销售登记  &gt; 其他产品销售</title>
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
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：产品销售 &gt; 销售登记&gt; 其他产品销售</div>
  <div class="rtabcont1">
    <html-el:form action="/JxcSellBill.do" >
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <input type="hidden" name="sn" value="${sn}"/>
      <html-el:hidden property="keySeq" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="4" align="center">销售登记 </th>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>销售日期：</td>
          <td><html-el:text property="sell_date" styleId="sell_date" size="9" maxlength="9" readonly="true" styleClass="webinput" value="${today}" onclick="new Calendar(2011, 2031, 0).show(this);" /></td>
          <td class="title_item"><font color="red">*</font>销售编号：</td>
          <td><span><font color="red">NO.</font><font color="red">${sn}</font></span></td>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>客户：</td>
          <td colspan="3"><input type="hidden" name="customer_type" id="customer_type"/>
            <select name="customer_id" id="customer_id">
              <option value="">请选择客户</option>
              <c:forEach var="cur" items="${customerList}" >
                <option value="${cur.id}" >${cur.name}</option>
              </c:forEach>
            </select>
            &nbsp; <span class="jxcTip">如果找不到客户，请点击</span>
            <input type="button" name="save" class="bgButtonAdd" value="新增 " id="addOtherCustomerSpan" /></td>
        </tr>
        <tr>
          <th colspan="4">产品信息</th>
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
            </span></td>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>产品品牌：</td>
          <td colspan="3"><input  class="webinput" title="点击选择产品品牌" name="brand_name" id="brand_name"  readonly="readonly" onclick="openChild()" />
            <input class="webinput" type=hidden name="brand_id" id="brand_id" /></td>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>产品型号：</td>
          <td colspan="3"><span id="pd_id_span"></span>
            <html-el:hidden property="pd_id" styleId="pd_id"/></td>
        </tr>
        <tr>
          <th colspan="4">销售信息</th>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>销售数量：</td>
          <td colspan="3"><input type="hidden" value="" name="maxPdNum" id="maxPdNum" />
            <input class="webinput" name="count" type="text" id="count" maxlength="8" value="1"/></td>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>销售单价：</td>
          <td colspan="3"><input type="hidden" value="" name="maxPdNum" id="maxPdNum" />
            <input class="webinput" name="price" type="text" id="price" maxlength="8" value="0"/></td>
        </tr>
        <tr>
          <td class="title_item">销售金额￥：</td>
          <td colspan="3"><input class="webinput" name="total_price" type="text" id="total_price"  maxlength="8" value="0" readonly="readonly"/></td>
        </tr>
        <tbody style="display:none;" id="tbodyKjPdCh">
          <tr>
            <td class="title_item">产品编号：</td>
            <td colspan="3"><input class="webinput" name="pd_bh" type="text" id="pd_bh"  maxlength="100"/></td>
          </tr>
          <tr>
            <td class="title_item">产品串号：</td>
            <td colspan="3"><input class="webinput" name="pd_ch" type="text" id="pd_ch"  maxlength="100"/></td>
          </tr>
        </tbody>
        <tr>
          <td class="title_item">说明：</td>
          <td colspan="3"><textarea class="webtextarea" name="remarks"  id="remarks"></textarea></td>
        </tr>
        <tr>
          <td class="title_item">经办人：</td>
          <td colspan="3"><input type="hidden" value="" name="maxPdNum" id="maxPdNum" />
            <input class="webinput" name="opr_man" type="text" id="opr_man" value="${userName}" size="10" maxlength="20" /></td>
        </tr>
        <tr>
          <td class="title_item">应收金额(￥)：</td>
          <td><input type="hidden" value="" name="maxPdNum" id="maxPdNum" />
            <input class="webinput" name="money" type="text" id="money"  maxlength="5" value="0.00" readonly="readonly"/></td>
          <td class="title_item"><font color="red">*</font>本次收款(￥)：</td>
          <td><input type="hidden" value="" name="maxPdNum" id="maxPdNum" />
            <input class="webinput" name="pay_money" type="text" id="pay_money"  maxlength="5" value="0.00"/></td>
        </tr>
        <tr>
          <td colspan="4" align="center"><input class="bgButtonSave" type="button" name="btn_submit" id="btn_submit"  value="保 存" />
            &nbsp;
            <input class="bgButtonBack" type="button" name="btn_back"  id="btn_back" value="返 回" onclick="history.back();"/></td>
        </tr>
      </table>
    </html-el:form>
    <div id="addCustomerDiv" style="display:none;" title="添加新客户">
      <form id="addCustomentForm" name="addCustomentForm" method="post" action="" >
        <input type="hidden" name="p_index" id="p_index" />
        <input type="hidden" name="keySeq" value="${af.map.keySeq}"/>
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
          <tr>
            <td class="title_item" width="16%"><span><font style="color: red">*</font></span>客户名称：</td>
            <td><input class="webinput" maxlength="128" name="name" type="text"  id="name" value="${af.map.name}"/>
              <input name="id" type="hidden"  id="id" value="${af.map.id}" /></td>
          </tr>
          <tr>
            <td class="title_item"><span><font style="color: red">*</font></span>联系人姓名：</td>
            <td><input class="webinput"  name="link_name" type="text"  id="link_name" value="${af.map.link_name}" maxlength="20"/></td>
          </tr>
          <tr>
            <td class="title_item"><span><font style="color: red">*</font></span>客户类型：</td>
            <td><html-el:select property="cus_type" value="${af.map.cus_type}" style="width:158px">
                <html-el:option value="0">零售</html-el:option>
                <html-el:option value="1">批发</html-el:option>
              </html-el:select></td>
          </tr>
          <tr>
            <td class="title_item"><span><font style="color: red">*</font></span>联系电话：</td>
            <td ><input class="webinput" name="tel" type="text"  id="tel" value="${af.map.tel}" />
              <span id="span_msg_tel__error" style="display: none;"><font style="color: red">请填写正确的联系电话</font></span></td>
          </tr>
          <tr>
            <td class="title_item">传真：</td>
            <td ><input class="webinput"  name="fax" type="text"  id="fax" value="${af.map.fax}" maxlength="20"/></td>
          </tr>
          <tr>
            <td class="title_item">QQ号码：</td>
            <td><input class="webinput"  name="qq" type="text"  id="qq" value="${af.map.qq}" maxlength="20"/></td>
          </tr>
          <tr>
            <td class="title_item" >电子邮箱：</td>
            <td><input class="webinput"  name="e_mail" type="text"  id="e_mail" value="${af.map.e_mail}"  maxlength="50"/>
              <span id="span_msg_e_mail__error" style="display: none;"><font style="color: red">请填写正确的邮箱</font></span></td>
          </tr>
          <tr>
            <td class="title_item" >邮政编码：</td>
            <td ><input class="webinput"  name="post" type="text"  id="post" value="${af.map.post}" maxlength="10"/></td>
          </tr>
          <tr>
            <td class="title_item" >所在地区：</td>
            <td ><select name="province" id="province" style="width:120px;">
                <option value="${af.map.province}">请选择...</option>
              </select>
              &nbsp;
              <select name="city" id="city" style="width:120px;">
                <option value="${af.map.city}">请选择...</option>
              </select>
              &nbsp;
              <select name="country" id="country" style="width:120px;">
                <option value="${af.map.country}">请选择...</option>
              </select></td>
          </tr>
          <tr>
            <td class="title_item">街道地址：</td>
            <td ><input class="webinput"  name="addr" type="text"  id="addr" value="${af.map.addr}" maxlength="255"/></td>
          </tr>
          <tr>
            <td class="title_item" >备注：</td>
            <td><html-el:textarea styleClass="webtextarea" property="remarks" value="${af.map.remarks}" /></td>
          </tr>
        </table>
        <div align="right" style="padding: 5px;">
          <input type="button" name="addCustomerButton" id="addCustomerButton" class="bgButton" value=" 保 存 " onclick="ajaxAddCustomer(this);" />
        </div>
      </form>
    </div>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script>
<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){
	$("#province").attr({"subElement": "city", "defaultText": "请选择省...", "defaultValue": "", "selectedValue": "${af.map.province}"});
	$("#city").attr({"subElement": "country", "defaultText": "请选择市...", "defaultValue": "", "selectedValue": "${af.map.city}"});
	$("#country" ).attr({"defaultText": "请选择县...", "defaultValue": "", "selectedValue": "${af.map.country}"});
	//$("#end_date"  ).attr("dataType" , "Require").attr("msg" , "请选择失效时间！");
	
	$("#province").cs("${ctx}/jxc/CsAjax.do?method=getBaseProvinceList", "p_index", "0", false);
	
	$("#province").change(function(){
		if (this.value.length != 0) {
			this.form.p_index.value = this.value;
			$("#city").val("");
			$("#county").val("");
		}
	});

	$("#city").change(function(){
		if (this.value.length != 0) {
			this.form.p_index.value = this.value;
			$("#county").val("");
		} else {
			this.form.p_index.value = this.form.province.value;
		}
	});

	$("#county").change(function(){
		if (this.value.length != 0) {
			this.form.p_index.value=this.value;
		} else {
			this.form.p_index.value = this.form.city.value;
		}
	});


	$("#tel").bind("blur",function(){
			var mobile=/^((\(\d{2,3}\))|(\d{3}\-))?(1[3458]\d{9})$/; 
			var phone=/^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/;
			if (mobile.exec(this.value)||phone.exec(this.value)){
				$("#span_msg_tel__error").hide();
				$("#addCustomerButton").removeAttr("disabled");
				
			}else{
				$("#span_msg_tel__error").show();
				$("#addCustomerButton").attr("disabled","true");
			}

		});
	$("#e_mail").bind("blur",function(){
			var email=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/; 
			if (email.exec(this.value)){
				$("#span_msg_e_mail__error").hide();
				$("#addCustomerButton").removeAttr("disabled");
			}else{
				$("#span_msg_e_mail__error").show();
				$("#addCustomerButton").attr("disabled","true");
			}

		});
		
	$("#customer_id").attr("dataType","Require").attr("msg","请选择客户！");
	$("#brand_id").attr("dataType","Require").attr("msg","请选择品牌！");
	//$("#out_sys_id").attr("dataType","Require").attr("msg","请选择客户！");
	$("#count").attr("dataType","Number").attr("msg","请填写销售数量,且只能为正整数据！");
	$("#price").focus(setOnlyNum).attr("dataType","Require").attr("msg","请填写零售价！");
	//$("#price").attr("dataType","Double").attr("msg","请填写零售价，且必须为实数！");
	//$("#pay_money").attr("dataType","Double").attr("msg","请填写本次付款，且必须为实数！");
	$("#pay_money").focus(setOnlyNum).attr("dataType","Require").attr("msg","请填写本次付款！");

		var f = document.forms[0];
		$("#btn_submit").click(function(){//提交
			var isSubmit = Validator.Validate(f, 3);
			if (isSubmit) {
				$("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
				$("#btn_reset").attr("disabled", "true");
				$("#btn_back").attr("disabled", "true");
				f.submit();
			}
			
		});	
});

var nameHtml = '<input type="text" name="name" value="${af.map.name}" id="name" style="width:220px;" maxlength="50" dataType="Require" msg="请填写产品型号！"/>';
var outSysIdHtml = '<select name="out_sys_id" multiple="multiple" style="display:none;" id="out_sys_id" styleClass="bdfont"></select>';


/*产品类型 （与自定义大类控制是否下级可选）Begin*/
$("#pd_type").change(function(){
	var pd_type = $(this).val();
	$("#brand_id").val("");
	$("#brand_name").val("");
	if(0 == pd_type) {// 选择"其他"时，提供自己的产品大类列表选择
		$("#otherPdType").show();
		$("#jxc_pd_type_id").attr("dataType","Require").attr("msg","请选择其他产品类型！");
	} else {
		$("#otherPdType").hide();
		$("#jxc_pd_type_id").removeAttr("dataType").val("");
	}
	backSellInfo();
});

$("#brand_id").change(function(){
	backSellInfo();
	var brand_id = $("#brand_id").val();
	if (brand_id != '' && brand_id == "${KONKA_BRAND_ID}" ) {//是康佳产品显示pd-bh/pd_ch
		  $("#tbodyKjPdCh").show();
	 } else {
		 $("#tbodyKjPdCh").hide();
	 }
});

//计算销售金额
$("#count").bind("keyup", function(){
	var _reg = /^\d+$/;
	var _this = $(this);
	var this_value = _this.val();//进货数量
	if("" != this_value) {
		if (!_reg.test(this_value)) {//非数字清空金额
			alert(_this.attr("msg"));
			_this.val(1);
			$("#total_price").val("0.00");
			$("#money").val("0.00");
			$("#pay_money").val("0.00");
			return false;
		}

		if(Number(this_value) > parseInt($("#maxPdNum").val())){
			alert("您输入的销售产品数量超过该产品最大库存[" + parseInt($(this).prev().val()) + "]，请重新输入！");
			_this.focus();
			_this.val(1);
		} 
		getMoney(this_value);
	}

		
});

$("#price").bind("keyup",function(){
	if(isNaN($(this).val())) {//非数字清空金额
		$("#total_price").val("0.00");
		$("#money").val("0.00");
		$("#pay_money").val("0.00");
		return false;
	}
	getMoney($(this).val());
});


/*  添加新客户  Begin*/
$("#addOtherCustomerSpan").click(function(){
	$("#jxc_pd_type_name").val("");
	$("#addCustomerDiv").dialog({
		width: 645,
		height: 530,
		resizable: false,
		position:'right',
		modal : true
	}).dialog("open");
});

/* 计算金额*/
function getMoney(this_value){
	if("" == this_value) {//非数字清空金额
		$("#total_price").val("0.00");
		$("#money").val("0.00");
		$("#pay_money").val("0.00");
		return false;
	}
	var count = $("#count").val();
	var price = $("#price").val();
	$("#total_price").val((parseFloat(price)*parseInt(count)).toFixed(2));
	$("#money").val((parseFloat(price)*parseInt(count)).toFixed(2));
	$("#pay_money").val((parseFloat(price)*parseInt(count)).toFixed(2));
	
}

/* 恢复初始销售信息 */
function backSellInfo(){
	
	$("#pd_id").val("");//清空选择的型号
	$("#pd_id_span").empty();//清空选择的型号
	
	$("#count").val(1);
 	$("#price").val(0);
	$("#total_price").val("0.00");
	$("#money").val("0.00");
	$("#pay_money").val("0.00");
}
/*  添加新客户   End*/                                           

function openChild(){//根据大类选品牌
 var pd_type = $("#pd_type").val();
 var returnValue = window.showModalDialog("JxcSellBill.do?method=chooseBrand&pd_type="+pd_type+"&time="+new Date(), window, "dialogWidth:800px;status:no;dialogHeight:680px"); 
  	if(returnValue != null) {
	var value = new Array();
	value = returnValue.split(",");
   	document.forms[0].brand_name.value = value[0];
    document.forms[0].brand_id.value = value[1];
  }
  var brand_id = $("#brand_id").val();
 
  if(brand_id!='' && brand_id=="${KONKA_BRAND_ID}" ){//是康佳产品显示pd-bh/pd_ch
	  $("#tbodyKjPdCh").show();
 }else{
	 $("#tbodyKjPdCh").hide();

 }

  
  getProductType(pd_type, brand_id);
	  $("#pd_id").val("");//清空选择的型号
	  $("#out_sys_id").val("");
	  $("#count").val(1);
	  $("#price").val(0);
	  $("#total_price").val("0.00");
	  $("#money").val("0.00");
	  $("#pay_money").val("0.00");
}

function getProductType(pd_type, brand_id, out_sys_id){//根据大类、品牌选型号
	
	if (pd_type == "" || brand_id == "") {
		return false;
	}
	
	var own_sys = $("input[type='radio'][name='own_sys']:checked").val();
	if (undefined != out_sys_id && 0 == own_sys && "" != "${af.map.name}" && out_sys_id < 0) {
		$("#pd_id_span").empty().append(nameHtml)
		return false;
	}
	var jxc_pd_type_id = $("#jxc_pd_type_id").val();
	$.ajax({
		type: "POST",
		url: 'JxcSellBill.do',
		data: "method=getPdModel&pd_type=" + pd_type + "&brand_id=" + brand_id + "&jxc_pd_type_id=" + jxc_pd_type_id + "&keySeq=${af.map.keySeq}",
		dataType: "json",
		async: false,
		error: function(request, settings) {alert("数据加载请求失败！"); },
		success: function(Dates) {
		
			$("#pd_id_span").empty();
			if (Dates.length > 1) {
				$("#pd_id_span").html(outSysIdHtml);
				notFindPd();
				for ( var i = 0; i < Dates.length - 1; i++) {
					var md_name ="产品编码:["+ Dates[i].pd_id +"]  " + Dates[i].md_name;
					//$("#out_sys_id").get(0).options.add(new Option(Dates[i].md_name, Dates[i].pd_id));
					$("#out_sys_id").get(0).options.add(new Option(md_name, Dates[i].values));
					//document.forms[0].price.value=Data[i].price;//默认价格
				}
				
				var el = $("#out_sys_id").multiselect({
					selectedList: 1,
					multiple: false,
					minWidth:380,
					click:function(event,ui){
					if(ui.value != ""){
						var s = ui.value.split("#$");
						$("#pd_id").val(s[0]);
						$("#price").val(s[1]);//取零售价
						$("#maxPdNum").val(s[2]);//最大库存
						$(".maxPdNumTip").remove();
						$("#count").after("<span style='color:red;padding-left:5px;' class='maxPdNumTip'>该产品当前库存为：【" + s[2]+ "】</span>");
						getMoney(ui.value);//计算金额
					}
				}
				}).multiselectfilter({width:280}).attr({"datatype" : "Ms" , "msg" : "请选择产品型号！"});
			   	$("#out_sys_id option[value='${af.map.out_sys_id}']").attr("selected", "selected");
				el.multiselect('refresh');
				
			} else {
				var own_sys = $("input[type='radio'][name='own_sys']:checked").val();
				if (0 == own_sys){
					$("#pd_id_span").html(nameHtml);
					
				} else {
					$("#pd_id_span").append('<span style="color:#f00;padding:5px;">没有型号,请重新选择品牌：</span>');//.append(getPdModelListHtml).append(outSysIdHiddenHtml);
					//alert($("#pd_id_span").html());
				}
			}
		}
	});
	if(undefined != out_sys_id && "" != out_sys_id){
		$("#out_sys_id").val(out_sys_id);
	}
}
function notFindPd(obj) {
	$("#notFindPd").click(function(){
		var own_sys = $("input[type='radio'][name='own_sys']:checked").val();
		if (0 == own_sys) {
			$("#pd_id_span").empty().append(nameHtml)
		} else {
			$("#pd_id_span").empty().append(getPdModelListHtml).append(outSysIdHiddenHtml);
			openChildForPdModel();
		}
	});
}


function gotoJxcBrandApply(){//申请品牌
	//window.location.href="JxcBrandApply.do?method=add&keySeq=${af.map.keySeq}";
}


function ajaxAddCustomer(obj){//添加新客户 
	obj.disabled = true;
	var name = $.trim($("#name").val());
	var link_name = $.trim($("#link_name").val());
	var tel = $.trim($("#tel").val());
	if ("" == name) {
		alert("请填写客户名称！");
		return false;
	}
	if ("" == link_name) {
		alert("请填写联系人姓名！");
		return false;
	}
	if ("" == tel) {
		alert("请填写联系人电话！");
		return false;
	}
	$.ajax({
		type: "POST",
		url: "JxcSellBill.do",
		async : false,
		data: "method=saveJxcCustomer&" + $("#addCustomentForm").serialize(),
		dataType: "json",
		error: function(request, settings) {alert("数据加载请求失败！"); },
		success: function(id) {
			if(-1 != id) {
				$.jGrowl("恭喜，信息添加成功！",{theme:  'success'});
				$("#customer_id").get(0).options.add(new Option(name, id));
				$("#customer_id").val(id);
			} else {
				$.jGrowl("对不起，您添加的产品类型名称已存在！",{theme:  'error'});
			}
			obj.disabled = false;
			$("#addCustomerDiv").dialog("close");
		}
	});
}

//正则表达式：只能输入数字和带小数点数字
function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		//if(this.value.length == 0) this.value = "0";
	});
}
//正则表达式：只能输入数字
function setOnlyNum1() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	var regInteger = /^[-\+]?\d+$/;
	$(this).keypress(function (){
		if(!this.value.match(regInteger))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(regInteger))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(regInteger))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(regInteger))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(regInteger))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = "0";
	});
}
//]]>--></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>