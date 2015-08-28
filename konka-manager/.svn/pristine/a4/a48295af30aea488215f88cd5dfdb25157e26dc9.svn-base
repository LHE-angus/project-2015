<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>增加产品</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css"></style>
</head><body>
<div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：库存管理 &gt; 产品管理</div>
<div class="rtabcont1">
<html-el:form action="/JxcPd" enctype="multipart/form-data">
  <html-el:hidden property="id" styleId="id"/>
  <html-el:hidden property="method" value="save" />
  <html-el:hidden property="keySeq" />
  <html-el:hidden property="queryString" />
  <c:if test="${not empty af.map.canNotEdit}">
    <input type="hidden" name="own_sys" value="${af.map.own_sys}" />
    <input type="hidden" name="pd_type" value="${af.map.pd_type}" />
    <input type="hidden" name="out_sys_id" value="${af.map.out_sys_id}" />
    <input type="hidden" name="name" value="${af.map.name}" />
  </c:if>
  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
    <tr>
      <th height="30" colspan="2"  style="padding-left:10px" align="center"><span style="font-weight:800;">产品信息</span></th>
    </tr>
    <tr>
      <td width="15%" height="30" align="right" nowrap="nowrap"  style="padding-left:10px;" class="title_item"><font color="red">*</font>所属系统：</td>
      <td width="85%" height="30" align="left" style="padding-left:10px;"><label for="own_sys_0">
          <html-el:radio property="own_sys" styleId="own_sys_0" value="0">非家电下乡</html-el:radio>
        </label>
        &nbsp;
        <label for="own_sys_1">
          <html-el:radio property="own_sys" styleId="own_sys_1" value="1" >家电下乡</html-el:radio>
        </label>
        <span id="own_sys_info" style="padding:5px;color:#f00;">
        <c:if test="${empty af.map.id}">说明：该产品为非家电下乡产品或者是[没有标识卡号]的家电下乡产品</c:if>
        </span></td>
    </tr>
    <tr>
      <td width="15%" height="30" align="right"  nowrap="nowrap"  style="padding-left:10px;" class="title_item"><font color="red">*</font>产品类型：</td>
      <td width="85%" height="30" align="left" style="padding-left:10px;"><html-el:select property="pd_type" styleId="pd_type" styleClass="bdfont" style="width:170px">
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
        &nbsp;
        <c:if test="${not empty af.map.canNotEdit}"><span style="color:#ccc;">添加其他产品类型</span></c:if>
        <c:if test="${empty af.map.canNotEdit}"><span style="color:#0066FF;cursor:pointer;" id="addOtherPdTypeSpan">添加其他产品类型</span></c:if>
        </span></td>
    </tr>
    <tr>
      <td height="30" align="right"  nowrap="nowrap" style="padding-left:10px;" class="title_item"><font color="red">*</font>产品品牌：</td>
      <td height="30" align="left" style="padding-left:10px;"><html-el:text title="点击选择产品品牌" property="brand_name" styleId="brand_name" style="cursor:pointer" readonly="true" onclick="openChild()" />
        <html-el:hidden property="brand_id" styleId="brand_id" />
        如果没有可用品牌，请点击 <span style="cursor:pointer;color:#0066FF;" onclick="gotoJxcBrandApply()">&nbsp;品牌申请</span></td>
    </tr>
    <tr id="jdxx">
      <td height="30" align="right"  style="padding-left:10px;" class="title_item"><font color="red">*</font>产品型号：</td>
      <td height="30" align="left" style="padding-left:10px;"><span id="pd_id_span"></span></td>
    </tr>
    <tr>
      <td height="30" align="right"  nowrap="nowrap" style="padding-left:10px;" class="title_item"><font color="red">*</font>期初库存：</td>
      <td height="30" align="left" style="padding-left:10px;"><html-el:text property="init_count" styleId="init_count" maxlength="10" />
        <span class="infoTip" title="【期初库存】：截止当前，仓库中该产品的数量，若无，填写为0。提交后将不能修改，请您慎重填写！"> <img src="${ctx}/styles/jxc/images/qm_yellow.gif" style="vertical-align:middle;" /></span></td>
    </tr>
    <tr>
      <td height="30" align="right"  nowrap="nowrap" style="padding-left:10px;" class="title_item">当前库存：</td>
      <td height="30" align="left" style="padding-left:10px;"><html-el:text property="count" styleId="count" maxlength="10" readonly="true" /></td>
    </tr>
    <tr>
      <td height="30" align="right"  nowrap="nowrap" style="padding-left:10px;" class="title_item">计量单位：</td>
      <td height="30" align="left" style="padding-left:10px;"><html-el:text property="unit" styleId="unit" maxlength="5"></html-el:text></td>
    </tr>
    <tr>
      <td height="30" align="right"  nowrap="nowrap" style="padding-left:10px;" class="title_item"><font color="red">*</font>参考进货价：</td>
      <td height="30" align="left" style="padding-left:10px;"><html-el:text property="ref_price" styleId="ref_price" maxlength="8"></html-el:text>
        &nbsp;（单位：元）<span class="infoTip" title="【参考进货价】：该产品的进货单价。在进货登记时将作为默认进货单价（可更改）。"> <img src="${ctx}/styles/jxc/images/qm_yellow.gif" style="vertical-align:middle;" /></span></td>
    </tr>
    <tr>
      <td height="30" align="right"  nowrap="nowrap" style="padding-left:10px;" class="title_item"><font color="red">*</font>零售价：</td>
      <td height="30" align="left" style="padding-left:10px;"><html-el:text property="price" styleId="price" maxlength="8"></html-el:text>
        &nbsp;（单位：元）<span class="infoTip" title="【零售价】：产品销售的单价，销售给零售客户的价格。"> <img src="${ctx}/styles/jxc/images/qm_yellow.gif" style="vertical-align:middle;" /></span></td>
    </tr>
    <tr>
      <td height="30" align="right"  nowrap="nowrap" style="padding-left:10px;" class="title_item"><font color="red">*</font>批发价：</td>
      <td height="30" align="left" style="padding-left:10px;"><html-el:text property="pf_price" styleId="pf_price" maxlength="8"></html-el:text>
        &nbsp;（单位：元）<span class="infoTip" title="【批发价】：产品销售的单价，销售给批发客户的价格。"> <img src="${ctx}/styles/jxc/images/qm_yellow.gif" style="vertical-align:middle;" /></span></td>
    </tr>
    <tr>
      <td height="80" align="right"  nowrap="nowrap" style="padding-left:10px;" class="title_item">备注说明：</td>
      <td height="80" align="left" style="padding-left:10px;"><html-el:textarea property="remarks" styleId="remarks" rows="4" style="width:450px"></html-el:textarea></td>
    </tr>
    <tr>
      <td height="56" colspan="2" align="center"><div>
          <input type="button" name="save" class="bgButtonSave" value=" 保 存 " id="btn_submit"/>
          <input type="reset" name="reset" class="bgButtonBack" value=" 重 填 " />
          <input type="button" name="return" id="btn_back" class="bgButtonBack" value=" 返 回 " onclick="history.back();" />
          <c:if test="${not empty af.map.canNotEdit}"> <span style="padding:5px;color:#f00;">对不起，该产品已经有交易记录，所以部分数据不能进行修改！ </span> </c:if>
        </div></td>
    </tr>
  </table>
