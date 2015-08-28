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
    <html-el:form action="/spgl/EcExtend" method="post">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
      	<tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>产品类型：</td>
          <td width="88%" align="left">
          	<html-el:select property="prod_type" styleId="prod_type">
          		<html-el:option value="0">彩电</html-el:option>
          		<html-el:option value="1">白电</html-el:option>
          		<html-el:option value="3">小家电</html-el:option>
          		<html-el:option value="10">配件</html-el:option>
          	</html-el:select>
           </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>属性名称：</td>
          <td width="88%" align="left">
          	<html-el:text property="prop_name" styleId="prop_name" size="15" maxlength="15"/>
           </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>属性值：</td>
          <td width="88%" align="left">
          	<html-el:text property="prop_value" styleId="prop_value" size="15" maxlength="15"/>
           </td>
        </tr>
        <tr >
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>商品：</td>
          <td width="88%" align="left">
	          	<html-el:hidden property="link_id" styleId="link_id" />
				<html-el:text property="pd_name" styleId="pd_name" size="40" styleClass="webinput" value="${af.map.pd_name}" readonly="true" onclick="${not empty af.map.id ?'openWindow();':'openWindow();'}" />
           </td>
        </tr>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">排序值：</td>
          <td width="88%" align="left">
          	<html-el:text property="prop_order_value" styleId="prop_order_value" size="10" maxlength="15"/>
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

	$("#prop_name").attr("dataType", "Require").attr("msg", "请填写属性名称");
	$("#prop_value").attr("dataType", "Require").attr("msg", "请填写属性值");
	$("#link_id").attr("dataType", "Require").attr("msg", "请选择商品");
	$("#prop_order_value").focus(function(){setOnlyInt(this);});


	$("#btn_submit").click(function(){
		
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
	$("#link_id").val(goods_id);
	$("#pd_name").val(pd_name);
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
