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
    <html-el:form action="/admin/BaseVisitType"  method="post">
      <html-el:hidden property="visit_type_id" styleId="visit_type_id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <c:set var="readOnly"  value="${empty af.map.visit_type_id?false:true}" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>上报类型：</td>
          <td width="88%" align="left">
            <html-el:select property="report_type" styleId="report_type" style="width:120px;">
               <html-el:option value="">请选择上报类型</html-el:option>
       		   <html-el:option value="-1">通用</html-el:option>
                <html-el:option value="1">正常客户拜访</html-el:option>
				<html-el:option value="2">老客户重拾</html-el:option>
				<html-el:option value="3">新客户开拓</html-el:option>
				<html-el:option value="4">事务上报</html-el:option>
            </html-el:select>&nbsp;
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>拜访类型：</td>
          <td width="88%" align="left">
          <html-el:select property="type_name" styleId="type_name" style="width:120px;">
			       <html-el:option value="">请选择拜访类型</html-el:option>
	      		   <html-el:option value="库存">库存</html-el:option>
	      		   <html-el:option value="样机">样机</html-el:option>
	      		   <html-el:option value="售后">售后</html-el:option>
	      		   <html-el:option value="费用">费用</html-el:option>
	      		   <html-el:option value="结构调整">结构调整</html-el:option>
	      		   <html-el:option value="价格">价格</html-el:option>
	      		   <html-el:option value="窜货">窜货</html-el:option>
	      		   <html-el:option value="演示设备">演示设备</html-el:option>
	      		   <html-el:option value="培训">培训</html-el:option>
	      		   <html-el:option value="促销">促销</html-el:option>
	      		   <html-el:option value="其他">其他</html-el:option>
        	   </html-el:select>
        	   <html-el:text property="visit_type_name" styleId="visit_type_name"></html-el:text>
          </td>
        </tr>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>状态：</td>
          <td width="88%" align="left">
          <html-el:select property="state" styleId="state" style="width:120px;">
      		   <html-el:option value="0">启用</html-el:option>
      		   <html-el:option value="1">停用</html-el:option>
        	 </html-el:select>
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
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script> 

<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#report_type").attr("datatype", "Require").attr("msg", "请选择上报类型");
	$("#visit_type_name").attr("datatype", "Require").attr("msg", "请选择拜访类型");
	$("#state").attr("datatype", "Require").attr("msg", "请选择状态");
	
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
			if(confirm("确定提交表单？")){
	            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	            $("#btn_back").attr("disabled", "true");
				this.form.submit();
			} else {
				return false;
			}
		}
	});
});
$("#type_name").change(function(){
	$("#visit_type_name").val($(this).val());
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

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