</html-el:form>
</div>
<div id="addOtherPdTypeDiv" style="display:none;" title="添加其他产品类型">
  <div align="left"> 产品名称：&nbsp;
    <input type="text" name="jxc_pd_type_name" id="jxc_pd_type_name" style="width:80px;" maxlength="10"/>
  </div>
  <div align="right" style="padding: 5px;">
    <input type="button" name="addOtherPdTypeButton" class="fk" value=" 添加" onclick="ajaxAddOtherPd(this);" />
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery1.5.1.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/cs.js"></script> 
<script type="text/javascript" src="${ctx}/javascripts/commons.plugin.js"></script> 
<script type="text/javascript" src="${ctx}/javascripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script> 
<script type="text/javascript" src="${ctx}/javascripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/javascripts/jquery-multiSelect/jquery.multiselect.min.js"></script> 
<script type="text/javascript" src="${ctx}/javascripts/jquery-multiSelect/jquery.multiselect.filter.js"></script> 
<script type="text/javascript" src="${ctx}/javascripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script> 
<script type="text/javascript" src="${ctx}/javascripts/jquery.jgrowl.js"></script> 
<script type="text/javascript" src="${ctx}/javascripts/tip/jquery.qtip.min.js"></script> 
<script type="text/javascript">//<![CDATA[
var getPdModelListHtml = '<input title="点击选择产品型号" type="text" name="out_sys_name_other" id="out_sys_name_other" style="width:220px;cursor:pointer" onclick="openChildForPdModel()" readonly="true" dataType="Require" msg="请选择产品型号！"/>'
var nameHtml = '<input type="text" name="name" value="${af.map.name}" id="name" style="width:220px;" maxlength="50" dataType="Require" msg="请填写产品型号！"/>';
<c:if test="${empty af.map.canNotEdit}">
var notFindPdHtml = '<span id="init_count_info" style="color:#A27D35;padding-left:5px;" align="left"> <img src="${ctx}/styles/client/images/qm_yellow.gif" style="vertical-align:middle;" /> 如果找不到型号，请点击<span id="notFindPd" style="color:#f00;padding:5px;cursor: pointer;">这里</span></span>';
var outSysIdHtml = '<select name="out_sys_id" multiple="multiple" style="display:none;" id="out_sys_id" styleClass="bdfont"></select>' + notFindPdHtml;
</c:if>
<c:if test="${not empty af.map.canNotEdit}">
var outSysIdHtml = '<select name="out_sys_id" id="out_sys_id" styleClass="bdfont"></select>'
</c:if>
var outSysIdHiddenHtml = '<input type="hidden" name="out_sys_id" id="out_sys_id" />'
$(document).ready(function(){
	initStyle();
	
   $(".infoTip").qtip({
			position: {
			my: 'left center', // Use the corner...
			at: 'right center'// ...and opposite corner
			}
	   });
	   
	$("#brand_name").attr("dataType","Require").attr("msg","请选择产品品牌！");
	$("#unit").attr("dataType","Require").attr("msg","请填写计量单位！");
	$("#init_count").attr("dataType","Integer").attr("msg","请填写期初库存,且只能为正整数据！").blur(function(){
		var this_value = $(this).val();
		$("#count").val(this_value);
	}).focus(setOnlyNum);

	//$("#init_count").focus(function() {
	//	$("#init_count_info").slideDown("fast");
	//}).blur(function() {
	//	$("#init_count_info").slideUp("middle");
	//});

	$("#ref_price").focus(setOnlyNum).attr("dataType","Require").attr("msg","请填写参考进货价！");
	$("#price").focus(setOnlyNum).attr("dataType","Require").attr("msg","请填写零售价！");
	$("#pf_price").focus(setOnlyNum).attr("dataType","Require").attr("msg","请填写批发价！");
	
	/*所属系统 Begin*/
	$("input[type='radio'][name='own_sys']").click(function(){
		$("#brand_name").val("");
		$("#brand_id").val("");
		$("#pd_id_span").empty();
		$("#tip").remove();
		var own_sys = $(this).val();
		if(0 == own_sys) { //非家电下乡
			$("#own_sys_info").empty().text("说明：该产品为非家电下乡产品或者是[没有标识卡号]的家电下乡产品");
			$("#pd_type option[value='0']").remove();
			$("#pd_type").get(0).options.add(new Option("其他", "0"));
			//删除产品大类select中重复选项 BEGIN
			var obj = $("#pd_type");

			//删除产品大类select中重复选项END
		} else { //家电下乡
			$("#own_sys_info").empty().text("说明：该产品为[有标识卡号]的家电下乡产品");
			$("#pd_id_span").empty();
			$("#otherPdType").hide();
			$("#jxc_pd_type_id").removeAttr("dataType").val("");
			$("#pd_type option[value='0']").remove();  
		}
	});
	/*所属系统 End*/
	
	/*产品类型 Begin*/
	$("#pd_type").change(function(){
		$("#brand_name").val("");
		$("#brand_id").val("");
		$("#pd_id_span").empty();
		$("#tip").remove();
		var pd_type = $(this).val();
		if(0 == pd_type) {
			$("#otherPdType").show();
			$("#jxc_pd_type_id").attr("dataType","Require").attr("msg","请选择其他产品类型！");
			$("#pd_id_span").html(nameHtml);
		} else {
			$("#otherPdType").hide();
			$("#jxc_pd_type_id").removeAttr("dataType").val("");
		}
	});
	/*产品类型 End*/
	
	/*  添加其他产品类型  Begin*/
	$("#addOtherPdTypeSpan").click(function(){
		$("#jxc_pd_type_name").val("");
		$("#addOtherPdTypeDiv").dialog({
			width: 220,
			height: 100,
			resizable: false,
			//position:'right',
			modal : true
		}).dialog("open");
	});

	/*  添加其他产品类型  End*/
	
	var f = document.forms[0];
	var k = 0;
	$("#btn_submit").click(function(){
		k++;
		var isSubmit = Validator.Validate(f, 3);
		if (isSubmit && k < 2){
			var out_sys_id = $("#out_sys_id").val() == undefined ? "" : $("#out_sys_id").val();
			var name = $("#name").val() == undefined ? "" : $("#name").val();
			var own_sys = $("input[type='radio'][name='own_sys']:checked").val();
			$("#tip").remove();
			//alert("out_sys_id:" + out_sys_id +"   name:"+ name +"  own_sys:"+ own_sys);
			$.ajax({
				type: "POST",
				url: "JxcPd.do",
				data: "method=validateName&id=${af.map.id}&keySeq=${af.map.keySeq}&out_sys_id=" + out_sys_id + "&name=" + name + "&own_sys=" + own_sys,
				dataType: "json",
				async: false,
				error: function(request, settings) {alert("数据加载请求失败！");k--;},
				success: function(datas) {
					if(datas.isExist == "1") {
						$("#pd_id_span").after('<span id="tip" style="color:#FF0000;">&nbsp;对不起，该型号产品'+ datas.msg +'已存在！<\/span>');
						k--;
					} else if (datas.isExist == "2") {
						$("#pd_id_span").after('<span id="tip" style="color:#FF0000;">&nbsp;密钥获取失败，请重新插入您的密钥！<\/span>');
						k--;
					} else {
						$(":submit").attr("value", "正在提交...").attr("disabled", "true");
						$(":button").attr("disabled", "true");
						$(":reset").attr("disabled", "true");
						f.submit();
					} 
				}
			});
		}else{
			k--;
		}
	});	
	
	$("#pd_type").change();
	
	$("input[type='radio'][name='own_sys']:checked").click();
	<c:if test="${not empty af.map.edit}">
	$("#init_count").attr("disabled","true");
	$("#brand_name").val("${af.map.brand_name}");
	$("#brand_id").val("${af.map.brand_id}");
	getProductType("${af.map.pd_type}", "${af.map.brand_id}", "${af.map.out_sys_id}")
	<c:if test="${not empty af.map.canNotEdit}">
		//disabledFormElements(f);
		//$("#btn_back").attr("disabled", "");
		$("input[name='own_sys'][type='radio']").attr("disabled","true");
		$("#pd_type").attr("disabled","true");
		$("#jxc_pd_type_id").attr("disabled","true");
		$("#brand_name").attr("disabled","true");
		$("#out_sys_id").attr("disabled","true");
		$("#name").attr("disabled","true");
		$("#out_sys_name_other").attr("disabled","true");
	</c:if>
	</c:if>

	if ("${af.map.jdxx_sale}" == "true") {
		$("#brand_name").val("${af.map.brand_name}");
		$("#brand_id").val("${af.map.brand_id}");
		getProductType("${af.map.pd_type}", "${af.map.brand_id}", "${af.map.pd_id}")
	}
	
});

