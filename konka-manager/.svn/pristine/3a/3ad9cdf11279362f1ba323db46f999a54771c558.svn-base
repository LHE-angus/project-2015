<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/spgl/EcBindingPd" method="post">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
       <html-el:hidden property="type" styleId="type" value="0"/>
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
      	<tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>套餐名称：</td>
          <td width="88%" align="left">
          	<html-el:text property="goods_name" styleId="goods_name" size="40" maxlength="40" />
           </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>价格：</td>
          <td width="88%" align="left">
          	<html-el:text property="price" styleId="price" size="15" maxlength="15"/>
           </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">类型：</td>
          <c:if test="${ empty af.map.id }">
          <td width="88%" align="left">
          	<html-el:radio property="binding_type" styleClass="binding_type" value="0" styleId="d1">服务套餐</html-el:radio>
          	<html-el:radio property="binding_type" styleClass="binding_type" value="1" styleId="d2">商品套餐</html-el:radio>
           </td>
           </c:if>
           <c:if test="${ not empty af.map.id }">
          	<td width="88%" align="left">
          	<html-el:radio property="binding_type" styleClass="binding_type" value="0" styleId="d1" disabled="true">服务套餐</html-el:radio>
          	<html-el:radio property="binding_type" styleClass="binding_type" value="1" styleId="d2" disabled="true">商品套餐</html-el:radio>
           	</td>
           </c:if>
        </tr>
        <tr id="t1" style="display: none">
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>产品：</td>
          <td width="88%" align="left">
	          	<html-el:hidden property="goods_id" styleId="goods_id" />
				<html-el:text property="pd_name" styleId="pd_name" size="40" styleClass="webinput" value="${pd_name}" readonly="true" onclick="${not empty af.map.id ?'':'openWindow();'}" />
           </td>
        </tr>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">排序值：</td>
          <td width="88%" align="left">
          	<html-el:text property="order_value" styleId="order_value" size="10" maxlength="15"/>
           </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="" value="提交" styleClass="but4" styleId="btn_submit" />
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();return false;" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#goods_name").attr("dataType", "Require").attr("msg", "请填写套餐名称");
	$("#price").attr("dataType", "Require").attr("msg", "请填写套餐价格");
	$("#order_value").focus(function(){setOnlyInt(this);});

	$("#price").keypress(function(){
		var price = $("#price").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#price").val(0);
		}
	}).keyup(function(){
		var price = $("#price").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#price").val(0);
		}
	}).blur(function(){
		var price = $("#price").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#price").val(0);
		}
	});	

	$(".binding_type").click(function(){
		if($(this).val() == 1){
			$("#t1").show();
			$("#type").val(1);
		}else{
			$("#t1").hide();
			$("#type").val(0);
		}
	});

	$("#btn_submit").click(function(){
		
		if($("#type").val() == 1){
			$("#goods_id").attr("dataType", "Require").attr("msg", "请选择产品");
		}
		
		var isSubmit = Validator.Validate(this.form, 3);
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
		}
	});
});

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

function openWindow(){
	 window.open("SelectKonkaBcompPdForBinding.do?azaz=" + Math.random(),'window','height=450,width=600,top=150,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
}

function set_value(goods_id,pd_name){
	$("#goods_id").val(goods_id);
	$("#pd_name").val(pd_name);
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
