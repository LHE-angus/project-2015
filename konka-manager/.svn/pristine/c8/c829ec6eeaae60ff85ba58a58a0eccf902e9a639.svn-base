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
	<html-el:form action="/spgl/EcBcompPdRebates" method="post">  	
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>商品：</td>
			<td width="88%" align="left">
				<html-el:hidden property="goods_id" styleId="goods_id" />
				<html-el:text property="pd_name" styleId="pd_name" size="40" styleClass="webinput" value="${pd_name}" readonly="true" onclick="${not empty af.map.id ?'':'openWindow();'}" />
			</td>
		</tr>
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

function openWindow(){
	 window.open("SelectKonkaBcompPd.do?selectype=signal&azaz=" + Math.random(),'window','height=450,width=600,top=150,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
}

function set_value(goods_id,pd_name){
	$("#goods_id").val(goods_id);
	$("#pd_name").val(pd_name);
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>