function ajaxAddOtherPd(obj){
	var JQ_jptn = $("#jxc_pd_type_name");
	var jptn = JQ_jptn.val();
	if("" == $.trim(jptn)) {
		alert("请输入产品名称");
		return false;
	}
	$("#tip").remove();
	obj.disabled = true;
	$.ajax({
		type: "POST",
		url: "JxcPd.do",
		async : false,
		data: "method=saveJxcPdType&jxc_pd_type_name=" + jptn + "&keySeq=${af.map.keySeq}",
		dataType: "json",
		error: function(request, settings) {alert("数据加载请求失败！"); },
		success: function(id) {
			if(-1 != id) {
				$.jGrowl("恭喜，信息添加成功！",{theme:  'success'});
				$("#jxc_pd_type_id").get(0).options.add(new Option(jptn, id));
				$("#jxc_pd_type_id").val(id);
			} else {
				$.jGrowl("对不起，您添加的产品类型名称已存在！",{theme:  'error'});
			}
			obj.disabled = false;
			$("#addOtherPdTypeDiv").dialog("close");
		}
	});
}

function openChild(){
  //$("#out_sys_id").removeAttr("disabled").attr({"dataType":"Require","msg":"请选择产品型号！"});
  //$("#out_sys_name_other").val("").removeAttr("dataType").hide();
  //$("#out_sys_id_other").val("");
  var pd_type = $("#pd_type").val();
  var returnValue = window.showModalDialog("JxcPd.do?method=chooseBrand&pd_type="+pd_type+"&time="+new Date(), window, "dialogWidth:800px;status:no;dialogHeight:680px"); 
  	if(returnValue != null) {
	var value = new Array();
	value = returnValue.split(",");
   	document.forms[0].brand_name.value = value[0];
    document.forms[0].brand_id.value = value[1];
  }
  var brand_id = $("#brand_id").val();
  getProductType(pd_type, brand_id);
}

