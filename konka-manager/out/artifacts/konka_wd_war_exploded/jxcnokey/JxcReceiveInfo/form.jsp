<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>退换货管理 &gt; 退货申请</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：退换货管理 &gt; 退货申请</div>
  <div class="rtabcont1">
    <html-el:form action="/JxcThApply">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="keySeq" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="2">添加盘存记录</th>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap"  class="title_item"><font color="red">*</font>所属系统：</td>
          <td width="85%" align="left"><label for="own_sys_0">
              <html-el:radio property="own_sys" styleId="own_sys_0" value="0">非家电下乡</html-el:radio>
            </label>
            &nbsp;
            <label for="own_sys_1">
              <html-el:radio property="own_sys" styleId="own_sys_1" value="1" >家电下乡</html-el:radio>
            </label></td>
        </tr>
        <tr>
          <td width="15%"  nowrap="nowrap"  class="title_item"><font color="red">*</font>产品类型：</td>
          <td width="85%" align="left"><html-el:select property="pd_type_id" styleId="pd_type_id" styleClass="bdfont" style="width:170px">
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
          <td  class="title_item"><font color="red">*</font>产品品牌：</td>
          <td align="left"><html-el:text styleClass="webinput" title="点击选择产品品牌" property="brand_name" styleId="brand_name" style="cursor:pointer" readonly="true" onclick="openChild()" />
            <html-el:hidden property="brand_id" styleId="brand_id" /></td>
        </tr>
        <tr id="jdxx">
          <td class="title_item"><font color="red" >*</font>产品型号：</td>
          <td align="left"><span id="pd_id_span"></span></td>
        </tr>
        <tr>
          <td class="title_item"><font color="red" >*</font>退货产品类型：</td>
          <td><html-el:select property="th_type">
              <html-el:option value="1">正品</html-el:option>
              <html-el:option value="0">残次品</html-el:option>
            </html-el:select></td>
        </tr>
        <tr>
          <td class="title_item"><font color="red" >*</font>退货数量：</td>
          <td><html-el:text property="th_count" styleId="th_count" maxlength="6" size="6" styleClass="webinput" /></td>
        </tr>
        <tr>
          <td class="title_item"><font color="red" >*</font>退货原因：</td>
          <td><html-el:text property="th_reason" styleId="th_reason" maxlength="100" style="width:60%" styleClass="webinput" /></td>
        </tr>
        <tr>
          <td class="title_item">备注：</td>
          <td><html-el:text property="remark" maxlength="100" style="width:60%"  styleClass="webinput" /></td>
        </tr>
        <tr>
          <td colspan="2" align="center"><html-el:button property="save" styleClass="bgButtonSave" styleId="btn_submit" value="保存"/>
            <html-el:button property="back" styleClass="bgButtonBack" value="返回" onclick="history.back();" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script> 
<script type="text/javascript">//<![CDATA[
var outSysIdHtml = '<select name="pd_id" id="pd_id" styleClass="bdfont"></select>'
var f = document.forms[0];                                          

$(document).ready(function() {

	$("#th_count").attr("dataType", "Number").attr("msg", "请填写退货数量，且必须是正整数！");
	$("#brand_name").attr("dataType","Require").attr("msg","请选择产品品牌！");
	$("#th_reason").attr("dataType","Require").attr("msg","请填写退货原因！");

	/*所属系统 Begin*/
	$("input[type='radio'][name='own_sys']").click(function(){
		$("#brand_name").val("");
		$("#brand_id").val("");
		$("#pd_id_span").empty();
		$("#tip").remove();
		var own_sys = $(this).val();
		if(0 == own_sys) { //非家电下乡
			$("#pd_type_id option[value='0']").remove();
			$("#pd_type_id").get(0).options.add(new Option("其他", "0"));
		} else { //家电下乡
			$("#pd_id_span").empty();
			$("#otherPdType").hide();
			$("#jxc_pd_type_id").removeAttr("dataType").val("");
			$("#pd_type_id option[value='0']").remove();  
		}
	});
	/*所属系统 End*/
	
	/*产品类型 Begin*/
	$("#pd_type_id").change(function(){
		$("#brand_name").val("");
		$("#brand_id").val("");
		$("#pd_id_span").empty();
		$("#tip").remove();
		var pd_type_id = $(this).val();
		if(0 == pd_type_id) {
			$("#otherPdType").show();
			$("#jxc_pd_type_id").attr("dataType","Require").attr("msg","请选择其他产品类型！");
		} else {
			$("#otherPdType").hide();
			$("#jxc_pd_type_id").removeAttr("dataType").val("");
		}
	});
	/*产品类型 End*/
	
	/*其他大类产品类型 Begin*/
	$("#jxc_pd_type_id").change(function(){
		$("#brand_name").val("");
		$("#brand_id").val("");
		$("#pd_id_span").empty();
		$("#tip").remove();
	});
	/*其他大类产品类型*/
	
	var f = document.forms[0];
	$("#btn_submit").click(function(){
		var isSubmit = Validator.Validate(f, 3);
		if (isSubmit){
			$(":submit").attr("value", "正在提交...").attr("disabled", "true");
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			f.submit();
		}
	});	
	
	$("input[type='radio'][name='own_sys']:checked").click();
	
});

function openChild(){
  var pd_type = $("#pd_type_id").val();
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

function getProductType(pd_type, brand_id, pd_id){
	if (pd_type == "" || brand_id == "") {
		return false;
	}
	var jxc_pd_type_id = $("#jxc_pd_type_id").val();
	var own_sys = $("input[type='radio'][name='own_sys']:checked").val();
	if (undefined != pd_id && 0 == own_sys && "" != "${af.map.name}" && pd_id < 0) {
		$("#pd_id_span").empty().append(nameHtml)
		return false;
	}
	$.ajax({
		type: "POST",
		url: 'JxcThApply.do',
		data: "method=getJxcPdList&pd_type=" + pd_type +"&brand_id=" + brand_id  + "&own_sys=" + own_sys  + "&jxc_pd_type_id=" + jxc_pd_type_id + "&keySeq=${af.map.keySeq}",
		dataType: "json",
		async: false,
		error: function(request, settings) {alert("数据加载请求失败！"); },
		success: function(Dates) {
			$("#pd_id_span").empty();
			if (Dates.length > 1) {
				$("#pd_id_span").html(outSysIdHtml);
				for ( var i = 0; i < Dates.length - 1; i++) {
					var md_name = Dates[i].name;
					$("#pd_id").get(0).options.add(new Option(md_name, Dates[i].id));
				}
				var el = $("#pd_id").multiselect({
					selectedList: 1,
					multiple: false,
					minWidth:320
				}).multiselectfilter({width:220}).attr({"datatype" : "Ms" , "msg" : "请选择产品型号！"});
			} else {
				$("#pd_id_span").append('<span style="color:#f00;padding:5px;">没有数据</span>');
			}
		}
	});
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

//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
