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
  <div class="rtabcont2">
	<html-el:form action="/spgl/EcBcompPdRebates" method="post">  	
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save2" />
      <html-el:hidden property="queryString" styleId="queryString" />
	  <html-el:hidden property="goods_id" styleId="goods_id" value="${af.map.goods_id}" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
      <c:if test="${af.map.fgs_id gt 0 and empty af.map.epp_fgs}">
        <tr>
	          <td align="right" nowrap="nowrap" class="title_item"><font color="red">* </font>选择R3客户：</td>
	          <td><html-el:text readonly="true" property="r3_code" styleId="r3_code" style="width:150px;" size="30" maxlength="30" />
	          	  <html-el:hidden property="c_id" styleId="c_id" value="" /> 
	          </td>
      	</tr></c:if>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>返利类型：</td>
			<td align="left">
				<html-el:select property="b_type" styleId="b_type">
					<html-el:option value="">请选择...</html-el:option>
					<html-el:option value="0">按比例</html-el:option>
					<html-el:option value="1">固定金额</html-el:option>
				</html-el:select>
			</td>
		</tr>   
		<tr>
			<td nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>数值/比例（%）：</td>
			<td align="left"><html-el:text property="b_value" styleId="b_value" styleClass="webinput" size="10" maxlength="10" />
				<span id="point_b_type" style="font-size:15px;"></span></td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">备注：</td>
			<td align="left"><html-el:textarea property="remarks" styleId="remarks" rows="5" style="width:300px;"></html-el:textarea></td>
		</tr> 
		<tr>
			<td align="center" colspan="2">
				<input class="but4" type="button" name="Submit4" value="保存" id="btn_submit" />
				<input class="but5" type="button" name="Submit5" value="返回" id="btn_back" onclick="history.back()" />
			</td>
		</tr>       
      </table>
    </html-el:form>
  </div>
  
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#pd_name").attr("dataType", "Require").attr("msg", "请选择商品！"); 
	<c:if test="${af.map.fgs_id >0 and empty af.map.epp_fgs}">
	$("#c_id").attr("dataType", "Require").attr("msg", "请选择R3客户！");</c:if>
	$("#b_type").attr("dataType", "Require").attr("msg", "请选择返利类型！");
	$("#b_value").attr("dataType", "Require").attr("msg", "请填写数值！");
	$("#remarks").attr("dataType", "LimitB").attr("max","240").attr("msg", "备注不能超过240个汉字！");
	
	$("#b_type").change(function(){
		if($(this).val() != "" && $(this).val() == 0){
			$("#point_b_type").text(" %");
		}else if($(this).val() != "" && $(this).val() == 1){
			$("#point_b_type").text(" 元");
		}else {
			$("#point_b_type").text("");
		}
		
	});
	

	// 选择客户
	$("#r3_code").click(function(){
		var fgs_id = '${af.map.fgs_id}';
		if ($.trim(fgs_id).length == 0) {
			alert("对不起！你不是分公司管理员");
			return;
		}
		var returnValue = window.showModalDialog("${ctx}/manager/admin/KonkaR3Store.do?method=listCustomer&is_xx=0&fgs_id=" + fgs_id +"&ts=" + Math.random(), window, "dialogWidth:800px;status:no;dialogHeight:600px");
		if (!returnValue) returnValue = window.returnValue;
		$("#r3_code").val(returnValue.cust_name);
		$("#c_id").val(returnValue.cust_id);
	});

	
	$("#btn_submit").click(function(){
		var isSubmit = Validator.Validate(this.form, 3);
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
		}
	});
});

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>