function openChildForPdModel(){
	var returnValue = window.showModalDialog("JxcPd.do?method=chooseAllPdModel&time=" + new Date().getTime(), window, "dialogWidth:800px;status:no;dialogHeight:680px");
	if (returnValue != null) {
		var value = new Array();
		value = returnValue.split(",");
		document.forms[0].out_sys_name_other.value = value[0];
		document.forms[0].out_sys_id.value = value[1];
		document.forms[0].pd_type.value = value[2];
		document.forms[0].brand_id.value = value[3];
		document.forms[0].brand_name.value = value[4];
	}
}

function getProductType(pd_type, brand_id, out_sys_id){
	if (pd_type == "" || brand_id == "") {
		return false;
	}
	var own_sys = $("input[type='radio'][name='own_sys']:checked").val();
	if (undefined != out_sys_id && 0 == own_sys && "" != "${af.map.name}" && out_sys_id < 0) {
		$("#pd_id_span").empty().append(nameHtml)
		return false;
	}
	$.ajax({
		type: "POST",
		url: 'JxcPd.do',
		data: "method=getPdModel&pd_type=" + pd_type +"&brand_id=" + brand_id + "&keySeq=${af.map.keySeq}",
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
					$("#out_sys_id").get(0).options.add(new Option(md_name, Dates[i].pd_id));
				}
				<c:if test="${empty af.map.canNotEdit}">
					var el = $("#out_sys_id").multiselect({
						selectedList: 1,
						multiple: false,
						minWidth:380
					}).multiselectfilter({width:280}).attr({"datatype" : "Ms" , "msg" : "请选择产品型号！"});
				   	$("#out_sys_id option[value='${af.map.out_sys_id}']").attr("selected", "selected");
					el.multiselect('refresh');
				</c:if>
			} else {
				var own_sys = $("input[type='radio'][name='own_sys']:checked").val();
				if (0 == own_sys){
					$("#pd_id_span").html(nameHtml);
					
				} else {
					$("#pd_id_span").append('<span style="color:#f00;padding:5px;">没有型号,请选择型号：</span>').append(getPdModelListHtml).append(outSysIdHiddenHtml);
					//alert($("#pd_id_span").html());
				}
			}
		}
	});
	if(undefined != out_sys_id && "" != out_sys_id){
		$("#out_sys_id").val(out_sys_id);
	}
}
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
function gotoJxcBrandApply(){
	window.location.href="JxcBrandApply.do?method=add&keySeq=${af.map.keySeq}";
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
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>